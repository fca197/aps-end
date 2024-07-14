package com.olivia.peanut.aps.api;

import org.springframework.validation.annotation.Validated;
import com.olivia.sdk.utils.DynamicsPage;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;
import com.olivia.peanut.aps.api.entity.apsOrderRollingForecast.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import org.springframework.web.multipart.MultipartFile;
import com.olivia.sdk.ann.InsertCheck;
import com.olivia.sdk.ann.UpdateCheck;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;


/**
 * 滚动预测(ApsOrderRollingForecast)对外API
 *
 * @author peanut
 * @since 2024-07-14 19:43:50
 */
// @FeignClient(value = "",contextId = "apsOrderRollingForecast-api",url = "${ portal..center.endpoint:}")
public interface ApsOrderRollingForecastApi {

  /**
   * 保存 滚动预测
   */
  @PostMapping("/apsOrderRollingForecast/insert")
  ApsOrderRollingForecastInsertRes insert(@RequestBody @Validated(InsertCheck.class) ApsOrderRollingForecastInsertReq req);

  /**
   * 根据ID 删除 滚动预测
   */
  @PostMapping("/apsOrderRollingForecast/deleteByIdList")
  ApsOrderRollingForecastDeleteByIdListRes deleteByIdList(@RequestBody @Valid ApsOrderRollingForecastDeleteByIdListReq req);

  /**
   * 查询 滚动预测
   */
  @PostMapping("/apsOrderRollingForecast/queryList")
  ApsOrderRollingForecastQueryListRes queryList(@RequestBody @Valid ApsOrderRollingForecastQueryListReq req);

  /**
   * 根据ID 更新 滚动预测
   */
  @PostMapping("/apsOrderRollingForecast/updateById")
  ApsOrderRollingForecastUpdateByIdRes updateById(@RequestBody @Validated(UpdateCheck.class) ApsOrderRollingForecastUpdateByIdReq req);

  /**
   * 分页查询 滚动预测
   */
  @PostMapping("/apsOrderRollingForecast/queryPageList")
  DynamicsPage<ApsOrderRollingForecastExportQueryPageListInfoRes> queryPageList(@RequestBody @Valid ApsOrderRollingForecastExportQueryPageListReq req);

  /**
   * 导出 滚动预测
   */
  @PostMapping("/apsOrderRollingForecast/exportQueryPageList")
  void queryPageListExport(@RequestBody @Valid ApsOrderRollingForecastExportQueryPageListReq req);

  /**
   * 导入
   */
  @PostMapping("/apsOrderRollingForecast/importData")
  ApsOrderRollingForecastImportRes importData(@RequestParam("file") MultipartFile file);


  /**
   * 根据ID 批量查询
   */
  @PostMapping("/apsOrderRollingForecast/queryByIdList")
  ApsOrderRollingForecastQueryByIdListRes queryByIdListRes(@RequestBody @Valid ApsOrderRollingForecastQueryByIdListReq req);


}
