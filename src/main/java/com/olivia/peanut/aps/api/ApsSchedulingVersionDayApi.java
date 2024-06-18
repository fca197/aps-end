package com.olivia.peanut.aps.api;


import com.olivia.peanut.aps.api.entity.apsSchedulingVersionDay.*;
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
 * (ApsSchedulingVersionDay)对外API
 *
 * @author peanut
 * @since 2024-04-23 14:37:04
 */
// @FeignClient(value = "",contextId = "apsSchedulingVersionDay-api",url = "${ portal..center.endpoint:}")
public interface ApsSchedulingVersionDayApi {

  /**
   * 保存
   */
  @PostMapping("/apsSchedulingVersionDay/insert")
  ApsSchedulingVersionDayInsertRes insert(@RequestBody @Validated(InsertCheck.class) ApsSchedulingVersionDayInsertReq req);

  /**
   * 根据ID 删除
   */
  @PostMapping("/apsSchedulingVersionDay/deleteByIdList")
  ApsSchedulingVersionDayDeleteByIdListRes deleteByIdList(@RequestBody @Valid ApsSchedulingVersionDayDeleteByIdListReq req);

  /**
   * 查询
   */
  @PostMapping("/apsSchedulingVersionDay/queryList")
  ApsSchedulingVersionDayQueryListRes queryList(@RequestBody @Valid ApsSchedulingVersionDayQueryListReq req);

  /**
   * 根据ID 更新
   */
  @PostMapping("/apsSchedulingVersionDay/updateById")
  ApsSchedulingVersionDayUpdateByIdRes updateById(@RequestBody @Validated(UpdateCheck.class) ApsSchedulingVersionDayUpdateByIdReq req);

  /**
   * 分页查询
   */
  @PostMapping("/apsSchedulingVersionDay/queryPageList")
  DynamicsPage<ApsSchedulingVersionDayExportQueryPageListInfoRes> queryPageList(@RequestBody @Valid ApsSchedulingVersionDayExportQueryPageListReq req);

  /**
   * 导出
   */
  @PostMapping("/apsSchedulingVersionDay/exportQueryPageList")
  void queryPageListExport(@RequestBody @Valid ApsSchedulingVersionDayExportQueryPageListReq req);

  /**
   * 导入
   */
  @PostMapping("/apsSchedulingVersionDay/importData")
  ApsSchedulingVersionDayImportRes importData(@RequestParam("file") MultipartFile file);


  /**
   * 根据ID 批量查询
   */
  @PostMapping("/apsSchedulingVersionDay/queryByIdList")
  ApsSchedulingVersionDayQueryByIdListRes queryByIdListRes(@RequestBody @Valid ApsSchedulingVersionDayQueryByIdListReq req);


}
