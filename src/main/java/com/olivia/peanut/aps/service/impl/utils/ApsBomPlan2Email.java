package com.olivia.peanut.aps.service.impl.utils;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.extra.spring.SpringUtil;
import com.olivia.peanut.aps.api.entity.apsGoodsBomBuyPlanItem.SendMail2supplierReq;
import com.olivia.peanut.aps.model.ApsBom;
import com.olivia.peanut.aps.model.ApsBomSupplier;
import com.olivia.peanut.aps.model.ApsGoodsBomBuyPlanItem;
import com.olivia.peanut.aps.service.impl.po.ApsBomEmail;
import com.olivia.peanut.base.model.TenantInfo;
import com.olivia.peanut.base.service.MailService;
import com.olivia.peanut.base.service.TenantInfoService;
import com.olivia.peanut.base.service.entity.SendMailReq;
import com.olivia.sdk.filter.LoginUserContext;
import com.olivia.sdk.utils.DateUtils;
import com.olivia.sdk.utils.FieldUtils;
import com.olivia.sdk.utils.JSON;
import lombok.extern.slf4j.Slf4j;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

import static com.olivia.peanut.aps.model.ApsGoodsBomBuyPlanItem.fieldName;
import static java.lang.Boolean.TRUE;


@Slf4j
public class ApsBomPlan2Email {
  public static void sendMail(SendMail2supplierReq req, Long id, ApsBomSupplier apsBomSupplier, List<ApsBom> apsBomList, Map<Long, List<ApsGoodsBomBuyPlanItem>> planBomMap) {
    if (Objects.isNull(apsBomSupplier)) {
      log.error("供应商为空 id:{}", id);
      return;
    }

    List<ApsBomEmail> apsBomEmailList = new ArrayList<>();
    List<LocalDate> localDateBetween = DateUtils.getLocalDateBetween(req.getBeginDate(), req.getEndDate());
    AtomicInteger maxDay = new AtomicInteger(0);
    apsBomList.forEach(apsBom -> {
      Map<Integer, List<ApsGoodsBomBuyPlanItem>> listMap = planBomMap.get(apsBom.getId()).stream().collect(Collectors.groupingBy(ApsGoodsBomBuyPlanItem::getYear));
      ApsBomEmail apsBomEmail = new ApsBomEmail();
      HashMap<LocalDate, Object> buyMap = new HashMap<>();
      apsBomEmail.setBomName(apsBom.getBomName()).setBomCostPriceUnit(apsBom.getBomCostPriceUnit()).setBuyMap(buyMap);
      apsBomEmailList.add(apsBomEmail);
      maxDay.set(Math.max(maxDay.get(), apsBom.getDeliveryCycleDay()));
      localDateBetween.forEach(localDate -> {
        ApsGoodsBomBuyPlanItem planItem = listMap.get(localDate.getYear()).getFirst();
        Object fieldValue = FieldUtils.getFieldValue(planItem, FieldUtils.getField(planItem, fieldName + localDate.getDayOfYear()));

        if (Objects.isNull(fieldValue)) {
          return;
        }
        Map<String, Object> jsonObject = JSON.readValue(String.valueOf(fieldValue));
        if (TRUE.equals(jsonObject.get("lack"))) {
          buyMap.put(localDate.minusDays(apsBom.getDeliveryCycleDay()), BigDecimal.valueOf((Double) jsonObject.get("buy_inv")).abs());
        }
      });
    });
    log.info("apsBomEmailList {}", JSON.toJSONString(apsBomEmailList));
    StringBuilder content = new StringBuilder();
    List<LocalDate> localDateBetweenEmail = DateUtils.getLocalDateBetween(req.getBeginDate().minusDays(maxDay.get()), req.getEndDate());
    localDateBetweenEmail.removeIf(t -> {
      AtomicInteger atomicInteger = new AtomicInteger();

      for (ApsBomEmail b : apsBomEmailList) {
        if (b.getBuyMap().containsKey(t)) {
          atomicInteger.incrementAndGet();
          break;
        }
      }

      // 如果都不存在， 删除
      return atomicInteger.get() == 0;
    });

    apsBomEmailList.removeIf(b -> {
      Map<LocalDate, Object> buyMap = b.getBuyMap();
      if (CollUtil.isEmpty(buyMap)) {
        return true;
      }
      AtomicInteger count = new AtomicInteger(0);
      localDateBetweenEmail.forEach(d -> {
        if (Objects.isNull(buyMap.get(d))) count.incrementAndGet();
      });
      return count.get() == localDateBetweenEmail.size();
    });
    TenantInfoService tenantInfoService = SpringUtil.getBean(TenantInfoService.class);
    TenantInfo tenantInfo = tenantInfoService.getById(LoginUserContext.getLoginUser().getTenantId());

    content.append("""
        <html>
        <style type="text/css">html,body {padding:0;margin:0;overflow:auto;overflow-x:hidden;}html {height:100%;}#mainFrame{position:absolute;_position:relative;}
                
        table {
            border-collapse: collapse;
        }
                
        table tr:nth-child(even) {
            background-color: #f2f2f2;
        }
                
        table tr:nth-child(odd) {
            background-color: white;
        }
                
        table tr td {
            border: 1px solid #ddd;
            padding: 10px;
        }
                
                
        </style>
        <body>
        <div>
              
        """);
    content.append("尊敬的").append(apsBomSupplier.getBomSupplierName()).append("团队：");
    content.append("""
              <br/>
        您好！<br/>
               <br/>
        """);
    content.append("我是").append(tenantInfo.getTenantName());
    content.append("""
                    ，首先对您在供应链的专业与成就表示由衷的赞赏。<br/>
                    我司近期因项目需求，拟采购一批零件，经过初步市场调研，贵公司因其优质的产品/服务质量和良好的市场口碑，成为我们此次采购的重点考虑对象。<br/>
                   <br/>      <br/>
                    一、采购需求详情<br/>
                    <br/>
        """);


    content.append("<table >  ");
    content.append("<tr> <td  style='width:200px' >零件名称</td> <td style='width:200px'> 规格</td>");
    localDateBetweenEmail.forEach(d -> {
      content.append("<td style='width:150px'>").append(d).append("</td>");
    });
    content.append("</tr>");
    apsBomEmailList.forEach(b -> {
      content.append("<tr>");
      content.append("<td>").append(b.getBomName()).append("</td>");
      content.append("<td>").append(b.getBomCostPriceUnit()).append("</td>");
      localDateBetweenEmail.forEach(d -> {
        content.append("<td>").append(b.getBuyMap().getOrDefault(d, "")).append("</td>");
      });
    });


    content.append("</table>");
    content.append("<br/>");
    content.append("""
                  <br/>
            二、询价/报价请求<br/>
           <br/>
            请提供上述产品的单价、总价（含税/不含税）、付款方式及付款条件。<br/>
            如有不同批次或配置的价格差异，请一并说明。<br/>
            附上产品目录、样品、技术手册或任何有助于我们了解产品的资料将不胜感激。<br/>         <br/>   <br/>
            三、合作意愿<br/>
            <br/>
            我们期待与贵公司建立长期稳定的合作关系，共同促进双方业务的繁荣发展。<br/>
            若贵公司能满足我们的采购需求，并能在价格、质量、交货期等方面提供竞争优势，我们将优先考虑与贵公司签订正式采购合同。<br/>
            <br/>      <br/>
            四、联系方式<br/>
            <br/>
            为便于进一步沟通，请通过以下方式联系我们：<br/>
            <br/>
            电话：[您的联系电话]<br/>
            邮箱：[您的电子邮箱]<br/>
            请您在收到本函后，于[期望回复日期]前给予回复。如有任何疑问或需要进一步讨论的事项，欢迎随时与我们联系。<br/>
            感谢您的关注与支持，期待与贵公司的合作能带来双赢的局面。<br/>
            <br/>
            此致 <br/>
            <br/>
            敬礼！<br/>
            <br/>
        """);
    content.append(tenantInfo.getTenantName());
    content.append("<br/>");
    content.append("</div>");
    content.append("</body>");
    content.append("</html>");
    MailService mailService = SpringUtil.getBean(MailService.class);
    mailService.sendMail(new SendMailReq().setTo(apsBomSupplier.getBomSupplierEmail()).setSubject("零件采购通知函").setContent(content.toString()));
  }
}
