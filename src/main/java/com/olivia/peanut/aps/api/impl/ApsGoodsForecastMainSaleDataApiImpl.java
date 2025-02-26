package com.olivia.peanut.aps.api.impl;


import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.date.DatePattern;
import cn.hutool.core.date.DateUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.github.yulichang.wrapper.MPJLambdaWrapper;
import com.olivia.peanut.aps.api.ApsGoodsForecastMainSaleDataApi;
import com.olivia.peanut.aps.api.entity.apsGoodsForecastMainGoodsData.GetDataByGoodsIdReq;
import com.olivia.peanut.aps.api.entity.apsGoodsForecastMainGoodsData.GetDataByGoodsIdRes;
import com.olivia.peanut.aps.api.entity.apsGoodsForecastMainSaleData.*;
import com.olivia.peanut.aps.api.impl.listener.ApsGoodsForecastMainSaleDataImportListener;
import com.olivia.peanut.aps.model.ApsGoodsForecastMainSaleData;
import com.olivia.peanut.aps.service.ApsGoodsForecastMainSaleDataService;
import com.olivia.sdk.utils.*;
import com.olivia.sdk.utils.DynamicsPage.Header;
import com.olivia.sdk.utils.model.YearMonth;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.lang.reflect.Field;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static com.olivia.sdk.utils.FieldUtils.getField;

/**
 * (ApsGoodsForecastMainSaleData)表服务实现类
 *
 * @author peanut
 * @since 2024-04-02 09:42:27
 */
@Slf4j
@RestController
public class ApsGoodsForecastMainSaleDataApiImpl implements ApsGoodsForecastMainSaleDataApi {

  private @Autowired ApsGoodsForecastMainSaleDataService apsGoodsForecastMainSaleDataService;

  /****
   * insert
   *
   */
  public @Override ApsGoodsForecastMainSaleDataInsertRes insert(ApsGoodsForecastMainSaleDataInsertReq req) {
    this.apsGoodsForecastMainSaleDataService.save($.copy(req, ApsGoodsForecastMainSaleData.class));
    return new ApsGoodsForecastMainSaleDataInsertRes().setCount(1);
  }

  /****
   * deleteByIds
   *
   */
  public @Override ApsGoodsForecastMainSaleDataDeleteByIdListRes deleteByIdList(ApsGoodsForecastMainSaleDataDeleteByIdListReq req) {
    apsGoodsForecastMainSaleDataService.removeByIds(req.getIdList());
    return new ApsGoodsForecastMainSaleDataDeleteByIdListRes();
  }

  /****
   * queryList
   *
   */
  public @Override ApsGoodsForecastMainSaleDataQueryListRes queryList(ApsGoodsForecastMainSaleDataQueryListReq req) {
    return apsGoodsForecastMainSaleDataService.queryList(req);
  }

  /****
   * updateById
   *
   */
  public @Override ApsGoodsForecastMainSaleDataUpdateByIdRes updateById(ApsGoodsForecastMainSaleDataUpdateByIdReq req) {
    apsGoodsForecastMainSaleDataService.updateById($.copy(req, ApsGoodsForecastMainSaleData.class));
    return new ApsGoodsForecastMainSaleDataUpdateByIdRes();

  }

  public @Override DynamicsPage<ApsGoodsForecastMainSaleDataExportQueryPageListInfoRes> queryPageList(ApsGoodsForecastMainSaleDataExportQueryPageListReq req) {
    return apsGoodsForecastMainSaleDataService.queryPageList(req);
  }

  public @Override void queryPageListExport(ApsGoodsForecastMainSaleDataExportQueryPageListReq req) {
    DynamicsPage<ApsGoodsForecastMainSaleDataExportQueryPageListInfoRes> page = queryPageList(req);
    List<ApsGoodsForecastMainSaleDataExportQueryPageListInfoRes> list = page.getDataList();
    // 类型转换，  更换枚举 等操作
    List<ApsGoodsForecastMainSaleDataExportQueryPageListInfoRes> listInfoRes = $.copyList(list, ApsGoodsForecastMainSaleDataExportQueryPageListInfoRes.class);
    PoiExcelUtil.export(ApsGoodsForecastMainSaleDataExportQueryPageListInfoRes.class, listInfoRes, "");
  }

  public @Override ApsGoodsForecastMainSaleDataImportRes importData(@RequestParam("file") MultipartFile file) {
    List<ApsGoodsForecastMainSaleDataImportReq> reqList = PoiExcelUtil.readData(file, new ApsGoodsForecastMainSaleDataImportListener(),
        ApsGoodsForecastMainSaleDataImportReq.class);
    // 类型转换，  更换枚举 等操作
    List<ApsGoodsForecastMainSaleData> readList = $.copyList(reqList, ApsGoodsForecastMainSaleData.class);
    boolean bool = apsGoodsForecastMainSaleDataService.saveBatch(readList);
    int c = bool ? readList.size() : 0;
    return new ApsGoodsForecastMainSaleDataImportRes().setCount(c);
  }

  public @Override ApsGoodsForecastMainSaleDataQueryByIdListRes queryByIdListRes(ApsGoodsForecastMainSaleDataQueryByIdListReq req) {
    MPJLambdaWrapper<ApsGoodsForecastMainSaleData> q = new MPJLambdaWrapper<ApsGoodsForecastMainSaleData>(ApsGoodsForecastMainSaleData.class)
        .selectAll(ApsGoodsForecastMainSaleData.class).in(ApsGoodsForecastMainSaleData::getId, req.getIdList());
    List<ApsGoodsForecastMainSaleData> list = this.apsGoodsForecastMainSaleDataService.list(q);
    List<ApsGoodsForecastMainSaleDataDto> dataList = $.copyList(list, ApsGoodsForecastMainSaleDataDto.class);
    return new ApsGoodsForecastMainSaleDataQueryByIdListRes().setDataList(dataList);
  }


  @Override
  public DynamicsPage<GetDataByGoodsIdRes> getDataByGoodsId(GetDataByGoodsIdReq req) {
    DynamicsPage<GetDataByGoodsIdRes> dynamicsPage = new DynamicsPage<>();
    Set<Integer> yearSet = new LinkedHashSet<>();
    List<String> headerList = new ArrayList<>();
    if (CollUtil.isEmpty(req.getDateRange())) {
      Calendar instance = Calendar.getInstance();
      yearSet.add(instance.get(Calendar.YEAR));
      headerList.add(DateUtil.format(instance.getTime(), DatePattern.NORM_MONTH_FORMAT));
      IntStream.range(1, 17).forEach(t -> {
        instance.add(Calendar.MONTH, 1);
        yearSet.add(instance.get(Calendar.YEAR));
        headerList.add(DateUtil.format(instance.getTime(), DatePattern.NORM_MONTH_FORMAT));
      });
    } else {
      List<YearMonth> monthList = DateUtils.getMonthList(req.getDateRange().getFirst(), req.getDateRange().get(1));
      monthList.stream().mapToInt(YearMonth::getYear).distinct().forEach(yearSet::add);
      headerList.addAll(monthList.stream().map(YearMonth::toString).toList());
    }

    log.info("yearSet:{}", headerList);

    Map<String, Map<Integer, ApsGoodsForecastMainSaleData>> mainSaleDataMap = this.apsGoodsForecastMainSaleDataService.list(new LambdaQueryWrapper<ApsGoodsForecastMainSaleData>()
            .eq(ApsGoodsForecastMainSaleData::getGoodsId, req.getId()).in(ApsGoodsForecastMainSaleData::getYear, yearSet))
        .stream().collect(Collectors.groupingBy(ApsGoodsForecastMainSaleData::getSaleConfigCode,
            Collectors.toMap(ApsGoodsForecastMainSaleData::getYear, Function.identity())));

    List<GetDataByGoodsIdRes> dataList = new ArrayList<>();
    mainSaleDataMap.forEach((code, v) -> {
      GetDataByGoodsIdRes data = new GetDataByGoodsIdRes();
      data.setSaleCode(code);
      data.put("saleCode", code);
      yearSet.stream().distinct().forEach(year -> {
        ApsGoodsForecastMainSaleData mainSaleData = v.get(year);
        IntStream.range(1, 13).forEach(m -> {
          String month = m < 10 ? "0" + m : "" + m;
          Field field = getField(mainSaleData, "month" + month);
          data.put(year + "-" + month, FieldUtils.getFieldValue(mainSaleData, field));
        });
      });

      dataList.add(data);
    });
//    dataList.add(new GetDataByGoodsIdRes().setSaleCode("合计"));
    dataList.sort(Comparator.comparing(GetDataByGoodsIdRes::getSaleCode));
//    headerList.add("销售特征值");
    List<Header> list = headerList.stream().map(m -> new Header().setFieldName(m).setShowName(m).setWidth(200)).collect(Collectors.toList());
    list.addFirst(new Header().setFieldName("saleCode").setShowName("销售特征值").setWidth(200));
    return dynamicsPage.setDataList(dataList).setHeaderList(list);
  }
}
