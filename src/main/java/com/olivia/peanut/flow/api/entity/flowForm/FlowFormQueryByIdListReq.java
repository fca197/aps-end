package com.olivia.peanut.flow.api.entity.flowForm;

import java.time.LocalDateTime;
import java.util.List;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;


import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * 工作流表单表(FlowForm)查询对象入参
 *
 * @author peanut
 * @since 2024-08-02 23:26:22
 */
@Accessors(chain = true)
@Getter
@Setter
@SuppressWarnings("serial")
public class FlowFormQueryByIdListReq {

  private List<Long> idList;


  public void checkParam() {
  }

}

