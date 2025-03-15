package com.olivia.peanut.aps.api.impl;

import com.github.yulichang.wrapper.MPJLambdaWrapper;
import com.olivia.peanut.aps.api.ApsWorkshopStationApi;
import com.olivia.peanut.aps.api.entity.workshopStation.*;
import com.olivia.peanut.aps.api.impl.listener.WorkshopStationImportListener;
import com.olivia.peanut.aps.model.ApsWorkshopStation;
import com.olivia.peanut.aps.service.ApsWorkshopStationService;
import com.olivia.sdk.utils.$;
import com.olivia.sdk.utils.DynamicsPage;
import com.olivia.sdk.utils.PoiExcelUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * 工位信息(WorkshopStation)表服务实现类
 *
 * @author makejava
 * @since 2024-03-11 10:55:23
 */
@RestController
public class ApsWorkshopStationApiImpl implements ApsWorkshopStationApi {

  private @Autowired ApsWorkshopStationService apsWorkshopStationService;

  /****
   * insert
   *
   */
  public @Override WorkshopStationInsertRes insert(WorkshopStationInsertReq req) {
    this.apsWorkshopStationService.save($.copy(req, ApsWorkshopStation.class));
    return new WorkshopStationInsertRes().setCount(1);
  }

  /****
   * deleteByIds
   *
   */
  public @Override WorkshopStationDeleteByIdListRes deleteByIdList(WorkshopStationDeleteByIdListReq req) {
    apsWorkshopStationService.removeByIds(req.getIdList());
    return new WorkshopStationDeleteByIdListRes();
  }

  /****
   * queryList
   *
   */
  public @Override WorkshopStationQueryListRes queryList(WorkshopStationQueryListReq req) {
    return apsWorkshopStationService.queryList(req);
  }

  /****
   * updateById
   *
   */
  public @Override WorkshopStationUpdateByIdRes updateById(WorkshopStationUpdateByIdReq req) {
    apsWorkshopStationService.updateById($.copy(req, ApsWorkshopStation.class));
    return new WorkshopStationUpdateByIdRes();

  }

  public @Override DynamicsPage<WorkshopStationExportQueryPageListInfoRes> queryPageList(WorkshopStationExportQueryPageListReq req) {
    return apsWorkshopStationService.queryPageList(req);
  }

  public @Override void queryPageListExport(WorkshopStationExportQueryPageListReq req) {
    DynamicsPage<WorkshopStationExportQueryPageListInfoRes> page = queryPageList(req);
    List<WorkshopStationExportQueryPageListInfoRes> list = page.getDataList();
    // 类型转换，  更换枚举 等操作
    List<WorkshopStationExportQueryPageListInfoRes> listInfoRes = $.copyList(list, WorkshopStationExportQueryPageListInfoRes.class);
    PoiExcelUtil.export(WorkshopStationExportQueryPageListInfoRes.class, listInfoRes, "工位信息");
  }

  public @Override WorkshopStationImportRes importData(@RequestParam("file") MultipartFile file) {
    List<WorkshopStationImportReq> reqList = PoiExcelUtil.readData(file, new WorkshopStationImportListener(), WorkshopStationImportReq.class);
    // 类型转换，  更换枚举 等操作
    List<ApsWorkshopStation> readList = $.copyList(reqList, ApsWorkshopStation.class);
    boolean bool = apsWorkshopStationService.saveBatch(readList);
    int c = bool ? readList.size() : 0;
    return new WorkshopStationImportRes().setCount(c);
  }

  public @Override WorkshopStationQueryByIdListRes queryByIdListRes(WorkshopStationQueryByIdListReq req) {
    MPJLambdaWrapper<ApsWorkshopStation> q = new MPJLambdaWrapper<ApsWorkshopStation>(ApsWorkshopStation.class)
        .selectAll(ApsWorkshopStation.class).in(ApsWorkshopStation::getId, req.getIdList());
    List<ApsWorkshopStation> list = this.apsWorkshopStationService.list(q);
    List<WorkshopStationQueryByIdListRes.Info> dataList = $.copyList(list, WorkshopStationQueryByIdListRes.Info.class);
    return new WorkshopStationQueryByIdListRes().setDataList(dataList);
  }
}
