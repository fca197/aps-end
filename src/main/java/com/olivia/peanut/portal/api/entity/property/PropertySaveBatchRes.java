package com.olivia.peanut.portal.api.entity.property;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.util.List;

/***
 *
 */
@Setter
@Getter
@Accessors(chain = true)
public class PropertySaveBatchRes {

  private List<Long> idList;
}
