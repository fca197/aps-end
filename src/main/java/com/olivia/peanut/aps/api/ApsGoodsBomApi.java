package com.olivia.peanut.aps.api;


import com.olivia.peanut.aps.api.entity.apsGoodsBom.*;
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
 * (ApsGoodsBom)对外API
 *
 * @author peanut
 * @since 2024-04-03 22:28:55
 */
// @FeignClient(value = "",contextId = "apsGoodsBom-api",url = "${ portal..center.endpoint:}")
public interface ApsGoodsBomApi {

  /**
   * 保存
   */
  @PostMapping("/apsGoodsBom/insert")
  ApsGoodsBomInsertRes insert(@RequestBody @Validated(InsertCheck.class) ApsGoodsBomInsertReq req);

  /**
   * 根据ID 删除
   */
  @PostMapping("/apsGoodsBom/deleteByIdList")
  ApsGoodsBomDeleteByIdListRes deleteByIdList(@RequestBody @Valid ApsGoodsBomDeleteByIdListReq req);

  /**
   * 查询
   */
  @PostMapping("/apsGoodsBom/queryList")
  ApsGoodsBomQueryListRes queryList(@RequestBody @Valid ApsGoodsBomQueryListReq req);

  /**
   * 根据ID 更新
   */
  @PostMapping("/apsGoodsBom/updateById")
  ApsGoodsBomUpdateByIdRes updateById(@RequestBody @Validated(UpdateCheck.class) ApsGoodsBomUpdateByIdReq req);

  /**
   * 分页查询
   */
  @PostMapping("/apsGoodsBom/queryPageList")
  DynamicsPage<ApsGoodsBomExportQueryPageListInfoRes> queryPageList(@RequestBody @Valid ApsGoodsBomExportQueryPageListReq req);

  /**
   * 导出
   */
  @PostMapping("/apsGoodsBom/exportQueryPageList")
  void queryPageListExport(@RequestBody @Valid ApsGoodsBomExportQueryPageListReq req);

  /**
   * 导入
   */
  @PostMapping("/apsGoodsBom/importData")
  ApsGoodsBomImportRes importData(@RequestParam("file") MultipartFile file);


  /**
   * 根据ID 批量查询
   */
  @PostMapping("/apsGoodsBom/queryByIdList")
  ApsGoodsBomQueryByIdListRes queryByIdListRes(@RequestBody @Valid ApsGoodsBomQueryByIdListReq req);


}
