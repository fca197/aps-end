package com.olivia.peanut.base.api.impl;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.ReflectUtil;
import com.olivia.peanut.aps.mapper.ApsRoomConfigMapper;
import com.olivia.peanut.aps.model.*;
import com.olivia.peanut.aps.service.*;
import com.olivia.peanut.base.api.BasePreviewApi;
import com.olivia.peanut.base.api.entity.basePreview.SystemConfigPreviewReq;
import com.olivia.peanut.base.api.entity.basePreview.SystemConfigPreviewRes;
import com.olivia.peanut.base.model.BaseRole;
import com.olivia.peanut.base.model.BaseRoleGroup;
import com.olivia.peanut.base.service.BaseRoleGroupService;
import com.olivia.peanut.base.service.BaseRoleService;
import com.olivia.peanut.portal.model.*;
import com.olivia.peanut.portal.service.*;
import com.olivia.sdk.utils.BaseEntity;
import com.olivia.sdk.utils.RunUtils;
import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicReference;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static com.olivia.sdk.utils.FieldUtils.getField;

@RestController

public class BasePreviewApiImpl implements BasePreviewApi {

  @Resource
  FactoryService factoryService;

  @Resource
  ShiftService shiftService;
  @Resource
  ShiftItemService shiftItemService;

  @Resource
  BaseRoleService baseRoleService;
  @Resource
  BaseRoleGroupService baseRoleGroupService;

  @Resource
  ApsSaleConfigService apsSaleConfigService;
  @Resource
  ApsProjectConfigService apsProjectConfigService;
  @Resource
  ApsStatusService apsStatusService;
  @Resource
  ApsProduceProcessService apsProduceProcessService;

  @Resource
  CalendarDayService calendarDayService;
  @Resource
  ApsProcessPathRoomService apsProcessPathRoomService;

  @Resource
  ApsProcessPathService apsProcessPathService;

  @Resource
  ApsRoomService apsRoomService;
  @Resource
  ApsRoomConfigService apsRoomConfigService;

  @Resource
  ApsGoodsService apsGoodsService;
  @Resource
  CalendarService calendarService;
  @Resource
  ApsWorkshopSectionService apsWorkshopSectionService;
  @Resource
  ApsWorkshopStationService apsWorkshopStationService;
  @Resource
  ApsLogisticsPathService apsLogisticsPathService;
  @Resource
  ApsMachineService apsMachineService;
  @Resource
  ApsBomGroupService apsBomGroupService;
  @Resource
  ApsBomService apsBomService;
  @Resource
  ApsGoodsBomBuyPlanService apsGoodsBomBuyPlanService;
  @Resource
  ApsGoodsSaleItemService apsGoodsSaleItemService;

  @Resource
  ApsGoodsForecastService apsGoodsForecastService;
  @Resource
  ApsGoodsForecastMainService apsGoodsForecastMainService;
  @Resource
  ApsProduceProcessItemService apsProduceProcessItemService;
  @Autowired
  private ApsRoomConfigMapper apsRoomConfigMapper;


  @Override
  public SystemConfigPreviewRes systemConfigPreview(SystemConfigPreviewReq req) {

    AtomicReference<List<Factory>> factoryListAtomic = new AtomicReference<>();
    AtomicReference<Map<Long, List<Shift>>> shiftListAtomic = new AtomicReference<>();
    AtomicReference<Map<Long, List<ShiftItem>>> shiftItemListAtomic = new AtomicReference<>();
    AtomicReference<List<BaseRole>> roleAtomic = new AtomicReference<>();
    AtomicReference<List<BaseRoleGroup>> roleGroupAtomic = new AtomicReference<>();
    AtomicReference<List<ApsSaleConfig>> apsSaleConfigAtomic = new AtomicReference<>();
    AtomicReference<List<ApsProjectConfig>> apsProjectConfigAtomic = new AtomicReference<>();
    AtomicReference<Map<Long, ApsStatus>> apsStatusAtomic = new AtomicReference<>();
    AtomicReference<Map<Long, List<ApsProduceProcess>>> apsProduceProcessAtomic = new AtomicReference<>();
    AtomicReference<List<ApsProcessPath>> apsProcessPathAtomic = new AtomicReference<>();
    AtomicReference<Map<Long, List<ApsProcessPathRoom>>> apsProcessPathRoomAtomic = new AtomicReference<>();
    AtomicReference<Map<Long, ApsRoom>> apsRoomAtomic = new AtomicReference<>();
    AtomicReference<Map<Long, List<ApsGoods>>> apsGoodsAtomic = new AtomicReference<>();
    AtomicReference<Map<Long, List<Calendar>>> calendarAtomic = new AtomicReference<>();
    AtomicReference<Map<Long, ApsWorkshopSection>> apsWorkshopSectionAtomic = new AtomicReference<>();
    AtomicReference<Map<Long, ApsWorkshopStation>> apsWorkshopStationAtomic = new AtomicReference<>();
//    AtomicReference<List<ApsLogisticsPath>> apsLogisticsPathAtomic = new AtomicReference<>();
    AtomicReference<Map<Long, List<ApsMachine>>> apsMachineAtomic = new AtomicReference<>();
    AtomicReference<List<ApsBomGroup>> apsBomGroupAtomic = new AtomicReference<>();
    AtomicReference<Map<Long, List<ApsBom>>> apsBomMapAtomic = new AtomicReference<>();
//    AtomicReference<List<ApsGoodsBomBuyPlan>> apsGoodsBomBuyPlanAtomic = new AtomicReference<>();
//    AtomicReference<List<ApsGoodsSaleItem>> apsGoodsSaleItemAtomic = new AtomicReference<>();
//    AtomicReference<List<ApsGoodsForecast>> apsGoodsForecastAtomic = new AtomicReference<>();
//    AtomicReference<List<ApsGoodsForecastMain>> apsGoodsForecastAtomicFinal = new AtomicReference<>();
    AtomicReference<Map<Long, List<ApsProduceProcessItem>>> apsProduceProcessItemMapAtomic = new AtomicReference<>();
    AtomicReference<Map<Long, List<ApsRoomConfig>>> apsRoomConfigMapAtomic = new AtomicReference<>();
    AtomicReference<Map<Long, List<CalendarDay>>> calendarDayMapAtomic = new AtomicReference<>();

    List<Runnable> runnableList = new ArrayList<>();
    runnableList.add(() -> factoryListAtomic.set(factoryService.list()));
    runnableList.add(() -> shiftListAtomic.set(shiftService.list().stream().collect(Collectors.groupingBy(Shift::getFactoryId))));
    runnableList.add(() -> shiftItemListAtomic.set(shiftItemService.list().stream().collect(Collectors.groupingBy(ShiftItem::getShiftId))));
    runnableList.add(() -> roleAtomic.set(baseRoleService.list()));
    runnableList.add(() -> roleGroupAtomic.set(baseRoleGroupService.list()));
    runnableList.add(() -> apsSaleConfigAtomic.set(apsSaleConfigService.list()));
    runnableList.add(() -> apsProjectConfigAtomic.set(apsProjectConfigService.list()));
    runnableList.add(() -> apsStatusAtomic.set(apsStatusService.list().stream().collect(Collectors.toMap(ApsStatus::getId, v -> v))));
    runnableList.add(() -> apsProduceProcessAtomic.set(apsProduceProcessService.list().stream().collect(Collectors.groupingBy(ApsProduceProcess::getFactoryId))));
    runnableList.add(() -> apsProcessPathAtomic.set(apsProcessPathService.list()));
    runnableList.add(() -> apsRoomAtomic.set(apsRoomService.list().stream().collect(Collectors.toMap(ApsRoom::getId, v -> v))));
    runnableList.add(() -> apsGoodsAtomic.set(apsGoodsService.list().stream().collect(Collectors.groupingBy(ApsGoods::getFactoryId))));
    runnableList.add(() -> calendarAtomic.set(calendarService.list().stream().collect(Collectors.groupingBy(Calendar::getFactoryId))));
    runnableList.add(() -> apsWorkshopSectionAtomic.set(apsWorkshopSectionService.list().stream().collect(Collectors.toMap(ApsWorkshopSection::getId, v -> v))));
    runnableList.add(() -> apsWorkshopStationAtomic.set(apsWorkshopStationService.list().stream().collect(Collectors.toMap(ApsWorkshopStation::getId, v -> v))));
//    runnableList.add(() -> apsLogisticsPathAtomic.set(apsLogisticsPathService.list()));
    runnableList.add(() -> apsMachineAtomic.set(apsMachineService.list().stream().collect(Collectors.groupingBy(ApsMachine::getFactoryId))));
    runnableList.add(() -> apsBomGroupAtomic.set(apsBomGroupService.list()));
    runnableList.add(() -> apsBomMapAtomic.set(apsBomService.list().stream().collect(Collectors.groupingBy(t -> Objects.isNull(t.getGroupId()) ? 0 : t.getGroupId()))));
//    runnableList.add(() -> apsGoodsBomBuyPlanAtomic.set(apsGoodsBomBuyPlanService.list()));
//    runnableList.add(() -> apsGoodsSaleItemAtomic.set(apsGoodsSaleItemService.list()));
//    runnableList.add(() -> apsGoodsForecastAtomic.set(apsGoodsForecastService.list()));
//    runnableList.add(() -> apsGoodsForecastAtomicFinal.set(apsGoodsForecastMainService.list()));
    runnableList.add(() -> apsProduceProcessItemMapAtomic.set(apsProduceProcessItemService.list().stream().collect(Collectors.groupingBy(ApsProduceProcessItem::getProduceProcessId))));
    runnableList.add(() -> apsProcessPathRoomAtomic.set(apsProcessPathRoomService.list().stream().collect(Collectors.groupingBy(ApsProcessPathRoom::getProcessPathId))));
    runnableList.add(() -> apsRoomConfigMapAtomic.set(apsRoomConfigService.list().stream().collect(Collectors.groupingBy(t -> Objects.isNull(t.getRoomId()) ? 0 : t.getRoomId()))));

    runnableList.add(() -> calendarDayMapAtomic.set(calendarDayService.list().stream().collect(Collectors.groupingBy(CalendarDay::getCalendarId))));

    RunUtils.run("preview", runnableList);


    // 工厂配置
    List<SystemConfigPreviewRes.Info> list = new ArrayList<>();
    factoryListAtomic.get().forEach(f -> {
      ArrayList<SystemConfigPreviewRes.Info> childrenList = new ArrayList<>();
      SystemConfigPreviewRes.Info factoryInfo = new SystemConfigPreviewRes.Info().setRefId(f.getId()).setName(f.getFactoryName()).setChildren(childrenList);
      list.add(factoryInfo);

      // 班次
      childrenList.add(new SystemConfigPreviewRes.Info().setName("班次信息").setChildren(shiftListAtomic.get().getOrDefault(f.getId(), List.of()).stream().map(shift -> new SystemConfigPreviewRes.Info().setName(shift.getShiftName()).setChildren(shiftItemListAtomic.get().getOrDefault(shift.getId(), List.of()).stream().map(t -> new SystemConfigPreviewRes.Info().setDesc(t.getBeginTime() + "/" + t.getBeginTime()).setChildren(List.of()).setName(t.getBeginTime() + "/" + t.getEndTime())).toList())).toList()));
      //工艺路径
      List<SystemConfigPreviewRes.Info> pathList = apsProcessPathAtomic.get().stream().map(path -> new SystemConfigPreviewRes.Info().setRefId(path.getId()).setName(path.getProcessPathName()).setChildren(apsProcessPathRoomAtomic.get().getOrDefault(path.getId(), List.of()).stream().map(t -> new SystemConfigPreviewRes.Info().setName(apsRoomAtomic.get().getOrDefault(t.getRoomId(), new ApsRoom()).getRoomName()).setChildren(apsRoomConfigMapAtomic.get().getOrDefault(t.getRoomId(), List.of()).stream().map(ct -> new SystemConfigPreviewRes.Info().setName(String.join("/", //
          apsRoomAtomic.get().getOrDefault(ct.getRoomId(), new ApsRoom()).getRoomName(), //
          apsWorkshopSectionAtomic.get().getOrDefault(ct.getSectionId(), new ApsWorkshopSection()).getSectionName(), //
          apsWorkshopStationAtomic.get().getOrDefault(ct.getStationId(), new ApsWorkshopStation()).getStationName(), //
          apsStatusAtomic.get().getOrDefault(ct.getStatusId(), new ApsStatus()).getStatusName(), //
          "耗时：", ct.getExecuteTime().toString()))).toList())).toList())).toList();
      childrenList.add(new SystemConfigPreviewRes.Info().setName("工艺路径").setChildren(pathList));
      Map<Long, ApsMachine> machineMapList = apsMachineAtomic.get().getOrDefault(f.getId(), List.of()).stream().collect(Collectors.toMap(BaseEntity::getId, Function.identity()));
      List<SystemConfigPreviewRes.Info> produceProcessList =
          apsProduceProcessAtomic.get().getOrDefault(f.getId(), List.of()).stream()
              .map(mgt -> new SystemConfigPreviewRes.Info().setRefId(mgt.getId()).setName(mgt.getProduceProcessName()).setChildren(apsProduceProcessItemMapAtomic.get().getOrDefault(mgt.getId(), List.of())
                  .stream().map(tt -> new SystemConfigPreviewRes.Info().setName(machineMapList.getOrDefault(tt.getMachineId(), new ApsMachine().setMachineName("机器已删除")).getMachineName() + "耗时：" + tt.getMachineUseTimeSecond() + "（秒）")).toList())).toList();
      //商品
      childrenList.add(new SystemConfigPreviewRes.Info().setName("商品").setChildren(apsGoodsAtomic.get().getOrDefault(f.getId(), List.of()).stream().//
          map(t -> new SystemConfigPreviewRes.Info().setName(t.getGoodsName()).setRefId(t.getId())//
          .setChildren(List.of(new SystemConfigPreviewRes.Info().setName("工艺路径").setChildren(pathList.stream().filter(gt -> Objects.equals(gt.getRefId(), t.getProcessPathId())).toList()),//
              new SystemConfigPreviewRes.Info().setName("制造路径").setChildren(produceProcessList.stream().filter(gt -> Objects.equals(gt.getRefId(), t.getProduceProcessId())).toList() //
              )))).toList()));
      // 日历
      childrenList.add(new SystemConfigPreviewRes.Info().setName("日历").setChildren(calendarAtomic.get().getOrDefault(f.getId(), List.of()).stream().map(t -> new SystemConfigPreviewRes.Info().setName(t.getCalendarName()).setChildren(calendarDayMapAtomic.get().getOrDefault(t.getId(), List.of()).stream().map(t2 -> new SystemConfigPreviewRes.Info().setName(t2.getDayYear() + "/" +
          t2.getDayMonthAddZero() + " 工作日：" + IntStream.rangeClosed(1, 31).map(t3 -> Objects.equals(ReflectUtil.getFieldValue(t2, getField(t2, "day" + t3)), 1) ? 1 : 0).filter(t3 -> t3 == 1).count() + "个")).toList())).toList()));
      // 机器

      childrenList.add(new SystemConfigPreviewRes.Info().setName("机器").setChildren(machineMapList.values().stream().map((t) -> new SystemConfigPreviewRes.Info().setName(t.getMachineName())).toList()));

      childrenList.add(new SystemConfigPreviewRes.Info().setName("生产路径").setChildren(produceProcessList));

    });

    // 基本配置
    ArrayList<SystemConfigPreviewRes.Info> baseChildrenList = new ArrayList<>();
    SystemConfigPreviewRes.Info baseInfo = new SystemConfigPreviewRes.Info().setName("基本配置").setChildren(baseChildrenList);
    baseChildrenList.add(new SystemConfigPreviewRes.Info().setName("角色").setChildren(roleAtomic.get().stream().map(t -> new SystemConfigPreviewRes.Info().setName(t.getRoleName())).toList()));
    baseChildrenList.add(new SystemConfigPreviewRes.Info().setName("角色组").setChildren(roleGroupAtomic.get().stream().map(t -> new SystemConfigPreviewRes.Info().setName(t.getRoleGroupName())).toList()));

    list.add(baseInfo);


    // 通用配置
    ArrayList<SystemConfigPreviewRes.Info> factoryCommonChildrenList = new ArrayList<>();
    factoryCommonChildrenList.add(new SystemConfigPreviewRes.Info().setName("销售配置").setChildren(apsSaleConfigAtomic.get().stream().map(t -> new SystemConfigPreviewRes.Info().setName(t.getSaleName()).setDesc(Objects.equals(t.getIsValue(), 1) ? "销售特征值" : "销售特征")).toList()));
    factoryCommonChildrenList.add(new SystemConfigPreviewRes.Info().setName("工程特征").setChildren(apsProjectConfigAtomic.get().stream().map(t -> new SystemConfigPreviewRes.Info().setName(t.getSaleName()).setDesc(Objects.equals(t.getIsValue(), 1) ? "工程特征值" : "工程特征")).toList()));
    factoryCommonChildrenList.add(new SystemConfigPreviewRes.Info().setName("状态").setChildren(apsStatusAtomic.get().values().stream().map(t -> new SystemConfigPreviewRes.Info().setName(t.getStatusName())).toList()));
    factoryCommonChildrenList.add(new SystemConfigPreviewRes.Info().setName("工段").setChildren(apsWorkshopSectionAtomic.get().values().stream().map(t -> new SystemConfigPreviewRes.Info().setName(t.getSectionName())).toList()));
    factoryCommonChildrenList.add(new SystemConfigPreviewRes.Info().setName("工位").setChildren(apsWorkshopStationAtomic.get().values().stream().map(t -> new SystemConfigPreviewRes.Info().setName(t.getStationName())).toList()));
    factoryCommonChildrenList.add(new SystemConfigPreviewRes.Info().setName("零件组").setChildren(apsBomGroupAtomic.get().stream().map(mgt -> new SystemConfigPreviewRes.Info().setName(mgt.getGroupName()).setChildren(apsBomMapAtomic.get().getOrDefault(mgt.getId(), List.of()).stream().map(tt -> new SystemConfigPreviewRes.Info().setName(tt.getBomName())).toList())).toList()));
    list.add(new SystemConfigPreviewRes.Info().setName("工厂通用配置").setChildren(factoryCommonChildrenList));
    list.forEach(t -> t.setValue(200L));
    setChildrenCount(list);
    return new SystemConfigPreviewRes().setDataList(list);
  }

  private void setChildrenCount(List<SystemConfigPreviewRes.Info> list) {
    if (CollUtil.isEmpty(list)) {
      return;
    }
    for (SystemConfigPreviewRes.Info info : list) {
      if (CollUtil.isEmpty(info.getChildren())) {
        if (Objects.isNull(info.getValue())) {
          info.setValue(1L);
        }
      } else {
        list.forEach(t -> setChildrenCount(t.getChildren()));
        if (Objects.isNull(info.getValue())) {
//          info.setValue(info.getChildren().stream().mapToLong(SystemConfigPreviewRes.Info::getValue).sum() + info.getChildren().size());
          info.setValue(1L);
        }
      }

    }
  }
}
