package com.olivia.peanut.aps.api;

import com.olivia.peanut.aps.api.entity.apsMachine.*;
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
 * aps 生产机器(ApsMachine)对外API
 *
 * @author makejava
 * @since 2024-10-24 16:31:13
 */
// @FeignClient(value = "",contextId = "apsMachine-api",url = "${ portal..center.endpoint:}")
public interface ApsMachineApi {

  /**
   * 保存 aps 生产机器
   */
  @PostMapping("/apsMachine/insert")
  ApsMachineInsertRes insert(@RequestBody @Validated(InsertCheck.class) ApsMachineInsertReq req);

  /**
   * 根据ID 删除 aps 生产机器
   */
  @PostMapping("/apsMachine/deleteByIdList")
  ApsMachineDeleteByIdListRes deleteByIdList(@RequestBody @Valid ApsMachineDeleteByIdListReq req);

  /**
   * 查询 aps 生产机器
   */
  @PostMapping("/apsMachine/queryList")
  ApsMachineQueryListRes queryList(@RequestBody @Valid ApsMachineQueryListReq req);

  /**
   * 根据ID 更新 aps 生产机器
   */
  @PostMapping("/apsMachine/updateById")
  ApsMachineUpdateByIdRes updateById(@RequestBody @Validated(UpdateCheck.class) ApsMachineUpdateByIdReq req);

  /**
   * 分页查询 aps 生产机器
   */
  @PostMapping("/apsMachine/queryPageList")
  DynamicsPage<ApsMachineExportQueryPageListInfoRes> queryPageList(@RequestBody @Valid ApsMachineExportQueryPageListReq req);

  /**
   * 导出 aps 生产机器
   */
  @PostMapping("/apsMachine/exportQueryPageList")
  void queryPageListExport(@RequestBody @Valid ApsMachineExportQueryPageListReq req);

  /**
   * 导入
   */
  @PostMapping("/apsMachine/importData")
  ApsMachineImportRes importData(@RequestParam("file") MultipartFile file);


  /**
   * 根据ID 批量查询
   */
  @PostMapping("/apsMachine/queryByIdList")
  ApsMachineQueryByIdListRes queryByIdListRes(@RequestBody @Valid ApsMachineQueryByIdListReq req);


}
