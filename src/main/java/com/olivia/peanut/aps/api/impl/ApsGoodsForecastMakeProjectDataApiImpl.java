package com.olivia.peanut.aps.api.impl;


import com.github.yulichang.wrapper.MPJLambdaWrapper;
import com.olivia.peanut.aps.api.ApsGoodsForecastMakeProjectDataApi;
import com.olivia.peanut.aps.api.entity.apsGoodsForecastMakeProjectData.*;
import com.olivia.peanut.aps.api.impl.listener.ApsGoodsForecastMakeProjectDataImportListener;
import com.olivia.peanut.aps.model.ApsGoodsForecastMakeProjectData;
import com.olivia.peanut.aps.service.ApsGoodsForecastMakeProjectDataService;
import com.olivia.sdk.utils.$;
import com.olivia.sdk.utils.DynamicsPage;
import com.olivia.sdk.utils.PoiExcelUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * (ApsGoodsForecastMakeProjectData)表服务实现类
 *
 * @author peanut
 * @since 2024-05-10 13:58:07
 */
@RestController
public class ApsGoodsForecastMakeProjectDataApiImpl implements ApsGoodsForecastMakeProjectDataApi {

  private @Autowired ApsGoodsForecastMakeProjectDataService apsGoodsForecastMakeProjectDataService;

  /****
   * insert
   *
   */
  public @Override ApsGoodsForecastMakeProjectDataInsertRes insert(ApsGoodsForecastMakeProjectDataInsertReq req) {
    this.apsGoodsForecastMakeProjectDataService.save($.copy(req, ApsGoodsForecastMakeProjectData.class));
    return new ApsGoodsForecastMakeProjectDataInsertRes().setCount(1);
  }

  /****
   * deleteByIds
   *
   */
  public @Override ApsGoodsForecastMakeProjectDataDeleteByIdListRes deleteByIdList(ApsGoodsForecastMakeProjectDataDeleteByIdListReq req) {
    apsGoodsForecastMakeProjectDataService.removeByIds(req.getIdList());
    return new ApsGoodsForecastMakeProjectDataDeleteByIdListRes();
  }

  /****
   * queryList
   *
   */
  public @Override ApsGoodsForecastMakeProjectDataQueryListRes queryList(ApsGoodsForecastMakeProjectDataQueryListReq req) {
    return apsGoodsForecastMakeProjectDataService.queryList(req);
  }

  /****
   * updateById
   *
   */
  public @Override ApsGoodsForecastMakeProjectDataUpdateByIdRes updateById(ApsGoodsForecastMakeProjectDataUpdateByIdReq req) {
    apsGoodsForecastMakeProjectDataService.updateById($.copy(req, ApsGoodsForecastMakeProjectData.class));
    return new ApsGoodsForecastMakeProjectDataUpdateByIdRes();

  }

  public @Override DynamicsPage<ApsGoodsForecastMakeProjectDataExportQueryPageListInfoRes> queryPageList(ApsGoodsForecastMakeProjectDataExportQueryPageListReq req) {
    return apsGoodsForecastMakeProjectDataService.queryPageList(req);
  }

  public @Override void queryPageListExport(ApsGoodsForecastMakeProjectDataExportQueryPageListReq req) {
    DynamicsPage<ApsGoodsForecastMakeProjectDataExportQueryPageListInfoRes> page = queryPageList(req);
    List<ApsGoodsForecastMakeProjectDataExportQueryPageListInfoRes> list = page.getDataList();
    // 类型转换，  更换枚举 等操作
    List<ApsGoodsForecastMakeProjectDataExportQueryPageListInfoRes> listInfoRes = $.copyList(list, ApsGoodsForecastMakeProjectDataExportQueryPageListInfoRes.class);
    PoiExcelUtil.export(ApsGoodsForecastMakeProjectDataExportQueryPageListInfoRes.class, listInfoRes, "");
  }

  public @Override ApsGoodsForecastMakeProjectDataImportRes importData(@RequestParam("file") MultipartFile file) {
    List<ApsGoodsForecastMakeProjectDataImportReq> reqList = PoiExcelUtil.readData(file, new ApsGoodsForecastMakeProjectDataImportListener(),
        ApsGoodsForecastMakeProjectDataImportReq.class);
    // 类型转换，  更换枚举 等操作
    List<ApsGoodsForecastMakeProjectData> readList = $.copyList(reqList, ApsGoodsForecastMakeProjectData.class);
    boolean bool = apsGoodsForecastMakeProjectDataService.saveBatch(readList);
    int c = bool ? readList.size() : 0;
    return new ApsGoodsForecastMakeProjectDataImportRes().setCount(c);
  }

  public @Override ApsGoodsForecastMakeProjectDataQueryByIdListRes queryByIdListRes(ApsGoodsForecastMakeProjectDataQueryByIdListReq req) {
    MPJLambdaWrapper<ApsGoodsForecastMakeProjectData> q = new MPJLambdaWrapper<ApsGoodsForecastMakeProjectData>(ApsGoodsForecastMakeProjectData.class)
        .selectAll(ApsGoodsForecastMakeProjectData.class).in(ApsGoodsForecastMakeProjectData::getId, req.getIdList());
    List<ApsGoodsForecastMakeProjectData> list = this.apsGoodsForecastMakeProjectDataService.list(q);
    List<ApsGoodsForecastMakeProjectDataDto> dataList = $.copyList(list, ApsGoodsForecastMakeProjectDataDto.class);
    this.apsGoodsForecastMakeProjectDataService.setName(dataList);
    return new ApsGoodsForecastMakeProjectDataQueryByIdListRes().setDataList(dataList);
  }
}
