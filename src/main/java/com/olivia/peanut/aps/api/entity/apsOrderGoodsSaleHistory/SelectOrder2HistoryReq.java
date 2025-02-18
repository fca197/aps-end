package com.olivia.peanut.aps.api.entity.apsOrderGoodsSaleHistory;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Setter
@Getter
@Accessors(chain = true)
public class SelectOrder2HistoryReq {

  private Long tenantId;
  private SelectOrder2HistoryType selectType;
}
