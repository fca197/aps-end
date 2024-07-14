package com.olivia.peanut.aps.model;


import java.time.LocalDate;
import java.time.LocalDateTime;
import com.olivia.sdk.utils.BaseEntity;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import com.baomidou.mybatisplus.annotation.TableName;

/**
 * 滚动预测(ApsOrderRollingForecast)表实体类
 *
 * @author peanut
 * @since 2024-07-14 19:43:51
 */
@Accessors(chain = true)
@Getter
@Setter
//@SuppressWarnings("serial")
@TableName("aps_order_rolling_forecast")
public class ApsOrderRollingForecast extends BaseEntity {

  /***
   *  唯一编码
   */
  private String rollCode;
  /***
   *  名称
   */
  private String rollName;
  /***
   *  开始时间
   */
  private LocalDate beginTime;
  /***
   *  结束时间
   */
  private LocalDate endTime;

}

