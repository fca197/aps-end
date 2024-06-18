package com.olivia.peanut.portal.api;


import com.olivia.peanut.portal.api.entity.jcxOrderItem.*;
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
 * (JcxOrderItem)对外API
 *
 * @author peanut
 * @since 2024-03-22 10:38:06
 */
// @FeignClient(value = "",contextId = "jcxOrderItem-api",url = "${ portal..center.endpoint:}")
public interface JcxOrderItemApi {

  /**
   * 保存
   */
  @PostMapping("/jcxOrderItem/insert")
  JcxOrderItemInsertRes insert(@RequestBody @Validated(InsertCheck.class) JcxOrderItemInsertReq req);

  /**
   * 根据ID 删除
   */
  @PostMapping("/jcxOrderItem/deleteByIdList")
  JcxOrderItemDeleteByIdListRes deleteByIdList(@RequestBody @Valid JcxOrderItemDeleteByIdListReq req);

  /**
   * 查询
   */
  @PostMapping("/jcxOrderItem/queryList")
  JcxOrderItemQueryListRes queryList(@RequestBody @Valid JcxOrderItemQueryListReq req);

  /**
   * 根据ID 更新
   */
  @PostMapping("/jcxOrderItem/updateById")
  JcxOrderItemUpdateByIdRes updateById(@RequestBody @Validated(UpdateCheck.class) JcxOrderItemUpdateByIdReq req);

  /**
   * 分页查询
   */
  @PostMapping("/jcxOrderItem/queryPageList")
  DynamicsPage<JcxOrderItemExportQueryPageListInfoRes> queryPageList(@RequestBody @Valid JcxOrderItemExportQueryPageListReq req);

  /**
   * 导出
   */
  @PostMapping("/jcxOrderItem/exportQueryPageList")
  void queryPageListExport(@RequestBody @Valid JcxOrderItemExportQueryPageListReq req);

  /**
   * 导入
   */
  @PostMapping("/jcxOrderItem/importData")
  JcxOrderItemImportRes importData(@RequestParam("file") MultipartFile file);


  /**
   * 根据ID 批量查询
   */
  @PostMapping("/jcxOrderItem/queryByIdList")
  JcxOrderItemQueryByIdListRes queryByIdListRes(@RequestBody @Valid JcxOrderItemQueryByIdListReq req);


}
