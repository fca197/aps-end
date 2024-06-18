package com.olivia.peanut.portal.timer;

import jakarta.annotation.PostConstruct;
import java.util.Timer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

/***
 *
 */

@Slf4j
@Component
@ConditionalOnProperty(name = "peanut.match.driver.enable", havingValue = "true")
public class DriverMatchTimer {

  static final Timer timer = new Timer();

  @Autowired
  StringRedisTemplate redisTemplate;

  @PostConstruct
  public void init() {
    log.info("正在启动司机撮合定时器");
//        timer.schedule(new TimerTask() {
//            @Override
//            public void run() {
//                String geo = redisTemplate.opsForList().leftPop(REDIS_KEY_DRIVER_GEO, 5, TimeUnit.SECONDS);
//                if (geo == null) {
//                    return;
//                }
//                redisTemplate.opsForList().rightPush(REDIS_KEY_DRIVER_GEO, geo);
//                log.info("正在撮合");
//            }
//        }, 1000,3000);
  }
}
