package com.olivia.peanut.portal.api;


import com.olivia.peanut.portal.api.entity.jcxGoodsWarning.*;
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
 * (JcxGoodsWarning)对外API
 *
 * @author peanut
 * @since 2024-03-24 14:10:54
 */
// @FeignClient(value = "",contextId = "jcxGoodsWarning-api",url = "${ portal..center.endpoint:}")
public interface JcxGoodsWarningApi {

  /**
   * 保存
   */
  @PostMapping("/jcxGoodsWarning/insert")
  JcxGoodsWarningInsertRes insert(@RequestBody @Validated(InsertCheck.class) JcxGoodsWarningInsertReq req);

  /**
   * 根据ID 删除
   */
  @PostMapping("/jcxGoodsWarning/deleteByIdList")
  JcxGoodsWarningDeleteByIdListRes deleteByIdList(@RequestBody @Valid JcxGoodsWarningDeleteByIdListReq req);

  /**
   * 查询
   */
  @PostMapping("/jcxGoodsWarning/queryList")
  JcxGoodsWarningQueryListRes queryList(@RequestBody @Valid JcxGoodsWarningQueryListReq req);

  /**
   * 根据ID 更新
   */
  @PostMapping("/jcxGoodsWarning/updateById")
  JcxGoodsWarningUpdateByIdRes updateById(@RequestBody @Validated(UpdateCheck.class) JcxGoodsWarningUpdateByIdReq req);

  /**
   * 分页查询
   */
  @PostMapping("/jcxGoodsWarning/queryPageList")
  DynamicsPage<JcxGoodsWarningExportQueryPageListInfoRes> queryPageList(@RequestBody @Valid JcxGoodsWarningExportQueryPageListReq req);

  /**
   * 导出
   */
  @PostMapping("/jcxGoodsWarning/exportQueryPageList")
  void queryPageListExport(@RequestBody @Valid JcxGoodsWarningExportQueryPageListReq req);

  /**
   * 导入
   */
  @PostMapping("/jcxGoodsWarning/importData")
  JcxGoodsWarningImportRes importData(@RequestParam("file") MultipartFile file);


  /**
   * 根据ID 批量查询
   */
  @PostMapping("/jcxGoodsWarning/queryByIdList")
  JcxGoodsWarningQueryByIdListRes queryByIdListRes(@RequestBody @Valid JcxGoodsWarningQueryByIdListReq req);


}
