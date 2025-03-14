package com.olivia.peanut.base.api.entity.h3;

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
public class CellToChildrenRes {

  private List<Long> dataList;
}
