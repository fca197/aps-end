package com.olivia.peanut.base.api;


import com.olivia.peanut.base.api.entity.shiftItem.*;
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
 * (ShiftItem)对外API
 *
 * @author peanut
 * @since 2024-04-04 12:10:16
 */
// @FeignClient(value = "",contextId = "shiftItem-api",url = "${ portal..center.endpoint:}")
public interface ShiftItemApi {

  /**
   * 保存
   */
  @PostMapping("/shiftItem/insert")
  ShiftItemInsertRes insert(@RequestBody @Validated(InsertCheck.class) ShiftItemInsertReq req);

  /**
   * 根据ID 删除
   */
  @PostMapping("/shiftItem/deleteByIdList")
  ShiftItemDeleteByIdListRes deleteByIdList(@RequestBody @Valid ShiftItemDeleteByIdListReq req);

  /**
   * 查询
   */
  @PostMapping("/shiftItem/queryList")
  ShiftItemQueryListRes queryList(@RequestBody @Valid ShiftItemQueryListReq req);

  /**
   * 根据ID 更新
   */
  @PostMapping("/shiftItem/updateById")
  ShiftItemUpdateByIdRes updateById(@RequestBody @Validated(UpdateCheck.class) ShiftItemUpdateByIdReq req);

  /**
   * 分页查询
   */
  @PostMapping("/shiftItem/queryPageList")
  DynamicsPage<ShiftItemExportQueryPageListInfoRes> queryPageList(@RequestBody @Valid ShiftItemExportQueryPageListReq req);

  /**
   * 导出
   */
  @PostMapping("/shiftItem/exportQueryPageList")
  void queryPageListExport(@RequestBody @Valid ShiftItemExportQueryPageListReq req);

  /**
   * 导入
   */
  @PostMapping("/shiftItem/importData")
  ShiftItemImportRes importData(@RequestParam("file") MultipartFile file);


  /**
   * 根据ID 批量查询
   */
  @PostMapping("/shiftItem/queryByIdList")
  ShiftItemQueryByIdListRes queryByIdListRes(@RequestBody @Valid ShiftItemQueryByIdListReq req);


}
