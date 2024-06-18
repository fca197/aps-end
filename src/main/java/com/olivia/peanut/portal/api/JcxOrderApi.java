package com.olivia.peanut.portal.api;


import com.olivia.peanut.portal.api.entity.jcxOrder.*;
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
 * (JcxOrder)对外API
 *
 * @author peanut
 * @since 2024-03-22 10:38:05
 */
// @FeignClient(value = "",contextId = "jcxOrder-api",url = "${ portal..center.endpoint:}")
public interface JcxOrderApi {

  /**
   * 保存
   */
  @PostMapping("/jcx/order/insert")
  JcxOrderInsertRes insert(@RequestBody @Validated(InsertCheck.class) JcxOrderInsertReq req);

  /**
   * 根据ID 删除
   */
  @PostMapping("/jcx/order/deleteByIdList")
  JcxOrderDeleteByIdListRes deleteByIdList(@RequestBody @Valid JcxOrderDeleteByIdListReq req);

  /**
   * 查询
   */
  @PostMapping("/jcx/order/queryList")
  JcxOrderQueryListRes queryList(@RequestBody @Valid JcxOrderQueryListReq req);

  /**
   * 根据ID 更新
   */
  @PostMapping("/jcx/order/updateById")
  JcxOrderUpdateByIdRes updateById(@RequestBody @Validated(UpdateCheck.class) JcxOrderUpdateByIdReq req);

  /**
   * 分页查询
   */
  @PostMapping("/jcx/order/queryPageList")
  DynamicsPage<JcxOrderExportQueryPageListInfoRes> queryPageList(@RequestBody @Valid JcxOrderExportQueryPageListReq req);

  /**
   * 导出
   */
  @PostMapping("/jcx/order/exportQueryPageList")
  void queryPageListExport(@RequestBody @Valid JcxOrderExportQueryPageListReq req);

  /**
   * 导入
   */
  @PostMapping("/jcx/order/importData")
  JcxOrderImportRes importData(@RequestParam("file") MultipartFile file);


  /**
   * 根据ID 批量查询
   */
  @PostMapping("/jcx/order/queryByIdList")
  JcxOrderQueryByIdListRes queryByIdListRes(@RequestBody @Valid JcxOrderQueryByIdListReq req);


  @PostMapping("/jcx/order/orderStatus")
  GetOrderStatusRes getOrderStatus(@RequestBody @Valid GetOrderStatusReq req);
}
