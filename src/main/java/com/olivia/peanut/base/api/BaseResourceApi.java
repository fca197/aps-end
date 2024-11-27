package com.olivia.peanut.base.api;

import com.olivia.peanut.base.api.entity.baseResource.*;
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
 * 资源(BaseResource)对外API
 *
 * @author peanut
 * @since 2024-08-06 17:29:00
 */
// @FeignClient(value = "",contextId = "baseResource-api",url = "${ portal..center.endpoint:}")
public interface BaseResourceApi {

  /**
   * 保存 资源
   */
  @PostMapping("/baseResource/insert")
  BaseResourceInsertRes insert(@RequestBody @Validated(InsertCheck.class) BaseResourceInsertReq req);

  /**
   * 根据ID 删除 资源
   */
  @PostMapping("/baseResource/deleteByIdList")
  BaseResourceDeleteByIdListRes deleteByIdList(@RequestBody @Valid BaseResourceDeleteByIdListReq req);

  /**
   * 查询 资源
   */
  @PostMapping("/baseResource/queryList")
  BaseResourceQueryListRes queryList(@RequestBody @Valid BaseResourceQueryListReq req);

  /**
   * 根据ID 更新 资源
   */
  @PostMapping("/baseResource/updateById")
  BaseResourceUpdateByIdRes updateById(@RequestBody @Validated(UpdateCheck.class) BaseResourceUpdateByIdReq req);

  /**
   * 分页查询 资源
   */
  @PostMapping("/baseResource/queryPageList")
  DynamicsPage<BaseResourceExportQueryPageListInfoRes> queryPageList(@RequestBody @Valid BaseResourceExportQueryPageListReq req);

  /**
   * 导出 资源
   */
  @PostMapping("/baseResource/exportQueryPageList")
  void queryPageListExport(@RequestBody @Valid BaseResourceExportQueryPageListReq req);

  /**
   * 导入
   */
  @PostMapping("/baseResource/importData")
  BaseResourceImportRes importData(@RequestParam("file") MultipartFile file);


  /**
   * 根据ID 批量查询
   */
  @PostMapping("/baseResource/queryByIdList")
  BaseResourceQueryByIdListRes queryByIdListRes(@RequestBody @Valid BaseResourceQueryByIdListReq req);


}
