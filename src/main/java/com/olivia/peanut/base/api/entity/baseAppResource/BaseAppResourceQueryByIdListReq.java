package com.olivia.peanut.base.api.entity.baseAppResource;

import java.time.LocalDateTime;
import java.util.List;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;


import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * 资源(BaseAppResource)查询对象入参
 *
 * @author peanut
 * @since 2024-08-06 17:30:28
 */
@Accessors(chain = true)
@Getter
@Setter
@SuppressWarnings("serial")
public class BaseAppResourceQueryByIdListReq {

  private List<Long> idList;


}

