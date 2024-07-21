package com.olivia.peanut.aps.service.impl.utils;

import com.alibaba.fastjson2.JSON;
import com.olivia.peanut.aps.api.entity.apsSchedulingDayConfig.ApsSchedulingDayConfigDto;
import com.olivia.peanut.aps.api.entity.apsSchedulingDayConfigItem.ApsSchedulingDayConfigItemDto;
import com.olivia.peanut.aps.enums.ApsSchedulingDayConfigItemConfigBizTypeEnum;
import com.olivia.peanut.aps.model.ApsSchedulingIssueItem;
import com.olivia.peanut.aps.service.impl.po.ApsSchedulingDayOrderRoomReq;
import com.olivia.peanut.aps.service.impl.po.ApsSchedulingDayOrderRoomRes;
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

    IntStream.range(1, 10).forEach(i -> {
      ApsSchedulingDayConfigItemDto itemDto = new ApsSchedulingDayConfigItemDto();
      itemDto.setSchedulingDayId(schedulingDayConfigDto.getId());
      itemDto.setStatusId((long) i);
      itemDto.setRoomId((long) (i % 4));
      itemDto.setConfigBizId((long) (i / 4));
      itemDto.setConfigBizType(ApsSchedulingDayConfigItemConfigBizTypeEnum.bom.name());
      itemDto.setConfigBizNum(3L);
      configItemDtoList.add(itemDto);
    });
    List<ApsSchedulingIssueItem> orderList = new ArrayList<>();
    LongStream.range(10, 200).forEach(t -> {
      orderList.add(new ApsSchedulingIssueItem().setOrderNo("NO-" + t).setBomIdList(List.of(t % 10)).setOrderId(t).setNumberIndex(t));
    });
    req.setIssueItemList(orderList);

    schedulingDayConfigDto.setSchedulingDayConfigItemDtoList(configItemDtoList);
    req.setSchedulingDayConfigDto(schedulingDayConfigDto);
    ApsSchedulingDayOrderRoomRes orderRoomRes = ApsSchedulingDayUtils.orderRoomStatus(req);
    log.info("orderRoomRes:{}", JSON.toJSONString(orderRoomRes));
  }
}
