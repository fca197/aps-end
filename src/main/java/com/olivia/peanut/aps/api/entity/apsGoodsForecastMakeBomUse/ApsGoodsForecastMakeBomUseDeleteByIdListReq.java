package com.olivia.peanut.aps.api.entity.apsGoodsForecastMakeBomUse;

import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.util.List;

/**
 * (ApsGoodsForecastMakeBomUse)根据ID删除多个入参
 *
 * @author peanut
 * @since 2024-05-15 10:26:03
 */
@Accessors(chain = true)
@Getter
@Setter
@SuppressWarnings("serial")
public class ApsGoodsForecastMakeBomUseDeleteByIdListReq {

  /***
   * 要删除的ID
   */
  @NotEmpty(message = "请选择删除对象")
  private List<Long> idList;


}

