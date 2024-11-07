package com.olivia.peanut.aps.api.entity.apsOrder;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.util.List;

@Setter
@Getter
@Accessors(chain = true)
public class OrderCreateDayCountRes {
  private List<Info> dataList;

  @Setter
  @Getter
  @Accessors(chain = true)
  public static class Info {
    private Object date;
    private Object count;
  }
}
