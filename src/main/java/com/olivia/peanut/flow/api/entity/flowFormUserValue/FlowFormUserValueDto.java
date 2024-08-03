package com.olivia.peanut.flow.api.entity.flowFormUserValue;

import java.time.LocalDateTime;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import java.util.List;
import com.olivia.peanut.portal.api.entity.BaseEntityDto;
import com.alibaba.excel.annotation.ExcelProperty;
import java.time.LocalDate;
import java.time.LocalDateTime;
import com.olivia.sdk.ann.InsertCheck;
import com.olivia.sdk.ann.UpdateCheck;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

/**
 * 工作流表单用户数据表(FlowFormUserValue)查询对象返回
 *
 * @author peanut
 * @since 2024-08-03 18:10:54
 */
//@Accessors(chain=true)
@Getter
@Setter
@SuppressWarnings("serial")
public class FlowFormUserValueDto extends BaseEntityDto {

  /***
   *  流程实例ID
   */
  @NotBlank(message = "流程实例ID不能为空", groups = {InsertCheck.class, UpdateCheck.class})
  private String processInstanceId;
  /***
   *  业务主键
   */
  @NotBlank(message = "业务主键不能为空", groups = {InsertCheck.class, UpdateCheck.class})
  private String businessKey;
  /***
   *  表单ID
   */
  @NotNull(message = "表单ID不能为空", groups = {InsertCheck.class, UpdateCheck.class})
  private Long formId;
  /***
   *  表单默认值
   */
  @NotBlank(message = "表单默认值不能为空", groups = {InsertCheck.class, UpdateCheck.class})
  private String formItemDefaultValue;
  /***
   *  表单字段
   */
  @NotBlank(message = "表单字段不能为空", groups = {InsertCheck.class, UpdateCheck.class})
  private String formItemFiled;
  /***
   *  表单名称
   */
  @NotBlank(message = "表单名称不能为空", groups = {InsertCheck.class, UpdateCheck.class})
  private String formItemName;
  /***
   *  表单值
   */
  @NotBlank(message = "表单值不能为空", groups = {InsertCheck.class, UpdateCheck.class})
  private String formItemValue;
  /***
   *  表单值类型
   */
  @NotBlank(message = "表单值类型不能为空", groups = {InsertCheck.class, UpdateCheck.class})
  private String formItemValueType;
  /***
   *  是否添加流程表单值 0 否,1 是
   */
  @NotNull(message = "是否添加流程表单值 0 否,1 是不能为空", groups = {InsertCheck.class, UpdateCheck.class})
  private Boolean isAddFlowValue;
  /***
   *  是否必填 0 否,1 是
   */
  @NotNull(message = "是否必填 0 否,1 是不能为空", groups = {InsertCheck.class, UpdateCheck.class})
  private Boolean isRequired;
  /***
   *  失去焦点事件
   */
  @NotBlank(message = "失去焦点事件不能为空", groups = {InsertCheck.class, UpdateCheck.class})
  private String loseFocusEvent;
  /***
   *  排序
   */
  @NotNull(message = "排序不能为空", groups = {InsertCheck.class, UpdateCheck.class})
  private Integer sortIndex;
  /***
   *  用户值
   */
  @NotBlank(message = "用户值不能为空", groups = {InsertCheck.class, UpdateCheck.class})
  private String userValue;

}


