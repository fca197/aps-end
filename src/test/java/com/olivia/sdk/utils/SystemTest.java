package com.olivia.sdk.utils;

import cn.hutool.system.SystemUtil;
import com.olivia.sdk.utils.JSON;
import org.junit.jupiter.api.Test;

import java.util.Properties;
import java.util.Set;

public class SystemTest {

  @Test
  public void test() {

//    Properties properties = System.getProperties();
//    Set<Object> set = properties.keySet();
//    for (Object key : set) {
//      System.out.println(key + ":" + properties.get(key));
//    }
    System.out.println(JSON.toJSONString(SystemUtil.getOsInfo()));
    System.out.println(JSON.toJSONString(SystemUtil.getFreeMemory()));
    System.out.println(JSON.toJSONString(SystemUtil.getCurrentPID()));
    System.out.println(JSON.toJSONString(SystemUtil.getHostInfo()));
    System.out.println(JSON.toJSONString(SystemUtil.getJavaInfo()));
    System.out.println(JSON.toJSONString(SystemUtil.getRuntimeInfo()));
    System.out.println(JSON.toJSONString(SystemUtil.getJvmInfo()));
    System.out.println(JSON.toJSONString(SystemUtil.getJvmSpecInfo()));
    System.out.println(JSON.toJSONString(SystemUtil.getJavaRuntimeInfo()));
    System.out.println(JSON.toJSONString(SystemUtil.getTotalThreadCount()));
  }
}
