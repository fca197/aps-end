package com.olivia.peanut.base.api.entity.baseAppResource;

import java.time.LocalDateTime;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import java.util.List;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * 资源(BaseAppResource)查询对象返回
 *
 * @author peanut
 * @since 2024-08-05 11:19:00
 */
@Accessors(chain = true)
@Getter
@Setter
@SuppressWarnings("serial")
public class BaseAppResourceQueryByIdListRes {

  /***
   * 返回对象列表
   */
  private List<BaseAppResourceDto> dataList;


}

