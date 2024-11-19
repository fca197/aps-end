package com.olivia.peanut.base.api.entity.baseH3Code;

import java.time.LocalDateTime;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;


import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * H3对应的值(BaseH3Code)查询对象入参
 *
 * @author makejava
 * @since 2024-11-19 16:09:18
 */
@Accessors(chain = true)
@Getter
@Setter
@SuppressWarnings("serial")
public class BaseH3CodeQueryListReq {

  private BaseH3CodeDto data;
}

