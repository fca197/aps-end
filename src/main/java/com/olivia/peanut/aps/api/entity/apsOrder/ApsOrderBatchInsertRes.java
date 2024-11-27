package com.olivia.peanut.aps.api.entity.apsOrder;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.util.List;

/***
 *
 */
@Setter
@Getter
@Accessors(chain = true)
public class ApsOrderBatchInsertRes {

  private List<Long> idList;
  private List<String> orderNoList;
}
