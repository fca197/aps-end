package com.olivia.peanut.base.api;


import com.olivia.peanut.base.api.entity.baseTableHeader.*;
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
 * (BaseTableHeader)对外API
 *
 * @author peanut
 * @since 2024-03-25 14:19:09
 */
// @FeignClient(value = "",contextId = "baseTableHeader-api",url = "${ portal..center.endpoint:}")
public interface BaseTableHeaderApi {

  /**
   * 保存
   */
  @PostMapping("/baseTableHeader/insert")
  BaseTableHeaderInsertRes insert(@RequestBody @Validated(InsertCheck.class) BaseTableHeaderInsertReq req);

  /**
   * 根据ID 删除
   */
  @PostMapping("/baseTableHeader/deleteByIdList")
  BaseTableHeaderDeleteByIdListRes deleteByIdList(@RequestBody @Valid BaseTableHeaderDeleteByIdListReq req);

  /**
   * 查询
   */
  @PostMapping("/baseTableHeader/queryList")
  BaseTableHeaderQueryListRes queryList(@RequestBody @Valid BaseTableHeaderQueryListReq req);

  /**
   * 根据ID 更新
   */
  @PostMapping("/baseTableHeader/updateById")
  BaseTableHeaderUpdateByIdRes updateById(@RequestBody @Validated(UpdateCheck.class) BaseTableHeaderUpdateByIdReq req);

  /**
   * 分页查询
   */
  @PostMapping("/baseTableHeader/queryPageList")
  DynamicsPage<BaseTableHeaderExportQueryPageListInfoRes> queryPageList(@RequestBody @Valid BaseTableHeaderExportQueryPageListReq req);

  /**
   * 导出
   */
  @PostMapping("/baseTableHeader/exportQueryPageList")
  void queryPageListExport(@RequestBody @Valid BaseTableHeaderExportQueryPageListReq req);

  /**
   * 导入
   */
  @PostMapping("/baseTableHeader/importData")
  BaseTableHeaderImportRes importData(@RequestParam("file") MultipartFile file);


  /**
   * 根据ID 批量查询
   */
  @PostMapping("/baseTableHeader/queryByIdList")
  BaseTableHeaderQueryByIdListRes queryByIdListRes(@RequestBody @Valid BaseTableHeaderQueryByIdListReq req);


}
