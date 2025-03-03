package com.olivia.peanut.flow.api.entity.flowDefinition;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.util.List;

/**
 * 工作定义表(FlowDefinition)查询对象入参
 *
 * @author peanut
 * @since 2024-08-01 10:43:49
 */
@Accessors(chain = true)
@Getter
@Setter
@SuppressWarnings("serial")
public class FlowDefinitionQueryByIdListReq {

  private List<Long> idList;


}

