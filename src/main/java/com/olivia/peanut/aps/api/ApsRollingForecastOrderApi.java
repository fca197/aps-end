package com.olivia.peanut.aps.api;

import com.olivia.peanut.aps.api.entity.apsRollingForecastOrder.*;
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
 * 滚动预测(ApsRollingForecastOrder)对外API
 *
 * @author peanut
 * @since 2024-07-14 20:22:27
 */
// @FeignClient(value = "",contextId = "apsRollingForecastOrder-api",url = "${ portal..center.endpoint:}")
public interface ApsRollingForecastOrderApi {

  /**
   * 保存 滚动预测
   */
  @PostMapping("/apsRollingForecastOrder/insert")
  ApsRollingForecastOrderInsertRes insert(@RequestBody @Validated(InsertCheck.class) ApsRollingForecastOrderInsertReq req);

  /**
   * 根据ID 删除 滚动预测
   */
  @PostMapping("/apsRollingForecastOrder/deleteByIdList")
  ApsRollingForecastOrderDeleteByIdListRes deleteByIdList(@RequestBody @Valid ApsRollingForecastOrderDeleteByIdListReq req);

  /**
   * 查询 滚动预测
   */
  @PostMapping("/apsRollingForecastOrder/queryList")
  ApsRollingForecastOrderQueryListRes queryList(@RequestBody @Valid ApsRollingForecastOrderQueryListReq req);

  /**
   * 根据ID 更新 滚动预测
   */
  @PostMapping("/apsRollingForecastOrder/updateById")
  ApsRollingForecastOrderUpdateByIdRes updateById(@RequestBody @Validated(UpdateCheck.class) ApsRollingForecastOrderUpdateByIdReq req);

  /**
   * 分页查询 滚动预测
   */
  @PostMapping("/apsRollingForecastOrder/queryPageList")
  DynamicsPage<ApsRollingForecastOrderExportQueryPageListInfoRes> queryPageList(@RequestBody @Valid ApsRollingForecastOrderExportQueryPageListReq req);

  /**
   * 导出 滚动预测
   */
  @PostMapping("/apsRollingForecastOrder/exportQueryPageList")
  void queryPageListExport(@RequestBody @Valid ApsRollingForecastOrderExportQueryPageListReq req);

  /**
   * 导入
   */
  @PostMapping("/apsRollingForecastOrder/importData")
  ApsRollingForecastOrderImportRes importData(@RequestParam("file") MultipartFile file);


  /**
   * 根据ID 批量查询
   */
  @PostMapping("/apsRollingForecastOrder/queryByIdList")
  ApsRollingForecastOrderQueryByIdListRes queryByIdListRes(@RequestBody @Valid ApsRollingForecastOrderQueryByIdListReq req);


}
