package com.olivia.peanut.config.entity;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
//@Accessors(chain = true)
public class DouBaoConfig {
  private String apiKey;
  private String arkBaseUrl;
  private Integer timeout;
  private Integer connectTimeout;
  private Integer retryTimes;
  private int maxIdleConnections;
  private long keepAliveDuration;
}

