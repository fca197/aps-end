package com.olivia.peanut.aps.api;


import com.olivia.peanut.aps.api.entity.apsGoodsForecastUserSaleData.*;
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
 * (ApsGoodsForecastUserSaleData)对外API
 *
 * @author peanut
 * @since 2024-03-30 18:29:07
 */
// @FeignClient(value = "",contextId = "apsGoodsForecastUserSaleData-api",url = "${ portal..center.endpoint:}")
public interface ApsGoodsForecastUserSaleDataApi {

  /**
   * 保存
   */
  @PostMapping("/apsGoodsForecastUserSaleData/insert")
  ApsGoodsForecastUserSaleDataInsertRes insert(@RequestBody @Validated(InsertCheck.class) ApsGoodsForecastUserSaleDataInsertReq req);

  /**
   * 根据ID 删除
   */
  @PostMapping("/apsGoodsForecastUserSaleData/deleteByIdList")
  ApsGoodsForecastUserSaleDataDeleteByIdListRes deleteByIdList(@RequestBody @Valid ApsGoodsForecastUserSaleDataDeleteByIdListReq req);

  /**
   * 查询
   */
  @PostMapping("/apsGoodsForecastUserSaleData/queryList")
  ApsGoodsForecastUserSaleDataQueryListRes queryList(@RequestBody @Valid ApsGoodsForecastUserSaleDataQueryListReq req);

  /**
   * 根据ID 更新
   */
  @PostMapping("/apsGoodsForecastUserSaleData/updateById")
  ApsGoodsForecastUserSaleDataUpdateByIdRes updateById(@RequestBody @Validated(UpdateCheck.class) ApsGoodsForecastUserSaleDataUpdateByIdReq req);

  /**
   * 分页查询
   */
  @PostMapping("/apsGoodsForecastUserSaleData/queryPageList")
  DynamicsPage<ApsGoodsForecastUserSaleDataExportQueryPageListInfoRes> queryPageList(@RequestBody @Valid ApsGoodsForecastUserSaleDataExportQueryPageListReq req);

  /**
   * 导出
   */
  @PostMapping("/apsGoodsForecastUserSaleData/exportQueryPageList")
  void queryPageListExport(@RequestBody @Valid ApsGoodsForecastUserSaleDataExportQueryPageListReq req);

  /**
   * 导入
   */
  @PostMapping("/apsGoodsForecastUserSaleData/importData")
  ApsGoodsForecastUserSaleDataImportRes importData(@RequestParam("file") MultipartFile file);


  /**
   * 根据ID 批量查询
   */
  @PostMapping("/apsGoodsForecastUserSaleData/queryByIdList")
  ApsGoodsForecastUserSaleDataQueryByIdListRes queryByIdListRes(@RequestBody @Valid ApsGoodsForecastUserSaleDataQueryByIdListReq req);


}
