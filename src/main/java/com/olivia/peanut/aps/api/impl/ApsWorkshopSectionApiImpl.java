package com.olivia.peanut.aps.api.impl;

import com.github.yulichang.wrapper.MPJLambdaWrapper;
import com.olivia.peanut.aps.api.ApsWorkshopSectionApi;
import com.olivia.peanut.aps.api.entity.workshopSection.*;
import com.olivia.peanut.aps.api.impl.listener.WorkshopSectionImportListener;
import com.olivia.peanut.aps.model.ApsWorkshopSection;
import com.olivia.peanut.aps.service.ApsWorkshopSectionService;
import com.olivia.sdk.utils.$;
import com.olivia.sdk.utils.DynamicsPage;
import com.olivia.sdk.utils.PoiExcelUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * 工段信息(WorkshopSection)表服务实现类
 *
 * @author makejava
 * @since 2024-03-11 10:55:22
 */
@RestController
public class ApsWorkshopSectionApiImpl implements ApsWorkshopSectionApi {

  private @Autowired ApsWorkshopSectionService apsWorkshopSectionService;

  /****
   * insert
   *
   */
  public @Override WorkshopSectionInsertRes insert(WorkshopSectionInsertReq req) {
    this.apsWorkshopSectionService.save($.copy(req, ApsWorkshopSection.class));
    return new WorkshopSectionInsertRes().setCount(1);
  }

  /****
   * deleteByIds
   *
   */
  public @Override WorkshopSectionDeleteByIdListRes deleteByIdList(WorkshopSectionDeleteByIdListReq req) {
    apsWorkshopSectionService.removeByIds(req.getIdList());
    return new WorkshopSectionDeleteByIdListRes();
  }

  /****
   * queryList
   *
   */
  public @Override WorkshopSectionQueryListRes queryList(WorkshopSectionQueryListReq req) {
    return apsWorkshopSectionService.queryList(req);
  }

  /****
   * updateById
   *
   */
  public @Override WorkshopSectionUpdateByIdRes updateById(WorkshopSectionUpdateByIdReq req) {
    apsWorkshopSectionService.updateById($.copy(req, ApsWorkshopSection.class));
    return new WorkshopSectionUpdateByIdRes();

  }

  public @Override DynamicsPage<WorkshopSectionExportQueryPageListInfoRes> queryPageList(WorkshopSectionExportQueryPageListReq req) {
    return apsWorkshopSectionService.queryPageList(req);
  }

  public @Override void queryPageListExport(WorkshopSectionExportQueryPageListReq req) {
    DynamicsPage<WorkshopSectionExportQueryPageListInfoRes> page = queryPageList(req);
    List<WorkshopSectionExportQueryPageListInfoRes> list = page.getDataList();
    // 类型转换，  更换枚举 等操作
    List<WorkshopSectionExportQueryPageListInfoRes> listInfoRes = $.copyList(list, WorkshopSectionExportQueryPageListInfoRes.class);
    PoiExcelUtil.export(WorkshopSectionExportQueryPageListInfoRes.class, listInfoRes, "工段信息");
  }

  public @Override WorkshopSectionImportRes importData(@RequestParam("file") MultipartFile file) {
    List<WorkshopSectionImportReq> reqList = PoiExcelUtil.readData(file, new WorkshopSectionImportListener(), WorkshopSectionImportReq.class);
    // 类型转换，  更换枚举 等操作
    List<ApsWorkshopSection> readList = $.copyList(reqList, ApsWorkshopSection.class);
    boolean bool = apsWorkshopSectionService.saveBatch(readList);
    int c = bool ? readList.size() : 0;
    return new WorkshopSectionImportRes().setCount(c);
  }

  public @Override WorkshopSectionQueryByIdListRes queryByIdListRes(WorkshopSectionQueryByIdListReq req) {
    MPJLambdaWrapper<ApsWorkshopSection> q = new MPJLambdaWrapper<ApsWorkshopSection>(ApsWorkshopSection.class)
        .selectAll(ApsWorkshopSection.class).in(ApsWorkshopSection::getId, req.getIdList());
    List<ApsWorkshopSection> list = this.apsWorkshopSectionService.list(q);
    List<WorkshopSectionQueryByIdListRes.Info> dataList = $.copyList(list, WorkshopSectionQueryByIdListRes.Info.class);
    return new WorkshopSectionQueryByIdListRes().setDataList(dataList);
  }
}
