//package com.olivia.peanut.aps.utils;
//
//import cn.hutool.core.date.DatePattern;
//import cn.hutool.core.util.RandomUtil;
//import com.olivia.peanut.aps.api.entity.apsProcessPath.ApsProcessPathDto;
//import com.olivia.peanut.aps.api.entity.apsProcessPathRoom.ApsProcessPathRoomDto;
//import com.olivia.peanut.aps.api.entity.apsRoomConfig.ApsRoomConfigDto;
//import com.olivia.peanut.aps.utils.model.ApsProcessPathInfo;
//import com.olivia.peanut.aps.utils.model.ApsProcessPathVo;
//import com.olivia.peanut.aps.utils.model.ShiftItemVo;
//import com.olivia.peanut.aps.utils.process.ProcessUtils;
//import com.olivia.peanut.base.model.ShiftItem;
//import com.olivia.sdk.utils.$;
//import com.olivia.sdk.utils.model.WeekInfo;
//
//import java.time.LocalDate;
//import java.time.LocalTime;
//import java.time.format.DateTimeFormatter;
//import java.util.ArrayList;
//import java.util.List;
//import java.util.concurrent.atomic.AtomicReference;
//import java.util.stream.IntStream;
//
//import org.junit.jupiter.api.Test;
//
//class ProcessUtilsTest {
//
//  @Test
//  public void testProcess() {
//
//    ApsProcessPathDto apsProcessPathDto = new ApsProcessPathDto();
//    IntStream.range(0, 4).forEach(i -> {
//      ArrayList<ApsProcessPathRoomDto> pathRoomList = new ArrayList<>();
//      IntStream.range(0, 4).forEach(j -> {
//        ApsProcessPathRoomDto roomDto = new ApsProcessPathRoomDto();
//        IntStream.range(0, 4).forEach(k -> {
//          ArrayList<ApsRoomConfigDto> apsRoomConfigList = new ArrayList<>();
//          IntStream.range(0, 30).forEach(l -> {
//            ApsRoomConfigDto roomConfig = new ApsRoomConfigDto();
//            roomConfig.setExecuteTime(RandomUtil.randomInt(3, 17) * 100);
//            apsRoomConfigList.add(roomConfig);
//          });
//          roomDto.setApsRoomConfigList(apsRoomConfigList);
//        });
//        pathRoomList.add(roomDto);
//      });
//      apsProcessPathDto.setPathRoomList(pathRoomList);
//    });
//
//    List<WeekInfo> weekInfoList = new ArrayList<>();
//    AtomicReference<LocalDate> localReference = new AtomicReference<>(LocalDate.now());
//    IntStream.range(0, 7).forEach(i -> {
//      WeekInfo weekInfo = new WeekInfo();
//      LocalDate now = localReference.get().minusDays(1 + i);
//      weekInfo.setCurrentDay(now.format(DateTimeFormatter.ofPattern(DatePattern.NORM_DATE_PATTERN)));
//      weekInfo.setCurrentDate(now);
//
//      weekInfo.setIsWorkDay(true);
//      weekInfoList.add(weekInfo);
//      localReference.set(now);
//    });
//    List<ShiftItem> shiftItemList = new ArrayList<>();
//    shiftItemList.add(new ShiftItem().setBeginTime(LocalTime.of(8, 0, 0)).setEndTime(LocalTime.of(20, 0, 0)));
//    shiftItemList.add(new ShiftItem().setBeginTime(LocalTime.of(20, 0, 0)).setEndTime(LocalTime.of(8, 0, 0)));
//    Long dayWorkSecond = ProcessUtils.getDayWorkSecond($.copyList(shiftItemList, ShiftItemVo.class));
//    ApsProcessPathInfo apsProcessPathInfo = ProcessUtils.apsProcessPathInfo($.copy(apsProcessPathDto, ApsProcessPathVo.class), weekInfoList, LocalDate.now(), dayWorkSecond);
//
//  }
//
//
//}
