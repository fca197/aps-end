package com.olivia.peanut.aps.service.impl;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.date.DatePattern;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.ReflectUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.github.yulichang.base.MPJBaseServiceImpl;
import com.github.yulichang.wrapper.MPJLambdaWrapper;
import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.olivia.peanut.aps.api.entity.apsGoodsForecast.*;
import com.olivia.peanut.aps.api.entity.apsGoodsSaleItem.ApsGoodsSaleItemDto;
import com.olivia.peanut.aps.api.entity.apsGoodsSaleItem.ApsGoodsSaleItemExportQueryPageListInfoRes;
import com.olivia.peanut.aps.api.entity.apsGoodsSaleItem.ApsGoodsSaleItemExportQueryPageListReq;
import com.olivia.peanut.aps.mapper.ApsGoodsForecastMapper;
import com.olivia.peanut.aps.model.*;
import com.olivia.peanut.aps.service.*;
import com.olivia.peanut.aps.service.impl.utils.ApsGoodsForecastUtils;
import com.olivia.peanut.aps.utils.forecast.OrToolsUtils;
import com.olivia.peanut.aps.utils.forecast.model.DivisionRes;
import com.olivia.peanut.aps.utils.forecast.model.SaleItemConfig;
import com.olivia.peanut.aps.utils.forecast.model.SkuGroup;
import com.olivia.sdk.ann.SetUserName;
import com.olivia.sdk.comment.ServiceComment;
import com.olivia.sdk.config.PeanutProperties;
import com.olivia.sdk.dto.ExcelErrorMsg;
import com.olivia.sdk.exception.CanIgnoreException;
import com.olivia.sdk.exception.RunException;
import com.olivia.sdk.utils.*;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.springframework.aop.framework.AopContext;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static com.olivia.sdk.utils.FieldUtils.getField;

/**
 * (ApsGoodsForecast)表服务实现类
 *
 * @author peanut
 * @since 2024-03-30 13:38:53
 */
@Slf4j
@Service("apsGoodsForecastService")
@Transactional
public class ApsGoodsForecastServiceImpl extends MPJBaseServiceImpl<ApsGoodsForecastMapper, ApsGoodsForecast> implements ApsGoodsForecastService {

  final static Cache<String, Map<String, String>> cache = CacheBuilder.newBuilder().maximumSize(100).expireAfterWrite(30, TimeUnit.MINUTES).build();

  @Resource
  ApsGoodsSaleItemService goodsSaleItemService;
  @Resource
  ApsGoodsForecastUserGoodsDataService goodsForecastUserGoodsDataService;
  @Resource
  ApsGoodsForecastUserSaleDataService goodsForecastUserSaleDataService;
  @Resource
  ApsSaleConfigService saleConfigService;
  @Resource
  ApsGoodsForecastComputeSaleDataService goodsForecastComputeSaleDataService;
  @Resource
  ApsGoodsForecastMainService goodsForecastMainService;
  @Resource
  ApsGoodsForecastMainSaleDataService goodsForecastMainSaleDataService;
  @Resource
  ApsGoodsService apsGoodsService;
  @Resource
  PeanutProperties peanutProperties;

  public @Override ApsGoodsForecastQueryListRes queryList(ApsGoodsForecastQueryListReq req) {

    MPJLambdaWrapper<ApsGoodsForecast> q = getWrapper(req.getData());
    List<ApsGoodsForecast> list = this.list(q);

    List<ApsGoodsForecastDto> dataList = list.stream().map(t -> $.copy(t, ApsGoodsForecastDto.class)).collect(Collectors.toList());
    ((ApsGoodsForecastServiceImpl) AopContext.currentProxy()).setName(dataList);

    return new ApsGoodsForecastQueryListRes().setDataList(dataList);
  }

  public @Override DynamicsPage<ApsGoodsForecastExportQueryPageListInfoRes> queryPageList(ApsGoodsForecastExportQueryPageListReq req) {

    DynamicsPage<ApsGoodsForecast> page = new DynamicsPage<>();
    page.setCurrent(req.getPageNum()).setSize(req.getPageSize());
    setQueryListHeader(page);
    MPJLambdaWrapper<ApsGoodsForecast> q = getWrapper(req.getData());
    List<ApsGoodsForecastExportQueryPageListInfoRes> records;
    if (Boolean.TRUE.equals(req.getQueryPage())) {
      IPage<ApsGoodsForecast> list = this.page(page, q);
      IPage<ApsGoodsForecastExportQueryPageListInfoRes> dataList = list.convert(t -> $.copy(t, ApsGoodsForecastExportQueryPageListInfoRes.class));
      records = dataList.getRecords();
    } else {
      records = $.copyList(this.list(q), ApsGoodsForecastExportQueryPageListInfoRes.class);
    }

    // 类型转换，  更换枚举 等操作

    List<ApsGoodsForecastExportQueryPageListInfoRes> listInfoRes = $.copyList(records, ApsGoodsForecastExportQueryPageListInfoRes.class);
    ((ApsGoodsForecastServiceImpl) AopContext.currentProxy()).setName(listInfoRes);

    return DynamicsPage.init(page, listInfoRes);
  }

  @SetUserName
  public @Override void setName(List<? extends ApsGoodsForecastDto> apsGoodsForecastDtoList) {

  }

  @Override
  public void downloadTemplate(Long id) {
    ApsGoodsForecastUtils.downloadTemplate(id);

  }

  @Override
  @Transactional
  public UploadTemplateRes uploadTemplate(Long id, MultipartFile multipartFile) {
    ApsGoodsForecast goodsForecast = this.getById(id);
    List<ApsSaleConfig> apsSaleConfigList = saleConfigService.list();
    Map<Long, ApsSaleConfig> apsSaleConfigMap = apsSaleConfigList.stream().collect(Collectors.toMap(BaseEntity::getId, Function.identity()));
    Map<String, ApsSaleConfig> saleConfigMap = apsSaleConfigList.stream().collect(Collectors.toMap(ApsSaleConfig::getSaleCode, Function.identity(), (a, b) -> a));
//    List<ApsGoodsForecastUserGoodsData> userGoodsDataList = new ArrayList<>();
    List<String> monthList = goodsForecast.getMonthList();
    //monthList ["2025-01","2025-02","2025-03","2025-04","2025-05","2025-06","2025-07","2025-08","2025-09","2025-10"]
    log.info("uploadTemplate {} monthList : {}", id, monthList);
//    List<Integer> yearList = monthList.stream().map(t -> t.substring(0, 4)).distinct().map(Integer::valueOf).toList();

    Map<String, ApsGoodsForecastUserGoodsData> goodsDataMap = new LinkedHashMap<>();
    Map<String, ApsGoodsForecastUserSaleData> goodsForecastUserSaleDataMap = new LinkedHashMap<>();
    try (Workbook workbook = WorkbookFactory.create(multipartFile.getInputStream())) {
      List<ExcelErrorMsg> excelErrorMsgList = new ArrayList<>();
      Sheet sheet = workbook.getSheetAt(0);
      // header
      IntStream.range(3, monthList.size() + 3).forEach(i -> {
        if (!Objects.equals(sheet.getRow(0).getCell(i).getStringCellValue().substring(1), monthList.get(Math.min(i - 3, monthList.size())))) {
          excelErrorMsgList.add(new ExcelErrorMsg().setColumnIndex(i + 3).setRowIndex(1).setErrMsg("月份不匹配"));
        }
      });
      IntStream.range(3, monthList.size() + 3).forEach(i -> {
        BigDecimal bigDecimal = BigDecimal.valueOf(sheet.getRow(1).getCell(i).getNumericCellValue());
        String moth = monthList.get(i - 3);
        log.info("row:{} moth:{} columnIndex {} {}", 1, moth, i, bigDecimal);
        String key = moth.substring(0, 4);
        ApsGoodsForecastUserGoodsData goodsData = goodsDataMap.getOrDefault(key, new ApsGoodsForecastUserGoodsData());
        goodsData.setForecastId(id).setYear(Integer.valueOf(key));
        ReflectUtil.setFieldValue(goodsData, "month" + moth.substring(5), bigDecimal.intValue());
        goodsDataMap.put(key, goodsData);

      });
      log.info("goodsDataMap {}", JSON.toJSONString(goodsDataMap));
      IntStream.range(3, sheet.getLastRowNum() + 1).forEach(t -> {
        IntStream.range(3, monthList.size() + 3).forEach(i -> {
          BigDecimal bigDecimal = BigDecimal.valueOf(sheet.getRow(t).getCell(i).getNumericCellValue());
          String moth = monthList.get(i - 3);
          ApsSaleConfig apsSaleConfig = saleConfigMap.get(sheet.getRow(t).getCell(1).getStringCellValue().split("/")[0]);
          Long saleConfigId = apsSaleConfig.getId();
          log.info("row:{} moth:{}  columnIndex {} {}", t, moth, i, bigDecimal);
          String key = moth.substring(0, 4) + "-" + saleConfigId;
          ApsGoodsForecastUserSaleData saleData = goodsForecastUserSaleDataMap.getOrDefault(key, new ApsGoodsForecastUserSaleData());
          saleData.setForecastId(id).setSaleConfigParentId(apsSaleConfig.getParentId()).setSaleConfigId(saleConfigId).setYear(Integer.valueOf(moth.substring(0, 4)));

          ReflectUtil.setFieldValue(saleData, "month" + moth.substring(5), bigDecimal);
          goodsForecastUserSaleDataMap.put(key, saleData);
        });
      });
      Collection<ApsGoodsForecastUserSaleData> forecastUserSaleDataList = goodsForecastUserSaleDataMap.values();


      Map<Integer, Map<Long, List<ApsGoodsForecastUserSaleData>>> apsGoodsForecastUserSaleDataMap = new HashMap<>();
      forecastUserSaleDataList.stream().collect(Collectors.groupingBy(ApsGoodsForecastUserSaleData::getYear))//
          .forEach((p, sl) -> {
            Map<Long, List<ApsGoodsForecastUserSaleData>> listMap = sl.stream().collect(Collectors.groupingBy(ApsGoodsForecastUserSaleData::getSaleConfigParentId));
            apsGoodsForecastUserSaleDataMap.put(sl.getFirst().getYear(), listMap);
          });

      monthList.forEach(month -> {
        String[] ym = month.split("-");
        Integer year = Integer.valueOf(ym[0]);
        apsGoodsForecastUserSaleDataMap.get(year).forEach((p, sl) -> {
          BigDecimal totalBigDecimal = sl.stream().map(t -> (BigDecimal) FieldUtils.getFieldValue(t, getField(ApsGoodsForecastUserSaleData.class, "month" + ym[1]))).reduce(BigDecimal::add).orElse(BigDecimal.ZERO);
          Long saleConfigParentId = sl.getFirst().getSaleConfigParentId();
          log.info("ID：{} totalBigDecimal  month:  {} {} {}", id, month, totalBigDecimal, saleConfigParentId);
          if (totalBigDecimal.compareTo(BigDecimal.ONE) != 0) {
            excelErrorMsgList.add(new ExcelErrorMsg()
                .setErrMsg(apsSaleConfigMap.get(saleConfigParentId).getSaleName() + "在" + month + "总和不为1,值为" + totalBigDecimal));
          }
        });
      });


      if (CollUtil.isNotEmpty(excelErrorMsgList)) {
        return new UploadTemplateRes().setExcelErrorMsgList(excelErrorMsgList).setSubCode(300);
      }
      // 数据保存
      this.goodsForecastUserGoodsDataService.remove(new LambdaQueryWrapper<ApsGoodsForecastUserGoodsData>().eq(ApsGoodsForecastUserGoodsData::getForecastId, id));
      this.goodsForecastUserGoodsDataService.saveBatch(goodsDataMap.values());
      this.goodsForecastUserSaleDataService.remove(new LambdaQueryWrapper<ApsGoodsForecastUserSaleData>().eq(ApsGoodsForecastUserSaleData::getForecastId, id));
      this.goodsForecastUserSaleDataService.saveBatch(forecastUserSaleDataList);
      this.update(new LambdaUpdateWrapper<ApsGoodsForecast>().eq(ApsGoodsForecast::getId, id).set(ApsGoodsForecast::getForecastStatus, ForecastStatusEnum.TO_COMPUTED.getCode()));
    } catch (Exception e) {
      log.error("excel 解析错误", e);
      throw new RunException(e);
    }
    return new UploadTemplateRes();
  }

  @Override
  public DynamicsPage<GetForecastDataByIdRes> getForecastDataById(GetForecastDataByIdReq req) {
    ApsGoodsForecast goodsForecast = this.getById(req.getId());
    $.requireNonNullCanIgnoreException(goodsForecast, "未找到该预测数据");
    Map<String, ApsGoodsForecastUserGoodsData> goodsDataMap = this.goodsForecastUserGoodsDataService.list(new LambdaQueryWrapper<ApsGoodsForecastUserGoodsData>().eq(ApsGoodsForecastUserGoodsData::getForecastId, req.getId())).stream().collect(Collectors.toMap(t -> String.valueOf(t.getYear()), t -> t, (a, b) -> a));

    Map<Long, ApsSaleConfig> apsSaleConfigMap = this.saleConfigService.list().stream().collect(Collectors.toMap(BaseEntity::getId, v -> v, (a, b) -> a));
    List<String> monthListTmp = goodsForecast.getMonthList();
    List<String> monthList = CollUtil.isEmpty(monthListTmp) ? List.of() : monthListTmp;
    DynamicsPage<GetForecastDataByIdRes> dynamicsPage = new DynamicsPage<>();
    dynamicsPage.addHeader("group", "销售特征组", 200);
    dynamicsPage.addHeader("value", "销售特征组值", 200);
    dynamicsPage.addHeader("month", "月份", 200);
    monthList.forEach(t -> {
      dynamicsPage.addHeader(t, t, 200);
    });
    List<GetForecastDataByIdRes> dataList = new ArrayList<>();
    GetForecastDataByIdRes data = new GetForecastDataByIdRes();
    data.put("month", "总计");
    monthList.forEach(t -> {
      String year = t.substring(0, 4);
      ApsGoodsForecastUserGoodsData goodsData = goodsDataMap.get(year);
      Field field = getField(goodsData, "month" + t.substring(5));
      data.put(t, FieldUtils.getFieldValue(goodsData, field));
    });
    data.setSaleConfigCode("");
    dataList.add(data);

    Map<String, ApsGoodsForecastUserSaleData> forecastUserSaleDataMap = this.goodsForecastUserSaleDataService.list(new LambdaQueryWrapper<ApsGoodsForecastUserSaleData>().eq(ApsGoodsForecastUserSaleData::getForecastId, req.getId())).stream().collect(Collectors.toMap(t -> t.getSaleConfigId() + "-" + t.getYear(), t -> t, (a, b) -> a));

    List<ApsGoodsSaleItemExportQueryPageListInfoRes> queryPageListInfoResList = this.goodsSaleItemService.queryPageList(new ApsGoodsSaleItemExportQueryPageListReq().setData(new ApsGoodsSaleItemDto().setGoodsId(goodsForecast.getGoodsId()))).getDataList();

    queryPageListInfoResList.forEach(t -> {
      GetForecastDataByIdRes tmp = new GetForecastDataByIdRes();
      ApsSaleConfig apsSaleConfig = apsSaleConfigMap.get(t.getSaleConfigId());
      if (Objects.isNull(apsSaleConfig) || apsSaleConfig.getIsValue() == 0) {
        return;
      }
      ApsSaleConfig parentSaleConfig = apsSaleConfigMap.getOrDefault(apsSaleConfig.getParentId(), new ApsSaleConfig());
      if (Objects.isNull(parentSaleConfig)) {
        return;
      }
      tmp.put("group", parentSaleConfig.getSaleCode() + "/" + parentSaleConfig.getSaleName());
      tmp.put("value", apsSaleConfig.getSaleCode() + "/" + apsSaleConfig.getSaleName());
      tmp.setSaleConfigCode(apsSaleConfig.getSaleCode());
      monthList.forEach(m -> {
        ApsGoodsForecastUserSaleData saleData = forecastUserSaleDataMap.get(t.getSaleConfigId() + "-" + m.substring(0, 4));
        Field field = getField(saleData, "month" + m.substring(5));
        tmp.put(m, FieldUtils.getFieldValue(saleData, field));
      });

      dataList.add(tmp);
    });
    dataList.sort(Comparator.comparing(GetForecastDataByIdRes::getSaleConfigCode));
    return dynamicsPage.setDataList(dataList);
  }

  @Override
  @Transactional
  public ComputeRes compute(ComputeReq req) {
    ApsGoodsForecast goodsForecast = this.getById(req.getId());
    $.requireNonNullCanIgnoreException(goodsForecast, "未找到该预测数据");
    Map<String, ApsGoodsForecastUserGoodsData> userSaleDataMap = this.goodsForecastUserGoodsDataService.list(new LambdaQueryWrapper<ApsGoodsForecastUserGoodsData>().eq(ApsGoodsForecastUserGoodsData::getForecastId, req.getId())).stream().collect(Collectors.toMap(t -> String.valueOf(t.getYear()), f -> f, (a, b) -> a));
    Map<String, ApsGoodsForecastUserSaleData> forecastUserSaleDataMap = this.goodsForecastUserSaleDataService.list(new LambdaQueryWrapper<ApsGoodsForecastUserSaleData>().eq(ApsGoodsForecastUserSaleData::getForecastId, req.getId())).stream().collect(Collectors.toMap(t -> t.getSaleConfigId() + "-" + t.getYear(), m -> m, (a, b) -> a));

    List<String> monthList = goodsForecast.getMonthList();
    Map<Long, ApsSaleConfig> apsSaleConfigMap = this.saleConfigService.list().stream().collect(Collectors.toMap(BaseEntity::getId, v -> v, (a, b) -> a));

    DynamicsPage<ApsGoodsSaleItemExportQueryPageListInfoRes> apsGoodsSaleItemList = this.goodsSaleItemService.queryPageList(new ApsGoodsSaleItemExportQueryPageListReq().setQueryPage(false).setData(new ApsGoodsSaleItemDto().setGoodsId(goodsForecast.getGoodsId())));
    Map<String, ApsGoodsForecastComputeSaleData> computeSaleDataMap = new HashMap<>();
    monthList.forEach(m -> {
      try {
        String year = m.substring(0, 4);
        List<SaleItemConfig> allTmpList = new ArrayList<>();
        apsGoodsSaleItemList.getDataList().forEach(sale -> {
          Long saleConfigId = sale.getSaleConfigId();
          ApsSaleConfig saleConfig = apsSaleConfigMap.get(saleConfigId);
          if (Objects.nonNull(saleConfig) && saleConfig.getIsValue() == 1) {
            ApsGoodsForecastUserSaleData saleData = forecastUserSaleDataMap.get(saleConfigId + "-" + year);
            Field field = getField(saleData, "month" + m.substring(5));
            BigDecimal value = (BigDecimal) FieldUtils.getFieldValue(saleData, field);
            allTmpList.add(new SaleItemConfig().setSaleCode(saleConfig.getSaleCode()).setTarget(value.doubleValue()).setParentId(saleConfig.getParentId()));
          }
        });

        Collection<List<SaleItemConfig>> listCollection = allTmpList.stream().collect(Collectors.groupingBy(SaleItemConfig::getParentId)).values();
        if (CollUtil.isEmpty(listCollection)) {
          log.warn("listCollection is null {}  month:{}", req.getId(), m);
          return;
        }
        List<SkuGroup> groupList = listCollection.stream().map(t -> new SkuGroup(String.valueOf(t.getFirst().getParentId()), t.stream().collect(Collectors.toMap(SaleItemConfig::getSaleCode, SaleItemConfig::getTarget)))).toList();

//        List<SkuGroup> list = new ArrayList<>();

        ApsGoodsForecastUserGoodsData userGoodsData = userSaleDataMap.get(year);
        Field field = getField(userGoodsData, "month" + m.substring(5));
        Integer value = FieldUtils.getFieldValue(userGoodsData, field);
        DivisionRes divisionRes = OrToolsUtils.division(value, groupList);
        log.info("req compute count {} groupList:{}", value, JSON.toJSONString(groupList));
        log.info("ret compute divisionRes {}", JSON.toJSONString(divisionRes));
//        orToolsComputeResList.removeIf(t -> t.getCount() == 0L);
        divisionRes.getSkuCombineInfoList().forEach(cm -> {
          if (Objects.equals(cm.getCount(), 0L)) {
            return;
          }
          String key = year + cm.getKey();
          ApsGoodsForecastComputeSaleData goodsForecastComputeSaleData = computeSaleDataMap.compute(key, (k, v) -> Objects.isNull(v) ? new ApsGoodsForecastComputeSaleData() : v);
          goodsForecastComputeSaleData.setForecastId(req.getId()).setYear(Integer.valueOf(year)).setSaleConfigCode(cm.getKey());
          ReflectUtil.setFieldValue(goodsForecastComputeSaleData, "month" + m.substring(5), cm.getCount());
        });
      } catch (Exception e) {
        log.error("compute error id {} ,month:{},msg:{}", req.getId(), m, e.getMessage(), e);
      }
    });

    this.goodsForecastComputeSaleDataService.remove(new LambdaQueryWrapper<ApsGoodsForecastComputeSaleData>().eq(ApsGoodsForecastComputeSaleData::getForecastId, req.getId()));
    this.goodsForecastComputeSaleDataService.saveBatch(computeSaleDataMap.values());

    this.update(new LambdaUpdateWrapper<ApsGoodsForecast>().eq(ApsGoodsForecast::getId, req.getId()).set(ApsGoodsForecast::getForecastStatus, ForecastStatusEnum.COMPUTED_RESULT.getCode()));
    return null;
  }

  @Override
  public DynamicsPage<ComputeResultRes> computeResult(ComputeResultReq req) {
    ApsGoodsForecast goodsForecast = this.getById(req.getId());
    $.requireNonNullCanIgnoreException(goodsForecast, "未找到该预测数据");
    DynamicsPage<ComputeResultRes> dynamicsPage = new DynamicsPage<>();
    dynamicsPage.addHeader("saleConfigCode", "销售值", 550);
    List<String> monthList = goodsForecast.getMonthList();
    monthList.forEach(m -> dynamicsPage.addHeader(m, m, 150));

    Map<String, ApsGoodsForecastComputeSaleData> saleDataMap = this.goodsForecastComputeSaleDataService.list(new LambdaQueryWrapper<ApsGoodsForecastComputeSaleData>().eq(ApsGoodsForecastComputeSaleData::getForecastId, req.getId())).stream().collect(Collectors.toMap(t -> String.valueOf(t.getSaleConfigCode() + "-" + t.getYear()), f -> f, (a, b) -> a));
    List<ComputeResultRes> retList = new ArrayList<>();

    saleDataMap.forEach((sd, sdv) -> {
      ComputeResultRes e = new ComputeResultRes();
      e.put("saleConfigCode", sdv.getSaleConfigCode());
      e.setSaleConfigCode((String) e.get("saleConfigCode"));
      retList.add(e);
      ApsGoodsForecastComputeSaleData saleData = saleDataMap.get(sd);
      monthList.stream().filter(t -> t.substring(0, 4).equals(StringUtils.right(sd, 4))).forEach(m -> {
        if (Objects.isNull(saleData)) {
          return;
        }
        Field field = getField(saleData, "month" + m.substring(5));
        e.put(m, FieldUtils.getFieldValue(saleData, field));
      });

    });
    retList.sort(Comparator.comparing(ComputeResultRes::getSaleConfigCode));
    return dynamicsPage.setDataList(retList);
  }

  @Override
  @Transactional
  public DeployRes deploy(DeployReq req) {
    ApsGoodsForecast goodsForecast = this.getById(req.getId());
    $.requireNonNullCanIgnoreException(goodsForecast, "未找到该预测数据");
    if (ForecastStatusEnum.COMPUTED_RESULT.getCode() != (goodsForecast.getForecastStatus())) {
      throw new CanIgnoreException("请先计算结果");
    }
    ApsGoods apsGoods = apsGoodsService.getById(goodsForecast.getGoodsId());
    LambdaQueryWrapper<ApsGoodsForecastMain> lambdaQueryWrapper = new LambdaQueryWrapper<ApsGoodsForecastMain>().eq(ApsGoodsForecastMain::getGoodsId, goodsForecast.getGoodsId()).last(Str.LIMIT_1);
    ApsGoodsForecastMain forecastMain = goodsForecastMainService.getOne(lambdaQueryWrapper);

    Map<String, ApsGoodsForecastComputeSaleData> computeSaleDataMap = this.goodsForecastComputeSaleDataService.list(new LambdaQueryWrapper<ApsGoodsForecastComputeSaleData>().eq(ApsGoodsForecastComputeSaleData::getForecastId, req.getId())).stream().collect(Collectors.toMap(t -> t.getSaleConfigCode() + t.getYear(), f -> f));

    if (Objects.isNull(forecastMain)) {
      forecastMain = new ApsGoodsForecastMain().setGoodsId(goodsForecast.getGoodsId()).setFactoryId(apsGoods.getFactoryId()).setForecastBeginDate(goodsForecast.getForecastBeginDate()).setForecastEndDate(goodsForecast.getForecastEndDate()).setForecastNo("main-" + DateUtil.format(new Date(), DatePattern.PURE_DATETIME_PATTERN)).setForecastName(peanutProperties.getAps().getForecastMainPrefix() + apsGoods.getGoodsName() + peanutProperties.getAps().getForecastMainSuffix());
      this.goodsForecastMainService.save(forecastMain);
    } else {
      this.goodsForecastMainService.update(new LambdaUpdateWrapper<ApsGoodsForecastMain>().eq(BaseEntity::getId, forecastMain.getId()).set(BaseEntity::getUpdateTime, LocalDateTime.now()).set(forecastMain.getForecastBeginDate().compareTo(goodsForecast.getForecastBeginDate()) > 0, ApsGoodsForecastMain::getForecastBeginDate, goodsForecast.getForecastBeginDate()).set(ApsGoodsForecastMain::getFactoryId, apsGoods.getFactoryId()).set(forecastMain.getForecastEndDate().compareTo(goodsForecast.getForecastEndDate()) < 0, ApsGoodsForecastMain::getForecastEndDate, goodsForecast.getForecastEndDate()));
    }
    Long forecastMainId = forecastMain.getId();
    List<Integer> yearList = goodsForecast.getMonthList().stream().map(t -> t.substring(0, 4)).distinct().map(Integer::valueOf).toList();

    // 2024: [01,02,03]
    Map<Integer, List<String>> monthMap = new HashMap<>();
    yearList.forEach(year -> {
      monthMap.put(year, goodsForecast.getMonthList().stream().filter(m -> m.substring(0, 4).equals(String.valueOf(year))).map(m -> m.substring(5)).toList());
    });

    Map<String, ApsGoodsForecastMainSaleData> mainSaleDataMap = this.goodsForecastMainSaleDataService.list(new LambdaQueryWrapper<ApsGoodsForecastMainSaleData>().eq(ApsGoodsForecastMainSaleData::getGoodsId, goodsForecast.getGoodsId()).in(ApsGoodsForecastMainSaleData::getYear, yearList)).stream().collect(Collectors.toMap(t -> t.getSaleConfigCode() + t.getYear(), t -> t));
    monthMap.forEach((y, ml) -> {
      UpdateWrapper<ApsGoodsForecastMainSaleData> dataUpdateWrapper = new UpdateWrapper<ApsGoodsForecastMainSaleData>().eq("forecast_main_id", forecastMainId).eq("goods_id", goodsForecast.getGoodsId()).eq("year", y);
      ml.forEach(m -> dataUpdateWrapper.set("month_" + m, null));
      // 当年当月清空
      this.goodsForecastMainSaleDataService.update(dataUpdateWrapper);
    });

    List<ApsGoodsForecastMainSaleData> updateList = new ArrayList<>();
    List<ApsGoodsForecastMainSaleData> insertList = new ArrayList<>();

    computeSaleDataMap.forEach((k, v) -> {
      ApsGoodsForecastMainSaleData apsGoodsForecastMainSaleData = mainSaleDataMap.get(k);
      if (Objects.nonNull(apsGoodsForecastMainSaleData)) {
        monthMap.get(v.getYear()).forEach(m -> {
          Field sf = getField(v, "month" + m);
          Field tf = getField(apsGoodsForecastMainSaleData, "month" + m);
          ReflectUtil.setFieldValue(apsGoodsForecastMainSaleData, tf, FieldUtils.getFieldValue(v, sf));
        });
        updateList.add(apsGoodsForecastMainSaleData);
      } else {
        ApsGoodsForecastMainSaleData saleData = new ApsGoodsForecastMainSaleData().setGoodsId(goodsForecast.getGoodsId()).setForecastMainId(forecastMainId).setSaleConfigCode(v.getSaleConfigCode()).setYear(v.getYear());
        monthMap.get(v.getYear()).forEach(m -> {
          Field sourceField = getField(v, "month" + m);
          Field targetField = getField(saleData, "month" + m);
          ReflectUtil.setFieldValue(saleData, targetField, FieldUtils.getFieldValue(v, sourceField));
        });
        insertList.add(saleData);
      }
    });

    if (CollUtil.isNotEmpty(updateList)) {
      this.goodsForecastMainSaleDataService.updateBatchById(updateList);
    }
    if (CollUtil.isNotEmpty(insertList)) {
      this.goodsForecastMainSaleDataService.saveBatch(insertList);
    }
    return new DeployRes();
  }

  // 以下为私有对象封装


  private MPJLambdaWrapper<ApsGoodsForecast> getWrapper(ApsGoodsForecastDto obj) {
    MPJLambdaWrapper<ApsGoodsForecast> q = new MPJLambdaWrapper<>();

    if (Objects.nonNull(obj)) {
      q.eq(Objects.nonNull(obj.getGoodsId()), ApsGoodsForecast::getGoodsId, obj.getGoodsId()).likeRight(StringUtils.isNoneBlank(obj.getForecastNo()), ApsGoodsForecast::getForecastNo, obj.getForecastNo()).likeRight(StringUtils.isNoneBlank(obj.getForecastName()), ApsGoodsForecast::getForecastName, obj.getForecastName()).eq(StringUtils.isNoneBlank(obj.getForecastBeginDate()), ApsGoodsForecast::getForecastBeginDate, obj.getForecastBeginDate()).eq(StringUtils.isNoneBlank(obj.getForecastEndDate()), ApsGoodsForecast::getForecastEndDate, obj.getForecastEndDate())

      ;
    }
    q.orderByDesc(ApsGoodsForecast::getId);
    return q;

  }

  private void setQueryListHeader(DynamicsPage<ApsGoodsForecast> page) {

    ServiceComment.header(page, "ApsGoodsForecastService#queryPageList");

  }


}

