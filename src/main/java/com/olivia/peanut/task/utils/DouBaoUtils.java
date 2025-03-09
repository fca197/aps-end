package com.olivia.peanut.task.utils;

import com.olivia.peanut.config.AiProperties;
import com.olivia.peanut.config.entity.DouBaoConfig;
import com.volcengine.ark.runtime.service.ArkService;
import jakarta.annotation.PostConstruct;
import jakarta.annotation.Resource;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import okhttp3.ConnectionPool;
import okhttp3.Dispatcher;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

@Slf4j
@Component
public class DouBaoUtils {
  private static DouBaoUtils instance;


  @Resource
  AiProperties aiProperties;
  @Getter
  private static ArkService arkService;

  @PostConstruct
  public void init() {

    try {
      DouBaoConfig douBao = aiProperties.getDouBao();
      if (Objects.nonNull(douBao)) {
        ConnectionPool connectionPool = new ConnectionPool(douBao.getMaxIdleConnections(), douBao.getKeepAliveDuration(), TimeUnit.SECONDS);
        Dispatcher dispatcher = new Dispatcher();
        arkService = ArkService.builder().dispatcher(dispatcher).connectionPool(connectionPool).apiKey(douBao.getApiKey()).timeout(Duration.ofMillis(douBao.getTimeout())).connectTimeout(Duration.ofMillis(douBao.getConnectTimeout())).build();
        log.info("DouBaoUtils init success");
      }
    } catch (Exception e) {
      log.error("豆包初始化失败， {}", e.getMessage(), e);
    }

    DouBaoUtils.instance = this;
  }
}
