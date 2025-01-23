package com.olivia.peanut.aps.model;


import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.olivia.sdk.utils.BaseEntity;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.util.List;
import java.util.Objects;

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
  private String orderNo;
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


  @TableField(exist = false)
  private List<Long> projectConfigIdList;
  @TableField(exist = false)
  private List<Long> saleConfigIdList;
  @TableField(exist = false)
  private List<Long> bomIdList;

  public List<Long> getBomIdList() {
    return Objects.nonNull(bomIdList) ? bomIdList : List.of();
  }

  public List<Long> getSaleConfigIdList() {
    return Objects.nonNull(saleConfigIdList) ? saleConfigIdList : List.of();
  }

  public List<Long> getProjectConfigIdList() {
    return Objects.nonNull(projectConfigIdList) ? projectConfigIdList : List.of();
  }
}

