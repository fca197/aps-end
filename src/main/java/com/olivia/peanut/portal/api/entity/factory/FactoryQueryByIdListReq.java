package com.olivia.peanut.portal.api.entity.factory;

import java.util.List;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * 工段信息(Factory)查询对象入参
 *
 * @author makejava
 * @since 2024-03-11 10:44:05
 */
@Accessors(chain = true)
@Getter
@Setter

public class FactoryQueryByIdListReq {

  private List<Long> idList;


  public void checkParam() {
  }

}

