package com.olivia.peanut.portal.api.entity.jcxOrderItem;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.util.List;

/**
 * (JcxOrderItem)查询对象入参
 *
 * @author peanut
 * @since 2024-03-22 10:38:08
 */
@Accessors(chain = true)
@Getter
@Setter
@SuppressWarnings("serial")
public class JcxOrderItemQueryByIdListReq {

  private List<Long> idList;


}

