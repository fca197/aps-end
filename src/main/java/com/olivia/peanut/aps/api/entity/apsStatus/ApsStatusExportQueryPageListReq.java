package com.olivia.peanut.aps.api.entity.apsStatus;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * (ApsStatus)查询对象入参
 *
 * @author peanut
 * @since 2024-04-01 10:50:12
 */
@Accessors(chain = true)
@Getter
@Setter
@SuppressWarnings("serial")
public class ApsStatusExportQueryPageListReq {

  private int pageNum;
  private int pageSize;
  private Boolean queryPage = true;
  private ApsStatusDto data;


}

