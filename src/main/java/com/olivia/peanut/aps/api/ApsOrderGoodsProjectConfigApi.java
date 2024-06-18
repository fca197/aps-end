package com.olivia.peanut.aps.api;


import com.olivia.peanut.aps.api.entity.apsOrderGoodsProjectConfig.*;
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
 * (ApsOrderGoodsProjectConfig)对外API
 *
 * @author peanut
 * @since 2024-04-09 13:19:37
 */
// @FeignClient(value = "",contextId = "apsOrderGoodsProjectConfig-api",url = "${ portal..center.endpoint:}")
public interface ApsOrderGoodsProjectConfigApi {

  /**
   * 保存
   */
  @PostMapping("/apsOrderGoodsProjectConfig/insert")
  ApsOrderGoodsProjectConfigInsertRes insert(@RequestBody @Validated(InsertCheck.class) ApsOrderGoodsProjectConfigInsertReq req);

  /**
   * 根据ID 删除
   */
  @PostMapping("/apsOrderGoodsProjectConfig/deleteByIdList")
  ApsOrderGoodsProjectConfigDeleteByIdListRes deleteByIdList(@RequestBody @Valid ApsOrderGoodsProjectConfigDeleteByIdListReq req);

  /**
   * 查询
   */
  @PostMapping("/apsOrderGoodsProjectConfig/queryList")
  ApsOrderGoodsProjectConfigQueryListRes queryList(@RequestBody @Valid ApsOrderGoodsProjectConfigQueryListReq req);

  /**
   * 根据ID 更新
   */
  @PostMapping("/apsOrderGoodsProjectConfig/updateById")
  ApsOrderGoodsProjectConfigUpdateByIdRes updateById(@RequestBody @Validated(UpdateCheck.class) ApsOrderGoodsProjectConfigUpdateByIdReq req);

  /**
   * 分页查询
   */
  @PostMapping("/apsOrderGoodsProjectConfig/queryPageList")
  DynamicsPage<ApsOrderGoodsProjectConfigExportQueryPageListInfoRes> queryPageList(@RequestBody @Valid ApsOrderGoodsProjectConfigExportQueryPageListReq req);

  /**
   * 导出
   */
  @PostMapping("/apsOrderGoodsProjectConfig/exportQueryPageList")
  void queryPageListExport(@RequestBody @Valid ApsOrderGoodsProjectConfigExportQueryPageListReq req);

  /**
   * 导入
   */
  @PostMapping("/apsOrderGoodsProjectConfig/importData")
  ApsOrderGoodsProjectConfigImportRes importData(@RequestParam("file") MultipartFile file);


  /**
   * 根据ID 批量查询
   */
  @PostMapping("/apsOrderGoodsProjectConfig/queryByIdList")
  ApsOrderGoodsProjectConfigQueryByIdListRes queryByIdListRes(@RequestBody @Valid ApsOrderGoodsProjectConfigQueryByIdListReq req);


}
