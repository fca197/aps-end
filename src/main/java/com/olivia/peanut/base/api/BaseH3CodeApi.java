package com.olivia.peanut.base.api;

import com.olivia.peanut.base.api.entity.baseH3Code.*;
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
 * H3对应的值(BaseH3Code)对外API
 *
 * @author makejava
 * @since 2024-11-19 16:09:17
 */
// @FeignClient(value = "",contextId = "baseH3Code-api",url = "${ portal..center.endpoint:}")
public interface BaseH3CodeApi {

  /**
   * 保存 H3对应的值
   */
  @PostMapping("/baseH3Code/insert")
  BaseH3CodeInsertRes insert(@RequestBody @Validated(InsertCheck.class) BaseH3CodeInsertReq req);

  /**
   * 根据ID 删除 H3对应的值
   */
  @PostMapping("/baseH3Code/deleteByIdList")
  BaseH3CodeDeleteByIdListRes deleteByIdList(@RequestBody @Valid BaseH3CodeDeleteByIdListReq req);

  /**
   * 查询 H3对应的值
   */
  @PostMapping("/baseH3Code/queryList")
  BaseH3CodeQueryListRes queryList(@RequestBody @Valid BaseH3CodeQueryListReq req);

  /**
   * 根据ID 更新 H3对应的值
   */
  @PostMapping("/baseH3Code/updateById")
  BaseH3CodeUpdateByIdRes updateById(@RequestBody @Validated(UpdateCheck.class) BaseH3CodeUpdateByIdReq req);

  /**
   * 分页查询 H3对应的值
   */
  @PostMapping("/baseH3Code/queryPageList")
  DynamicsPage<BaseH3CodeExportQueryPageListInfoRes> queryPageList(@RequestBody @Valid BaseH3CodeExportQueryPageListReq req);

  /**
   * 导出 H3对应的值
   */
  @PostMapping("/baseH3Code/exportQueryPageList")
  void queryPageListExport(@RequestBody @Valid BaseH3CodeExportQueryPageListReq req);

  /**
   * 导入
   */
  @PostMapping("/baseH3Code/importData")
  BaseH3CodeImportRes importData(@RequestParam("file") MultipartFile file);


  /**
   * 根据ID 批量查询
   */
  @PostMapping("/baseH3Code/queryByIdList")
  BaseH3CodeQueryByIdListRes queryByIdListRes(@RequestBody @Valid BaseH3CodeQueryByIdListReq req);


}
