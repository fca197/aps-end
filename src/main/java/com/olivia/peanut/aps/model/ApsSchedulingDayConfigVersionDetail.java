package com.olivia.peanut.aps.model;


import com.baomidou.mybatisplus.annotation.TableName;
import com.olivia.sdk.utils.BaseEntity;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * 排程版本配置明细表(ApsSchedulingDayConfigVersionDetail)表实体类
 *
 * @author peanut
 * @since 2024-07-19 19:19:57
 */
@Accessors(chain = true)
@Getter
@Setter
//@SuppressWarnings("serial")
@TableName("aps_scheduling_day_config_version_detail")
public class ApsSchedulingDayConfigVersionDetail extends BaseEntity {

  private Long schedulingDayId;
  /***
   *  配置类型 sale,part,bom
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

  private Long roomId;
  private Long statusId;
  private Integer sortIndex;
  /***
   *  订单ID
   */
  private Long orderId;
  /***
   *  订单编号
   */
  private String orderNo;
  /***
   *  是否匹配
   */
  private Boolean isMatch;
  /***
   *  循环次数
   */
  private Integer loopIndex;
  /***
   *  是否满足
   */
  private Boolean loopEnough;

}

