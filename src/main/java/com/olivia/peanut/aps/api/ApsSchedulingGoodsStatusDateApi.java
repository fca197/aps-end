package com.olivia.peanut.aps.api;

import com.olivia.peanut.aps.api.entity.apsSchedulingGoodsStatusDate.*;
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
 * 订单商品状态表(ApsSchedulingGoodsStatusDate)对外API
 *
 * @author peanut
 * @since 2024-06-14 21:35:08
 */
// @FeignClient(value = "",contextId = "apsSchedulingGoodsStatusDate-api",url = "${ portal..center.endpoint:}")
public interface ApsSchedulingGoodsStatusDateApi {

  /**
   * 保存 订单商品状态表
   */
  @PostMapping("/apsSchedulingGoodsStatusDate/insert")
  ApsSchedulingGoodsStatusDateInsertRes insert(@RequestBody @Validated(InsertCheck.class) ApsSchedulingGoodsStatusDateInsertReq req);

  /**
   * 根据ID 删除 订单商品状态表
   */
  @PostMapping("/apsSchedulingGoodsStatusDate/deleteByIdList")
  ApsSchedulingGoodsStatusDateDeleteByIdListRes deleteByIdList(@RequestBody @Valid ApsSchedulingGoodsStatusDateDeleteByIdListReq req);

  /**
   * 查询 订单商品状态表
   */
  @PostMapping("/apsSchedulingGoodsStatusDate/queryList")
  ApsSchedulingGoodsStatusDateQueryListRes queryList(@RequestBody @Valid ApsSchedulingGoodsStatusDateQueryListReq req);

  /**
   * 根据ID 更新 订单商品状态表
   */
  @PostMapping("/apsSchedulingGoodsStatusDate/updateById")
  ApsSchedulingGoodsStatusDateUpdateByIdRes updateById(@RequestBody @Validated(UpdateCheck.class) ApsSchedulingGoodsStatusDateUpdateByIdReq req);

  /**
   * 分页查询 订单商品状态表
   */
  @PostMapping("/apsSchedulingGoodsStatusDate/queryPageList")
  DynamicsPage<ApsSchedulingGoodsStatusDateExportQueryPageListInfoRes> queryPageList(@RequestBody @Valid ApsSchedulingGoodsStatusDateExportQueryPageListReq req);

  /**
   * 导出 订单商品状态表
   */
  @PostMapping("/apsSchedulingGoodsStatusDate/exportQueryPageList")
  void queryPageListExport(@RequestBody @Valid ApsSchedulingGoodsStatusDateExportQueryPageListReq req);

  /**
   * 导入
   */
  @PostMapping("/apsSchedulingGoodsStatusDate/importData")
  ApsSchedulingGoodsStatusDateImportRes importData(@RequestParam("file") MultipartFile file);


  /**
   * 根据ID 批量查询
   */
  @PostMapping("/apsSchedulingGoodsStatusDate/queryByIdList")
  ApsSchedulingGoodsStatusDateQueryByIdListRes queryByIdListRes(@RequestBody @Valid ApsSchedulingGoodsStatusDateQueryByIdListReq req);


}
