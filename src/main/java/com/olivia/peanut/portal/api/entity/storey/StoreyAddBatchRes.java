package com.olivia.peanut.portal.api.entity.storey;

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
public class StoreyAddBatchRes {

  private List<Long> idList;
}
