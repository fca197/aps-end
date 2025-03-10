package com.olivia.peanut.flow.api.entity.flowGroup;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * 工作流组表(FlowGroup)查询对象入参
 *
 * @author peanut
 * @since 2024-08-01 10:43:53
 */
@Accessors(chain = true)
@Getter
@Setter
@SuppressWarnings("serial")
public class FlowGroupExportQueryPageListReq {

  private int pageNum;
  private int pageSize;
  private Boolean queryPage = true;
  private FlowGroupDto data;


}

