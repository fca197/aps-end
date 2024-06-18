package com.olivia.peanut.aps.api.entity.apsSchedulingGoodsBomTotal;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/***
 *
 */
@Setter
@Getter
@Accessors(chain = true)
public class ApsSchedulingGoodsBomTotalQueryBomTotalReq {

  //  private int pageNum = 1;
//  private int pageSize = 10;
  @NotNull
  private Long schedulingVersionId;
  private Boolean retBom = Boolean.FALSE;
}
