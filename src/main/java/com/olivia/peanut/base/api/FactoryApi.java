package com.olivia.peanut.base.api;


import com.olivia.peanut.base.api.entity.factory.*;
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
 * 工段信息(Factory)对外API
 *
 * @author makejava
 * @since 2024-03-11 10:44:04
 */
// @FeignClient(value = "",contextId = "factory-api",url = "${ portal..center.endpoint:}")
public interface FactoryApi {

  /**
   * 保存 工段信息
   */
  @PostMapping("/factory/insert")
  FactoryInsertRes insert(@RequestBody @Validated(InsertCheck.class) FactoryInsertReq req);

  /**
   * 根据ID 删除 工段信息
   */
  @PostMapping("/factory/deleteByIdList")
  FactoryDeleteByIdListRes deleteByIdList(@RequestBody @Valid FactoryDeleteByIdListReq req);

  /**
   * 查询 工段信息
   */
  @PostMapping("/factory/queryList")
  FactoryQueryListRes queryList(@RequestBody @Valid FactoryQueryListReq req);

  /**
   * 根据ID 更新 工段信息
   */
  @PostMapping("/factory/updateById")
  FactoryUpdateByIdRes updateById(@RequestBody @Validated(UpdateCheck.class) FactoryUpdateByIdReq req);

  /**
   * 分页查询 工段信息
   */
  @PostMapping("/factory/queryPageList")
  DynamicsPage<FactoryExportQueryPageListInfoRes> queryPageList(@RequestBody @Valid FactoryExportQueryPageListReq req);

  /**
   * 导出 工段信息
   */
  @PostMapping("/factory/exportQueryPageList")
  void queryPageListExport(@RequestBody @Valid FactoryExportQueryPageListReq req);

  /**
   * 导入
   */
  @PostMapping("/factory/importData")
  FactoryImportRes importData(@RequestParam("file") MultipartFile file);


  /**
   * 根据ID 批量查询
   */
  @PostMapping("/factory/queryByIdList")
  FactoryQueryByIdListRes queryByIdListRes(@RequestBody @Valid FactoryQueryByIdListReq req);


}
