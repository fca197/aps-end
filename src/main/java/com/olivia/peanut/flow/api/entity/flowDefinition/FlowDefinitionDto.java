package com.olivia.peanut.flow.api.entity.flowDefinition;

import com.olivia.peanut.portal.api.entity.BaseEntityDto;
import com.olivia.sdk.ann.InsertCheck;
import com.olivia.sdk.ann.UpdateCheck;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

/**
 * 工作定义表(FlowDefinition)查询对象返回
 *
 * @author peanut
 * @since 2024-08-01 10:43:49
 */
//@Accessors(chain=true)
@Getter
@Setter
@SuppressWarnings("serial")
public class FlowDefinitionDto extends BaseEntityDto {

  /***
   *  工作流名称
   */
  @NotBlank(message = "工作流名称不能为空", groups = {InsertCheck.class, UpdateCheck.class})
  private String flowName;
  /***
   *  工作流组ID
   */
  @NotNull(message = "工作流组ID不能为空", groups = {InsertCheck.class, UpdateCheck.class})
  private Long flowGroupId;


  @NotNull(message = "流程key不能为空")
  private String flowKey;
}


