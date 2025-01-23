package com.olivia.peanut.aps.api.entity.apsSchedulingDayConfigVersion;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.util.List;

/**
 * 排程版本(ApsSchedulingDayConfigVersion)查询对象入参
 *
 * @author peanut
 * @since 2024-07-19 19:19:56
 */
@Accessors(chain = true)
@Getter
@Setter
@SuppressWarnings("serial")
public class ApsSchedulingDayConfigVersionQueryByIdListReq {

  private List<Long> idList;


}

