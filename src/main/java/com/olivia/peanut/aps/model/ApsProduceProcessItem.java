package com.olivia.peanut.aps.model;


import com.baomidou.mybatisplus.annotation.TableName;
import com.olivia.sdk.utils.BaseEntity;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * aps 生产机器(ApsProduceProcessItem)表实体类
 *
 * @author makejava
 * @since 2024-10-24 17:00:21
 */
@Accessors(chain = true)
@Getter
@Setter
//@SuppressWarnings("serial")
@TableName("aps_produce_process_item")
public class ApsProduceProcessItem extends BaseEntity {
  /***
   *  生产路径 Id aps_produce_process
   */
  private Long produceProcessId;
  /***
   *  机器ID
   */
  private Long machineId;

  /***
   * 状态
   */
  private Long statusId;

  /***
   *  耗时（秒）
   */
  private Long machineUseTimeSecond;

}

