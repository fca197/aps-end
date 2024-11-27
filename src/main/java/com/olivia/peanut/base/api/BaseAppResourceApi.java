package com.olivia.peanut.base.api;

import com.olivia.peanut.base.api.entity.baseAppResource.*;
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
 * 资源(BaseAppResource)对外API
 *
 * @author peanut
 * @since 2024-08-06 17:30:27
 */
// @FeignClient(value = "",contextId = "baseAppResource-api",url = "${ portal..center.endpoint:}")
public interface BaseAppResourceApi {

  /**
   * 保存 资源
   */
  @PostMapping("/baseAppResource/insert")
  BaseAppResourceInsertRes insert(@RequestBody @Validated(InsertCheck.class) BaseAppResourceInsertReq req);

  @PostMapping("/baseAppResource/insertList")
  BaseAppResourceInsertListRes insertList(@RequestBody @Validated(InsertCheck.class) BaseAppResourceInsertListReq req);

  /**
   * 根据ID 删除 资源
   */
  @PostMapping("/baseAppResource/deleteByIdList")
  BaseAppResourceDeleteByIdListRes deleteByIdList(@RequestBody @Valid BaseAppResourceDeleteByIdListReq req);

  /**
   * 查询 资源
   */
  @PostMapping("/baseAppResource/queryList")
  BaseAppResourceQueryListRes queryList(@RequestBody @Valid BaseAppResourceQueryListReq req);

  /**
   * 根据ID 更新 资源
   */
  @PostMapping("/baseAppResource/updateById")
  BaseAppResourceUpdateByIdRes updateById(@RequestBody @Validated(UpdateCheck.class) BaseAppResourceUpdateByIdReq req);

  /**
   * 分页查询 资源
   */
  @PostMapping("/baseAppResource/queryPageList")
  DynamicsPage<BaseAppResourceExportQueryPageListInfoRes> queryPageList(@RequestBody @Valid BaseAppResourceExportQueryPageListReq req);

  /**
   * 导出 资源
   */
  @PostMapping("/baseAppResource/exportQueryPageList")
  void queryPageListExport(@RequestBody @Valid BaseAppResourceExportQueryPageListReq req);

  /**
   * 导入
   */
  @PostMapping("/baseAppResource/importData")
  BaseAppResourceImportRes importData(@RequestParam("file") MultipartFile file);


  /**
   * 根据ID 批量查询
   */
  @PostMapping("/baseAppResource/queryByIdList")
  BaseAppResourceQueryByIdListRes queryByIdListRes(@RequestBody @Valid BaseAppResourceQueryByIdListReq req);


}
