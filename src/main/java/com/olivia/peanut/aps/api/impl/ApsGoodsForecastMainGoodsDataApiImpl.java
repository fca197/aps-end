package com.olivia.peanut.aps.api.impl;


import com.github.yulichang.wrapper.MPJLambdaWrapper;
import com.olivia.peanut.aps.api.ApsGoodsForecastMainGoodsDataApi;
import com.olivia.peanut.aps.api.entity.apsGoodsForecastMainGoodsData.*;
import com.olivia.peanut.aps.api.impl.listener.ApsGoodsForecastMainGoodsDataImportListener;
import com.olivia.peanut.aps.model.ApsGoodsForecastMainGoodsData;
import com.olivia.peanut.aps.service.ApsGoodsForecastMainGoodsDataService;
import com.olivia.sdk.utils.$;
import com.olivia.sdk.utils.DynamicsPage;
import com.olivia.sdk.utils.PoiExcelUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * (ApsGoodsForecastMainGoodsData)表服务实现类
 *
 * @author peanut
 * @since 2024-04-02 13:44:28
 */
@Slf4j
@RestController
public class ApsGoodsForecastMainGoodsDataApiImpl implements ApsGoodsForecastMainGoodsDataApi {

  private @Autowired ApsGoodsForecastMainGoodsDataService apsGoodsForecastMainGoodsDataService;

  /****
   * insert
   *
   */
  public @Override ApsGoodsForecastMainGoodsDataInsertRes insert(ApsGoodsForecastMainGoodsDataInsertReq req) {
    this.apsGoodsForecastMainGoodsDataService.save($.copy(req, ApsGoodsForecastMainGoodsData.class));
    return new ApsGoodsForecastMainGoodsDataInsertRes().setCount(1);
  }

  /****
   * deleteByIds
   *
   */
  public @Override ApsGoodsForecastMainGoodsDataDeleteByIdListRes deleteByIdList(ApsGoodsForecastMainGoodsDataDeleteByIdListReq req) {
    apsGoodsForecastMainGoodsDataService.removeByIds(req.getIdList());
    return new ApsGoodsForecastMainGoodsDataDeleteByIdListRes();
  }

  /****
   * queryList
   *
   */
  public @Override ApsGoodsForecastMainGoodsDataQueryListRes queryList(ApsGoodsForecastMainGoodsDataQueryListReq req) {
    return apsGoodsForecastMainGoodsDataService.queryList(req);
  }

  /****
   * updateById
   *
   */
  public @Override ApsGoodsForecastMainGoodsDataUpdateByIdRes updateById(ApsGoodsForecastMainGoodsDataUpdateByIdReq req) {
    apsGoodsForecastMainGoodsDataService.updateById($.copy(req, ApsGoodsForecastMainGoodsData.class));
    return new ApsGoodsForecastMainGoodsDataUpdateByIdRes();

  }

  public @Override DynamicsPage<ApsGoodsForecastMainGoodsDataExportQueryPageListInfoRes> queryPageList(ApsGoodsForecastMainGoodsDataExportQueryPageListReq req) {
    return apsGoodsForecastMainGoodsDataService.queryPageList(req);
  }

  public @Override void queryPageListExport(ApsGoodsForecastMainGoodsDataExportQueryPageListReq req) {
    DynamicsPage<ApsGoodsForecastMainGoodsDataExportQueryPageListInfoRes> page = queryPageList(req);
    List<ApsGoodsForecastMainGoodsDataExportQueryPageListInfoRes> list = page.getDataList();
    // 类型转换，  更换枚举 等操作
    List<ApsGoodsForecastMainGoodsDataExportQueryPageListInfoRes> listInfoRes = $.copyList(list, ApsGoodsForecastMainGoodsDataExportQueryPageListInfoRes.class);
    PoiExcelUtil.export(ApsGoodsForecastMainGoodsDataExportQueryPageListInfoRes.class, listInfoRes, "");
  }

  public @Override ApsGoodsForecastMainGoodsDataImportRes importData(@RequestParam("file") MultipartFile file) {
    List<ApsGoodsForecastMainGoodsDataImportReq> reqList = PoiExcelUtil.readData(file, new ApsGoodsForecastMainGoodsDataImportListener(),
        ApsGoodsForecastMainGoodsDataImportReq.class);
    // 类型转换，  更换枚举 等操作
    List<ApsGoodsForecastMainGoodsData> readList = $.copyList(reqList, ApsGoodsForecastMainGoodsData.class);
    boolean bool = apsGoodsForecastMainGoodsDataService.saveBatch(readList);
    int c = bool ? readList.size() : 0;
    return new ApsGoodsForecastMainGoodsDataImportRes().setCount(c);
  }

  public @Override ApsGoodsForecastMainGoodsDataQueryByIdListRes queryByIdListRes(ApsGoodsForecastMainGoodsDataQueryByIdListReq req) {
    MPJLambdaWrapper<ApsGoodsForecastMainGoodsData> q = new MPJLambdaWrapper<ApsGoodsForecastMainGoodsData>(ApsGoodsForecastMainGoodsData.class)
        .selectAll(ApsGoodsForecastMainGoodsData.class).in(ApsGoodsForecastMainGoodsData::getId, req.getIdList());
    List<ApsGoodsForecastMainGoodsData> list = this.apsGoodsForecastMainGoodsDataService.list(q);
    List<ApsGoodsForecastMainGoodsDataDto> dataList = $.copyList(list, ApsGoodsForecastMainGoodsDataDto.class);
    return new ApsGoodsForecastMainGoodsDataQueryByIdListRes().setDataList(dataList);
  }
}
