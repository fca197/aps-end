package com.olivia.peanut.base.api;

import com.olivia.peanut.base.api.entity.baseRole.*;
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
 * 角色表(BaseRole)对外API
 *
 * @author peanut
 * @since 2024-07-31 14:33:34
 */
// @FeignClient(value = "",contextId = "baseRole-api",url = "${ portal..center.endpoint:}")
public interface BaseRoleApi {

  /**
   * 保存 角色表
   */
  @PostMapping("/baseRole/insert")
  BaseRoleInsertRes insert(@RequestBody @Validated(InsertCheck.class) BaseRoleInsertReq req);

  /**
   * 根据ID 删除 角色表
   */
  @PostMapping("/baseRole/deleteByIdList")
  BaseRoleDeleteByIdListRes deleteByIdList(@RequestBody @Valid BaseRoleDeleteByIdListReq req);

  /**
   * 查询 角色表
   */
  @PostMapping("/baseRole/queryList")
  BaseRoleQueryListRes queryList(@RequestBody @Valid BaseRoleQueryListReq req);

  /**
   * 根据ID 更新 角色表
   */
  @PostMapping("/baseRole/updateById")
  BaseRoleUpdateByIdRes updateById(@RequestBody @Validated(UpdateCheck.class) BaseRoleUpdateByIdReq req);

  /**
   * 分页查询 角色表
   */
  @PostMapping("/baseRole/queryPageList")
  DynamicsPage<BaseRoleExportQueryPageListInfoRes> queryPageList(@RequestBody @Valid BaseRoleExportQueryPageListReq req);

  /**
   * 导出 角色表
   */
  @PostMapping("/baseRole/exportQueryPageList")
  void queryPageListExport(@RequestBody @Valid BaseRoleExportQueryPageListReq req);

  /**
   * 导入
   */
  @PostMapping("/baseRole/importData")
  BaseRoleImportRes importData(@RequestParam("file") MultipartFile file);


  /**
   * 根据ID 批量查询
   */
  @PostMapping("/baseRole/queryByIdList")
  BaseRoleQueryByIdListRes queryByIdListRes(@RequestBody @Valid BaseRoleQueryByIdListReq req);


}
