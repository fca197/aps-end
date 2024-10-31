package com.olivia.peanut.flow.model;


import java.time.LocalDate;
import java.time.LocalDateTime;

import com.olivia.sdk.utils.BaseEntity;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import com.baomidou.mybatisplus.annotation.TableName;

/**
 * 工作流表单表(FlowForm)表实体类
 *
 * @author peanut
 * @since 2024-08-02 23:26:22
 */
@Accessors(chain = true)
@Getter
@Setter
//@SuppressWarnings("serial")
@TableName("flow_form")
public class FlowForm extends BaseEntity {

  /***
   *  表单名称
   */
  private String formName;
  /***
   *  表单编码
   */
  private String formCode;

}

