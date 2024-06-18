package com.olivia.peanut.aps.api;


import com.olivia.peanut.aps.api.entity.workshopSection.*;
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
 * 工段信息(WorkshopSection)对外API
 *
 * @author makejava
 * @since 2024-03-11 10:55:22
 */
// @FeignClient(value = "",contextId = "workshopSection-api",url = "${ portal..center.endpoint:}")
public interface ApsWorkshopSectionApi {

  /**
   * 保存 工段信息
   */
  @PostMapping("/workshopSection/insert")
  WorkshopSectionInsertRes insert(@RequestBody @Validated(InsertCheck.class) WorkshopSectionInsertReq req);

  /**
   * 根据ID 删除 工段信息
   */
  @PostMapping("/workshopSection/deleteByIdList")
  WorkshopSectionDeleteByIdListRes deleteByIdList(@RequestBody @Valid WorkshopSectionDeleteByIdListReq req);

  /**
   * 查询 工段信息
   */
  @PostMapping("/workshopSection/queryList")
  WorkshopSectionQueryListRes queryList(@RequestBody @Valid WorkshopSectionQueryListReq req);

  /**
   * 根据ID 更新 工段信息
   */
  @PostMapping("/workshopSection/updateById")
  WorkshopSectionUpdateByIdRes updateById(@RequestBody @Validated(UpdateCheck.class) WorkshopSectionUpdateByIdReq req);

  /**
   * 分页查询 工段信息
   */
  @PostMapping("/workshopSection/queryPageList")
  DynamicsPage<WorkshopSectionExportQueryPageListInfoRes> queryPageList(@RequestBody @Valid WorkshopSectionExportQueryPageListReq req);

  /**
   * 导出 工段信息
   */
  @PostMapping("/workshopSection/exportQueryPageList")
  void queryPageListExport(@RequestBody @Valid WorkshopSectionExportQueryPageListReq req);

  /**
   * 导入
   */
  @PostMapping("/workshopSection/importData")
  WorkshopSectionImportRes importData(@RequestParam("file") MultipartFile file);


  /**
   * 根据ID 批量查询
   */
  @PostMapping("/workshopSection/queryByIdList")
  WorkshopSectionQueryByIdListRes queryByIdListRes(@RequestBody @Valid WorkshopSectionQueryByIdListReq req);


}
