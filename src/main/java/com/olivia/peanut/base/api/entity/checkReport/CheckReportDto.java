package com.olivia.peanut.base.api.entity.checkReport;

import com.alibaba.excel.annotation.ExcelIgnore;
import com.alibaba.excel.annotation.ExcelProperty;
import com.olivia.peanut.portal.api.entity.BaseEntityDto;
import lombok.Getter;
import lombok.Setter;

/**
 * 报表信息(CheckReport)查询对象返回
 *
 * @author makejava
 * @since 2024-03-14 13:31:36
 */
//@Accessors(chain=true)
@Getter
@Setter

public class CheckReportDto extends BaseEntityDto {


  /***
   *  工厂ID
   */
  @ExcelIgnore
  private Long factoryId;
  @ExcelProperty("工厂")
  private String factoryName;
  /***
   *  报表编码
   */
  @ExcelProperty("报表编码")
  private String reportCode;
  /***
   *  报表名称
   */
  @ExcelProperty("报表名称")
  private String reportName;


  @ExcelProperty("是否结束")
  private Boolean isOver;
  private String isOverStr;
}


