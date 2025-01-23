package com.olivia.peanut.aps.api.impl;


import com.github.yulichang.wrapper.MPJLambdaWrapper;
import com.olivia.peanut.aps.api.ApsSchedulingVersionDayApi;
import com.olivia.peanut.aps.api.entity.apsSchedulingVersionDay.*;
import com.olivia.peanut.aps.api.impl.listener.ApsSchedulingVersionDayImportListener;
import com.olivia.peanut.aps.model.ApsSchedulingVersionDay;
import com.olivia.peanut.aps.service.ApsSchedulingVersionDayService;
import com.olivia.sdk.utils.$;
import com.olivia.sdk.utils.DynamicsPage;
import com.olivia.sdk.utils.PoiExcelUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * (ApsSchedulingVersionDay)表服务实现类
 *
 * @author peanut
 * @since 2024-04-23 14:37:05
 */
@RestController
public class ApsSchedulingVersionDayApiImpl implements ApsSchedulingVersionDayApi {

  private @Autowired ApsSchedulingVersionDayService apsSchedulingVersionDayService;

  /****
   * insert
   *
   */
  public @Override ApsSchedulingVersionDayInsertRes insert(ApsSchedulingVersionDayInsertReq req) {
    this.apsSchedulingVersionDayService.save($.copy(req, ApsSchedulingVersionDay.class));
    return new ApsSchedulingVersionDayInsertRes().setCount(1);
  }

  /****
   * deleteByIds
   *
   */
  public @Override ApsSchedulingVersionDayDeleteByIdListRes deleteByIdList(ApsSchedulingVersionDayDeleteByIdListReq req) {
    apsSchedulingVersionDayService.removeByIds(req.getIdList());
    return new ApsSchedulingVersionDayDeleteByIdListRes();
  }

  /****
   * queryList
   *
   */
  public @Override ApsSchedulingVersionDayQueryListRes queryList(ApsSchedulingVersionDayQueryListReq req) {
    return apsSchedulingVersionDayService.queryList(req);
  }

  /****
   * updateById
   *
   */
  public @Override ApsSchedulingVersionDayUpdateByIdRes updateById(ApsSchedulingVersionDayUpdateByIdReq req) {
    apsSchedulingVersionDayService.updateById($.copy(req, ApsSchedulingVersionDay.class));
    return new ApsSchedulingVersionDayUpdateByIdRes();

  }

  public @Override DynamicsPage<ApsSchedulingVersionDayExportQueryPageListInfoRes> queryPageList(ApsSchedulingVersionDayExportQueryPageListReq req) {
    return apsSchedulingVersionDayService.queryPageList(req);
  }

  public @Override void queryPageListExport(ApsSchedulingVersionDayExportQueryPageListReq req) {
    DynamicsPage<ApsSchedulingVersionDayExportQueryPageListInfoRes> page = queryPageList(req);
    List<ApsSchedulingVersionDayExportQueryPageListInfoRes> list = page.getDataList();
    // 类型转换，  更换枚举 等操作
    List<ApsSchedulingVersionDayExportQueryPageListInfoRes> listInfoRes = $.copyList(list, ApsSchedulingVersionDayExportQueryPageListInfoRes.class);
    PoiExcelUtil.export(ApsSchedulingVersionDayExportQueryPageListInfoRes.class, listInfoRes, "");
  }

  public @Override ApsSchedulingVersionDayImportRes importData(@RequestParam("file") MultipartFile file) {
    List<ApsSchedulingVersionDayImportReq> reqList = PoiExcelUtil.readData(file, new ApsSchedulingVersionDayImportListener(), ApsSchedulingVersionDayImportReq.class);
    // 类型转换，  更换枚举 等操作
    List<ApsSchedulingVersionDay> readList = $.copyList(reqList, ApsSchedulingVersionDay.class);
    boolean bool = apsSchedulingVersionDayService.saveBatch(readList);
    int c = bool ? readList.size() : 0;
    return new ApsSchedulingVersionDayImportRes().setCount(c);
  }

  public @Override ApsSchedulingVersionDayQueryByIdListRes queryByIdListRes(ApsSchedulingVersionDayQueryByIdListReq req) {
    MPJLambdaWrapper<ApsSchedulingVersionDay> q = new MPJLambdaWrapper<ApsSchedulingVersionDay>(ApsSchedulingVersionDay.class)
        .selectAll(ApsSchedulingVersionDay.class).in(ApsSchedulingVersionDay::getId, req.getIdList());
    List<ApsSchedulingVersionDay> list = this.apsSchedulingVersionDayService.list(q);
    List<ApsSchedulingVersionDayDto> dataList = $.copyList(list, ApsSchedulingVersionDayDto.class);
    this.apsSchedulingVersionDayService.setName(dataList);
    return new ApsSchedulingVersionDayQueryByIdListRes().setDataList(dataList);
  }
}
