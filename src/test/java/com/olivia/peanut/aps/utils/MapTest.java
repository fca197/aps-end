package com.olivia.peanut.aps.utils;

import java.util.HashMap;
import java.util.Map;
import org.junit.jupiter.api.Test;

/***
 *
 */
public class MapTest {

  @Test
  public void test() {
    // 创建一个 HashMap 实例
    Map<String, Integer> map = new HashMap<>();

    // 添加键值对到 HashMap
    map.put("key1", 10);
    map.put("key2", 20);
    map.put("key3", null);

    // 使用 compute 方法更新现有的键值对
    map.compute("key1", (k, v) -> v == null ? 1 : v + 1); // key1 存在，值为 10，将值更新为 11
    map.compute("key2", (k, v) -> v == null ? 1 : v + 1); // key2 存在，值为 20，将值更新为 21
    map.compute("key3", (k, v) -> v == null ? 1 : v + 1); // key3 存在，值为 null，将值更新为 1
    map.compute("key4", (k, v) -> v == null ? 1 : v + 1); // key4 不存在，直接返回 1
    map.computeIfPresent("key6", (k, v) -> v == null ? 1 : v + 1); // key4 不存在，直接返回 1
    map.putIfAbsent("key7", 44); // key4 不存在，直接返回 1
    map.putIfAbsent("key1", 44); // key4 不存在，直接返回 1

    // 输出更新后的 Map 内容
    System.out.println("Updated Map: " + map);
  }
}
