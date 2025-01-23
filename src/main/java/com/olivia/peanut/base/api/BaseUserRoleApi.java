package com.olivia.peanut.base.api;

import com.olivia.peanut.base.api.entity.baseUserRole.*;
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
 * 用户角色表(BaseUserRole)对外API
 *
 * @author peanut
 * @since 2024-07-31 14:36:02
 */
// @FeignClient(value = "",contextId = "baseUserRole-api",url = "${ portal..center.endpoint:}")
public interface BaseUserRoleApi {

  /**
   * 保存 用户角色表
   */
  @PostMapping("/baseUserRole/insert")
  BaseUserRoleInsertRes insert(@RequestBody @Validated(InsertCheck.class) BaseUserRoleInsertReq req);

  /**
   * 根据ID 删除 用户角色表
   */
  @PostMapping("/baseUserRole/deleteByIdList")
  BaseUserRoleDeleteByIdListRes deleteByIdList(@RequestBody @Valid BaseUserRoleDeleteByIdListReq req);

  /**
   * 查询 用户角色表
   */
  @PostMapping("/baseUserRole/queryList")
  BaseUserRoleQueryListRes queryList(@RequestBody @Valid BaseUserRoleQueryListReq req);

  /**
   * 根据ID 更新 用户角色表
   */
  @PostMapping("/baseUserRole/updateById")
  BaseUserRoleUpdateByIdRes updateById(@RequestBody @Validated(UpdateCheck.class) BaseUserRoleUpdateByIdReq req);

  /**
   * 分页查询 用户角色表
   */
  @PostMapping("/baseUserRole/queryPageList")
  DynamicsPage<BaseUserRoleExportQueryPageListInfoRes> queryPageList(@RequestBody @Valid BaseUserRoleExportQueryPageListReq req);

  /**
   * 导出 用户角色表
   */
  @PostMapping("/baseUserRole/exportQueryPageList")
  void queryPageListExport(@RequestBody @Valid BaseUserRoleExportQueryPageListReq req);

  /**
   * 导入
   */
  @PostMapping("/baseUserRole/importData")
  BaseUserRoleImportRes importData(@RequestParam("file") MultipartFile file);


  /**
   * 根据ID 批量查询
   */
  @PostMapping("/baseUserRole/queryByIdList")
  BaseUserRoleQueryByIdListRes queryByIdListRes(@RequestBody @Valid BaseUserRoleQueryByIdListReq req);


}
