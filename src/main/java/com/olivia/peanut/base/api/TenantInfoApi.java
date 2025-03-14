package com.olivia.peanut.base.api;


import com.olivia.peanut.base.api.entity.tenantInfo.*;
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
 * 租户信息(TenantInfo)对外API
 *
 * @author makejava
 * @since 2024-03-11 10:55:21
 */
// @FeignClient(value = "",contextId = "tenantInfo-api",url = "${ portal..center.endpoint:}")
public interface TenantInfoApi {

  /**
   * 保存 租户信息
   */
  @PostMapping("/tenantInfo/insert")
  TenantInfoInsertRes insert(@RequestBody @Validated(InsertCheck.class) TenantInfoInsertReq req);

  /**
   * 根据ID 删除 租户信息
   */
  @PostMapping("/tenantInfo/deleteByIdList")
  TenantInfoDeleteByIdListRes deleteByIdList(@RequestBody @Valid TenantInfoDeleteByIdListReq req);

  /**
   * 查询 租户信息
   */
  @PostMapping("/tenantInfo/queryList")
  TenantInfoQueryListRes queryList(@RequestBody @Valid TenantInfoQueryListReq req);

  /**
   * 根据ID 更新 租户信息
   */
  @PostMapping("/tenantInfo/updateById")
  TenantInfoUpdateByIdRes updateById(@RequestBody @Validated(UpdateCheck.class) TenantInfoUpdateByIdReq req);

  /**
   * 分页查询 租户信息
   */
  @PostMapping("/tenantInfo/queryPageList")
  DynamicsPage<TenantInfoExportQueryPageListInfoRes> queryPageList(@RequestBody @Valid TenantInfoExportQueryPageListReq req);

  /**
   * 导出 租户信息
   */
  @PostMapping("/tenantInfo/exportQueryPageList")
  void queryPageListExport(@RequestBody @Valid TenantInfoExportQueryPageListReq req);

  /**
   * 导入
   */
  @PostMapping("/tenantInfo/importData")
  TenantInfoImportRes importData(@RequestParam("file") MultipartFile file);


  /**
   * 根据ID 批量查询
   */
  @PostMapping("/tenantInfo/queryByIdList")
  TenantInfoQueryByIdListRes queryByIdListRes(@RequestBody @Valid TenantInfoQueryByIdListReq req);


}
