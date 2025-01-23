package com.olivia.peanut.aps.api.impl;


import com.github.yulichang.wrapper.MPJLambdaWrapper;
import com.olivia.peanut.aps.api.ApsSchedulingVersionLimitApi;
import com.olivia.peanut.aps.api.entity.apsSchedulingVersionLimit.*;
import com.olivia.peanut.aps.api.impl.listener.ApsSchedulingVersionLimitImportListener;
import com.olivia.peanut.aps.model.ApsSchedulingVersionLimit;
import com.olivia.peanut.aps.service.ApsSchedulingVersionLimitService;
import com.olivia.sdk.utils.$;
import com.olivia.sdk.utils.DynamicsPage;
import com.olivia.sdk.utils.PoiExcelUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * (ApsSchedulingVersionLimit)表服务实现类
 *
 * @author peanut
 * @since 2024-04-19 14:56:59
 */
@RestController
public class ApsSchedulingVersionLimitApiImpl implements ApsSchedulingVersionLimitApi {

  private @Autowired ApsSchedulingVersionLimitService apsSchedulingVersionLimitService;

  /****
   * insert
   *
   */
  public @Override ApsSchedulingVersionLimitInsertRes insert(ApsSchedulingVersionLimitInsertReq req) {
    this.apsSchedulingVersionLimitService.save($.copy(req, ApsSchedulingVersionLimit.class));
    return new ApsSchedulingVersionLimitInsertRes().setCount(1);
  }

  /****
   * deleteByIds
   *
   */
  public @Override ApsSchedulingVersionLimitDeleteByIdListRes deleteByIdList(ApsSchedulingVersionLimitDeleteByIdListReq req) {
    apsSchedulingVersionLimitService.removeByIds(req.getIdList());
    return new ApsSchedulingVersionLimitDeleteByIdListRes();
  }

  /****
   * queryList
   *
   */
  public @Override ApsSchedulingVersionLimitQueryListRes queryList(ApsSchedulingVersionLimitQueryListReq req) {
    return apsSchedulingVersionLimitService.queryList(req);
  }

  /****
   * updateById
   *
   */
  public @Override ApsSchedulingVersionLimitUpdateByIdRes updateById(ApsSchedulingVersionLimitUpdateByIdReq req) {
    apsSchedulingVersionLimitService.updateById($.copy(req, ApsSchedulingVersionLimit.class));
    return new ApsSchedulingVersionLimitUpdateByIdRes();

  }

  public @Override DynamicsPage<ApsSchedulingVersionLimitExportQueryPageListInfoRes> queryPageList(ApsSchedulingVersionLimitExportQueryPageListReq req) {
    return apsSchedulingVersionLimitService.queryPageList(req);
  }

  public @Override void queryPageListExport(ApsSchedulingVersionLimitExportQueryPageListReq req) {
    DynamicsPage<ApsSchedulingVersionLimitExportQueryPageListInfoRes> page = queryPageList(req);
    List<ApsSchedulingVersionLimitExportQueryPageListInfoRes> list = page.getDataList();
    // 类型转换，  更换枚举 等操作
    List<ApsSchedulingVersionLimitExportQueryPageListInfoRes> listInfoRes = $.copyList(list, ApsSchedulingVersionLimitExportQueryPageListInfoRes.class);
    PoiExcelUtil.export(ApsSchedulingVersionLimitExportQueryPageListInfoRes.class, listInfoRes, "");
  }

  public @Override ApsSchedulingVersionLimitImportRes importData(@RequestParam("file") MultipartFile file) {
    List<ApsSchedulingVersionLimitImportReq> reqList = PoiExcelUtil.readData(file, new ApsSchedulingVersionLimitImportListener(), ApsSchedulingVersionLimitImportReq.class);
    // 类型转换，  更换枚举 等操作
    List<ApsSchedulingVersionLimit> readList = $.copyList(reqList, ApsSchedulingVersionLimit.class);
    boolean bool = apsSchedulingVersionLimitService.saveBatch(readList);
    int c = bool ? readList.size() : 0;
    return new ApsSchedulingVersionLimitImportRes().setCount(c);
  }

  public @Override ApsSchedulingVersionLimitQueryByIdListRes queryByIdListRes(ApsSchedulingVersionLimitQueryByIdListReq req) {
    MPJLambdaWrapper<ApsSchedulingVersionLimit> q = new MPJLambdaWrapper<ApsSchedulingVersionLimit>(ApsSchedulingVersionLimit.class)
        .selectAll(ApsSchedulingVersionLimit.class).in(ApsSchedulingVersionLimit::getId, req.getIdList());
    List<ApsSchedulingVersionLimit> list = this.apsSchedulingVersionLimitService.list(q);
    List<ApsSchedulingVersionLimitDto> dataList = $.copyList(list, ApsSchedulingVersionLimitDto.class);
    this.apsSchedulingVersionLimitService.setName(dataList);
    return new ApsSchedulingVersionLimitQueryByIdListRes().setDataList(dataList);
  }
}
