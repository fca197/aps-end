package com.olivia.peanut.portal.service.impl;

import cn.hutool.core.collection.CollUtil;
import com.olivia.peanut.portal.service.CacheService;

import java.util.*;

import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import lombok.SneakyThrows;
import lombok.experimental.Accessors;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/***
 *
 */
@Slf4j
@Service
public class CacheServiceImpl implements CacheService {

  private final Map<String, CacheItem> cacheItemMap = Collections.synchronizedMap(new HashMap<>());
  Timer timer = new Timer();

  //  @PostConstruct
  public void init() {
    timer.schedule(new TimerTask() {
      @Override
      public void run() {
        Set<String> keySet = cacheItemMap.keySet();
        if (CollUtil.isEmpty(keySet)) {
          return;
        }
        List<String> keyList = new ArrayList<>(keySet);
        log.info("clear cache item key {}", keyList);
        keyList.forEach(k -> {
          if (cacheItemMap.containsKey(k)) {
            CacheItem cacheItem = cacheItemMap.get(k);
            if (cacheItem.getTimeOutInSecond() < new Date().getTime()) {
              cacheItemMap.remove(k);
            }
          }
        });
      }
    }, 0, 500);

  }

  public void setKv(@NonNull String key, @NonNull Object val, @NonNull Long timeOutInSecond) {
    cacheItemMap.put(key, new CacheItem().setObj(val).setTimeOutInSecond(new Date().getTime() + timeOutInSecond * 1000));
  }

  @Override
  @SneakyThrows
  @SuppressWarnings("unchecked")
  public <T> T getKv(@NonNull String key) {
    CacheItem cacheItem = cacheItemMap.get(key);
    if (Objects.nonNull(cacheItem)) {
      if (cacheItem.getTimeOutInSecond() > new Date().getTime()) {
        return (T) cacheItem.getObj();
      } else {
        cacheItemMap.remove(key);
      }
    }
    return null;
  }

  @Override
  public boolean hasKey(String key) {
    return Objects.nonNull(cacheItemMap.get(key));
  }

  @Override
  public void deleteKey(String key) {
    cacheItemMap.remove(key);
  }

  @Setter
  @Getter
  @Accessors(chain = true)
  class CacheItem {

    private Object obj;
    private Long timeOutInSecond;
  }
}
