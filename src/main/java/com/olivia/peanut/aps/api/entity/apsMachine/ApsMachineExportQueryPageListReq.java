package com.olivia.peanut.aps.api.entity.apsMachine;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * aps 生产机器(ApsMachine)查询对象入参
 *
 * @author makejava
 * @since 2024-10-24 16:31:15
 */
@Accessors(chain = true)
@Getter
@Setter
@SuppressWarnings("serial")
public class ApsMachineExportQueryPageListReq {
  private int pageNum;
  private int pageSize;
  private Boolean queryPage = true;
  private ApsMachineDto data;


}

