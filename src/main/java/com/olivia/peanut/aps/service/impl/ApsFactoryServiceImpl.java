package com.olivia.peanut.aps.service.impl;

import static java.lang.Boolean.TRUE;

import com.alibaba.fastjson2.JSON;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
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
import java.time.Duration;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
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

  Cache<String, List<WeekInfo>> factoryWeekInfoCache = CacheBuilder.newBuilder().maximumSize(20).expireAfterWrite(Duration.ofMinutes(10)).build();
  Cache<String, List<ShiftItem>> factoryShiftCache = CacheBuilder.newBuilder().maximumSize(20).expireAfterWrite(Duration.ofMinutes(10)).build();
  Cache<String, Map<Long, ApsProcessPathDto>> factoryPathCache = CacheBuilder.newBuilder().maximumSize(20).expireAfterWrite(Duration.ofMinutes(10)).build();

  @Override
  public FactoryConfigRes getFactoryConfig(FactoryConfigReq req) {
    if (log.isDebugEnabled()) {
      log.debug("getFactoryConfig req :{}", JSON.toJSONString(req));
    }
    List<Runnable> runnableList = new ArrayList<>();
    Long factoryId = req.getFactoryId();
    FactoryConfigRes res = new FactoryConfigRes().setFactoryId(factoryId).setFactoryName(req.getFactoryName());
    if (TRUE.equals(req.getGetWeek())) {
      runnableList.add(() -> {
        try {
          List<WeekInfo> retList = factoryWeekInfoCache.get(factoryId.toString() + req.getWeekBeginDate() + req.getWeekEndDate(), () -> {
            List<WeekInfo> weekList = calendarService.getWeekList(factoryId, req.getWeekBeginDate(), req.getWeekEndDate());
            weekList.removeIf(w -> Boolean.FALSE.equals(w.getIsWorkDay()));
            weekList.sort(Comparator.comparing(WeekInfo::getCurrentDate));
            return weekList;
          });
          res.setWeekList(retList);
        } catch (Exception e) {
          log.error("factoryWeekInfoCache {} error:{}", factoryId, e.getMessage(), e);
        }
      });
    }
    if (TRUE.equals(req.getGetShift())) {
      runnableList.add(() -> {
        try {
          List<ShiftItem> shiftItemList = factoryShiftCache.get("dayWorkSecond-" + factoryId, () -> {
            Shift shift = shiftService.getOne(new LambdaQueryWrapper<Shift>().eq(Shift::getFactoryId, factoryId), false);
            $.requireNonNullCanIgnoreException(shift, req.getFactoryName() + "排产版本工厂没有班次");
            return shiftItemService.list(new LambdaQueryWrapper<ShiftItem>().eq(ShiftItem::getShiftId, shift.getId()).orderByAsc(ShiftItem::getBeginTime));
          });
          List<ShiftItemVo> shiftItemVoList = $.copyList(shiftItemList, ShiftItemVo.class);
          Long dayWorkSecond = ProcessUtils.getDayWorkSecond(shiftItemVoList);
          Long dayWorkLastSecond = ProcessUtils.getDayWorkLastSecond(shiftItemVoList, LocalTime.now());
          res.setShiftItemList(shiftItemList).setDayWorkSecond(dayWorkSecond).setDayWorkLastSecond(dayWorkLastSecond);
        } catch (Exception e) {
          log.error("shiftItemList error {} error:{}", factoryId, e.getMessage(), e);
        }
      });
    }
    if (TRUE.equals(req.getGetPath())) {
      runnableList.add(() -> {
        try {
          Map<Long, ApsProcessPathDto> pathDtoMap = factoryPathCache.get(req.getFactoryName(),
              () -> apsProcessPathService.queryList(new ApsProcessPathQueryListReq().setData(new ApsProcessPathDto().setFactoryId(factoryId).setIsDefault(req.getGetPathDefault())))
                  .getDataList().stream().collect(Collectors.toMap(BaseEntityDto::getId, Function.identity())));
          res.setPathDtoMap(pathDtoMap);
        } catch (Exception e) {
          log.error("factoryPathCache {} error: {}", factoryId, e.getMessage(), e);
        }

      });
    }
    RunUtils.run("getFactoryConfig " + factoryId, runnableList);
    return res;
  }
}
