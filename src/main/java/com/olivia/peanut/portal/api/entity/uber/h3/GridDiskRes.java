package com.olivia.peanut.portal.api.entity.uber.h3;

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
public class GridDiskRes {

  private List<Long> regionList;
}
