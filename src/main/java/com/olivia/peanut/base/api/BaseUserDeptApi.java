package com.olivia.peanut.base.api;

import com.olivia.peanut.base.api.entity.baseUserDept.*;
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
 * 用户部门表(BaseUserDept)对外API
 *
 * @author peanut
 * @since 2024-07-31 14:36:01
 */
// @FeignClient(value = "",contextId = "baseUserDept-api",url = "${ portal..center.endpoint:}")
public interface BaseUserDeptApi {

  /**
   * 保存 用户部门表
   */
  @PostMapping("/baseUserDept/insert")
  BaseUserDeptInsertRes insert(@RequestBody @Validated(InsertCheck.class) BaseUserDeptInsertReq req);

  /**
   * 根据ID 删除 用户部门表
   */
  @PostMapping("/baseUserDept/deleteByIdList")
  BaseUserDeptDeleteByIdListRes deleteByIdList(@RequestBody @Valid BaseUserDeptDeleteByIdListReq req);

  /**
   * 查询 用户部门表
   */
  @PostMapping("/baseUserDept/queryList")
  BaseUserDeptQueryListRes queryList(@RequestBody @Valid BaseUserDeptQueryListReq req);

  /**
   * 根据ID 更新 用户部门表
   */
  @PostMapping("/baseUserDept/updateById")
  BaseUserDeptUpdateByIdRes updateById(@RequestBody @Validated(UpdateCheck.class) BaseUserDeptUpdateByIdReq req);

  /**
   * 分页查询 用户部门表
   */
  @PostMapping("/baseUserDept/queryPageList")
  DynamicsPage<BaseUserDeptExportQueryPageListInfoRes> queryPageList(@RequestBody @Valid BaseUserDeptExportQueryPageListReq req);

  /**
   * 导出 用户部门表
   */
  @PostMapping("/baseUserDept/exportQueryPageList")
  void queryPageListExport(@RequestBody @Valid BaseUserDeptExportQueryPageListReq req);

  /**
   * 导入
   */
  @PostMapping("/baseUserDept/importData")
  BaseUserDeptImportRes importData(@RequestParam("file") MultipartFile file);


  /**
   * 根据ID 批量查询
   */
  @PostMapping("/baseUserDept/queryByIdList")
  BaseUserDeptQueryByIdListRes queryByIdListRes(@RequestBody @Valid BaseUserDeptQueryByIdListReq req);


}
