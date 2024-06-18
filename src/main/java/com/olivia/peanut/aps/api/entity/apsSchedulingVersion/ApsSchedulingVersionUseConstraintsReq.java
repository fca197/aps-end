package com.olivia.peanut.aps.api.entity.apsSchedulingVersion;

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
public class ApsSchedulingVersionUseConstraintsReq implements BaseCheck {

  private Long id;

  @Override
  public void checkParam() {
    $.requireNonNullCanIgnoreException(this.id, "排产版本不能为空");
  }
}
