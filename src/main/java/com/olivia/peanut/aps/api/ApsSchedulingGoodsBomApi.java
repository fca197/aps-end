package com.olivia.peanut.aps.api;


import com.olivia.peanut.aps.api.entity.apsSchedulingGoodsBom.*;
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
 * 订单商品零件表(ApsSchedulingGoodsBom)对外API
 *
 * @author peanut
 * @since 2024-06-02 21:50:24
 */
// @FeignClient(value = "",contextId = "apsSchedulingGoodsBom-api",url = "${ portal..center.endpoint:}")
public interface ApsSchedulingGoodsBomApi {

  /**
   * 保存 订单商品零件表
   */
  @PostMapping("/apsSchedulingGoodsBom/insert")
  ApsSchedulingGoodsBomInsertRes insert(@RequestBody @Validated(InsertCheck.class) ApsSchedulingGoodsBomInsertReq req);

  /**
   * 根据ID 删除 订单商品零件表
   */
  @PostMapping("/apsSchedulingGoodsBom/deleteByIdList")
  ApsSchedulingGoodsBomDeleteByIdListRes deleteByIdList(@RequestBody @Valid ApsSchedulingGoodsBomDeleteByIdListReq req);

  /**
   * 查询 订单商品零件表
   */
  @PostMapping("/apsSchedulingGoodsBom/queryList")
  ApsSchedulingGoodsBomQueryListRes queryList(@RequestBody @Valid ApsSchedulingGoodsBomQueryListReq req);

  /**
   * 根据ID 更新 订单商品零件表
   */
  @PostMapping("/apsSchedulingGoodsBom/updateById")
  ApsSchedulingGoodsBomUpdateByIdRes updateById(@RequestBody @Validated(UpdateCheck.class) ApsSchedulingGoodsBomUpdateByIdReq req);

  /**
   * 分页查询 订单商品零件表
   */
  @PostMapping("/apsSchedulingGoodsBom/queryPageList")
  DynamicsPage<ApsSchedulingGoodsBomExportQueryPageListInfoRes> queryPageList(@RequestBody @Valid ApsSchedulingGoodsBomExportQueryPageListReq req);

  /**
   * 导出 订单商品零件表
   */
  @PostMapping("/apsSchedulingGoodsBom/exportQueryPageList")
  void queryPageListExport(@RequestBody @Valid ApsSchedulingGoodsBomExportQueryPageListReq req);

  /**
   * 导入
   */
  @PostMapping("/apsSchedulingGoodsBom/importData")
  ApsSchedulingGoodsBomImportRes importData(@RequestParam("file") MultipartFile file);


  /**
   * 根据ID 批量查询
   */
  @PostMapping("/apsSchedulingGoodsBom/queryByIdList")
  ApsSchedulingGoodsBomQueryByIdListRes queryByIdListRes(@RequestBody @Valid ApsSchedulingGoodsBomQueryByIdListReq req);


}
