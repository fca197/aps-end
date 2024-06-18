package com.olivia.sdk.utils;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

@Slf4j
class IdUtilsTest {

  @Test
  void getUniqueId() {
    String uniqueId = IdUtils.getUniqueId();
    log.info("uniqueId:{}", uniqueId);
  }

  @Test
  void getSecondUniqueId() {
    String uniqueId = IdUtils.getSecondUniqueId();
    log.info("getSecondUniqueId:{}", uniqueId);
  }
}
