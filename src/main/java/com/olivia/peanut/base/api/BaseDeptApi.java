package com.olivia.peanut.base.api;

import com.olivia.peanut.base.api.entity.baseDept.*;
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
 * 部门表(BaseDept)对外API
 *
 * @author peanut
 * @since 2024-07-31 14:33:30
 */
// @FeignClient(value = "",contextId = "baseDept-api",url = "${ portal..center.endpoint:}")
public interface BaseDeptApi {

  /**
   * 保存 部门表
   */
  @PostMapping("/baseDept/insert")
  BaseDeptInsertRes insert(@RequestBody @Validated(InsertCheck.class) BaseDeptInsertReq req);

  /**
   * 根据ID 删除 部门表
   */
  @PostMapping("/baseDept/deleteByIdList")
  BaseDeptDeleteByIdListRes deleteByIdList(@RequestBody @Valid BaseDeptDeleteByIdListReq req);

  /**
   * 查询 部门表
   */
  @PostMapping("/baseDept/queryList")
  BaseDeptQueryListRes queryList(@RequestBody @Valid BaseDeptQueryListReq req);

  /**
   * 根据ID 更新 部门表
   */
  @PostMapping("/baseDept/updateById")
  BaseDeptUpdateByIdRes updateById(@RequestBody @Validated(UpdateCheck.class) BaseDeptUpdateByIdReq req);

  /**
   * 分页查询 部门表
   */
  @PostMapping("/baseDept/queryPageList")
  DynamicsPage<BaseDeptExportQueryPageListInfoRes> queryPageList(@RequestBody @Valid BaseDeptExportQueryPageListReq req);

  /**
   * 导出 部门表
   */
  @PostMapping("/baseDept/exportQueryPageList")
  void queryPageListExport(@RequestBody @Valid BaseDeptExportQueryPageListReq req);

  /**
   * 导入
   */
  @PostMapping("/baseDept/importData")
  BaseDeptImportRes importData(@RequestParam("file") MultipartFile file);


  /**
   * 根据ID 批量查询
   */
  @PostMapping("/baseDept/queryByIdList")
  BaseDeptQueryByIdListRes queryByIdListRes(@RequestBody @Valid BaseDeptQueryByIdListReq req);


}
