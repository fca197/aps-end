package com.olivia.peanut.aps.api;


import com.olivia.peanut.aps.api.entity.apsGoodsForecastMakeProjectData.*;
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
 * (ApsGoodsForecastMakeProjectData)对外API
 *
 * @author peanut
 * @since 2024-05-10 13:58:07
 */
// @FeignClient(value = "",contextId = "apsGoodsForecastMakeProjectData-api",url = "${ portal..center.endpoint:}")
public interface ApsGoodsForecastMakeProjectDataApi {

  /**
   * 保存
   */
  @PostMapping("/apsGoodsForecastMakeProjectData/insert")
  ApsGoodsForecastMakeProjectDataInsertRes insert(@RequestBody @Validated(InsertCheck.class) ApsGoodsForecastMakeProjectDataInsertReq req);

  /**
   * 根据ID 删除
   */
  @PostMapping("/apsGoodsForecastMakeProjectData/deleteByIdList")
  ApsGoodsForecastMakeProjectDataDeleteByIdListRes deleteByIdList(@RequestBody @Valid ApsGoodsForecastMakeProjectDataDeleteByIdListReq req);

  /**
   * 查询
   */
  @PostMapping("/apsGoodsForecastMakeProjectData/queryList")
  ApsGoodsForecastMakeProjectDataQueryListRes queryList(@RequestBody @Valid ApsGoodsForecastMakeProjectDataQueryListReq req);

  /**
   * 根据ID 更新
   */
  @PostMapping("/apsGoodsForecastMakeProjectData/updateById")
  ApsGoodsForecastMakeProjectDataUpdateByIdRes updateById(@RequestBody @Validated(UpdateCheck.class) ApsGoodsForecastMakeProjectDataUpdateByIdReq req);

  /**
   * 分页查询
   */
  @PostMapping("/apsGoodsForecastMakeProjectData/queryPageList")
  DynamicsPage<ApsGoodsForecastMakeProjectDataExportQueryPageListInfoRes> queryPageList(@RequestBody @Valid ApsGoodsForecastMakeProjectDataExportQueryPageListReq req);

  /**
   * 导出
   */
  @PostMapping("/apsGoodsForecastMakeProjectData/exportQueryPageList")
  void queryPageListExport(@RequestBody @Valid ApsGoodsForecastMakeProjectDataExportQueryPageListReq req);

  /**
   * 导入
   */
  @PostMapping("/apsGoodsForecastMakeProjectData/importData")
  ApsGoodsForecastMakeProjectDataImportRes importData(@RequestParam("file") MultipartFile file);


  /**
   * 根据ID 批量查询
   */
  @PostMapping("/apsGoodsForecastMakeProjectData/queryByIdList")
  ApsGoodsForecastMakeProjectDataQueryByIdListRes queryByIdListRes(@RequestBody @Valid ApsGoodsForecastMakeProjectDataQueryByIdListReq req);


}
