package com.olivia.peanut.portal.api.entity.shift;

import java.util.List;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * (Shift)查询对象入参
 *
 * @author peanut
 * @since 2024-04-04 12:10:15
 */
@Accessors(chain = true)
@Getter
@Setter
@SuppressWarnings("serial")
public class ShiftQueryByIdListReq {

  private List<Long> idList;


  public void checkParam() {
  }

}

