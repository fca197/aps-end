package com.olivia.peanut.aps.api;

import org.springframework.validation.annotation.Validated;
import com.olivia.sdk.utils.DynamicsPage;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;
import com.olivia.peanut.aps.api.entity.apsRollingForecastOrderItem.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import org.springframework.web.multipart.MultipartFile;
import com.olivia.sdk.ann.InsertCheck;
import com.olivia.sdk.ann.UpdateCheck;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;


/**
 * 滚动预测订单节点表(ApsRollingForecastOrderItem)对外API
 *
 * @author peanut
 * @since 2024-07-16 10:31:19
 */
// @FeignClient(value = "",contextId = "apsRollingForecastOrderItem-api",url = "${ portal..center.endpoint:}")
public interface ApsRollingForecastOrderItemApi {

  /**
   * 保存 滚动预测订单节点表
   */
  @PostMapping("/apsRollingForecastOrderItem/insert")
  ApsRollingForecastOrderItemInsertRes insert(@RequestBody @Validated(InsertCheck.class) ApsRollingForecastOrderItemInsertReq req);

  /**
   * 根据ID 删除 滚动预测订单节点表
   */
  @PostMapping("/apsRollingForecastOrderItem/deleteByIdList")
  ApsRollingForecastOrderItemDeleteByIdListRes deleteByIdList(@RequestBody @Valid ApsRollingForecastOrderItemDeleteByIdListReq req);

  /**
   * 查询 滚动预测订单节点表
   */
  @PostMapping("/apsRollingForecastOrderItem/queryList")
  ApsRollingForecastOrderItemQueryListRes queryList(@RequestBody @Valid ApsRollingForecastOrderItemQueryListReq req);

  /**
   * 根据ID 更新 滚动预测订单节点表
   */
  @PostMapping("/apsRollingForecastOrderItem/updateById")
  ApsRollingForecastOrderItemUpdateByIdRes updateById(@RequestBody @Validated(UpdateCheck.class) ApsRollingForecastOrderItemUpdateByIdReq req);

  /**
   * 分页查询 滚动预测订单节点表
   */
  @PostMapping("/apsRollingForecastOrderItem/queryPageList")
  DynamicsPage<ApsRollingForecastOrderItemExportQueryPageListInfoRes> queryPageList(@RequestBody @Valid ApsRollingForecastOrderItemExportQueryPageListReq req);

  /**
   * 导出 滚动预测订单节点表
   */
  @PostMapping("/apsRollingForecastOrderItem/exportQueryPageList")
  void queryPageListExport(@RequestBody @Valid ApsRollingForecastOrderItemExportQueryPageListReq req);

  /**
   * 导入
   */
  @PostMapping("/apsRollingForecastOrderItem/importData")
  ApsRollingForecastOrderItemImportRes importData(@RequestParam("file") MultipartFile file);


  /**
   * 根据ID 批量查询
   */
  @PostMapping("/apsRollingForecastOrderItem/queryByIdList")
  ApsRollingForecastOrderItemQueryByIdListRes queryByIdListRes(@RequestBody @Valid ApsRollingForecastOrderItemQueryByIdListReq req);


}
