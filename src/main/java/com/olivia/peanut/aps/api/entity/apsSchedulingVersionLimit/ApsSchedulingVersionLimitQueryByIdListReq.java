package com.olivia.peanut.aps.api.entity.apsSchedulingVersionLimit;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.util.List;

/**
 * (ApsSchedulingVersionLimit)查询对象入参
 *
 * @author peanut
 * @since 2024-04-19 14:57:00
 */
@Accessors(chain = true)
@Getter
@Setter
@SuppressWarnings("serial")
public class ApsSchedulingVersionLimitQueryByIdListReq {

  private List<Long> idList;


}

