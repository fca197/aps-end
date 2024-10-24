package com.olivia.peanut.flow.api.entity.flowForm;

import com.olivia.peanut.flow.api.entity.flowFormItem.FlowFormItemDto;
import com.olivia.peanut.portal.api.entity.BaseEntityDto;
import com.olivia.sdk.ann.InsertCheck;
import com.olivia.sdk.ann.UpdateCheck;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

/**
 * 工作流表单表(FlowForm)查询对象返回
 *
 * @author peanut
 * @since 2024-08-02 23:26:22
 */
//@Accessors(chain=true)
@Getter
@Setter
@SuppressWarnings("serial")
public class FlowFormDto extends BaseEntityDto {

  /***
   *  表单名称
   */
  @NotBlank(message = "表单名称不能为空", groups = {InsertCheck.class, UpdateCheck.class})
  private String formName;
  /***
   *  表单编码
   */
  @NotBlank(message = "表单编码不能为空", groups = {InsertCheck.class, UpdateCheck.class})
  private String formCode;

  @Size(max = 100, message = "表单字段不能超过100个")
  @NotNull(message = "表单字段不能为空", groups = {InsertCheck.class, UpdateCheck.class})
  private List<FlowFormItemDto> flowFormItemDtoList;
}


