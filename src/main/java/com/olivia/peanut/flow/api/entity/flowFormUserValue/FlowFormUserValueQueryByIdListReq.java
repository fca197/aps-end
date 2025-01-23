package com.olivia.peanut.flow.api.entity.flowFormUserValue;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.util.List;

/**
 * 工作流表单用户数据表(FlowFormUserValue)查询对象入参
 *
 * @author peanut
 * @since 2024-08-03 18:10:54
 */
@Accessors(chain = true)
@Getter
@Setter
@SuppressWarnings("serial")
public class FlowFormUserValueQueryByIdListReq {

  private List<Long> idList;


}

