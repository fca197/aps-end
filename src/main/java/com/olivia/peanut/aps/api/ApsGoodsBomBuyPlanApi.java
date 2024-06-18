package com.olivia.peanut.aps.api;

import com.olivia.peanut.aps.api.entity.apsGoodsBomBuyPlan.*;
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
 * BOM 购买计划(ApsGoodsBomBuyPlan)对外API
 *
 * @author peanut
 * @since 2024-06-05 14:35:29
 */
// @FeignClient(value = "",contextId = "apsGoodsBomBuyPlan-api",url = "${ portal..center.endpoint:}")
public interface ApsGoodsBomBuyPlanApi {

  /**
   * 保存 BOM 购买计划
   */
  @PostMapping("/apsGoodsBomBuyPlan/insert")
  ApsGoodsBomBuyPlanInsertRes insert(@RequestBody @Validated(InsertCheck.class) ApsGoodsBomBuyPlanInsertReq req);

  /**
   * 根据ID 删除 BOM 购买计划
   */
  @PostMapping("/apsGoodsBomBuyPlan/deleteByIdList")
  ApsGoodsBomBuyPlanDeleteByIdListRes deleteByIdList(@RequestBody @Valid ApsGoodsBomBuyPlanDeleteByIdListReq req);

  /**
   * 查询 BOM 购买计划
   */
  @PostMapping("/apsGoodsBomBuyPlan/queryList")
  ApsGoodsBomBuyPlanQueryListRes queryList(@RequestBody @Valid ApsGoodsBomBuyPlanQueryListReq req);

  /**
   * 根据ID 更新 BOM 购买计划
   */
  @PostMapping("/apsGoodsBomBuyPlan/updateById")
  ApsGoodsBomBuyPlanUpdateByIdRes updateById(@RequestBody @Validated(UpdateCheck.class) ApsGoodsBomBuyPlanUpdateByIdReq req);

  /**
   * 分页查询 BOM 购买计划
   */
  @PostMapping("/apsGoodsBomBuyPlan/queryPageList")
  DynamicsPage<ApsGoodsBomBuyPlanExportQueryPageListInfoRes> queryPageList(@RequestBody @Valid ApsGoodsBomBuyPlanExportQueryPageListReq req);

  /**
   * 导出 BOM 购买计划
   */
  @PostMapping("/apsGoodsBomBuyPlan/exportQueryPageList")
  void queryPageListExport(@RequestBody @Valid ApsGoodsBomBuyPlanExportQueryPageListReq req);

  /**
   * 导入
   */
  @PostMapping("/apsGoodsBomBuyPlan/importData")
  ApsGoodsBomBuyPlanImportRes importData(@RequestParam("file") MultipartFile file);


  /**
   * 根据ID 批量查询
   */
  @PostMapping("/apsGoodsBomBuyPlan/queryByIdList")
  ApsGoodsBomBuyPlanQueryByIdListRes queryByIdListRes(@RequestBody @Valid ApsGoodsBomBuyPlanQueryByIdListReq req);


}
