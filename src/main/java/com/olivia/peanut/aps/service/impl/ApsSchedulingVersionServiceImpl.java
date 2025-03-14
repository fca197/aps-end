package com.olivia.peanut.aps.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.ReflectUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.IdWorker;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.github.yulichang.base.MPJBaseServiceImpl;
import com.github.yulichang.wrapper.MPJLambdaWrapper;
import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.google.common.collect.Maps;
import com.olivia.peanut.aps.api.entity.apsOrder.ApsOrderStatusEnum;
import com.olivia.peanut.aps.api.entity.apsProcessPath.ApsProcessPathDto;
import com.olivia.peanut.aps.api.entity.apsSchedulingVersion.*;
import com.olivia.peanut.aps.con.ApsStr;
import com.olivia.peanut.aps.mapper.ApsSchedulingVersionMapper;
import com.olivia.peanut.aps.model.*;
import com.olivia.peanut.aps.service.*;
import com.olivia.peanut.aps.service.impl.po.OrderGoods;
import com.olivia.peanut.aps.service.pojo.FactoryConfigReq;
import com.olivia.peanut.aps.service.pojo.FactoryConfigRes;
import com.olivia.peanut.aps.utils.capacity.MakeCapacityUtils;
import com.olivia.peanut.aps.utils.capacity.model.Limit;
import com.olivia.peanut.aps.utils.capacity.model.Limit.LimitTypeEnum;
import com.olivia.peanut.aps.utils.capacity.model.MakeCapacityResult;
import com.olivia.peanut.aps.utils.constrained.ConstrainedContentUtils;
import com.olivia.peanut.aps.utils.constrained.model.ConstrainedResult;
import com.olivia.peanut.aps.utils.constrained.model.sub.ConstrainedContent;
import com.olivia.peanut.aps.utils.constrained.model.sub.constrained.FieldConfig;
import com.olivia.peanut.aps.utils.model.ApsGoodsBomVo;
import com.olivia.peanut.aps.utils.model.ApsProcessPathInfo;
import com.olivia.peanut.aps.utils.model.ApsProcessPathInfo.Info;
import com.olivia.peanut.aps.utils.model.ApsProcessPathVo;
import com.olivia.peanut.aps.utils.process.ProcessUtils;
import com.olivia.peanut.base.model.CalendarDay;
import com.olivia.peanut.base.model.Factory;
import com.olivia.peanut.base.service.CalendarDayService;
import com.olivia.peanut.base.service.FactoryService;
import com.olivia.peanut.util.SetNamePojoUtils;
import com.olivia.sdk.ann.SetUserName;
import com.olivia.sdk.comment.ServiceComment;
import com.olivia.sdk.service.SetNameService;
import com.olivia.sdk.utils.*;
import com.olivia.sdk.utils.DynamicsPage.Header;
import com.olivia.sdk.utils.model.WeekInfo;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.aop.framework.AopContext;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.lang.reflect.Field;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static com.olivia.peanut.aps.con.ApsStr.GOODS_STATUS_ID;
import static com.olivia.peanut.aps.utils.capacity.model.Limit.LimitTypeEnum.SALE_CONFIG_LIMIT;
import static com.olivia.sdk.utils.FieldUtils.getField;
import static com.olivia.sdk.utils.JSON.toJSONString;
import static com.olivia.sdk.utils.ValueUtils.value2Str;
import static java.lang.Boolean.FALSE;

/**
 * (ApsSchedulingVersion)表服务实现类
 *
 * @author peanut
 * @since 2024-04-13 21:32:14
 */
@Slf4j
@Service("apsSchedulingVersionService")
@Transactional
public class ApsSchedulingVersionServiceImpl extends MPJBaseServiceImpl<ApsSchedulingVersionMapper, ApsSchedulingVersion> implements ApsSchedulingVersionService {

  final static Cache<String, Map<String, String>> cache = CacheBuilder.newBuilder().maximumSize(100).expireAfterWrite(30, TimeUnit.MINUTES).build();
  private final String SALE = "sale";
  private final String PROJECT = "project";
  @Resource
  ApsSchedulingConstraintsService apsSchedulingConstraintsService;
  @Resource
  ApsOrderService apsOrderService;

  // 以下为私有对象封装
  @Resource
  ApsSchedulingVersionItemService apsSchedulingVersionItemService;
  @Resource
  ApsGoodsService apsGoodsService;
  @Resource
  ApsOrderUserService apsOrderUserService;
  @Resource
  ApsOrderGoodsService apsOrderGoodsService;
  @Resource
  ApsSchedulingVersionCapacityService apsSchedulingVersionCapacityService;
  @Resource
  ApsMakeCapacityFactoryService apsMakeCapacityFactoryService;
  @Resource
  FactoryService factoryService;
  @Resource
  ApsMakeCapacityGoodsService apsMakeCapacityGoodsService;
  @Resource
  ApsSchedulingVersionLimitService apsSchedulingVersionLimitService;
  @Resource
  ApsOrderGoodsSaleConfigService apsOrderGoodsSaleConfigService;
  @Resource
  ApsSaleConfigService apsSaleConfigService;
  @Resource
  ApsMakeCapacitySaleConfigService apsMakeCapacitySaleConfigService;
  @Resource
  CalendarDayService calendarDayService;

  @Resource
  ApsSchedulingVersionDayService apsSchedulingVersionDayService;

  @Resource
  ApsGoodsBomService apsGoodsBomService;

  @Resource
  ApsSchedulingGoodsBomService apsSchedulingGoodsBomService;
  @Resource
  @Lazy
  ApsSchedulingGoodsBomTotalService apsSchedulingGoodsBomTotalService;
  @Resource
  ApsOrderGoodsStatusDateService apsOrderGoodsStatusDateService;
  @Resource
  ApsSchedulingGoodsStatusDateService apsSchedulingGoodsStatusDateService;
  @Resource

  ApsFactoryService apsFactoryService;
  @Resource
  SetNameService setNameService;

  private static List<Runnable> getBomRunList(ApsSchedulingVersion schedulingVersion, List<ApsSchedulingVersionCapacity> apsSchedulingVersionCapacityList,
                                              Map<Long, ApsGoods> goodsMap, Map<Long, ApsProcessPathDto> apsProcessPathDtoMap, Map<Long, List<WeekInfo>> factoryWeekListMap, Map<Long, Long> dayWorkSecondMap,
                                              Map<Long, Map<Long, List<ApsGoodsBom>>> goodsBomMap, List<ApsSchedulingGoodsBom> apsSchedulingGoodsBomList, List<ApsSchedulingGoodsStatusDate> apsOrderGoodsStatusDateList) {
    List<Runnable> runnableList = new ArrayList<>();
    apsSchedulingVersionCapacityList.forEach(order -> {
      runnableList.add(() -> {
        Long goodsId = order.getGoodsId();
        ApsGoods apsGoods = goodsMap.get(goodsId);
        Long factoryId = order.getFactoryId();
        ApsProcessPathDto apsProcessPathDto = apsProcessPathDtoMap.get(apsGoods.getProcessPathId());
        List<WeekInfo> weekInfoList = factoryWeekListMap.get(factoryId);
        Long dayWorkSecond = dayWorkSecondMap.get(factoryId);
        Map<Long, List<ApsGoodsBom>> apsGoodsBomList = goodsBomMap.getOrDefault(goodsId, Map.of());
        Map<Long, List<ApsGoodsBomVo>> apsGoodsBomVoMap = apsGoodsBomList.keySet().stream()
            .collect(Collectors.toMap(key -> key, vL -> $.copyList(apsGoodsBomList.get(vL), ApsGoodsBomVo.class)));
        ApsProcessPathInfo scheduledPathDate = ProcessUtils.schedulePathDate($.copy(apsProcessPathDto, ApsProcessPathVo.class), weekInfoList, 0L, dayWorkSecond,
            order.getGoodsStatusId(), apsGoodsBomVoMap, LocalDate.parse(order.getCurrentDay()));
        if (Objects.nonNull(scheduledPathDate)) {
          List<Info> dataList = scheduledPathDate.getDataList();
          AtomicInteger statusIndex = new AtomicInteger(1);
          dataList.forEach(info -> {
            ApsSchedulingGoodsStatusDate statusDate = new ApsSchedulingGoodsStatusDate().setSchedulingId(schedulingVersion.getId());
            statusDate.setExpectMakeBeginTime(info.getBeginLocalDate()).setExpectMakeEndTime(info.getEndLocalDate()).setGoodsStatusId(info.getStatusId()).setGoodsId(goodsId)
                .setOrderId(order.getOrderId()).setFactoryId(order.getFactoryId()).setStatusIndex(statusIndex.getAndIncrement());
            apsOrderGoodsStatusDateList.add(statusDate);
            List<ApsGoodsBomVo> bomList = info.getApsGoodsBomList();
            if (CollUtil.isNotEmpty(bomList)) {
              bomList.forEach(bom -> {
                ApsSchedulingGoodsBom goodsBom = $.copy(bom, ApsSchedulingGoodsBom.class);
                goodsBom.setGoodsId(goodsId).setFactoryId(factoryId).setGoodsBomId(bom.getId()).setBomId(goodsBom.getBomId()).setBomUseDate(info.getBeginLocalDate());
                goodsBom.setGoodsStatusId(info.getStatusId());
                goodsBom.setSchedulingId(schedulingVersion.getId()).setOrderId(order.getOrderId());
                goodsBom.setId(IdWorker.getId());
                apsSchedulingGoodsBomList.add(goodsBom);
              });
            }
          });
        }
      });
    });
    return runnableList;
  }

  private static void setCapacityWrapper(Boolean bool, LambdaQueryWrapper<? extends BaseEntity> wrapper) {
    if (FALSE.equals(bool)) {
      wrapper.eq(BaseEntity::getId, Long.MIN_VALUE);
    }
  }

  public @Override ApsSchedulingVersionQueryListRes queryList(ApsSchedulingVersionQueryListReq req) {

    MPJLambdaWrapper<ApsSchedulingVersion> q = getWrapper(req.getData());
    List<ApsSchedulingVersion> list = this.list(q);

    List<ApsSchedulingVersionDto> dataList = list.stream().map(t -> $.copy(t, ApsSchedulingVersionDto.class)).collect(Collectors.toList());
    //  this.setName(dataList);
    ((ApsSchedulingVersionServiceImpl) AopContext.currentProxy()).setName(dataList);

    return new ApsSchedulingVersionQueryListRes().setDataList(dataList);
  }

  public @Override DynamicsPage<ApsSchedulingVersionExportQueryPageListInfoRes> queryPageList(ApsSchedulingVersionExportQueryPageListReq req) {

    DynamicsPage<ApsSchedulingVersion> page = new DynamicsPage<>();
    page.setCurrent(req.getPageNum()).setSize(req.getPageSize());
    setQueryListHeader(page);
    MPJLambdaWrapper<ApsSchedulingVersion> q = getWrapper(req.getData());
    List<ApsSchedulingVersionExportQueryPageListInfoRes> records;
    if (Boolean.TRUE.equals(req.getQueryPage())) {
      IPage<ApsSchedulingVersion> list = this.page(page, q);
      IPage<ApsSchedulingVersionExportQueryPageListInfoRes> dataList = list.convert(t -> $.copy(t, ApsSchedulingVersionExportQueryPageListInfoRes.class));
      records = dataList.getRecords();
    } else {
      records = $.copyList(this.list(q), ApsSchedulingVersionExportQueryPageListInfoRes.class);
    }

    // 类型转换，  更换枚举 等操作

    List<ApsSchedulingVersionExportQueryPageListInfoRes> listInfoRes = $.copyList(records, ApsSchedulingVersionExportQueryPageListInfoRes.class);
    ((ApsSchedulingVersionServiceImpl) AopContext.currentProxy()).setName(listInfoRes);
    return DynamicsPage.init(page, listInfoRes);
  }

  @Override
  @Transactional
  public ApsSchedulingVersionUseConstraintsRes useConstraints(ApsSchedulingVersionUseConstraintsReq req) {
    ApsSchedulingVersion schedulingVersion = this.getById(req.getId());
    $.requireNonNullCanIgnoreException(schedulingVersion, "排产版本为空");
    try {
      ApsSchedulingConstraints schedulingConstraints = this.apsSchedulingConstraintsService.getById(schedulingVersion.getSchedulingConstraintsId());
      List<ConstrainedContent> constrainedList = JSON.readList(schedulingConstraints.getConstraintsContext(), ConstrainedContent.class);

      // 获取数据
      List<ApsOrder> orderList = apsOrderService.list(new LambdaQueryWrapper<ApsOrder>()
          .in(ApsOrder::getOrderStatus, Stream.of(ApsOrderStatusEnum.INIT).map(ApsOrderStatusEnum::getCode).toList()
          ));
      Map<Long, ApsSaleConfig> saleConfigMap = new HashMap<>();
      Map<Long, List<ApsOrderGoodsSaleConfig>> saleMap = new HashMap<>();

      List<Long> orderIdList = orderList.stream().map(BaseEntity::getId).toList();
      $.requireNonNullCanIgnoreException(orderIdList, "订单为空");
      if (hasSale(constrainedList, SALE)) {
        saleConfigMap.putAll(apsSaleConfigService.list().stream().collect(Collectors.toMap(BaseEntity::getId, Function.identity())));
        saleMap.putAll(apsOrderGoodsSaleConfigService.list(
                new LambdaQueryWrapper<ApsOrderGoodsSaleConfig>().in(ApsOrderGoodsSaleConfig::getOrderId, orderIdList)).stream()
            .collect(Collectors.groupingBy(ApsOrderGoodsSaleConfig::getOrderId)));
      }

      Map<Long, String> orderIdNoMap = orderList.stream().collect(Collectors.toMap(BaseEntity::getId, ApsOrder::getOrderNo));

      List<ApsOrderGoods> goodsList = this.apsOrderGoodsService.list(
          new LambdaQueryWrapper<ApsOrderGoods>().in(ApsOrderGoods::getOrderId, orderIdList));
      Map<Long, ApsOrder> oMap = orderList.stream().collect(Collectors.toMap(BaseEntity::getId, Function.identity()));
      // 分组排序
      List<Map<String, Object>> orgList = goodsList.stream().map(t -> {
        Map<String, Object> map = new HashMap<>();
        map.putAll(BeanUtil.beanToMap(t));
        map.putAll(BeanUtil.beanToMap(oMap.get(t.getOrderId())));
        map.put(ApsStr.FACTORY_ID, t.getFactoryId());
        map.put(ApsStr.ORDER_NO, orderIdNoMap.get(t.getOrderId()));
        saleMap.getOrDefault(t.getOrderId(), List.of()).forEach(s -> {
          ApsSaleConfig apsSaleConfig = saleConfigMap.get(s.getConfigId());
          map.put("sale_" + apsSaleConfig.getParentId(), apsSaleConfig.getId());
          map.put("sale_" + apsSaleConfig.getParentId() + "_show", apsSaleConfig.getSaleName());
        });

        return map;
      }).collect(Collectors.toList());
      ConstrainedResult result = ConstrainedContentUtils.constrained(orgList, constrainedList);
      Map<String, String> headerNameMap = apsSchedulingConstraintsService.getUseField().getValues().stream()
          .collect(Collectors.toMap(FieldConfig::getFieldName, FieldConfig::getShowName));
      result.getHeaderList().forEach(t -> t.setShowName(headerNameMap.get(t.getFieldName())));
      result.getHeaderList().stream().filter(t -> t.getFieldName().startsWith(SALE)).forEach(t -> t.setFieldName(t.getFieldName() + ("_show")));
      // 删除之前数据
      apsSchedulingVersionItemService.remove(new LambdaQueryWrapper<ApsSchedulingVersionItem>().eq(ApsSchedulingVersionItem::getSchedulingVersionId, req.getId()));

      // 保存
      this.update(new LambdaUpdateWrapper<ApsSchedulingVersion>().set(ApsSchedulingVersion::getVersionStep, 30)
          .set(ApsSchedulingVersion::getHeaderList, toJSONString(result.getHeaderList())).eq(BaseEntity::getId, req.getId()));

      List<ApsSchedulingVersionItem> itemList = result.getDataList().stream().map(m -> {
        ApsSchedulingVersionItem item = new ApsSchedulingVersionItem().setSchedulingVersionId(req.getId());
        item.setId(IdWorker.getId());
        item.setFactoryId((Long) m.get(ApsStr.FACTORY_ID));
        item.setGoodsId((Long) m.get(ApsStr.GOODS_ID));
        item.setOrderNo((String) m.get(ApsStr.ORDER_NO));
        item.setNumberIndex((Long) m.get("numberIndex"));
        item.setOrderId((Long) m.get("id"));
        IntStream.range(0, Math.min(result.getHeaderList().size(), 20)).forEach(i -> {
          Header header = result.getHeaderList().get(i);
          Object o = m.get(header.getFieldName());
          if (Objects.nonNull(o)) {
            ReflectUtil.setFieldValue(item, "field" + i, value2Str(o));
          }
        });
        return item;
      }).toList();
      this.apsSchedulingVersionItemService.saveBatch(itemList);
    } catch (Exception e) {
      this.update(new LambdaUpdateWrapper<ApsSchedulingVersion>().set(ApsSchedulingVersion::getVersionStep, 25).set(ApsSchedulingVersion::getVersionStepError, e.getMessage())
          .eq(BaseEntity::getId, req.getId()));
      log.error("排产约束使用失败  id: {} error:{}", req.getId(), e.getMessage(), e);
    }
    return new ApsSchedulingVersionUseConstraintsRes();
  }

  private boolean hasSale(List<ConstrainedContent> contentList, String key) {
    if (CollUtil.isEmpty(contentList)) {
      return false;
    }
    return contentList.stream().anyMatch(t -> hasSale(t, key));
  }

  private boolean hasSale(ConstrainedContent constrainedContent, String key) {
    boolean b = constrainedContent.getFilterList().stream().anyMatch(t -> t.getFieldName().startsWith(key));
    if (b) {
      return true;
    }
    List<ConstrainedContent> children = constrainedContent.getChildren();
    if (CollUtil.isNotEmpty(children)) {
      return false;
    }
    for (ConstrainedContent child : children) {
      if (hasSale(child, key)) {
        return true;
      }
    }
    return false;
  }

  @Override
  @Transactional
  public ApsSchedulingVersionUseMakeCapacityRes useMakeCapacity(ApsSchedulingVersionUseMakeCapacityReq req) {
    ApsSchedulingVersion schedulingVersion = this.getById(req.getId());
    $.requireNonNullCanIgnoreException(schedulingVersion, "排产版本为空");
//    $.assertTrueCanIgnoreException(schedulingVersion.getVersionStep() == 30, "排产版本状态错误");
    List<ApsSchedulingVersionItem> schedulingList = this.apsSchedulingVersionItemService.list(
        new LambdaQueryWrapper<ApsSchedulingVersionItem>().eq(ApsSchedulingVersionItem::getSchedulingVersionId, req.getId()));
    //

    List<Long> orderIdList = schedulingList.stream().map(ApsSchedulingVersionItem::getOrderId).toList();

    LocalDate nowDate = schedulingVersion.getStartDate();
    LocalDate lastDate = nowDate.plusDays(schedulingVersion.getSchedulingDayCount());

    List<LocalDate> localDateBetween = DateUtils.getLocalDateBetween(nowDate, lastDate);

    List<Long> factoryIdList = schedulingList.stream().map(ApsSchedulingVersionItem::getFactoryId).distinct().toList();
    $.requireNonNullCanIgnoreException(factoryIdList, "排产工厂为空");
    List<Factory> facortyList = factoryService.listByIds(factoryIdList);

    Map<Long, ApsGoods> goodsMap = apsGoodsService.list().stream().collect(Collectors.toMap(BaseEntity::getId, Function.identity()));

    Map<Long, List<WeekInfo>> factoryWeekListMap = Maps.newHashMap();
    Map<Long, Long> dayWorkSecondMap = Maps.newHashMap();
    Map<Long, ApsProcessPathDto> apsProcessPathDtoMap = Maps.newHashMap();
    facortyList.forEach(f -> {

      FactoryConfigRes factoryConfig = apsFactoryService.getFactoryConfig(
          new FactoryConfigReq().setFactoryId(f.getId()).setGetShift(Boolean.TRUE).setGetWeek(Boolean.TRUE).setGetPath(Boolean.TRUE).setWeekBeginDate(nowDate)
              .setWeekEndDate(lastDate.plusDays(schedulingVersion.getSchedulingDayCount())));
      log.info("add factory configuration {} name:{}", f.getId(), f.getFactoryName());
      factoryWeekListMap.put(f.getId(), factoryConfig.getWeekList());
      Long dayWorkSecond = factoryConfig.getDayWorkSecond();
      dayWorkSecondMap.put(f.getId(), dayWorkSecond);
      Map<Long, ApsProcessPathDto> pathDtoMap = factoryConfig.getPathDtoMap();
      log.info("Process {}: {}", f.getId(), toJSONString(pathDtoMap));
      apsProcessPathDtoMap.putAll(pathDtoMap);
    });

    List<List<Integer>> ymList = localDateBetween.stream().map(t -> List.of(t.getYear(), t.getMonthValue())).distinct().toList();

    LambdaQueryWrapper<ApsMakeCapacityFactory> factoryUpdateWrapper = new LambdaQueryWrapper<>();
    LambdaQueryWrapper<ApsMakeCapacitySaleConfig> configLambdaQueryWrapper = new LambdaQueryWrapper<>();
    LambdaQueryWrapper<CalendarDay> calendarDayLambdaQueryWrapper = new LambdaQueryWrapper<>();
    LambdaQueryWrapper<ApsMakeCapacityGoods> apsMakeCapacityGoodsWrapper = new LambdaQueryWrapper<>();
    setCapacityWrapper(schedulingVersion.getUseFactoryMakeCapacity(), factoryUpdateWrapper);
    setCapacityWrapper(schedulingVersion.getUseGoodsMakeCapacity(), apsMakeCapacityGoodsWrapper);
    setCapacityWrapper(schedulingVersion.getUseSaleConfigMakeCapacity(), configLambdaQueryWrapper);
    // 工程配置 TODO： 待实现

    ymList.forEach(t -> {
      factoryUpdateWrapper.or(r -> r.eq(ApsMakeCapacityFactory::getYear, t.getFirst()).eq(ApsMakeCapacityFactory::getMonth, t.get(1)));
      configLambdaQueryWrapper.or(r -> r.eq(ApsMakeCapacitySaleConfig::getYear, t.getFirst()).eq(ApsMakeCapacitySaleConfig::getMonth, t.get(1)));
      calendarDayLambdaQueryWrapper.or(r -> r.eq(CalendarDay::getDayYear, t.getFirst()).eq(CalendarDay::getDayMonth, t.get(1)));
      apsMakeCapacityGoodsWrapper.or(r -> r.eq(ApsMakeCapacityGoods::getYear, t.getFirst()).eq(ApsMakeCapacityGoods::getMonth, t.get(1)));
    });
    Map<String, CalendarDay> calendarDayMap = calendarDayService.list(calendarDayLambdaQueryWrapper).stream()
        .collect(Collectors.toMap(t -> t.getDayYear() + "-" + t.getDayMonth() + "-" + t.getFactoryId(), Function.identity()));
    Map<String, ApsMakeCapacityFactory> makeCapacityFactoryMap = this.apsMakeCapacityFactoryService.list(factoryUpdateWrapper).stream()
        .collect(Collectors.toMap(t -> t.getYear() + "-" + t.getMonth(), Function.identity(), (a, b) -> a));

    Map<String, List<ApsMakeCapacitySaleConfig>> makeCapacitySaleConfigMap = this.apsMakeCapacitySaleConfigService.list(configLambdaQueryWrapper).stream()
        .collect(Collectors.groupingBy(t -> t.getYear() + "-" + t.getMonth()));

    List<ApsMakeCapacityGoods> makeCapacityGoodsList = this.apsMakeCapacityGoodsService.list(apsMakeCapacityGoodsWrapper);

    Map<String, List<ApsMakeCapacityGoods>> makeCapacityGoodsMap = makeCapacityGoodsList.stream().collect(Collectors.groupingBy(t -> t.getYear() + "-" + t.getMonth()));

    List<Limit> limitList = new ArrayList<>();
    Map<Long, String> fnMap = this.factoryService.listByIds(makeCapacityFactoryMap.values().stream().map(ApsMakeCapacityFactory::getFactoryId).collect(Collectors.toSet())).stream()
        .collect(Collectors.toMap(Factory::getId, Factory::getFactoryName));
    Map<Long, String> gnMap = CollUtil.isEmpty(makeCapacityGoodsList) ? Map.of()
        : this.apsGoodsService.listByIds(makeCapacityGoodsList.stream().map(ApsMakeCapacityGoods::getGoodsId).collect(Collectors.toSet())).stream()
        .collect(Collectors.toMap(ApsGoods::getId, ApsGoods::getGoodsName));
    Map<Long, List<ApsOrderGoodsSaleConfig>> orderSaleListMap = new HashMap<>();
    Map<Long, ApsSaleConfig> apsSaleConfigMap = new HashMap<>();

    if (CollUtil.isNotEmpty(makeCapacitySaleConfigMap)) {
      apsSaleConfigMap.putAll(this.apsSaleConfigService.list().stream().collect(Collectors.toMap(BaseEntity::getId, Function.identity())));
      orderSaleListMap.putAll(this.apsOrderGoodsSaleConfigService.list(new LambdaQueryWrapper<ApsOrderGoodsSaleConfig>().in(ApsOrderGoodsSaleConfig::getOrderId, orderIdList)).stream().collect(Collectors.groupingBy(ApsOrderGoodsSaleConfig::getOrderId)));
    }

    localDateBetween.forEach(ym -> {
      // 移除非工作日
      String currentDate = ym.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
      String ymKey = ym.getYear() + "-" + ym.getMonthValue();
      ApsMakeCapacityFactory apsMakeCapacityFactory = makeCapacityFactoryMap.get(ymKey);
      if (Objects.nonNull(apsMakeCapacityFactory)) {
        CalendarDay calendarDay = calendarDayMap.get(ymKey + "-" + apsMakeCapacityFactory.getFactoryId());
        if (Objects.isNull(calendarDay)) {
          log.info("工厂Id: {}  {} 日期为空，移除", fnMap.get(apsMakeCapacityFactory.getFactoryId()), currentDate);
          return;
        }
        Field field = getField(calendarDay, "day" + ym.getDayOfMonth());
        Object value = FieldUtils.getFieldValue(calendarDay, field);
        if (FALSE.equals(value)) {
          log.info("工厂ID: {}  {} 非工作日，移除", fnMap.get(apsMakeCapacityFactory.getFactoryId()), currentDate);
          return;
        }
        Integer min = (Integer) FieldUtils.getFieldValue(apsMakeCapacityFactory, getField(apsMakeCapacityFactory, "dayMin" + ym.getDayOfMonth()));
        Integer max = (Integer) FieldUtils.getFieldValue(apsMakeCapacityFactory, getField(apsMakeCapacityFactory, "dayMax" + ym.getDayOfMonth()));
        Limit limit = new Limit().setCurrentDate(currentDate).setMin(min).setMax(max).setCurrentCount(0).setFieldValue(value2Str(apsMakeCapacityFactory.getFactoryId()))
            .setFieldValue(value2Str(apsMakeCapacityFactory.getFactoryId())).setFieldName(ApsStr.FACTORY_ID).setShowName(fnMap.get(apsMakeCapacityFactory.getFactoryId()))
            .setLimitTypeEnum(LimitTypeEnum.FACTORY_LIMIT);
        limitList.add(limit);
      } else {
        log.info("工厂: {}  {} 空节点", "工厂", currentDate);
        return;
      }
      List<ApsMakeCapacityGoods> apsMakeCapacityGoodsList = makeCapacityGoodsMap.get(ymKey);
      if (CollUtil.isNotEmpty(apsMakeCapacityGoodsList)) {
        for (ApsMakeCapacityGoods apsMakeCapacityGoods : apsMakeCapacityGoodsList) {
          if (Objects.nonNull(apsMakeCapacityGoods)) {

            Field minField = getField(apsMakeCapacityGoods, "dayMin" + ym.getDayOfMonth());
            Field maxField = getField(apsMakeCapacityGoods, "dayMax" + ym.getDayOfMonth());
            Integer min = (Integer) FieldUtils.getFieldValue(apsMakeCapacityGoods, minField);
            Integer max = (Integer) FieldUtils.getFieldValue(apsMakeCapacityGoods, maxField);
            Limit limit = new Limit().setCurrentDate(currentDate).setMin(min).setMax(max).setCurrentCount(0).setFieldValue(value2Str(apsMakeCapacityGoods.getGoodsId()))
                .setFieldName(ApsStr.GOODS_ID).setShowName(gnMap.get(apsMakeCapacityGoods.getGoodsId())).setLimitTypeEnum(LimitTypeEnum.GOODS_LIMIT);
            limitList.add(limit);
          }
        }
      }

      List<ApsMakeCapacitySaleConfig> apsMakeCapacitySaleConfigList = makeCapacitySaleConfigMap.get(ymKey);
      if (CollUtil.isNotEmpty(apsMakeCapacitySaleConfigList)) {
        apsMakeCapacitySaleConfigList.forEach(apsMakeCapacitySaleConfig -> {
          ApsSaleConfig apsSaleConfig = apsSaleConfigMap.get(apsMakeCapacitySaleConfig.getSaleConfigId());
          if (Objects.isNull(apsSaleConfig)) {
            return;
          }
          ApsSaleConfig parentSaleConfig = apsSaleConfigMap.get(apsSaleConfig.getParentId());

          Field minField = getField(apsMakeCapacitySaleConfig, "dayMin" + ym.getDayOfMonth());
          Field maxField = getField(apsMakeCapacitySaleConfig, "dayMax" + ym.getDayOfMonth());
          Integer min = (Integer) FieldUtils.getFieldValue(apsMakeCapacitySaleConfig, minField);
          Integer max = (Integer) FieldUtils.getFieldValue(apsMakeCapacitySaleConfig, maxField);
          Limit limit = new Limit().setCurrentDate(currentDate).setMin(min).setMax(max).setCurrentCount(0).setFieldValue(value2Str(apsMakeCapacitySaleConfig.getSaleConfigId()))
              .setFieldName(SALE + apsSaleConfig.getParentId()).setShowName(parentSaleConfig.getSaleName() + "_" + apsSaleConfig.getSaleName()).setLimitTypeEnum(SALE_CONFIG_LIMIT);
          limitList.add(limit);
        });
      }
    });
    List<Limit> saleLimit = limitList.stream().filter(t -> SALE_CONFIG_LIMIT.equals(t.getLimitTypeEnum())).toList();
    List<Map<String, Object>> needCapcaitylist = schedulingList.stream().map(item -> {
      Map<String, Object> map = BeanUtil.beanToMap(item);
      map.put("factoryId_show", fnMap.get(item.getFactoryId()));
      map.put("goodsId_show", gnMap.get(item.getGoodsId()));
      if (CollUtil.isNotEmpty(saleLimit)) {
        List<ApsOrderGoodsSaleConfig> goodsSaleConfigList = orderSaleListMap.get(item.getOrderId());
        goodsSaleConfigList.forEach(t -> {
          ApsSaleConfig apsSaleConfig = apsSaleConfigMap.get(t.getConfigId());
          map.put(SALE + apsSaleConfig.getParentId(), apsSaleConfig.getId());
          map.put(SALE + apsSaleConfig.getParentId() + "_show", apsSaleConfig.getSaleName());
        });

      }
      return map;
    }).collect(Collectors.toList());

    MakeCapacityResult capacityResult = MakeCapacityUtils.capacity(needCapcaitylist, limitList);

    final List<Limit> limitListFinal = limitList.stream().collect(Collectors.toMap(Limit::getFieldName, f -> f, (a, b) -> a)).values().stream().limit(20).toList();

    List<ApsSchedulingVersionCapacity> apsSchedulingVersionCapacityList = new ArrayList<>();
    AtomicLong index = new AtomicLong(1);
    List<ApsSchedulingVersionLimit> insertLimit = new ArrayList<>();
    List<ApsSchedulingVersionDay> versionDayArrayList = new ArrayList<>();
    capacityResult.getData().forEach(info -> {
      List<Map<String, Object>> mapList = info.getMapList();
      info.getLimitList().forEach(limit -> {
        insertLimit.add(new ApsSchedulingVersionLimit().setVersionId(req.getId()).setMax(limit.getMax()).setCurrentCount(limit.getCurrentCount()).setMin(limit.getMin())
            .setCurrentDay(info.getCurrentDate()).setFieldValue(limit.getFieldValue()).setFieldName(limit.getFieldName()).setShowName(limit.getShowName())
            .setLimitType(limit.getLimitTypeEnum().getName()));
      });
      versionDayArrayList.add(new ApsSchedulingVersionDay().setVersionId(req.getId()).setCurrentDay(info.getCurrentDate())
          .setHasEnough(info.getLimitList().stream().noneMatch(t -> t.getCurrentCount() < t.getMin())));
      mapList.forEach(map -> {
        ApsSchedulingVersionCapacity apsSchedulingVersionCapacity = new ApsSchedulingVersionCapacity();
        apsSchedulingVersionCapacity.setSchedulingVersionId(req.getId()).setCurrentDay(info.getCurrentDate()).setOrderNo((String) map.get(ApsStr.ORDER_NO));
        apsSchedulingVersionCapacity.setId(IdWorker.getId());
        apsSchedulingVersionCapacity.setGoodsId((Long) map.get(ApsStr.GOODS_ID)).setOrderId((Long) map.get(ApsStr.ORDER_ID)).setFactoryId((Long) map.get(ApsStr.FACTORY_ID))
            .setGoodsStatusId((Long) map.get(GOODS_STATUS_ID));
        apsSchedulingVersionCapacity.setNumberIndex(index.getAndIncrement());
        IntStream.range(0, limitListFinal.size()).forEach(l -> {
          Limit limit = limitListFinal.get(l);
          ReflectUtil.setFieldValue(apsSchedulingVersionCapacity, "field" + l, value2Str(map.get(limit.getFieldName() + "_show")));
        });
        apsSchedulingVersionCapacityList.add(apsSchedulingVersionCapacity);
      });
    });
    limitListFinal.stream().filter(t -> t.getLimitTypeEnum().equals(SALE_CONFIG_LIMIT)).forEach(t -> t.setShowName(t.getShowName().split("_")[1]));

    // 增加 aps_order_goods_bom, 创建订单时增加bom录入
    //apsSchedulingVersionCapacity
    // 获取每个订单的bom 清单
    // 获取商品工艺路径
    // 根据工艺路径, 推算零件使用
    // 保存零件使用
    // 对比零件库存, 生成差异
    // 基于库存,生成采购计划

    List<ApsSchedulingGoodsBom> apsSchedulingGoodsBomList = Collections.synchronizedList(new ArrayList<>());
    List<ApsSchedulingGoodsStatusDate> apsOrderGoodsStatusDateList = Collections.synchronizedList(new ArrayList<>());

    Map<Long, Map<Long, List<ApsGoodsBom>>> goodsBomMap = apsGoodsBomService.list().stream()
        .collect(Collectors.groupingBy(ApsGoodsBom::getGoodsId, Collectors.groupingBy(ApsGoodsBom::getBomUseWorkStation)));
    if (CollUtil.isNotEmpty(apsSchedulingVersionCapacityList)) {

      List<Runnable> runnableList = getBomRunList(schedulingVersion, apsSchedulingVersionCapacityList, goodsMap, apsProcessPathDtoMap, factoryWeekListMap, dayWorkSecondMap,
          goodsBomMap, apsSchedulingGoodsBomList, apsOrderGoodsStatusDateList);

      Boolean schedulingBom = RunUtils.run("scheduling bom " + req.getId(), runnableList);
      $.assertTrueCanIgnoreException(schedulingBom, "零件计算失败");
    }

    this.update(new LambdaUpdateWrapper<ApsSchedulingVersion>().eq(BaseEntity::getId, req.getId()).set(ApsSchedulingVersion::getVersionStep, 40)
        .set(ApsSchedulingVersion::getCapacityDateList,
            toJSONString(insertLimit.stream().map(ApsSchedulingVersionLimit::getCurrentDay).distinct().sorted().collect(Collectors.toList())))
        .set(ApsSchedulingVersion::getCapacityHeaderList, toJSONString(limitListFinal)));

    apsSchedulingVersionDayService.remove(new LambdaQueryWrapper<ApsSchedulingVersionDay>().eq(ApsSchedulingVersionDay::getVersionId, req.getId()));
    apsSchedulingVersionDayService.saveBatch(versionDayArrayList);
    apsSchedulingVersionLimitService.remove(new LambdaQueryWrapper<ApsSchedulingVersionLimit>().eq(ApsSchedulingVersionLimit::getVersionId, req.getId()));
    apsSchedulingVersionLimitService.saveBatch(insertLimit);
    apsSchedulingVersionCapacityService.remove(new LambdaQueryWrapper<ApsSchedulingVersionCapacity>().eq(ApsSchedulingVersionCapacity::getSchedulingVersionId, req.getId()));
    apsSchedulingGoodsBomTotalService.remove(new LambdaQueryWrapper<ApsSchedulingGoodsBomTotal>().eq(ApsSchedulingGoodsBomTotal::getSchedulingId, req.getId()));
    apsSchedulingGoodsBomService.remove(new LambdaQueryWrapper<ApsSchedulingGoodsBom>().in(ApsSchedulingGoodsBom::getOrderId, orderIdList).eq(ApsSchedulingGoodsBom::getSchedulingId, req.getId()));
    if (CollUtil.isNotEmpty(apsSchedulingVersionCapacityList)) {
      this.apsSchedulingVersionCapacityService.saveBatch(apsSchedulingVersionCapacityList);
    }
    if (CollUtil.isNotEmpty(apsSchedulingGoodsBomList)) {
      apsSchedulingGoodsBomService.saveBatch(apsSchedulingGoodsBomList);
      Map<String, List<ApsSchedulingGoodsBom>> bomsTotalMap = apsSchedulingGoodsBomList.stream().collect(Collectors.groupingBy(t -> t.getBomId() + "" + t.getBomUseDate()));
      List<ApsSchedulingGoodsBomTotal> insertApsSchedulingGoodsBomList = new ArrayList<>();
      bomsTotalMap.values().forEach(bomList -> {
        ApsSchedulingGoodsBom apsSchedulingGoodsBom = bomList.getFirst();
        ApsSchedulingGoodsBomTotal bomTotal = $.copy(apsSchedulingGoodsBom, ApsSchedulingGoodsBomTotal.class);
        insertApsSchedulingGoodsBomList.add(bomTotal);
        IntStream.range(1, bomList.size()).forEach(i -> {
          ApsSchedulingGoodsBom tmp = bomList.get(i);
          bomTotal.setBomUsageAdd(tmp.getBomUsage()).setBomCostPriceAdd(tmp.getBomCostPrice());
        });
      });
      this.apsSchedulingGoodsBomTotalService.saveBatch(insertApsSchedulingGoodsBomList);
    }

    apsSchedulingGoodsStatusDateService.remove(new LambdaQueryWrapper<ApsSchedulingGoodsStatusDate>().eq(ApsSchedulingGoodsStatusDate::getSchedulingId, req.getId()));
    apsSchedulingGoodsStatusDateService.saveBatch(apsOrderGoodsStatusDateList);
//    updateApsOrderGoodsStatusDate(apsGoodsList, apsOrderGoodsStatusDateList);
    return new ApsSchedulingVersionUseMakeCapacityRes();
  }

  private void updateApsOrderGoodsStatusDate(Set<OrderGoods> orderGoodsSet, List<ApsOrderGoodsStatusDate> apsOrderGoodsStatusDateList) {
    if (CollUtil.isNotEmpty(orderGoodsSet)) {
      this.apsOrderGoodsStatusDateService.remove(new LambdaUpdateWrapper<ApsOrderGoodsStatusDate>().isNull(ApsOrderGoodsStatusDate::getActualMakeBeginTime).and(a -> {
        orderGoodsSet.forEach(o -> {
          a.or(t -> {
            t.eq(ApsOrderGoodsStatusDate::getOrderId, o.getOrderId()).eq(ApsOrderGoodsStatusDate::getGoodsId, o.getGoodsId());
          });
        });
      }));
    }
    this.apsOrderGoodsStatusDateService.saveBatch(apsOrderGoodsStatusDateList);
  }

  @Override
  public DynamicsPage<ApsSchedulingVersionUseConstraintsResultRes> useConstraintsResult(ApsSchedulingVersionUseConstraintsResultReq req) {
    ApsSchedulingVersion schedulingVersion = this.getById(req.getId());
    $.requireNonNullCanIgnoreException(schedulingVersion, "排产版本为空");
    Page<ApsSchedulingVersionItem> apsSchedulingVersionItemPage = this.apsSchedulingVersionItemService.page(new Page<>(req.getPageNum(), req.getPageSize()),
        new LambdaQueryWrapper<ApsSchedulingVersionItem>().eq(ApsSchedulingVersionItem::getSchedulingVersionId, req.getId())
            .eq(Objects.nonNull(req.getGoodsId()), ApsSchedulingVersionItem::getGoodsId, req.getGoodsId()));
    List<ApsSchedulingVersionItem> records = apsSchedulingVersionItemPage.getRecords();
    Map<Long, String> gnMap = new HashMap<>();
    Map<Long, String> unMap = new HashMap<>();
    Map<Long, ApsOrder> apsOrderMap = new HashMap<>();
    if (CollUtil.isNotEmpty(records)) {
      gnMap.putAll(this.apsGoodsService.listByIds(records.stream().map(ApsSchedulingVersionItem::getGoodsId).collect(Collectors.toSet())).stream()
          .collect(Collectors.toMap(BaseEntity::getId, ApsGoods::getGoodsName)));
      unMap.putAll(this.apsOrderUserService.list(
              new LambdaQueryWrapper<ApsOrderUser>().in(ApsOrderUser::getOrderId, records.stream().map(ApsSchedulingVersionItem::getOrderId).collect(Collectors.toSet()))).stream()
          .collect(Collectors.toMap(ApsOrderUser::getOrderId, ApsOrderUser::getUserName)));

      apsOrderMap.putAll(this.apsOrderService.listByIds(records.stream().map(ApsSchedulingVersionItem::getOrderId).collect(Collectors.toSet())).stream()
          .collect(Collectors.toMap(BaseEntity::getId, t -> t)));
    }

    List<ApsSchedulingVersionUseConstraintsResultRes> resList = records.stream().map(t -> {
      ApsSchedulingVersionUseConstraintsResultRes ret = new ApsSchedulingVersionUseConstraintsResultRes();
      ret.putAll(BeanUtil.beanToMap(t));
      ret.put("goodsName", gnMap.get(t.getGoodsId()));
      ret.put("userName", unMap.get(t.getOrderId()));
      ret.put(ApsStr.ORDER_NO, apsOrderMap.getOrDefault(t.getOrderId(), new ApsOrder()).getOrderNo());
      return ret;
    }).collect(Collectors.toList());

    return DynamicsPage.init(apsSchedulingVersionItemPage, getResultHeader(schedulingVersion.getHeaderList()), resList);

  }

  @Override
  @Transactional
  public DynamicsPage<ApsSchedulingVersionUseMakeCapacityResultRes> useMakeCapacityResult(ApsSchedulingVersionUseMakeCapacityResultReq req) {

    ApsSchedulingVersion schedulingVersion = this.getById(req.getId());
    $.requireNonNullCanIgnoreException(schedulingVersion, "排产版本为空");

    Page<ApsSchedulingVersionCapacity> capacityPage = this.apsSchedulingVersionCapacityService.page(new Page<>(req.getPageNum(), req.getPageSize()),
        new LambdaQueryWrapper<ApsSchedulingVersionCapacity>().eq(ApsSchedulingVersionCapacity::getSchedulingVersionId, req.getId())
            .in(CollUtil.isNotEmpty(req.getCurrentDate()), ApsSchedulingVersionCapacity::getCurrentDay, req.getCurrentDate()));

    List<ApsSchedulingVersionCapacity> records = capacityPage.getRecords();
    if (CollUtil.isEmpty(records)) {
      return new DynamicsPage<>();
    }

    Map<Long, String> gnMap = this.apsGoodsService.listByIds(records.stream().map(ApsSchedulingVersionCapacity::getGoodsId).collect(Collectors.toSet())).stream()
        .collect(Collectors.toMap(BaseEntity::getId, ApsGoods::getGoodsName));
    Map<Long, String> unMap = this.apsOrderUserService.list(
            new LambdaQueryWrapper<ApsOrderUser>().in(ApsOrderUser::getOrderId, records.stream().map(ApsSchedulingVersionCapacity::getOrderId).collect(Collectors.toSet()))).stream()
        .collect(Collectors.toMap(ApsOrderUser::getOrderId, ApsOrderUser::getUserName));

    Map<Long, ApsOrder> apsOrderMap = this.apsOrderService.listByIds(records.stream().map(ApsSchedulingVersionCapacity::getOrderId).collect(Collectors.toSet())).stream()
        .collect(Collectors.toMap(BaseEntity::getId, t -> t));
    Map<Long, ApsSchedulingVersionItem> versionItemMap = this.apsSchedulingVersionItemService.list(
            new LambdaQueryWrapper<ApsSchedulingVersionItem>().eq(ApsSchedulingVersionItem::getSchedulingVersionId, req.getId())
                .in(ApsSchedulingVersionItem::getOrderId, capacityPage.getRecords().stream().map(ApsSchedulingVersionCapacity::getOrderId).toList())).stream()
        .collect(Collectors.toMap(ApsSchedulingVersionItem::getOrderId, Function.identity()));

    List<ApsSchedulingVersionUseMakeCapacityResultRes> resList = records.stream().map(t -> {
      ApsSchedulingVersionUseMakeCapacityResultRes ret = new ApsSchedulingVersionUseMakeCapacityResultRes();
      ret.putAll(BeanUtil.beanToMap(t));
      ret.put("goodsName", gnMap.get(t.getGoodsId()));
      ret.put("userName", unMap.get(t.getOrderId()));
      ret.put(ApsStr.ORDER_NO, apsOrderMap.getOrDefault(t.getOrderId(), new ApsOrder()).getOrderNo());
      ret.put("preNumberIndex", versionItemMap.getOrDefault(t.getOrderId(), new ApsSchedulingVersionItem()).getNumberIndex());
      return ret;
    }).collect(Collectors.toList());

    return DynamicsPage.init(capacityPage, getMakeResultHeader(schedulingVersion.getCapacityHeaderList()), resList);
  }

  @Transactional
  @Override
  public ApsSchedulingVersionFinishRes finish(ApsSchedulingVersionFinishReq req) {

    List<ApsOrderGoodsStatusDate> apsOrderGoodsStatusDateList = new ArrayList<>();
    Set<OrderGoods> orderGoodsSet = new HashSet<>();
    List<ApsSchedulingGoodsStatusDate> goodsStatusDateList = this.apsSchedulingGoodsStatusDateService.list(
        new LambdaQueryWrapper<ApsSchedulingGoodsStatusDate>().eq(ApsSchedulingGoodsStatusDate::getSchedulingId, req.getId()));
    if (CollUtil.isNotEmpty(goodsStatusDateList)) {
      goodsStatusDateList.forEach(t -> {
        orderGoodsSet.add(new OrderGoods().setOrderId(t.getOrderId()).setGoodsId(t.getGoodsId()));
        apsOrderGoodsStatusDateList.add($.copy(t, ApsOrderGoodsStatusDate.class));
      });
    }
    updateApsOrderGoodsStatusDate(orderGoodsSet, apsOrderGoodsStatusDateList);
    this.update(new LambdaUpdateWrapper<ApsSchedulingVersion>().eq(BaseEntity::getId, req.getId()).set(ApsSchedulingVersion::getVersionStep, 100));
    return new ApsSchedulingVersionFinishRes();
  }

  private List<Header> getResultHeader(String headerStr) {
    ArrayList<Header> headerList = new ArrayList<>();
    headerList.add(new Header().setFieldName(ApsStr.ORDER_NO).setShowName("订单号").setWidth(150));
    headerList.add(new Header().setFieldName("numberIndex").setShowName("预排序号").setWidth(80));
    headerList.add(new Header("goodsName", "商品名称", 100, ""));
    headerList.add(new Header("userName", "用户名", 100, ""));
    List<Header> headerListTmp = JSON.readList(headerStr, Header.class);
    IntStream.range(0, Math.min(20, CollUtil.isEmpty(headerListTmp) ? 0 : headerListTmp.size())).forEach(i -> {
      Header header = new Header("field" + i, headerListTmp.get(i).getShowName(), 160, "");
      headerList.add(header);
    });
    return headerList;
  }

  private List<Header> getMakeResultHeader(String headerStr) {
    ArrayList<Header> headerList = new ArrayList<>();
//    headerList.add(new Header().setFieldName("id").setShowName("序号").setWidth(200));
    headerList.add(new Header().setFieldName(ApsStr.ORDER_NO).setShowName("订单号").setWidth(150));
    headerList.add(new Header().setFieldName("preNumberIndex").setShowName("预排序号").setWidth(80));
    headerList.add(new Header().setFieldName("numberIndex").setShowName("产能序号").setWidth(80));
    headerList.add(new Header("goodsName", "商品名称", 100, ""));
    headerList.add(new Header("userName", "用户名", 100, ""));
    List<Header> headerListTmp = JSON.readList(headerStr, Header.class);
    IntStream.range(0, Math.min(20, CollUtil.isEmpty(headerListTmp) ? 0 : headerListTmp.size())).forEach(i -> {
      Header header = new Header("field" + i, headerListTmp.get(i).getShowName(), 160, "");
      headerList.add(header);
    });
    return headerList;
  }

  @SetUserName
  public @Override void setName(List<? extends ApsSchedulingVersionDto> apsSchedulingVersionDtoList) {

    setNameService.setName(apsSchedulingVersionDtoList,//
        SetNamePojoUtils.getSetNamePojo(ApsSchedulingConstraintsService.class, //
            "constraintsName", "schedulingConstraintsId", "schedulingConstraintsName"));

//    Map<Long, String> snMap = apsSchedulingConstraintsService.listByIds(
//            apsSchedulingVersionDtoList.stream().map(ApsSchedulingVersionDto::getSchedulingConstraintsId).collect(Collectors.toSet())).stream()
//        .collect(Collectors.toMap(BaseEntity::getId, ApsSchedulingConstraints::getConstraintsName));
////    Map<Long, List<ApsSchedulingVersionLimit>> limitMap = this.apsSchedulingVersionLimitService.list(
////        new LambdaQueryWrapper<ApsSchedulingVersionLimit>().in(ApsSchedulingVersionLimit::getVersionId, apsSchedulingVersionDtoList.stream().map(
////            BaseEntityDto::getId).toList())).stream().collect(Collectors.groupingBy(ApsSchedulingVersionLimit::getVersionId));
//    apsSchedulingVersionDtoList.forEach(t -> {
//      t.setSchedulingConstraintsName(snMap.get(t.getSchedulingConstraintsId()));
////      t.setLimitDtoList($.copyList(limitMap.getOrDefault(t.getId(), Collections.emptyList()), ApsSchedulingVersionLimitDto.class));
//    });
  }


  private MPJLambdaWrapper<ApsSchedulingVersion> getWrapper(ApsSchedulingVersionDto obj) {
    MPJLambdaWrapper<ApsSchedulingVersion> q = new MPJLambdaWrapper<>();

    if (Objects.nonNull(obj)) {
      q.eq(StringUtils.isNoneBlank(obj.getSchedulingVersionNo()), ApsSchedulingVersion::getSchedulingVersionNo, obj.getSchedulingVersionNo())
      ;
    }
    q.orderByDesc(ApsSchedulingVersion::getId);
    return q;

  }

  private void setQueryListHeader(DynamicsPage<?> page) {

    ServiceComment.header(page, "ApsSchedulingVersionService#queryPageList");

  }


}

