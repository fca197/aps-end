package com.olivia.peanut.portal.api.impl;

import com.uber.h3core.H3Core;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

class UberH3ApiImplTest {
  static H3Core h3Core;

  static {
    try {
      h3Core = H3Core.newInstance();
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

  @Test
  void test() {

    double lat = 31.920523;
    double lng = 119.268182;

    IntStream.range(0, 16).forEach(t -> {
      System.out.println(" value" + t + " bigint comment '第" + t + "级对应的h3值',");
      long code = h3Core.latLngToCell(lat, lng, t);
      String collect = h3Core.cellToBoundary(code).stream().map(tt -> tt.lat + "," + tt.lng).collect(Collectors.joining("/"));
//      System.out.println(t + "\t" + code + "\t" + collect);
    });

  }
}