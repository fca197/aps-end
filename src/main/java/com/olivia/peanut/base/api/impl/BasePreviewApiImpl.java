package com.olivia.peanut.base.api.impl;

import cn.hutool.core.collection.CollUtil;
import com.olivia.peanut.aps.model.*;
import com.olivia.peanut.aps.service.*;
import com.olivia.peanut.base.api.BasePreviewApi;
import com.olivia.peanut.base.api.entity.basePreview.SystemConfigPreviewReq;
import com.olivia.peanut.base.api.entity.basePreview.SystemConfigPreviewRes;
import com.olivia.peanut.base.model.BaseRole;
import com.olivia.peanut.base.model.BaseRoleGroup;
import com.olivia.peanut.base.service.BaseRoleGroupService;
import com.olivia.peanut.base.service.BaseRoleService;
import com.olivia.peanut.portal.model.Calendar;
import com.olivia.peanut.portal.model.Factory;
import com.olivia.peanut.portal.model.Shift;
import com.olivia.peanut.portal.model.ShiftItem;
import com.olivia.peanut.portal.service.CalendarService;
import com.olivia.peanut.portal.service.FactoryService;
import com.olivia.peanut.portal.service.ShiftItemService;
import com.olivia.peanut.portal.service.ShiftService;
import com.olivia.sdk.utils.RunUtils;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;

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
  ApsProcessPathService apsProcessPathService;

  @Resource
  ApsRoomService apsRoomService;

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

  @Override
  public SystemConfigPreviewRes systemConfigPreview(SystemConfigPreviewReq req) {

    AtomicReference<List<Factory>> factoryListAtomic = new AtomicReference<>();
    AtomicReference<Map<Long, List<Shift>>> shiftListAtomic = new AtomicReference<>();
    AtomicReference<Map<Long, List<ShiftItem>>> shiftItemListAtomic = new AtomicReference<>();
    AtomicReference<List<BaseRole>> roleAtomic = new AtomicReference<>();
    AtomicReference<List<BaseRoleGroup>> roleGroupAtomic = new AtomicReference<>();
    AtomicReference<List<ApsSaleConfig>> apsSaleConfigAtomic = new AtomicReference<>();
    AtomicReference<List<ApsProjectConfig>> apsProjectConfigAtomic = new AtomicReference<>();
    AtomicReference<List<ApsStatus>> apsStatusAtomic = new AtomicReference<>();
    AtomicReference<List<ApsProduceProcess>> apsProduceProcessAtomic = new AtomicReference<>();
    AtomicReference<List<ApsProcessPath>> apsProcessPathAtomic = new AtomicReference<>();
    AtomicReference<List<ApsRoom>> apsRoomAtomic = new AtomicReference<>();
    AtomicReference<List<ApsGoods>> apsGoodsAtomic = new AtomicReference<>();
    AtomicReference<List<Calendar>> calendarAtomic = new AtomicReference<>();
    AtomicReference<List<ApsWorkshopSection>> apsWorkshopSectionAtomic = new AtomicReference<>();
    AtomicReference<List<ApsWorkshopStation>> apsWorkshopStationAtomic = new AtomicReference<>();
    AtomicReference<List<ApsLogisticsPath>> apsLogisticsPathAtomic = new AtomicReference<>();
    AtomicReference<List<ApsMachine>> apsMachineAtomic = new AtomicReference<>();
    AtomicReference<List<ApsBomGroup>> apsBomGroupAtomic = new AtomicReference<>();
    AtomicReference<Map<Long, List<ApsBom>>> apsBomMapAtomic = new AtomicReference<>();
    AtomicReference<List<ApsGoodsBomBuyPlan>> apsGoodsBomBuyPlanAtomic = new AtomicReference<>();
    AtomicReference<List<ApsGoodsSaleItem>> apsGoodsSaleItemAtomic = new AtomicReference<>();
    AtomicReference<List<ApsGoodsForecast>> apsGoodsForecastAtomic = new AtomicReference<>();
    AtomicReference<List<ApsGoodsForecastMain>> apsGoodsForecastAtomicFinal = new AtomicReference<>();


    List<Runnable> runnableList = new ArrayList<>();
    runnableList.add(() -> factoryListAtomic.set(factoryService.list()));
    runnableList.add(() -> shiftListAtomic.set(shiftService.list().stream().collect(Collectors.groupingBy(Shift::getFactoryId))));
    runnableList.add(() -> shiftItemListAtomic.set(shiftItemService.list().stream().collect(Collectors.groupingBy(ShiftItem::getShiftId))));
    runnableList.add(() -> roleAtomic.set(baseRoleService.list()));
    runnableList.add(() -> roleGroupAtomic.set(baseRoleGroupService.list()));
    runnableList.add(() -> apsSaleConfigAtomic.set(apsSaleConfigService.list()));
    runnableList.add(() -> apsProjectConfigAtomic.set(apsProjectConfigService.list()));
    runnableList.add(() -> apsStatusAtomic.set(apsStatusService.list()));
    runnableList.add(() -> apsProduceProcessAtomic.set(apsProduceProcessService.list()));
    runnableList.add(() -> apsProcessPathAtomic.set(apsProcessPathService.list()));
    runnableList.add(() -> apsRoomAtomic.set(apsRoomService.list()));
    runnableList.add(() -> apsGoodsAtomic.set(apsGoodsService.list()));
    runnableList.add(() -> calendarAtomic.set(calendarService.list()));
    runnableList.add(() -> apsWorkshopSectionAtomic.set(apsWorkshopSectionService.list()));
    runnableList.add(() -> apsWorkshopStationAtomic.set(apsWorkshopStationService.list()));
    runnableList.add(() -> apsLogisticsPathAtomic.set(apsLogisticsPathService.list()));
    runnableList.add(() -> apsMachineAtomic.set(apsMachineService.list()));
    runnableList.add(() -> apsBomGroupAtomic.set(apsBomGroupService.list()));
    runnableList.add(() -> apsBomMapAtomic.set(apsBomService.list().stream().collect(Collectors.groupingBy(ApsBom::getGroupId))));
    runnableList.add(() -> apsGoodsBomBuyPlanAtomic.set(apsGoodsBomBuyPlanService.list()));
    runnableList.add(() -> apsGoodsSaleItemAtomic.set(apsGoodsSaleItemService.list()));
    runnableList.add(() -> apsGoodsForecastAtomic.set(apsGoodsForecastService.list()));
    runnableList.add(() -> apsGoodsForecastAtomicFinal.set(apsGoodsForecastMainService.list()));


    RunUtils.run("preview", runnableList);

    List<Factory> factoryList = factoryListAtomic.get();
    Map<Long, List<Shift>> shiftMap = shiftListAtomic.get();
    Map<Long, List<ShiftItem>> shiftItemMap = shiftItemListAtomic.get();
    List<SystemConfigPreviewRes.Info> list = new ArrayList<>();

    factoryList.forEach(f -> {
      ArrayList<SystemConfigPreviewRes.Info> childrenList = new ArrayList<>();
      SystemConfigPreviewRes.Info info = new SystemConfigPreviewRes.Info().setName(f.getFactoryName()).setChildren(childrenList);
      Long factoryId = f.getId();
      List<Shift> shiftList = shiftMap.getOrDefault(factoryId, List.of());
      childrenList.add(new SystemConfigPreviewRes.Info().setName("班次信息").setChildren(shiftList.stream().map(shift -> new SystemConfigPreviewRes.Info().setName(shift.getShiftName()).setChildren(shiftItemMap.getOrDefault(shift.getId(), List.of()).stream().map(t -> new SystemConfigPreviewRes.Info().setDesc(t.getBeginTime() + "/" + t.getBeginTime()).setChildren(List.of()).setName(t.getBeginTime() + "/" + t.getEndTime())).toList())).toList()));
      list.add(info.setChildren(childrenList));
    });
    ArrayList<SystemConfigPreviewRes.Info> baseChildrenList = new ArrayList<>();
    SystemConfigPreviewRes.Info baseInfo = new SystemConfigPreviewRes.Info().setName("基本配置").setChildren(baseChildrenList);
    baseChildrenList.add(new SystemConfigPreviewRes.Info().setName("角色").setChildren(roleAtomic.get().stream().map(t -> new SystemConfigPreviewRes.Info().setName(t.getRoleName())).toList()));
    baseChildrenList.add(new SystemConfigPreviewRes.Info().setName("角色组").setChildren(roleGroupAtomic.get().stream().map(t -> new SystemConfigPreviewRes.Info().setName(t.getRoleGroupName())).toList()));

    list.add(baseInfo);

    ArrayList<SystemConfigPreviewRes.Info> factoryCommonChildrenList = new ArrayList<>();
    factoryCommonChildrenList.add(new SystemConfigPreviewRes.Info().setName("销售配置").setChildren(apsSaleConfigAtomic.get().stream().map(t -> new SystemConfigPreviewRes.Info().setName(t.getSaleName()).setDesc(Objects.equals(t.getIsValue(), 1) ? "销售特征值" : "销售特征")).toList()));
    factoryCommonChildrenList.add(new SystemConfigPreviewRes.Info().setName("工程特征").setChildren(apsProjectConfigAtomic.get().stream().map(t -> new SystemConfigPreviewRes.Info().setName(t.getSaleName()).setDesc(Objects.equals(t.getIsValue(), 1) ? "工程特征值" : "工程特征")).toList()));
    factoryCommonChildrenList.add(new SystemConfigPreviewRes.Info().setName("状态").setChildren(apsStatusAtomic.get().stream().map(t -> new SystemConfigPreviewRes.Info().setName(t.getStatusName())).toList()));
    factoryCommonChildrenList.add(new SystemConfigPreviewRes.Info().setName("工段").setChildren(apsWorkshopSectionAtomic.get().stream().map(t -> new SystemConfigPreviewRes.Info().setName(t.getSectionName())).toList()));
    factoryCommonChildrenList.add(new SystemConfigPreviewRes.Info().setName("工位").setChildren(apsWorkshopStationAtomic.get().stream().map(t -> new SystemConfigPreviewRes.Info().setName(t.getStationName())).toList()));
    factoryCommonChildrenList.add(new SystemConfigPreviewRes.Info().setName("机器").setChildren(apsMachineAtomic.get().stream().map(t -> new SystemConfigPreviewRes.Info().setName(t.getMachineName())).toList()));
    factoryCommonChildrenList.add(new SystemConfigPreviewRes.Info().setName("零件组").setChildren(apsBomGroupAtomic.get().stream().map(mgt -> new SystemConfigPreviewRes.Info().setName(mgt.getGroupName()).setChildren(apsBomMapAtomic.get().getOrDefault(mgt.getId(), List.of()).stream().map(tt -> new SystemConfigPreviewRes.Info().setName(tt.getBomName())).toList())).toList()));
    list.add(new SystemConfigPreviewRes.Info().setName("工厂通用配置").setChildren(factoryCommonChildrenList));

    setChildrenCount(list);
    return new SystemConfigPreviewRes().setDataList(list);
  }

  private void setChildrenCount(List<SystemConfigPreviewRes.Info> list) {
    if (CollUtil.isEmpty(list)) {
      return;
    }
    for (SystemConfigPreviewRes.Info info : list) {
      if (CollUtil.isEmpty(info.getChildren())) {
        info.setValue(1);
      } else {
        list.forEach(t -> setChildrenCount(t.getChildren()));
        info.setValue(info.getChildren().stream().mapToInt(SystemConfigPreviewRes.Info::getValue).sum() + info.getChildren().size());
      }

    }
  }
}
