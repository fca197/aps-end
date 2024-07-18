package com.olivia.peanut.aps.api;

import com.olivia.peanut.aps.api.entity.apsLogisticsPath.*;
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
 * 物流路径表(ApsLogisticsPath)对外API
 *
 * @author peanut
 * @since 2024-07-18 13:27:35
 */
// @FeignClient(value = "",contextId = "apsLogisticsPath-api",url = "${ portal..center.endpoint:}")
public interface ApsLogisticsPathApi {

  /**
   * 保存 物流路径表
   */
  @PostMapping("/apsLogisticsPath/insert")
  ApsLogisticsPathInsertRes insert(@RequestBody @Validated(InsertCheck.class) ApsLogisticsPathInsertReq req);

  /**
   * 根据ID 删除 物流路径表
   */
  @PostMapping("/apsLogisticsPath/deleteByIdList")
  ApsLogisticsPathDeleteByIdListRes deleteByIdList(@RequestBody @Valid ApsLogisticsPathDeleteByIdListReq req);

  /**
   * 查询 物流路径表
   */
  @PostMapping("/apsLogisticsPath/queryList")
  ApsLogisticsPathQueryListRes queryList(@RequestBody @Valid ApsLogisticsPathQueryListReq req);

  /**
   * 根据ID 更新 物流路径表
   */
  @PostMapping("/apsLogisticsPath/updateById")
  ApsLogisticsPathUpdateByIdRes updateById(@RequestBody @Validated(UpdateCheck.class) ApsLogisticsPathUpdateByIdReq req);

  /**
   * 分页查询 物流路径表
   */
  @PostMapping("/apsLogisticsPath/queryPageList")
  DynamicsPage<ApsLogisticsPathExportQueryPageListInfoRes> queryPageList(@RequestBody @Valid ApsLogisticsPathExportQueryPageListReq req);

  /**
   * 导出 物流路径表
   */
  @PostMapping("/apsLogisticsPath/exportQueryPageList")
  void queryPageListExport(@RequestBody @Valid ApsLogisticsPathExportQueryPageListReq req);

  /**
   * 导入
   */
  @PostMapping("/apsLogisticsPath/importData")
  ApsLogisticsPathImportRes importData(@RequestParam("file") MultipartFile file);


  /**
   * 根据ID 批量查询
   */
  @PostMapping("/apsLogisticsPath/queryByIdList")
  ApsLogisticsPathQueryByIdListRes queryByIdListRes(@RequestBody @Valid ApsLogisticsPathQueryByIdListReq req);


}
