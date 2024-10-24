package com.olivia.peanut.base.api.entity.baseUserResource;

import java.time.LocalDateTime;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.util.List;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * 用户角色资源表(BaseUserResource)查询对象返回
 *
 * @author peanut
 * @since 2024-08-09 16:26:39
 */
@Accessors(chain = true)
@Getter
@Setter
@SuppressWarnings("serial")
public class BaseUserResourceQueryListRes {

  /***
   * 返回对象列表
   */
  private List<BaseUserResourceDto> dataList;


}

