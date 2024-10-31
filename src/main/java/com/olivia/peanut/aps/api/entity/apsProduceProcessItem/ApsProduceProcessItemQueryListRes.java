package com.olivia.peanut.aps.api.entity.apsProduceProcessItem;

import java.time.LocalDateTime;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.util.List;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * aps 生产机器(ApsProduceProcessItem)查询对象返回
 *
 * @author makejava
 * @since 2024-10-24 17:00:21
 */
@Accessors(chain = true)
@Getter
@Setter
@SuppressWarnings("serial")
public class ApsProduceProcessItemQueryListRes {
  /***
   * 返回对象列表
   */
  private List<ApsProduceProcessItemDto> dataList;


}

