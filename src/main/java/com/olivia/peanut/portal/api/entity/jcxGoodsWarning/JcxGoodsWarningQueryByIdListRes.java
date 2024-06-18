package com.olivia.peanut.portal.api.entity.jcxGoodsWarning;

import java.util.List;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * (JcxGoodsWarning)查询对象返回
 *
 * @author peanut
 * @since 2024-03-24 14:10:55
 */
@Accessors(chain = true)
@Getter
@Setter
@SuppressWarnings("serial")
public class JcxGoodsWarningQueryByIdListRes {

  /***
   * 返回对象列表
   */
  private List<JcxGoodsWarningDto> dataList;


}

