package com.olivia.peanut.aps.api.impl;


import com.github.yulichang.wrapper.MPJLambdaWrapper;
import com.olivia.peanut.aps.api.ApsGoodsForecastMainMakeSaleDataApi;
import com.olivia.peanut.aps.api.entity.apsGoodsForecastMainMakeSaleData.*;
import com.olivia.peanut.aps.api.impl.listener.ApsGoodsForecastMainMakeSaleDataImportListener;
import com.olivia.peanut.aps.model.ApsGoodsForecastMainMakeSaleData;
import com.olivia.peanut.aps.service.ApsGoodsForecastMainMakeSaleDataService;
import com.olivia.sdk.utils.$;
import com.olivia.sdk.utils.DynamicsPage;
import com.olivia.sdk.utils.PoiExcelUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * (ApsGoodsForecastMainMakeSaleData)表服务实现类
 *
 * @author peanut
 * @since 2024-04-08 09:52:50
 */
@RestController
public class ApsGoodsForecastMainMakeSaleDataApiImpl implements ApsGoodsForecastMainMakeSaleDataApi {

  private @Autowired ApsGoodsForecastMainMakeSaleDataService apsGoodsForecastMainMakeSaleDataService;

  /****
   * insert
   *
   */
  public @Override ApsGoodsForecastMainMakeSaleDataInsertRes insert(ApsGoodsForecastMainMakeSaleDataInsertReq req) {
    this.apsGoodsForecastMainMakeSaleDataService.save($.copy(req, ApsGoodsForecastMainMakeSaleData.class));
    return new ApsGoodsForecastMainMakeSaleDataInsertRes().setCount(1);
  }

  /****
   * deleteByIds
   *
   */
  public @Override ApsGoodsForecastMainMakeSaleDataDeleteByIdListRes deleteByIdList(ApsGoodsForecastMainMakeSaleDataDeleteByIdListReq req) {
    apsGoodsForecastMainMakeSaleDataService.removeByIds(req.getIdList());
    return new ApsGoodsForecastMainMakeSaleDataDeleteByIdListRes();
  }

  /****
   * queryList
   *
   */
  public @Override ApsGoodsForecastMainMakeSaleDataQueryListRes queryList(ApsGoodsForecastMainMakeSaleDataQueryListReq req) {
    return apsGoodsForecastMainMakeSaleDataService.queryList(req);
  }

  /****
   * updateById
   *
   */
  public @Override ApsGoodsForecastMainMakeSaleDataUpdateByIdRes updateById(ApsGoodsForecastMainMakeSaleDataUpdateByIdReq req) {
    apsGoodsForecastMainMakeSaleDataService.updateById($.copy(req, ApsGoodsForecastMainMakeSaleData.class));
    return new ApsGoodsForecastMainMakeSaleDataUpdateByIdRes();

  }

  public @Override DynamicsPage<ApsGoodsForecastMainMakeSaleDataExportQueryPageListInfoRes> queryPageList(ApsGoodsForecastMainMakeSaleDataExportQueryPageListReq req) {
    return apsGoodsForecastMainMakeSaleDataService.queryPageList(req);
  }

  public @Override void queryPageListExport(ApsGoodsForecastMainMakeSaleDataExportQueryPageListReq req) {
    DynamicsPage<ApsGoodsForecastMainMakeSaleDataExportQueryPageListInfoRes> page = queryPageList(req);
    List<ApsGoodsForecastMainMakeSaleDataExportQueryPageListInfoRes> list = page.getDataList();
    // 类型转换，  更换枚举 等操作
    List<ApsGoodsForecastMainMakeSaleDataExportQueryPageListInfoRes> listInfoRes = $.copyList(list, ApsGoodsForecastMainMakeSaleDataExportQueryPageListInfoRes.class);
    PoiExcelUtil.export(ApsGoodsForecastMainMakeSaleDataExportQueryPageListInfoRes.class, listInfoRes, "");
  }

  public @Override ApsGoodsForecastMainMakeSaleDataImportRes importData(@RequestParam("file") MultipartFile file) {
    List<ApsGoodsForecastMainMakeSaleDataImportReq> reqList = PoiExcelUtil.readData(file, new ApsGoodsForecastMainMakeSaleDataImportListener(),
        ApsGoodsForecastMainMakeSaleDataImportReq.class);
    // 类型转换，  更换枚举 等操作
    List<ApsGoodsForecastMainMakeSaleData> readList = $.copyList(reqList, ApsGoodsForecastMainMakeSaleData.class);
    boolean bool = apsGoodsForecastMainMakeSaleDataService.saveBatch(readList);
    int c = bool ? readList.size() : 0;
    return new ApsGoodsForecastMainMakeSaleDataImportRes().setCount(c);
  }

  public @Override ApsGoodsForecastMainMakeSaleDataQueryByIdListRes queryByIdListRes(ApsGoodsForecastMainMakeSaleDataQueryByIdListReq req) {
    MPJLambdaWrapper<ApsGoodsForecastMainMakeSaleData> q = new MPJLambdaWrapper<ApsGoodsForecastMainMakeSaleData>(ApsGoodsForecastMainMakeSaleData.class)
        .selectAll(ApsGoodsForecastMainMakeSaleData.class).in(ApsGoodsForecastMainMakeSaleData::getId, req.getIdList());
    List<ApsGoodsForecastMainMakeSaleData> list = this.apsGoodsForecastMainMakeSaleDataService.list(q);
    List<ApsGoodsForecastMainMakeSaleDataDto> dataList = $.copyList(list, ApsGoodsForecastMainMakeSaleDataDto.class);
    this.apsGoodsForecastMainMakeSaleDataService.setName(dataList);
    return new ApsGoodsForecastMainMakeSaleDataQueryByIdListRes().setDataList(dataList);
  }
}
