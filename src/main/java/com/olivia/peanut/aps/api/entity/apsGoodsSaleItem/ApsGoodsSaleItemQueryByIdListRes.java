package com.olivia.peanut.aps.api.entity.apsGoodsSaleItem;

import java.util.List;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * (ApsGoodsSaleItem)查询对象返回
 *
 * @author peanut
 * @since 2024-03-30 10:38:36
 */
@Accessors(chain = true)
@Getter
@Setter
@SuppressWarnings("serial")
public class ApsGoodsSaleItemQueryByIdListRes {

  /***
   * 返回对象列表
   */
  private List<ApsGoodsSaleItemDto> dataList;


}

