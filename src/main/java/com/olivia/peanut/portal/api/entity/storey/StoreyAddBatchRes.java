package com.olivia.peanut.portal.api.entity.storey;

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
public class StoreyAddBatchRes {

  private List<Long> idList;
}
