package com.olivia.test.forcecast.drools;


import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.kie.api.KieBase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @ClassName DroolsTest
 * @Description TODO drools规则引擎单元测试
 * @Author tangxl
 * @create 2023-05-09 18:59
 **/
@SpringBootTest()
@Slf4j
public class DroolsTest {

  @Autowired
  private KieBase kieBase;

  @Test
  public void droolsTest() {
//        Order order = new Order();
//        order.setAmount(2000);
//        //创建会话对象，用于和规则交互的引擎
//        KieSession kieSession = kieBase.newKieSession();
//        kieSession.setGlobal("ps", propService);
//        kieSession.setGlobal("asx", 1111);
//        //讲数据交给规则引擎，规则迎请会根据提供的数据进行规则匹配
//        kieSession.insert(order);
//        //激活规则引擎，如果匹配成功则执行
//        kieSession.fireAllRules(new RuleNameStartsWithAgendaFilter("score_"));
//        //关闭会话
//        kieSession.dispose();
//        System.out.println("222订单金额：" + order.getAmount() + ",订单积分：" + order.getScore());
  }

  @Test
  public void droolsTestQuery() {
//        Order order = new Order();
//        order.setAmount(200);
//        Order order2 = new Order();
//        order2.setAmount(0);
//        //创建会话对象，用于和规则交互的引擎
//        KieSession kieSession = kieBase.newKieSession();
//        //讲数据交给规则引擎，规则迎请会根据提供的数据进行规则匹配
//        kieSession.insert(order);
//        kieSession.insert(order2);
//        //激活规则引擎，如果匹配成功则执行
//        QueryResults queryResultsRows = kieSession.getQueryResults("score_max");
//        queryResultsRows.forEach(t -> {
//            log.info("score_max:" + t.get("$s"));
//        });
//        queryResultsRows = kieSession.getQueryResults("score_max2");
//        queryResultsRows.forEach(t -> {
//            log.info("score_max2:" + t.get("$s2"));
//        });
//
//        //关闭会话
//        kieSession.dispose();
//        System.out.println("订单金额：" + order.getAmount() + ",订单积分：" + order.getScore());
  }
}
