package com.olivia.peanut.aps.api.entity.apsLogisticsPath;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * 物流路径表(ApsLogisticsPath)查询对象入参
 *
 * @author peanut
 * @since 2024-07-18 13:27:36
 */
@Accessors(chain = true)
@Getter
@Setter
@SuppressWarnings("serial")
public class ApsLogisticsPathQueryListReq {

  private ApsLogisticsPathDto data;
}

