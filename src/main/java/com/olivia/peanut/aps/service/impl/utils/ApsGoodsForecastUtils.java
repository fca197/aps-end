package com.olivia.peanut.aps.service.impl.utils;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.extra.spring.SpringUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.olivia.peanut.aps.model.ApsGoods;
import com.olivia.peanut.aps.model.ApsGoodsForecast;
import com.olivia.peanut.aps.model.ApsGoodsSaleItem;
import com.olivia.peanut.aps.model.ApsSaleConfig;
import com.olivia.peanut.aps.service.ApsGoodsForecastService;
import com.olivia.peanut.aps.service.ApsGoodsSaleItemService;
import com.olivia.peanut.aps.service.ApsGoodsService;
import com.olivia.peanut.aps.service.ApsSaleConfigService;
import com.olivia.sdk.utils.$;
import com.olivia.sdk.utils.BaseEntity;
import com.olivia.sdk.utils.PoiExcelUtil;
import com.olivia.sdk.utils.PoiExcelUtil.CellStyleEnum;
import com.olivia.sdk.utils.ReqResUtils;
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.VerticalAlignment;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.streaming.SXSSFCell;
import org.apache.poi.xssf.streaming.SXSSFRow;
import org.apache.poi.xssf.streaming.SXSSFSheet;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;

import java.net.URLEncoder;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class ApsGoodsForecastUtils {

  public static void downloadTemplate(Long id) {
    ApsGoodsForecastService apsGoodsForecastService = SpringUtil.getBean(ApsGoodsForecastService.class);
    ApsGoodsForecast goodsForecast = apsGoodsForecastService.getById(id);
    $.requireNonNullCanIgnoreException(goodsForecast, "未找到数据");

    HttpServletResponse response = ReqResUtils.getResponse();
    SXSSFWorkbook workbook = new SXSSFWorkbook();
    ApsGoodsService goodsService = SpringUtil.getBean(ApsGoodsService.class);

    ApsGoodsSaleItemService goodsSaleItemService = SpringUtil.getBean(ApsGoodsSaleItemService.class);
    try (workbook; ServletOutputStream outputStream = response.getOutputStream()) {
      workbook.setCompressTempFiles(true);
      Map<CellStyleEnum, CellStyle> styleMap = PoiExcelUtil.createStyles(workbook);
      CellStyle headerCellStyle = styleMap.get(CellStyleEnum.HEADER);
      CellStyle bodyCellStyle = styleMap.get(CellStyleEnum.BODY);
      ApsGoods apsGoods = goodsService.getById(goodsForecast.getGoodsId());

      SXSSFSheet sheet = workbook.createSheet(apsGoods.getGoodsName());
      SXSSFRow row = sheet.createRow(0);
      CellStyle style = workbook.createCellStyle();
      SXSSFCell goodsNameCell = row.createCell(0);
      goodsNameCell.setCellValue(apsGoods.getGoodsName());
      style.setAlignment(HorizontalAlignment.CENTER);
      style.setVerticalAlignment(VerticalAlignment.CENTER);
      goodsNameCell.setCellStyle(style);
      SXSSFCell cell = row.createCell(2);
      cell.setCellStyle(headerCellStyle);
      cell.setCellValue("月份");
      List<String> monthList = goodsForecast.getMonthList();
      if (CollUtil.isNotEmpty(monthList)) {
        for (int i = 0; i < monthList.size(); i++) {
          cell = row.createCell(i + 3);
          cell.setCellStyle(headerCellStyle);
          cell.setCellValue("'" + monthList.get(i));
        }
        SXSSFCell cc = sheet.createRow(1).createCell(2);
        cc.setCellValue("总计");
        cc.setCellStyle(headerCellStyle);
        ApsSaleConfigService apsSaleConfigService = SpringUtil.getBean(ApsSaleConfigService.class);
        Map<Long, ApsSaleConfig> saleConfigMap = apsSaleConfigService.list().stream().collect(Collectors.toMap(BaseEntity::getId, Function.identity()));
        List<ApsGoodsSaleItem> apsGoodsSaleItemList = goodsSaleItemService.list(new LambdaQueryWrapper<ApsGoodsSaleItem>().eq(ApsGoodsSaleItem::getGoodsId, apsGoods.getId()));

        apsGoodsSaleItemList.forEach(item -> {
          ApsSaleConfig apsSaleConfig = saleConfigMap.getOrDefault(item.getSaleConfigId(), new ApsSaleConfig());
          item.setSaleConfigCode(apsSaleConfig.getSaleCode()).setSaleConfigName(apsSaleConfig.getSaleName()).setIsValue(apsSaleConfig.getIsValue());
          apsSaleConfig = saleConfigMap.getOrDefault(apsSaleConfig.getParentId(), new ApsSaleConfig());
          item.setParentSaleConfigCode(apsSaleConfig.getSaleCode()).setParentSaleConfigName(apsSaleConfig.getSaleName());
        });
        apsGoodsSaleItemList.removeIf(t -> !Objects.equals(t.getIsValue(), 1));
        apsGoodsSaleItemList.sort(Comparator.comparing(ApsGoodsSaleItem::getSaleConfigCode));
        SXSSFRow nameRow = sheet.createRow(2);
        SXSSFCell ct1 = nameRow.createCell(0);
        ct1.setCellValue("销售特征组");
        ct1.setCellStyle(headerCellStyle);
        SXSSFCell ct2 = nameRow.createCell(1);
        ct2.setCellValue("销售特征");
        ct2.setCellStyle(headerCellStyle);
        IntStream.range(0, apsGoodsSaleItemList.size()).forEach(i -> {
          SXSSFRow rowTmp = sheet.createRow(i + 3);
          ApsGoodsSaleItem apsGoodsSaleItem = apsGoodsSaleItemList.get(i);
          rowTmp.createCell(0).setCellValue(apsGoodsSaleItem.getParentSaleConfigCode() + "/" + apsGoodsSaleItem.getParentSaleConfigName());
          rowTmp.createCell(1).setCellValue(apsGoodsSaleItem.getSaleConfigCode() + "/" + apsGoodsSaleItem.getSaleConfigName());
        });
        sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, 1));
        IntStream.range(0, sheet.getRow(0).getLastCellNum()).forEach(i -> {
          sheet.setColumnWidth(i, 20 * 256);
        });
      }
      response.reset();
      response.setContentType("application/ms-excel;charset=UTF-8");
      response.setHeader("Content-Disposition", "attachment;filename=".concat(String.valueOf(URLEncoder.encode(goodsForecast.getForecastName() + ".xls", "UTF-8"))));
      workbook.write(outputStream);
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }


}
