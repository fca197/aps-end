package com.olivia.peanut.portal.api.entity.brand;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.util.List;

/**
 * 品牌信息(Brand)查询对象返回
 *
 * @author makejava
 * @since 2024-03-11 10:44:02
 */
@Accessors(chain = true)
@Getter
@Setter

public class BrandQueryByIdListRes {

  /***
   * 返回对象列表
   */
  private List<BrandDto> dataList;

}

