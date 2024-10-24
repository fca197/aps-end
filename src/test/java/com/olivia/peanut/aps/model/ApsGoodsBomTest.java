package com.olivia.peanut.aps.model;

import com.googlecode.aviator.AviatorEvaluator;

import java.util.Map;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

@Slf4j
class ApsGoodsBomTest {

  @Test
  public void test() {
    String bom = " (AA001&AC002)&(AB001|AB002)";

    StringBuilder sb = new StringBuilder();
    for (int i = 0; i < bom.length(); i++) {
      char c = bom.charAt(i);
      if (c == '(') {
        sb.append(" ( ");
      } else if (c == ')') {
        sb.append(" ) ");
      } else if (c == '&') {
        sb.append(" && ");
      } else if (c == '|') {
        sb.append(" || ");
      } else {
        sb.append(c);
      }
    }
    log.debug("sb: {}", String.join("-", sb.toString().split(" ")));
  }

  @Test
  public void test2() {
    Object result = AviatorEvaluator.execute("(true && true )&& ( AB002 || true)", Map.of("AB002", false));
    log.debug("result: {}", result);
  }
}
