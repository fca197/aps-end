package com.olivia.peanut.portal.api.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.github.yulichang.wrapper.MPJLambdaWrapper;
import com.olivia.peanut.portal.api.CheckReportApi;
import com.olivia.peanut.portal.api.CheckReportListApi;
import com.olivia.peanut.portal.api.entity.checkReport.*;
import com.olivia.peanut.portal.api.entity.checkReportList.CheckReportListFactoryDataReq;
import com.olivia.peanut.portal.api.entity.checkReportList.CheckReportListFactoryDataRes;
import com.olivia.peanut.portal.api.entity.checkReportList.CheckReportListRoomDataReq;
import com.olivia.peanut.portal.api.entity.checkReportList.CheckReportListRoomDataRes;
import com.olivia.peanut.portal.api.impl.listener.CheckReportImportListener;
import com.olivia.peanut.portal.model.CheckReport;
import com.olivia.peanut.portal.model.Room;
import com.olivia.peanut.portal.service.CheckReportService;
import com.olivia.peanut.portal.service.RoomService;
import com.olivia.sdk.ann.MethodExt;
import com.olivia.sdk.ann.MethodExt.FileSuffix;
import com.olivia.sdk.utils.*;
import com.olivia.sdk.utils.EasyExcelUtilExportMultipleData.SheetData;
import com.olivia.sdk.utils.EasyExcelUtilExportMultipleData.SheetHeader;
import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

/**
 * 报表信息(CheckReport)表服务实现类
 *
 * @author makejava
 * @since 2024-03-14 13:31:35
 */
@RestController
public class CheckReportApiImpl implements CheckReportApi {

  @Resource
  CheckReportListApi checkReportListApi;
  @Resource
  RoomService roomService;
  private @Autowired CheckReportService checkReportService;

  /****
   * insert
   *
   */
  public @Override CheckReportInsertRes insert(CheckReportInsertReq req) {
    CheckReport report = $.copy(req, CheckReport.class);
    report.setIsOver(Boolean.FALSE).setId(IdUtils.getId());
    this.checkReportService.save(report);
    return new CheckReportInsertRes().setCount(1).setId(report.getId());
  }

  /****
   * deleteByIds
   *
   */
  public @Override CheckReportDeleteByIdListRes deleteByIdList(CheckReportDeleteByIdListReq req) {
    checkReportService.removeByIds(req.getIdList());
    return new CheckReportDeleteByIdListRes();
  }

  /****
   * queryList
   *
   */
  public @Override CheckReportQueryListRes queryList(CheckReportQueryListReq req) {
    return checkReportService.queryList(req);
  }

  /****
   * updateById
   *
   */
  public @Override CheckReportUpdateByIdRes updateById(CheckReportUpdateByIdReq req) {
    checkReportService.updateById($.copy(req, CheckReport.class));
    return new CheckReportUpdateByIdRes();

  }

  public @Override DynamicsPage<CheckReportExportQueryPageListInfoRes> queryPageList(CheckReportExportQueryPageListReq req) {
    return checkReportService.queryPageList(req);
  }

  public @Override void queryPageListExport(CheckReportExportQueryPageListReq req) {
    DynamicsPage<CheckReportExportQueryPageListInfoRes> page = queryPageList(req);
    List<CheckReportExportQueryPageListInfoRes> list = page.getDataList();
    // 类型转换，  更换枚举 等操作
    List<CheckReportExportQueryPageListInfoRes> listInfoRes = $.copyList(list, CheckReportExportQueryPageListInfoRes.class);
    PoiExcelUtil.export(CheckReportExportQueryPageListInfoRes.class, listInfoRes, "报表信息");
  }

  public @Override CheckReportImportRes importData(@RequestParam("file") MultipartFile file) {
    List<CheckReportImportReq> reqList = PoiExcelUtil.readData(file, new CheckReportImportListener(), CheckReportImportReq.class);
    // 类型转换，  更换枚举 等操作
    List<CheckReport> readList = $.copyList(reqList, CheckReport.class);
    boolean bool = checkReportService.saveBatch(readList);
    int c = bool ? readList.size() : 0;
    return new CheckReportImportRes().setCount(c);
  }

  public @Override CheckReportQueryByIdListRes queryByIdListRes(CheckReportQueryByIdListReq req) {
    MPJLambdaWrapper<CheckReport> q = new MPJLambdaWrapper<CheckReport>(CheckReport.class)
        .selectAll(CheckReport.class).in(CheckReport::getId, req.getIdList());
    List<CheckReport> list = this.checkReportService.list(q);
    List<CheckReportDto> dataList = $.copyList(list, CheckReportDto.class);
    return new CheckReportQueryByIdListRes().setDataList(dataList);
  }

  @Override
  @MethodExt(isDownLoad = true, downLoadErrorMsg = "下载失败,请稍后再试", downLoadFileSuffix = FileSuffix.XLSX)
  public void downLoad(CheckReportDownLoadReq req) {
    CheckReport report = this.checkReportService.getById(req.getId());
    $.requireNonNullCanIgnoreException(report, "报表信息不存在");
    CheckReportListFactoryDataRes factoryData = this.checkReportListApi.factoryData(new CheckReportListFactoryDataReq().setCheckId(req.getId()));
    EasyExcelUtilExportMultipleData exportMultipleData = new EasyExcelUtilExportMultipleData();
    List<SheetData> sheetDataList = Collections.synchronizedList(new ArrayList<>());
    // 工厂数据
    {
      List<SheetHeader> headerList = new ArrayList<>();
      headerList.add(new SheetHeader().setFieldName("storeyName").setShowName("楼层名称"));
      headerList.add(new SheetHeader().setFieldName("roomName").setShowName("房间名称"));
      headerList.add(new SheetHeader().setFieldName("checkCount").setShowName("已检查数"));
      headerList.add(new SheetHeader().setFieldName("allCount").setShowName("所有资产数"));
      headerList.add(new SheetHeader().setFieldName("checkResult").setShowName("盘点结果"));
      factoryData.getDataList().forEach(data -> {
        data.setCheckResult(Objects.equals(data.getCheckCount(), data.getAllCount()) ? "正常" : "缺失");
      });
      sheetDataList.add(new SheetData().setSheetName("盘点概览").setHeaderList(headerList).setDataList(factoryData.getDataList()));
    }
    List<Room> roomList = roomService.list(new LambdaQueryWrapper<Room>().eq(Room::getFactoryId, report.getFactoryId()));
    List<Runnable> runnableList = new ArrayList<>();
    roomList.forEach(room -> {
      runnableList.add(() -> {
        CheckReportListRoomDataRes roomedData = this.checkReportListApi.roomData(new CheckReportListRoomDataReq().setCheckId(req.getId()).setRoomId(room.getId()));
        List<SheetHeader> headerList = new ArrayList<>();
        headerList.add(new SheetHeader().setFieldName("propertyName").setShowName("资产名称"));
        headerList.add(new SheetHeader().setFieldName("isCheck").setShowName("是否盘点"));
        headerList.add(new SheetHeader().setFieldName("checkDate").setShowName("盘点日期"));
        sheetDataList.add(new SheetData().setSheetName(room.getRoomName()).setHeaderList(headerList).setDataList(roomedData.getDataList()));
      });
    });

    RunUtils.run("downLoad", runnableList);
    PoiExcelUtil.exportMultipleData(exportMultipleData.setSheetDataList(sheetDataList).setFileName(report.getReportName() + "盘点报表"));
  }
}
