package com.olivia.peanut.aps.api;


import com.olivia.peanut.aps.api.entity.apsGoodsForecastUserGoodsData.*;
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
 * (ApsGoodsForecastUserGoodsData)对外API
 *
 * @author peanut
 * @since 2024-03-30 18:29:06
 */
// @FeignClient(value = "",contextId = "apsGoodsForecastUserGoodsData-api",url = "${ portal..center.endpoint:}")
public interface ApsGoodsForecastUserGoodsDataApi {

  /**
   * 保存
   */
  @PostMapping("/apsGoodsForecastUserGoodsData/insert")
  ApsGoodsForecastUserGoodsDataInsertRes insert(@RequestBody @Validated(InsertCheck.class) ApsGoodsForecastUserGoodsDataInsertReq req);

  /**
   * 根据ID 删除
   */
  @PostMapping("/apsGoodsForecastUserGoodsData/deleteByIdList")
  ApsGoodsForecastUserGoodsDataDeleteByIdListRes deleteByIdList(@RequestBody @Valid ApsGoodsForecastUserGoodsDataDeleteByIdListReq req);

  /**
   * 查询
   */
  @PostMapping("/apsGoodsForecastUserGoodsData/queryList")
  ApsGoodsForecastUserGoodsDataQueryListRes queryList(@RequestBody @Valid ApsGoodsForecastUserGoodsDataQueryListReq req);

  /**
   * 根据ID 更新
   */
  @PostMapping("/apsGoodsForecastUserGoodsData/updateById")
  ApsGoodsForecastUserGoodsDataUpdateByIdRes updateById(@RequestBody @Validated(UpdateCheck.class) ApsGoodsForecastUserGoodsDataUpdateByIdReq req);

  /**
   * 分页查询
   */
  @PostMapping("/apsGoodsForecastUserGoodsData/queryPageList")
  DynamicsPage<ApsGoodsForecastUserGoodsDataExportQueryPageListInfoRes> queryPageList(@RequestBody @Valid ApsGoodsForecastUserGoodsDataExportQueryPageListReq req);

  /**
   * 导出
   */
  @PostMapping("/apsGoodsForecastUserGoodsData/exportQueryPageList")
  void queryPageListExport(@RequestBody @Valid ApsGoodsForecastUserGoodsDataExportQueryPageListReq req);

  /**
   * 导入
   */
  @PostMapping("/apsGoodsForecastUserGoodsData/importData")
  ApsGoodsForecastUserGoodsDataImportRes importData(@RequestParam("file") MultipartFile file);


  /**
   * 根据ID 批量查询
   */
  @PostMapping("/apsGoodsForecastUserGoodsData/queryByIdList")
  ApsGoodsForecastUserGoodsDataQueryByIdListRes queryByIdListRes(@RequestBody @Valid ApsGoodsForecastUserGoodsDataQueryByIdListReq req);


}
