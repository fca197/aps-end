package com.olivia.peanut.aps.api.impl;

import java.time.LocalDate;
import java.time.LocalDateTime;

import com.olivia.peanut.aps.model.ApsOrderRollingForecast;
import com.olivia.sdk.utils.$;
import com.olivia.sdk.utils.DynamicsPage;
import com.olivia.sdk.utils.PoiExcelUtil;
import java.util.stream.Collectors;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;
import org.apache.commons.lang3.StringUtils;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import com.olivia.peanut.aps.api.entity.apsOrderRollingForecast.*;
import com.olivia.peanut.aps.service.ApsOrderRollingForecastService;
import com.olivia.peanut.aps.model.*;
import com.baomidou.mybatisplus.core.conditions.query.*;
import com.github.yulichang.wrapper.MPJLambdaWrapper;
import org.springframework.web.bind.annotation.*;
import com.olivia.peanut.aps.api.ApsOrderRollingForecastApi;

import com.olivia.peanut.aps.api.impl.listener.*;
import org.springframework.web.multipart.MultipartFile;

/**
 * 滚动预测(ApsOrderRollingForecast)表服务实现类
 *
 * @author peanut
 * @since 2024-07-14 19:43:51
 */
@RestController
public class ApsOrderRollingForecastApiImpl implements ApsOrderRollingForecastApi {

  private @Autowired ApsOrderRollingForecastService apsOrderRollingForecastService;

  /****
   * insert
   *
   */
  public @Override ApsOrderRollingForecastInsertRes insert(ApsOrderRollingForecastInsertReq req) {
    this.apsOrderRollingForecastService.save($.copy(req, ApsOrderRollingForecast.class));
    return new ApsOrderRollingForecastInsertRes().setCount(1);
  }

  /****
   * deleteByIds
   *
   */
  public @Override ApsOrderRollingForecastDeleteByIdListRes deleteByIdList(ApsOrderRollingForecastDeleteByIdListReq req) {
    apsOrderRollingForecastService.removeByIds(req.getIdList());
    return new ApsOrderRollingForecastDeleteByIdListRes();
  }

  /****
   * queryList
   *
   */
  public @Override ApsOrderRollingForecastQueryListRes queryList(ApsOrderRollingForecastQueryListReq req) {
    return apsOrderRollingForecastService.queryList(req);
  }

  /****
   * updateById
   *
   */
  public @Override ApsOrderRollingForecastUpdateByIdRes updateById(ApsOrderRollingForecastUpdateByIdReq req) {
    apsOrderRollingForecastService.updateById($.copy(req, ApsOrderRollingForecast.class));
    return new ApsOrderRollingForecastUpdateByIdRes();

  }

  public @Override DynamicsPage<ApsOrderRollingForecastExportQueryPageListInfoRes> queryPageList(ApsOrderRollingForecastExportQueryPageListReq req) {
    return apsOrderRollingForecastService.queryPageList(req);
  }

  public @Override void queryPageListExport(ApsOrderRollingForecastExportQueryPageListReq req) {
    DynamicsPage<ApsOrderRollingForecastExportQueryPageListInfoRes> page = queryPageList(req);
    List<ApsOrderRollingForecastExportQueryPageListInfoRes> list = page.getDataList();
    // 类型转换，  更换枚举 等操作
    List<ApsOrderRollingForecastExportQueryPageListInfoRes> listInfoRes = $.copyList(list, ApsOrderRollingForecastExportQueryPageListInfoRes.class);
    PoiExcelUtil.export(ApsOrderRollingForecastExportQueryPageListInfoRes.class, listInfoRes, "滚动预测");
  }

  public @Override ApsOrderRollingForecastImportRes importData(@RequestParam("file") MultipartFile file) {
    List<ApsOrderRollingForecastImportReq> reqList = PoiExcelUtil.readData(file, new ApsOrderRollingForecastImportListener(), ApsOrderRollingForecastImportReq.class);
    // 类型转换，  更换枚举 等操作
    List<ApsOrderRollingForecast> readList = $.copyList(reqList, ApsOrderRollingForecast.class);
    boolean bool = apsOrderRollingForecastService.saveBatch(readList);
    int c = bool ? readList.size() : 0;
    return new ApsOrderRollingForecastImportRes().setCount(c);
  }

  public @Override ApsOrderRollingForecastQueryByIdListRes queryByIdListRes(ApsOrderRollingForecastQueryByIdListReq req) {
    MPJLambdaWrapper<ApsOrderRollingForecast> q = new MPJLambdaWrapper<ApsOrderRollingForecast>(ApsOrderRollingForecast.class)
        .selectAll(ApsOrderRollingForecast.class).in(ApsOrderRollingForecast::getId, req.getIdList());
    List<ApsOrderRollingForecast> list = this.apsOrderRollingForecastService.list(q);
    List<ApsOrderRollingForecastDto> dataList = $.copyList(list, ApsOrderRollingForecastDto.class);
    this.apsOrderRollingForecastService.setName(dataList);
    return new ApsOrderRollingForecastQueryByIdListRes().setDataList(dataList);
  }
}
