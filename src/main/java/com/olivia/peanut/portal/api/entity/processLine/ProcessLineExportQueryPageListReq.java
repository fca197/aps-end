package com.olivia.peanut.portal.api.entity.processLine;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * 流水线信息(ProcessLine)查询对象入参
 *
 * @author makejava
 * @since 2024-03-11 10:55:20
 */
@Accessors(chain = true)
@Getter
@Setter

public class ProcessLineExportQueryPageListReq {

  private int pageNum;
  private int pageSize;
  private Boolean queryPage = true;
  private ProcessLineDto data;


}

