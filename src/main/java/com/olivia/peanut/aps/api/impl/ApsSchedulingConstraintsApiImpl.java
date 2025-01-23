package com.olivia.peanut.aps.api.impl;


import com.github.yulichang.wrapper.MPJLambdaWrapper;
import com.olivia.peanut.aps.api.ApsSchedulingConstraintsApi;
import com.olivia.peanut.aps.api.entity.apsSchedulingConstraints.*;
import com.olivia.peanut.aps.api.impl.listener.ApsSchedulingConstraintsImportListener;
import com.olivia.peanut.aps.model.ApsSchedulingConstraints;
import com.olivia.peanut.aps.service.ApsSchedulingConstraintsService;
import com.olivia.sdk.utils.$;
import com.olivia.sdk.utils.DynamicsPage;
import com.olivia.sdk.utils.PoiExcelUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * (ApsSchedulingConstraints)表服务实现类
 *
 * @author peanut
 * @since 2024-04-13 21:32:12
 */
@RestController
public class ApsSchedulingConstraintsApiImpl implements ApsSchedulingConstraintsApi {

  private @Autowired ApsSchedulingConstraintsService apsSchedulingConstraintsService;

  /****
   * insert
   *
   */
  public @Override ApsSchedulingConstraintsInsertRes insert(ApsSchedulingConstraintsInsertReq req) {
    this.apsSchedulingConstraintsService.save($.copy(req, ApsSchedulingConstraints.class));
    return new ApsSchedulingConstraintsInsertRes().setCount(1);
  }

  /****
   * deleteByIds
   *
   */
  public @Override ApsSchedulingConstraintsDeleteByIdListRes deleteByIdList(ApsSchedulingConstraintsDeleteByIdListReq req) {
    apsSchedulingConstraintsService.removeByIds(req.getIdList());
    return new ApsSchedulingConstraintsDeleteByIdListRes();
  }

  /****
   * queryList
   *
   */
  public @Override ApsSchedulingConstraintsQueryListRes queryList(ApsSchedulingConstraintsQueryListReq req) {
    return apsSchedulingConstraintsService.queryList(req);
  }

  /****
   * updateById
   *
   */
  public @Override ApsSchedulingConstraintsUpdateByIdRes updateById(ApsSchedulingConstraintsUpdateByIdReq req) {
    apsSchedulingConstraintsService.updateById($.copy(req, ApsSchedulingConstraints.class));
    return new ApsSchedulingConstraintsUpdateByIdRes();

  }

  public @Override DynamicsPage<ApsSchedulingConstraintsExportQueryPageListInfoRes> queryPageList(ApsSchedulingConstraintsExportQueryPageListReq req) {
    return apsSchedulingConstraintsService.queryPageList(req);
  }

  public @Override void queryPageListExport(ApsSchedulingConstraintsExportQueryPageListReq req) {
    DynamicsPage<ApsSchedulingConstraintsExportQueryPageListInfoRes> page = queryPageList(req);
    List<ApsSchedulingConstraintsExportQueryPageListInfoRes> list = page.getDataList();
    // 类型转换，  更换枚举 等操作
    List<ApsSchedulingConstraintsExportQueryPageListInfoRes> listInfoRes = $.copyList(list, ApsSchedulingConstraintsExportQueryPageListInfoRes.class);
    PoiExcelUtil.export(ApsSchedulingConstraintsExportQueryPageListInfoRes.class, listInfoRes, "");
  }

  public @Override ApsSchedulingConstraintsImportRes importData(@RequestParam("file") MultipartFile file) {
    List<ApsSchedulingConstraintsImportReq> reqList = PoiExcelUtil.readData(file, new ApsSchedulingConstraintsImportListener(), ApsSchedulingConstraintsImportReq.class);
    // 类型转换，  更换枚举 等操作
    List<ApsSchedulingConstraints> readList = $.copyList(reqList, ApsSchedulingConstraints.class);
    boolean bool = apsSchedulingConstraintsService.saveBatch(readList);
    int c = bool ? readList.size() : 0;
    return new ApsSchedulingConstraintsImportRes().setCount(c);
  }

  public @Override ApsSchedulingConstraintsQueryByIdListRes queryByIdListRes(ApsSchedulingConstraintsQueryByIdListReq req) {
    MPJLambdaWrapper<ApsSchedulingConstraints> q = new MPJLambdaWrapper<>(ApsSchedulingConstraints.class)
        .selectAll(ApsSchedulingConstraints.class).in(ApsSchedulingConstraints::getId, req.getIdList());
    List<ApsSchedulingConstraints> list = this.apsSchedulingConstraintsService.list(q);
    List<ApsSchedulingConstraintsDto> dataList = $.copyList(list, ApsSchedulingConstraintsDto.class);
    this.apsSchedulingConstraintsService.setName(dataList);
    return new ApsSchedulingConstraintsQueryByIdListRes().setDataList(dataList);
  }

  @Override
  public ApsSchedulingConstraintsGetUseFieldRes getUseField() {
    return this.apsSchedulingConstraintsService.getUseField();
  }
}
