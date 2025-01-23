package com.olivia.peanut.base.api;

import com.olivia.peanut.base.api.entity.baseApp.*;
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
 * 应用表(BaseApp)对外API
 *
 * @author peanut
 * @since 2024-08-05 11:18:57
 */
// @FeignClient(value = "",contextId = "baseApp-api",url = "${ portal..center.endpoint:}")
public interface BaseAppApi {

  /**
   * 保存 应用表
   */
  @PostMapping("/baseApp/insert")
  BaseAppInsertRes insert(@RequestBody @Validated(InsertCheck.class) BaseAppInsertReq req);

  /**
   * 根据ID 删除 应用表
   */
  @PostMapping("/baseApp/deleteByIdList")
  BaseAppDeleteByIdListRes deleteByIdList(@RequestBody @Valid BaseAppDeleteByIdListReq req);

  /**
   * 查询 应用表
   */
  @PostMapping("/baseApp/queryList")
  BaseAppQueryListRes queryList(@RequestBody @Valid BaseAppQueryListReq req);

  /**
   * 根据ID 更新 应用表
   */
  @PostMapping("/baseApp/updateById")
  BaseAppUpdateByIdRes updateById(@RequestBody @Validated(UpdateCheck.class) BaseAppUpdateByIdReq req);

  /**
   * 分页查询 应用表
   */
  @PostMapping("/baseApp/queryPageList")
  DynamicsPage<BaseAppExportQueryPageListInfoRes> queryPageList(@RequestBody @Valid BaseAppExportQueryPageListReq req);

  /**
   * 导出 应用表
   */
  @PostMapping("/baseApp/exportQueryPageList")
  void queryPageListExport(@RequestBody @Valid BaseAppExportQueryPageListReq req);

  /**
   * 导入
   */
  @PostMapping("/baseApp/importData")
  BaseAppImportRes importData(@RequestParam("file") MultipartFile file);


  /**
   * 根据ID 批量查询
   */
  @PostMapping("/baseApp/queryByIdList")
  BaseAppQueryByIdListRes queryByIdListRes(@RequestBody @Valid BaseAppQueryByIdListReq req);


}
