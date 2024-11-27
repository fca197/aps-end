package com.olivia.peanut.aps.api.impl;


import com.github.yulichang.wrapper.MPJLambdaWrapper;
import com.olivia.peanut.aps.api.ApsGoodsForecastUserSaleDataApi;
import com.olivia.peanut.aps.api.entity.apsGoodsForecastUserSaleData.*;
import com.olivia.peanut.aps.api.impl.listener.ApsGoodsForecastUserSaleDataImportListener;
import com.olivia.peanut.aps.model.ApsGoodsForecastUserSaleData;
import com.olivia.peanut.aps.service.ApsGoodsForecastUserSaleDataService;
import com.olivia.sdk.utils.$;
import com.olivia.sdk.utils.DynamicsPage;
import com.olivia.sdk.utils.PoiExcelUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * (ApsGoodsForecastUserSaleData)表服务实现类
 *
 * @author peanut
 * @since 2024-03-30 18:29:07
 */
@RestController
public class ApsGoodsForecastUserSaleDataApiImpl implements ApsGoodsForecastUserSaleDataApi {

  private @Autowired ApsGoodsForecastUserSaleDataService apsGoodsForecastUserSaleDataService;

  /****
   * insert
   *
   */
  public @Override ApsGoodsForecastUserSaleDataInsertRes insert(ApsGoodsForecastUserSaleDataInsertReq req) {
    this.apsGoodsForecastUserSaleDataService.save($.copy(req, ApsGoodsForecastUserSaleData.class));
    return new ApsGoodsForecastUserSaleDataInsertRes().setCount(1);
  }

  /****
   * deleteByIds
   *
   */
  public @Override ApsGoodsForecastUserSaleDataDeleteByIdListRes deleteByIdList(ApsGoodsForecastUserSaleDataDeleteByIdListReq req) {
    apsGoodsForecastUserSaleDataService.removeByIds(req.getIdList());
    return new ApsGoodsForecastUserSaleDataDeleteByIdListRes();
  }

  /****
   * queryList
   *
   */
  public @Override ApsGoodsForecastUserSaleDataQueryListRes queryList(ApsGoodsForecastUserSaleDataQueryListReq req) {
    return apsGoodsForecastUserSaleDataService.queryList(req);
  }

  /****
   * updateById
   *
   */
  public @Override ApsGoodsForecastUserSaleDataUpdateByIdRes updateById(ApsGoodsForecastUserSaleDataUpdateByIdReq req) {
    apsGoodsForecastUserSaleDataService.updateById($.copy(req, ApsGoodsForecastUserSaleData.class));
    return new ApsGoodsForecastUserSaleDataUpdateByIdRes();

  }

  public @Override DynamicsPage<ApsGoodsForecastUserSaleDataExportQueryPageListInfoRes> queryPageList(ApsGoodsForecastUserSaleDataExportQueryPageListReq req) {
    return apsGoodsForecastUserSaleDataService.queryPageList(req);
  }

  public @Override void queryPageListExport(ApsGoodsForecastUserSaleDataExportQueryPageListReq req) {
    DynamicsPage<ApsGoodsForecastUserSaleDataExportQueryPageListInfoRes> page = queryPageList(req);
    List<ApsGoodsForecastUserSaleDataExportQueryPageListInfoRes> list = page.getDataList();
    // 类型转换，  更换枚举 等操作
    List<ApsGoodsForecastUserSaleDataExportQueryPageListInfoRes> listInfoRes = $.copyList(list, ApsGoodsForecastUserSaleDataExportQueryPageListInfoRes.class);
    PoiExcelUtil.export(ApsGoodsForecastUserSaleDataExportQueryPageListInfoRes.class, listInfoRes, "");
  }

  public @Override ApsGoodsForecastUserSaleDataImportRes importData(@RequestParam("file") MultipartFile file) {
    List<ApsGoodsForecastUserSaleDataImportReq> reqList = PoiExcelUtil.readData(file, new ApsGoodsForecastUserSaleDataImportListener(),
        ApsGoodsForecastUserSaleDataImportReq.class);
    // 类型转换，  更换枚举 等操作
    List<ApsGoodsForecastUserSaleData> readList = $.copyList(reqList, ApsGoodsForecastUserSaleData.class);
    boolean bool = apsGoodsForecastUserSaleDataService.saveBatch(readList);
    int c = bool ? readList.size() : 0;
    return new ApsGoodsForecastUserSaleDataImportRes().setCount(c);
  }

  public @Override ApsGoodsForecastUserSaleDataQueryByIdListRes queryByIdListRes(ApsGoodsForecastUserSaleDataQueryByIdListReq req) {
    MPJLambdaWrapper<ApsGoodsForecastUserSaleData> q = new MPJLambdaWrapper<ApsGoodsForecastUserSaleData>(ApsGoodsForecastUserSaleData.class)
        .selectAll(ApsGoodsForecastUserSaleData.class).in(ApsGoodsForecastUserSaleData::getId, req.getIdList());
    List<ApsGoodsForecastUserSaleData> list = this.apsGoodsForecastUserSaleDataService.list(q);
    List<ApsGoodsForecastUserSaleDataDto> dataList = $.copyList(list, ApsGoodsForecastUserSaleDataDto.class);
    return new ApsGoodsForecastUserSaleDataQueryByIdListRes().setDataList(dataList);
  }
}
