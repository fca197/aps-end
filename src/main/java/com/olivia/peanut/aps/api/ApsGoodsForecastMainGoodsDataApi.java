package com.olivia.peanut.aps.api;


import com.olivia.peanut.aps.api.entity.apsGoodsForecastMainGoodsData.*;
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
 * (ApsGoodsForecastMainGoodsData)对外API
 *
 * @author peanut
 * @since 2024-04-02 13:44:28
 */
// @FeignClient(value = "",contextId = "apsGoodsForecastMainGoodsData-api",url = "${ portal..center.endpoint:}")
public interface ApsGoodsForecastMainGoodsDataApi {

  /**
   * 保存
   */
  @PostMapping("/apsGoodsForecastMainGoodsData/insert")
  ApsGoodsForecastMainGoodsDataInsertRes insert(@RequestBody @Validated(InsertCheck.class) ApsGoodsForecastMainGoodsDataInsertReq req);

  /**
   * 根据ID 删除
   */
  @PostMapping("/apsGoodsForecastMainGoodsData/deleteByIdList")
  ApsGoodsForecastMainGoodsDataDeleteByIdListRes deleteByIdList(@RequestBody @Valid ApsGoodsForecastMainGoodsDataDeleteByIdListReq req);

  /**
   * 查询
   */
  @PostMapping("/apsGoodsForecastMainGoodsData/queryList")
  ApsGoodsForecastMainGoodsDataQueryListRes queryList(@RequestBody @Valid ApsGoodsForecastMainGoodsDataQueryListReq req);

  /**
   * 根据ID 更新
   */
  @PostMapping("/apsGoodsForecastMainGoodsData/updateById")
  ApsGoodsForecastMainGoodsDataUpdateByIdRes updateById(@RequestBody @Validated(UpdateCheck.class) ApsGoodsForecastMainGoodsDataUpdateByIdReq req);

  /**
   * 分页查询
   */
  @PostMapping("/apsGoodsForecastMainGoodsData/queryPageList")
  DynamicsPage<ApsGoodsForecastMainGoodsDataExportQueryPageListInfoRes> queryPageList(@RequestBody @Valid ApsGoodsForecastMainGoodsDataExportQueryPageListReq req);

  /**
   * 导出
   */
  @PostMapping("/apsGoodsForecastMainGoodsData/exportQueryPageList")
  void queryPageListExport(@RequestBody @Valid ApsGoodsForecastMainGoodsDataExportQueryPageListReq req);

  /**
   * 导入
   */
  @PostMapping("/apsGoodsForecastMainGoodsData/importData")
  ApsGoodsForecastMainGoodsDataImportRes importData(@RequestParam("file") MultipartFile file);


  /**
   * 根据ID 批量查询
   */
  @PostMapping("/apsGoodsForecastMainGoodsData/queryByIdList")
  ApsGoodsForecastMainGoodsDataQueryByIdListRes queryByIdListRes(@RequestBody @Valid ApsGoodsForecastMainGoodsDataQueryByIdListReq req);


}
