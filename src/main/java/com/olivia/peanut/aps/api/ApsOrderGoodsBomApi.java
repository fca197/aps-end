package com.olivia.peanut.aps.api;

import com.olivia.peanut.aps.api.entity.apsOrderGoodsBom.*;
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
 * 订单商品零件表(ApsOrderGoodsBom)对外API
 *
 * @author peanut
 * @since 2024-07-30 10:28:23
 */
// @FeignClient(value = "",contextId = "apsOrderGoodsBom-api",url = "${ portal..center.endpoint:}")
public interface ApsOrderGoodsBomApi {

  /**
   * 保存 订单商品零件表
   */
  @PostMapping("/apsOrderGoodsBom/insert")
  ApsOrderGoodsBomInsertRes insert(@RequestBody @Validated(InsertCheck.class) ApsOrderGoodsBomInsertReq req);

  /**
   * 根据ID 删除 订单商品零件表
   */
  @PostMapping("/apsOrderGoodsBom/deleteByIdList")
  ApsOrderGoodsBomDeleteByIdListRes deleteByIdList(@RequestBody @Valid ApsOrderGoodsBomDeleteByIdListReq req);

  /**
   * 查询 订单商品零件表
   */
  @PostMapping("/apsOrderGoodsBom/queryList")
  ApsOrderGoodsBomQueryListRes queryList(@RequestBody @Valid ApsOrderGoodsBomQueryListReq req);

  /**
   * 根据ID 更新 订单商品零件表
   */
  @PostMapping("/apsOrderGoodsBom/updateById")
  ApsOrderGoodsBomUpdateByIdRes updateById(@RequestBody @Validated(UpdateCheck.class) ApsOrderGoodsBomUpdateByIdReq req);

  /**
   * 分页查询 订单商品零件表
   */
  @PostMapping("/apsOrderGoodsBom/queryPageList")
  DynamicsPage<ApsOrderGoodsBomExportQueryPageListInfoRes> queryPageList(@RequestBody @Valid ApsOrderGoodsBomExportQueryPageListReq req);

  /**
   * 导出 订单商品零件表
   */
  @PostMapping("/apsOrderGoodsBom/exportQueryPageList")
  void queryPageListExport(@RequestBody @Valid ApsOrderGoodsBomExportQueryPageListReq req);

  /**
   * 导入
   */
  @PostMapping("/apsOrderGoodsBom/importData")
  ApsOrderGoodsBomImportRes importData(@RequestParam("file") MultipartFile file);


  /**
   * 根据ID 批量查询
   */
  @PostMapping("/apsOrderGoodsBom/queryByIdList")
  ApsOrderGoodsBomQueryByIdListRes queryByIdListRes(@RequestBody @Valid ApsOrderGoodsBomQueryByIdListReq req);


}
