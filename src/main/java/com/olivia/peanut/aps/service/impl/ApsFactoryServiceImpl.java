package com.olivia.peanut.aps.service.impl;

import static java.lang.Boolean.TRUE;

import com.alibaba.fastjson2.JSON;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.olivia.peanut.aps.api.entity.apsProcessPath.ApsProcessPathDto;
import com.olivia.peanut.aps.api.entity.apsProcessPath.ApsProcessPathQueryListReq;
import com.olivia.peanut.aps.service.ApsFactoryService;
import com.olivia.peanut.aps.service.ApsProcessPathService;
import com.olivia.peanut.aps.service.pojo.FactoryConfigReq;
import com.olivia.peanut.aps.service.pojo.FactoryConfigRes;
import com.olivia.peanut.aps.utils.ProcessUtils;
import com.olivia.peanut.aps.utils.model.ShiftItemVo;
import com.olivia.peanut.portal.api.entity.BaseEntityDto;
import com.olivia.peanut.portal.model.Shift;
import com.olivia.peanut.portal.model.ShiftItem;
import com.olivia.peanut.portal.service.CalendarService;
import com.olivia.peanut.portal.service.ShiftItemService;
import com.olivia.peanut.portal.service.ShiftService;
import com.olivia.sdk.utils.$;
import com.olivia.sdk.utils.RunUtils;
import com.olivia.sdk.utils.model.WeekInfo;
import jakarta.annotation.Resource;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/***
 *
 */

@Slf4j
@Service
public class ApsFactoryServiceImpl implements ApsFactoryService {

  @Resource
  CalendarService calendarService;
  @Resource
  ShiftService shiftService;
  @Resource
  ShiftItemService shiftItemService;

  @Resource
  ApsProcessPathService apsProcessPathService;

  @Override
  public FactoryConfigRes getFactoryConfig(FactoryConfigReq req) {
    if (log.isDebugEnabled()) {
      log.debug("getFactoryConfig req :{}", JSON.toJSONString(req));
    }
    List<Runnable> runnableList = new ArrayList<>();
    FactoryConfigRes res = new FactoryConfigRes().setFactoryId(req.getFactoryId()).setFactoryName(req.getFactoryName());
    if (TRUE.equals(req.getGetWeek())) {
      runnableList.add(() -> {
        $.assertTrueCanIgnoreException(Objects.isNull(req.getWeekBeginDate()) || Objects.isNull(req.getWeekEndDate()), "工厂查询日期不能为空");
        List<WeekInfo> weekList = calendarService.getWeekList(req.getFactoryId(), req.getWeekBeginDate().toString(), req.getWeekEndDate().toString());
        weekList.removeIf(w -> Boolean.FALSE.equals(w.getIsWorkDay()));
        weekList.sort(Comparator.comparing(WeekInfo::getCurrentDate));
        res.setWeekList(weekList);
      });
    }
    if (TRUE.equals(req.getGetShift())) {
      runnableList.add(() -> {
        Shift shift = shiftService.getOne(new LambdaQueryWrapper<Shift>().eq(Shift::getFactoryId, req.getFactoryId()), false);
        $.requireNonNullCanIgnoreException(shift, req.getFactoryName() + "排产版本工厂没有班次");
        List<ShiftItem> shiftItemList = shiftItemService.list(new LambdaQueryWrapper<ShiftItem>().eq(ShiftItem::getShiftId, shift.getId()).orderByAsc(ShiftItem::getBeginTime));
        Long dayWorkSecond = ProcessUtils.getDayWorkSecond($.copyList(shiftItemList, ShiftItemVo.class));
        res.setShiftItemList(shiftItemList).setDayWorkSecond(dayWorkSecond);
      });
    }
    if (TRUE.equals(req.getGetPath())) {
      runnableList.add(() -> {
        Map<Long, ApsProcessPathDto> pathDtoMap = apsProcessPathService.queryList(
                new ApsProcessPathQueryListReq().setData(new ApsProcessPathDto().setFactoryId(req.getFactoryId()).setIsDefault(req.getGetPathDefault())))
            .getDataList().stream().collect(Collectors.toMap(BaseEntityDto::getId, Function.identity()));
        res.setPathDtoMap(pathDtoMap);
      });
    }
    RunUtils.run("getFactoryConfig " + req.getFactoryId(), runnableList);
    return res;
  }
}
