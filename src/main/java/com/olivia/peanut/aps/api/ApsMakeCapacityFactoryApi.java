package com.olivia.peanut.aps.api;


import com.olivia.peanut.aps.api.entity.apsMakeCapacityFactory.*;
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
 * (ApsMakeCapacityFactory)对外API
 *
 * @author peanut
 * @since 2024-04-13 12:05:03
 */
// @FeignClient(value = "",contextId = "apsMakeCapacityFactory-api",url = "${ portal..center.endpoint:}")
public interface ApsMakeCapacityFactoryApi {

  /**
   * 保存
   */
  @PostMapping("/apsMakeCapacityFactory/insert")
  ApsMakeCapacityFactoryInsertRes insert(@RequestBody @Validated(InsertCheck.class) ApsMakeCapacityFactoryInsertReq req);

  /**
   * 根据ID 删除
   */
  @PostMapping("/apsMakeCapacityFactory/deleteByIdList")
  ApsMakeCapacityFactoryDeleteByIdListRes deleteByIdList(@RequestBody @Valid ApsMakeCapacityFactoryDeleteByIdListReq req);

  /**
   * 查询
   */
  @PostMapping("/apsMakeCapacityFactory/queryList")
  ApsMakeCapacityFactoryQueryListRes queryList(@RequestBody @Valid ApsMakeCapacityFactoryQueryListReq req);

  /**
   * 根据ID 更新
   */
  @PostMapping("/apsMakeCapacityFactory/updateById")
  ApsMakeCapacityFactoryUpdateByIdRes updateById(@RequestBody @Validated(UpdateCheck.class) ApsMakeCapacityFactoryUpdateByIdReq req);

  /**
   * 分页查询
   */
  @PostMapping("/apsMakeCapacityFactory/queryPageList")
  DynamicsPage<ApsMakeCapacityFactoryExportQueryPageListInfoRes> queryPageList(@RequestBody @Valid ApsMakeCapacityFactoryExportQueryPageListReq req);

  /**
   * 导出
   */
  @PostMapping("/apsMakeCapacityFactory/exportQueryPageList")
  void queryPageListExport(@RequestBody @Valid ApsMakeCapacityFactoryExportQueryPageListReq req);

  /**
   * 导入
   */
  @PostMapping("/apsMakeCapacityFactory/importData")
  ApsMakeCapacityFactoryImportRes importData(@RequestParam("file") MultipartFile file);


  /**
   * 根据ID 批量查询
   */
  @PostMapping("/apsMakeCapacityFactory/queryByIdList")
  ApsMakeCapacityFactoryQueryByIdListRes queryByIdListRes(@RequestBody @Valid ApsMakeCapacityFactoryQueryByIdListReq req);


}
