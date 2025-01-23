package com.olivia.peanut.portal.api.entity.storey;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * 楼层信息(Storey)查询对象入参
 *
 * @author makejava
 * @since 2024-03-11 17:20:55
 */
@Accessors(chain = true)
@Getter
@Setter

public class StoreyExportQueryPageListReq {

  private int pageNum = 1;
  private int pageSize = 999;
  private Boolean queryPage = true;
  private StoreyDto data;


}

