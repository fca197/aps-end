package com.olivia.peanut.portal.api;


import com.olivia.peanut.portal.api.entity.jcxBuyOrder.*;
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
 * (JcxBuyOrder)对外API
 *
 * @author peanut
 * @since 2024-03-27 13:51:36
 */
// @FeignClient(value = "",contextId = "jcxBuyOrder-api",url = "${ portal..center.endpoint:}")
public interface JcxBuyOrderApi {

  /**
   * 保存
   */
  @PostMapping("/jcxBuyOrder/insert")
  JcxBuyOrderInsertRes insert(@RequestBody @Validated(InsertCheck.class) JcxBuyOrderInsertReq req);

  /**
   * 根据ID 删除
   */
  @PostMapping("/jcxBuyOrder/deleteByIdList")
  JcxBuyOrderDeleteByIdListRes deleteByIdList(@RequestBody @Valid JcxBuyOrderDeleteByIdListReq req);

  /**
   * 查询
   */
  @PostMapping("/jcxBuyOrder/queryList")
  JcxBuyOrderQueryListRes queryList(@RequestBody @Valid JcxBuyOrderQueryListReq req);

  /**
   * 根据ID 更新
   */
  @PostMapping("/jcxBuyOrder/updateById")
  JcxBuyOrderUpdateByIdRes updateById(@RequestBody @Validated(UpdateCheck.class) JcxBuyOrderUpdateByIdReq req);

  /**
   * 分页查询
   */
  @PostMapping("/jcxBuyOrder/queryPageList")
  DynamicsPage<JcxBuyOrderExportQueryPageListInfoRes> queryPageList(@RequestBody @Valid JcxBuyOrderExportQueryPageListReq req);

  /**
   * 导出
   */
  @PostMapping("/jcxBuyOrder/exportQueryPageList")
  void queryPageListExport(@RequestBody @Valid JcxBuyOrderExportQueryPageListReq req);

  /**
   * 导入
   */
  @PostMapping("/jcxBuyOrder/importData")
  JcxBuyOrderImportRes importData(@RequestParam("file") MultipartFile file);


  /**
   * 根据ID 批量查询
   */
  @PostMapping("/jcxBuyOrder/queryByIdList")
  JcxBuyOrderQueryByIdListRes queryByIdListRes(@RequestBody @Valid JcxBuyOrderQueryByIdListReq req);

  @PostMapping("/jcxBuyOrder/updateStatus")
  JcxBuyOrderUpdateStatusRes updateStatus(@RequestBody @Valid JcxBuyOrderUpdateStatusReq req);

}
