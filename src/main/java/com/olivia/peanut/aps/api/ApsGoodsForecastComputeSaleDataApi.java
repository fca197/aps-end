package com.olivia.peanut.aps.api;


import com.olivia.peanut.aps.api.entity.apsGoodsForecastComputeSaleData.*;
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
 * (ApsGoodsForecastComputeSaleData)对外API
 *
 * @author peanut
 * @since 2024-03-31 20:58:34
 */
// @FeignClient(value = "",contextId = "apsGoodsForecastComputeSaleData-api",url = "${ portal..center.endpoint:}")
public interface ApsGoodsForecastComputeSaleDataApi {

  /**
   * 保存
   */
  @PostMapping("/apsGoodsForecastComputeSaleData/insert")
  ApsGoodsForecastComputeSaleDataInsertRes insert(@RequestBody @Validated(InsertCheck.class) ApsGoodsForecastComputeSaleDataInsertReq req);

  /**
   * 根据ID 删除
   */
  @PostMapping("/apsGoodsForecastComputeSaleData/deleteByIdList")
  ApsGoodsForecastComputeSaleDataDeleteByIdListRes deleteByIdList(@RequestBody @Valid ApsGoodsForecastComputeSaleDataDeleteByIdListReq req);

  /**
   * 查询
   */
  @PostMapping("/apsGoodsForecastComputeSaleData/queryList")
  ApsGoodsForecastComputeSaleDataQueryListRes queryList(@RequestBody @Valid ApsGoodsForecastComputeSaleDataQueryListReq req);

  /**
   * 根据ID 更新
   */
  @PostMapping("/apsGoodsForecastComputeSaleData/updateById")
  ApsGoodsForecastComputeSaleDataUpdateByIdRes updateById(@RequestBody @Validated(UpdateCheck.class) ApsGoodsForecastComputeSaleDataUpdateByIdReq req);

  /**
   * 分页查询
   */
  @PostMapping("/apsGoodsForecastComputeSaleData/queryPageList")
  DynamicsPage<ApsGoodsForecastComputeSaleDataExportQueryPageListInfoRes> queryPageList(@RequestBody @Valid ApsGoodsForecastComputeSaleDataExportQueryPageListReq req);

  /**
   * 导出
   */
  @PostMapping("/apsGoodsForecastComputeSaleData/exportQueryPageList")
  void queryPageListExport(@RequestBody @Valid ApsGoodsForecastComputeSaleDataExportQueryPageListReq req);

  /**
   * 导入
   */
  @PostMapping("/apsGoodsForecastComputeSaleData/importData")
  ApsGoodsForecastComputeSaleDataImportRes importData(@RequestParam("file") MultipartFile file);


  /**
   * 根据ID 批量查询
   */
  @PostMapping("/apsGoodsForecastComputeSaleData/queryByIdList")
  ApsGoodsForecastComputeSaleDataQueryByIdListRes queryByIdListRes(@RequestBody @Valid ApsGoodsForecastComputeSaleDataQueryByIdListReq req);


}
