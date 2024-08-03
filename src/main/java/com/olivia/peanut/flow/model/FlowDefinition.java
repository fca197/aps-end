package com.olivia.peanut.flow.model;


import java.time.LocalDate;
import java.time.LocalDateTime;
import com.olivia.sdk.utils.BaseEntity;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import com.baomidou.mybatisplus.annotation.TableName;

/**
 * 工作定义表(FlowDefinition)表实体类
 *
 * @author peanut
 * @since 2024-08-01 10:43:49
 */
@Accessors(chain = true)
@Getter
@Setter
//@SuppressWarnings("serial")
@TableName("flow_definition")
public class FlowDefinition extends BaseEntity {

  /***
   *  工作流名称
   */
  private String flowName;
  private String flowKey;
//  private String
  /***
   *  工作流组ID
   */
  private Long flowGroupId;
  private Long flowFormId;
}

