package com.olivia.peanut.aps.api;

import com.olivia.peanut.aps.api.entity.apsOrderGoodsHistory.*;
import com.olivia.peanut.aps.api.entity.apsOrderGoodsSaleHistory.SelectOrder2HistoryReq;
import com.olivia.peanut.aps.api.entity.apsOrderGoodsSaleHistory.SelectOrder2HistoryRes;
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
 * 历史订单记录(ApsOrderGoodsHistory)对外API
 *
 * @author makejava
 * @since 2025-02-20 17:18:46
 */
// @FeignClient(value = "",contextId = "apsOrderGoodsHistory-api",url = "${ portal..center.endpoint:}")
public interface ApsOrderGoodsHistoryApi {

  /**
   * 保存 历史订单记录
   */
  @PostMapping("/apsOrderGoodsHistory/insert")
  ApsOrderGoodsHistoryInsertRes insert(@RequestBody @Validated(InsertCheck.class) ApsOrderGoodsHistoryInsertReq req);

  /**
   * 根据ID 删除 历史订单记录
   */
  @PostMapping("/apsOrderGoodsHistory/deleteByIdList")
  ApsOrderGoodsHistoryDeleteByIdListRes deleteByIdList(@RequestBody @Valid ApsOrderGoodsHistoryDeleteByIdListReq req);

  /**
   * 查询 历史订单记录
   */
  @PostMapping("/apsOrderGoodsHistory/queryList")
  ApsOrderGoodsHistoryQueryListRes queryList(@RequestBody @Valid ApsOrderGoodsHistoryQueryListReq req);

  /**
   * 根据ID 更新 历史订单记录
   */
  @PostMapping("/apsOrderGoodsHistory/updateById")
  ApsOrderGoodsHistoryUpdateByIdRes updateById(@RequestBody @Validated(UpdateCheck.class) ApsOrderGoodsHistoryUpdateByIdReq req);

  /**
   * 分页查询 历史订单记录
   */
  @PostMapping("/apsOrderGoodsHistory/queryPageList")
  DynamicsPage<ApsOrderGoodsHistoryExportQueryPageListInfoRes> queryPageList(@RequestBody @Valid ApsOrderGoodsHistoryExportQueryPageListReq req);

  /**
   * 导出 历史订单记录
   */
  @PostMapping("/apsOrderGoodsHistory/exportQueryPageList")
  void queryPageListExport(@RequestBody @Valid ApsOrderGoodsHistoryExportQueryPageListReq req);

  /**
   * 导入
   */
  @PostMapping("/apsOrderGoodsHistory/importData")
  ApsOrderGoodsHistoryImportRes importData(@RequestParam("file") MultipartFile file);


  /**
   * 根据ID 批量查询
   */
  @PostMapping("/apsOrderGoodsHistory/queryByIdList")
  ApsOrderGoodsHistoryQueryByIdListRes queryByIdListRes(@RequestBody @Valid ApsOrderGoodsHistoryQueryByIdListReq req);


  /***
   *  查询销售历史到结果表
   * @param req 如参
   * @return 结果
   */
  @PostMapping("/apsOrderGoodsHistory/selectOrder2History")
  SelectOrder2HistoryRes selectOrder2History(@RequestBody @Valid SelectOrder2HistoryReq req);
}
