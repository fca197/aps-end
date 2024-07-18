package com.olivia.peanut.aps.api;

import com.olivia.peanut.aps.api.entity.apsLogisticsPathItem.*;
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
 * 物流路详情径表(ApsLogisticsPathItem)对外API
 *
 * @author peanut
 * @since 2024-07-18 13:27:39
 */
// @FeignClient(value = "",contextId = "apsLogisticsPathItem-api",url = "${ portal..center.endpoint:}")
public interface ApsLogisticsPathItemApi {

  /**
   * 保存 物流路详情径表
   */
  @PostMapping("/apsLogisticsPathItem/insert")
  ApsLogisticsPathItemInsertRes insert(@RequestBody @Validated(InsertCheck.class) ApsLogisticsPathItemInsertReq req);

  /**
   * 根据ID 删除 物流路详情径表
   */
  @PostMapping("/apsLogisticsPathItem/deleteByIdList")
  ApsLogisticsPathItemDeleteByIdListRes deleteByIdList(@RequestBody @Valid ApsLogisticsPathItemDeleteByIdListReq req);

  /**
   * 查询 物流路详情径表
   */
  @PostMapping("/apsLogisticsPathItem/queryList")
  ApsLogisticsPathItemQueryListRes queryList(@RequestBody @Valid ApsLogisticsPathItemQueryListReq req);

  /**
   * 根据ID 更新 物流路详情径表
   */
  @PostMapping("/apsLogisticsPathItem/updateById")
  ApsLogisticsPathItemUpdateByIdRes updateById(@RequestBody @Validated(UpdateCheck.class) ApsLogisticsPathItemUpdateByIdReq req);

  /**
   * 分页查询 物流路详情径表
   */
  @PostMapping("/apsLogisticsPathItem/queryPageList")
  DynamicsPage<ApsLogisticsPathItemExportQueryPageListInfoRes> queryPageList(@RequestBody @Valid ApsLogisticsPathItemExportQueryPageListReq req);

  /**
   * 导出 物流路详情径表
   */
  @PostMapping("/apsLogisticsPathItem/exportQueryPageList")
  void queryPageListExport(@RequestBody @Valid ApsLogisticsPathItemExportQueryPageListReq req);

  /**
   * 导入
   */
  @PostMapping("/apsLogisticsPathItem/importData")
  ApsLogisticsPathItemImportRes importData(@RequestParam("file") MultipartFile file);


  /**
   * 根据ID 批量查询
   */
  @PostMapping("/apsLogisticsPathItem/queryByIdList")
  ApsLogisticsPathItemQueryByIdListRes queryByIdListRes(@RequestBody @Valid ApsLogisticsPathItemQueryByIdListReq req);


}
