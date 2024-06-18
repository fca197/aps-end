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
