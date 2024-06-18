package com.olivia.peanut.aps.api;


import com.olivia.peanut.aps.api.entity.workshopStation.*;
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
 * 工位信息(WorkshopStation)对外API
 *
 * @author makejava
 * @since 2024-03-11 10:55:23
 */
// @FeignClient(value = "",contextId = "workshopStation-api",url = "${ portal..center.endpoint:}")
public interface ApsWorkshopStationApi {

  /**
   * 保存 工位信息
   */
  @PostMapping("/workshopStation/insert")
  WorkshopStationInsertRes insert(@RequestBody @Validated(InsertCheck.class) WorkshopStationInsertReq req);

  /**
   * 根据ID 删除 工位信息
   */
  @PostMapping("/workshopStation/deleteByIdList")
  WorkshopStationDeleteByIdListRes deleteByIdList(@RequestBody @Valid WorkshopStationDeleteByIdListReq req);

  /**
   * 查询 工位信息
   */
  @PostMapping("/workshopStation/queryList")
  WorkshopStationQueryListRes queryList(@RequestBody @Valid WorkshopStationQueryListReq req);

  /**
   * 根据ID 更新 工位信息
   */
  @PostMapping("/workshopStation/updateById")
  WorkshopStationUpdateByIdRes updateById(@RequestBody @Validated(UpdateCheck.class) WorkshopStationUpdateByIdReq req);

  /**
   * 分页查询 工位信息
   */
  @PostMapping("/workshopStation/queryPageList")
  DynamicsPage<WorkshopStationExportQueryPageListInfoRes> queryPageList(@RequestBody @Valid WorkshopStationExportQueryPageListReq req);

  /**
   * 导出 工位信息
   */
  @PostMapping("/workshopStation/exportQueryPageList")
  void queryPageListExport(@RequestBody @Valid WorkshopStationExportQueryPageListReq req);

  /**
   * 导入
   */
  @PostMapping("/workshopStation/importData")
  WorkshopStationImportRes importData(@RequestParam("file") MultipartFile file);


  /**
   * 根据ID 批量查询
   */
  @PostMapping("/workshopStation/queryByIdList")
  WorkshopStationQueryByIdListRes queryByIdListRes(@RequestBody @Valid WorkshopStationQueryByIdListReq req);


}
