package com.olivia.peanut.task.engine;

import jakarta.annotation.PostConstruct;
import lombok.SneakyThrows;
import org.springframework.stereotype.Component;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

@Component
public class BaseTaskEngine {

//  BlockingQueue<Long> queue = new LinkedBlockingQueue<>(3000);

//  @SneakyThrows
//  @PostConstruct
//  public void init() {
//    while (true) {
//      Long taskId = queue.take();
//      System.out.println("Consuming: " + taskId);
//    }
//  }


}
