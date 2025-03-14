package com.olivia.peanut.base.api;


import com.olivia.peanut.base.api.entity.shift.*;
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
 * (Shift)对外API
 *
 * @author peanut
 * @since 2024-04-04 12:10:14
 */
// @FeignClient(value = "",contextId = "shift-api",url = "${ portal..center.endpoint:}")
public interface ShiftApi {

  /**
   * 保存
   */
  @PostMapping("/shift/insert")
  ShiftInsertRes insert(@RequestBody @Validated(InsertCheck.class) ShiftInsertReq req);

  /**
   * 根据ID 删除
   */
  @PostMapping("/shift/deleteByIdList")
  ShiftDeleteByIdListRes deleteByIdList(@RequestBody @Valid ShiftDeleteByIdListReq req);

  /**
   * 查询
   */
  @PostMapping("/shift/queryList")
  ShiftQueryListRes queryList(@RequestBody @Valid ShiftQueryListReq req);

  /**
   * 根据ID 更新
   */
  @PostMapping("/shift/updateById")
  ShiftUpdateByIdRes updateById(@RequestBody @Validated(UpdateCheck.class) ShiftUpdateByIdReq req);

  /**
   * 分页查询
   */
  @PostMapping("/shift/queryPageList")
  DynamicsPage<ShiftExportQueryPageListInfoRes> queryPageList(@RequestBody @Valid ShiftExportQueryPageListReq req);

  /**
   * 导出
   */
  @PostMapping("/shift/exportQueryPageList")
  void queryPageListExport(@RequestBody @Valid ShiftExportQueryPageListReq req);

  /**
   * 导入
   */
  @PostMapping("/shift/importData")
  ShiftImportRes importData(@RequestParam("file") MultipartFile file);


  /**
   * 根据ID 批量查询
   */
  @PostMapping("/shift/queryByIdList")
  ShiftQueryByIdListRes queryByIdListRes(@RequestBody @Valid ShiftQueryByIdListReq req);


}
