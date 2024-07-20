package com.olivia.peanut.aps.api.entity.apsSchedulingIssueItem;

import java.time.LocalDateTime;
import java.util.List;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;


import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * 排产下发详情(ApsSchedulingIssueItem)查询对象入参
 *
 * @author peanut
 * @since 2024-07-20 13:55:55
 */
@Accessors(chain = true)
@Getter
@Setter
@SuppressWarnings("serial")
public class ApsSchedulingIssueItemQueryByIdListReq {

  private List<Long> idList;


  public void checkParam() {
  }

}

