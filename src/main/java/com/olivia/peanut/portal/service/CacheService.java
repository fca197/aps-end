package com.olivia.peanut.portal.service;


public interface CacheService {

  void setKv(String key, Object val, Long timeOutInSecond);

  <T> T getKv(String key);

  void deleteKey(String key);

  boolean hasKey(String key);
}
