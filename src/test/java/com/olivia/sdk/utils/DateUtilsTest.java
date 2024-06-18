package com.olivia.sdk.utils;

import static com.olivia.sdk.utils.DateUtils.getLocalDateBetween;

import com.alibaba.fastjson2.JSON;
import com.olivia.sdk.utils.model.WeekInfo;
import java.time.LocalDate;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

@Slf4j
class DateUtilsTest {

  @Test
  void getWeekList2() {
    List<LocalDate> localDateBetween = getLocalDateBetween(LocalDate.now(), LocalDate.now().plusDays(7));
    log.info("localDateBetween:{}", JSON.toJSONString(localDateBetween));
  }

  void getWeekList() {
    List<WeekInfo> weekList = DateUtils.getWeekList("2024-04", "2024-04");
    log.info("weekList:{}", JSON.toJSONString(weekList));
  }
}
