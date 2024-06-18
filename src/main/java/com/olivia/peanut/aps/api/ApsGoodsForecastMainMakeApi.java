package com.olivia.peanut.aps.api;


import com.olivia.peanut.aps.api.entity.apsGoodsForecastMainMake.*;
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
 * (ApsGoodsForecastMainMake)对外API
 *
 * @author peanut
 * @since 2024-04-08 09:52:49
 */
// @FeignClient(value = "",contextId = "apsGoodsForecastMainMake-api",url = "${ portal..center.endpoint:}")
public interface ApsGoodsForecastMainMakeApi {

  /**
   * 保存
   */
  @PostMapping("/apsGoodsForecastMainMake/insert")
  ApsGoodsForecastMainMakeInsertRes insert(@RequestBody @Validated(InsertCheck.class) ApsGoodsForecastMainMakeInsertReq req);

  /**
   * 根据ID 删除
   */
  @PostMapping("/apsGoodsForecastMainMake/deleteByIdList")
  ApsGoodsForecastMainMakeDeleteByIdListRes deleteByIdList(@RequestBody @Valid ApsGoodsForecastMainMakeDeleteByIdListReq req);

  /**
   * 查询
   */
  @PostMapping("/apsGoodsForecastMainMake/queryList")
  ApsGoodsForecastMainMakeQueryListRes queryList(@RequestBody @Valid ApsGoodsForecastMainMakeQueryListReq req);

  /**
   * 根据ID 更新
   */
  @PostMapping("/apsGoodsForecastMainMake/updateById")
  ApsGoodsForecastMainMakeUpdateByIdRes updateById(@RequestBody @Validated(UpdateCheck.class) ApsGoodsForecastMainMakeUpdateByIdReq req);

  /**
   * 分页查询
   */
  @PostMapping("/apsGoodsForecastMainMake/queryPageList")
  DynamicsPage<ApsGoodsForecastMainMakeExportQueryPageListInfoRes> queryPageList(@RequestBody @Valid ApsGoodsForecastMainMakeExportQueryPageListReq req);

  /**
   * 导出
   */
  @PostMapping("/apsGoodsForecastMainMake/exportQueryPageList")
  void queryPageListExport(@RequestBody @Valid ApsGoodsForecastMainMakeExportQueryPageListReq req);

  /**
   * 导入
   */
  @PostMapping("/apsGoodsForecastMainMake/importData")
  ApsGoodsForecastMainMakeImportRes importData(@RequestParam("file") MultipartFile file);


  /**
   * 根据ID 批量查询
   */
  @PostMapping("/apsGoodsForecastMainMake/queryByIdList")
  ApsGoodsForecastMainMakeQueryByIdListRes queryByIdListRes(@RequestBody @Valid ApsGoodsForecastMainMakeQueryByIdListReq req);

  @PostMapping("/apsGoodsForecastMainMake/queryDataById")
  DynamicsPage<ApsGoodsForecastMainMakeQueryDataByIdRes> queryDataById(@RequestBody @Valid ApsGoodsForecastMainMakeQueryDataByIdReq req);

}
