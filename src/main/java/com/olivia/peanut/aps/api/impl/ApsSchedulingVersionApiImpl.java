package com.olivia.peanut.aps.api.impl;


import com.baomidou.mybatisplus.core.toolkit.IdWorker;
import com.github.yulichang.wrapper.MPJLambdaWrapper;
import com.olivia.peanut.aps.api.ApsSchedulingVersionApi;
import com.olivia.peanut.aps.api.entity.apsSchedulingVersion.*;
import com.olivia.peanut.aps.api.impl.listener.ApsSchedulingVersionImportListener;
import com.olivia.peanut.aps.model.ApsSchedulingVersion;
import com.olivia.peanut.aps.service.ApsSchedulingVersionService;
import com.olivia.sdk.utils.$;
import com.olivia.sdk.utils.DynamicsPage;
import com.olivia.sdk.utils.PoiExcelUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * (ApsSchedulingVersion)表服务实现类
 *
 * @author peanut
 * @since 2024-04-13 21:32:13
 */
@RestController
public class ApsSchedulingVersionApiImpl implements ApsSchedulingVersionApi {

  private @Autowired ApsSchedulingVersionService apsSchedulingVersionService;

  /****
   * insert
   *
   */
  public @Override ApsSchedulingVersionInsertRes insert(ApsSchedulingVersionInsertReq req) {
    ApsSchedulingVersion version = $.copy(req, ApsSchedulingVersion.class);
    version.setId(IdWorker.getId());
    version.setVersionStep(1);
    this.apsSchedulingVersionService.save(version);
    return new ApsSchedulingVersionInsertRes().setCount(1).setId(version.getId());
  }

  /****
   * deleteByIds
   *
   */
  public @Override ApsSchedulingVersionDeleteByIdListRes deleteByIdList(ApsSchedulingVersionDeleteByIdListReq req) {
    apsSchedulingVersionService.removeByIds(req.getIdList());
    return new ApsSchedulingVersionDeleteByIdListRes();
  }

  /****
   * queryList
   *
   */
  public @Override ApsSchedulingVersionQueryListRes queryList(ApsSchedulingVersionQueryListReq req) {
    return apsSchedulingVersionService.queryList(req);
  }

  /****
   * updateById
   *
   */
  public @Override ApsSchedulingVersionUpdateByIdRes updateById(ApsSchedulingVersionUpdateByIdReq req) {
    apsSchedulingVersionService.updateById($.copy(req, ApsSchedulingVersion.class));
    return new ApsSchedulingVersionUpdateByIdRes();

  }

  public @Override DynamicsPage<ApsSchedulingVersionExportQueryPageListInfoRes> queryPageList(ApsSchedulingVersionExportQueryPageListReq req) {
    return apsSchedulingVersionService.queryPageList(req);
  }

  public @Override void queryPageListExport(ApsSchedulingVersionExportQueryPageListReq req) {
    DynamicsPage<ApsSchedulingVersionExportQueryPageListInfoRes> page = queryPageList(req);
    List<ApsSchedulingVersionExportQueryPageListInfoRes> list = page.getDataList();
    // 类型转换，  更换枚举 等操作
    List<ApsSchedulingVersionExportQueryPageListInfoRes> listInfoRes = $.copyList(list, ApsSchedulingVersionExportQueryPageListInfoRes.class);
    PoiExcelUtil.export(ApsSchedulingVersionExportQueryPageListInfoRes.class, listInfoRes, "");
  }

  public @Override ApsSchedulingVersionImportRes importData(@RequestParam("file") MultipartFile file) {
    List<ApsSchedulingVersionImportReq> reqList = PoiExcelUtil.readData(file, new ApsSchedulingVersionImportListener(), ApsSchedulingVersionImportReq.class);
    // 类型转换，  更换枚举 等操作
    List<ApsSchedulingVersion> readList = $.copyList(reqList, ApsSchedulingVersion.class);
    boolean bool = apsSchedulingVersionService.saveBatch(readList);
    int c = bool ? readList.size() : 0;
    return new ApsSchedulingVersionImportRes().setCount(c);
  }

  public @Override ApsSchedulingVersionQueryByIdListRes queryByIdListRes(ApsSchedulingVersionQueryByIdListReq req) {
    MPJLambdaWrapper<ApsSchedulingVersion> q = new MPJLambdaWrapper<ApsSchedulingVersion>(ApsSchedulingVersion.class)
        .selectAll(ApsSchedulingVersion.class).in(ApsSchedulingVersion::getId, req.getIdList());
    List<ApsSchedulingVersion> list = this.apsSchedulingVersionService.list(q);
    List<ApsSchedulingVersionDto> dataList = $.copyList(list, ApsSchedulingVersionDto.class);
    this.apsSchedulingVersionService.setName(dataList);
    return new ApsSchedulingVersionQueryByIdListRes().setDataList(dataList);
  }

  @Override
  public ApsSchedulingVersionUseConstraintsRes useConstraints(ApsSchedulingVersionUseConstraintsReq req) {
    return this.apsSchedulingVersionService.useConstraints(req);
  }

  @Override
  public ApsSchedulingVersionUseMakeCapacityRes useMakeCapacity(ApsSchedulingVersionUseMakeCapacityReq req) {
    return this.apsSchedulingVersionService.useMakeCapacity(req);
  }

  @Override
  public DynamicsPage<ApsSchedulingVersionUseConstraintsResultRes> useConstraintsResult(ApsSchedulingVersionUseConstraintsResultReq req) {
    return this.apsSchedulingVersionService.useConstraintsResult(req);
  }

  @Override
  public DynamicsPage<ApsSchedulingVersionUseMakeCapacityResultRes> useMakeCapacityResult(ApsSchedulingVersionUseMakeCapacityResultReq req) {
    return this.apsSchedulingVersionService.useMakeCapacityResult(req);
  }

  @Override
  public ApsSchedulingVersionFinishRes finish(ApsSchedulingVersionFinishReq req) {
    return this.apsSchedulingVersionService.finish(req);

  }
}
