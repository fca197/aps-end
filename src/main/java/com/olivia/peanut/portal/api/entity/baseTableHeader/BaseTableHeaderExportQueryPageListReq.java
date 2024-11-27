package com.olivia.peanut.portal.api.entity.baseTableHeader;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * (BaseTableHeader)查询对象入参
 *
 * @author peanut
 * @since 2024-03-25 14:19:10
 */
@Accessors(chain = true)
@Getter
@Setter
@SuppressWarnings("serial")
public class BaseTableHeaderExportQueryPageListReq {

  private int pageNum;
  private int pageSize;
  private Boolean queryPage = true;
  private BaseTableHeaderDto data;


}

