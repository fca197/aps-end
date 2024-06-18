package com.olivia.peanut.aps.api.entity.apsProjectConfig;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * (ApsProjectConfig)查询对象入参
 *
 * @author peanut
 * @since 2024-03-30 16:21:20
 */
@Accessors(chain = true)
@Getter
@Setter
@SuppressWarnings("serial")
public class ApsProjectConfigQueryListReq {

  private ApsProjectConfigDto data;
}

