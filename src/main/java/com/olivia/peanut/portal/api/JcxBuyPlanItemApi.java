package com.olivia.peanut.portal.api;


import com.olivia.peanut.portal.api.entity.jcxBuyPlanItem.*;
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
 * (JcxBuyPlanItem)对外API
 *
 * @author peanut
 * @since 2024-03-24 20:27:11
 */
// @FeignClient(value = "",contextId = "jcxBuyPlanItem-api",url = "${ portal..center.endpoint:}")
public interface JcxBuyPlanItemApi {

  /**
   * 保存
   */
  @PostMapping("/jcxBuyPlanItem/insert")
  JcxBuyPlanItemInsertRes insert(@RequestBody @Validated(InsertCheck.class) JcxBuyPlanItemInsertReq req);

  /**
   * 根据ID 删除
   */
  @PostMapping("/jcxBuyPlanItem/deleteByIdList")
  JcxBuyPlanItemDeleteByIdListRes deleteByIdList(@RequestBody @Valid JcxBuyPlanItemDeleteByIdListReq req);

  /**
   * 查询
   */
  @PostMapping("/jcxBuyPlanItem/queryList")
  JcxBuyPlanItemQueryListRes queryList(@RequestBody @Valid JcxBuyPlanItemQueryListReq req);

  /**
   * 根据ID 更新
   */
  @PostMapping("/jcxBuyPlanItem/updateById")
  JcxBuyPlanItemUpdateByIdRes updateById(@RequestBody @Validated(UpdateCheck.class) JcxBuyPlanItemUpdateByIdReq req);

  /**
   * 分页查询
   */
  @PostMapping("/jcxBuyPlanItem/queryPageList")
  DynamicsPage<JcxBuyPlanItemExportQueryPageListInfoRes> queryPageList(@RequestBody @Valid JcxBuyPlanItemExportQueryPageListReq req);

  /**
   * 导出
   */
  @PostMapping("/jcxBuyPlanItem/exportQueryPageList")
  void queryPageListExport(@RequestBody @Valid JcxBuyPlanItemExportQueryPageListReq req);

  /**
   * 导入
   */
  @PostMapping("/jcxBuyPlanItem/importData")
  JcxBuyPlanItemImportRes importData(@RequestParam("file") MultipartFile file);


  /**
   * 根据ID 批量查询
   */
  @PostMapping("/jcxBuyPlanItem/queryByIdList")
  JcxBuyPlanItemQueryByIdListRes queryByIdListRes(@RequestBody @Valid JcxBuyPlanItemQueryByIdListReq req);


}
