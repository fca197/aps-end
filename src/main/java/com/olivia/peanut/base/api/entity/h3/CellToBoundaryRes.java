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
public class CellToBoundaryRes {

  List<Info> dataList;

  @Getter
  @Setter
  @Accessors(chain = true)
  public static class Info {

    private double lat;
    private double lng;
  }
}
