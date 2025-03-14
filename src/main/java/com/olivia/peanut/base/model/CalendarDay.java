package com.olivia.peanut.base.model;


import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.olivia.sdk.utils.BaseEntity;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * 工作日历详情(CalendarDay)表实体类
 *
 * @author makejava
 * @since 2024-03-11 10:44:03
 */
@Accessors(chain = true)
@Getter
@Setter
//
@TableName("t_calendar_day")
public class CalendarDay extends BaseEntity {

  /***
   *  id
   */
  private Long id;
  /***
   *  所属工厂id
   */
  private Long factoryId;
  /***
   *  日历id
   */
  private Long calendarId;
  /***
   *  年
   */
  private Integer dayYear;
  /***
   *  月
   */
  private Integer dayMonth;
  /***
   *  天 ,0休息, 1 上班
   */
  @TableField("day_1")
  private Boolean day1;
  /***
   *  天 ,0休息, 1 上班
   */

  @TableField("day_2")
  private Boolean day2;
  /***
   *  天 ,0休息, 1 上班
   */
  @TableField("day_3")

  private Boolean day3;
  /***
   *  天 ,0休息, 1 上班
   */
  @TableField("day_4")

  private Boolean day4;
  /***
   *  天 ,0休息, 1 上班
   */
  @TableField("day_5")
  private Boolean day5;
  /***
   *  天 ,0休息, 1 上班
   */
  @TableField("day_6")
  private Boolean day6;
  /***
   *  天 ,0休息, 1 上班
   */
  @TableField("day_7")
  private Boolean day7;
  /***
   *  天 ,0休息, 1 上班
   */
  @TableField("day_8")
  private Boolean day8;
  /***
   *  天 ,0休息, 1 上班
   */
  @TableField("day_9")
  private Boolean day9;
  /***
   *  天 ,0休息, 1 上班
   */
  @TableField("day_10")
  private Boolean day10;
  /***
   *  天 ,0休息, 1 上班
   */
  @TableField("day_11")
  private Boolean day11;
  /***
   *  天 ,0休息, 1 上班
   */
  @TableField("day_12")
  private Boolean day12;
  /***
   *  天 ,0休息, 1 上班
   */
  @TableField("day_13")
  private Boolean day13;
  /***
   *  天 ,0休息, 1 上班
   */
  @TableField("day_14")
  private Boolean day14;
  /***
   *  天 ,0休息, 1 上班
   */
  @TableField("day_15")
  private Boolean day15;
  /***
   *  天 ,0休息, 1 上班
   */
  @TableField("day_16")
  private Boolean day16;
  /***
   *  天 ,0休息, 1 上班
   */
  @TableField("day_17")
  private Boolean day17;
  /***
   *  天 ,0休息, 1 上班
   */
  @TableField("day_18")
  private Boolean day18;
  /***
   *  天 ,0休息, 1 上班
   */
  @TableField("day_19")
  private Boolean day19;
  /***
   *  天 ,0休息, 1 上班
   */
  @TableField("day_20")
  private Boolean day20;
  /***
   *  天 ,0休息, 1 上班
   */
  @TableField("day_21")
  private Boolean day21;
  /***
   *  天 ,0休息, 1 上班
   */
  @TableField("day_22")
  private Boolean day22;
  /***
   *  天 ,0休息, 1 上班
   */
  @TableField("day_23")
  private Boolean day23;
  /***
   *  天 ,0休息, 1 上班
   */
  @TableField("day_24")
  private Boolean day24;
  /***
   *  天 ,0休息, 1 上班
   */
  @TableField("day_25")
  private Boolean day25;
  /***
   *  天 ,0休息, 1 上班
   */
  @TableField("day_26")
  private Boolean day26;
  /***
   *  天 ,0休息, 1 上班
   */
  @TableField("day_27")
  private Boolean day27;
  /***
   *  天 ,0休息, 1 上班
   */
  @TableField("day_28")
  private Boolean day28;
  /***
   *  天 ,0休息, 1 上班
   */
  @TableField("day_29")
  private Boolean day29;
  /***
   *  天 ,0休息, 1 上班
   */
  @TableField("day_30")
  private Boolean day30;
  /***
   *  天 ,0休息, 1 上班
   */
  @TableField("day_31")
  private Boolean day31;

  public String getDayMonthAddZero() {
    return dayMonth < 10 ? "0" + dayMonth : dayMonth + "";
  }
}

