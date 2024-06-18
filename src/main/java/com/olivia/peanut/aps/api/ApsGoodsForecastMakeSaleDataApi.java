package com.olivia.peanut.aps.api;


import com.olivia.peanut.aps.api.entity.apsGoodsForecastMakeSaleData.*;
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
 * (ApsGoodsForecastMakeSaleData)对外API
 *
 * @author peanut
 * @since 2024-04-07 15:07:49
 */
// @FeignClient(value = "",contextId = "apsGoodsForecastMakeSaleData-api",url = "${ portal..center.endpoint:}")
public interface ApsGoodsForecastMakeSaleDataApi {

  /**
   * 保存
   */
  @PostMapping("/apsGoodsForecastMakeSaleData/insert")
  ApsGoodsForecastMakeSaleDataInsertRes insert(@RequestBody @Validated(InsertCheck.class) ApsGoodsForecastMakeSaleDataInsertReq req);

  /**
   * 根据ID 删除
   */
  @PostMapping("/apsGoodsForecastMakeSaleData/deleteByIdList")
  ApsGoodsForecastMakeSaleDataDeleteByIdListRes deleteByIdList(@RequestBody @Valid ApsGoodsForecastMakeSaleDataDeleteByIdListReq req);

  /**
   * 查询
   */
  @PostMapping("/apsGoodsForecastMakeSaleData/queryList")
  ApsGoodsForecastMakeSaleDataQueryListRes queryList(@RequestBody @Valid ApsGoodsForecastMakeSaleDataQueryListReq req);

  /**
   * 根据ID 更新
   */
  @PostMapping("/apsGoodsForecastMakeSaleData/updateById")
  ApsGoodsForecastMakeSaleDataUpdateByIdRes updateById(@RequestBody @Validated(UpdateCheck.class) ApsGoodsForecastMakeSaleDataUpdateByIdReq req);

  /**
   * 分页查询
   */
  @PostMapping("/apsGoodsForecastMakeSaleData/queryPageList")
  DynamicsPage<ApsGoodsForecastMakeSaleDataExportQueryPageListInfoRes> queryPageList(@RequestBody @Valid ApsGoodsForecastMakeSaleDataExportQueryPageListReq req);

  /**
   * 导出
   */
  @PostMapping("/apsGoodsForecastMakeSaleData/exportQueryPageList")
  void queryPageListExport(@RequestBody @Valid ApsGoodsForecastMakeSaleDataExportQueryPageListReq req);

  /**
   * 导入
   */
  @PostMapping("/apsGoodsForecastMakeSaleData/importData")
  ApsGoodsForecastMakeSaleDataImportRes importData(@RequestParam("file") MultipartFile file);


  /**
   * 根据ID 批量查询
   */
  @PostMapping("/apsGoodsForecastMakeSaleData/queryByIdList")
  ApsGoodsForecastMakeSaleDataQueryByIdListRes queryByIdListRes(@RequestBody @Valid ApsGoodsForecastMakeSaleDataQueryByIdListReq req);


}
