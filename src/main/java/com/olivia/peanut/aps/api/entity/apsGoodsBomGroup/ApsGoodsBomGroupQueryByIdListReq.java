package com.olivia.peanut.aps.api.entity.apsGoodsBomGroup;

import java.time.LocalDateTime;
import java.util.List;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;


import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * 零件组配置(ApsGoodsBomGroup)查询对象入参
 *
 * @author peanut
 * @since 2024-06-19 16:49:04
 */
@Accessors(chain = true)
@Getter
@Setter
@SuppressWarnings("serial")
public class ApsGoodsBomGroupQueryByIdListReq {

  private List<Long> idList;


  public void checkParam() {
  }

}

