package com.olivia.peanut.flow.api.entity.flowFormUserValue;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.util.List;

import jakarta.validation.constraints.NotEmpty;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * 工作流表单用户数据表(FlowFormUserValue)根据ID删除多个入参
 *
 * @author peanut
 * @since 2024-08-03 18:10:54
 */
@Accessors(chain = true)
@Getter
@Setter
@SuppressWarnings("serial")
public class FlowFormUserValueDeleteByIdListReq {

  /***
   * 要删除的ID
   */
  @NotEmpty(message = "请选择删除对象")
  private List<Long> idList;


}

