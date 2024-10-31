package com.olivia.peanut.aps.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@Slf4j
class ApsSchedulingDayConfigVersionServiceImplTest {

  @Test
  public void test() {
    List<Integer> objects = List.of(1, 2, 4);
    List<Integer> l2 = new ArrayList<>();
    l2.add(6);
    l2.add(7);
    l2.add(8);
    l2.addAll(0, objects);
    log.info("l2: {}", l2);
  }
}