package com.olivia.peanut.portal.api.impl;


import com.github.yulichang.wrapper.MPJLambdaWrapper;
import com.olivia.peanut.portal.api.ShiftApi;
import com.olivia.peanut.portal.api.entity.shift.*;
import com.olivia.peanut.portal.api.impl.listener.ShiftImportListener;
import com.olivia.peanut.portal.model.Shift;
import com.olivia.peanut.portal.service.ShiftService;
import com.olivia.sdk.utils.$;
import com.olivia.sdk.utils.DynamicsPage;
import com.olivia.sdk.utils.PoiExcelUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * (Shift)表服务实现类
 *
 * @author peanut
 * @since 2024-04-04 12:10:15
 */
@RestController
public class ShiftApiImpl implements ShiftApi {

  private @Autowired ShiftService shiftService;

  /****
   * insert
   *
   */
  public @Override ShiftInsertRes insert(ShiftInsertReq req) {
    return this.shiftService.save(req);
  }

  /****
   * deleteByIds
   *
   */
  public @Override ShiftDeleteByIdListRes deleteByIdList(ShiftDeleteByIdListReq req) {
    shiftService.removeByIds(req.getIdList());
    return new ShiftDeleteByIdListRes();
  }

  /****
   * queryList
   *
   */
  public @Override ShiftQueryListRes queryList(ShiftQueryListReq req) {
    return shiftService.queryList(req);
  }

  /****
   * updateById
   *
   */
  public @Override ShiftUpdateByIdRes updateById(ShiftUpdateByIdReq req) {
    return shiftService.updateById(req);
//    return new ShiftUpdateByIdRes();

  }

  public @Override DynamicsPage<ShiftExportQueryPageListInfoRes> queryPageList(ShiftExportQueryPageListReq req) {
    return shiftService.queryPageList(req);
  }

  public @Override void queryPageListExport(ShiftExportQueryPageListReq req) {
    DynamicsPage<ShiftExportQueryPageListInfoRes> page = queryPageList(req);
    List<ShiftExportQueryPageListInfoRes> list = page.getDataList();
    // 类型转换，  更换枚举 等操作
    List<ShiftExportQueryPageListInfoRes> listInfoRes = $.copyList(list, ShiftExportQueryPageListInfoRes.class);
    PoiExcelUtil.export(ShiftExportQueryPageListInfoRes.class, listInfoRes, "");
  }

  public @Override ShiftImportRes importData(@RequestParam("file") MultipartFile file) {
    List<ShiftImportReq> reqList = PoiExcelUtil.readData(file, new ShiftImportListener(), ShiftImportReq.class);
    // 类型转换，  更换枚举 等操作
    List<Shift> readList = $.copyList(reqList, Shift.class);
    boolean bool = shiftService.saveBatch(readList);
    int c = bool ? readList.size() : 0;
    return new ShiftImportRes().setCount(c);
  }

  public @Override ShiftQueryByIdListRes queryByIdListRes(ShiftQueryByIdListReq req) {
    MPJLambdaWrapper<Shift> q = new MPJLambdaWrapper<Shift>(Shift.class)
        .selectAll(Shift.class).in(Shift::getId, req.getIdList());
    List<Shift> list = this.shiftService.list(q);
    List<ShiftDto> dataList = $.copyList(list, ShiftDto.class);
    this.shiftService.setName(dataList);
    return new ShiftQueryByIdListRes().setDataList(dataList);
  }
}
