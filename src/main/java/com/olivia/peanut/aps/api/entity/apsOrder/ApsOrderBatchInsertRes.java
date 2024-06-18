package com.olivia.peanut.aps.api.entity.apsOrder;

import java.util.List;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

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
