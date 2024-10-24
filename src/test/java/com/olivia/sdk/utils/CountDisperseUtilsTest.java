package com.olivia.sdk.utils;

import java.util.ArrayList;
import java.util.List;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

@Slf4j
class CountDisperseUtilsTest {

  @Test
  void unidimensional() {
    List<Long> longList = CountDisperseUtils.unidimensional(1005L, new ArrayList<>(List.of(1d, 2d, 3d)));
    log.info("longList:{} {}", longList, longList.stream().mapToDouble(o -> o).sum());

  }

  @Test
  void unidimensional2() {
    for (int i = 0; i < 1000; i++) {
      List<Long> longList = CountDisperseUtils.unidimensional((long) i, 18);
      log.info("longList:{} {}", longList, longList.stream().mapToDouble(o -> o).sum());
    }
  }
}
