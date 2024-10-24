package com.olivia.peanut.aps.api.entity.apsProduceProcessItem;

import java.time.LocalDateTime;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;


import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * aps 生产机器(ApsProduceProcessItem)查询对象入参
 *
 * @author makejava
 * @since 2024-10-24 17:00:21
 */
@Accessors(chain = true)
@Getter
@Setter
@SuppressWarnings("serial")
public class ApsProduceProcessItemQueryListReq {

  private ApsProduceProcessItemDto data;
}

