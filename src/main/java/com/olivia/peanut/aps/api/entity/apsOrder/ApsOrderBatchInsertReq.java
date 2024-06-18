package com.olivia.peanut.aps.api.entity.apsOrder;

import com.olivia.peanut.portal.api.entity.BaseCheck;
import com.olivia.sdk.utils.$;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/***
 *
 */
@Setter
@Getter
@Accessors(chain = true)
public class ApsOrderBatchInsertReq implements BaseCheck {

  private int createCount;

  @Override
  public void checkParam() {
    $.assertTrueCanIgnoreException(createCount > 0, "createCount must > 0");
  }
}
