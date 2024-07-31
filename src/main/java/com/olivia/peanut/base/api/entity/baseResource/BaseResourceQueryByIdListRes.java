package com.olivia.peanut.base.api.entity.baseResource;

import java.time.LocalDateTime;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import java.util.List;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * 资源(BaseResource)查询对象返回
 *
 * @author peanut
 * @since 2024-07-31 14:33:33
 */
@Accessors(chain = true)
@Getter
@Setter
@SuppressWarnings("serial")
public class BaseResourceQueryByIdListRes {

  /***
   * 返回对象列表
   */
  private List<BaseResourceDto> dataList;


}

