package com.olivia.peanut.base.api.entity.baseH3Code;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * H3对应的值(BaseH3Code)查询对象入参
 *
 * @author makejava
 * @since 2024-11-19 16:09:18
 */
@Accessors(chain = true)
@Getter
@Setter
@SuppressWarnings("serial")
public class BaseH3CodeExportQueryPageListReq {
  private int pageNum;
  private int pageSize;
  private Boolean queryPage = true;
  private BaseH3CodeDto data;


}

