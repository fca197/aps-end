package com.olivia.peanut.aps.api.impl;


import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.github.yulichang.wrapper.MPJLambdaWrapper;
import com.olivia.peanut.aps.api.ApsStatusApi;
import com.olivia.peanut.aps.api.entity.apsStatus.*;
import com.olivia.peanut.aps.api.impl.listener.ApsStatusImportListener;
import com.olivia.peanut.aps.model.ApsStatus;
import com.olivia.peanut.aps.service.ApsStatusService;
import com.olivia.sdk.utils.$;
import com.olivia.sdk.utils.BaseEntity;
import com.olivia.sdk.utils.DynamicsPage;
import com.olivia.sdk.utils.PoiExcelUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * (ApsStatus)表服务实现类
 *
 * @author peanut
 * @since 2024-04-01 10:50:11
 */
@RestController
public class ApsStatusApiImpl implements ApsStatusApi {

  private @Autowired ApsStatusService apsStatusService;

  /****
   * insert
   *
   */
  public @Override ApsStatusInsertRes insert(ApsStatusInsertReq req) {
    this.apsStatusService.save($.copy(req, ApsStatus.class));
    return new ApsStatusInsertRes().setCount(1);
  }

  /****
   * deleteByIds
   *
   */
  public @Override ApsStatusDeleteByIdListRes deleteByIdList(ApsStatusDeleteByIdListReq req) {
    apsStatusService.removeByIds(req.getIdList());
    return new ApsStatusDeleteByIdListRes();
  }

  /****
   * queryList
   *
   */
  public @Override ApsStatusQueryListRes queryList(ApsStatusQueryListReq req) {
    return apsStatusService.queryList(req);
  }

  /****
   * updateById
   *
   */
  public @Override ApsStatusUpdateByIdRes updateById(ApsStatusUpdateByIdReq req) {
    apsStatusService.updateById($.copy(req, ApsStatus.class));
    apsStatusService.update(new LambdaUpdateWrapper<ApsStatus>() //
        .eq(BaseEntity::getId, req.getId()).set(ApsStatus::getOrderStatusId, req.getOrderStatusId()));
    return new ApsStatusUpdateByIdRes();

  }

  public @Override DynamicsPage<ApsStatusExportQueryPageListInfoRes> queryPageList(ApsStatusExportQueryPageListReq req) {
    return apsStatusService.queryPageList(req);
  }

  public @Override void queryPageListExport(ApsStatusExportQueryPageListReq req) {
    DynamicsPage<ApsStatusExportQueryPageListInfoRes> page = queryPageList(req);
    List<ApsStatusExportQueryPageListInfoRes> list = page.getDataList();
    // 类型转换，  更换枚举 等操作
    List<ApsStatusExportQueryPageListInfoRes> listInfoRes = $.copyList(list, ApsStatusExportQueryPageListInfoRes.class);
    PoiExcelUtil.export(ApsStatusExportQueryPageListInfoRes.class, listInfoRes, "");
  }

  public @Override ApsStatusImportRes importData(@RequestParam("file") MultipartFile file) {
    List<ApsStatusImportReq> reqList = PoiExcelUtil.readData(file, new ApsStatusImportListener(), ApsStatusImportReq.class);
    // 类型转换，  更换枚举 等操作
    List<ApsStatus> readList = $.copyList(reqList, ApsStatus.class);
    boolean bool = apsStatusService.saveBatch(readList);
    int c = bool ? readList.size() : 0;
    return new ApsStatusImportRes().setCount(c);
  }

  public @Override ApsStatusQueryByIdListRes queryByIdListRes(ApsStatusQueryByIdListReq req) {
    MPJLambdaWrapper<ApsStatus> q = new MPJLambdaWrapper<ApsStatus>(ApsStatus.class)
        .selectAll(ApsStatus.class).in(ApsStatus::getId, req.getIdList());
    List<ApsStatus> list = this.apsStatusService.list(q);
    List<ApsStatusDto> dataList = $.copyList(list, ApsStatusDto.class);
    return new ApsStatusQueryByIdListRes().setDataList(dataList);
  }
}
