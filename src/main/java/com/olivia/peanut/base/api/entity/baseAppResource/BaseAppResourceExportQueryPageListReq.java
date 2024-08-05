package com.olivia.peanut.base.api.entity.baseAppResource;

import java.time.LocalDateTime;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;


import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * 资源(BaseAppResource)查询对象入参
 *
 * @author peanut
 * @since 2024-08-05 11:19:00
 */
@Accessors(chain = true)
@Getter
@Setter
@SuppressWarnings("serial")
public class BaseAppResourceExportQueryPageListReq {

  private int pageNum;
  private int pageSize;
  private Boolean queryPage = true;
  private BaseAppResourceDto data;


  public void checkParam() {
  }

}

