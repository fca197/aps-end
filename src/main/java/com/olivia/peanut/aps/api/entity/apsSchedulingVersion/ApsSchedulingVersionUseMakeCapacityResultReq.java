package com.olivia.peanut.aps.api.entity.apsSchedulingVersion;

import jakarta.validation.constraints.NotNull;
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
public class ApsSchedulingVersionUseMakeCapacityResultReq {


  private Integer pageNum;
  private Integer pageSize;

  @NotNull(message = "id不能为空")
  private Long id;

  private List<String> currentDate;
}
