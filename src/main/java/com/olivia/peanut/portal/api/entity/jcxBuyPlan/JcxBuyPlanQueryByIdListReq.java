package com.olivia.peanut.portal.api.entity.jcxBuyPlan;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.util.List;

/**
 * (JcxBuyPlan)查询对象入参
 *
 * @author peanut
 * @since 2024-03-24 20:27:11
 */
@Accessors(chain = true)
@Getter
@Setter
@SuppressWarnings("serial")
public class JcxBuyPlanQueryByIdListReq {

  private List<Long> idList;


}

