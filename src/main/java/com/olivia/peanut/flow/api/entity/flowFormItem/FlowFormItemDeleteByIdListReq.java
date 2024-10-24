package com.olivia.peanut.flow.api.entity.flowFormItem;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.util.List;

import jakarta.validation.constraints.NotEmpty;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * 工作流表单项表(FlowFormItem)根据ID删除多个入参
 *
 * @author peanut
 * @since 2024-08-02 23:26:25
 */
@Accessors(chain = true)
@Getter
@Setter
@SuppressWarnings("serial")
public class FlowFormItemDeleteByIdListReq {

  /***
   * 要删除的ID
   */
  @NotEmpty(message = "请选择删除对象")
  private List<Long> idList;


  public void checkParam() {
  }

}

