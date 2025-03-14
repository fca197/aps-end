package com.olivia.peanut.base.api.entity.calendar;

import com.alibaba.excel.annotation.ExcelProperty;
import com.olivia.peanut.portal.api.entity.BaseEntityDto;
import com.olivia.sdk.ann.InsertCheck;
import com.olivia.sdk.ann.UpdateCheck;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

/**
 * 工作日历(Calendar)查询对象返回
 *
 * @author makejava
 * @since 2024-03-11 10:44:03
 */
//@Accessors(chain=true)
@Getter
@Setter

public class CalendarDto extends BaseEntityDto {


  /***
   *  所属工厂id
   */
  @ExcelProperty("所属工厂id")
  @NotNull(message = "所属工厂id不能为空", groups = {InsertCheck.class, UpdateCheck.class})
  private Long factoryId;
  /***
   *  日历名称
   */
  @NotBlank(message = "日历名称不能为空", groups = {InsertCheck.class, UpdateCheck.class})
  @ExcelProperty("日历名称")
  private String calendarName;
  /***
   *  日历编码
   */
  @ExcelProperty("日历编码")
  @NotBlank(message = "日历编码不能为空", groups = {InsertCheck.class, UpdateCheck.class})
  private String calendarCode;

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

}


