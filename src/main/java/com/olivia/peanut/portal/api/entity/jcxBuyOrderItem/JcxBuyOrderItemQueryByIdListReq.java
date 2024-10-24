package com.olivia.peanut.portal.api.entity.jcxBuyOrderItem;

import java.util.List;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * (JcxBuyOrderItem)查询对象入参
 *
 * @author peanut
 * @since 2024-03-27 13:51:37
 */
@Accessors(chain = true)
@Getter
@Setter
@SuppressWarnings("serial")
public class JcxBuyOrderItemQueryByIdListReq {

  private List<Long> idList;


  public void checkParam() {
  }

}

