package com.olivia.peanut.flow.api.entity.flowFormItem;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * 工作流表单项表(FlowFormItem)保存入参
 *
 * @author peanut
 * @since 2024-08-02 23:26:25
 */
@Accessors(chain = true)
@Getter
@Setter
@SuppressWarnings("serial")
public class FlowFormItemInsertReq extends FlowFormItemDto {

  public void checkParam() {
  }
}

