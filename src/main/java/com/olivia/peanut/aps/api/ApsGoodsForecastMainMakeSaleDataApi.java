package com.olivia.peanut.aps.api;


import com.olivia.peanut.aps.api.entity.apsGoodsForecastMainMakeSaleData.*;
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
 * (ApsGoodsForecastMainMakeSaleData)对外API
 *
 * @author peanut
 * @since 2024-04-08 09:52:50
 */
// @FeignClient(value = "",contextId = "apsGoodsForecastMainMakeSaleData-api",url = "${ portal..center.endpoint:}")
public interface ApsGoodsForecastMainMakeSaleDataApi {

  /**
   * 保存
   */
  @PostMapping("/apsGoodsForecastMainMakeSaleData/insert")
  ApsGoodsForecastMainMakeSaleDataInsertRes insert(@RequestBody @Validated(InsertCheck.class) ApsGoodsForecastMainMakeSaleDataInsertReq req);

  /**
   * 根据ID 删除
   */
  @PostMapping("/apsGoodsForecastMainMakeSaleData/deleteByIdList")
  ApsGoodsForecastMainMakeSaleDataDeleteByIdListRes deleteByIdList(@RequestBody @Valid ApsGoodsForecastMainMakeSaleDataDeleteByIdListReq req);

  /**
   * 查询
   */
  @PostMapping("/apsGoodsForecastMainMakeSaleData/queryList")
  ApsGoodsForecastMainMakeSaleDataQueryListRes queryList(@RequestBody @Valid ApsGoodsForecastMainMakeSaleDataQueryListReq req);

  /**
   * 根据ID 更新
   */
  @PostMapping("/apsGoodsForecastMainMakeSaleData/updateById")
  ApsGoodsForecastMainMakeSaleDataUpdateByIdRes updateById(@RequestBody @Validated(UpdateCheck.class) ApsGoodsForecastMainMakeSaleDataUpdateByIdReq req);

  /**
   * 分页查询
   */
  @PostMapping("/apsGoodsForecastMainMakeSaleData/queryPageList")
  DynamicsPage<ApsGoodsForecastMainMakeSaleDataExportQueryPageListInfoRes> queryPageList(@RequestBody @Valid ApsGoodsForecastMainMakeSaleDataExportQueryPageListReq req);

  /**
   * 导出
   */
  @PostMapping("/apsGoodsForecastMainMakeSaleData/exportQueryPageList")
  void queryPageListExport(@RequestBody @Valid ApsGoodsForecastMainMakeSaleDataExportQueryPageListReq req);

  /**
   * 导入
   */
  @PostMapping("/apsGoodsForecastMainMakeSaleData/importData")
  ApsGoodsForecastMainMakeSaleDataImportRes importData(@RequestParam("file") MultipartFile file);


  /**
   * 根据ID 批量查询
   */
  @PostMapping("/apsGoodsForecastMainMakeSaleData/queryByIdList")
  ApsGoodsForecastMainMakeSaleDataQueryByIdListRes queryByIdListRes(@RequestBody @Valid ApsGoodsForecastMainMakeSaleDataQueryByIdListReq req);


}
