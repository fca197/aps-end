package com.olivia.peanut.aps.service.impl.utils;

import cn.hutool.core.util.ReflectUtil;
import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONObject;
import com.olivia.peanut.aps.api.entity.apsGoodsBomBuyPlanItem.SendMail2supplierReq;
import com.olivia.peanut.aps.model.ApsBom;
import com.olivia.peanut.aps.model.ApsBomSupplier;
import com.olivia.peanut.aps.model.ApsGoodsBomBuyPlanItem;
import com.olivia.peanut.aps.service.impl.po.ApsBomEmail;
import com.olivia.sdk.utils.DateUtils;
import com.olivia.sdk.utils.FieldUtils;
import lombok.extern.slf4j.Slf4j;

import java.time.LocalDate;
import java.util.*;
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
    apsBomList.forEach(apsBom -> {
      Map<Integer, List<ApsGoodsBomBuyPlanItem>> listMap = planBomMap.get(apsBom.getId()).stream().collect(Collectors.groupingBy(ApsGoodsBomBuyPlanItem::getYear));
      ApsBomEmail apsBomEmail = new ApsBomEmail();
      HashMap<String, Object> buyMap = new HashMap<>();
      apsBomEmail.setBomName(apsBom.getBomName()).setBomUnit(apsBomEmail.getBomUnit()).setBuyMap(buyMap);
      apsBomEmailList.add(apsBomEmail);
      localDateBetween.forEach(localDate -> {
        ApsGoodsBomBuyPlanItem planItem = listMap.get(localDate.getYear()).getFirst();
        Object fieldValue = ReflectUtil.getFieldValue(planItem, FieldUtils.getField(planItem, fieldName + localDate.getDayOfYear()));

        if (Objects.isNull(fieldValue)) {
          return;
        }
        JSONObject jsonObject = JSON.parseObject(String.valueOf(fieldValue));
        if (TRUE.equals(jsonObject.getBooleanValue("lack"))) {
          buyMap.put(localDate.toString(), jsonObject.getString("buy_inv"));
        }
      });
    });
    log.info("apsBomEmailList {}", JSON.toJSONString(apsBomEmailList));
  }
}
