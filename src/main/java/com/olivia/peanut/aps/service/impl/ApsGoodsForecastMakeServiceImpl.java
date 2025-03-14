package com.olivia.peanut.aps.service.impl;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.date.DatePattern;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.ReflectUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.IdWorker;
import com.github.yulichang.base.MPJBaseServiceImpl;
import com.github.yulichang.wrapper.MPJLambdaWrapper;
import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.google.common.collect.Lists;
import com.googlecode.aviator.AviatorEvaluator;
import com.olivia.peanut.aps.api.entity.apsGoodsForecastMake.*;
import com.olivia.peanut.aps.api.entity.apsGoodsSaleProjectConfig.ApsGoodsSaleProjectConfigSale2ProjectReq;
import com.olivia.peanut.aps.api.entity.apsGoodsSaleProjectConfig.ApsGoodsSaleProjectConfigSale2ProjectRes;
import com.olivia.peanut.aps.api.entity.apsGoodsSaleProjectConfig.ApsGoodsSaleProjectConfigSale2ProjectRes.Info;
import com.olivia.peanut.aps.api.entity.apsProcessPath.ApsProcessPathDto;
import com.olivia.peanut.aps.api.entity.apsProcessPath.ApsProcessPathQueryListReq;
import com.olivia.peanut.aps.api.entity.apsProcessPath.ApsProcessPathQueryListRes;
import com.olivia.peanut.aps.mapper.ApsGoodsForecastMakeMapper;
import com.olivia.peanut.aps.model.*;
import com.olivia.peanut.aps.service.*;
import com.olivia.peanut.aps.service.impl.po.BomUseDate;
import com.olivia.peanut.aps.service.impl.po.ProjectConfig;
import com.olivia.peanut.aps.utils.model.ApsProcessPathInfo;
import com.olivia.peanut.aps.utils.model.ApsProcessPathVo;
import com.olivia.peanut.aps.utils.model.ShiftItemVo;
import com.olivia.peanut.aps.utils.process.ProcessUtils;
import com.olivia.peanut.base.model.Shift;
import com.olivia.peanut.base.model.ShiftItem;
import com.olivia.peanut.base.service.CalendarService;
import com.olivia.peanut.base.service.ShiftItemService;
import com.olivia.peanut.base.service.ShiftService;
import com.olivia.peanut.util.SetNamePojoUtils;
import com.olivia.sdk.ann.SetUserName;
import com.olivia.sdk.comment.ServiceComment;
import com.olivia.sdk.config.PeanutProperties;
import com.olivia.sdk.service.SetNameService;
import com.olivia.sdk.utils.*;
import com.olivia.sdk.utils.DynamicsPage.Header;
import com.olivia.sdk.utils.model.WeekInfo;
import jakarta.annotation.Resource;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.aop.framework.AopContext;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.AtomicReference;
import java.util.function.Function;
import java.util.stream.Collectors;

import static com.olivia.sdk.utils.BomUtils.bomExpression2List;
import static com.olivia.sdk.utils.FieldUtils.getField;
import static java.lang.Boolean.FALSE;
import static java.lang.Boolean.TRUE;

/**
 * (ApsGoodsForecastMake)表服务实现类
 *
 * @author peanut
 * @since 2024-04-07 15:07:48
 */
@Slf4j
@Service("apsGoodsForecastMakeService")
@Transactional
public class ApsGoodsForecastMakeServiceImpl extends MPJBaseServiceImpl<ApsGoodsForecastMakeMapper, ApsGoodsForecastMake> implements ApsGoodsForecastMakeService {

  static final Cache<String, Map<String, String>> cache = CacheBuilder.newBuilder().maximumSize(100).expireAfterWrite(30, TimeUnit.MINUTES).build();
  static final Cache<Long, Map<Object, List<ApsGoodsBom>>> goodsBomCache = CacheBuilder.newBuilder().maximumSize(100).expireAfterWrite(30, TimeUnit.MINUTES).build();
  private static final String DAY_NUM_FIELD = "dayNum";
  @Resource
  ApsGoodsForecastMakeSaleDataService apsGoodsForecastMakeSaleDataService;
  @Resource
  ApsGoodsForecastMainService apsGoodsForecastMainService;
  @Resource
  ApsGoodsForecastMainSaleDataService apsGoodsForecastMainSaleDataService;
  @Resource
  CalendarService calendarService;
  @Resource
  ApsGoodsService apsGoodsService;
  @Resource
  ApsGoodsForecastMainMakeService apsGoodsForecastMainMakeService;
  @Resource
  ApsGoodsForecastMakeProjectDataService apsGoodsForecastMakeProjectDataService;
  @Resource
  PeanutProperties peanutProperties;
  @Resource
  ApsGoodsBomService apsGoodsBomService;
  @Resource
  ApsProcessPathService apsProcessPathService;
  @Resource
  ShiftService shiftService;
  @Resource
  ShiftItemService shiftItemService;
  @Resource
  ApsGoodsForecastMakeBomUseService apsGoodsForecastMakeBomUseService;
  @Resource
  SetNameService setNameService;
  @Resource
  private ApsGoodsForecastMainMakeSaleDataService apsGoodsForecastMainMakeSaleDataService;
  @Resource
  private ApsGoodsSaleProjectConfigService apsGoodsSaleProjectConfigService;

  public @Override ApsGoodsForecastMakeQueryListRes queryList(ApsGoodsForecastMakeQueryListReq req) {

    MPJLambdaWrapper<ApsGoodsForecastMake> q = getWrapper(req.getData());
    List<ApsGoodsForecastMake> list = this.list(q);

    List<ApsGoodsForecastMakeDto> dataList = list.stream().map(t -> $.copy(t, ApsGoodsForecastMakeDto.class)).collect(Collectors.toList());
    //  this.setName(dataList);
    ((ApsGoodsForecastMakeServiceImpl) AopContext.currentProxy()).setName(dataList);

    return new ApsGoodsForecastMakeQueryListRes().setDataList(dataList);
  }

  public @Override DynamicsPage<ApsGoodsForecastMakeExportQueryPageListInfoRes> queryPageList(ApsGoodsForecastMakeExportQueryPageListReq req) {

    DynamicsPage<ApsGoodsForecastMake> page = new DynamicsPage<>();
    page.setCurrent(req.getPageNum()).setSize(req.getPageSize());
    setQueryListHeader(page);
    MPJLambdaWrapper<ApsGoodsForecastMake> q = getWrapper(req.getData());
    List<ApsGoodsForecastMakeExportQueryPageListInfoRes> records;
    if (TRUE.equals(req.getQueryPage())) {
      IPage<ApsGoodsForecastMake> list = this.page(page, q);
      IPage<ApsGoodsForecastMakeExportQueryPageListInfoRes> dataList = list.convert(t -> $.copy(t, ApsGoodsForecastMakeExportQueryPageListInfoRes.class));
      records = dataList.getRecords();
    } else {
      records = $.copyList(this.list(q), ApsGoodsForecastMakeExportQueryPageListInfoRes.class);
    }

    // 类型转换，  更换枚举 等操作

    List<ApsGoodsForecastMakeExportQueryPageListInfoRes> listInfoRes = $.copyList(records, ApsGoodsForecastMakeExportQueryPageListInfoRes.class);
    // this.setName(listInfoRes);
    ((ApsGoodsForecastMakeServiceImpl) AopContext.currentProxy()).setName(listInfoRes);

    return DynamicsPage.init(page, listInfoRes);
  }

  @Override
  public ApsGoodsForecastMakeInsertRes save(ApsGoodsForecastMakeInsertReq req) {
    Long id = IdWorker.getId();
    ApsGoodsForecastMain forecastMain = apsGoodsForecastMainService.getById(req.getForecastMainId());
    ApsGoodsForecastMake apsGoodsForecastMake = new ApsGoodsForecastMake();
    apsGoodsForecastMake.setGoodsId(forecastMain.getGoodsId()).setFactoryId(forecastMain.getFactoryId()).setForecastMainId(req.getForecastMainId()).setForecastMakeMonthBeginDate(req.getForecastMakeMonthBeginDate()).setForecastMakeMonthEndDate(req.getForecastMakeMonthEndDate()).setForecastMakeMonthName(req.getForecastMakeMonthName()).setForecastMakeMonthNo(req.getForecastMakeMonthNo()).setMonth(req.getMonth()).setIsDeploy(FALSE).setId(id);
    $.requireNonNullCanIgnoreException(forecastMain, "未找到对应的预测主表");
    ApsProcessPathQueryListRes apsProcessPathQueryListRes = apsProcessPathService.queryList(new ApsProcessPathQueryListReq().setData(new ApsProcessPathDto().setFactoryId(forecastMain.getFactoryId()).setIsDefault(TRUE)));
    $.assertTrueCanIgnoreException(CollUtil.isNotEmpty(apsProcessPathQueryListRes.getDataList()) && apsProcessPathQueryListRes.getDataList().size() == 1, "未找到对应的工厂的工艺路径");
    Map<String, List<ApsGoodsForecastMainSaleData>> monthDataMap = apsGoodsForecastMainSaleDataService.list(new LambdaQueryWrapper<ApsGoodsForecastMainSaleData>().eq(ApsGoodsForecastMainSaleData::getForecastMainId, forecastMain.getId())).stream().collect(Collectors.groupingBy(t -> t.getYear() + ""));
    List<WeekInfo> weekInfoListAll = calendarService.getWeekList(forecastMain.getFactoryId(), req.getForecastMakeMonthBeginDate(), req.getForecastMakeMonthEndDate());

    Map<String, List<WeekInfo>> ymListMap = weekInfoListAll.stream().collect(Collectors.groupingBy(t -> t.getCurrentDay().substring(0, 7)));
    Map<String, ApsGoodsForecastMakeSaleData> apsGoodsForecastMakeSaleDataMap = new HashMap<>();
    List<Runnable> runnableList = new ArrayList<>();
    List<ApsGoodsSaleProjectConfigSale2ProjectRes> sale2ProjectResList = Collections.synchronizedList(new ArrayList<>());

    ApsProcessPathDto apsProcessPathDto = apsProcessPathQueryListRes.getDataList().getFirst();
    ApsProcessPathVo apsProcessPathVo = $.copy(apsProcessPathDto, ApsProcessPathVo.class);

    Shift shift = shiftService.getOne(new LambdaQueryWrapper<Shift>().eq(Shift::getFactoryId, forecastMain.getFactoryId()), false);

    List<ShiftItem> shiftItemList = shiftItemService.list(new LambdaQueryWrapper<ShiftItem>().eq(ShiftItem::getShiftId, shift.getId()).orderByAsc(ShiftItem::getBeginTime));

    Long dayWorkSecond = ProcessUtils.getDayWorkSecond($.copyList(shiftItemList, ShiftItemVo.class));

    String[] bds = apsGoodsForecastMake.getForecastMakeMonthBeginDate().split("-");
//    LocalDate parse = LocalDate.parse(apsGoodsForecastMake.getForecastMakeMonthBeginDate(),DateTimeFormatter.ofPattern("yyyy-MM"));

    LocalDate beginDate = LocalDate.of(Integer.parseInt(bds[0]), Integer.parseInt(bds[1]), 1).minusDays(60);
    List<WeekInfo> weekList = calendarService.getWeekList(apsGoodsForecastMake.getFactoryId(), beginDate.format(DateTimeFormatter.ofPattern(DatePattern.NORM_DATE_PATTERN)), apsGoodsForecastMake.getForecastMakeMonthEndDate()).stream().filter(t -> TRUE.equals(t.getIsWorkDay())).sorted(Comparator.comparing(WeekInfo::getCurrentDay).reversed()).toList();

    log.info("weekList : {}", JSON.toJSONString(weekList));

    Map<LocalDate, ApsProcessPathInfo> apsProcessPathInfoMap = new HashMap<>();

    ymListMap.forEach((ym, weekInfoList) -> {
      String year = ym.substring(0, 4);
      String month = ym.substring(5, 7);
      List<ApsGoodsForecastMainSaleData> mainSaleDataList = monthDataMap.get(year);
      mainSaleDataList.forEach(c -> {
        String key = year + "-" + c.getSaleConfigCode();
        ApsGoodsForecastMakeSaleData makeSaleData = apsGoodsForecastMakeSaleDataMap.getOrDefault(key, new ApsGoodsForecastMakeSaleData()).setSaleConfigCode(c.getSaleConfigCode());
        makeSaleData.setFactoryId(forecastMain.getFactoryId()).setYear(Integer.valueOf(year));
        Field field = getField(c, "month" + month);
        Long count = (Long) FieldUtils.getFieldValue(c, field);
        if (Objects.isNull(count)) {
          return;
        }
        long wc = weekInfoList.stream().filter(t -> TRUE.equals(t.getIsWorkDay())).count();
        List<Long> dayCount = CountDisperseUtils.unidimensional(count, wc);
        weekInfoList.forEach(weekInfo -> {
          int day = weekInfo.getCurrentDate().getDayOfYear();
          // 工作日
          apsProcessPathInfoMap.put(weekInfo.getCurrentDate(), ProcessUtils.apsProcessPathInfo(apsProcessPathVo, weekList, weekInfo.getCurrentDate(), dayWorkSecond));

          if (TRUE.equals(weekInfo.getIsWorkDay())) {
            Long ct = dayCount.remove(0);
            runnableList.add(() -> {
              sale2ProjectResList.add(apsGoodsSaleProjectConfigService.sale2project(new ApsGoodsSaleProjectConfigSale2ProjectReq().setGoodsId(forecastMain.getGoodsId()).setSaleConfig(makeSaleData.getSaleConfigCode()).setConvertCount(ct).setBizKey(weekInfo.getCurrentDate())).setId(makeSaleData.getId()));
            });
            ReflectUtil.setFieldValue(makeSaleData, DAY_NUM_FIELD + day, ct);
          } else {
            ReflectUtil.setFieldValue(makeSaleData, DAY_NUM_FIELD + day, 0);
          }
        });
        apsGoodsForecastMakeSaleDataMap.put(key, makeSaleData);
      });
    });

    ArrayList<ApsGoodsForecastMakeSaleData> insertList = new ArrayList<>(apsGoodsForecastMakeSaleDataMap.values());
    insertList.sort(Comparator.comparing(ApsGoodsForecastMakeSaleData::getSaleConfigCode));
    insertList.forEach(t -> t.setMakeMonthId(id).setFactoryId(forecastMain.getFactoryId()).setGoodsId(forecastMain.getGoodsId()));
    this.save(apsGoodsForecastMake);
    this.apsGoodsForecastMakeSaleDataService.saveBatch(insertList);

    makeSale2Project2BomData(req, runnableList, sale2ProjectResList, apsGoodsForecastMake, forecastMain, apsProcessPathInfoMap);

    return new ApsGoodsForecastMakeInsertRes().setCount(1).setId(id);
  }

  @SneakyThrows
  private void makeSale2Project2BomData(ApsGoodsForecastMakeInsertReq req, List<Runnable> runnableList, List<ApsGoodsSaleProjectConfigSale2ProjectRes> sale2ProjectResList, ApsGoodsForecastMake apsGoodsForecastMake, ApsGoodsForecastMain forecastMain, Map<LocalDate, ApsProcessPathInfo> apsProcessPathInfoMap) {

    AtomicReference<LocalDate> bomMinDate = new AtomicReference<>();
    AtomicReference<LocalDate> bomMaxDate = new AtomicReference<>(LocalDate.now());
    Set<String> operationSet = new HashSet<>();
    operationSet.add("(");
    operationSet.add(")");
    operationSet.add("&&");
    operationSet.add("||");
    boolean bool = RunUtils.run("销售转规划 " + req.getId(), runnableList);
    $.assertTrueCanIgnoreException(bool, "销售转规划失败");
    runnableList.clear();
    Map<String, ApsGoodsForecastMakeProjectData> projectDataMap = new HashMap<>();
    Map<BomUseDate, BigDecimal> bomUseLongHashMap = new HashMap<>();
    for (ApsGoodsSaleProjectConfigSale2ProjectRes projectRes : sale2ProjectResList) {
      for (Info p : projectRes.getDataList()) {

        LocalDate bizKey = projectRes.getBizKey();
        if (Objects.isNull(bomMaxDate.get())) {
          bomMaxDate.set(bizKey);
        }
        if (bizKey.isAfter(bomMaxDate.get())) {
          bomMaxDate.set(bizKey);
        }
        int year = bizKey.getYear();
        ApsGoodsForecastMakeProjectData projectData = projectDataMap.get(p.getProjectCode() + year);
        if (Objects.isNull(projectData)) {
          projectData = new ApsGoodsForecastMakeProjectData().setProjectConfigCode(p.getProjectCode()).setMakeMonthId(apsGoodsForecastMake.getId()).setMakeSaleConfigId(projectRes.getId()).setYear((long) year);
          projectData.setProjectConfigCode(p.getProjectCode()).setId(IdWorker.getId());
          ReflectUtil.setFieldValue(projectData, DAY_NUM_FIELD + bizKey.getDayOfYear(), p.getConvertCount());
          ProjectConfig projectConfig = new ProjectConfig();
          projectConfig.setProjectId(projectData.getId()).setCalendarList(Lists.newArrayList(bizKey));
        } else {
          ReflectUtil.setFieldValue(projectData, DAY_NUM_FIELD + bizKey.getDayOfYear(), p.getConvertCount());
        }
        projectDataMap.put(p.getProjectCode() + year, projectData);

        log.info("开始获取零件 :{}", p.getProjectCode());
        Map<Object, List<ApsGoodsBom>> bomListMap = new HashMap<>(goodsBomCache.get(forecastMain.getGoodsId(), () -> apsGoodsBomService.list(new LambdaQueryWrapper<ApsGoodsBom>().eq(ApsGoodsBom::getGoodsId, forecastMain.getGoodsId())).stream().collect(Collectors.groupingBy(t -> bomExpression2List(t.getBomUseExpression())))));
        List<ApsGoodsBom> useBomList = new ArrayList<>();
        List<ApsGoodsBom> remove = bomListMap.remove(".");
        if (CollUtil.isNotEmpty(remove)) {
          useBomList.addAll(remove);
        }
        Set<String> projectSet = new HashSet<>(List.of(projectData.getProjectConfigCode().split(",")));
        ApsGoodsForecastMakeProjectData finalProjectData = projectData;
        bomListMap.forEach((k, value) -> {
          StringBuilder sb = new StringBuilder();
          if (k instanceof List<?> kl) {
            for (Object klt : kl) {
              if (klt instanceof String klts) {
                if (operationSet.contains(klts)) {
                  sb.append(klts);
                } else {
                  sb.append(projectSet.contains(klts));
                }
              }
            }
          }
          String bomExp = sb.toString();
          log.info("project code {} ,bom sb : {}", finalProjectData.getProjectConfigCode(), bomExp);
          boolean boolBom = (Boolean) AviatorEvaluator.execute(bomExp);
          if (boolBom) {
            useBomList.addAll(value);
          }
        });
        ApsProcessPathInfo apsProcessPathInfo = apsProcessPathInfoMap.get(projectRes.getBizKey());
        log.info("apsProcessPathInfo :{} {}", projectRes.getBizKey(), JSON.toJSONString(apsProcessPathInfo));
        if (Objects.isNull(apsProcessPathInfo)) {
          return;
        }
        Map<Long, List<ApsGoodsBom>> bomStationMap = useBomList.stream().collect(Collectors.groupingBy(ApsGoodsBom::getBomUseWorkStation));
        apsProcessPathInfo.getDataList().forEach(b -> {
          List<ApsGoodsBom> bomList = bomStationMap.get(b.getStationId());
          if (CollUtil.isNotEmpty(bomList)) {
            bomList.forEach(bt -> {
              BomUseDate key = new BomUseDate().setBomId(bt.getId()).setCurrentDate(b.getBeginLocalDate());
              BigDecimal c = bomUseLongHashMap.getOrDefault(key, new BigDecimal(0));
              c = c.add(bt.getBomUsage().multiply(p.getConvertCount()));
              bomUseLongHashMap.put(key, c);
              if (Objects.isNull(bomMinDate.get())) {
                bomMinDate.set(b.getBeginLocalDate());
              }
              LocalDate localDate = bomMinDate.get();
              if (b.getBeginLocalDate().isBefore(localDate)) {
                bomMinDate.set(b.getBeginLocalDate());
              }
            });
          }
        });

      }
    }
    if (CollUtil.isEmpty(projectDataMap)) {
      return;
    }
    List<ApsGoodsForecastMakeProjectData> projectDataList = new ArrayList<>(projectDataMap.values());
    projectDataList.sort(Comparator.comparing(ApsGoodsForecastMakeProjectData::getProjectConfigCode));
    apsGoodsForecastMakeProjectDataService.saveBatch(projectDataList);

    // 规划转BOM

    Map<BomUseDate, ApsGoodsForecastMakeBomUse> bomUseDateApsGoodsForecastMakeBomUseMap = new HashMap<>();
    bomUseLongHashMap.forEach((k, c) -> {
      LocalDate currentDate = k.getCurrentDate();
      if (Objects.isNull(currentDate)) {
        return;
      }
      BomUseDate bomUseDate = new BomUseDate().setCurrentDate(LocalDate.ofYearDay(currentDate.getYear(), 1)).setBomId(k.getBomId());
      ApsGoodsForecastMakeBomUse bomUse = bomUseDateApsGoodsForecastMakeBomUseMap.get(bomUseDate);
      if (Objects.isNull(bomUse)) {
        bomUse = new ApsGoodsForecastMakeBomUse().setYear((long) k.getCurrentDate().getYear()).setBomId(k.getBomId()).setMakeMonthId(apsGoodsForecastMake.getId()).setFactoryId(forecastMain.getFactoryId());
        ReflectUtil.setFieldValue(bomUse, DAY_NUM_FIELD + currentDate.getDayOfYear(), c);
      } else {
        ReflectUtil.setFieldValue(bomUse, DAY_NUM_FIELD + currentDate.getDayOfYear(), c);
      }
      bomUseDateApsGoodsForecastMakeBomUseMap.put(bomUseDate, bomUse);
    });
    apsGoodsForecastMakeBomUseService.saveBatch(bomUseDateApsGoodsForecastMakeBomUseMap.values());
    this.update(new LambdaUpdateWrapper<ApsGoodsForecastMake>().eq(ApsGoodsForecastMake::getId, apsGoodsForecastMake.getId()).set(ApsGoodsForecastMake::getBomUseBeginDate, bomMinDate.get().format(DateTimeFormatter.ofPattern("yyyy-MM"))).set(ApsGoodsForecastMake::getBomUseEndDate, bomMaxDate.get().format(DateTimeFormatter.ofPattern("yyyy-MM"))));
  }

  @Override
  @Transactional
  public ApsGoodsForecastMakeDeployRes deploy(ApsGoodsForecastMakeDeployReq req) {
    ApsGoodsForecastMake forecastMake = this.getById(req.getId());
    $.requireNonNullCanIgnoreException(forecastMake, "未找到对应的预测信息");
    $.assertTrueCanIgnoreException(FALSE.equals(forecastMake.getIsDeploy()), "该预测信息已发布，请勿重复发布");
    Map<String, ApsGoodsForecastMakeSaleData> yearDataMap = this.apsGoodsForecastMakeSaleDataService.list(new LambdaQueryWrapper<ApsGoodsForecastMakeSaleData>().eq(ApsGoodsForecastMakeSaleData::getMakeMonthId, req.getId())).stream().collect(Collectors.toMap(t -> t.getYear() + "-" + t.getSaleConfigCode(), f -> f));

    ApsGoodsForecastMainMake mainMake = this.apsGoodsForecastMainMakeService.getOne(new LambdaQueryWrapper<ApsGoodsForecastMainMake>().eq(ApsGoodsForecastMainMake::getGoodsId, forecastMake.getGoodsId()));

    if (Objects.isNull(mainMake)) {
      mainMake = new ApsGoodsForecastMainMake().setGoodsId(forecastMake.getGoodsId()).setFactoryId(forecastMake.getFactoryId()).setForecastMakeMainBeginDate(forecastMake.getForecastMakeMonthBeginDate()).setForecastMakeMainEndDate(forecastMake.getForecastMakeMonthEndDate()).setFactoryId(forecastMake.getFactoryId()).setGoodsId(forecastMake.getGoodsId()).setForecastMakeMainNo("SC-" + DateUtil.format(new Date(), "yyyyMMddH")).setForecastMakeMainName(forecastMake.getForecastMakeMonthName());
      mainMake.setId(IdWorker.getId());
      this.apsGoodsForecastMainMakeService.save(mainMake);
    } else {
      ApsGoods apsGoods = apsGoodsService.getById(forecastMake.getGoodsId());
      this.apsGoodsForecastMainMakeService.update(new LambdaUpdateWrapper<ApsGoodsForecastMainMake>().set(Str.isBefore(mainMake.getForecastMakeMainBeginDate(), forecastMake.getForecastMakeMonthBeginDate()), ApsGoodsForecastMainMake::getForecastMakeMainBeginDate, forecastMake.getForecastMakeMonthBeginDate()).set(ApsGoodsForecastMainMake::getForecastMakeMainNo, "SC-" + DateUtil.format(new Date(), "yyyyMMddH")).set(ApsGoodsForecastMainMake::getForecastMakeMainName, apsGoods.getGoodsName() + "生产计划").eq(Str.isAfter(mainMake.getForecastMakeMainEndDate(), forecastMake.getForecastMakeMonthEndDate()), ApsGoodsForecastMainMake::getForecastMakeMainEndDate, forecastMake.getForecastMakeMonthEndDate()).set(BaseEntity::getVersionNum, mainMake.getVersionNum() + 1).eq(BaseEntity::getId, mainMake.getId()));
    }
    Long id = mainMake.getId();
    List<WeekInfo> weekInfoList = calendarService.getWeekList(forecastMake.getFactoryId(), forecastMake.getForecastMakeMonthBeginDate(), forecastMake.getForecastMakeMonthEndDate());
    Set<Integer> ySet = weekInfoList.stream().map(t -> t.getCurrentDate().getYear()).collect(Collectors.toSet());
    // 已存在的年份
    List<ApsGoodsForecastMainMakeSaleData> mainMakeSaleDataList = this.apsGoodsForecastMainMakeSaleDataService.list(new LambdaQueryWrapper<ApsGoodsForecastMainMakeSaleData>().in(ApsGoodsForecastMainMakeSaleData::getYear, ySet).eq(ApsGoodsForecastMainMakeSaleData::getGoodsId, forecastMake.getGoodsId()));
    mainMakeSaleDataList.stream().collect(Collectors.groupingBy(ApsGoodsForecastMainMakeSaleData::getYear)).forEach((y, cl) -> {
      cl.forEach(c -> {
        ApsGoodsForecastMakeSaleData tmp = yearDataMap.remove(y + "-" + c.getSaleConfigCode());
        weekInfoList.stream().filter(t -> t.getCurrentDate().getYear() == y).forEach(d -> {
          Object v = 0L;
          if (Objects.nonNull(tmp)) {
            Field field = getField(tmp, DAY_NUM_FIELD + d.getCurrentDate().getDayOfYear());
            v = FieldUtils.getFieldValue(tmp, field);
          }
          Field field = getField(c, DAY_NUM_FIELD + d.getCurrentDate().getDayOfYear());
          ReflectUtil.setFieldValue(c, field, v);
        });
      });
    });

    this.apsGoodsForecastMainMakeSaleDataService.updateBatchById(mainMakeSaleDataList);
    Collection<ApsGoodsForecastMakeSaleData> dataCollection = yearDataMap.values();
    if (CollUtil.isNotEmpty(dataCollection)) {
      List<ApsGoodsForecastMainMakeSaleData> apsGoodsForecastMainMakeSaleDataList = dataCollection.stream().map(t -> (ApsGoodsForecastMainMakeSaleData) $.copy(t, ApsGoodsForecastMainMakeSaleData.class).setMainMakeId(id).setId(IdWorker.getId())).toList();
      this.apsGoodsForecastMainMakeSaleDataService.saveBatch(apsGoodsForecastMainMakeSaleDataList);
    }
    this.update(new LambdaUpdateWrapper<ApsGoodsForecastMake>().set(ApsGoodsForecastMake::getIsDeploy, TRUE).eq(BaseEntity::getId, forecastMake.getId()));
    return new ApsGoodsForecastMakeDeployRes().setId(id);
  }

  @Override
  public DynamicsPage<ApsGoodsForecastMakeQueryDataByIdRes> queryDataById(ApsGoodsForecastMakeQueryDataByIdReq req) {
    ApsGoodsForecastMake forecastMake = this.getById(req.getId());
    $.requireNonNullCanIgnoreException(forecastMake, "未找到对应的预测信息");
    Map<Integer, List<ApsGoodsForecastMakeSaleData>> saleMap = this.apsGoodsForecastMakeSaleDataService.list(new LambdaQueryWrapper<ApsGoodsForecastMakeSaleData>().eq(ApsGoodsForecastMakeSaleData::getMakeMonthId, req.getId())).stream().collect(Collectors.groupingBy(ApsGoodsForecastMakeSaleData::getYear));
    List<WeekInfo> weekInfoList = this.calendarService.getWeekList(forecastMake.getFactoryId(), forecastMake.getForecastMakeMonthBeginDate(), forecastMake.getForecastMakeMonthEndDate());

    List<Header> headerList = new ArrayList<>();
    Map<String, ApsGoodsForecastMakeQueryDataByIdRes> dataListMap = new HashMap<>();
    headerList.add(new Header().setFieldName("saleConfigCode").setShowName("销售配置编码").setWidth(200));
    Map<String, List<WeekInfo>> weekListMap = weekInfoList.stream().collect(Collectors.groupingBy(t -> t.getCurrentDay().substring(0, 7) + t.getWeekNumber()));

    new ArrayList<>(weekListMap.keySet()).stream().sorted().forEach(ym -> {
      int year = Integer.parseInt(ym.substring(0, 4));
      List<WeekInfo> weekInfoListTmp = weekListMap.get(ym).stream().filter(w -> TRUE.equals(w.getIsWorkDay())).toList();
      saleMap.getOrDefault(year, List.of()).forEach(t -> {
        ApsGoodsForecastMakeQueryDataByIdRes queryDataByIdRes = dataListMap.getOrDefault(t.getSaleConfigCode(), new ApsGoodsForecastMakeQueryDataByIdRes());
        AtomicReference<BigDecimal> s = new AtomicReference<>(new BigDecimal(0));
        weekInfoListTmp.forEach(d -> {
          Field field = getField(t, DAY_NUM_FIELD + d.getCurrentDate().getDayOfYear());
          Object value = FieldUtils.getFieldValue(t, field);
          if (Objects.nonNull(value)) {
            s.set(s.get().add((BigDecimal) value));
          }
        });
//        WeekInfo weekInfo = weekInfoListTmp.getFirst();
        queryDataByIdRes.put(ym, s.get());
        queryDataByIdRes.put("saleConfigCode", t.getSaleConfigCode());
        dataListMap.put(t.getSaleConfigCode(), queryDataByIdRes.setSaleConfigCode(t.getSaleConfigCode()));
      });
      headerList.add(new Header().setWidth(60).setFieldName(ym).setShowName(ym.substring(5, 7) + "月" + weekListMap.get(ym).getFirst().getWeekNumber() + "周"));

    });
    List<ApsGoodsForecastMakeQueryDataByIdRes> values = new ArrayList<>(dataListMap.values());
    values.sort(Comparator.comparing(ApsGoodsForecastMakeQueryDataByIdRes::getSaleConfigCode));
    return new DynamicsPage<ApsGoodsForecastMakeQueryDataByIdRes>().setHeaderList(headerList).setDataList(values);
  }

  @Override
  public DynamicsPage<ApsGoodsForecastMakeQueryDataByIdRes> queryProjectDataById(ApsGoodsForecastMakeQueryDataByIdReq req) {

    ApsGoodsForecastMake forecastMake = this.getById(req.getId());
    $.requireNonNullCanIgnoreException(forecastMake, "未找到对应的预测信息");
    Map<Long, List<ApsGoodsForecastMakeProjectData>> saleMap = this.apsGoodsForecastMakeProjectDataService.list(new LambdaQueryWrapper<ApsGoodsForecastMakeProjectData>().eq(ApsGoodsForecastMakeProjectData::getMakeMonthId, req.getId())).stream().collect(Collectors.groupingBy(ApsGoodsForecastMakeProjectData::getYear));
    List<WeekInfo> weekInfoList = this.calendarService.getWeekList(forecastMake.getFactoryId(), forecastMake.getForecastMakeMonthBeginDate(), forecastMake.getForecastMakeMonthEndDate());

    List<Header> headerList = new ArrayList<>();
    Map<String, ApsGoodsForecastMakeQueryDataByIdRes> dataListMap = new HashMap<>();
    headerList.add(new Header().setFieldName("saleConfigCode").setShowName("销售配置编码").setWidth(200));
    Map<String, List<WeekInfo>> weekListMap = weekInfoList.stream().collect(Collectors.groupingBy(t -> t.getCurrentDay().substring(0, 7) + t.getWeekNumber()));

    new ArrayList<>(weekListMap.keySet()).stream().sorted().forEach(ym -> {
      Long year = Long.parseLong(ym.substring(0, 4));
      List<WeekInfo> weekInfoListTmp = weekListMap.get(ym).stream().filter(w -> TRUE.equals(w.getIsWorkDay())).toList();
      saleMap.getOrDefault(year, List.of()).forEach(t -> {
        ApsGoodsForecastMakeQueryDataByIdRes queryDataByIdRes = dataListMap.getOrDefault(t.getProjectConfigCode(), new ApsGoodsForecastMakeQueryDataByIdRes());
        AtomicReference<BigDecimal> s = new AtomicReference<>(new BigDecimal(0L));
        weekInfoListTmp.forEach(d -> {
          Field field = getField(t, DAY_NUM_FIELD + d.getCurrentDate().getDayOfYear());
          Object value = FieldUtils.getFieldValue(t, field);
          if (Objects.nonNull(value)) {
            s.set(s.get().add((BigDecimal) value));
          }
        });
//        WeekInfo weekInfo = weekInfoListTmp.getFirst();
        queryDataByIdRes.put(ym, s.get());
        queryDataByIdRes.put("saleConfigCode", t.getProjectConfigCode());
        dataListMap.put(t.getProjectConfigCode(), queryDataByIdRes.setSaleConfigCode(t.getProjectConfigCode()));
      });
      headerList.add(new Header().setWidth(60).setFieldName(ym).setShowName(ym.substring(5, 7) + "月" + weekListMap.get(ym).getFirst().getWeekNumber() + "周"));

    });
    List<ApsGoodsForecastMakeQueryDataByIdRes> values = new ArrayList<>(dataListMap.values());
    values.sort(Comparator.comparing(ApsGoodsForecastMakeQueryDataByIdRes::getSaleConfigCode));
    return new DynamicsPage<ApsGoodsForecastMakeQueryDataByIdRes>().setHeaderList(headerList).setDataList(values);
  }

  // 以下为私有对象封装

  @Override
  public DynamicsPage<ApsGoodsForecastMakeQueryUseBomByIdRes> queryBomUseDataById(ApsGoodsForecastMakeQueryUseBomByIdReq req) {
    ApsGoodsForecastMake forecastMake = this.getById(req.getId());
    $.requireNonNullCanIgnoreException(forecastMake, "未找到对应的预测信息");
    List<ApsGoodsForecastMakeBomUse> makeBomUseList = this.apsGoodsForecastMakeBomUseService.list(new LambdaQueryWrapper<ApsGoodsForecastMakeBomUse>().eq(ApsGoodsForecastMakeBomUse::getMakeMonthId, req.getId()));
    Map<Long, List<ApsGoodsForecastMakeBomUse>> saleMap = makeBomUseList.stream().collect(Collectors.groupingBy(ApsGoodsForecastMakeBomUse::getYear));
    List<WeekInfo> weekInfoList = this.calendarService.getWeekList(forecastMake.getFactoryId(), forecastMake.getBomUseBeginDate(), forecastMake.getBomUseEndDate());

    Map<Long, ApsGoodsBom> bomMap = apsGoodsBomService.listByIds(makeBomUseList.stream().map(ApsGoodsForecastMakeBomUse::getBomId).toList()).stream().collect(Collectors.toMap(BaseEntity::getId, Function.identity()));
    List<Header> headerList = new ArrayList<>();
    Map<Long, ApsGoodsForecastMakeQueryUseBomByIdRes> dataListMap = new HashMap<>();
    headerList.add(new Header().setFieldName("bomId").setShowName("零件编码").setWidth(100));
    headerList.add(new Header().setFieldName("bomName").setShowName("零件名称").setWidth(100));
    headerList.add(new Header().setFieldName("bomUnit").setShowName("零件规格").setWidth(100));
    Map<String, List<WeekInfo>> weekListMap = weekInfoList.stream().collect(Collectors.groupingBy(t -> t.getCurrentDay().substring(0, 7) + t.getWeekNumber()));
    new ArrayList<>(weekListMap.keySet()).stream().sorted().forEach(ym -> {
      Long year = Long.parseLong(ym.substring(0, 4));
      List<WeekInfo> weekInfoListTmp = weekListMap.get(ym).stream().filter(w -> TRUE.equals(w.getIsWorkDay())).toList();
      saleMap.getOrDefault(year, List.of()).forEach(t -> {
        ApsGoodsForecastMakeQueryUseBomByIdRes queryDataByIdRes = dataListMap.getOrDefault(t.getBomId(), new ApsGoodsForecastMakeQueryUseBomByIdRes());
        AtomicLong s = new AtomicLong(0);
        ApsGoodsBom apsGoodsBom = bomMap.get(t.getBomId());
        queryDataByIdRes.put("bomId", t.getBomId());
        queryDataByIdRes.put("bomName", apsGoodsBom.getBomName());
        queryDataByIdRes.put("bomUnit", apsGoodsBom.getBomUnit());
        weekInfoListTmp.forEach(d -> {
          Field field = getField(t, DAY_NUM_FIELD + d.getCurrentDate().getDayOfYear());
          Object value = FieldUtils.getFieldValue(t, field);
          if (Objects.nonNull(value)) {
            s.addAndGet((Long) value);
          }
        });
        queryDataByIdRes.put(ym, s.get());
        dataListMap.put(t.getBomId(), queryDataByIdRes.setBomName(apsGoodsBom.getBomName()));
      });
      headerList.add(new Header().setWidth(60).setFieldName(ym).setShowName(ym.substring(5, 7) + "月" + weekListMap.get(ym).getFirst().getWeekNumber() + "周"));

    });
    List<ApsGoodsForecastMakeQueryUseBomByIdRes> values = new ArrayList<>(dataListMap.values());
    values.sort(Comparator.comparing(ApsGoodsForecastMakeQueryUseBomByIdRes::getBomName));
    return new DynamicsPage<ApsGoodsForecastMakeQueryUseBomByIdRes>().setHeaderList(headerList).setDataList(values);
  }

  @SetUserName
  public @Override void setName(List<? extends ApsGoodsForecastMakeDto> apsGoodsForecastMakeDtoList) {

    setNameService.setName(apsGoodsForecastMakeDtoList, SetNamePojoUtils.GOODS, SetNamePojoUtils.OP_USER_NAME);

//    if (CollUtil.isEmpty(apsGoodsForecastMakeDtoList)) {
//      return;
//    }
//    Set<Long> gIdSet = apsGoodsForecastMakeDtoList.stream().map(ApsGoodsForecastMakeDto::getGoodsId).collect(Collectors.toSet());
//    Map<Long, String> gnMap = this.apsGoodsService.listByIds(gIdSet).stream().collect(Collectors.toMap(BaseEntity::getId, ApsGoods::getGoodsName));
//    apsGoodsForecastMakeDtoList.forEach(t -> t.setGoodsName(gnMap.get(t.getGoodsId())));
  }


  private MPJLambdaWrapper<ApsGoodsForecastMake> getWrapper(ApsGoodsForecastMakeDto obj) {
    MPJLambdaWrapper<ApsGoodsForecastMake> q = new MPJLambdaWrapper<>();

    if (Objects.nonNull(obj)) {
      q.eq(Objects.nonNull(obj.getGoodsId()), ApsGoodsForecastMake::getGoodsId, obj.getGoodsId()).eq(StringUtils.isNoneBlank(obj.getForecastMakeMonthNo()), ApsGoodsForecastMake::getForecastMakeMonthNo, obj.getForecastMakeMonthNo()).likeRight(StringUtils.isNoneBlank(obj.getForecastMakeMonthName()), ApsGoodsForecastMake::getForecastMakeMonthName, obj.getForecastMakeMonthName()).eq(StringUtils.isNoneBlank(obj.getForecastMakeMonthBeginDate()), ApsGoodsForecastMake::getForecastMakeMonthBeginDate, obj.getForecastMakeMonthBeginDate()).eq(StringUtils.isNoneBlank(obj.getForecastMakeMonthEndDate()), ApsGoodsForecastMake::getForecastMakeMonthEndDate, obj.getForecastMakeMonthEndDate()).eq(Objects.nonNull(obj.getFactoryId()), ApsGoodsForecastMake::getFactoryId, obj.getFactoryId()).eq(StringUtils.isNoneBlank(obj.getMonth()), ApsGoodsForecastMake::getMonth, obj.getMonth()).eq(StringUtils.isNoneBlank(obj.getWeeks()), ApsGoodsForecastMake::getWeeks, obj.getWeeks())

      ;
    }
    q.orderByDesc(ApsGoodsForecastMake::getId);
    return q;

  }

  private void setQueryListHeader(DynamicsPage<ApsGoodsForecastMake> page) {

    ServiceComment.header(page, "ApsGoodsForecastMakeService#queryPageList");

  }


}

