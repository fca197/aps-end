package com.olivia.peanut.portal.api;


import com.olivia.peanut.portal.api.entity.brand.*;
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
 * 品牌信息(Brand)对外API
 *
 * @author makejava
 * @since 2024-03-11 10:44:02
 */
// @FeignClient(value = "",contextId = "brand-api",url = "${ portal..center.endpoint:}")
public interface BrandApi {

  /**
   * 保存 品牌信息
   */
  @PostMapping("/brand/insert")
  BrandInsertRes insert(@RequestBody @Validated(InsertCheck.class) BrandInsertReq req);

  /**
   * 根据ID 删除 品牌信息
   */
  @PostMapping("/brand/deleteByIdList")
  BrandDeleteByIdListRes deleteByIdList(@RequestBody @Valid BrandDeleteByIdListReq req);

  /**
   * 查询 品牌信息
   */
  @PostMapping("/brand/queryList")
  BrandQueryListRes queryList(@RequestBody @Valid BrandQueryListReq req);

  /**
   * 根据ID 更新 品牌信息
   */
  @PostMapping("/brand/updateById")
  BrandUpdateByIdRes updateById(@RequestBody @Validated(UpdateCheck.class) BrandUpdateByIdReq req);

  /**
   * 分页查询 品牌信息
   */
  @PostMapping("/brand/queryPageList")
  DynamicsPage<BrandExportQueryPageListInfoRes> queryPageList(@RequestBody @Valid BrandExportQueryPageListReq req);

  /**
   * 导出 品牌信息
   */
  @PostMapping("/brand/exportQueryPageList")
  void queryPageListExport(@RequestBody @Valid BrandExportQueryPageListReq req);

  /**
   * 导入
   */
  @PostMapping("/brand/importData")
  BrandImportRes importData(@RequestParam("file") MultipartFile file);


  /**
   * 根据ID 批量查询
   */
  @PostMapping("/brand/queryByIdList")
  BrandQueryByIdListRes queryByIdListRes(@RequestBody @Valid BrandQueryByIdListReq req);


}
