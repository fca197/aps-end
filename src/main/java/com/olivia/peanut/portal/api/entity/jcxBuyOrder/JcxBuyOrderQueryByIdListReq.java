package com.olivia.peanut.portal.api.entity.jcxBuyOrder;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.util.List;

/**
 * (JcxBuyOrder)查询对象入参
 *
 * @author peanut
 * @since 2024-03-27 13:51:37
 */
@Accessors(chain = true)
@Getter
@Setter
@SuppressWarnings("serial")
public class JcxBuyOrderQueryByIdListReq {

  private List<Long> idList;


}

