package com.olivia.peanut.portal.api;


import com.olivia.peanut.base.api.entity.checkReport.*;

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
 * 报表信息(CheckReport)对外API
 *
 * @author makejava
 * @since 2024-03-14 13:31:34
 */
// @FeignClient(value = "",contextId = "checkReport-api",url = "${ portal..center.endpoint:}")
public interface CheckReportApi {

  /**
   * 保存 报表信息
   */
  @PostMapping("/checkReport/insert")
  CheckReportInsertRes insert(@RequestBody @Validated(InsertCheck.class) CheckReportInsertReq req);

  /**
   * 根据ID 删除 报表信息
   */
  @PostMapping("/checkReport/deleteByIdList")
  CheckReportDeleteByIdListRes deleteByIdList(@RequestBody @Valid CheckReportDeleteByIdListReq req);

  /**
   * 查询 报表信息
   */
  @PostMapping("/checkReport/queryList")
  CheckReportQueryListRes queryList(@RequestBody @Valid CheckReportQueryListReq req);

  /**
   * 根据ID 更新 报表信息
   */
  @PostMapping("/checkReport/updateById")
  CheckReportUpdateByIdRes updateById(@RequestBody @Validated(UpdateCheck.class) CheckReportUpdateByIdReq req);

  /**
   * 分页查询 报表信息
   */
  @PostMapping("/checkReport/queryPageList")
  DynamicsPage<CheckReportExportQueryPageListInfoRes> queryPageList(@RequestBody @Valid CheckReportExportQueryPageListReq req);

  /**
   * 导出 报表信息
   */
  @PostMapping("/checkReport/exportQueryPageList")
  void queryPageListExport(@RequestBody @Valid CheckReportExportQueryPageListReq req);

  /**
   * 导入
   */
  @PostMapping("/checkReport/importData")
  CheckReportImportRes importData(@RequestParam("file") MultipartFile file);


  /**
   * 根据ID 批量查询
   */
  @PostMapping("/checkReport/queryByIdList")
  CheckReportQueryByIdListRes queryByIdListRes(@RequestBody @Valid CheckReportQueryByIdListReq req);

  @PostMapping("/checkReport/downLoad")
  void downLoad(@RequestBody @Valid CheckReportDownLoadReq req);

}
