package com.olivia.peanut.aps.model;


import java.time.LocalDate;
import java.time.LocalDateTime;
import com.olivia.sdk.utils.BaseEntity;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import com.baomidou.mybatisplus.annotation.TableName;

/**
 * 排程版本配置明细表(ApsSchedulingDayConfigVersionDetail)表实体类
 *
 * @author peanut
 * @since 2024-07-19 15:05:09
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
  /***
   *  订单ID
   */
  private Long orderId;
  /***
   *  订单编号
   */
  private String orderNo;
  /***
   *  循环次数
   */
  private Integer loopIndex;
  /***
   *  是否匹配 0 否,1 是
   */
  private Boolean isMatch;

}

