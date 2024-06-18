package com.olivia.test.forcecast;

import java.io.FileOutputStream;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFRichTextString;
import org.junit.jupiter.api.Test;

/***
 *
 */
@Slf4j
public class PoiTest {

  String str = "<table> <img  src='https://img-home.csdnimg.cn/images/20240129062750.png'/> </table>";

  @Test
  @SneakyThrows
  public void test() {
    Workbook wb = WorkbookFactory.create(true);
    Sheet sheet = wb.createSheet();
    sheet.createRow(0).createCell(0).setCellValue("当前时间 :" + LocalDateTime.now());
    Row row = sheet.createRow(1);
    CellStyle cellStyle = wb.createCellStyle();

    Cell cell = row.createCell(0);
//        cell.setCellFormula("<table> <img  src='https://img-home.csdnimg.cn/images/20240129062750.png'/> </table >");

    XSSFRichTextString richTextString = new XSSFRichTextString(str);
//        cell.setCellValue(richTextString);
//        richTextString.getCTRst().addNewPhoneticPr().set(new CTTableStyleInfoImpl(new SchemaTypeImpl()));
//        cell.setCellFormula(CellType.FORMULA);
    FileOutputStream fout = new FileOutputStream("test.xls");
    wb.write(fout);
    wb.close();
    fout.close();
    System.out.println("ok");
  }

  @Test
  public void test2() {
    List<List<String>> list = List.of(List.of("112", "33sa44"), List.of("wXxx", "aa"));

    List<String> ret = list.stream().flatMap(List::parallelStream).sorted().collect(Collectors.toList());
    log.info("ret:{}", ret);


  }
}
