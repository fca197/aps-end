package com.olivia.peanut.base.api.entity.baseOplog;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * 操作日志(BaseOplog)查询对象入参
 *
 * @author makejava
 * @since 2024-11-30 16:01:01
 */
@Accessors(chain = true)
@Getter
@Setter
@SuppressWarnings("serial")
public class BaseOplogExportQueryPageListReq {
  private int pageNum;
  private int pageSize;
  private Boolean queryPage = true;
  private BaseOplogDto data;
}

