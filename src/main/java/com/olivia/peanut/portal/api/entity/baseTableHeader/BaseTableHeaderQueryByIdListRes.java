package com.olivia.peanut.portal.api.entity.baseTableHeader;

import java.util.List;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * (BaseTableHeader)查询对象返回
 *
 * @author peanut
 * @since 2024-03-25 14:19:10
 */
@Accessors(chain = true)
@Getter
@Setter
@SuppressWarnings("serial")
public class BaseTableHeaderQueryByIdListRes {

  /***
   * 返回对象列表
   */
  private List<BaseTableHeaderDto> dataList;


}

