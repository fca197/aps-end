package com.olivia.peanut.aps.api.entity.apsBom;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.util.List;

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
public class ApsBomQueryByIdListRes {

  /***
   * 返回对象列表
   */
  private List<ApsBomDto> dataList;


}

