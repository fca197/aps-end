package com.olivia.peanut.aps.api;

import com.olivia.peanut.aps.api.entity.apsSellerStore.*;
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
 * aps销售门店(ApsSellerStore)对外API
 *
 * @author makejava
 * @since 2024-11-15 14:58:57
 */
// @FeignClient(value = "",contextId = "apsSellerStore-api",url = "${ portal..center.endpoint:}")
public interface ApsSellerStoreApi {

  /**
   * 保存 aps销售门店
   */
  @PostMapping("/apsSellerStore/insert")
  ApsSellerStoreInsertRes insert(@RequestBody @Validated(InsertCheck.class) ApsSellerStoreInsertReq req);

  /**
   * 根据ID 删除 aps销售门店
   */
  @PostMapping("/apsSellerStore/deleteByIdList")
  ApsSellerStoreDeleteByIdListRes deleteByIdList(@RequestBody @Valid ApsSellerStoreDeleteByIdListReq req);

  /**
   * 查询 aps销售门店
   */
  @PostMapping("/apsSellerStore/queryList")
  ApsSellerStoreQueryListRes queryList(@RequestBody @Valid ApsSellerStoreQueryListReq req);

  /**
   * 根据ID 更新 aps销售门店
   */
  @PostMapping("/apsSellerStore/updateById")
  ApsSellerStoreUpdateByIdRes updateById(@RequestBody @Validated(UpdateCheck.class) ApsSellerStoreUpdateByIdReq req);

  /**
   * 分页查询 aps销售门店
   */
  @PostMapping("/apsSellerStore/queryPageList")
  DynamicsPage<ApsSellerStoreExportQueryPageListInfoRes> queryPageList(@RequestBody @Valid ApsSellerStoreExportQueryPageListReq req);

  /**
   * 导出 aps销售门店
   */
  @PostMapping("/apsSellerStore/exportQueryPageList")
  void queryPageListExport(@RequestBody @Valid ApsSellerStoreExportQueryPageListReq req);

  /**
   * 导入
   */
  @PostMapping("/apsSellerStore/importData")
  ApsSellerStoreImportRes importData(@RequestParam("file") MultipartFile file);


  /**
   * 根据ID 批量查询
   */
  @PostMapping("/apsSellerStore/queryByIdList")
  ApsSellerStoreQueryByIdListRes queryByIdListRes(@RequestBody @Valid ApsSellerStoreQueryByIdListReq req);


}
