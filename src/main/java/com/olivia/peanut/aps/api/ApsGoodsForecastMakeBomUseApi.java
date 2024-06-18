package com.olivia.peanut.aps.api;


import com.olivia.peanut.aps.api.entity.apsGoodsForecastMakeBomUse.*;
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
 * (ApsGoodsForecastMakeBomUse)对外API
 *
 * @author peanut
 * @since 2024-05-15 10:26:03
 */
// @FeignClient(value = "",contextId = "apsGoodsForecastMakeBomUse-api",url = "${ portal..center.endpoint:}")
public interface ApsGoodsForecastMakeBomUseApi {

  /**
   * 保存
   */
  @PostMapping("/apsGoodsForecastMakeBomUse/insert")
  ApsGoodsForecastMakeBomUseInsertRes insert(@RequestBody @Validated(InsertCheck.class) ApsGoodsForecastMakeBomUseInsertReq req);

  /**
   * 根据ID 删除
   */
  @PostMapping("/apsGoodsForecastMakeBomUse/deleteByIdList")
  ApsGoodsForecastMakeBomUseDeleteByIdListRes deleteByIdList(@RequestBody @Valid ApsGoodsForecastMakeBomUseDeleteByIdListReq req);

  /**
   * 查询
   */
  @PostMapping("/apsGoodsForecastMakeBomUse/queryList")
  ApsGoodsForecastMakeBomUseQueryListRes queryList(@RequestBody @Valid ApsGoodsForecastMakeBomUseQueryListReq req);

  /**
   * 根据ID 更新
   */
  @PostMapping("/apsGoodsForecastMakeBomUse/updateById")
  ApsGoodsForecastMakeBomUseUpdateByIdRes updateById(@RequestBody @Validated(UpdateCheck.class) ApsGoodsForecastMakeBomUseUpdateByIdReq req);

  /**
   * 分页查询
   */
  @PostMapping("/apsGoodsForecastMakeBomUse/queryPageList")
  DynamicsPage<ApsGoodsForecastMakeBomUseExportQueryPageListInfoRes> queryPageList(@RequestBody @Valid ApsGoodsForecastMakeBomUseExportQueryPageListReq req);

  /**
   * 导出
   */
  @PostMapping("/apsGoodsForecastMakeBomUse/exportQueryPageList")
  void queryPageListExport(@RequestBody @Valid ApsGoodsForecastMakeBomUseExportQueryPageListReq req);

  /**
   * 导入
   */
  @PostMapping("/apsGoodsForecastMakeBomUse/importData")
  ApsGoodsForecastMakeBomUseImportRes importData(@RequestParam("file") MultipartFile file);


  /**
   * 根据ID 批量查询
   */
  @PostMapping("/apsGoodsForecastMakeBomUse/queryByIdList")
  ApsGoodsForecastMakeBomUseQueryByIdListRes queryByIdListRes(@RequestBody @Valid ApsGoodsForecastMakeBomUseQueryByIdListReq req);


}
