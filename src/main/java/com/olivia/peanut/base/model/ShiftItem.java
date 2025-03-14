package com.olivia.peanut.base.model;


import com.baomidou.mybatisplus.annotation.TableName;
import com.olivia.sdk.utils.BaseEntity;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.time.LocalTime;

/**
 * (ShiftItem)表实体类
 *
 * @author peanut
 * @since 2024-04-04 12:10:16
 */
@Accessors(chain = true)
@Getter
@Setter
//@SuppressWarnings("serial")
@TableName("t_shift_item")
public class ShiftItem extends BaseEntity {

  private Long shiftId;
  private LocalTime beginTime;
  private LocalTime endTime;
  private Long factoryId;

}

