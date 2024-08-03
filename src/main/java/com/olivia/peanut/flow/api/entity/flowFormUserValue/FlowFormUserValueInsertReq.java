package com.olivia.peanut.flow.api.entity.flowFormUserValue;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * 工作流表单用户数据表(FlowFormUserValue)保存入参
 *
 * @author peanut
 * @since 2024-08-03 18:10:53
 */
@Accessors(chain = true)
@Getter
@Setter
@SuppressWarnings("serial")
public class FlowFormUserValueInsertReq extends FlowFormUserValueDto {

  public void checkParam() {
  }
}

