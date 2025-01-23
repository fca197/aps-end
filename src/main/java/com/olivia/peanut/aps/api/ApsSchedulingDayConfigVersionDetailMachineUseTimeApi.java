package com.olivia.peanut.aps.api;

import com.olivia.peanut.aps.api.entity.apsSchedulingDayConfigVersionDetailMachineUseTime.*;
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
 * 排程结果机器使用率(ApsSchedulingDayConfigVersionDetailMachineUseTime)对外API
 *
 * @author makejava
 * @since 2024-11-11 15:21:47
 */
// @FeignClient(value = "",contextId = "apsSchedulingDayConfigVersionDetailMachineUseTime-api",url = "${ portal..center.endpoint:}")
public interface ApsSchedulingDayConfigVersionDetailMachineUseTimeApi {

  /**
   * 保存 排程结果机器使用率
   */
  @PostMapping("/apsSchedulingDayConfigVersionDetailMachineUseTime/insert")
  ApsSchedulingDayConfigVersionDetailMachineUseTimeInsertRes insert(@RequestBody @Validated(InsertCheck.class) ApsSchedulingDayConfigVersionDetailMachineUseTimeInsertReq req);

  /**
   * 根据ID 删除 排程结果机器使用率
   */
  @PostMapping("/apsSchedulingDayConfigVersionDetailMachineUseTime/deleteByIdList")
  ApsSchedulingDayConfigVersionDetailMachineUseTimeDeleteByIdListRes deleteByIdList(@RequestBody @Valid ApsSchedulingDayConfigVersionDetailMachineUseTimeDeleteByIdListReq req);

  /**
   * 查询 排程结果机器使用率
   */
  @PostMapping("/apsSchedulingDayConfigVersionDetailMachineUseTime/queryList")
  ApsSchedulingDayConfigVersionDetailMachineUseTimeQueryListRes queryList(@RequestBody @Valid ApsSchedulingDayConfigVersionDetailMachineUseTimeQueryListReq req);

  /**
   * 根据ID 更新 排程结果机器使用率
   */
  @PostMapping("/apsSchedulingDayConfigVersionDetailMachineUseTime/updateById")
  ApsSchedulingDayConfigVersionDetailMachineUseTimeUpdateByIdRes updateById(@RequestBody @Validated(UpdateCheck.class) ApsSchedulingDayConfigVersionDetailMachineUseTimeUpdateByIdReq req);

  /**
   * 分页查询 排程结果机器使用率
   */
  @PostMapping("/apsSchedulingDayConfigVersionDetailMachineUseTime/queryPageList")
  DynamicsPage<ApsSchedulingDayConfigVersionDetailMachineUseTimeExportQueryPageListInfoRes> queryPageList(@RequestBody @Valid ApsSchedulingDayConfigVersionDetailMachineUseTimeExportQueryPageListReq req);

  /**
   * 导出 排程结果机器使用率
   */
  @PostMapping("/apsSchedulingDayConfigVersionDetailMachineUseTime/exportQueryPageList")
  void queryPageListExport(@RequestBody @Valid ApsSchedulingDayConfigVersionDetailMachineUseTimeExportQueryPageListReq req);

  /**
   * 导入
   */
  @PostMapping("/apsSchedulingDayConfigVersionDetailMachineUseTime/importData")
  ApsSchedulingDayConfigVersionDetailMachineUseTimeImportRes importData(@RequestParam("file") MultipartFile file);


  /**
   * 根据ID 批量查询
   */
  @PostMapping("/apsSchedulingDayConfigVersionDetailMachineUseTime/queryByIdList")
  ApsSchedulingDayConfigVersionDetailMachineUseTimeQueryByIdListRes queryByIdListRes(@RequestBody @Valid ApsSchedulingDayConfigVersionDetailMachineUseTimeQueryByIdListReq req);


}
