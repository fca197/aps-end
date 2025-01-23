package com.olivia.peanut.flow.model;


import com.baomidou.mybatisplus.annotation.TableName;
import com.olivia.sdk.utils.BaseEntity;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * 工作流组表(FlowGroup)表实体类
 *
 * @author peanut
 * @since 2024-08-01 10:43:53
 */
@Accessors(chain = true)
@Getter
@Setter
//@SuppressWarnings("serial")
@TableName("flow_group")
public class FlowGroup extends BaseEntity {

  /***
   *  工作流组编码
   */
  private String flowGroupCode;
  /***
   *  工作流组名称
   */
  private String flowGroupName;

}

