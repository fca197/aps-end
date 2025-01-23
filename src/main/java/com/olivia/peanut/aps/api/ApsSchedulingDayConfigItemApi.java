package com.olivia.peanut.aps.api;

import com.olivia.peanut.aps.api.entity.apsSchedulingDayConfigItem.*;
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
 * 排程版本配置表(ApsSchedulingDayConfigItem)对外API
 *
 * @author peanut
 * @since 2024-07-19 19:19:51
 */
// @FeignClient(value = "",contextId = "apsSchedulingDayConfigItem-api",url = "${ portal..center.endpoint:}")
public interface ApsSchedulingDayConfigItemApi {

  /**
   * 保存 排程版本配置表
   */
  @PostMapping("/apsSchedulingDayConfigItem/insert")
  ApsSchedulingDayConfigItemInsertRes insert(@RequestBody @Validated(InsertCheck.class) ApsSchedulingDayConfigItemInsertReq req);

  /**
   * 根据ID 删除 排程版本配置表
   */
  @PostMapping("/apsSchedulingDayConfigItem/deleteByIdList")
  ApsSchedulingDayConfigItemDeleteByIdListRes deleteByIdList(@RequestBody @Valid ApsSchedulingDayConfigItemDeleteByIdListReq req);

  /**
   * 查询 排程版本配置表
   */
  @PostMapping("/apsSchedulingDayConfigItem/queryList")
  ApsSchedulingDayConfigItemQueryListRes queryList(@RequestBody @Valid ApsSchedulingDayConfigItemQueryListReq req);

  /**
   * 根据ID 更新 排程版本配置表
   */
  @PostMapping("/apsSchedulingDayConfigItem/updateById")
  ApsSchedulingDayConfigItemUpdateByIdRes updateById(@RequestBody @Validated(UpdateCheck.class) ApsSchedulingDayConfigItemUpdateByIdReq req);

  /**
   * 分页查询 排程版本配置表
   */
  @PostMapping("/apsSchedulingDayConfigItem/queryPageList")
  DynamicsPage<ApsSchedulingDayConfigItemExportQueryPageListInfoRes> queryPageList(@RequestBody @Valid ApsSchedulingDayConfigItemExportQueryPageListReq req);

  /**
   * 导出 排程版本配置表
   */
  @PostMapping("/apsSchedulingDayConfigItem/exportQueryPageList")
  void queryPageListExport(@RequestBody @Valid ApsSchedulingDayConfigItemExportQueryPageListReq req);

  /**
   * 导入
   */
  @PostMapping("/apsSchedulingDayConfigItem/importData")
  ApsSchedulingDayConfigItemImportRes importData(@RequestParam("file") MultipartFile file);


  /**
   * 根据ID 批量查询
   */
  @PostMapping("/apsSchedulingDayConfigItem/queryByIdList")
  ApsSchedulingDayConfigItemQueryByIdListRes queryByIdListRes(@RequestBody @Valid ApsSchedulingDayConfigItemQueryByIdListReq req);


}
