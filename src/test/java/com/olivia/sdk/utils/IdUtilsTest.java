package com.olivia.sdk.utils;

import cn.hutool.core.util.RadixUtil;
import com.baomidou.mybatisplus.core.toolkit.IdWorker;
import com.google.common.collect.HashMultiset;
import com.google.common.collect.Multiset;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.util.Comparator;

@Slf4j
class IdUtilsTest {

  @Test
  void getUniqueId() {
    HashMultiset<Long> multiset = HashMultiset.create();
    for (int i = 0; i < 1100; i++) {
      Long uniqueId = Math.abs(RadixUtil.decode(RadixUtil.RADIXS_59, IdWorker.get32UUID()) % 256);
      //log.info("uniqueId:{}", uniqueId + " ");
      multiset.add(uniqueId);
    }
    log.info("multiset:{}", multiset.toString());

    multiset.entrySet().stream().sorted(Comparator.comparing(Multiset.Entry::getCount)).forEach(t -> {
      log.info("multiset:{}x{}", t.getElement(), t.getCount());

    });
//    Long uniqueId = Math.abs(RadixUtil.decode(RadixUtil.RADIXS_59, IdWorker.get32UUID()) % 128);
//    log.info("uniqueId:{}", uniqueId);
  }

  @Test
  void getSecondUniqueId() {
    String uniqueId = IdUtils.getSecondUniqueId();
    String uniqueId2 = IdUtils.getUniqueId();
    log.info("getSecondUniqueId:{} {}", uniqueId, uniqueId2);
  }
}
