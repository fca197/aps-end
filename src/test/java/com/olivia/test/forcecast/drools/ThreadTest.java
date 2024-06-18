package com.olivia.test.forcecast.drools;

import cn.hutool.core.thread.ThreadUtil;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

/***
 *
 */
@Slf4j
public class ThreadTest {

  @Test
  public void test() {
    ThreadPoolExecutor executor = new ThreadPoolExecutor(
        3, 3,
        1, TimeUnit.SECONDS,
        new ArrayBlockingQueue<>(18),
        Thread::new,
        new ThreadPoolExecutor.AbortPolicy());

    IntStream.rangeClosed(0, 12).forEach(t -> executor.execute(() -> {
//            ThreadUtil.safeSleep(MathUtil.randomInt(10, 3000));
      log.info("threadPoolExecutor: " + t);
    }));

    ThreadUtil.safeSleep(10000);
  }
}
