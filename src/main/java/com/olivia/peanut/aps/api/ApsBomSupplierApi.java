package com.olivia.peanut.aps.api;

import com.olivia.peanut.aps.api.entity.apsBomSupplier.*;
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
 * 供应商表(ApsBomSupplier)对外API
 *
 * @author makejava
 * @since 2024-12-15 14:39:43
 */
// @FeignClient(value = "",contextId = "apsBomSupplier-api",url = "${ portal..center.endpoint:}")
public interface ApsBomSupplierApi {

  /**
   * 保存 供应商表
   */
  @PostMapping("/apsBomSupplier/insert")
  ApsBomSupplierInsertRes insert(@RequestBody @Validated(InsertCheck.class) ApsBomSupplierInsertReq req);

  /**
   * 根据ID 删除 供应商表
   */
  @PostMapping("/apsBomSupplier/deleteByIdList")
  ApsBomSupplierDeleteByIdListRes deleteByIdList(@RequestBody @Valid ApsBomSupplierDeleteByIdListReq req);

  /**
   * 查询 供应商表
   */
  @PostMapping("/apsBomSupplier/queryList")
  ApsBomSupplierQueryListRes queryList(@RequestBody @Valid ApsBomSupplierQueryListReq req);

  /**
   * 根据ID 更新 供应商表
   */
  @PostMapping("/apsBomSupplier/updateById")
  ApsBomSupplierUpdateByIdRes updateById(@RequestBody @Validated(UpdateCheck.class) ApsBomSupplierUpdateByIdReq req);

  /**
   * 分页查询 供应商表
   */
  @PostMapping("/apsBomSupplier/queryPageList")
  DynamicsPage<ApsBomSupplierExportQueryPageListInfoRes> queryPageList(@RequestBody @Valid ApsBomSupplierExportQueryPageListReq req);

  /**
   * 导出 供应商表
   */
  @PostMapping("/apsBomSupplier/exportQueryPageList")
  void queryPageListExport(@RequestBody @Valid ApsBomSupplierExportQueryPageListReq req);

  /**
   * 导入
   */
  @PostMapping("/apsBomSupplier/importData")
  ApsBomSupplierImportRes importData(@RequestParam("file") MultipartFile file);


  /**
   * 根据ID 批量查询
   */
  @PostMapping("/apsBomSupplier/queryByIdList")
  ApsBomSupplierQueryByIdListRes queryByIdListRes(@RequestBody @Valid ApsBomSupplierQueryByIdListReq req);


}
