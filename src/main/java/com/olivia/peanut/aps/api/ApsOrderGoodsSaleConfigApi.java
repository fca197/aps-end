package com.olivia.peanut.aps.api;


import com.olivia.peanut.aps.api.entity.apsOrderGoodsSaleConfig.*;
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
 * (ApsOrderGoodsSaleConfig)对外API
 *
 * @author peanut
 * @since 2024-04-09 13:19:37
 */
// @FeignClient(value = "",contextId = "apsOrderGoodsSaleConfig-api",url = "${ portal..center.endpoint:}")
public interface ApsOrderGoodsSaleConfigApi {

  /**
   * 保存
   */
  @PostMapping("/apsOrderGoodsSaleConfig/insert")
  ApsOrderGoodsSaleConfigInsertRes insert(@RequestBody @Validated(InsertCheck.class) ApsOrderGoodsSaleConfigInsertReq req);

  /**
   * 根据ID 删除
   */
  @PostMapping("/apsOrderGoodsSaleConfig/deleteByIdList")
  ApsOrderGoodsSaleConfigDeleteByIdListRes deleteByIdList(@RequestBody @Valid ApsOrderGoodsSaleConfigDeleteByIdListReq req);

  /**
   * 查询
   */
  @PostMapping("/apsOrderGoodsSaleConfig/queryList")
  ApsOrderGoodsSaleConfigQueryListRes queryList(@RequestBody @Valid ApsOrderGoodsSaleConfigQueryListReq req);

  /**
   * 根据ID 更新
   */
  @PostMapping("/apsOrderGoodsSaleConfig/updateById")
  ApsOrderGoodsSaleConfigUpdateByIdRes updateById(@RequestBody @Validated(UpdateCheck.class) ApsOrderGoodsSaleConfigUpdateByIdReq req);

  /**
   * 分页查询
   */
  @PostMapping("/apsOrderGoodsSaleConfig/queryPageList")
  DynamicsPage<ApsOrderGoodsSaleConfigExportQueryPageListInfoRes> queryPageList(@RequestBody @Valid ApsOrderGoodsSaleConfigExportQueryPageListReq req);

  /**
   * 导出
   */
  @PostMapping("/apsOrderGoodsSaleConfig/exportQueryPageList")
  void queryPageListExport(@RequestBody @Valid ApsOrderGoodsSaleConfigExportQueryPageListReq req);

  /**
   * 导入
   */
  @PostMapping("/apsOrderGoodsSaleConfig/importData")
  ApsOrderGoodsSaleConfigImportRes importData(@RequestParam("file") MultipartFile file);


  /**
   * 根据ID 批量查询
   */
  @PostMapping("/apsOrderGoodsSaleConfig/queryByIdList")
  ApsOrderGoodsSaleConfigQueryByIdListRes queryByIdListRes(@RequestBody @Valid ApsOrderGoodsSaleConfigQueryByIdListReq req);


}
