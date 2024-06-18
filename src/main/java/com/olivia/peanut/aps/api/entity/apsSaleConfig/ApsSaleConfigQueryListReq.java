package com.olivia.peanut.aps.api.entity.apsSaleConfig;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * (ApsSaleConfig)查询对象入参
 *
 * @author peanut
 * @since 2024-03-29 23:10:39
 */
@Accessors(chain = true)
@Getter
@Setter
@SuppressWarnings("serial")
public class ApsSaleConfigQueryListReq {

  private ApsSaleConfigDto data;
}

