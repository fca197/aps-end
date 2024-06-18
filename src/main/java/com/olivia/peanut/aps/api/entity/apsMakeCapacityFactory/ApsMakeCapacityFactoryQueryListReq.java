package com.olivia.peanut.aps.api.entity.apsMakeCapacityFactory;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * (ApsMakeCapacityFactory)查询对象入参
 *
 * @author peanut
 * @since 2024-04-13 12:05:04
 */
@Accessors(chain = true)
@Getter
@Setter
@SuppressWarnings("serial")
public class ApsMakeCapacityFactoryQueryListReq {

  private ApsMakeCapacityFactoryDto data;
}

