package com.olivia.peanut.aps.api.impl;


import com.github.yulichang.wrapper.MPJLambdaWrapper;
import com.olivia.peanut.aps.api.ApsGoodsForecastMakeBomUseApi;
import com.olivia.peanut.aps.api.entity.apsGoodsForecastMakeBomUse.*;
import com.olivia.peanut.aps.api.impl.listener.ApsGoodsForecastMakeBomUseImportListener;
import com.olivia.peanut.aps.model.ApsGoodsForecastMakeBomUse;
import com.olivia.peanut.aps.service.ApsGoodsForecastMakeBomUseService;
import com.olivia.sdk.utils.$;
import com.olivia.sdk.utils.DynamicsPage;
import com.olivia.sdk.utils.PoiExcelUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * (ApsGoodsForecastMakeBomUse)表服务实现类
 *
 * @author peanut
 * @since 2024-05-15 10:26:03
 */
@RestController
public class ApsGoodsForecastMakeBomUseApiImpl implements ApsGoodsForecastMakeBomUseApi {

  private @Autowired ApsGoodsForecastMakeBomUseService apsGoodsForecastMakeBomUseService;

  /****
   * insert
   *
   */
  public @Override ApsGoodsForecastMakeBomUseInsertRes insert(ApsGoodsForecastMakeBomUseInsertReq req) {
    this.apsGoodsForecastMakeBomUseService.save($.copy(req, ApsGoodsForecastMakeBomUse.class));
    return new ApsGoodsForecastMakeBomUseInsertRes().setCount(1);
  }

  /****
   * deleteByIds
   *
   */
  public @Override ApsGoodsForecastMakeBomUseDeleteByIdListRes deleteByIdList(ApsGoodsForecastMakeBomUseDeleteByIdListReq req) {
    apsGoodsForecastMakeBomUseService.removeByIds(req.getIdList());
    return new ApsGoodsForecastMakeBomUseDeleteByIdListRes();
  }

  /****
   * queryList
   *
   */
  public @Override ApsGoodsForecastMakeBomUseQueryListRes queryList(ApsGoodsForecastMakeBomUseQueryListReq req) {
    return apsGoodsForecastMakeBomUseService.queryList(req);
  }

  /****
   * updateById
   *
   */
  public @Override ApsGoodsForecastMakeBomUseUpdateByIdRes updateById(ApsGoodsForecastMakeBomUseUpdateByIdReq req) {
    apsGoodsForecastMakeBomUseService.updateById($.copy(req, ApsGoodsForecastMakeBomUse.class));
    return new ApsGoodsForecastMakeBomUseUpdateByIdRes();

  }

  public @Override DynamicsPage<ApsGoodsForecastMakeBomUseExportQueryPageListInfoRes> queryPageList(ApsGoodsForecastMakeBomUseExportQueryPageListReq req) {
    return apsGoodsForecastMakeBomUseService.queryPageList(req);
  }

  public @Override void queryPageListExport(ApsGoodsForecastMakeBomUseExportQueryPageListReq req) {
    DynamicsPage<ApsGoodsForecastMakeBomUseExportQueryPageListInfoRes> page = queryPageList(req);
    List<ApsGoodsForecastMakeBomUseExportQueryPageListInfoRes> list = page.getDataList();
    // 类型转换，  更换枚举 等操作
    List<ApsGoodsForecastMakeBomUseExportQueryPageListInfoRes> listInfoRes = $.copyList(list, ApsGoodsForecastMakeBomUseExportQueryPageListInfoRes.class);
    PoiExcelUtil.export(ApsGoodsForecastMakeBomUseExportQueryPageListInfoRes.class, listInfoRes, "");
  }

  public @Override ApsGoodsForecastMakeBomUseImportRes importData(@RequestParam("file") MultipartFile file) {
    List<ApsGoodsForecastMakeBomUseImportReq> reqList = PoiExcelUtil.readData(file, new ApsGoodsForecastMakeBomUseImportListener(), ApsGoodsForecastMakeBomUseImportReq.class);
    // 类型转换，  更换枚举 等操作
    List<ApsGoodsForecastMakeBomUse> readList = $.copyList(reqList, ApsGoodsForecastMakeBomUse.class);
    boolean bool = apsGoodsForecastMakeBomUseService.saveBatch(readList);
    int c = bool ? readList.size() : 0;
    return new ApsGoodsForecastMakeBomUseImportRes().setCount(c);
  }

  public @Override ApsGoodsForecastMakeBomUseQueryByIdListRes queryByIdListRes(ApsGoodsForecastMakeBomUseQueryByIdListReq req) {
    MPJLambdaWrapper<ApsGoodsForecastMakeBomUse> q = new MPJLambdaWrapper<ApsGoodsForecastMakeBomUse>(ApsGoodsForecastMakeBomUse.class)
        .selectAll(ApsGoodsForecastMakeBomUse.class).in(ApsGoodsForecastMakeBomUse::getId, req.getIdList());
    List<ApsGoodsForecastMakeBomUse> list = this.apsGoodsForecastMakeBomUseService.list(q);
    List<ApsGoodsForecastMakeBomUseDto> dataList = $.copyList(list, ApsGoodsForecastMakeBomUseDto.class);
    this.apsGoodsForecastMakeBomUseService.setName(dataList);
    return new ApsGoodsForecastMakeBomUseQueryByIdListRes().setDataList(dataList);
  }
}
