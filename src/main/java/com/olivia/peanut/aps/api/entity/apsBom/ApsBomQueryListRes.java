package com.olivia.peanut.aps.api.entity.apsBom;

import java.util.List;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * BOM 清单(ApsBom)查询对象返回
 *
 * @author peanut
 * @since 2024-06-06 11:27:34
 */
@Accessors(chain = true)
@Getter
@Setter
@SuppressWarnings("serial")
public class ApsBomQueryListRes {

  /***
   * 返回对象列表
   */
  private List<ApsBomDto> dataList;


}

