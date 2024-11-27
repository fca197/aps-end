package com.olivia.peanut.portal.api.entity.jcxOrder;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.util.Map;

/***
 *
 */
@Setter
@Getter
@Accessors(chain = true)
public class GetOrderStatusRes {

  Map<String, String> orderStatusName;
}
