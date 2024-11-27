package com.olivia.peanut.base.api;

import com.olivia.peanut.base.api.entity.baseRoleGroupResource.*;
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
 * 角色组资源表(BaseRoleGroupResource)对外API
 *
 * @author peanut
 * @since 2024-08-09 15:42:34
 */
// @FeignClient(value = "",contextId = "baseRoleGroupResource-api",url = "${ portal..center.endpoint:}")
public interface BaseRoleGroupResourceApi {

  /**
   * 保存 角色组资源表
   */
  @PostMapping("/baseRoleGroupResource/insertList")
  BaseRoleGroupResourceInsertRes insert(@RequestBody @Validated(InsertCheck.class) BaseRoleGroupResourceInsertReq req);

  /**
   * 根据ID 删除 角色组资源表
   */
  @PostMapping("/baseRoleGroupResource/deleteByIdList")
  BaseRoleGroupResourceDeleteByIdListRes deleteByIdList(@RequestBody @Valid BaseRoleGroupResourceDeleteByIdListReq req);

  /**
   * 查询 角色组资源表
   */
  @PostMapping("/baseRoleGroupResource/queryList")
  BaseRoleGroupResourceQueryListRes queryList(@RequestBody @Valid BaseRoleGroupResourceQueryListReq req);

  /**
   * 根据ID 更新 角色组资源表
   */
  @PostMapping("/baseRoleGroupResource/updateById")
  BaseRoleGroupResourceUpdateByIdRes updateById(@RequestBody @Validated(UpdateCheck.class) BaseRoleGroupResourceUpdateByIdReq req);

  /**
   * 分页查询 角色组资源表
   */
  @PostMapping("/baseRoleGroupResource/queryPageList")
  DynamicsPage<BaseRoleGroupResourceExportQueryPageListInfoRes> queryPageList(@RequestBody @Valid BaseRoleGroupResourceExportQueryPageListReq req);

  /**
   * 导出 角色组资源表
   */
  @PostMapping("/baseRoleGroupResource/exportQueryPageList")
  void queryPageListExport(@RequestBody @Valid BaseRoleGroupResourceExportQueryPageListReq req);

  /**
   * 导入
   */
  @PostMapping("/baseRoleGroupResource/importData")
  BaseRoleGroupResourceImportRes importData(@RequestParam("file") MultipartFile file);


  /**
   * 根据ID 批量查询
   */
  @PostMapping("/baseRoleGroupResource/queryByIdList")
  BaseRoleGroupResourceQueryByIdListRes queryByIdListRes(@RequestBody @Valid BaseRoleGroupResourceQueryByIdListReq req);


}
