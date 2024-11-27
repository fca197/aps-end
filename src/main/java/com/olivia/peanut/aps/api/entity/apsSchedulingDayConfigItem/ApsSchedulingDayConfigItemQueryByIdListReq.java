package com.olivia.peanut.aps.api.entity.apsSchedulingDayConfigItem;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.util.List;

/**
 * 排程版本配置表(ApsSchedulingDayConfigItem)查询对象入参
 *
 * @author peanut
 * @since 2024-07-19 19:19:53
 */
@Accessors(chain = true)
@Getter
@Setter
@SuppressWarnings("serial")
public class ApsSchedulingDayConfigItemQueryByIdListReq {

  private List<Long> idList;


}

