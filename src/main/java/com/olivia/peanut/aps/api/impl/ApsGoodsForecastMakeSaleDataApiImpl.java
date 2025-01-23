package com.olivia.peanut.aps.api.impl;


import com.github.yulichang.wrapper.MPJLambdaWrapper;
import com.olivia.peanut.aps.api.ApsGoodsForecastMakeSaleDataApi;
import com.olivia.peanut.aps.api.entity.apsGoodsForecastMakeSaleData.*;
import com.olivia.peanut.aps.api.impl.listener.ApsGoodsForecastMakeSaleDataImportListener;
import com.olivia.peanut.aps.model.ApsGoodsForecastMakeSaleData;
import com.olivia.peanut.aps.service.ApsGoodsForecastMakeSaleDataService;
import com.olivia.sdk.utils.$;
import com.olivia.sdk.utils.DynamicsPage;
import com.olivia.sdk.utils.PoiExcelUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * (ApsGoodsForecastMakeSaleData)表服务实现类
 *
 * @author peanut
 * @since 2024-04-07 15:07:49
 */
@RestController
public class ApsGoodsForecastMakeSaleDataApiImpl implements ApsGoodsForecastMakeSaleDataApi {

  private @Autowired ApsGoodsForecastMakeSaleDataService apsGoodsForecastMakeSaleDataService;

  /****
   * insert
   *
   */
  public @Override ApsGoodsForecastMakeSaleDataInsertRes insert(ApsGoodsForecastMakeSaleDataInsertReq req) {
    this.apsGoodsForecastMakeSaleDataService.save($.copy(req, ApsGoodsForecastMakeSaleData.class));
    return new ApsGoodsForecastMakeSaleDataInsertRes().setCount(1);
  }

  /****
   * deleteByIds
   *
   */
  public @Override ApsGoodsForecastMakeSaleDataDeleteByIdListRes deleteByIdList(ApsGoodsForecastMakeSaleDataDeleteByIdListReq req) {
    apsGoodsForecastMakeSaleDataService.removeByIds(req.getIdList());
    return new ApsGoodsForecastMakeSaleDataDeleteByIdListRes();
  }

  /****
   * queryList
   *
   */
  public @Override ApsGoodsForecastMakeSaleDataQueryListRes queryList(ApsGoodsForecastMakeSaleDataQueryListReq req) {
    return apsGoodsForecastMakeSaleDataService.queryList(req);
  }

  /****
   * updateById
   *
   */
  public @Override ApsGoodsForecastMakeSaleDataUpdateByIdRes updateById(ApsGoodsForecastMakeSaleDataUpdateByIdReq req) {
    apsGoodsForecastMakeSaleDataService.updateById($.copy(req, ApsGoodsForecastMakeSaleData.class));
    return new ApsGoodsForecastMakeSaleDataUpdateByIdRes();

  }

  public @Override DynamicsPage<ApsGoodsForecastMakeSaleDataExportQueryPageListInfoRes> queryPageList(ApsGoodsForecastMakeSaleDataExportQueryPageListReq req) {
    return apsGoodsForecastMakeSaleDataService.queryPageList(req);
  }

  public @Override void queryPageListExport(ApsGoodsForecastMakeSaleDataExportQueryPageListReq req) {
    DynamicsPage<ApsGoodsForecastMakeSaleDataExportQueryPageListInfoRes> page = queryPageList(req);
    List<ApsGoodsForecastMakeSaleDataExportQueryPageListInfoRes> list = page.getDataList();
    // 类型转换，  更换枚举 等操作
    List<ApsGoodsForecastMakeSaleDataExportQueryPageListInfoRes> listInfoRes = $.copyList(list, ApsGoodsForecastMakeSaleDataExportQueryPageListInfoRes.class);
    PoiExcelUtil.export(ApsGoodsForecastMakeSaleDataExportQueryPageListInfoRes.class, listInfoRes, "");
  }

  public @Override ApsGoodsForecastMakeSaleDataImportRes importData(@RequestParam("file") MultipartFile file) {
    List<ApsGoodsForecastMakeSaleDataImportReq> reqList = PoiExcelUtil.readData(file, new ApsGoodsForecastMakeSaleDataImportListener(),
        ApsGoodsForecastMakeSaleDataImportReq.class);
    // 类型转换，  更换枚举 等操作
    List<ApsGoodsForecastMakeSaleData> readList = $.copyList(reqList, ApsGoodsForecastMakeSaleData.class);
    boolean bool = apsGoodsForecastMakeSaleDataService.saveBatch(readList);
    int c = bool ? readList.size() : 0;
    return new ApsGoodsForecastMakeSaleDataImportRes().setCount(c);
  }

  public @Override ApsGoodsForecastMakeSaleDataQueryByIdListRes queryByIdListRes(ApsGoodsForecastMakeSaleDataQueryByIdListReq req) {
    MPJLambdaWrapper<ApsGoodsForecastMakeSaleData> q = new MPJLambdaWrapper<ApsGoodsForecastMakeSaleData>(ApsGoodsForecastMakeSaleData.class)
        .selectAll(ApsGoodsForecastMakeSaleData.class).in(ApsGoodsForecastMakeSaleData::getId, req.getIdList());
    List<ApsGoodsForecastMakeSaleData> list = this.apsGoodsForecastMakeSaleDataService.list(q);
    List<ApsGoodsForecastMakeSaleDataDto> dataList = $.copyList(list, ApsGoodsForecastMakeSaleDataDto.class);
    this.apsGoodsForecastMakeSaleDataService.setName(dataList);
    return new ApsGoodsForecastMakeSaleDataQueryByIdListRes().setDataList(dataList);
  }
}
