package com.olivia.peanut.aps.api;

import com.olivia.peanut.aps.api.entity.apsOrderGoodsStatusDate.*;
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
 * 订单商品状态表(ApsOrderGoodsStatusDate)对外API
 *
 * @author peanut
 * @since 2024-06-14 10:26:58
 */
// @FeignClient(value = "",contextId = "apsOrderGoodsStatusDate-api",url = "${ portal..center.endpoint:}")
public interface ApsOrderGoodsStatusDateApi {

  /**
   * 保存 订单商品状态表
   */
  @PostMapping("/apsOrderGoodsStatusDate/insert")
  ApsOrderGoodsStatusDateInsertRes insert(@RequestBody @Validated(InsertCheck.class) ApsOrderGoodsStatusDateInsertReq req);

  /**
   * 根据ID 删除 订单商品状态表
   */
  @PostMapping("/apsOrderGoodsStatusDate/deleteByIdList")
  ApsOrderGoodsStatusDateDeleteByIdListRes deleteByIdList(@RequestBody @Valid ApsOrderGoodsStatusDateDeleteByIdListReq req);

  /**
   * 查询 订单商品状态表
   */
  @PostMapping("/apsOrderGoodsStatusDate/queryList")
  ApsOrderGoodsStatusDateQueryListRes queryList(@RequestBody @Valid ApsOrderGoodsStatusDateQueryListReq req);

  /**
   * 根据ID 更新 订单商品状态表
   */
  @PostMapping("/apsOrderGoodsStatusDate/updateById")
  ApsOrderGoodsStatusDateUpdateByIdRes updateById(@RequestBody @Validated(UpdateCheck.class) ApsOrderGoodsStatusDateUpdateByIdReq req);

  /**
   * 分页查询 订单商品状态表
   */
  @PostMapping("/apsOrderGoodsStatusDate/queryPageList")
  DynamicsPage<ApsOrderGoodsStatusDateExportQueryPageListInfoRes> queryPageList(@RequestBody @Valid ApsOrderGoodsStatusDateExportQueryPageListReq req);

  /**
   * 导出 订单商品状态表
   */
  @PostMapping("/apsOrderGoodsStatusDate/exportQueryPageList")
  void queryPageListExport(@RequestBody @Valid ApsOrderGoodsStatusDateExportQueryPageListReq req);

  /**
   * 导入
   */
  @PostMapping("/apsOrderGoodsStatusDate/importData")
  ApsOrderGoodsStatusDateImportRes importData(@RequestParam("file") MultipartFile file);


  /**
   * 根据ID 批量查询
   */
  @PostMapping("/apsOrderGoodsStatusDate/queryByIdList")
  ApsOrderGoodsStatusDateQueryByIdListRes queryByIdListRes(@RequestBody @Valid ApsOrderGoodsStatusDateQueryByIdListReq req);


}
