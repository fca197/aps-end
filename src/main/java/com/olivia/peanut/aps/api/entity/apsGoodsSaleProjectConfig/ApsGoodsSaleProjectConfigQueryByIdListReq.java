package com.olivia.peanut.aps.api.entity.apsGoodsSaleProjectConfig;

import java.util.List;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * (ApsGoodsSaleProjectConfig)查询对象入参
 *
 * @author peanut
 * @since 2024-04-27 16:07:23
 */
@Accessors(chain = true)
@Getter
@Setter
@SuppressWarnings("serial")
public class ApsGoodsSaleProjectConfigQueryByIdListReq {

  private List<Long> idList;


  public void checkParam() {
  }

}

