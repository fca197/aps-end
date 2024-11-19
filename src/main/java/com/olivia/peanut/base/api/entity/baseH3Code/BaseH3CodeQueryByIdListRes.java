package com.olivia.peanut.base.api.entity.baseH3Code;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.util.List;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * H3对应的值(BaseH3Code)查询对象返回
 *
 * @author makejava
 * @since 2024-11-19 16:09:19
 */
@Accessors(chain = true)
@Getter
@Setter
@SuppressWarnings("serial")
public class BaseH3CodeQueryByIdListRes {
  /***
   * 返回对象列表
   */
  private List<BaseH3CodeDto> dataList;


}

