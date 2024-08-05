package com.olivia.peanut.base.api.entity.baseApp;

import java.time.LocalDateTime;
import java.util.List;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;


import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * 应用表(BaseApp)查询对象入参
 *
 * @author peanut
 * @since 2024-08-05 11:18:58
 */
@Accessors(chain = true)
@Getter
@Setter
@SuppressWarnings("serial")
public class BaseAppQueryByIdListReq {

  private List<Long> idList;


  public void checkParam() {
  }

}

