package com.olivia.peanut.aps.api.impl;


import com.github.yulichang.wrapper.MPJLambdaWrapper;
import com.olivia.peanut.aps.api.ApsSchedulingVersionCapacityApi;
import com.olivia.peanut.aps.api.entity.apsSchedulingVersionCapacity.*;
import com.olivia.peanut.aps.api.impl.listener.ApsSchedulingVersionCapacityImportListener;
import com.olivia.peanut.aps.model.ApsSchedulingVersionCapacity;
import com.olivia.peanut.aps.service.ApsSchedulingVersionCapacityService;
import com.olivia.sdk.utils.$;
import com.olivia.sdk.utils.DynamicsPage;
import com.olivia.sdk.utils.PoiExcelUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * (ApsSchedulingVersionCapacity)表服务实现类
 *
 * @author peanut
 * @since 2024-04-18 14:57:34
 */
@RestController
public class ApsSchedulingVersionCapacityApiImpl implements ApsSchedulingVersionCapacityApi {

  private @Autowired ApsSchedulingVersionCapacityService apsSchedulingVersionCapacityService;

  /****
   * insert
   *
   */
  public @Override ApsSchedulingVersionCapacityInsertRes insert(ApsSchedulingVersionCapacityInsertReq req) {
    this.apsSchedulingVersionCapacityService.save($.copy(req, ApsSchedulingVersionCapacity.class));
    return new ApsSchedulingVersionCapacityInsertRes().setCount(1);
  }

  /****
   * deleteByIds
   *
   */
  public @Override ApsSchedulingVersionCapacityDeleteByIdListRes deleteByIdList(ApsSchedulingVersionCapacityDeleteByIdListReq req) {
    apsSchedulingVersionCapacityService.removeByIds(req.getIdList());
    return new ApsSchedulingVersionCapacityDeleteByIdListRes();
  }

  /****
   * queryList
   *
   */
  public @Override ApsSchedulingVersionCapacityQueryListRes queryList(ApsSchedulingVersionCapacityQueryListReq req) {
    return apsSchedulingVersionCapacityService.queryList(req);
  }

  /****
   * updateById
   *
   */
  public @Override ApsSchedulingVersionCapacityUpdateByIdRes updateById(ApsSchedulingVersionCapacityUpdateByIdReq req) {
    apsSchedulingVersionCapacityService.updateById($.copy(req, ApsSchedulingVersionCapacity.class));
    return new ApsSchedulingVersionCapacityUpdateByIdRes();

  }

  public @Override DynamicsPage<ApsSchedulingVersionCapacityExportQueryPageListInfoRes> queryPageList(ApsSchedulingVersionCapacityExportQueryPageListReq req) {
    return apsSchedulingVersionCapacityService.queryPageList(req);
  }

  public @Override void queryPageListExport(ApsSchedulingVersionCapacityExportQueryPageListReq req) {
    DynamicsPage<ApsSchedulingVersionCapacityExportQueryPageListInfoRes> page = queryPageList(req);
    List<ApsSchedulingVersionCapacityExportQueryPageListInfoRes> list = page.getDataList();
    // 类型转换，  更换枚举 等操作
    List<ApsSchedulingVersionCapacityExportQueryPageListInfoRes> listInfoRes = $.copyList(list, ApsSchedulingVersionCapacityExportQueryPageListInfoRes.class);
    PoiExcelUtil.export(ApsSchedulingVersionCapacityExportQueryPageListInfoRes.class, listInfoRes, "");
  }

  public @Override ApsSchedulingVersionCapacityImportRes importData(@RequestParam("file") MultipartFile file) {
    List<ApsSchedulingVersionCapacityImportReq> reqList = PoiExcelUtil.readData(file, new ApsSchedulingVersionCapacityImportListener(),
        ApsSchedulingVersionCapacityImportReq.class);
    // 类型转换，  更换枚举 等操作
    List<ApsSchedulingVersionCapacity> readList = $.copyList(reqList, ApsSchedulingVersionCapacity.class);
    boolean bool = apsSchedulingVersionCapacityService.saveBatch(readList);
    int c = bool ? readList.size() : 0;
    return new ApsSchedulingVersionCapacityImportRes().setCount(c);
  }

  public @Override ApsSchedulingVersionCapacityQueryByIdListRes queryByIdListRes(ApsSchedulingVersionCapacityQueryByIdListReq req) {
    MPJLambdaWrapper<ApsSchedulingVersionCapacity> q = new MPJLambdaWrapper<ApsSchedulingVersionCapacity>(ApsSchedulingVersionCapacity.class)
        .selectAll(ApsSchedulingVersionCapacity.class).in(ApsSchedulingVersionCapacity::getId, req.getIdList());
    List<ApsSchedulingVersionCapacity> list = this.apsSchedulingVersionCapacityService.list(q);
    List<ApsSchedulingVersionCapacityDto> dataList = $.copyList(list, ApsSchedulingVersionCapacityDto.class);
    this.apsSchedulingVersionCapacityService.setName(dataList);
    return new ApsSchedulingVersionCapacityQueryByIdListRes().setDataList(dataList);
  }
}
