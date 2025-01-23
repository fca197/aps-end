package com.olivia.peanut.aps.model;


import com.baomidou.mybatisplus.annotation.TableName;
import com.olivia.sdk.utils.BaseEntity;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * 排程版本配置表(ApsSchedulingDayConfigItem)表实体类
 *
 * @author peanut
 * @since 2024-07-19 19:19:52
 */
@Accessors(chain = true)
@Getter
@Setter
//@SuppressWarnings("serial")
@TableName("aps_scheduling_day_config_item")
public class ApsSchedulingDayConfigItem extends BaseEntity {

  /***
   *  排程版本ID
   */
  private Long schedulingDayId;
  /***
   *  工艺路径ID
   */
  private Long processId;
  /***
   *  车间ID
   */
  private Long roomId;
  /***
   *  状态ID
   */
  private Long statusId;
  /***
   *  配置类型 sale,part,bom ,sleep
   */
  private String configBizType;
  /***
   *  配置业务ID
   */
  private Long configBizId;
  /***
   *  配置业务名称
   */
  private String configBizName;
  /***
   *  配置业务数量
   */
  private Long configBizNum;
  /***
   *  配置业务耗时(秒)
   */
  private Long configBizTime;
  /***
   *  是否默认
   */
  private Boolean isDefault;

}

