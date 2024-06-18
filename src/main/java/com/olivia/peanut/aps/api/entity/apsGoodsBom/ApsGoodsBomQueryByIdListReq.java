package com.olivia.peanut.aps.api.entity.apsGoodsBom;

import java.util.List;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * (ApsGoodsBom)查询对象入参
 *
 * @author peanut
 * @since 2024-04-03 22:28:56
 */
@Accessors(chain = true)
@Getter
@Setter
@SuppressWarnings("serial")
public class ApsGoodsBomQueryByIdListReq {

  private List<Long> idList;


  public void checkParam() {
  }

}

