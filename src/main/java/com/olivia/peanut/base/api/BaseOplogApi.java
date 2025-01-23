package com.olivia.peanut.base.api;

import com.olivia.peanut.base.api.entity.baseOplog.*;
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
 * 操作日志(BaseOplog)对外API
 *
 * @author makejava
 * @since 2024-11-30 16:01:00
 */
// @FeignClient(value = "",contextId = "baseOplog-api",url = "${ portal..center.endpoint:}")
public interface BaseOplogApi {

  /**
   * 保存 操作日志
   */
  @PostMapping("/baseOplog/insert")
  BaseOplogInsertRes insert(@RequestBody @Validated(InsertCheck.class) BaseOplogInsertReq req);

  /**
   * 根据ID 删除 操作日志
   */
  @PostMapping("/baseOplog/deleteByIdList")
  BaseOplogDeleteByIdListRes deleteByIdList(@RequestBody @Valid BaseOplogDeleteByIdListReq req);

  /**
   * 查询 操作日志
   */
  @PostMapping("/baseOplog/queryList")
  BaseOplogQueryListRes queryList(@RequestBody @Valid BaseOplogQueryListReq req);

  /**
   * 根据ID 更新 操作日志
   */
  @PostMapping("/baseOplog/updateById")
  BaseOplogUpdateByIdRes updateById(@RequestBody @Validated(UpdateCheck.class) BaseOplogUpdateByIdReq req);

  /**
   * 分页查询 操作日志
   */
  @PostMapping("/baseOplog/queryPageList")
  DynamicsPage<BaseOplogExportQueryPageListInfoRes> queryPageList(@RequestBody @Valid BaseOplogExportQueryPageListReq req);

  /**
   * 导出 操作日志
   */
  @PostMapping("/baseOplog/exportQueryPageList")
  void queryPageListExport(@RequestBody @Valid BaseOplogExportQueryPageListReq req);

  /**
   * 导入
   */
  @PostMapping("/baseOplog/importData")
  BaseOplogImportRes importData(@RequestParam("file") MultipartFile file);


  /**
   * 根据ID 批量查询
   */
  @PostMapping("/baseOplog/queryByIdList")
  BaseOplogQueryByIdListRes queryByIdListRes(@RequestBody @Valid BaseOplogQueryByIdListReq req);


}
