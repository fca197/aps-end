package com.olivia.peanut.aps.api.entity.apsLogisticsPath;

import java.util.List;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * 物流路径表(ApsLogisticsPath)查询对象返回
 *
 * @author peanut
 * @since 2024-07-18 13:27:37
 */
@Accessors(chain = true)
@Getter
@Setter
@SuppressWarnings("serial")
public class ApsLogisticsPathQueryByIdListRes {

  /***
   * 返回对象列表
   */
  private List<ApsLogisticsPathDto> dataList;


}

