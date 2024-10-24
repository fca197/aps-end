package com.olivia.peanut.portal.api.impl;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.date.DatePattern;
import cn.hutool.core.date.DateUtil;
import com.alibaba.fastjson2.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.IdWorker;
import com.olivia.peanut.portal.api.JcxInventoryApi;
import com.olivia.peanut.portal.api.entity.jcxInventory.GoodsInventoryRes;
import com.olivia.peanut.portal.model.JcxGoods;
import com.olivia.peanut.portal.model.JcxGoodsWarning;
import com.olivia.peanut.portal.model.MsgMessage;
import com.olivia.peanut.portal.service.JcxGoodsService;
import com.olivia.peanut.portal.service.JcxGoodsWarningService;
import com.olivia.peanut.portal.service.MsgMessageService;
import com.olivia.sdk.filter.LoginUserContext;
import com.olivia.sdk.utils.$;
import com.olivia.sdk.utils.BaseEntity;
import com.olivia.sdk.utils.DynamicsPage.Header;
import com.olivia.sdk.utils.TableJson;
import jakarta.annotation.Resource;

import java.util.*;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RestController;

/***
 *
 */
@Slf4j
@RestController
public class JcxInventoryApiImpl implements JcxInventoryApi {

  @Resource
  JcxGoodsService jcxGoodsService;
  @Resource
  MsgMessageService msgMessageService;

  @Resource
  JcxGoodsWarningService jcxGoodsWarningService;

  @Override
  public GoodsInventoryRes goodsInventory(Long type, Long tenantId) {
    LoginUserContext.ignoreTenantId();
    List<JcxGoods> list = this.jcxGoodsService.list(
        new LambdaQueryWrapper<JcxGoods>().eq(BaseEntity::getTenantId, tenantId).eq(JcxGoods::getIsInventory, Boolean.TRUE).apply("goods_inventory_count < warning_count"));
    if (CollUtil.isEmpty(list)) {
      log.warn("没有盘点库存商品");
    }
    List<JcxGoodsWarning> jcxGoodsWarningList = new ArrayList<>();
    List<Header> headerList = new ArrayList<>();
    headerList.add(new Header().setFieldName("goodsName").setShowName("商品名称"));
    headerList.add(new Header().setFieldName("goodsInventoryCount").setShowName("剩余库存"));
    headerList.add(new Header().setFieldName("warningCount").setShowName("预警库存"));
    List<Map<String, Object>> bodyList = new ArrayList<>();
    String messageTitle = Objects.equals(type, 1L) ? "手动盘点" + DateUtil.format(new Date(), DatePattern.PURE_DATETIME_PATTERN) : "商品自动盘点 " + DateUtil.today();
    list.forEach(t -> {
      bodyList.add(Map.of("goodsName", t.getGoodsName(), "goodsInventoryCount", t.getGoodsInventoryCount(), "warningCount", t.getWarningCount()));
      JcxGoodsWarning goodsWarning = $.copy(t, JcxGoodsWarning.class);
      goodsWarning.setGoodsId(t.getId()).setReportNo(messageTitle).setIsDone(Boolean.FALSE).setId(IdWorker.getId());
      jcxGoodsWarningList.add(goodsWarning);
    });
    JSONObject jsonObject = TableJson.toJson(headerList, bodyList);
    MsgMessage message = new MsgMessage();
    message.setMessageTitle(messageTitle).setMessageContext("盘点库存商品告警如下:").setIsAll(Boolean.TRUE);
    message.setMessageJsonData(jsonObject.toJSONString());
    msgMessageService.save(message);
    jcxGoodsWarningService.saveBatch(jcxGoodsWarningList);
    return new GoodsInventoryRes().setMsgId(message.getId());
  }
}
