package com.olivia.peanut.aps.api.impl;


import com.github.yulichang.wrapper.MPJLambdaWrapper;
import com.olivia.peanut.aps.api.ApsGoodsForecastUserGoodsDataApi;
import com.olivia.peanut.aps.api.entity.apsGoodsForecastUserGoodsData.*;
import com.olivia.peanut.aps.api.impl.listener.ApsGoodsForecastUserGoodsDataImportListener;
import com.olivia.peanut.aps.model.ApsGoodsForecastUserGoodsData;
import com.olivia.peanut.aps.service.ApsGoodsForecastUserGoodsDataService;
import com.olivia.sdk.utils.$;
import com.olivia.sdk.utils.DynamicsPage;
import com.olivia.sdk.utils.PoiExcelUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * (ApsGoodsForecastUserGoodsData)表服务实现类
 *
 * @author peanut
 * @since 2024-03-30 18:29:06
 */
@RestController
public class ApsGoodsForecastUserGoodsDataApiImpl implements ApsGoodsForecastUserGoodsDataApi {

  private @Autowired ApsGoodsForecastUserGoodsDataService apsGoodsForecastUserGoodsDataService;

  /****
   * insert
   *
   */
  public @Override ApsGoodsForecastUserGoodsDataInsertRes insert(ApsGoodsForecastUserGoodsDataInsertReq req) {
    this.apsGoodsForecastUserGoodsDataService.save($.copy(req, ApsGoodsForecastUserGoodsData.class));
    return new ApsGoodsForecastUserGoodsDataInsertRes().setCount(1);
  }

  /****
   * deleteByIds
   *
   */
  public @Override ApsGoodsForecastUserGoodsDataDeleteByIdListRes deleteByIdList(ApsGoodsForecastUserGoodsDataDeleteByIdListReq req) {
    apsGoodsForecastUserGoodsDataService.removeByIds(req.getIdList());
    return new ApsGoodsForecastUserGoodsDataDeleteByIdListRes();
  }

  /****
   * queryList
   *
   */
  public @Override ApsGoodsForecastUserGoodsDataQueryListRes queryList(ApsGoodsForecastUserGoodsDataQueryListReq req) {
    return apsGoodsForecastUserGoodsDataService.queryList(req);
  }

  /****
   * updateById
   *
   */
  public @Override ApsGoodsForecastUserGoodsDataUpdateByIdRes updateById(ApsGoodsForecastUserGoodsDataUpdateByIdReq req) {
    apsGoodsForecastUserGoodsDataService.updateById($.copy(req, ApsGoodsForecastUserGoodsData.class));
    return new ApsGoodsForecastUserGoodsDataUpdateByIdRes();

  }

  public @Override DynamicsPage<ApsGoodsForecastUserGoodsDataExportQueryPageListInfoRes> queryPageList(ApsGoodsForecastUserGoodsDataExportQueryPageListReq req) {
    return apsGoodsForecastUserGoodsDataService.queryPageList(req);
  }

  public @Override void queryPageListExport(ApsGoodsForecastUserGoodsDataExportQueryPageListReq req) {
    DynamicsPage<ApsGoodsForecastUserGoodsDataExportQueryPageListInfoRes> page = queryPageList(req);
    List<ApsGoodsForecastUserGoodsDataExportQueryPageListInfoRes> list = page.getDataList();
    // 类型转换，  更换枚举 等操作
    List<ApsGoodsForecastUserGoodsDataExportQueryPageListInfoRes> listInfoRes = $.copyList(list, ApsGoodsForecastUserGoodsDataExportQueryPageListInfoRes.class);
    PoiExcelUtil.export(ApsGoodsForecastUserGoodsDataExportQueryPageListInfoRes.class, listInfoRes, "");
  }

  public @Override ApsGoodsForecastUserGoodsDataImportRes importData(@RequestParam("file") MultipartFile file) {
    List<ApsGoodsForecastUserGoodsDataImportReq> reqList = PoiExcelUtil.readData(file, new ApsGoodsForecastUserGoodsDataImportListener(),
        ApsGoodsForecastUserGoodsDataImportReq.class);
    // 类型转换，  更换枚举 等操作
    List<ApsGoodsForecastUserGoodsData> readList = $.copyList(reqList, ApsGoodsForecastUserGoodsData.class);
    boolean bool = apsGoodsForecastUserGoodsDataService.saveBatch(readList);
    int c = bool ? readList.size() : 0;
    return new ApsGoodsForecastUserGoodsDataImportRes().setCount(c);
  }

  public @Override ApsGoodsForecastUserGoodsDataQueryByIdListRes queryByIdListRes(ApsGoodsForecastUserGoodsDataQueryByIdListReq req) {
    MPJLambdaWrapper<ApsGoodsForecastUserGoodsData> q = new MPJLambdaWrapper<ApsGoodsForecastUserGoodsData>(ApsGoodsForecastUserGoodsData.class)
        .selectAll(ApsGoodsForecastUserGoodsData.class).in(ApsGoodsForecastUserGoodsData::getId, req.getIdList());
    List<ApsGoodsForecastUserGoodsData> list = this.apsGoodsForecastUserGoodsDataService.list(q);
    List<ApsGoodsForecastUserGoodsDataDto> dataList = $.copyList(list, ApsGoodsForecastUserGoodsDataDto.class);
    return new ApsGoodsForecastUserGoodsDataQueryByIdListRes().setDataList(dataList);
  }
}
