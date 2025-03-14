package com.olivia.peanut.base.api;


import com.olivia.peanut.base.api.entity.calendar.*;
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
 * 工作日历(Calendar)对外API
 *
 * @author makejava
 * @since 2024-03-11 10:44:02
 */
// @FeignClient(value = "",contextId = "calendar-api",url = "${ portal..center.endpoint:}")
public interface CalendarApi {

  /**
   * 保存 工作日历
   */
  @PostMapping("/calendar/insert")
  CalendarInsertRes insert(@RequestBody @Validated(InsertCheck.class) CalendarInsertReq req);

  /**
   * 根据ID 删除 工作日历
   */
  @PostMapping("/calendar/deleteByIdList")
  CalendarDeleteByIdListRes deleteByIdList(@RequestBody @Valid CalendarDeleteByIdListReq req);

  /**
   * 查询 工作日历
   */
  @PostMapping("/calendar/queryList")
  CalendarQueryListRes queryList(@RequestBody @Valid CalendarQueryListReq req);

  /**
   * 根据ID 更新 工作日历
   */
  @PostMapping("/calendar/updateById")
  CalendarUpdateByIdRes updateById(@RequestBody @Validated(UpdateCheck.class) CalendarUpdateByIdReq req);

  /**
   * 分页查询 工作日历
   */
  @PostMapping("/calendar/queryPageList")
  DynamicsPage<CalendarExportQueryPageListInfoRes> queryPageList(@RequestBody @Valid CalendarExportQueryPageListReq req);

  /**
   * 导出 工作日历
   */
  @PostMapping("/calendar/exportQueryPageList")
  void queryPageListExport(@RequestBody @Valid CalendarExportQueryPageListReq req);

  /**
   * 导入
   */
  @PostMapping("/calendar/importData")
  CalendarImportRes importData(@RequestParam("file") MultipartFile file);


  /**
   * 根据ID 批量查询
   */
  @PostMapping("/calendar/queryByIdList")
  CalendarQueryByIdListRes queryByIdListRes(@RequestBody @Valid CalendarQueryByIdListReq req);

  @PostMapping("/calendar/day/update")
  CalendarDayUpdateRes calendarDayUpdate(@RequestBody @Valid CalendarDayUpdateReq req);

  @PostMapping("/calendar/day/byId")
  CalendarDayByIdRes calendarDayById(@RequestBody @Valid CalendarDayByIdReq req);


}
