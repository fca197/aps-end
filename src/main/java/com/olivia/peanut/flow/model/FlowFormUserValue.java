package com.olivia.peanut.flow.model;


import com.baomidou.mybatisplus.annotation.TableName;
import com.olivia.sdk.utils.BaseEntity;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * 工作流表单用户数据表(FlowFormUserValue)表实体类
 *
 * @author peanut
 * @since 2024-08-03 18:10:54
 */
@Accessors(chain = true)
@Getter
@Setter
//@SuppressWarnings("serial")
@TableName("flow_form_user_value")
public class FlowFormUserValue extends BaseEntity {

  /***
   *  流程实例ID
   */
  private String processInstanceId;
  /***
   *  业务主键
   */
  private String businessKey;
  /***
   *  表单ID
   */
  private Long formId;
  /***
   *  表单默认值
   */
  private String formItemDefaultValue;
  /***
   *  表单字段
   */
  private String formItemFiled;
  /***
   *  表单名称
   */
  private String formItemName;
  /***
   *  表单值
   */
  private String formItemValue;
  /***
   *  表单值类型
   */
  private String formItemValueType;
  /***
   *  是否添加流程表单值
   */
  private Boolean isAddFlowValue;
  /***
   *  是否必填
   */
  private Boolean isRequired;
  /***
   *  失去焦点事件
   */
  private String loseFocusEvent;
  /***
   *  排序
   */
  private Integer sortIndex;
  /***
   *  用户值
   */
  private String userValue;

}

