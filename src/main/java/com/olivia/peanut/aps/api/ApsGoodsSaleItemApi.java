package com.olivia.peanut.aps.api;


import com.olivia.peanut.aps.api.entity.apsGoodsSaleItem.*;
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
 * (ApsGoodsSaleItem)对外API
 *
 * @author peanut
 * @since 2024-03-30 10:38:35
 */
// @FeignClient(value = "",contextId = "apsGoodsSaleItem-api",url = "${ portal..center.endpoint:}")
public interface ApsGoodsSaleItemApi {

  /**
   * 保存
   */
  @PostMapping("/apsGoodsSaleItem/insert")
  ApsGoodsSaleItemInsertRes insert(@RequestBody @Validated(InsertCheck.class) ApsGoodsSaleItemInsertReq req);

  /**
   * 根据ID 删除
   */
  @PostMapping("/apsGoodsSaleItem/deleteByIdList")
  ApsGoodsSaleItemDeleteByIdListRes deleteByIdList(@RequestBody @Valid ApsGoodsSaleItemDeleteByIdListReq req);

  /**
   * 查询
   */
  @PostMapping("/apsGoodsSaleItem/queryList")
  ApsGoodsSaleItemQueryListRes queryList(@RequestBody @Valid ApsGoodsSaleItemQueryListReq req);

  /**
   * 根据ID 更新
   */
  @PostMapping("/apsGoodsSaleItem/updateById")
  ApsGoodsSaleItemUpdateByIdRes updateById(@RequestBody @Validated(UpdateCheck.class) ApsGoodsSaleItemUpdateByIdReq req);

  /**
   * 分页查询
   */
  @PostMapping("/apsGoodsSaleItem/queryPageList")
  DynamicsPage<ApsGoodsSaleItemExportQueryPageListInfoRes> queryPageList(@RequestBody @Valid ApsGoodsSaleItemExportQueryPageListReq req);

  /**
   * 导出
   */
  @PostMapping("/apsGoodsSaleItem/exportQueryPageList")
  void queryPageListExport(@RequestBody @Valid ApsGoodsSaleItemExportQueryPageListReq req);

  /**
   * 导入
   */
  @PostMapping("/apsGoodsSaleItem/importData")
  ApsGoodsSaleItemImportRes importData(@RequestParam("file") MultipartFile file);


  /**
   * 根据ID 批量查询
   */
  @PostMapping("/apsGoodsSaleItem/queryByIdList")
  ApsGoodsSaleItemQueryByIdListRes queryByIdListRes(@RequestBody @Valid ApsGoodsSaleItemQueryByIdListReq req);

  @PostMapping("/apsGoodsSaleItem/updateSaleConfig")
  UpdateSaleConfigRes updateSaleConfig(@RequestBody @Valid UpdateSaleConfigReq req);

  @PostMapping("/apsGoodsSaleItem/updateForecast")
  UpdateForecastRes updateForecast(@RequestBody @Valid UpdateForecastReq req);

}
