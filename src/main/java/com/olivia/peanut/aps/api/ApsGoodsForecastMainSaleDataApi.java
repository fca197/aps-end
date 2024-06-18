package com.olivia.peanut.aps.api;


import com.olivia.peanut.aps.api.entity.apsGoodsForecastMainGoodsData.GetDataByGoodsIdReq;
import com.olivia.peanut.aps.api.entity.apsGoodsForecastMainGoodsData.GetDataByGoodsIdRes;
import com.olivia.peanut.aps.api.entity.apsGoodsForecastMainSaleData.*;
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
 * (ApsGoodsForecastMainSaleData)对外API
 *
 * @author peanut
 * @since 2024-04-02 09:42:27
 */
// @FeignClient(value = "",contextId = "apsGoodsForecastMainSaleData-api",url = "${ portal..center.endpoint:}")
public interface ApsGoodsForecastMainSaleDataApi {

  /**
   * 保存
   */
  @PostMapping("/apsGoodsForecastMainSaleData/insert")
  ApsGoodsForecastMainSaleDataInsertRes insert(@RequestBody @Validated(InsertCheck.class) ApsGoodsForecastMainSaleDataInsertReq req);

  /**
   * 根据ID 删除
   */
  @PostMapping("/apsGoodsForecastMainSaleData/deleteByIdList")
  ApsGoodsForecastMainSaleDataDeleteByIdListRes deleteByIdList(@RequestBody @Valid ApsGoodsForecastMainSaleDataDeleteByIdListReq req);

  /**
   * 查询
   */
  @PostMapping("/apsGoodsForecastMainSaleData/queryList")
  ApsGoodsForecastMainSaleDataQueryListRes queryList(@RequestBody @Valid ApsGoodsForecastMainSaleDataQueryListReq req);

  /**
   * 根据ID 更新
   */
  @PostMapping("/apsGoodsForecastMainSaleData/updateById")
  ApsGoodsForecastMainSaleDataUpdateByIdRes updateById(@RequestBody @Validated(UpdateCheck.class) ApsGoodsForecastMainSaleDataUpdateByIdReq req);

  /**
   * 分页查询
   */
  @PostMapping("/apsGoodsForecastMainSaleData/queryPageList")
  DynamicsPage<ApsGoodsForecastMainSaleDataExportQueryPageListInfoRes> queryPageList(@RequestBody @Valid ApsGoodsForecastMainSaleDataExportQueryPageListReq req);

  /**
   * 导出
   */
  @PostMapping("/apsGoodsForecastMainSaleData/exportQueryPageList")
  void queryPageListExport(@RequestBody @Valid ApsGoodsForecastMainSaleDataExportQueryPageListReq req);

  /**
   * 导入
   */
  @PostMapping("/apsGoodsForecastMainSaleData/importData")
  ApsGoodsForecastMainSaleDataImportRes importData(@RequestParam("file") MultipartFile file);


  /**
   * 根据ID 批量查询
   */
  @PostMapping("/apsGoodsForecastMainSaleData/queryByIdList")
  ApsGoodsForecastMainSaleDataQueryByIdListRes queryByIdListRes(@RequestBody @Valid ApsGoodsForecastMainSaleDataQueryByIdListReq req);


  @PostMapping("/apsGoodsForecastMainSaleData/getDataByGoodsId")
  DynamicsPage<GetDataByGoodsIdRes> getDataByGoodsId(@RequestBody @Valid GetDataByGoodsIdReq req);
}
