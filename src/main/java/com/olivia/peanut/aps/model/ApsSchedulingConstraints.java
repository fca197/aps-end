package com.olivia.peanut.aps.model;


import com.baomidou.mybatisplus.annotation.TableName;
import com.olivia.sdk.utils.BaseEntity;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * (ApsSchedulingConstraints)表实体类
 *
 * @author peanut
 * @since 2024-04-13 21:32:13
 */
@Accessors(chain = true)
@Getter
@Setter
//@SuppressWarnings("serial")
@TableName("aps_scheduling_constraints")
public class ApsSchedulingConstraints extends BaseEntity {

  private String constraintsNo;
  private String constraintsName;
  private String constraintsContext;
  private String constraintsRemark;

}

