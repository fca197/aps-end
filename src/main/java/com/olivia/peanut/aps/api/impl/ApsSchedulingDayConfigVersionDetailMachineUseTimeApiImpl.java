package com.olivia.peanut.aps.api.impl;

import com.github.yulichang.wrapper.MPJLambdaWrapper;
import com.olivia.peanut.aps.api.ApsSchedulingDayConfigVersionDetailMachineUseTimeApi;
import com.olivia.peanut.aps.api.entity.apsSchedulingDayConfigVersionDetailMachineUseTime.*;
import com.olivia.peanut.aps.api.impl.listener.ApsSchedulingDayConfigVersionDetailMachineUseTimeImportListener;
import com.olivia.peanut.aps.model.ApsSchedulingDayConfigVersionDetailMachineUseTime;
import com.olivia.peanut.aps.service.ApsSchedulingDayConfigVersionDetailMachineUseTimeService;
import com.olivia.sdk.utils.$;
import com.olivia.sdk.utils.DynamicsPage;
import com.olivia.sdk.utils.PoiExcelUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * 排程结果机器使用率(ApsSchedulingDayConfigVersionDetailMachineUseTime)表服务实现类
 *
 * @author makejava
 * @since 2024-11-11 15:21:48
 */
@RestController
public class ApsSchedulingDayConfigVersionDetailMachineUseTimeApiImpl implements ApsSchedulingDayConfigVersionDetailMachineUseTimeApi {

  private @Autowired ApsSchedulingDayConfigVersionDetailMachineUseTimeService apsSchedulingDayConfigVersionDetailMachineUseTimeService;

  /****
   * insert
   *
   */
  public @Override ApsSchedulingDayConfigVersionDetailMachineUseTimeInsertRes insert(ApsSchedulingDayConfigVersionDetailMachineUseTimeInsertReq req) {
    this.apsSchedulingDayConfigVersionDetailMachineUseTimeService.save($.copy(req, ApsSchedulingDayConfigVersionDetailMachineUseTime.class));
    return new ApsSchedulingDayConfigVersionDetailMachineUseTimeInsertRes().setCount(1);
  }

  /****
   * deleteByIds
   *
   */
  public @Override ApsSchedulingDayConfigVersionDetailMachineUseTimeDeleteByIdListRes deleteByIdList(ApsSchedulingDayConfigVersionDetailMachineUseTimeDeleteByIdListReq req) {
    apsSchedulingDayConfigVersionDetailMachineUseTimeService.removeByIds(req.getIdList());
    return new ApsSchedulingDayConfigVersionDetailMachineUseTimeDeleteByIdListRes();
  }

  /****
   * queryList
   *
   */
  public @Override ApsSchedulingDayConfigVersionDetailMachineUseTimeQueryListRes queryList(ApsSchedulingDayConfigVersionDetailMachineUseTimeQueryListReq req) {
    return apsSchedulingDayConfigVersionDetailMachineUseTimeService.queryList(req);
  }

  /****
   * updateById
   *
   */
  public @Override ApsSchedulingDayConfigVersionDetailMachineUseTimeUpdateByIdRes updateById(ApsSchedulingDayConfigVersionDetailMachineUseTimeUpdateByIdReq req) {
    apsSchedulingDayConfigVersionDetailMachineUseTimeService.updateById($.copy(req, ApsSchedulingDayConfigVersionDetailMachineUseTime.class));
    return new ApsSchedulingDayConfigVersionDetailMachineUseTimeUpdateByIdRes();

  }

  public @Override DynamicsPage<ApsSchedulingDayConfigVersionDetailMachineUseTimeExportQueryPageListInfoRes> queryPageList(ApsSchedulingDayConfigVersionDetailMachineUseTimeExportQueryPageListReq req) {
    return apsSchedulingDayConfigVersionDetailMachineUseTimeService.queryPageList(req);
  }

  public @Override void queryPageListExport(ApsSchedulingDayConfigVersionDetailMachineUseTimeExportQueryPageListReq req) {
    DynamicsPage<ApsSchedulingDayConfigVersionDetailMachineUseTimeExportQueryPageListInfoRes> page = queryPageList(req);
    List<ApsSchedulingDayConfigVersionDetailMachineUseTimeExportQueryPageListInfoRes> list = page.getDataList();
    // 类型转换，  更换枚举 等操作
    List<ApsSchedulingDayConfigVersionDetailMachineUseTimeExportQueryPageListInfoRes> listInfoRes = $.copyList(list, ApsSchedulingDayConfigVersionDetailMachineUseTimeExportQueryPageListInfoRes.class);
    PoiExcelUtil.export(ApsSchedulingDayConfigVersionDetailMachineUseTimeExportQueryPageListInfoRes.class, listInfoRes, "排程结果机器使用率");
  }

  public @Override ApsSchedulingDayConfigVersionDetailMachineUseTimeImportRes importData(@RequestParam("file") MultipartFile file) {
    List<ApsSchedulingDayConfigVersionDetailMachineUseTimeImportReq> reqList = PoiExcelUtil.readData(file, new ApsSchedulingDayConfigVersionDetailMachineUseTimeImportListener(), ApsSchedulingDayConfigVersionDetailMachineUseTimeImportReq.class);
    // 类型转换，  更换枚举 等操作
    List<ApsSchedulingDayConfigVersionDetailMachineUseTime> readList = $.copyList(reqList, ApsSchedulingDayConfigVersionDetailMachineUseTime.class);
    boolean bool = apsSchedulingDayConfigVersionDetailMachineUseTimeService.saveBatch(readList);
    int c = bool ? readList.size() : 0;
    return new ApsSchedulingDayConfigVersionDetailMachineUseTimeImportRes().setCount(c);
  }

  public @Override ApsSchedulingDayConfigVersionDetailMachineUseTimeQueryByIdListRes queryByIdListRes(ApsSchedulingDayConfigVersionDetailMachineUseTimeQueryByIdListReq req) {
    MPJLambdaWrapper<ApsSchedulingDayConfigVersionDetailMachineUseTime> q = new MPJLambdaWrapper<ApsSchedulingDayConfigVersionDetailMachineUseTime>(ApsSchedulingDayConfigVersionDetailMachineUseTime.class)
        .selectAll(ApsSchedulingDayConfigVersionDetailMachineUseTime.class).in(ApsSchedulingDayConfigVersionDetailMachineUseTime::getId, req.getIdList());
    List<ApsSchedulingDayConfigVersionDetailMachineUseTime> list = this.apsSchedulingDayConfigVersionDetailMachineUseTimeService.list(q);
    List<ApsSchedulingDayConfigVersionDetailMachineUseTimeDto> dataList = $.copyList(list, ApsSchedulingDayConfigVersionDetailMachineUseTimeDto.class);
    this.apsSchedulingDayConfigVersionDetailMachineUseTimeService.setName(dataList);
    return new ApsSchedulingDayConfigVersionDetailMachineUseTimeQueryByIdListRes().setDataList(dataList);
  }
}
