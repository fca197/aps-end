package com.olivia.peanut.aps.api;


import com.olivia.peanut.aps.api.entity.apsGoodsForecastMain.*;
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
 * (ApsGoodsForecastMain)对外API
 *
 * @author peanut
 * @since 2024-04-02 09:42:26
 */
// @FeignClient(value = "",contextId = "apsGoodsForecastMain-api",url = "${ portal..center.endpoint:}")
public interface ApsGoodsForecastMainApi {

  /**
   * 保存
   */
  @PostMapping("/apsGoodsForecastMain/insert")
  ApsGoodsForecastMainInsertRes insert(@RequestBody @Validated(InsertCheck.class) ApsGoodsForecastMainInsertReq req);

  /**
   * 根据ID 删除
   */
  @PostMapping("/apsGoodsForecastMain/deleteByIdList")
  ApsGoodsForecastMainDeleteByIdListRes deleteByIdList(@RequestBody @Valid ApsGoodsForecastMainDeleteByIdListReq req);

  /**
   * 查询
   */
  @PostMapping("/apsGoodsForecastMain/queryList")
  ApsGoodsForecastMainQueryListRes queryList(@RequestBody @Valid ApsGoodsForecastMainQueryListReq req);

  /**
   * 根据ID 更新
   */
  @PostMapping("/apsGoodsForecastMain/updateById")
  ApsGoodsForecastMainUpdateByIdRes updateById(@RequestBody @Validated(UpdateCheck.class) ApsGoodsForecastMainUpdateByIdReq req);

  /**
   * 分页查询
   */
  @PostMapping("/apsGoodsForecastMain/queryPageList")
  DynamicsPage<ApsGoodsForecastMainExportQueryPageListInfoRes> queryPageList(@RequestBody @Valid ApsGoodsForecastMainExportQueryPageListReq req);

  /**
   * 导出
   */
  @PostMapping("/apsGoodsForecastMain/exportQueryPageList")
  void queryPageListExport(@RequestBody @Valid ApsGoodsForecastMainExportQueryPageListReq req);

  /**
   * 导入
   */
  @PostMapping("/apsGoodsForecastMain/importData")
  ApsGoodsForecastMainImportRes importData(@RequestParam("file") MultipartFile file);


  /**
   * 根据ID 批量查询
   */
  @PostMapping("/apsGoodsForecastMain/queryByIdList")
  ApsGoodsForecastMainQueryByIdListRes queryByIdListRes(@RequestBody @Valid ApsGoodsForecastMainQueryByIdListReq req);

}
