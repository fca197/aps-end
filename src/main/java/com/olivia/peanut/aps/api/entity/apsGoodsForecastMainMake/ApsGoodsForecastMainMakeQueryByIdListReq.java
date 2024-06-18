package com.olivia.peanut.aps.api.entity.apsGoodsForecastMainMake;

import java.util.List;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * (ApsGoodsForecastMainMake)查询对象入参
 *
 * @author peanut
 * @since 2024-04-08 09:52:50
 */
@Accessors(chain = true)
@Getter
@Setter
@SuppressWarnings("serial")
public class ApsGoodsForecastMainMakeQueryByIdListReq {

  private List<Long> idList;


  public void checkParam() {
  }

}

