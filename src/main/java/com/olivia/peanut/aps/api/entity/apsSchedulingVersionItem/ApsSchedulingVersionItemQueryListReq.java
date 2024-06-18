package com.olivia.peanut.aps.api.entity.apsSchedulingVersionItem;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * (ApsSchedulingVersionItem)查询对象入参
 *
 * @author peanut
 * @since 2024-04-16 09:24:05
 */
@Accessors(chain = true)
@Getter
@Setter
@SuppressWarnings("serial")
public class ApsSchedulingVersionItemQueryListReq {

  private ApsSchedulingVersionItemDto data;
}

