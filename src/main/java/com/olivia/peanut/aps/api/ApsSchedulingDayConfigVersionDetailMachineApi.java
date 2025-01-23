package com.olivia.peanut.aps.api;

import com.olivia.peanut.aps.api.entity.apsSchedulingDayConfigVersionDetailMachine.*;
import com.olivia.sdk.ann.InsertCheck;
import com.olivia.sdk.ann.UpdateCheck;
import com.olivia.sdk.utils.DynamicsPage;
import jakarta.validation.Valid;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;


/**
 * 排程版本详情_机器(ApsSchedulingDayConfigVersionDetailMachine)对外API
 *
 * @author makejava
 * @since 2024-10-27 00:12:53
 */
// @FeignClient(value = "",contextId = "apsSchedulingDayConfigVersionDetailMachine-api",url = "${ portal..center.endpoint:}")
public interface ApsSchedulingDayConfigVersionDetailMachineApi {

  /**
   * 保存 排程版本详情_机器
   */
  @PostMapping("/apsSchedulingDayConfigVersionDetailMachine/insert")
  ApsSchedulingDayConfigVersionDetailMachineInsertRes insert(@RequestBody @Validated(InsertCheck.class) ApsSchedulingDayConfigVersionDetailMachineInsertReq req);

  /**
   * 根据ID 删除 排程版本详情_机器
   */
  @PostMapping("/apsSchedulingDayConfigVersionDetailMachine/deleteByIdList")
  ApsSchedulingDayConfigVersionDetailMachineDeleteByIdListRes deleteByIdList(@RequestBody @Valid ApsSchedulingDayConfigVersionDetailMachineDeleteByIdListReq req);

  /**
   * 查询 排程版本详情_机器
   */
  @PostMapping("/apsSchedulingDayConfigVersionDetailMachine/queryList")
  ApsSchedulingDayConfigVersionDetailMachineQueryListRes queryList(@RequestBody @Valid ApsSchedulingDayConfigVersionDetailMachineQueryListReq req);

  /**
   * 根据ID 更新 排程版本详情_机器
   */
  @PostMapping("/apsSchedulingDayConfigVersionDetailMachine/updateById")
  ApsSchedulingDayConfigVersionDetailMachineUpdateByIdRes updateById(@RequestBody @Validated(UpdateCheck.class) ApsSchedulingDayConfigVersionDetailMachineUpdateByIdReq req);

  /**
   * 分页查询 排程版本详情_机器
   */
  @PostMapping("/apsSchedulingDayConfigVersionDetailMachine/queryPageList")
  DynamicsPage<ApsSchedulingDayConfigVersionDetailMachineExportQueryPageListInfoRes> queryPageList(@RequestBody @Valid ApsSchedulingDayConfigVersionDetailMachineExportQueryPageListReq req);

  /**
   * 导出 排程版本详情_机器
   */
  @PostMapping("/apsSchedulingDayConfigVersionDetailMachine/exportQueryPageList")
  void queryPageListExport(@RequestBody @Valid ApsSchedulingDayConfigVersionDetailMachineExportQueryPageListReq req);

  /**
   * 导入
   */
  @PostMapping("/apsSchedulingDayConfigVersionDetailMachine/importData")
  ApsSchedulingDayConfigVersionDetailMachineImportRes importData(@RequestParam("file") MultipartFile file);


  /**
   * 根据ID 批量查询
   */
  @PostMapping("/apsSchedulingDayConfigVersionDetailMachine/queryByIdList")
  ApsSchedulingDayConfigVersionDetailMachineQueryByIdListRes queryByIdListRes(@RequestBody @Valid ApsSchedulingDayConfigVersionDetailMachineQueryByIdListReq req);


}
