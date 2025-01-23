package com.olivia.peanut.aps.api;

import com.olivia.peanut.aps.api.entity.apsProduceProcessItem.*;
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
 * aps 生产机器(ApsProduceProcessItem)对外API
 *
 * @author makejava
 * @since 2024-10-24 17:00:20
 */
// @FeignClient(value = "",contextId = "apsProduceProcessItem-api",url = "${ portal..center.endpoint:}")
public interface ApsProduceProcessItemApi {

  /**
   * 保存 aps 生产机器
   */
  @PostMapping("/apsProduceProcessItem/insert")
  ApsProduceProcessItemInsertRes insert(@RequestBody @Validated(InsertCheck.class) ApsProduceProcessItemInsertReq req);

  /**
   * 根据ID 删除 aps 生产机器
   */
  @PostMapping("/apsProduceProcessItem/deleteByIdList")
  ApsProduceProcessItemDeleteByIdListRes deleteByIdList(@RequestBody @Valid ApsProduceProcessItemDeleteByIdListReq req);

  /**
   * 查询 aps 生产机器
   */
  @PostMapping("/apsProduceProcessItem/queryList")
  ApsProduceProcessItemQueryListRes queryList(@RequestBody @Valid ApsProduceProcessItemQueryListReq req);

  /**
   * 根据ID 更新 aps 生产机器
   */
  @PostMapping("/apsProduceProcessItem/updateById")
  ApsProduceProcessItemUpdateByIdRes updateById(@RequestBody @Validated(UpdateCheck.class) ApsProduceProcessItemUpdateByIdReq req);

  /**
   * 分页查询 aps 生产机器
   */
  @PostMapping("/apsProduceProcessItem/queryPageList")
  DynamicsPage<ApsProduceProcessItemExportQueryPageListInfoRes> queryPageList(@RequestBody @Valid ApsProduceProcessItemExportQueryPageListReq req);

  /**
   * 导出 aps 生产机器
   */
  @PostMapping("/apsProduceProcessItem/exportQueryPageList")
  void queryPageListExport(@RequestBody @Valid ApsProduceProcessItemExportQueryPageListReq req);

  /**
   * 导入
   */
  @PostMapping("/apsProduceProcessItem/importData")
  ApsProduceProcessItemImportRes importData(@RequestParam("file") MultipartFile file);


  /**
   * 根据ID 批量查询
   */
  @PostMapping("/apsProduceProcessItem/queryByIdList")
  ApsProduceProcessItemQueryByIdListRes queryByIdListRes(@RequestBody @Valid ApsProduceProcessItemQueryByIdListReq req);


}
