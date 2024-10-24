package com.olivia.sdk.utils;

import com.alibaba.fastjson2.JSON;
import com.olivia.peanut.aps.api.entity.apsGoodsBom.ApsGoodsBomDto;
import com.olivia.peanut.aps.utils.model.ApsGoodsBomVo;

import java.time.LocalDateTime;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

@Slf4j
class RandomUserUtilTest {

  @Test
  void getRandomUserName() {
    for (int i = 0; i < 100; i++) {
      String randomUserName = RandomUserUtil.getRandomUserName();
      log.info("randomUserName:{} {} {}", randomUserName, RandomUserUtil.getRandomPhone(), RandomUserUtil.getRandomSexInt());
    }
  }

  @Test
  public void testRandom() {
    ApsGoodsBomDto apsGoodsBomDto = new ApsGoodsBomDto();
    apsGoodsBomDto.setBomName("xxxx").setCreateTime(LocalDateTime.now());
    ApsGoodsBomVo copy = $.copy(apsGoodsBomDto, ApsGoodsBomVo.class);
    log.info("copy {}", JSON.toJSONString(copy));

  }

}
