package com.olivia.peanut.base.api.entity.baseH3Code;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.util.List;

/**
 * H3对应的值(BaseH3Code)查询对象返回
 *
 * @author makejava
 * @since 2024-11-19 16:09:18
 */
@Accessors(chain = true)
@Getter
@Setter
@SuppressWarnings("serial")
public class BaseH3CodeQueryListRes {
  /***
   * 返回对象列表
   */
  private List<BaseH3CodeDto> dataList;


}

