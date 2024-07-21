package com.olivia.peanut.aps.model;


import java.time.LocalDate;
import java.time.LocalDateTime;
import com.olivia.sdk.utils.BaseEntity;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import com.baomidou.mybatisplus.annotation.TableName;

/**
 * 排产下发详情(ApsSchedulingIssueItem)表实体类
 *
 * @author peanut
 * @since 2024-07-20 13:55:55
 */
@Accessors(chain = true)
@Getter
@Setter
//@SuppressWarnings("serial")
@TableName("aps_scheduling_issue_item")
public class ApsSchedulingIssueItem extends BaseEntity {



  private Long schedulingVersionId;
  /***
   *  当前日期
   */
  private String currentDay;
  /***
   *  订单ID
   */
  private Long orderId;
  /***
   *  商品ID
   */
  private Long goodsId;
  /***
   *  生产序号
   */
  private Long numberIndex;
  /***
   *  工厂id
   */
  private Long factoryId;


}

