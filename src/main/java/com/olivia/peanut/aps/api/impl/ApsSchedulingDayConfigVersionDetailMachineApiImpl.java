package com.olivia.peanut.aps.api.impl;

import java.time.LocalDateTime;

import com.olivia.peanut.aps.model.ApsSchedulingDayConfigVersionDetailMachine;
import com.olivia.sdk.utils.$;
import com.olivia.sdk.utils.DynamicsPage;
import com.olivia.sdk.utils.PoiExcelUtil;

import java.util.stream.Collectors;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;

import org.apache.commons.lang3.StringUtils;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import com.olivia.peanut.aps.api.entity.apsSchedulingDayConfigVersionDetailMachine.*;
import com.olivia.peanut.aps.service.ApsSchedulingDayConfigVersionDetailMachineService;
import com.olivia.peanut.aps.model.*;
import com.baomidou.mybatisplus.core.conditions.query.*;
import com.github.yulichang.wrapper.MPJLambdaWrapper;
import org.springframework.web.bind.annotation.*;
import com.olivia.peanut.aps.api.ApsSchedulingDayConfigVersionDetailMachineApi;

import com.olivia.peanut.aps.api.impl.listener.*;
import org.springframework.web.multipart.MultipartFile;

/**
 * 排程版本详情_机器(ApsSchedulingDayConfigVersionDetailMachine)表服务实现类
 *
 * @author makejava
 * @since 2024-10-27 00:12:53
 */
@RestController
public class ApsSchedulingDayConfigVersionDetailMachineApiImpl implements ApsSchedulingDayConfigVersionDetailMachineApi {

  private @Autowired ApsSchedulingDayConfigVersionDetailMachineService apsSchedulingDayConfigVersionDetailMachineService;

  /****
   * insert
   *
   */
  public @Override ApsSchedulingDayConfigVersionDetailMachineInsertRes insert(ApsSchedulingDayConfigVersionDetailMachineInsertReq req) {
    this.apsSchedulingDayConfigVersionDetailMachineService.save($.copy(req, ApsSchedulingDayConfigVersionDetailMachine.class));
    return new ApsSchedulingDayConfigVersionDetailMachineInsertRes().setCount(1);
  }

  /****
   * deleteByIds
   *
   */
  public @Override ApsSchedulingDayConfigVersionDetailMachineDeleteByIdListRes deleteByIdList(ApsSchedulingDayConfigVersionDetailMachineDeleteByIdListReq req) {
    apsSchedulingDayConfigVersionDetailMachineService.removeByIds(req.getIdList());
    return new ApsSchedulingDayConfigVersionDetailMachineDeleteByIdListRes();
  }

  /****
   * queryList
   *
   */
  public @Override ApsSchedulingDayConfigVersionDetailMachineQueryListRes queryList(ApsSchedulingDayConfigVersionDetailMachineQueryListReq req) {
    return apsSchedulingDayConfigVersionDetailMachineService.queryList(req);
  }

  /****
   * updateById
   *
   */
  public @Override ApsSchedulingDayConfigVersionDetailMachineUpdateByIdRes updateById(ApsSchedulingDayConfigVersionDetailMachineUpdateByIdReq req) {
    apsSchedulingDayConfigVersionDetailMachineService.updateById($.copy(req, ApsSchedulingDayConfigVersionDetailMachine.class));
    return new ApsSchedulingDayConfigVersionDetailMachineUpdateByIdRes();

  }

  public @Override DynamicsPage<ApsSchedulingDayConfigVersionDetailMachineExportQueryPageListInfoRes> queryPageList(ApsSchedulingDayConfigVersionDetailMachineExportQueryPageListReq req) {
    return apsSchedulingDayConfigVersionDetailMachineService.queryPageList(req);
  }

  public @Override void queryPageListExport(ApsSchedulingDayConfigVersionDetailMachineExportQueryPageListReq req) {
    DynamicsPage<ApsSchedulingDayConfigVersionDetailMachineExportQueryPageListInfoRes> page = queryPageList(req);
    List<ApsSchedulingDayConfigVersionDetailMachineExportQueryPageListInfoRes> list = page.getDataList();
    // 类型转换，  更换枚举 等操作
    List<ApsSchedulingDayConfigVersionDetailMachineExportQueryPageListInfoRes> listInfoRes = $.copyList(list, ApsSchedulingDayConfigVersionDetailMachineExportQueryPageListInfoRes.class);
    PoiExcelUtil.export(ApsSchedulingDayConfigVersionDetailMachineExportQueryPageListInfoRes.class, listInfoRes, "排程版本详情_机器");
  }

  public @Override ApsSchedulingDayConfigVersionDetailMachineImportRes importData(@RequestParam("file") MultipartFile file) {
    List<ApsSchedulingDayConfigVersionDetailMachineImportReq> reqList = PoiExcelUtil.readData(file, new ApsSchedulingDayConfigVersionDetailMachineImportListener(), ApsSchedulingDayConfigVersionDetailMachineImportReq.class);
    // 类型转换，  更换枚举 等操作
    List<ApsSchedulingDayConfigVersionDetailMachine> readList = $.copyList(reqList, ApsSchedulingDayConfigVersionDetailMachine.class);
    boolean bool = apsSchedulingDayConfigVersionDetailMachineService.saveBatch(readList);
    int c = bool ? readList.size() : 0;
    return new ApsSchedulingDayConfigVersionDetailMachineImportRes().setCount(c);
  }

  public @Override ApsSchedulingDayConfigVersionDetailMachineQueryByIdListRes queryByIdListRes(ApsSchedulingDayConfigVersionDetailMachineQueryByIdListReq req) {
    MPJLambdaWrapper<ApsSchedulingDayConfigVersionDetailMachine> q = new MPJLambdaWrapper<ApsSchedulingDayConfigVersionDetailMachine>(ApsSchedulingDayConfigVersionDetailMachine.class)
        .selectAll(ApsSchedulingDayConfigVersionDetailMachine.class).in(ApsSchedulingDayConfigVersionDetailMachine::getId, req.getIdList());
    List<ApsSchedulingDayConfigVersionDetailMachine> list = this.apsSchedulingDayConfigVersionDetailMachineService.list(q);
    List<ApsSchedulingDayConfigVersionDetailMachineDto> dataList = $.copyList(list, ApsSchedulingDayConfigVersionDetailMachineDto.class);
    this.apsSchedulingDayConfigVersionDetailMachineService.setName(dataList);
    return new ApsSchedulingDayConfigVersionDetailMachineQueryByIdListRes().setDataList(dataList);
  }
}
