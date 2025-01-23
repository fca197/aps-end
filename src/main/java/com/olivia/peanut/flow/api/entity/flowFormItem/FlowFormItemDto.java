package com.olivia.peanut.flow.api.entity.flowFormItem;

import com.olivia.peanut.portal.api.entity.BaseEntityDto;
import com.olivia.sdk.ann.InsertCheck;
import com.olivia.sdk.ann.UpdateCheck;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

/**
 * 工作流表单项表(FlowFormItem)查询对象返回
 *
 * @author peanut
 * @since 2024-08-02 23:26:26
 */
//@Accessors(chain=true)
@Getter
@Setter
@SuppressWarnings("serial")
public class FlowFormItemDto extends BaseEntityDto {

  /***
   *  表单ID
   */
  @NotNull(message = "表单ID不能为空", groups = {InsertCheck.class, UpdateCheck.class})
  private Long formId;
  /***
   *  表单项名称
   */
  @NotBlank(message = "表单项名称不能为空", groups = {InsertCheck.class, UpdateCheck.class})
  private String formItemName;
  /***
   *  表单项字段
   */
  @NotBlank(message = "表单项字段不能为空", groups = {InsertCheck.class, UpdateCheck.class})
  private String formItemFiled;
  /***
   *  表单项类型
   */
  @NotBlank(message = "表单项类型不能为空", groups = {InsertCheck.class, UpdateCheck.class})
  private String formItemValue;
  /***
   *  表单项默认值
   */
  @NotBlank(message = "表单项默认值不能为空", groups = {InsertCheck.class, UpdateCheck.class})
  private String formItemDefaultValue;
  /***
   *  表单值类型  text , date , dateTime ,array
   */
  @NotBlank(message = "表单值类型  text , date , dateTime ,array不能为空", groups = {InsertCheck.class, UpdateCheck.class})
  private String formItemValueType;
  /***
   *  是否添加流程变量
   */
  @NotNull(message = "是否添加流程变量不能为空", groups = {InsertCheck.class, UpdateCheck.class})
  private Boolean isAddFlowValue;
  /***
   *  是否必填
   */
  @NotNull(message = "是否必填不能为空", groups = {InsertCheck.class, UpdateCheck.class})
  private Boolean isRequired;
  /***
   *  排序
   */
  @NotNull(message = "排序不能为空", groups = {InsertCheck.class, UpdateCheck.class})
  private Integer sortIndex;
  /***
   *  失去焦点事件
   */
  @NotBlank(message = "失去焦点事件不能为空", groups = {InsertCheck.class, UpdateCheck.class})
  private String loseFocusEvent;

}


