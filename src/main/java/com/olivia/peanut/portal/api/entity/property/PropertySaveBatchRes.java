package com.olivia.peanut.portal.api.entity.property;

import java.util.List;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/***
 *
 */
@Setter
@Getter
@Accessors(chain = true)
public class PropertySaveBatchRes {

  private List<Long> idList;
}
