package com.olivia.peanut.flow.api.entity.flowGroup;

import com.olivia.peanut.flow.api.entity.flowDefinition.FlowDefinitionDto;
import com.olivia.peanut.portal.api.entity.BaseEntityDto;
import com.olivia.sdk.ann.InsertCheck;
import com.olivia.sdk.ann.UpdateCheck;
import jakarta.validation.constraints.NotBlank;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

/**
 * 工作流组表(FlowGroup)查询对象返回
 *
 * @author peanut
 * @since 2024-08-01 10:43:53
 */
//@Accessors(chain=true)
@Getter
@Setter
@SuppressWarnings("serial")
public class FlowGroupDto extends BaseEntityDto {

  /***
   *  工作流组编码
   */
  @NotBlank(message = "工作流组编码不能为空", groups = {InsertCheck.class, UpdateCheck.class})
  private String flowGroupCode;
  /***
   *  工作流组名称
   */
  @NotBlank(message = "工作流组名称不能为空", groups = {InsertCheck.class, UpdateCheck.class})
  private String flowGroupName;

  private List<FlowDefinitionDto> flowList;

}


