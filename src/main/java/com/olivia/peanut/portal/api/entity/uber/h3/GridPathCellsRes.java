package com.olivia.peanut.portal.api.entity.uber.h3;

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
public class GridPathCellsRes {

  private List<Long> dataList;
}
