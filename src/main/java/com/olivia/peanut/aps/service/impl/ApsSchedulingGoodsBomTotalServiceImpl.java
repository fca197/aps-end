package com.olivia.peanut.aps.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.IdWorker;
import com.github.yulichang.base.MPJBaseServiceImpl;
import com.github.yulichang.wrapper.MPJLambdaWrapper;
import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.olivia.peanut.aps.api.entity.apsGoodsBom.ApsGoodsBomDto;
import com.olivia.peanut.aps.api.entity.apsSchedulingGoodsBomTotal.*;
import com.olivia.peanut.aps.mapper.ApsSchedulingGoodsBomTotalMapper;
import com.olivia.peanut.aps.model.*;
import com.olivia.peanut.aps.service.*;
import com.olivia.sdk.ann.RedissonLockAnn;
import com.olivia.sdk.ann.SetUserName;
import com.olivia.sdk.comment.ServiceComment;
import com.olivia.sdk.utils.$;
import com.olivia.sdk.utils.BaseEntity;
import com.olivia.sdk.utils.DynamicsPage;
import com.olivia.sdk.utils.Str;
import jakarta.annotation.Resource;
import org.apache.commons.lang3.StringUtils;
import org.redisson.Redisson;
import org.springframework.aop.framework.AopContext;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;
import java.util.function.Function;
import java.util.stream.Collectors;

import static com.olivia.sdk.utils.Str.DISTINCT;

/**
 * 订单商品零件汇总表(ApsSchedulingGoodsBomTotal)表服务实现类
 *
 * @author peanut
 * @since 2024-06-02 22:04:08
 */
@Service("apsSchedulingGoodsBomTotalService")
@Transactional
public class ApsSchedulingGoodsBomTotalServiceImpl extends MPJBaseServiceImpl<ApsSchedulingGoodsBomTotalMapper, ApsSchedulingGoodsBomTotal> implements
    ApsSchedulingGoodsBomTotalService {

  final static Cache<String, Map<String, String>> cache = CacheBuilder.newBuilder().maximumSize(100).expireAfterWrite(30, TimeUnit.MINUTES).build();

  @Resource
  ApsGoodsBomService apsGoodsBomService;
  @Resource
  ApsSchedulingVersionService apsSchedulingVersionService;
  @Resource
  ApsGoodsBomBuyPlanItemService apsGoodsBomBuyPlanItemService;
  @Resource
  ApsGoodsBomBuyPlanService apsGoodsBomBuyPlanService;
  @Resource
  Redisson redisson;
  @Resource
  ApsBomService apsBomService;

  public @Override ApsSchedulingGoodsBomTotalQueryListRes queryList(ApsSchedulingGoodsBomTotalQueryListReq req) {

    MPJLambdaWrapper<ApsSchedulingGoodsBomTotal> q = getWrapper(req.getData());
    List<ApsSchedulingGoodsBomTotal> list = this.list(q);

    List<ApsSchedulingGoodsBomTotalDto> dataList = list.stream().map(t -> $.copy(t, ApsSchedulingGoodsBomTotalDto.class)).collect(Collectors.toList());
    //  this.setName(dataList);
    ((ApsSchedulingGoodsBomTotalService) AopContext.currentProxy()).setName(dataList);

    return new ApsSchedulingGoodsBomTotalQueryListRes().setDataList(dataList);
  }

  public @Override DynamicsPage<ApsSchedulingGoodsBomTotalExportQueryPageListInfoRes> queryPageList(ApsSchedulingGoodsBomTotalExportQueryPageListReq req) {

    DynamicsPage<ApsSchedulingGoodsBomTotal> page = new DynamicsPage<>();
    page.setCurrent(req.getPageNum()).setSize(req.getPageSize());
    setQueryListHeader(page);
    MPJLambdaWrapper<ApsSchedulingGoodsBomTotal> q = getWrapper(req.getData());
    List<ApsSchedulingGoodsBomTotalExportQueryPageListInfoRes> records;
    if (Boolean.TRUE.equals(req.getQueryPage())) {
      IPage<ApsSchedulingGoodsBomTotal> list = this.page(page, q);
      IPage<ApsSchedulingGoodsBomTotalExportQueryPageListInfoRes> dataList = list.convert(t -> $.copy(t, ApsSchedulingGoodsBomTotalExportQueryPageListInfoRes.class));
      records = dataList.getRecords();
    } else {
      records = $.copyList(this.list(q), ApsSchedulingGoodsBomTotalExportQueryPageListInfoRes.class);
    }

    // 类型转换，  更换枚举 等操作

    List<ApsSchedulingGoodsBomTotalExportQueryPageListInfoRes> listInfoRes = $.copyList(records, ApsSchedulingGoodsBomTotalExportQueryPageListInfoRes.class);
    // this.setName(listInfoRes);
    ((ApsSchedulingGoodsBomTotalService) AopContext.currentProxy()).setName(listInfoRes);

    return DynamicsPage.init(page, listInfoRes);
  }

  @Override
  public DynamicsPage<ApsSchedulingGoodsBomTotalQueryBomTotalRes> queryBomTotal(ApsSchedulingGoodsBomTotalQueryBomTotalReq req) {
    ApsSchedulingVersion schedulingVersion = apsSchedulingVersionService.getById(req.getSchedulingVersionId());
    $.requireNonNullCanIgnoreException(schedulingVersion, "排产为空,请确认版本");
    DynamicsPage<ApsSchedulingGoodsBomTotalQueryBomTotalRes> retPage = new DynamicsPage<>();
    List<Long> bomIdList = this.list(
            new QueryWrapper<ApsSchedulingGoodsBomTotal>().select(DISTINCT + "  goods_bom_Id").lambda().in(ApsSchedulingGoodsBomTotal::getSchedulingId, req.getSchedulingVersionId()))
        .stream().map(ApsSchedulingGoodsBomTotal::getGoodsBomId).collect(Collectors.toList());
    ;
    if (CollUtil.isEmpty(bomIdList)) {
//      retPage.setCurrent(req.getPageNum()).setTotal(bomTotalList.size());
      return retPage;
    }
    Map<Long, ApsGoodsBom> goodsBomMap = apsGoodsBomService.listByIds(bomIdList).stream().collect(Collectors.toMap(ApsGoodsBom::getId, Function.identity()));
    Map<Long, ApsBom> apsBomMap = apsBomService.list().stream().collect(Collectors.toMap(BaseEntity::getId, Function.identity()));
    Map<Long, List<ApsSchedulingGoodsBomTotal>> bomUserMap = this.list(
        new LambdaQueryWrapper<ApsSchedulingGoodsBomTotal>().eq(ApsSchedulingGoodsBomTotal::getSchedulingId, req.getSchedulingVersionId())
            .in(ApsSchedulingGoodsBomTotal::getGoodsBomId, bomIdList)).stream().collect(Collectors.groupingBy(ApsSchedulingGoodsBomTotal::getGoodsBomId));
    List<ApsSchedulingGoodsBomTotalQueryBomTotalRes> retList = new ArrayList<>();
    Set<String> headerSet = new HashSet<>();
//    String bomTotalEndDate = schedulingVersion.getBomTotalEndDate().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
    bomUserMap.forEach((goodsBomId, bomList) -> {
      ApsSchedulingGoodsBomTotal apsSchedulingGoodsBomTotal = bomList.getFirst();
      ApsSchedulingGoodsBomTotalQueryBomTotalRes retObj = new ApsSchedulingGoodsBomTotalQueryBomTotalRes().setBomName(apsSchedulingGoodsBomTotal.getBomName());
      retObj.putAll(BeanUtil.beanToMap(apsSchedulingGoodsBomTotal));

      AtomicLong usageCount = new AtomicLong(0);
      bomList.forEach(bomTotal -> {

        String currentDate = bomTotal.getBomUseDate().toString();
        headerSet.add(currentDate);
        retObj.put(currentDate, bomTotal.getBomUsage());
//        if (currentDate.compareTo(bomTotalEndDate) <= 0) {
        usageCount.addAndGet(bomTotal.getBomUsage());
//        }
      });
      retList.add(retObj);
      ApsGoodsBom apsGoodsBom = goodsBomMap.get(goodsBomId);
      ApsBom apsBom = apsBomMap.get(apsGoodsBom.getBomId());
      if (Boolean.TRUE.equals(req.getRetBom())) {
        retObj.setApsGoodsBomDto($.copy(apsGoodsBom, ApsGoodsBomDto.class));
      }
//      apsBomMap.get()
      Long bomInventory = apsBom.getBomInventory().longValue();
      boolean isEnough = bomInventory > usageCount.get();
      retObj.put("bomInventory", bomInventory);
      retObj.put("bomUseCount", usageCount.get());
      retObj.put("bomLackCount", bomInventory - usageCount.get());
      retObj.put("bomContrast", usageCount.get() + "/" + bomInventory);
      retObj.put("enough", isEnough ? 1 : 0);
      retObj.put("isFollowStr", Str.booleanToStr(apsGoodsBom.getIsFollow()));
      retObj.put("isNotFollow", Boolean.TRUE.equals(apsGoodsBom.getIsFollow()) ? "1" : "0");
      retObj.put("enoughStr", isEnough ? "满足" : "缺少");
//      retObj.put("bomTotalEndDate", bomTotalEndDate);
    });
    retList.sort(Comparator.comparing(ApsSchedulingGoodsBomTotalQueryBomTotalRes::getBomName));
    retList.sort(Comparator.comparing(ApsSchedulingGoodsBomTotalQueryBomTotalRes::getIsNotFollow).reversed());
    retList.sort(Comparator.comparingInt(t -> (int) t.get("enough")));
    retList.sort(Comparator.comparing(ApsSchedulingGoodsBomTotalQueryBomTotalRes::getIsNotFollow).reversed());
    retPage.setHeaderList(new ArrayList<>()).setDataList(retList);
    ServiceComment.header(retPage, "ApsSchedulingGoodsBomTotalService#queryPageList");
    new ArrayList<>(headerSet).stream().sorted().forEach(h -> retPage.addHeader(h, h, 100));
    return retPage;
  }

  @Override
  @Transactional
  @RedissonLockAnn(lockBizKeyFlag = "schedulingBom#createBomBuyPlan", loginBizKeyExpression = "#req.schedulingVersionId")
  public ApsSchedulingGoodsBomTotalCreateBomBuyPlanRes createBomBuyPlan(ApsSchedulingGoodsBomTotalCreateBomBuyPlanReq req) {
    DynamicsPage<ApsSchedulingGoodsBomTotalQueryBomTotalRes> queryBomTotalRes = this.queryBomTotal(
        $.copy(req, ApsSchedulingGoodsBomTotalQueryBomTotalReq.class).setRetBom(Boolean.TRUE));

    List<ApsSchedulingGoodsBomTotalQueryBomTotalRes> totalResList = queryBomTotalRes.getDataList();
    $.assertTrueCanIgnoreException(CollUtil.isNotEmpty(totalResList), "没有需要生成的采购数据");
    totalResList.removeIf(ApsSchedulingGoodsBomTotalQueryBomTotalRes::isEnough);
    $.assertTrueCanIgnoreException(CollUtil.isNotEmpty(totalResList), "没有需要生成的采购数据,当前库存都满足需求");
    ApsSchedulingVersion schedulingVersion = apsSchedulingVersionService.getById(req.getSchedulingVersionId());

    ApsGoodsBomBuyPlan apsGoodsBomBuyPlan = new ApsGoodsBomBuyPlan().setPlanName(schedulingVersion.getSchedulingVersionName() + "排产零件购买")
        .setPlanRemark("基于排产结果生成零件购买计划").setPlanSource("scheduling");
    long buyPlanId = IdWorker.getId();
    apsGoodsBomBuyPlan.setId(buyPlanId);
    List<ApsGoodsBomBuyPlanItem> apsGoodsBomBuyPlanItemList = new ArrayList<>();
    Map<Long, ApsBom> apsBomMap = apsBomService.listByIds(
            totalResList.stream().map(ApsSchedulingGoodsBomTotalQueryBomTotalRes::getApsGoodsBomDto).map(ApsGoodsBomDto::getBomId).toList()).stream()
        .collect(Collectors.toMap(BaseEntity::getId, Function.identity()));
    totalResList.forEach(retBom -> {
      ApsGoodsBomDto bom = retBom.getApsGoodsBomDto();
      ApsGoodsBomBuyPlanItem planItem = new ApsGoodsBomBuyPlanItem().setBuyPlanId(buyPlanId);
      ApsBom apsBom = apsBomMap.get(bom.getBomId());
      planItem.setGoodsBomId(bom.getId()).setBomId(apsBom.getId()).setBomCode(bom.getBomCode()).setBomName(bom.getBomName()).setIsFollow(bom.getIsFollow())
          .setBomCostPrice(bom.getBomCostPrice()).setBomCostPriceUnit(bom.getBomCostPriceUnit()).setBomInventory(apsBom.getBomInventory())
          .setBomBuyCount(new BigDecimal(-retBom.getInteger("bomLackCount")));
      apsGoodsBomBuyPlanItemList.add(planItem);
    });
    apsGoodsBomBuyPlan.setPlanTotalAmount(
        apsGoodsBomBuyPlanItemList.stream().map(t -> t.getBomCostPrice().multiply(t.getBomBuyCount())).reduce(new BigDecimal(0), BigDecimal::add));
    this.apsGoodsBomBuyPlanService.save(apsGoodsBomBuyPlan);
    this.apsGoodsBomBuyPlanItemService.saveBatch(apsGoodsBomBuyPlanItemList);

    return new ApsSchedulingGoodsBomTotalCreateBomBuyPlanRes();
  }

  // 以下为私有对象封装

  @SetUserName
  public @Override void setName(List<? extends ApsSchedulingGoodsBomTotalDto> apsSchedulingGoodsBomTotalDtoList) {

    if (CollUtil.isEmpty(apsSchedulingGoodsBomTotalDtoList)) {
    }


  }


  private MPJLambdaWrapper<ApsSchedulingGoodsBomTotal> getWrapper(ApsSchedulingGoodsBomTotalDto obj) {
    MPJLambdaWrapper<ApsSchedulingGoodsBomTotal> q = new MPJLambdaWrapper<>();

    if (Objects.nonNull(obj)) {
      q.eq(Objects.nonNull(obj.getSchedulingId()), ApsSchedulingGoodsBomTotal::getSchedulingId, obj.getSchedulingId())
          .eq(Objects.nonNull(obj.getBomId()), ApsSchedulingGoodsBomTotal::getGoodsBomId, obj.getBomId())
          .eq(StringUtils.isNoneBlank(obj.getBomCode()), ApsSchedulingGoodsBomTotal::getBomCode, obj.getBomCode())
          .eq(StringUtils.isNoneBlank(obj.getBomName()), ApsSchedulingGoodsBomTotal::getBomName, obj.getBomName())
          .eq(Objects.nonNull(obj.getBomUsage()), ApsSchedulingGoodsBomTotal::getBomUsage, obj.getBomUsage())
          .eq(StringUtils.isNoneBlank(obj.getBomUnit()), ApsSchedulingGoodsBomTotal::getBomUnit, obj.getBomUnit())
          .eq(Objects.nonNull(obj.getBomCostPrice()), ApsSchedulingGoodsBomTotal::getBomCostPrice, obj.getBomCostPrice())
          .eq(StringUtils.isNoneBlank(obj.getBomCostPriceUnit()), ApsSchedulingGoodsBomTotal::getBomCostPriceUnit, obj.getBomCostPriceUnit())
          .eq(Objects.nonNull(obj.getBomUseWorkStation()), ApsSchedulingGoodsBomTotal::getBomUseWorkStation, obj.getBomUseWorkStation())
          .eq(Objects.nonNull(obj.getBomUseDate()), ApsSchedulingGoodsBomTotal::getBomUseDate, obj.getBomUseDate())
          .eq(Objects.nonNull(obj.getFactoryId()), ApsSchedulingGoodsBomTotal::getFactoryId, obj.getFactoryId())

      ;
    }
    q.orderByDesc(ApsSchedulingGoodsBomTotal::getId);
    return q;

  }

  private void setQueryListHeader(DynamicsPage<ApsSchedulingGoodsBomTotal> page) {

    ServiceComment.header(page, "ApsSchedulingGoodsBomTotalService#queryPageList");

  }


}

