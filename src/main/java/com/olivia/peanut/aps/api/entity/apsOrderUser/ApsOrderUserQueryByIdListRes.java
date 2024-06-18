package com.olivia.peanut.aps.api.entity.apsOrderUser;

import java.util.List;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * (ApsOrderUser)查询对象返回
 *
 * @author peanut
 * @since 2024-04-09 13:19:39
 */
@Accessors(chain = true)
@Getter
@Setter
@SuppressWarnings("serial")
public class ApsOrderUserQueryByIdListRes {

  /***
   * 返回对象列表
   */
  private List<ApsOrderUserDto> dataList;


}

