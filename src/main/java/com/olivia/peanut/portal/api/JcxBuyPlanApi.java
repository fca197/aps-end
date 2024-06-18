package com.olivia.peanut.portal.api;


import com.olivia.peanut.portal.api.entity.jcxBuyPlan.*;
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
 * (JcxBuyPlan)对外API
 *
 * @author peanut
 * @since 2024-03-24 20:27:10
 */
// @FeignClient(value = "",contextId = "jcxBuyPlan-api",url = "${ portal..center.endpoint:}")
public interface JcxBuyPlanApi {

  /**
   * 保存
   */
  @PostMapping("/jcxBuyPlan/insert")
  JcxBuyPlanInsertRes insert(@RequestBody @Validated(InsertCheck.class) JcxBuyPlanInsertReq req);

  /**
   * 根据ID 删除
   */
  @PostMapping("/jcxBuyPlan/deleteByIdList")
  JcxBuyPlanDeleteByIdListRes deleteByIdList(@RequestBody @Valid JcxBuyPlanDeleteByIdListReq req);

  /**
   * 查询
   */
  @PostMapping("/jcxBuyPlan/queryList")
  JcxBuyPlanQueryListRes queryList(@RequestBody @Valid JcxBuyPlanQueryListReq req);

  /**
   * 根据ID 更新
   */
  @PostMapping("/jcxBuyPlan/updateById")
  JcxBuyPlanUpdateByIdRes updateById(@RequestBody @Validated(UpdateCheck.class) JcxBuyPlanUpdateByIdReq req);

  /**
   * 分页查询
   */
  @PostMapping("/jcxBuyPlan/queryPageList")
  DynamicsPage<JcxBuyPlanExportQueryPageListInfoRes> queryPageList(@RequestBody @Valid JcxBuyPlanExportQueryPageListReq req);

  /**
   * 导出
   */
  @PostMapping("/jcxBuyPlan/exportQueryPageList")
  void queryPageListExport(@RequestBody @Valid JcxBuyPlanExportQueryPageListReq req);

  /**
   * 导入
   */
  @PostMapping("/jcxBuyPlan/importData")
  JcxBuyPlanImportRes importData(@RequestParam("file") MultipartFile file);


  /**
   * 根据ID 批量查询
   */
  @PostMapping("/jcxBuyPlan/queryByIdList")
  JcxBuyPlanQueryByIdListRes queryByIdListRes(@RequestBody @Valid JcxBuyPlanQueryByIdListReq req);

  @PostMapping("/jcxBuyPlan/updateStatus")
  JcxBuyPlanUpdateStatusRes updateStatus(@RequestBody @Valid JcxBuyPlanUpdateStatusReq req);

}
