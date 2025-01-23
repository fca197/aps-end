package com.olivia.peanut.portal.api.entity.storey;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.util.List;

/**
 * 楼层信息(Storey)查询对象返回
 *
 * @author makejava
 * @since 2024-03-11 17:20:55
 */
@Accessors(chain = true)
@Getter
@Setter

public class StoreyQueryListRes {

  /***
   * 返回对象列表
   */
  private List<StoreyDto> dataList;


}

