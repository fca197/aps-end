package com.olivia;

import com.google.ortools.Loader;
import com.googlecode.aviator.AviatorEvaluator;
import com.googlecode.aviator.Options;
import com.olivia.sdk.config.ServiceNotice;
import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/***
 *
 */
@Slf4j
@EnableCaching
//@EnableAspectJAutoProxy
@EnableAspectJAutoProxy(proxyTargetClass = true, exposeProxy = true)
@MapperScan(basePackages = {"com.olivia.peanut.*.mapper"})
@SpringBootApplication

@EnableTransactionManagement
public class BootstrapApplication {

  static {
    // 加载 or-tools 库
    Loader.loadNativeLibraries();

    AviatorEvaluator.getInstance().setOption(Options.OPTIMIZE_LEVEL, AviatorEvaluator.COMPILE);
  }


  public static void main(String[] args) {
    try {
      SpringApplication.run(BootstrapApplication.class, args);
      ServiceNotice.start();
    } catch (Exception e) {
      ServiceNotice.errorStop(e);
    }
  }
}
