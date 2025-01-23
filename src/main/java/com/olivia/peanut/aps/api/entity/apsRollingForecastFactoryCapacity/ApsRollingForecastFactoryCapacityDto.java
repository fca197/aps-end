package com.olivia.peanut.aps.api.entity.apsRollingForecastFactoryCapacity;

import com.olivia.peanut.portal.api.entity.BaseEntityDto;
import com.olivia.sdk.ann.InsertCheck;
import com.olivia.sdk.ann.UpdateCheck;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * 滚动预测(ApsRollingForecastFactoryCapacity)查询对象返回
 *
 * @author peanut
 * @since 2024-07-14 20:22:23
 */
//@Accessors(chain=true)
@Getter
@Setter
@SuppressWarnings("serial")
public class ApsRollingForecastFactoryCapacityDto extends BaseEntityDto {

  /***
   *  工厂ID
   */
  @NotNull(message = "工厂不能为空", groups = {InsertCheck.class, UpdateCheck.class})
  private Long factoryId;
  private String factoryName;
  /***
   *  年份
   */
  @NotNull(message = "年份不能为空", groups = {})
  private Integer year;
  /***
   *  月份
   */
  @NotNull(message = "月份不能为空", groups = {})
  private Integer month;
  /***
   *  01日期产量
   */
  @NotNull(message = "01日期产量不能为空", groups = {})
  private Integer day01;
  /***
   *  02日期产量
   */
  @NotNull(message = "02日期产量不能为空", groups = {})
  private Integer day02;
  /***
   *  03日期产量
   */
  @NotNull(message = "03日期产量不能为空", groups = {})
  private Integer day03;
  /***
   *  04日期产量
   */
  @NotNull(message = "04日期产量不能为空", groups = {})
  private Integer day04;
  /***
   *  05日期产量
   */
  @NotNull(message = "05日期产量不能为空", groups = {})
  private Integer day05;
  /***
   *  06日期产量
   */
  @NotNull(message = "06日期产量不能为空", groups = {})
  private Integer day06;
  /***
   *  07日期产量
   */
  @NotNull(message = "07日期产量不能为空", groups = {})
  private Integer day07;
  /***
   *  08日期产量
   */
  @NotNull(message = "08日期产量不能为空", groups = {})
  private Integer day08;
  /***
   *  09日期产量
   */
  @NotNull(message = "09日期产量不能为空", groups = {})
  private Integer day09;
  /***
   *  10日期产量
   */
  @NotNull(message = "10日期产量不能为空", groups = {})
  private Integer day10;
  /***
   *  11日期产量
   */
  @NotNull(message = "11日期产量不能为空", groups = {})
  private Integer day11;
  /***
   *  12日期产量
   */
  @NotNull(message = "12日期产量不能为空", groups = {})
  private Integer day12;
  /***
   *  13日期产量
   */
  @NotNull(message = "13日期产量不能为空", groups = {})
  private Integer day13;
  /***
   *  14日期产量
   */
  @NotNull(message = "14日期产量不能为空", groups = {})
  private Integer day14;
  /***
   *  15日期产量
   */
  @NotNull(message = "15日期产量不能为空", groups = {})
  private Integer day15;
  /***
   *  16日期产量
   */
  @NotNull(message = "16日期产量不能为空", groups = {})
  private Integer day16;
  /***
   *  17日期产量
   */
  @NotNull(message = "17日期产量不能为空", groups = {})
  private Integer day17;
  /***
   *  18日期产量
   */
  @NotNull(message = "18日期产量不能为空", groups = {})
  private Integer day18;
  /***
   *  19日期产量
   */
  @NotNull(message = "19日期产量不能为空", groups = {})
  private Integer day19;
  /***
   *  20日期产量
   */
  @NotNull(message = "20日期产量不能为空", groups = {})
  private Integer day20;
  /***
   *  21日期产量
   */
  @NotNull(message = "21日期产量不能为空", groups = {})
  private Integer day21;
  /***
   *  22日期产量
   */
  @NotNull(message = "22日期产量不能为空", groups = {})
  private Integer day22;
  /***
   *  23日期产量
   */
  @NotNull(message = "23日期产量不能为空", groups = {})
  private Integer day23;
  /***
   *  24日期产量
   */
  @NotNull(message = "24日期产量不能为空", groups = {})
  private Integer day24;
  /***
   *  25日期产量
   */
  @NotNull(message = "25日期产量不能为空", groups = {})
  private Integer day25;
  /***
   *  26日期产量
   */
  @NotNull(message = "26日期产量不能为空", groups = {})
  private Integer day26;
  /***
   *  27日期产量
   */
  @NotNull(message = "27日期产量不能为空", groups = {})
  private Integer day27;
  /***
   *  28日期产量
   */
  @NotNull(message = "28日期产量不能为空", groups = {})
  private Integer day28;
  /***
   *  29日期产量
   */
  @NotNull(message = "29日期产量不能为空", groups = {})
  private Integer day29;
  /***
   *  30日期产量
   */
  @NotNull(message = "30日期产量不能为空", groups = {})
  private Integer day30;
  /***
   *  31日期产量
   */
  @NotNull(message = "31日期产量不能为空", groups = {})
  private Integer day31;
  /***
   *  容量
   */
  @NotNull(message = "容量不能为空", groups = {})
  private Integer capacity;


  @NotNull(message = "产能不能为空", groups = {InsertCheck.class, UpdateCheck.class})
  @Size(min = 1, max = 20, message = "产能不能为空", groups = {InsertCheck.class, UpdateCheck.class})
  private List<ApsRollingForecastFactoryCapacityDtoInfo> capacityList;


}



