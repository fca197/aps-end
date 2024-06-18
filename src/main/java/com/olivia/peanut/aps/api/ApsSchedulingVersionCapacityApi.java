package com.olivia.peanut.aps.api;


import com.olivia.peanut.aps.api.entity.apsSchedulingVersionCapacity.*;
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
 * (ApsSchedulingVersionCapacity)对外API
 *
 * @author peanut
 * @since 2024-04-18 14:57:34
 */
// @FeignClient(value = "",contextId = "apsSchedulingVersionCapacity-api",url = "${ portal..center.endpoint:}")
public interface ApsSchedulingVersionCapacityApi {

  /**
   * 保存
   */
  @PostMapping("/apsSchedulingVersionCapacity/insert")
  ApsSchedulingVersionCapacityInsertRes insert(@RequestBody @Validated(InsertCheck.class) ApsSchedulingVersionCapacityInsertReq req);

  /**
   * 根据ID 删除
   */
  @PostMapping("/apsSchedulingVersionCapacity/deleteByIdList")
  ApsSchedulingVersionCapacityDeleteByIdListRes deleteByIdList(@RequestBody @Valid ApsSchedulingVersionCapacityDeleteByIdListReq req);

  /**
   * 查询
   */
  @PostMapping("/apsSchedulingVersionCapacity/queryList")
  ApsSchedulingVersionCapacityQueryListRes queryList(@RequestBody @Valid ApsSchedulingVersionCapacityQueryListReq req);

  /**
   * 根据ID 更新
   */
  @PostMapping("/apsSchedulingVersionCapacity/updateById")
  ApsSchedulingVersionCapacityUpdateByIdRes updateById(@RequestBody @Validated(UpdateCheck.class) ApsSchedulingVersionCapacityUpdateByIdReq req);

  /**
   * 分页查询
   */
  @PostMapping("/apsSchedulingVersionCapacity/queryPageList")
  DynamicsPage<ApsSchedulingVersionCapacityExportQueryPageListInfoRes> queryPageList(@RequestBody @Valid ApsSchedulingVersionCapacityExportQueryPageListReq req);

  /**
   * 导出
   */
  @PostMapping("/apsSchedulingVersionCapacity/exportQueryPageList")
  void queryPageListExport(@RequestBody @Valid ApsSchedulingVersionCapacityExportQueryPageListReq req);

  /**
   * 导入
   */
  @PostMapping("/apsSchedulingVersionCapacity/importData")
  ApsSchedulingVersionCapacityImportRes importData(@RequestParam("file") MultipartFile file);


  /**
   * 根据ID 批量查询
   */
  @PostMapping("/apsSchedulingVersionCapacity/queryByIdList")
  ApsSchedulingVersionCapacityQueryByIdListRes queryByIdListRes(@RequestBody @Valid ApsSchedulingVersionCapacityQueryByIdListReq req);


}
