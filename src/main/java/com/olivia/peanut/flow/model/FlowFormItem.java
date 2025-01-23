package com.olivia.peanut.flow.model;


import com.baomidou.mybatisplus.annotation.TableName;
import com.olivia.sdk.utils.BaseEntity;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * 工作流表单项表(FlowFormItem)表实体类
 *
 * @author peanut
 * @since 2024-08-02 23:26:26
 */
@Accessors(chain = true)
@Getter
@Setter
//@SuppressWarnings("serial")
@TableName("flow_form_item")
public class FlowFormItem extends BaseEntity {

  /***
   *  表单ID
   */
  private Long formId;
  /***
   *  表单项名称
   */
  private String formItemName;
  /***
   *  表单项字段
   */
  private String formItemFiled;
  /***
   *  表单项类型
   */
  private String formItemValue;
  /***
   *  表单项默认值
   */
  private String formItemDefaultValue;
  /***
   *  表单值类型  text , date , dateTime ,array
   */
  private String formItemValueType;
  /***
   *  是否添加流程变量
   */
  private Boolean isAddFlowValue;
  /***
   *  是否必填
   */
  private Boolean isRequired;
  /***
   *  排序
   */
  private Integer sortIndex;
  /***
   *  失去焦点事件
   */
  private String loseFocusEvent;

}

