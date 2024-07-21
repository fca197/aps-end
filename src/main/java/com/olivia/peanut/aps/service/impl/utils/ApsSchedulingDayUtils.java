package com.olivia.peanut.aps.service.impl.utils;

import cn.hutool.core.collection.CollUtil;
import com.olivia.peanut.aps.api.entity.apsSchedulingDayConfig.ApsSchedulingDayConfigDto;
import com.olivia.peanut.aps.api.entity.apsSchedulingDayConfigItem.ApsSchedulingDayConfigItemDto;
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
        if (CollUtil.isEmpty(itemDtoList) || CollUtil.isEmpty(req.getIssueItemList())) {
          return;
        }

        Long statusId = itemDtoList.get(0).getStatusId();

        String[] ks = k.split("-");
        Long roomId = Long.valueOf(ks[0]);
        List<ApsSchedulingIssueItem> orderItemList = new ArrayList<>(req.getIssueItemList());

        boolean isBreak = false;
        int loopIndex = 0;
        do {
          for (ApsSchedulingDayConfigItemDto apsSchedulingDayConfigItemDto : itemDtoList) {

            List<ApsSchedulingIssueItem> failList = matchOrderItem(roomId, statusId, loopIndex, orderItemList, apsSchedulingDayConfigItemDto, retDetailList);
            if (CollUtil.isEmpty(failList)) {
              isBreak = true;
              break;
            }
            orderItemList.clear();
            orderItemList.addAll(failList);

          }
          loopIndex++;
        } while (!isBreak);

      });
    });

    RunUtils.run("orderRoomStatus " + req.getSchedulingDayId(), runnableList);

    retDetailList.forEach(t -> t.setSchedulingDayId(req.getSchedulingDayId()));
    return new ApsSchedulingDayOrderRoomRes().setApsSchedulingDayConfigVersionDetailList(retDetailList);
  }

  private static List<ApsSchedulingIssueItem> matchOrderItem(Long roomId, Long statusId, Integer loopIndex, List<ApsSchedulingIssueItem> orderItemList,
      ApsSchedulingDayConfigItemDto configItemDto, List<ApsSchedulingDayConfigVersionDetail> retDetailList) {
    List<ApsSchedulingIssueItem> failList = new ArrayList<>();
    ApsSchedulingDayConfigVersionDetail detail = new ApsSchedulingDayConfigVersionDetail();
    detail.setRoomId(roomId);
    detail.setStatusId(statusId).setLoopIndex(loopIndex);
    retDetailList.add(detail);
    return failList;
  }

}
