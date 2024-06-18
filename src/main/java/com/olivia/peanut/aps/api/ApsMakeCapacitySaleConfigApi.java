package com.olivia.peanut.aps.api;


import com.olivia.peanut.aps.api.entity.apsMakeCapacitySaleConfig.*;
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
 * (ApsMakeCapacitySaleConfig)对外API
 *
 * @author peanut
 * @since 2024-04-13 12:05:06
 */
// @FeignClient(value = "",contextId = "apsMakeCapacitySaleConfig-api",url = "${ portal..center.endpoint:}")
public interface ApsMakeCapacitySaleConfigApi {

  /**
   * 保存
   */
  @PostMapping("/apsMakeCapacitySaleConfig/insert")
  ApsMakeCapacitySaleConfigInsertRes insert(@RequestBody @Validated(InsertCheck.class) ApsMakeCapacitySaleConfigInsertReq req);

  /**
   * 根据ID 删除
   */
  @PostMapping("/apsMakeCapacitySaleConfig/deleteByIdList")
  ApsMakeCapacitySaleConfigDeleteByIdListRes deleteByIdList(@RequestBody @Valid ApsMakeCapacitySaleConfigDeleteByIdListReq req);

  /**
   * 查询
   */
  @PostMapping("/apsMakeCapacitySaleConfig/queryList")
  ApsMakeCapacitySaleConfigQueryListRes queryList(@RequestBody @Valid ApsMakeCapacitySaleConfigQueryListReq req);

  /**
   * 根据ID 更新
   */
  @PostMapping("/apsMakeCapacitySaleConfig/updateById")
  ApsMakeCapacitySaleConfigUpdateByIdRes updateById(@RequestBody @Validated(UpdateCheck.class) ApsMakeCapacitySaleConfigUpdateByIdReq req);

  /**
   * 分页查询
   */
  @PostMapping("/apsMakeCapacitySaleConfig/queryPageList")
  DynamicsPage<ApsMakeCapacitySaleConfigExportQueryPageListInfoRes> queryPageList(@RequestBody @Valid ApsMakeCapacitySaleConfigExportQueryPageListReq req);

  /**
   * 导出
   */
  @PostMapping("/apsMakeCapacitySaleConfig/exportQueryPageList")
  void queryPageListExport(@RequestBody @Valid ApsMakeCapacitySaleConfigExportQueryPageListReq req);

  /**
   * 导入
   */
  @PostMapping("/apsMakeCapacitySaleConfig/importData")
  ApsMakeCapacitySaleConfigImportRes importData(@RequestParam("file") MultipartFile file);


  /**
   * 根据ID 批量查询
   */
  @PostMapping("/apsMakeCapacitySaleConfig/queryByIdList")
  ApsMakeCapacitySaleConfigQueryByIdListRes queryByIdListRes(@RequestBody @Valid ApsMakeCapacitySaleConfigQueryByIdListReq req);


}
