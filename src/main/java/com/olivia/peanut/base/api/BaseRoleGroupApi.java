package com.olivia.peanut.base.api;

import com.olivia.peanut.base.api.entity.baseRoleGroup.*;
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
 * 角色组表(BaseRoleGroup)对外API
 *
 * @author peanut
 * @since 2024-07-31 14:33:36
 */
// @FeignClient(value = "",contextId = "baseRoleGroup-api",url = "${ portal..center.endpoint:}")
public interface BaseRoleGroupApi {

  /**
   * 保存 角色组表
   */
  @PostMapping("/baseRoleGroup/insert")
  BaseRoleGroupInsertRes insert(@RequestBody @Validated(InsertCheck.class) BaseRoleGroupInsertReq req);

  /**
   * 根据ID 删除 角色组表
   */
  @PostMapping("/baseRoleGroup/deleteByIdList")
  BaseRoleGroupDeleteByIdListRes deleteByIdList(@RequestBody @Valid BaseRoleGroupDeleteByIdListReq req);

  /**
   * 查询 角色组表
   */
  @PostMapping("/baseRoleGroup/queryList")
  BaseRoleGroupQueryListRes queryList(@RequestBody @Valid BaseRoleGroupQueryListReq req);

  /**
   * 根据ID 更新 角色组表
   */
  @PostMapping("/baseRoleGroup/updateById")
  BaseRoleGroupUpdateByIdRes updateById(@RequestBody @Validated(UpdateCheck.class) BaseRoleGroupUpdateByIdReq req);

  /**
   * 分页查询 角色组表
   */
  @PostMapping("/baseRoleGroup/queryPageList")
  DynamicsPage<BaseRoleGroupExportQueryPageListInfoRes> queryPageList(@RequestBody @Valid BaseRoleGroupExportQueryPageListReq req);

  /**
   * 导出 角色组表
   */
  @PostMapping("/baseRoleGroup/exportQueryPageList")
  void queryPageListExport(@RequestBody @Valid BaseRoleGroupExportQueryPageListReq req);

  /**
   * 导入
   */
  @PostMapping("/baseRoleGroup/importData")
  BaseRoleGroupImportRes importData(@RequestParam("file") MultipartFile file);


  /**
   * 根据ID 批量查询
   */
  @PostMapping("/baseRoleGroup/queryByIdList")
  BaseRoleGroupQueryByIdListRes queryByIdListRes(@RequestBody @Valid BaseRoleGroupQueryByIdListReq req);


}
