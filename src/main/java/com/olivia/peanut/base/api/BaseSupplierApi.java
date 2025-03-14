package com.olivia.peanut.base.api;


import com.olivia.peanut.base.api.entity.baseSupplier.*;
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
 * (BaseSupplier)对外API
 *
 * @author peanut
 * @since 2024-03-28 15:35:37
 */
// @FeignClient(value = "",contextId = "baseSupplier-api",url = "${ portal..center.endpoint:}")
public interface BaseSupplierApi {

  /**
   * 保存
   */
  @PostMapping("/baseSupplier/insert")
  BaseSupplierInsertRes insert(@RequestBody @Validated(InsertCheck.class) BaseSupplierInsertReq req);

  /**
   * 根据ID 删除
   */
  @PostMapping("/baseSupplier/deleteByIdList")
  BaseSupplierDeleteByIdListRes deleteByIdList(@RequestBody @Valid BaseSupplierDeleteByIdListReq req);

  /**
   * 查询
   */
  @PostMapping("/baseSupplier/queryList")
  BaseSupplierQueryListRes queryList(@RequestBody @Valid BaseSupplierQueryListReq req);

  /**
   * 根据ID 更新
   */
  @PostMapping("/baseSupplier/updateById")
  BaseSupplierUpdateByIdRes updateById(@RequestBody @Validated(UpdateCheck.class) BaseSupplierUpdateByIdReq req);

  /**
   * 分页查询
   */
  @PostMapping("/baseSupplier/queryPageList")
  DynamicsPage<BaseSupplierExportQueryPageListInfoRes> queryPageList(@RequestBody @Valid BaseSupplierExportQueryPageListReq req);

  /**
   * 导出
   */
  @PostMapping("/baseSupplier/exportQueryPageList")
  void queryPageListExport(@RequestBody @Valid BaseSupplierExportQueryPageListReq req);

  /**
   * 导入
   */
  @PostMapping("/baseSupplier/importData")
  BaseSupplierImportRes importData(@RequestParam("file") MultipartFile file);


  /**
   * 根据ID 批量查询
   */
  @PostMapping("/baseSupplier/queryByIdList")
  BaseSupplierQueryByIdListRes queryByIdListRes(@RequestBody @Valid BaseSupplierQueryByIdListReq req);


}
