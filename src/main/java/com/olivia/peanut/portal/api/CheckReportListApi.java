package com.olivia.peanut.portal.api;


import com.olivia.peanut.portal.api.entity.checkReportList.*;
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
 * 报表检查记录信息(CheckReportList)对外API
 *
 * @author makejava
 * @since 2024-03-14 13:31:36
 */
// @FeignClient(value = "",contextId = "checkReportList-api",url = "${ portal..center.endpoint:}")
public interface CheckReportListApi {

  /**
   * 保存 报表检查记录信息
   */
  @PostMapping("/checkReportList/insert")
  CheckReportListInsertRes insert(@RequestBody @Validated(InsertCheck.class) CheckReportListInsertReq req);

  /**
   * 根据ID 删除 报表检查记录信息
   */
  @PostMapping("/checkReportList/deleteByIdList")
  CheckReportListDeleteByIdListRes deleteByIdList(@RequestBody @Valid CheckReportListDeleteByIdListReq req);

  /**
   * 查询 报表检查记录信息
   */
  @PostMapping("/checkReportList/queryList")
  CheckReportListQueryListRes queryList(@RequestBody @Valid CheckReportListQueryListReq req);

  /**
   * 根据ID 更新 报表检查记录信息
   */
  @PostMapping("/checkReportList/updateById")
  CheckReportListUpdateByIdRes updateById(@RequestBody @Validated(UpdateCheck.class) CheckReportListUpdateByIdReq req);

  /**
   * 分页查询 报表检查记录信息
   */
  @PostMapping("/checkReportList/queryPageList")
  DynamicsPage<CheckReportListExportQueryPageListInfoRes> queryPageList(@RequestBody @Valid CheckReportListExportQueryPageListReq req);

  /**
   * 导出 报表检查记录信息
   */
  @PostMapping("/checkReportList/exportQueryPageList")
  void queryPageListExport(@RequestBody @Valid CheckReportListExportQueryPageListReq req);

  /**
   * 导入
   */
  @PostMapping("/checkReportList/importData")
  CheckReportListImportRes importData(@RequestParam("file") MultipartFile file);


  /**
   * 根据ID 批量查询
   */
  @PostMapping("/checkReportList/queryByIdList")
  CheckReportListQueryByIdListRes queryByIdListRes(@RequestBody @Valid CheckReportListQueryByIdListReq req);


  @PostMapping("/checkReportList/factory")
  CheckReportListFactoryDataRes factoryData(@RequestBody @Valid CheckReportListFactoryDataReq req);

  @PostMapping("/checkReportList/room")
  CheckReportListRoomDataRes roomData(@RequestBody @Valid CheckReportListRoomDataReq req);

}
