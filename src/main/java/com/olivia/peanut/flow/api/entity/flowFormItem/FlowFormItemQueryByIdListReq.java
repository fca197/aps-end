package com.olivia.peanut.flow.api.entity.flowFormItem;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.util.List;

/**
 * 工作流表单项表(FlowFormItem)查询对象入参
 *
 * @author peanut
 * @since 2024-08-02 23:26:26
 */
@Accessors(chain = true)
@Getter
@Setter
@SuppressWarnings("serial")
public class FlowFormItemQueryByIdListReq {

  private List<Long> idList;


}

