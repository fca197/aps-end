package com.olivia.peanut.flow.api.entity.flowFormItem;

import java.time.LocalDateTime;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.io.Serializable;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
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
@SuppressWarnings("serial")
public class FlowFormItemUpdateByIdReq extends FlowFormItemDto {


  public void checkParam() {
  }

}

