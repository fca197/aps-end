package com.olivia.peanut.aps.api;

import com.olivia.peanut.aps.api.entity.apsOrderGoodsSaleHistory.*;
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
 * 销售规划订单历史销售占比(ApsOrderGoodsSaleHistory)对外API
 *
 * @author makejava
 * @since 2025-02-18 14:28:39
 */
// @FeignClient(value = "",contextId = "apsOrderGoodsSaleHistory-api",url = "${ portal..center.endpoint:}")
public interface ApsOrderGoodsSaleHistoryApi {

  /**
   * 保存 销售规划订单历史销售占比
   */
  @PostMapping("/apsOrderGoodsSaleHistory/insert")
  ApsOrderGoodsSaleHistoryInsertRes insert(@RequestBody @Validated(InsertCheck.class) ApsOrderGoodsSaleHistoryInsertReq req);

  /**
   * 根据ID 删除 销售规划订单历史销售占比
   */
  @PostMapping("/apsOrderGoodsSaleHistory/deleteByIdList")
  ApsOrderGoodsSaleHistoryDeleteByIdListRes deleteByIdList(@RequestBody @Valid ApsOrderGoodsSaleHistoryDeleteByIdListReq req);

  /**
   * 查询 销售规划订单历史销售占比
   */
  @PostMapping("/apsOrderGoodsSaleHistory/queryList")
  ApsOrderGoodsSaleHistoryQueryListRes queryList(@RequestBody @Valid ApsOrderGoodsSaleHistoryQueryListReq req);

  /**
   * 根据ID 更新 销售规划订单历史销售占比
   */
  @PostMapping("/apsOrderGoodsSaleHistory/updateById")
  ApsOrderGoodsSaleHistoryUpdateByIdRes updateById(@RequestBody @Validated(UpdateCheck.class) ApsOrderGoodsSaleHistoryUpdateByIdReq req);

  /**
   * 分页查询 销售规划订单历史销售占比
   */
  @PostMapping("/apsOrderGoodsSaleHistory/queryPageList")
  DynamicsPage<ApsOrderGoodsSaleHistoryExportQueryPageListInfoRes> queryPageList(@RequestBody @Valid ApsOrderGoodsSaleHistoryExportQueryPageListReq req);

  /**
   * 导出 销售规划订单历史销售占比
   */
  @PostMapping("/apsOrderGoodsSaleHistory/exportQueryPageList")
  void queryPageListExport(@RequestBody @Valid ApsOrderGoodsSaleHistoryExportQueryPageListReq req);

  /**
   * 导入
   */
  @PostMapping("/apsOrderGoodsSaleHistory/importData")
  ApsOrderGoodsSaleHistoryImportRes importData(@RequestParam("file") MultipartFile file);


  /**
   * 根据ID 批量查询
   */
  @PostMapping("/apsOrderGoodsSaleHistory/queryByIdList")
  ApsOrderGoodsSaleHistoryQueryByIdListRes queryByIdListRes(@RequestBody @Valid ApsOrderGoodsSaleHistoryQueryByIdListReq req);


  /***
   *  查询销售历史到结果表
   * @param req 如参
   * @return 结果
   */
  @PostMapping("/apsOrderGoodsSaleHistory/selectOrder2History")
  SelectOrder2HistoryRes selectOrder2History(@RequestBody @Valid SelectOrder2HistoryReq req);

}
