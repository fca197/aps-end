package com.olivia.peanut.portal.api.entity.calendar;

import com.alibaba.excel.annotation.ExcelProperty;
import java.time.LocalTime;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * 工作日历(Calendar)查询对象返回
 *
 * @author makejava
 * @since 2024-03-11 10:44:03
 */
@Accessors(chain = true)
@Getter
@Setter

public class CalendarExportQueryPageListInfoRes {


  /***
   *  id
   */
  @ExcelProperty("id")
  private Long id;
  /***
   *  所属租户id
   */
  @ExcelProperty("所属租户id")
  private Long tenantId;
  /***
   *  所属工厂id
   */
  @ExcelProperty("所属工厂id")
  private Long factoryId;
  /***
   *  日历名称
   */
  @ExcelProperty("日历名称")
  private String calendarName;
  /***
   *  日历编码
   */
  @ExcelProperty("日历编码")
  private String calendarCode;
  /***
   *  日历类型
   */
  @ExcelProperty("日历类型 ")
  private String calendarType;
  /***
   *  日历描述
   */
  @ExcelProperty("日历描述")
  private String calendarDesc;
  /***
   *  日历状态 0 启用 ,1 禁用
   */
  @ExcelProperty("日历状态 0 启用 ,1 禁用  ")
  private Boolean calendarDisabled;


  /***
   *  创建时间
   */
  @ExcelProperty("创建时间")
  private LocalTime createTime;
  /***
   *  创建人id
   */
  @ExcelProperty("创建人id")
  private Long createBy;
  /***
   *  更新时间
   */
  @ExcelProperty("更新时间")
  private LocalTime updateTime;
  /***
   *  更新人id
   */
  @ExcelProperty("更新人id")
  private Long updateBy;
  /***
   *  调用链
   */
  @ExcelProperty("调用链")
  private String traceId;

}


