package com.olivia.peanut.aps.service.impl.utils;

import com.alibaba.fastjson2.JSON;
import com.olivia.peanut.aps.utils.scheduling.ApsSchedulingDayUtils;
import com.olivia.peanut.aps.utils.scheduling.model.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;
import java.util.stream.LongStream;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

@Slf4j
class ApsSchedulingDayUtilsTest {

  @Test
  void orderRoomStatus() {
    ApsSchedulingDayOrderRoomReq req = new ApsSchedulingDayOrderRoomReq();
    req.setSchedulingDayId(1L);
    ApsSchedulingDayConfigDto schedulingDayConfigDto = new ApsSchedulingDayConfigDto();
    schedulingDayConfigDto.setId(100L);
    ArrayList<ApsSchedulingDayConfigItemDto> configItemDtoList = new ArrayList<>();

//    IntStream.range(1, 10).forEach(i -> {

    ApsSchedulingDayConfigItemDto itemDto = new ApsSchedulingDayConfigItemDto();
    itemDto.setSchedulingDayId(schedulingDayConfigDto.getId());
    itemDto.setStatusId(3L);
    itemDto.setRoomId(4L);
    itemDto.setConfigBizId(3L);
    itemDto.setConfigBizType(ApsSchedulingDayConfigItemConfigBizTypeEnum.bom.name());
    itemDto.setConfigBizNum(3L);
    configItemDtoList.add(itemDto);
    itemDto = new ApsSchedulingDayConfigItemDto();

    itemDto.setSchedulingDayId(schedulingDayConfigDto.getId());
    itemDto.setStatusId(3L);
    itemDto.setRoomId(4L);
    itemDto.setConfigBizId(2L);
    itemDto.setConfigBizType(ApsSchedulingDayConfigItemConfigBizTypeEnum.bom.name());
    itemDto.setConfigBizNum(2L);
    configItemDtoList.add(itemDto);
//    });
    List<ApsSchedulingIssueItemDto> orderList = new ArrayList<>();

    IntStream.range(0, 10).forEach(tt -> {
      LongStream.range(0, 4).forEach(t -> {
        orderList.add(new ApsSchedulingIssueItemDto().setOrderNo("NO-" + tt + "-" + t).setBomIdList(List.of(t)).setOrderId(t).setNumberIndex(t));
      });
    });

    req.setIssueItemList(orderList);

    schedulingDayConfigDto.setSchedulingDayConfigItemDtoList(configItemDtoList);
    req.setSchedulingDayConfigDto(schedulingDayConfigDto);
    ApsSchedulingDayOrderRoomRes orderRoomRes = ApsSchedulingDayUtils.orderRoomStatus(req);
    log.info("orderRoomReq:{}", JSON.toJSONString(req));
    log.info("orderRoomRes:{}", JSON.toJSONString(orderRoomRes));
  }
}
