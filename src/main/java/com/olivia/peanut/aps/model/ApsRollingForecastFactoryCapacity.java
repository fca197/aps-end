package com.olivia.peanut.aps.model;


import com.baomidou.mybatisplus.annotation.TableName;
import com.olivia.sdk.utils.BaseEntity;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.util.Objects;

/**
 * 滚动预测(ApsRollingForecastFactoryCapacity)表实体类
 *
 * @author peanut
 * @since 2024-07-14 20:22:23
 */
@Accessors(chain = true)
@Getter
@Setter
//@SuppressWarnings("serial")
@TableName("aps_rolling_forecast_factory_capacity")
public class ApsRollingForecastFactoryCapacity extends BaseEntity {

  /***
   *  工厂ID
   */
  private Long factoryId;
  /***
   *  年份
   */
  private Integer year;
  /***
   *  月份
   */
  private Integer month;
  /***
   *  01日期产量
   */
  private Integer day01;
  /***
   *  02日期产量
   */
  private Integer day02;
  /***
   *  03日期产量
   */
  private Integer day03;
  /***
   *  04日期产量
   */
  private Integer day04;
  /***
   *  05日期产量
   */
  private Integer day05;
  /***
   *  06日期产量
   */
  private Integer day06;
  /***
   *  07日期产量
   */
  private Integer day07;
  /***
   *  08日期产量
   */
  private Integer day08;
  /***
   *  09日期产量
   */
  private Integer day09;
  /***
   *  10日期产量
   */
  private Integer day10;
  /***
   *  11日期产量
   */
  private Integer day11;
  /***
   *  12日期产量
   */
  private Integer day12;
  /***
   *  13日期产量
   */
  private Integer day13;
  /***
   *  14日期产量
   */
  private Integer day14;
  /***
   *  15日期产量
   */
  private Integer day15;
  /***
   *  16日期产量
   */
  private Integer day16;
  /***
   *  17日期产量
   */
  private Integer day17;
  /***
   *  18日期产量
   */
  private Integer day18;
  /***
   *  19日期产量
   */
  private Integer day19;
  /***
   *  20日期产量
   */
  private Integer day20;
  /***
   *  21日期产量
   */
  private Integer day21;
  /***
   *  22日期产量
   */
  private Integer day22;
  /***
   *  23日期产量
   */
  private Integer day23;
  /***
   *  24日期产量
   */
  private Integer day24;
  /***
   *  25日期产量
   */
  private Integer day25;
  /***
   *  26日期产量
   */
  private Integer day26;
  /***
   *  27日期产量
   */
  private Integer day27;
  /***
   *  28日期产量
   */
  private Integer day28;
  /***
   *  29日期产量
   */
  private Integer day29;
  /***
   *  30日期产量
   */
  private Integer day30;
  /***
   *  31日期产量
   */
  private Integer day31;
  /***
   *  容量
   */
  private Integer capacity;

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ApsRollingForecastFactoryCapacity that = (ApsRollingForecastFactoryCapacity) o;
    return Objects.equals(factoryId, that.factoryId) && Objects.equals(year, that.year) && Objects.equals(month, that.month);
  }

  @Override
  public int hashCode() {
    return Objects.hash(factoryId, year, month);
  }
}

