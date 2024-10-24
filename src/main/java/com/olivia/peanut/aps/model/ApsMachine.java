package com.olivia.peanut.aps.model;


import java.time.LocalDate;
import java.time.LocalDateTime;

import com.olivia.sdk.utils.BaseEntity;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import com.baomidou.mybatisplus.annotation.TableName;

/**
 * aps 生产机器(ApsMachine)表实体类
 *
 * @author makejava
 * @since 2024-10-24 16:31:15
 */
@Accessors(chain = true)
@Getter
@Setter
//@SuppressWarnings("serial")
@TableName("aps_machine")
public class ApsMachine extends BaseEntity {
  /***
   *  机器编号
   */
  private String machineNo;
  /***
   *  机器名称
   */
  private String machineName;

}

