package com.olivia.peanut.aps.service.impl.utils;

import cn.hutool.core.collection.CollUtil;
import com.olivia.peanut.aps.api.entity.apsSchedulingDayConfig.ApsSchedulingDayConfigDto;
import com.olivia.peanut.aps.api.entity.apsSchedulingDayConfigItem.ApsSchedulingDayConfigItemDto;
import com.olivia.peanut.aps.enums.ApsSchedulingDayConfigItemConfigBizTypeEnum;
import com.olivia.peanut.aps.model.ApsSchedulingDayConfigVersionDetail;
import com.olivia.peanut.aps.model.ApsSchedulingIssueItem;
import com.olivia.peanut.aps.service.impl.po.ApsSchedulingDayOrderRoomReq;
import com.olivia.peanut.aps.service.impl.po.ApsSchedulingDayOrderRoomRes;
import com.olivia.sdk.utils.$;
import com.olivia.sdk.utils.RunUtils;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;
import lombok.extern.slf4j.Slf4j;

/***
 *
 */
@Slf4j
public class ApsSchedulingDayUtils {

  private ApsSchedulingDayUtils() {
  }

  public static ApsSchedulingDayOrderRoomRes orderRoomStatus(ApsSchedulingDayOrderRoomReq req) {
    List<ApsSchedulingDayConfigVersionDetail> retDetailList = Collections.synchronizedList(new ArrayList<>());
//    List<ApsSchedulingIssueItem> orderItemList = req.getOrderItemList();
    $.requireNonNullCanIgnoreException(req.getIssueItemList(), "排程订单不能为空");
    ApsSchedulingDayConfigDto schedulingDayConfigDto = req.getSchedulingDayConfigDto();
    log.info("orderRoomStatus  orderSize {} schedulingDayConfigD :id {}", req.getIssueItemList().size(), schedulingDayConfigDto.getId());
    List<ApsSchedulingDayConfigItemDto> schedulingDayConfigItemDtoList = schedulingDayConfigDto.getSchedulingDayConfigItemDtoList();

    List<Runnable> runnableList = new ArrayList<>();

    Map<String, List<ApsSchedulingDayConfigItemDto>> configItemMap = schedulingDayConfigItemDtoList.stream()
        .collect(Collectors.groupingBy(t -> t.getRoomId() + "-" + t.getStatusId()));

    configItemMap.forEach((k, itemDtoList) -> {
      runnableList.add(() -> {
        matchOrderItem(req.getIssueItemList(), itemDtoList, retDetailList);
      });
    });

    RunUtils.run("orderRoomStatus " + req.getSchedulingDayId(), runnableList);

    retDetailList.forEach(t -> t.setSchedulingDayId(req.getSchedulingDayId()));
    return new ApsSchedulingDayOrderRoomRes().setApsSchedulingDayConfigVersionDetailList(retDetailList);
  }

  private static void matchOrderItem(List<ApsSchedulingIssueItem> orderItemList, List<ApsSchedulingDayConfigItemDto> itemDtoList,
      List<ApsSchedulingDayConfigVersionDetail> retDetailList) {

    AtomicInteger loopIndex = new AtomicInteger(0);
    AtomicInteger sortIndex = new AtomicInteger(1);
    List<ApsSchedulingIssueItem> failList = new ArrayList<>();

    AtomicBoolean isBreak = new AtomicBoolean(false);
    ArrayList<ApsSchedulingIssueItem> orderList = new ArrayList<>(orderItemList);

    do {
      loopIndex.incrementAndGet();
      isBreak.set(false);
      List<ApsSchedulingDayConfigVersionDetail> loopList = new ArrayList<>();
      List<ApsSchedulingIssueItem> successList = new ArrayList<>();
      for (ApsSchedulingDayConfigItemDto configItemDto : itemDtoList) {
        ApsSchedulingDayConfigItemConfigBizTypeEnum configBizTypeEnum = ApsSchedulingDayConfigItemConfigBizTypeEnum.valueOf(configItemDto.getConfigBizType());
        Long bizId = configItemDto.getConfigBizId();
        long number = configItemDto.getConfigBizNum();
        orderList.addAll(failList);
        failList.clear();
        while (CollUtil.isNotEmpty(orderList) && successList.size() < number) {
          ApsSchedulingIssueItem orderInfo = orderList.remove(0);
          if (configBizTypeEnum == ApsSchedulingDayConfigItemConfigBizTypeEnum.sale) {
            if (orderInfo.getSaleConfigIdList().contains(bizId)) {
              successList.add(orderInfo);
            } else {
              failList.add(orderInfo);
            }
          } else if (configBizTypeEnum == ApsSchedulingDayConfigItemConfigBizTypeEnum.project) {
            if (orderInfo.getProjectConfigIdList().contains(bizId)) {
              successList.add(orderInfo);
            } else {
              failList.add(orderInfo);
            }
          } else if (configBizTypeEnum == ApsSchedulingDayConfigItemConfigBizTypeEnum.bom) {
            if (orderInfo.getBomIdList().contains(bizId)) {
              successList.add(orderInfo);
            } else {
              failList.add(orderInfo);
            }
          }
        }
        log.info("bizId : {} successList  {} {}", bizId, successList.size(), failList.size());
        successList.forEach(orderItem -> {
          isBreak.set(true);
          ApsSchedulingDayConfigVersionDetail detail = new ApsSchedulingDayConfigVersionDetail();
          detail.setRoomId(configItemDto.getRoomId()).setStatusId(configItemDto.getStatusId()).setLoopIndex(loopIndex.get()).setIsMatch(true).setOrderId(orderItem.getOrderId())
              .setOrderNo(orderItem.getOrderNo())
              .setConfigBizId(configItemDto.getConfigBizId()).setConfigBizType(configItemDto.getConfigBizType()).setConfigBizName(configItemDto.getConfigBizName());
          detail.setLoopEnough(true).setSortIndex(sortIndex.getAndIncrement());
          retDetailList.add(detail);
          loopList.add(detail);
        });
        successList.clear();
        if (loopList.size() == itemDtoList.stream().mapToLong(ApsSchedulingDayConfigItemDto::getConfigBizNum).sum()) {
          loopList.forEach(t -> t.setLoopEnough(true));
        } else {
          loopList.forEach(t -> t.setLoopEnough(false));
        }
      }

    } while (isBreak.get());
    ApsSchedulingDayConfigItemDto configItemDto = itemDtoList.get(0);
    orderList.addAll(failList);
    orderList.forEach(orderItem -> {
      ApsSchedulingDayConfigVersionDetail detail = new ApsSchedulingDayConfigVersionDetail();
      detail.setRoomId(configItemDto.getRoomId()).setStatusId(configItemDto.getStatusId()).setLoopIndex(loopIndex.get()).setIsMatch(false).setOrderId(orderItem.getOrderId())
          .setOrderNo(orderItem.getOrderNo())
          .setConfigBizId(configItemDto.getConfigBizId()).setConfigBizType(configItemDto.getConfigBizType()).setConfigBizName(configItemDto.getConfigBizName());
      detail.setLoopEnough(false).setSortIndex(sortIndex.getAndIncrement());
      retDetailList.add(detail);
    });
  }
}


