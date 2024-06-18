package com.olivia.peanut.portal.api;


import com.olivia.peanut.portal.api.entity.jcxBuyOrderItem.*;
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
 * (JcxBuyOrderItem)对外API
 *
 * @author peanut
 * @since 2024-03-27 13:51:37
 */
// @FeignClient(value = "",contextId = "jcxBuyOrderItem-api",url = "${ portal..center.endpoint:}")
public interface JcxBuyOrderItemApi {

  /**
   * 保存
   */
  @PostMapping("/jcxBuyOrderItem/insert")
  JcxBuyOrderItemInsertRes insert(@RequestBody @Validated(InsertCheck.class) JcxBuyOrderItemInsertReq req);

  /**
   * 根据ID 删除
   */
  @PostMapping("/jcxBuyOrderItem/deleteByIdList")
  JcxBuyOrderItemDeleteByIdListRes deleteByIdList(@RequestBody @Valid JcxBuyOrderItemDeleteByIdListReq req);

  /**
   * 查询
   */
  @PostMapping("/jcxBuyOrderItem/queryList")
  JcxBuyOrderItemQueryListRes queryList(@RequestBody @Valid JcxBuyOrderItemQueryListReq req);

  /**
   * 根据ID 更新
   */
  @PostMapping("/jcxBuyOrderItem/updateById")
  JcxBuyOrderItemUpdateByIdRes updateById(@RequestBody @Validated(UpdateCheck.class) JcxBuyOrderItemUpdateByIdReq req);

  /**
   * 分页查询
   */
  @PostMapping("/jcxBuyOrderItem/queryPageList")
  DynamicsPage<JcxBuyOrderItemExportQueryPageListInfoRes> queryPageList(@RequestBody @Valid JcxBuyOrderItemExportQueryPageListReq req);

  /**
   * 导出
   */
  @PostMapping("/jcxBuyOrderItem/exportQueryPageList")
  void queryPageListExport(@RequestBody @Valid JcxBuyOrderItemExportQueryPageListReq req);

  /**
   * 导入
   */
  @PostMapping("/jcxBuyOrderItem/importData")
  JcxBuyOrderItemImportRes importData(@RequestParam("file") MultipartFile file);


  /**
   * 根据ID 批量查询
   */
  @PostMapping("/jcxBuyOrderItem/queryByIdList")
  JcxBuyOrderItemQueryByIdListRes queryByIdListRes(@RequestBody @Valid JcxBuyOrderItemQueryByIdListReq req);


}
