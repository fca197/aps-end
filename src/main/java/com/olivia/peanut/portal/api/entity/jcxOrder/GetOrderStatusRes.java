package com.olivia.peanut.portal.api.entity.jcxOrder;

import java.util.Map;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/***
 *
 */
@Setter
@Getter
@Accessors(chain = true)
public class GetOrderStatusRes {

  Map<String, String> orderStatusName;
}
