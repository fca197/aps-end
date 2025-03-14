package com.olivia.peanut.base.api.entity.baseTableHeader;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.util.List;

/**
 * (BaseTableHeader)查询对象返回
 *
 * @author peanut
 * @since 2024-03-25 14:19:09
 */
@Accessors(chain = true)
@Getter
@Setter
@SuppressWarnings("serial")
public class BaseTableHeaderQueryListRes {

  /***
   * 返回对象列表
   */
  private List<BaseTableHeaderDto> dataList;


}

