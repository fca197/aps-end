package com.olivia.peanut.portal.api;


import com.olivia.peanut.portal.api.entity.property.*;
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
 * 资产信息(Property)对外API
 *
 * @author makejava
 * @since 2024-03-11 17:20:52
 */
// @FeignClient(value = "",contextId = "property-api",url = "${ portal..center.endpoint:}")
public interface PropertyApi {

  /**
   * 保存 资产信息
   */
  @PostMapping("/property/insert")
  PropertyInsertRes insert(@RequestBody @Validated(InsertCheck.class) PropertyInsertReq req);

  /**
   * 根据ID 删除 资产信息
   */
  @PostMapping("/property/deleteByIdList")
  PropertyDeleteByIdListRes deleteByIdList(@RequestBody @Valid PropertyDeleteByIdListReq req);

  /**
   * 查询 资产信息
   */
  @PostMapping("/property/queryList")
  PropertyQueryListRes queryList(@RequestBody @Valid PropertyQueryListReq req);

  /**
   * 根据ID 更新 资产信息
   */
  @PostMapping("/property/updateById")
  PropertyUpdateByIdRes updateById(@RequestBody @Validated(UpdateCheck.class) PropertyUpdateByIdReq req);

  /**
   * 分页查询 资产信息
   */
  @PostMapping("/property/queryPageList")
  DynamicsPage<PropertyExportQueryPageListInfoRes> queryPageList(@RequestBody @Valid PropertyExportQueryPageListReq req);

  /**
   * 导出 资产信息
   */
  @PostMapping("/property/exportQueryPageList")
  void queryPageListExport(@RequestBody @Valid PropertyExportQueryPageListReq req);

  /**
   * 导入
   */
  @PostMapping("/property/importData")
  PropertyImportRes importData(@RequestParam("file") MultipartFile file);


  /**
   * 根据ID 批量查询
   */
  @PostMapping("/property/queryByIdList")
  PropertyQueryByIdListRes queryByIdListRes(@RequestBody @Valid PropertyQueryByIdListReq req);


  /**
   * 根据ID 批量查询
   */
  @PostMapping("/property/saveBatch")
  PropertySaveBatchRes saveBatch(@RequestBody @Valid PropertySaveBatchReq req);

  @PostMapping("/property/updateUse")
  PropertyUpdateUseRes updateUse(@RequestBody @Valid PropertyUpdateUseReq req);


}
