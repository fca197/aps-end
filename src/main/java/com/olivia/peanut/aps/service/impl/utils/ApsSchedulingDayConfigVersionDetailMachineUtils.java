package com.olivia.peanut.aps.service.impl.utils;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.date.DateUtil;
import cn.hutool.extra.spring.SpringUtil;
import com.olivia.peanut.aps.api.entity.apsSchedulingDayConfigVersionDetailMachine.ApsSchedulingDayConfigVersionDetailMachineDto;
import com.olivia.peanut.aps.api.entity.apsSchedulingDayConfigVersionDetailMachine.ApsSchedulingDayConfigVersionDetailMachineExportQueryPageListInfoRes;
import com.olivia.peanut.aps.api.entity.apsSchedulingDayConfigVersionDetailMachine.ApsSchedulingDayConfigVersionDetailMachineExportQueryPageListReq;
import com.olivia.peanut.aps.model.ApsMachine;
import com.olivia.peanut.aps.service.ApsMachineService;
import com.olivia.sdk.utils.PoiExcelUtil;
import com.olivia.sdk.utils.ReqResUtils;
import com.olivia.sdk.utils.Str;
import jakarta.servlet.ServletOutputStream;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.streaming.SXSSFCell;
import org.apache.poi.xssf.streaming.SXSSFRow;
import org.apache.poi.xssf.streaming.SXSSFSheet;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

@Slf4j
public class ApsSchedulingDayConfigVersionDetailMachineUtils {
  public static void downLoad(ApsSchedulingDayConfigVersionDetailMachineExportQueryPageListReq req, List<ApsSchedulingDayConfigVersionDetailMachineExportQueryPageListInfoRes> list) {

    try (ServletOutputStream servletOutputStream = ReqResUtils.getOutputStream4downLoad("排程详情-" + DateUtil.today() + ".XLSX");  //
         SXSSFWorkbook workbook = new SXSSFWorkbook()) {
      setData2WorkBook(req, list, workbook);
      workbook.write(servletOutputStream);
    } catch (Exception e) {
      log.error("文件下载失败 {}", e.getMessage(), e);
    }
  }

  private static void setData2WorkBook(ApsSchedulingDayConfigVersionDetailMachineExportQueryPageListReq req, List<ApsSchedulingDayConfigVersionDetailMachineExportQueryPageListInfoRes> list, SXSSFWorkbook workbook) {
    if (CollUtil.isEmpty(list)) {
      log.info("排程版本 {} 数据为空", req.getData().getSchedulingDayId());
      return;
    }
    log.info("排程版本 {} 数据为 {} 条", req.getData().getSchedulingDayId(), list.size());

    List<ApsMachine> machineList = SpringUtil.getBean(ApsMachineService.class).list();

    machineList.sort(Comparator.comparing(ApsMachine::getSortIndex));

    List<String> orderNoList = list.stream().map(ApsSchedulingDayConfigVersionDetailMachineDto::getOrderNo).toList();
    Map<String, CellStyle> orderStyleMap = new HashMap<>();
    List<IndexedColors> colorsList = Arrays.asList(IndexedColors.values());

    int ci = 0;
    for (String orderNo : orderNoList) {
      CellStyle style = workbook.createCellStyle();
      style.setFillForegroundColor(colorsList.get(ci).index);
      style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
      orderStyleMap.put(orderNo, style);
      ci++;
      ci = ci > colorsList.size() - 1 ? 0 : ci;
    }

    Map<Long, List<ApsSchedulingDayConfigVersionDetailMachineExportQueryPageListInfoRes>> machineMap = list.stream().collect(Collectors.groupingBy(ApsSchedulingDayConfigVersionDetailMachineDto::getMachineId));
    LocalDateTime beginLocalDateTime = list.stream().map(ApsSchedulingDayConfigVersionDetailMachineDto::getBeginDateTime).sorted().limit(1).toList().getFirst();
    LocalDateTime endLocalDateTime = list.stream().map(ApsSchedulingDayConfigVersionDetailMachineDto::getBeginDateTime).sorted(Comparator.reverseOrder()).limit(1).toList().getFirst();
    log.info("beginLocalDateTime {} endLocalDateTime {}", beginLocalDateTime, endLocalDateTime);
    long bt = getTimeIndex(req, beginLocalDateTime);
    long et = getTimeIndex(req, endLocalDateTime);
    long timeStep = et - bt;
    log.info("bt {} et {} timeStep {}", bt, et, timeStep);

    Map<PoiExcelUtil.CellStyleEnum, CellStyle> styleMap = PoiExcelUtil.createStyles(workbook);
    CellStyle headerCellStyle = styleMap.get(PoiExcelUtil.CellStyleEnum.HEADER);
    CellStyle bodyCellStyle = styleMap.get(PoiExcelUtil.CellStyleEnum.BODY);
    SXSSFSheet sheet = workbook.createSheet("排程详情");
    SXSSFRow row = sheet.createRow(0);
    AtomicInteger index = new AtomicInteger(0);
    SXSSFCell hc = row.createCell(index.getAndIncrement());
    hc.setCellStyle(headerCellStyle);
    hc.setCellValue("机器名称");
    long epochSecond = beginLocalDateTime.toEpochSecond(ZoneOffset.of(Str.OFFSET_ID)) * 1000;
    for (int i = 0; i < timeStep; i++) {
      SXSSFCell cell = row.createCell(index.getAndIncrement());
      cell.setCellStyle(headerCellStyle);
      cell.setCellValue(DateUtil.formatDateTime(new Date(epochSecond + i * req.getTimeSpan() * 1000)).substring(0, 17) + "00");
    }
//    IntStream.range(0, index.get()).forEach(i -> row.getCell(i).setCellStyle(headerCellStyle));

    short lastCellNum = sheet.getRow(0).getLastCellNum();
    for (int i = 0; i < lastCellNum; i++) {
      sheet.setColumnWidth(i, 20 * 256);
    }
    list.forEach(t -> {
      t.setOBIndex(getTimeIndex(req, t.getBeginDateTime()));
      t.setOEIndex(getTimeIndex(req, t.getEndDateTime()));
      t.setColSpan(t.getOEIndex() - t.getOBIndex() + 1);
      t.setCellIndex(t.getOBIndex() - bt);
    });


    AtomicInteger rowIndex = new AtomicInteger(1);
    machineList.forEach(machine -> {
      String machineName = machine.getMachineName();
      List<ApsSchedulingDayConfigVersionDetailMachineExportQueryPageListInfoRes> ml = machineMap.getOrDefault(machine.getId(), List.of());
      Map<Long, List<ApsSchedulingDayConfigVersionDetailMachineExportQueryPageListInfoRes>> machineTimeStemOrderMap =
          ml.stream().collect(Collectors.groupingBy(ApsSchedulingDayConfigVersionDetailMachineExportQueryPageListInfoRes::getCellIndex, Collectors.collectingAndThen(Collectors.toList(), a -> a.stream().sorted(Comparator.comparing(ApsSchedulingDayConfigVersionDetailMachineDto::getBeginDateTime)).collect(Collectors.toList()))));

      log.info("write excel {} ml {} {}", machineName, ml.size(), ml.stream().map(ApsSchedulingDayConfigVersionDetailMachineDto::getOrderNo).toList());

      int maxOrderList = machineTimeStemOrderMap.values().stream().mapToInt(Collection::size).max().orElse(0);
      for (int i = 0; i < maxOrderList; i++) {
        SXSSFRow rt = sheet.createRow(rowIndex.get() + i);
        SXSSFCell cell = rt.createCell(0);
        cell.setCellStyle(bodyCellStyle);
        cell.setCellValue(machineName);
        for (int j = 0; j < timeStep; j++) {
          rt.createCell(j + 1);
        }
      }

      mergedSheet(sheet, rowIndex.get(), rowIndex.get() + maxOrderList - 1, 0, 0);

      int max = 0;
      for (int i = 0; i < timeStep; i++) {
        List<ApsSchedulingDayConfigVersionDetailMachineExportQueryPageListInfoRes> stemOrderList = machineTimeStemOrderMap.get((long) i);
        if (CollUtil.isEmpty(stemOrderList)) {
          continue;
        }
        stemOrderList.sort(Comparator.nullsFirst(Comparator.comparing(ApsSchedulingDayConfigVersionDetailMachineDto::getBeginDateTime)));
        for (int j = 0; j < stemOrderList.size(); j++) {
          int rowNum = rowIndex.get() + j;
          SXSSFRow tmpRow = sheet.getRow(rowNum);
//          tmpRow.createCell(0).setCellValue(stemOrderList.getFirst().getMachineName());
          ApsSchedulingDayConfigVersionDetailMachineExportQueryPageListInfoRes orderInfo = stemOrderList.get(j);
          SXSSFCell cell = tmpRow.getCell(i + 1);
          cell.setCellValue(orderInfo.getOrderNo());
          mergedSheet(sheet, rowNum, rowNum, (int) (orderInfo.getOBIndex().intValue() - bt) + 1, (int) (orderInfo.getOEIndex() - bt));
          cell.setCellStyle(orderStyleMap.get(orderInfo.getOrderNo()));
        }

      }
      rowIndex.addAndGet(maxOrderList);
      log.info("machineName {}  rowIndex {}  maxRowIndex  {}", machineName, max, rowIndex);
    });
  }

  private static void mergedSheet(SXSSFSheet sheet, int firstRow, int lastRow, int firstCol, int lastCol) {
    try {
      if (lastRow < firstRow || lastCol < firstCol) {
//        log.info("Invalid cell range, having lastRow < firstRow || lastCol < firstCol, had rows {} >= {} or cells {} >= {}", lastRow, firstRow, lastCol, firstCol);
        return;
      }
      sheet.addMergedRegion(new CellRangeAddress(firstRow, lastRow, firstCol, lastCol));
    } catch (Exception e) {
//      log.error("addMergedRegion {}", e.getMessage());
    }
  }

  private static long getTimeIndex(ApsSchedulingDayConfigVersionDetailMachineExportQueryPageListReq req, LocalDateTime localDateTime) {
    return localDateTime.toEpochSecond(ZoneOffset.of(Str.OFFSET_ID)) / req.getTimeSpan();
  }
}
