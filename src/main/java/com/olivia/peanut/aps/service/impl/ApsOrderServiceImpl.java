package com.olivia.peanut.aps.service.impl;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.date.DatePattern;
import cn.hutool.core.util.RandomUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.IdWorker;
import com.github.yulichang.base.MPJBaseServiceImpl;
import com.github.yulichang.wrapper.MPJLambdaWrapper;
import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.olivia.peanut.aps.api.entity.apsOrder.*;
import com.olivia.peanut.aps.api.entity.apsOrder.ApsOrderTimeLineRes.StatusInfo;
import com.olivia.peanut.aps.api.entity.apsOrderGoods.ApsOrderGoodsDto;
import com.olivia.peanut.aps.api.entity.apsOrderGoodsBom.ApsOrderGoodsBomDto;
import com.olivia.peanut.aps.api.entity.apsOrderGoodsProjectConfig.ApsOrderGoodsProjectConfigDto;
import com.olivia.peanut.aps.api.entity.apsOrderGoodsSaleConfig.ApsOrderGoodsSaleConfigDto;
import com.olivia.peanut.aps.api.entity.apsOrderUser.ApsOrderUserDto;
import com.olivia.peanut.aps.api.entity.apsProcessPath.ApsProcessPathDto;
import com.olivia.peanut.aps.api.entity.apsProjectConfig.ApsProjectConfigDto;
import com.olivia.peanut.aps.api.entity.apsProjectConfig.ApsProjectConfigExportQueryPageListInfoRes;
import com.olivia.peanut.aps.api.entity.apsProjectConfig.ApsProjectConfigExportQueryPageListReq;
import com.olivia.peanut.aps.api.entity.apsSaleConfig.ApsSaleConfigDto;
import com.olivia.peanut.aps.api.entity.apsSaleConfig.ApsSaleConfigExportQueryPageListInfoRes;
import com.olivia.peanut.aps.api.entity.apsSaleConfig.ApsSaleConfigExportQueryPageListReq;
import com.olivia.peanut.aps.mapper.ApsOrderMapper;
import com.olivia.peanut.aps.model.*;
import com.olivia.peanut.aps.service.*;
import com.olivia.peanut.aps.service.pojo.FactoryConfigReq;
import com.olivia.peanut.aps.service.pojo.FactoryConfigRes;
import com.olivia.peanut.aps.utils.model.ApsProcessPathInfo;
import com.olivia.peanut.aps.utils.model.ApsProcessPathInfo.Info;
import com.olivia.peanut.aps.utils.model.ApsProcessPathVo;
import com.olivia.peanut.aps.utils.process.ProcessUtils;
import com.olivia.peanut.portal.api.entity.BaseEntityDto;
import com.olivia.sdk.ann.SetUserName;
import com.olivia.sdk.comment.ServiceComment;
import com.olivia.sdk.config.PeanutProperties;
import com.olivia.sdk.utils.*;
import com.olivia.sdk.utils.model.UserInfo;
import jakarta.annotation.Resource;
import org.apache.commons.compress.utils.Lists;
import org.apache.commons.lang3.StringUtils;
import org.springframework.aop.framework.AopContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * (ApsOrder)表服务实现类
 *
 * @author peanut
 * @since 2024-04-09 13:19:36
 */
@Service("apsOrderService")
@Transactional
public class ApsOrderServiceImpl extends MPJBaseServiceImpl<ApsOrderMapper, ApsOrder> implements ApsOrderService {

  final static Cache<String, Map<String, String>> cache = CacheBuilder.newBuilder().maximumSize(100).expireAfterWrite(30, TimeUnit.MINUTES).build();

  @Resource
  ApsOrderGoodsService apsOrderGoodsService;
  @Resource
  ApsOrderGoodsProjectConfigService apsOrderGoodsProjectConfigService;
  @Resource
  ApsOrderUserService apsOrderUserService;
  @Resource
  ApsOrderGoodsSaleConfigService apsOrderGoodsSaleConfigService;
  @Resource
  ApsGoodsSaleItemService apsGoodsSaleItemService;
  @Resource
  ApsSaleConfigService apsSaleConfigService;
  @Resource
  ApsProjectConfigService apsProjectConfigService;
  @Resource
  ApsGoodsService apsGoodsService;
  @Resource
  ApsStatusService apsStatusService;
  @Resource
  ApsSchedulingGoodsStatusDateService apsSchedulingGoodsStatusDateService;
  @Resource
  @Lazy
  ApsOrderGoodsStatusDateService apsOrderGoodsStatusDateService;
  @Autowired
  ApsFactoryService apsFactoryService;
  @Resource
  PeanutProperties peanutProperties;
  @Resource
  ApsOrderGoodsBomService apsOrderGoodsBomService;
  @Resource
  ApsGoodsBomService apsGoodsBomService;

  public @Override ApsOrderQueryListRes queryList(ApsOrderQueryListReq req) {

    MPJLambdaWrapper<ApsOrder> q = getWrapper(req.getData());
    List<ApsOrder> list = this.list(q);

    List<ApsOrderDto> dataList = list.stream().map(t -> $.copy(t, ApsOrderDto.class)).collect(Collectors.toList());
    //  this.setName(dataList);
    ((ApsOrderServiceImpl) AopContext.currentProxy()).setName(dataList);

    return new ApsOrderQueryListRes().setDataList(dataList);
  }

  public @Override DynamicsPage<ApsOrderExportQueryPageListInfoRes> queryPageList(ApsOrderExportQueryPageListReq req) {

    DynamicsPage<ApsOrder> page = new DynamicsPage<>();
    page.setCurrent(req.getPageNum()).setSize(req.getPageSize());
    setQueryListHeader(page);
    MPJLambdaWrapper<ApsOrder> q = getWrapper(req.getData());
    List<ApsOrderExportQueryPageListInfoRes> records;
    if (Boolean.TRUE.equals(req.getQueryPage())) {
      IPage<ApsOrder> list = this.page(page, q);
      IPage<ApsOrderExportQueryPageListInfoRes> dataList = list.convert(t -> $.copy(t, ApsOrderExportQueryPageListInfoRes.class));
      records = dataList.getRecords();
    } else {
      records = $.copyList(this.list(q), ApsOrderExportQueryPageListInfoRes.class);
    }

    // 类型转换，  更换枚举 等操作

    List<ApsOrderExportQueryPageListInfoRes> listInfoRes = $.copyList(records, ApsOrderExportQueryPageListInfoRes.class);
    // this.setName(listInfoRes);
    ((ApsOrderServiceImpl) AopContext.currentProxy()).setName(listInfoRes);

    return DynamicsPage.init(page, listInfoRes);
  }

  @Override
  public ApsOrderInsertRes save(ApsOrderInsertReq req) {
    ApsOrder apsOrder = $.copy(req, ApsOrder.class);
    apsOrder.setOrderNo(IdUtils.getUniqueId());
    apsOrder.setId(IdWorker.getId());
    ApsStatus apsStatus = apsStatusService.getOne(new LambdaQueryWrapper<ApsStatus>().eq(ApsStatus::getOrderStatusId, ApsOrderStatusEnum.INIT.getCode()));
    $.requireNonNullCanIgnoreException(apsStatus, "订单初始化aps状态为空");
    this.save(apsOrder);
    List<ApsOrderGoods> goodsList = $.copyList(req.getGoodsList(), ApsOrderGoods.class);
    goodsList.forEach(t -> t.setOrderId(apsOrder.getId()).setApsStatusId(apsStatus.getId()));
    List<ApsOrderGoodsProjectConfig> projectConfigList = $.copyList(req.getGoodsProjectConfigList(), ApsOrderGoodsProjectConfig.class);
    projectConfigList.forEach(t -> t.setOrderId(apsOrder.getId()));
    List<ApsOrderGoodsSaleConfig> saleConfigList = $.copyList(req.getGoodsSaleConfigList(), ApsOrderGoodsSaleConfig.class);
    saleConfigList.forEach(t -> t.setOrderId(apsOrder.getId()).setFactoryId(req.getFactoryId()));
    ApsOrderUser orderUser = $.copy(req.getOrderUser(), ApsOrderUser.class);
    orderUser.setOrderId(apsOrder.getId());
    if (CollUtil.isNotEmpty(req.getApsOrderGoodsBomList())) {
      List<ApsOrderGoodsBom> orderGoodsBomList = this.apsGoodsBomService.lambdaQuery().//
          in(BaseEntity::getId, req.getApsOrderGoodsBomList().stream().map(ApsOrderGoodsBomDto::getGoodsBomId).toList()) //
          .list().stream().map(v -> $.copy(v, ApsOrderGoodsBom.class).setOrderId(apsOrder.getId())).peek(t -> t.setId(IdUtils.getId())).toList();
      this.apsOrderGoodsBomService.saveBatch(orderGoodsBomList);
    }
    this.apsOrderGoodsSaleConfigService.saveBatch(saleConfigList);
    this.apsOrderGoodsProjectConfigService.saveBatch(projectConfigList);
    this.apsOrderGoodsService.saveBatch(goodsList);
    this.apsOrderUserService.save(orderUser);
    return new ApsOrderInsertRes().setId(apsOrder.getId()).setCount(1);
  }

  @Override
  @Transactional
  public ApsOrderBatchInsertRes saveBatch(ApsOrderBatchInsertReq req) {

    List<Long> idList = new ArrayList<>();
    List<String> orderNoList = new ArrayList<>();
    List<ApsGoods> goodsList = this.apsGoodsService.list();
    List<ApsSaleConfigExportQueryPageListInfoRes> saleConfigList = apsSaleConfigService.queryPageList(new ApsSaleConfigExportQueryPageListReq().setQueryPage(false)).getDataList();
    List<ApsOrderGoodsSaleConfig> insertSaleConfigList = new ArrayList<>();
    List<ApsOrderGoods> apsOrderGoodList = new ArrayList<>();
    List<ApsOrder> apsOrderList = new ArrayList<>();
    List<ApsOrderUser> apsOrderUserList = new ArrayList<>();
    ApsStatus apsStatus = apsStatusService.getOne(new LambdaQueryWrapper<ApsStatus>().eq(ApsStatus::getOrderStatusId, ApsOrderStatusEnum.INIT.getCode()));
    $.requireNonNullCanIgnoreException(apsStatus, "订单初始化aps状态为空");
//    Long statusId = Objects.nonNull(apsStatus) ? apsStatus.getId() : null;
//           .stream().collect(Collectors.toMap(ApsGoodsSaleItemDto::getSaleConfigId))
    List<ApsProjectConfigExportQueryPageListInfoRes> projectConfigList = this.apsProjectConfigService.queryPageList(new ApsProjectConfigExportQueryPageListReq().setQueryPage(false)).getDataList();
    ArrayList<ApsOrderGoodsProjectConfig> projectConfigArrayList = new ArrayList<>();

    Map<Long, List<ApsGoodsBom>> goodsBomMap = this.apsGoodsBomService.list().stream().collect(Collectors.groupingBy(ApsGoodsBom::getGoodsId));
    List<ApsOrderGoodsBom> apsOrderGoodsBomList = new ArrayList<>();
    IntStream.range(1, req.getCreateCount() + 1).forEach(i -> {
      long totalPrice = i * RandomUtil.randomLong(1000, 2000);

      LocalDateTime dateTime = LocalDateTime.now().plus(Duration.ofDays(RandomUtil.randomInt(1, 50)));
      ApsOrder apsOrder = new ApsOrder().setOrderRemark("第" + i + "个订单").setOrderNo(IdUtils.getUniqueId()).setOrderNoParent("p_" + IdWorker.getId()).setReserveAmount(new BigDecimal(RandomUtil.randomLong(400000, 500000))).setReserveDatetime(LocalDateTime.now())
          .setMakeFinishDate(dateTime.toLocalDate()).setOrderTotalPrice(new BigDecimal(totalPrice)).setOrderStatus(ApsOrderStatusEnum.INIT);
      apsOrder.setFinishPayedAmount(new BigDecimal(0)).setFinishPayedDatetime(dateTime.plusDays(RandomUtil.randomInt(3, 30)));
      apsOrder.setDeliveryDate(dateTime.plusDays(RandomUtil.randomInt(30, 60)).toLocalDate());
      apsOrder.setId(IdWorker.getId());
      apsOrder.setOrderStatus(ApsOrderStatusEnum.INIT);
      apsOrderList.add(apsOrder);
      ApsGoods goods = goodsList.get(RandomUtil.randomInt(0, goodsList.size()));

      List<ApsGoodsBom> apsGoodsBomList = goodsBomMap.getOrDefault(goods.getId(), List.of());
      List<ApsOrderGoodsBom> apsOrderGoodsBomListTmp = apsGoodsBomList.stream().map(t -> $.copy(t, ApsOrderGoodsBom.class).setGoodsBomId(t.getBomId())).toList();// $.copyList(apsGoodsBomList, ApsOrderGoodsBom.class);

      apsOrderGoodsBomListTmp.forEach(t -> t.setOrderId(IdUtils.getId()).setOrderId(apsOrder.getId()).setBomUsage(BigDecimal.valueOf(RandomUtil.randomLong(1, 60))).setId(IdUtils.getId()));
      apsOrderGoodsBomList.addAll(apsOrderGoodsBomListTmp);


      ApsOrderGoods apsOrderGood = new ApsOrderGoods().setOrderId(apsOrder.getId())
          .setGoodsId(goods.getId()).setGoodsName(goods.getGoodsName()).setGoodsRemark(goods.getGoodsRemark())
          .setFactoryId(goods.getFactoryId()).setGoodsUnit("").setGoodsUnitPrice(new BigDecimal(RandomUtil.randomInt(100, 999)))
          .setApsStatusId(apsStatus.getId())
          .setGoodsTotalPrice(new BigDecimal(totalPrice)).setGoodsAmount(new BigDecimal(RandomUtil.randomInt(50000)));
      apsOrderGoodList.add(apsOrderGood);
      saleConfigList.forEach(saleCode -> {
        List<? extends ApsSaleConfigDto> saleItemDtoList = saleCode.getChildren();
        if (CollUtil.isEmpty(saleItemDtoList)) {
          return;
        }
        ApsSaleConfigDto goodsSaleItemDto = saleItemDtoList.get(RandomUtil.randomInt(0, saleItemDtoList.size()));
        insertSaleConfigList.add(new ApsOrderGoodsSaleConfig().setOrderId(apsOrder.getId()).setConfigId(goodsSaleItemDto.getId()).setFactoryId(goods.getFactoryId()).setGoodsId(goods.getId()));
      });

      UserInfo randomUser = RandomUserUtil.getRandomUser();
      ApsOrderUser orderUser = new ApsOrderUser().setOrderId(apsOrder.getId()).setUserName("用户-" + i).setUserAddress(randomUser.getAddress()).setUserPhone(randomUser.getPhone()).setUserSex(randomUser.getSex());
      apsOrderUserList.add(orderUser);
      orderNoList.add(apsOrder.getOrderNo());
      idList.add(apsOrder.getId());

      projectConfigList.forEach(t -> {
        List<? extends ApsProjectConfigDto> children = t.getChildren();
        if (CollUtil.isEmpty(children)) {
          return;
        }
        ApsProjectConfigDto apsProjectConfigDto = children.get(RandomUtil.randomInt(0, children.size()));
        projectConfigArrayList.add(new ApsOrderGoodsProjectConfig().setOrderId(apsOrder.getId()).setGoodsId(goods.getId()).setFactoryId(goods.getFactoryId()).setConfigId(apsProjectConfigDto.getId()));
      });

    });
    this.saveBatch(apsOrderList);
    this.apsOrderGoodsService.saveBatch(apsOrderGoodList);
    this.apsOrderGoodsSaleConfigService.saveBatch(insertSaleConfigList);
    this.apsOrderUserService.saveBatch(apsOrderUserList);
    this.apsOrderGoodsProjectConfigService.saveBatch(projectConfigArrayList);
    this.apsOrderGoodsBomService.saveBatch(apsOrderGoodsBomList);

    return new ApsOrderBatchInsertRes().setOrderNoList(orderNoList).setIdList(idList);
  }

  @Override
  public DynamicsPage<ApsOrderTimeLineRes> timeLine(ApsOrderTimeLineReq req) {

    ApsOrder apsOrder = this.getApsOrderByNo(req.getOrderNo());

    LocalDate now = LocalDate.now();
    List<Long> orderIdList = apsOrderGoodsStatusDateService.list(new QueryWrapper<ApsOrderGoodsStatusDate>().select(Str.DISTINCT + "order_id").lambda().eq(Objects.nonNull(apsOrder), ApsOrderGoodsStatusDate::getOrderId, Objects.nonNull(apsOrder) ? apsOrder.getId() : null).and(Boolean.TRUE.equals(req.getIsActualMakeTime()), r -> r.ge(ApsOrderGoodsStatusDate::getActualMakeBeginTime, req.getBeginDate()).le(ApsOrderGoodsStatusDate::getActualMakeEndTime, req.getEndDate())).and(!Boolean.TRUE.equals(req.getIsActualMakeTime()), r -> r.ge(ApsOrderGoodsStatusDate::getExpectMakeBeginTime, req.getBeginDate()).le(ApsOrderGoodsStatusDate::getExpectMakeEndTime, req.getEndDate()))).stream().map(ApsOrderGoodsStatusDate::getOrderId).toList();

    DynamicsPage<ApsOrderTimeLineRes> page = new DynamicsPage<>();
    page.setHeaderList(Lists.newArrayList()).setTotal(0);
    List<LocalDate> localDateList = DateUtils.getLocalDateBetween(req.getBeginDate(), req.getEndDate());
    localDateList.forEach(t -> page.addHeader(t.format(DatePattern.NORM_DATE_FORMATTER), t.format(DatePattern.NORM_DATE_FORMATTER), 100));
    if (CollUtil.isEmpty(orderIdList)) {
      return page;
    }
    int total = orderIdList.size();

    orderIdList = CollUtil.sub(orderIdList, (req.getPageNum() - 1) * req.getPageSize(), req.getPageNum() * req.getPageSize());
    if (CollUtil.isEmpty(orderIdList)) {
      return page;
    }

    Map<Long, List<ApsOrderGoodsStatusDate>> orderStatusMap = this.apsOrderGoodsStatusDateService.list(new LambdaQueryWrapper<ApsOrderGoodsStatusDate>().in(ApsOrderGoodsStatusDate::getOrderId, orderIdList)).stream().collect(Collectors.groupingBy(ApsOrderGoodsStatusDate::getOrderId));
    Map<Long, String> sNameMap = this.apsStatusService.list().stream().collect(Collectors.toMap(BaseEntity::getId, ApsStatus::getStatusName));
    List<ApsOrderTimeLineRes> list = $.copyList(this.listByIds(orderIdList), ApsOrderTimeLineRes.class);
    setName(list);
    if (CollUtil.isNotEmpty(list)) {
      list.forEach(t -> {
        t.setStatusInfoList(orderStatusMap.getOrDefault(t.getId(), List.of()).stream().map(s -> getStatusInfo(s, Boolean.TRUE.equals(req.getIsActualMakeTime()), now).setStatusName(sNameMap.get(s.getGoodsStatusId()))).collect(Collectors.toList()));
      });
    }
    page.setTotal(total).setRecords(list);
    return page;
  }

  private StatusInfo getStatusInfo(ApsOrderGoodsStatusDate statusDate, Boolean isActualMakeTime, LocalDate now) {
    StatusInfo statusInfo = new StatusInfo().setStatusId(statusDate.getGoodsStatusId())
        //.setBeginDate(statusDate.getExpectMakeBeginTime())
//        .setEndDate(statusDate.getExpectMakeEndTime())
        .setExpectMakeEndTime(statusDate.getExpectMakeEndTime()).setExpectMakeBeginTime(statusDate.getExpectMakeBeginTime()).setActualMakeBeginTime(statusDate.getActualMakeBeginTime()).setActualMakeEndTime(statusDate.getActualMakeEndTime());
    if (Boolean.TRUE.equals(isActualMakeTime)) {
      statusInfo.setBeginDate(statusDate.getActualMakeBeginTime().toLocalDate());
    } else {
      statusInfo.setBeginDate(statusDate.getExpectMakeBeginTime());
    }
    // 是否延期 ,实际实际大于预计时间
    if (Objects.nonNull(statusInfo.getActualMakeBeginTime()) && Objects.nonNull(statusInfo.getExpectMakeBeginTime())) {
      statusInfo.setIsDelay(statusInfo.getActualMakeBeginTime().toLocalDate().isAfter(statusInfo.getExpectMakeBeginTime()));
    }
    if (Objects.nonNull(statusInfo.getExpectMakeBeginTime()) && Objects.isNull(statusInfo.getActualMakeBeginTime())) {
      statusInfo.setIsDelay(now.isAfter(statusInfo.getExpectMakeBeginTime()));
    }
    return statusInfo;
  }

  @Override
  @Transactional
  public ApsOrderUpdateOrderStatusRes updateOrderStatus(ApsOrderUpdateOrderStatusReq req) {
    ApsOrder apsOrder = this.getById(req.getOrderId());
    $.requireNonNullCanIgnoreException(apsOrder, "订单不存在");
    ApsStatus apsStatus = this.apsStatusService.getById(req.getGoodsStatusId());
    $.requireNonNullCanIgnoreException(apsStatus, "状态不存在");
    List<ApsOrderGoods> apsOrderGoods = apsOrderGoodsService.getApsOrderGoodsByOrderId(apsOrder.getId());
    $.requireNonNullCanIgnoreException(apsOrderGoods, "订单商品不存在");
    ApsOrderGoods orderGoods = apsOrderGoods.getFirst();
    ApsGoods apsGoods = apsGoodsService.getById(orderGoods.getGoodsId());

    LocalDate now = LocalDate.now();
    Long factoryId = orderGoods.getFactoryId();
    FactoryConfigRes factoryConfig = this.apsFactoryService.getFactoryConfig(new FactoryConfigReq().setFactoryId(factoryId).setGetPath(Boolean.TRUE).setWeekBeginDate(now).setWeekEndDate(now.plusDays(peanutProperties.getOrderStatusUpdateNeedDayCount())).setGetWeek(Boolean.TRUE).setGetShift(Boolean.TRUE).setNowDateTime(LocalDateTime.now()));
    Long dayWorkSecond = factoryConfig.getDayWorkSecond();
    Long dayWorkLastSecond = factoryConfig.getDayWorkLastSecond();
    ApsProcessPathDto apsProcessPathDto = factoryConfig.getPathDtoMap().get(apsGoods.getProcessPathId());
    ApsProcessPathInfo apsProcessPathInfo = ProcessUtils.schedulePathDate($.copy(apsProcessPathDto, ApsProcessPathVo.class), factoryConfig.getWeekList(), dayWorkLastSecond, dayWorkSecond, req.getGoodsStatusId(), Map.of(), LocalDate.now());
    List<Info> dataList = apsProcessPathInfo.getDataList();

    Map<Long, ApsOrderGoodsStatusDate> statusDateMap = this.apsOrderGoodsStatusDateService.listByOrderIdGoodsId(orderGoods.getOrderId(), apsGoods.getId()).stream().collect(Collectors.toMap(ApsOrderGoodsStatusDate::getGoodsStatusId, Function.identity()));

    List<ApsOrderGoodsStatusDate> updateList = new ArrayList<>();
    List<ApsOrderGoodsStatusDate> insertList = new ArrayList<>();
    dataList.forEach(t -> {
      ApsOrderGoodsStatusDate apsOrderGoodsStatusDate = statusDateMap.get(t.getStatusId());
      if (Objects.nonNull(apsOrderGoodsStatusDate)) {
        updateList.add(apsOrderGoodsStatusDate.setExpectMakeBeginTime(t.getEndLocalDate()).setExpectMakeEndTime(t.getBeginLocalDate()).setStatusIndex(t.getSortIndex()));
      } else {
        ApsOrderGoodsStatusDate statusDate = new ApsOrderGoodsStatusDate().setOrderId(req.getOrderId()).setGoodsStatusId(t.getStatusId()).setStatusIndex(t.getSortIndex()).setFactoryId(factoryId).setGoodsStatusId(t.getStatusId()).setExpectMakeBeginTime(t.getBeginLocalDate()).setExpectMakeEndTime(t.getEndLocalDate()).setGoodsId(apsGoods.getId());
        insertList.add(statusDate);
      }
    });
    if (CollUtil.isNotEmpty(insertList)) {
      this.apsOrderGoodsStatusDateService.saveBatch(insertList);
    }
    if (CollUtil.isNotEmpty(updateList)) {
      this.apsOrderGoodsStatusDateService.updateBatchById(updateList);
    }
    if (Objects.nonNull(apsStatus.getOrderStatusId())) {
      this.update(new LambdaUpdateWrapper<ApsOrder>().eq(BaseEntity::getId, apsOrder.getId()).set(ApsOrder::getOrderStatus, apsStatus.getOrderStatusId()));
    }
    this.apsOrderGoodsService.update(new LambdaUpdateWrapper<ApsOrderGoods>().eq(ApsOrderGoods::getOrderId, req.getOrderId())
        .set(ApsOrderGoods::getApsStatusId, req.getGoodsStatusId()));
    this.apsOrderGoodsStatusDateService.update(new LambdaUpdateWrapper<ApsOrderGoodsStatusDate>().eq(ApsOrderGoodsStatusDate::getOrderId, req.getOrderId()).eq(ApsOrderGoodsStatusDate::getGoodsStatusId, req.getGoodsStatusId()).eq(ApsOrderGoodsStatusDate::getGoodsId, orderGoods.getGoodsId()).set(Boolean.TRUE.equals(req.getIsBeginTime()), ApsOrderGoodsStatusDate::getActualMakeBeginTime, LocalDateTime.now()).set(Boolean.FALSE.equals(req.getIsBeginTime()), ApsOrderGoodsStatusDate::getActualMakeEndTime, LocalDateTime.now()));

    return new ApsOrderUpdateOrderStatusRes();
  }

  @Override
  public ApsOrderUpdateSchedulingDateRes updateSchedulingDate(ApsOrderUpdateSchedulingDateReq req) {
    $.assertTrueCanIgnoreException(Objects.isNull(req.getSchedulingDate()) || LocalDate.now().isBefore(req.getSchedulingDate()), "排产日期不能小于今天");
    this.update(new LambdaUpdateWrapper<ApsOrder>().eq(BaseEntity::getId, req.getId()).set(ApsOrder::getSchedulingDate, req.getSchedulingDate()));
    return new ApsOrderUpdateSchedulingDateRes();
  }

  @Override
  public ApsOrder getApsOrderByNo(String orderNo) {
    return StringUtils.isNotBlank(orderNo) ? this.getOne(new LambdaQueryWrapper<ApsOrder>().eq(ApsOrder::getOrderNo, orderNo)) : null;
  }

  @Override
  public OrderCreateDayCountRes orderCreateDayCount(OrderCreateDayCountReq req) {
    int year = Objects.isNull(req.getYear()) ? LocalDate.now().getYear() : req.getYear();
    LocalDateTime beginLocalDate = LocalDateTime.of(year, 1, 1, 0, 0, 0);
    LocalDateTime endLocalDate = LocalDateTime.of(year + 1, 1, 1, 0, 0, 0);
    List<OrderCreateDayCountRes.Info> list = this.listMaps(new QueryWrapper<ApsOrder>().select("date_format(create_time,'%Y-%m-%d') date ,count(1) c ").groupBy("date").lambda().between(BaseEntity::getCreateTime, beginLocalDate, endLocalDate)).stream().map(t -> new OrderCreateDayCountRes.Info().setDate(t.get("date")).setCount(t.get("c"))).toList();
    return new OrderCreateDayCountRes().setDataList(list);
  }

  // 以下为私有对象封装

  @SetUserName
  public @Override void setName(List<? extends ApsOrderDto> apsOrderDtoList) {

    if (CollUtil.isEmpty(apsOrderDtoList)) {
      return;
    }
    Set<Long> oidSet = apsOrderDtoList.stream().map(BaseEntityDto::getId).collect(Collectors.toSet());

    Map<Long, String> stautsNameMap = this.apsStatusService.list().stream().collect(Collectors.toMap(BaseEntity::getId, ApsStatus::getStatusName));
    Map<Long, List<ApsOrderGoodsSaleConfig>> saleListMap = this.apsOrderGoodsSaleConfigService.list(new LambdaQueryWrapper<ApsOrderGoodsSaleConfig>().in(ApsOrderGoodsSaleConfig::getOrderId, oidSet)).stream().collect(Collectors.groupingBy(ApsOrderGoodsSaleConfig::getOrderId));
    Map<Long, List<ApsOrderGoodsProjectConfig>> projectListMap = this.apsOrderGoodsProjectConfigService.list(new LambdaQueryWrapper<ApsOrderGoodsProjectConfig>().in(ApsOrderGoodsProjectConfig::getOrderId, oidSet)).stream().collect(Collectors.groupingBy(ApsOrderGoodsProjectConfig::getOrderId));
    Map<Long, List<ApsOrderGoods>> goodsListMap = this.apsOrderGoodsService.list(new LambdaQueryWrapper<ApsOrderGoods>().in(ApsOrderGoods::getOrderId, oidSet)).stream().collect(Collectors.groupingBy(ApsOrderGoods::getOrderId));
    Map<Long, ApsOrderUser> userMap = this.apsOrderUserService.list(new LambdaQueryWrapper<ApsOrderUser>().in(ApsOrderUser::getOrderId, oidSet)).stream().collect(Collectors.toMap(ApsOrderUser::getOrderId, Function.identity()));
    apsOrderDtoList.forEach(t -> {
      t.setGoodsProjectConfigList($.copyList(projectListMap.get(t.getId()), ApsOrderGoodsProjectConfigDto.class));
      t.setGoodsSaleConfigList($.copyList(saleListMap.get(t.getId()), ApsOrderGoodsSaleConfigDto.class));
      t.setGoodsList($.copyList(goodsListMap.get(t.getId()), ApsOrderGoodsDto.class));
      t.setOrderUser($.copy(userMap.get(t.getId()), ApsOrderUserDto.class));
      t.setOrderStatusName(ApsOrderStatusEnum.getDescByCode(t.getOrderStatus()));
      t.setOrderGoodsStatus(t.getGoodsList().getFirst().getApsStatusId());
      t.setOrderStatusGoodsName(stautsNameMap.get(t.getOrderGoodsStatus()));
    });
  }


  private MPJLambdaWrapper<ApsOrder> getWrapper(ApsOrderDto obj) {
    MPJLambdaWrapper<ApsOrder> q = new MPJLambdaWrapper<>();

    if (Objects.nonNull(obj)) {
      q.eq(StringUtils.isNoneBlank(obj.getOrderNo()), ApsOrder::getOrderNo, obj.getOrderNo()).eq(StringUtils.isNoneBlank(obj.getOrderRemark()), ApsOrder::getOrderRemark, obj.getOrderRemark()).eq(Objects.nonNull(obj.getOrderStatus()), ApsOrder::getOrderStatus, obj.getOrderStatus()).eq(Objects.nonNull(obj.getOrderTotalPrice()), ApsOrder::getOrderTotalPrice, obj.getOrderTotalPrice()).eq(Objects.nonNull(obj.getReserveAmount()), ApsOrder::getReserveAmount, obj.getReserveAmount()).eq(Objects.nonNull(obj.getReserveDatetime()), ApsOrder::getReserveDatetime, obj.getReserveDatetime()).eq(Objects.nonNull(obj.getFinishPayedAmount()), ApsOrder::getFinishPayedAmount, obj.getFinishPayedAmount()).eq(Objects.nonNull(obj.getFinishPayedDatetime()), ApsOrder::getFinishPayedDatetime, obj.getFinishPayedDatetime()).eq(Objects.nonNull(obj.getMakeFinishDate()), ApsOrder::getMakeFinishDate, obj.getMakeFinishDate()).eq(Objects.nonNull(obj.getDeliveryDate()), ApsOrder::getDeliveryDate, obj.getDeliveryDate()).eq(Objects.nonNull(obj.getFactoryId()), ApsOrder::getFactoryId, obj.getFactoryId());
    }
    q.orderByDesc(ApsOrder::getUrgencyLevel).orderByDesc(ApsOrder::getId);
    return q;

  }

  private void setQueryListHeader(DynamicsPage<ApsOrder> page) {

    ServiceComment.header(page, "ApsOrderService#queryPageList");

  }


}

