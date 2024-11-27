package com.olivia.peanut.aps.api.impl;


import com.github.yulichang.wrapper.MPJLambdaWrapper;
import com.olivia.peanut.aps.api.ApsGoodsForecastComputeSaleDataApi;
import com.olivia.peanut.aps.api.entity.apsGoodsForecastComputeSaleData.*;
import com.olivia.peanut.aps.api.impl.listener.ApsGoodsForecastComputeSaleDataImportListener;
import com.olivia.peanut.aps.model.ApsGoodsForecastComputeSaleData;
import com.olivia.peanut.aps.service.ApsGoodsForecastComputeSaleDataService;
import com.olivia.sdk.utils.$;
import com.olivia.sdk.utils.DynamicsPage;
import com.olivia.sdk.utils.PoiExcelUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * (ApsGoodsForecastComputeSaleData)表服务实现类
 *
 * @author peanut
 * @since 2024-03-31 20:58:34
 */
@RestController
public class ApsGoodsForecastComputeSaleDataApiImpl implements ApsGoodsForecastComputeSaleDataApi {

  private @Autowired ApsGoodsForecastComputeSaleDataService apsGoodsForecastComputeSaleDataService;

  /****
   * insert
   *
   */
  public @Override ApsGoodsForecastComputeSaleDataInsertRes insert(ApsGoodsForecastComputeSaleDataInsertReq req) {
    this.apsGoodsForecastComputeSaleDataService.save($.copy(req, ApsGoodsForecastComputeSaleData.class));
    return new ApsGoodsForecastComputeSaleDataInsertRes().setCount(1);
  }

  /****
   * deleteByIds
   *
   */
  public @Override ApsGoodsForecastComputeSaleDataDeleteByIdListRes deleteByIdList(ApsGoodsForecastComputeSaleDataDeleteByIdListReq req) {
    apsGoodsForecastComputeSaleDataService.removeByIds(req.getIdList());
    return new ApsGoodsForecastComputeSaleDataDeleteByIdListRes();
  }

  /****
   * queryList
   *
   */
  public @Override ApsGoodsForecastComputeSaleDataQueryListRes queryList(ApsGoodsForecastComputeSaleDataQueryListReq req) {
    return apsGoodsForecastComputeSaleDataService.queryList(req);
  }

  /****
   * updateById
   *
   */
  public @Override ApsGoodsForecastComputeSaleDataUpdateByIdRes updateById(ApsGoodsForecastComputeSaleDataUpdateByIdReq req) {
    apsGoodsForecastComputeSaleDataService.updateById($.copy(req, ApsGoodsForecastComputeSaleData.class));
    return new ApsGoodsForecastComputeSaleDataUpdateByIdRes();

  }

  public @Override DynamicsPage<ApsGoodsForecastComputeSaleDataExportQueryPageListInfoRes> queryPageList(ApsGoodsForecastComputeSaleDataExportQueryPageListReq req) {
    return apsGoodsForecastComputeSaleDataService.queryPageList(req);
  }

  public @Override void queryPageListExport(ApsGoodsForecastComputeSaleDataExportQueryPageListReq req) {
    DynamicsPage<ApsGoodsForecastComputeSaleDataExportQueryPageListInfoRes> page = queryPageList(req);
    List<ApsGoodsForecastComputeSaleDataExportQueryPageListInfoRes> list = page.getDataList();
    // 类型转换，  更换枚举 等操作
    List<ApsGoodsForecastComputeSaleDataExportQueryPageListInfoRes> listInfoRes = $.copyList(list, ApsGoodsForecastComputeSaleDataExportQueryPageListInfoRes.class);
    PoiExcelUtil.export(ApsGoodsForecastComputeSaleDataExportQueryPageListInfoRes.class, listInfoRes, "");
  }

  public @Override ApsGoodsForecastComputeSaleDataImportRes importData(@RequestParam("file") MultipartFile file) {
    List<ApsGoodsForecastComputeSaleDataImportReq> reqList = PoiExcelUtil.readData(file, new ApsGoodsForecastComputeSaleDataImportListener(),
        ApsGoodsForecastComputeSaleDataImportReq.class);
    // 类型转换，  更换枚举 等操作
    List<ApsGoodsForecastComputeSaleData> readList = $.copyList(reqList, ApsGoodsForecastComputeSaleData.class);
    boolean bool = apsGoodsForecastComputeSaleDataService.saveBatch(readList);
    int c = bool ? readList.size() : 0;
    return new ApsGoodsForecastComputeSaleDataImportRes().setCount(c);
  }

  public @Override ApsGoodsForecastComputeSaleDataQueryByIdListRes queryByIdListRes(ApsGoodsForecastComputeSaleDataQueryByIdListReq req) {
    MPJLambdaWrapper<ApsGoodsForecastComputeSaleData> q = new MPJLambdaWrapper<ApsGoodsForecastComputeSaleData>(ApsGoodsForecastComputeSaleData.class)
        .selectAll(ApsGoodsForecastComputeSaleData.class).in(ApsGoodsForecastComputeSaleData::getId, req.getIdList());
    List<ApsGoodsForecastComputeSaleData> list = this.apsGoodsForecastComputeSaleDataService.list(q);
    List<ApsGoodsForecastComputeSaleDataDto> dataList = $.copyList(list, ApsGoodsForecastComputeSaleDataDto.class);
    return new ApsGoodsForecastComputeSaleDataQueryByIdListRes().setDataList(dataList);
  }
}
