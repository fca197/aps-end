package com.olivia.peanut.aps.api;


import com.olivia.peanut.aps.api.entity.apsOrderUser.*;
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
 * (ApsOrderUser)对外API
 *
 * @author peanut
 * @since 2024-04-09 13:19:38
 */
// @FeignClient(value = "",contextId = "apsOrderUser-api",url = "${ portal..center.endpoint:}")
public interface ApsOrderUserApi {

  /**
   * 保存
   */
  @PostMapping("/apsOrderUser/insert")
  ApsOrderUserInsertRes insert(@RequestBody @Validated(InsertCheck.class) ApsOrderUserInsertReq req);

  /**
   * 根据ID 删除
   */
  @PostMapping("/apsOrderUser/deleteByIdList")
  ApsOrderUserDeleteByIdListRes deleteByIdList(@RequestBody @Valid ApsOrderUserDeleteByIdListReq req);

  /**
   * 查询
   */
  @PostMapping("/apsOrderUser/queryList")
  ApsOrderUserQueryListRes queryList(@RequestBody @Valid ApsOrderUserQueryListReq req);

  /**
   * 根据ID 更新
   */
  @PostMapping("/apsOrderUser/updateById")
  ApsOrderUserUpdateByIdRes updateById(@RequestBody @Validated(UpdateCheck.class) ApsOrderUserUpdateByIdReq req);

  /**
   * 分页查询
   */
  @PostMapping("/apsOrderUser/queryPageList")
  DynamicsPage<ApsOrderUserExportQueryPageListInfoRes> queryPageList(@RequestBody @Valid ApsOrderUserExportQueryPageListReq req);

  /**
   * 导出
   */
  @PostMapping("/apsOrderUser/exportQueryPageList")
  void queryPageListExport(@RequestBody @Valid ApsOrderUserExportQueryPageListReq req);

  /**
   * 导入
   */
  @PostMapping("/apsOrderUser/importData")
  ApsOrderUserImportRes importData(@RequestParam("file") MultipartFile file);


  /**
   * 根据ID 批量查询
   */
  @PostMapping("/apsOrderUser/queryByIdList")
  ApsOrderUserQueryByIdListRes queryByIdListRes(@RequestBody @Valid ApsOrderUserQueryByIdListReq req);


}
