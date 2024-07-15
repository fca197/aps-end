package com.olivia.peanut.aps.api;

import com.olivia.peanut.aps.api.entity.apsRollingForecastFactoryCapacity.*;
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
 * 滚动预测(ApsRollingForecastFactoryCapacity)对外API
 *
 * @author peanut
 * @since 2024-07-14 20:22:22
 */
// @FeignClient(value = "",contextId = "apsRollingForecastFactoryCapacity-api",url = "${ portal..center.endpoint:}")
public interface ApsRollingForecastFactoryCapacityApi {

  /**
   * 保存 滚动预测
   */
  @PostMapping("/apsRollingForecastFactoryCapacity/insert")
  ApsRollingForecastFactoryCapacityInsertRes insert(@RequestBody @Validated(InsertCheck.class) ApsRollingForecastFactoryCapacityInsertReq req);

  /**
   * 根据ID 删除 滚动预测
   */
  @PostMapping("/apsRollingForecastFactoryCapacity/deleteByIdList")
  ApsRollingForecastFactoryCapacityDeleteByIdListRes deleteByIdList(@RequestBody @Valid ApsRollingForecastFactoryCapacityDeleteByIdListReq req);

  /**
   * 查询 滚动预测
   */
  @PostMapping("/apsRollingForecastFactoryCapacity/queryList")
  ApsRollingForecastFactoryCapacityQueryListRes queryList(@RequestBody @Valid ApsRollingForecastFactoryCapacityQueryListReq req);

  /**
   * 根据ID 更新 滚动预测
   */
  @PostMapping("/apsRollingForecastFactoryCapacity/updateById")
  ApsRollingForecastFactoryCapacityUpdateByIdRes updateById(@RequestBody @Validated(UpdateCheck.class) ApsRollingForecastFactoryCapacityUpdateByIdReq req);

  /**
   * 分页查询 滚动预测
   */
  @PostMapping("/apsRollingForecastFactoryCapacity/queryPageList")
  DynamicsPage<ApsRollingForecastFactoryCapacityExportQueryPageListInfoRes> queryPageList(@RequestBody @Valid ApsRollingForecastFactoryCapacityExportQueryPageListReq req);

  /**
   * 导出 滚动预测
   */
  @PostMapping("/apsRollingForecastFactoryCapacity/exportQueryPageList")
  void queryPageListExport(@RequestBody @Valid ApsRollingForecastFactoryCapacityExportQueryPageListReq req);

  /**
   * 导入
   */
  @PostMapping("/apsRollingForecastFactoryCapacity/importData")
  ApsRollingForecastFactoryCapacityImportRes importData(@RequestParam("file") MultipartFile file);


  /**
   * 根据ID 批量查询
   */
  @PostMapping("/apsRollingForecastFactoryCapacity/queryByIdList")
  ApsRollingForecastFactoryCapacityQueryByIdListRes queryByIdListRes(@RequestBody @Valid ApsRollingForecastFactoryCapacityQueryByIdListReq req);


}
