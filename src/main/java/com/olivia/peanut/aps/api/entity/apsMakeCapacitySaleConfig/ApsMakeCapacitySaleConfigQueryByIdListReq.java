package com.olivia.peanut.aps.api.entity.apsMakeCapacitySaleConfig;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.util.List;

/**
 * (ApsMakeCapacitySaleConfig)查询对象入参
 *
 * @author peanut
 * @since 2024-04-13 12:05:07
 */
@Accessors(chain = true)
@Getter
@Setter
@SuppressWarnings("serial")
public class ApsMakeCapacitySaleConfigQueryByIdListReq {

  private List<Long> idList;


}

