package com.olivia.peanut.aps.api;


import com.olivia.peanut.aps.api.entity.apsOrder.*;
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
 * (ApsOrder)对外API
 *
 * @author peanut
 * @since 2024-04-09 13:19:35
 */
// @FeignClient(value = "",contextId = "apsOrder-api",url = "${ portal..center.endpoint:}")
public interface ApsOrderApi {

  @PostMapping("/apsOrder/batchInsert")
  ApsOrderBatchInsertRes batchInsert(@RequestBody @Validated(InsertCheck.class) ApsOrderBatchInsertReq req);

  /**
   * 保存
   */
  @PostMapping("/apsOrder/insert")
  ApsOrderInsertRes insert(@RequestBody @Validated(InsertCheck.class) ApsOrderInsertReq req);

  /**
   * 根据ID 删除
   */
  @PostMapping("/apsOrder/deleteByIdList")
  ApsOrderDeleteByIdListRes deleteByIdList(@RequestBody @Valid ApsOrderDeleteByIdListReq req);

  /**
   * 查询
   */
  @PostMapping("/apsOrder/queryList")
  ApsOrderQueryListRes queryList(@RequestBody @Valid ApsOrderQueryListReq req);

  /**
   * 根据ID 更新
   */
  @PostMapping("/apsOrder/updateById")
  ApsOrderUpdateByIdRes updateById(@RequestBody @Validated(UpdateCheck.class) ApsOrderUpdateByIdReq req);

  /**
   * 分页查询
   */
  @PostMapping("/apsOrder/queryPageList")
  DynamicsPage<ApsOrderExportQueryPageListInfoRes> queryPageList(@RequestBody @Valid ApsOrderExportQueryPageListReq req);

  /**
   * 导出
   */
  @PostMapping("/apsOrder/exportQueryPageList")
  void queryPageListExport(@RequestBody @Valid ApsOrderExportQueryPageListReq req);

  /**
   * 导入
   */
  @PostMapping("/apsOrder/importData")
  ApsOrderImportRes importData(@RequestParam("file") MultipartFile file);


  /**
   * 根据ID 批量查询
   */
  @PostMapping("/apsOrder/queryByIdList")
  ApsOrderQueryByIdListRes queryByIdListRes(@RequestBody @Valid ApsOrderQueryByIdListReq req);

  @PostMapping("/apsOrder/timeLine")
  DynamicsPage<ApsOrderTimeLineRes> timeLine(@RequestBody @Valid ApsOrderTimeLineReq req);

  @PostMapping("/apsOrder/updateOrderStatus")
  ApsOrderUpdateOrderStatusRes updateOrderStatus(@RequestBody @Valid ApsOrderUpdateOrderStatusReq req);

  //schedulingDate
  @PostMapping("/apsOrder/updateSchedulingDate")
  ApsOrderUpdateSchedulingDateRes updateSchedulingDate(@RequestBody @Valid ApsOrderUpdateSchedulingDateReq req);

  @PostMapping("/apsOrder/orderCreateDayCount")
  OrderCreateDayCountRes orderCreateDayCount(@RequestBody @Valid OrderCreateDayCountReq req);

  @PostMapping("/apsOrder/statusList")
  OrderStatusListRes orderStatusList(@RequestBody @Valid OrderStatusListReq req);


}
