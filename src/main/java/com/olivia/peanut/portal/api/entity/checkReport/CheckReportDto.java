package com.olivia.peanut.portal.api.entity.checkReport;

import com.alibaba.excel.annotation.ExcelIgnore;
import com.alibaba.excel.annotation.ExcelProperty;
import java.time.LocalDateTime;
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

public class CheckReportDto {


  /***
   *  id
   */
  @ExcelProperty("id")
  private Long id;
  /***
   *  所属租户id
   */
  @ExcelProperty("所属租户id")
  @ExcelIgnore
  private Long tenantId;
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


  /***
   *  创建时间
   */
  @ExcelProperty("创建时间")
  private LocalDateTime createTime;
  /***
   *  创建人id
   */
  @ExcelProperty("创建人id")
  private Long createBy;
  /***
   *  更新时间
   */
  @ExcelProperty("更新时间")
  private LocalDateTime updateTime;
  /***
   *  更新人id
   */
  @ExcelProperty("更新人id")
  private Long updateBy;
  /***
   *  链路追踪ID
   */
  @ExcelProperty("链路追踪ID")
  private String traceId;
  /***
   *  版本号
   */
  @ExcelProperty("版本号")
  private Integer versionNum;

  @ExcelProperty("是否结束")
  private Boolean isOver;
  private String isOverStr;
}


