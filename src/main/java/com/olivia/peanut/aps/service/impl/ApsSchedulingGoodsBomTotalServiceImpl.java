package com.olivia.peanut.aps.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.ReflectUtil;
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
import com.olivia.peanut.aps.enums.ApsGoodsBomBuyPlanTypeEnum;
import com.olivia.peanut.aps.mapper.ApsSchedulingGoodsBomTotalMapper;
import com.olivia.peanut.aps.model.*;
import com.olivia.peanut.aps.service.*;
import com.olivia.sdk.ann.RedissonLockAnn;
import com.olivia.sdk.ann.SetUserName;
import com.olivia.sdk.comment.ServiceComment;
import com.olivia.sdk.utils.*;
import jakarta.annotation.Resource;
import org.apache.commons.lang3.StringUtils;
import org.springframework.aop.framework.AopContext;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;
import java.util.function.Function;
import java.util.stream.Collectors;

import static com.olivia.sdk.utils.FieldUtils.getField;
import static com.olivia.sdk.utils.Str.DISTINCT;
import static java.lang.Boolean.FALSE;
import static java.math.BigDecimal.ZERO;

/**
 * 订单商品零件汇总表(ApsSchedulingGoodsBomTotal)表服务实现类
 *
 * @author peanut
 * @since 2024-06-02 22:04:08
 */
@Service("apsSchedulingGoodsBomTotalService")
@Transactional
public class ApsSchedulingGoodsBomTotalServiceImpl extends MPJBaseServiceImpl<ApsSchedulingGoodsBomTotalMapper, ApsSchedulingGoodsBomTotal> implements ApsSchedulingGoodsBomTotalService {

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
  ApsBomService apsBomService;


  @Resource
  ApsSchedulingGoodsBomService apsSchedulingGoodsBomService;

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
    List<Long> bomIdList = this.list(new QueryWrapper<ApsSchedulingGoodsBomTotal>().select(DISTINCT + "  goods_bom_Id").lambda().in(ApsSchedulingGoodsBomTotal::getSchedulingId, req.getSchedulingVersionId())).stream().map(ApsSchedulingGoodsBomTotal::getGoodsBomId).collect(Collectors.toList());
    ;
    if (CollUtil.isEmpty(bomIdList)) {
//      retPage.setCurrent(req.getPageNum()).setTotal(bomTotalList.size());
      return retPage;
    }
    Map<Long, ApsGoodsBom> goodsBomMap = apsGoodsBomService.listByIds(bomIdList).stream().collect(Collectors.toMap(ApsGoodsBom::getId, Function.identity()));
    Map<Long, ApsBom> apsBomMap = apsBomService.list().stream().collect(Collectors.toMap(BaseEntity::getId, Function.identity()));
    Map<Long, List<ApsSchedulingGoodsBomTotal>> bomUserMap = this.list(new LambdaQueryWrapper<ApsSchedulingGoodsBomTotal>().eq(ApsSchedulingGoodsBomTotal::getSchedulingId, req.getSchedulingVersionId()).in(ApsSchedulingGoodsBomTotal::getGoodsBomId, bomIdList)).stream().collect(Collectors.groupingBy(ApsSchedulingGoodsBomTotal::getGoodsBomId));
    List<ApsSchedulingGoodsBomTotalQueryBomTotalRes> retList = new ArrayList<>();
    Set<String> headerSet = new HashSet<>();
//    String bomTotalEndDate = schedulingVersion.getBomTotalEndDate().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
    bomUserMap.forEach((goodsBomId, bomList) -> {
      ApsSchedulingGoodsBomTotal apsSchedulingGoodsBomTotal = bomList.getFirst();
      ApsSchedulingGoodsBomTotalQueryBomTotalRes retObj = new ApsSchedulingGoodsBomTotalQueryBomTotalRes().setBomName(apsSchedulingGoodsBomTotal.getBomName());
      retObj.putAll(BeanUtil.beanToMap(apsSchedulingGoodsBomTotal));

      AtomicReference<BigDecimal> usageCount = new AtomicReference<>(new BigDecimal(0));
      bomList.forEach(bomTotal -> {

        String currentDate = bomTotal.getBomUseDate().toString();
        headerSet.add(currentDate);
        retObj.put(currentDate, bomTotal.getBomUsage());
//        if (currentDate.compareTo(bomTotalEndDate) <= 0) {
        usageCount.getAndAccumulate(bomTotal.getBomUsage(), BigDecimal::add);
//        }
      });
      retList.add(retObj);
      ApsGoodsBom apsGoodsBom = goodsBomMap.get(goodsBomId);
      ApsBom apsBom = apsBomMap.get(apsGoodsBom.getBomId());
      if (Boolean.TRUE.equals(req.getRetBom())) {
        retObj.setApsGoodsBomDto($.copy(apsGoodsBom, ApsGoodsBomDto.class));
      }
//      apsBomMap.get()
      BigDecimal bomInventory = apsBom.getBomInventory();
      boolean isEnough = bomInventory.compareTo(usageCount.get()) >= 0;
      retObj.put("bomInventory", bomInventory);
      retObj.put("bomUseCount", usageCount.get());
      retObj.put("bomLackCount", bomInventory.subtract(usageCount.get()));
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
  @RedissonLockAnn(lockBizKeyFlag = "schedulingBom#createBomBuyPlan", keyExpression = "#req.schedulingVersionId")
  public ApsSchedulingGoodsBomTotalCreateBomBuyPlanRes createBomBuyPlan(ApsSchedulingGoodsBomTotalCreateBomBuyPlanReq req) {


    ApsSchedulingVersion apsSchedulingVersion = apsSchedulingVersionService.getById(req.getSchedulingVersionId());
    $.requireNonNullCanIgnoreException(apsSchedulingVersion, "没有需要生成的采购数据,当前版本为空");

    List<ApsSchedulingGoodsBomTotal> bomTotalList = this.list(new LambdaQueryWrapper<ApsSchedulingGoodsBomTotal>().eq(ApsSchedulingGoodsBomTotal::getSchedulingId, req.getSchedulingVersionId()));

    List<Long> bomIdList = bomTotalList.stream().map(ApsSchedulingGoodsBomTotal::getBomId).toList();
    List<ApsBom> bomList = this.apsBomService.listByIds(bomIdList);
    Map<Long, Boolean> bomFollowMap = apsGoodsBomService.list(new LambdaQueryWrapper<ApsGoodsBom>().in(ApsGoodsBom::getBomId, bomIdList)).stream().collect(Collectors.toMap(BaseEntity::getId, ApsGoodsBom::getIsFollow));
    Map<Long, BigDecimal> bomInvMap = bomList.stream().collect(Collectors.toMap(BaseEntity::getId, ApsBom::getBomInventory));

    Map<Long, BigDecimal> bomUseMap = bomTotalList.stream().collect(Collectors.groupingBy(ApsSchedulingGoodsBomTotal::getBomId,
        Collectors.collectingAndThen(Collectors.toList(),
            r -> r.stream().map(ApsSchedulingGoodsBomTotal::getBomUsage).filter(Objects::nonNull).reduce(BigDecimal::add).orElse(ZERO))));


    Set<Long> bomIdSet = new HashSet<>(bomInvMap.keySet());
    // 如果库存>=使用  移除零件，剩下的不合格
    bomIdSet.removeIf(bomId -> bomInvMap.get(bomId).compareTo(bomUseMap.get(bomId)) <= 0);

    // 删除,非不合格
    bomTotalList.removeIf(b -> bomIdSet.contains(b.getBomId()));

    $.assertTrueCanIgnoreException(CollUtil.isNotEmpty(bomTotalList), "没有需要生成的采购数据");
    long buyPlanId = IdWorker.getId();

    List<ApsGoodsBomBuyPlanItem> apsGoodsBomBuyPlanItemList = new ArrayList<>();
    bomTotalList.stream().collect(Collectors.groupingBy(t -> t.getBomId() + t.getBomUseDate().getYear()))
        .forEach((k, vl) -> {
          ApsSchedulingGoodsBomTotal bom = vl.getFirst();
          ApsGoodsBomBuyPlanItem planItem = $.copy(bom, ApsGoodsBomBuyPlanItem.class);
          planItem.setBuyPlanId(buyPlanId).setYear(bom.getBomUseDate().getYear()).setIsFollow(bomFollowMap.getOrDefault(bom.getId(), FALSE)).setId(IdWorker.getId());
          vl.forEach(v -> {
            BigDecimal inv = bomInvMap.getOrDefault(v.getBomId(), ZERO);
            BigDecimal lastInv = inv.subtract(v.getBomUsage());
            Map<String, Object> map = Map.of("need_use", v.getBomUsage(), "buy_inv", lastInv, "bom_inv", inv, "lack", lastInv.compareTo(ZERO) < 0);
            ReflectUtil.setFieldValue(planItem, getField(ApsGoodsBomBuyPlanItem.class, ApsGoodsBomBuyPlanItem.fieldName + v.getBomUseDate().getDayOfYear()), JSON.toJSONString(map));
          });
          apsGoodsBomBuyPlanItemList.add(planItem);
        });

    String dateList = JSON.toJSONString(bomTotalList.stream().map(ApsSchedulingGoodsBomTotal::getBomUseDate).distinct().sorted().map(t -> Map.of("date", t, "day", t.getDayOfYear())).toList());
    ApsGoodsBomBuyPlan apsGoodsBomBuyPlan = new ApsGoodsBomBuyPlan().setPlanName(apsSchedulingVersion.getSchedulingVersionName() + "排产零件购买").setPlanRemark("基于排产结果生成零件购买计划").setPlanSource("scheduling").setBuyPlanType(ApsGoodsBomBuyPlanTypeEnum.SCHEDULING);
    apsGoodsBomBuyPlan.setBomUseDate(dateList).setId(buyPlanId);

    apsGoodsBomBuyPlan.setPlanTotalAmount(apsGoodsBomBuyPlanItemList.stream().map(t -> t.getBomCostPrice().multiply(t.getBomUsage())).reduce(ZERO, BigDecimal::add));
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
      q.eq(Objects.nonNull(obj.getSchedulingId()), ApsSchedulingGoodsBomTotal::getSchedulingId, obj.getSchedulingId()).eq(Objects.nonNull(obj.getBomId()), ApsSchedulingGoodsBomTotal::getGoodsBomId, obj.getBomId()).eq(StringUtils.isNoneBlank(obj.getBomCode()), ApsSchedulingGoodsBomTotal::getBomCode, obj.getBomCode()).eq(StringUtils.isNoneBlank(obj.getBomName()), ApsSchedulingGoodsBomTotal::getBomName, obj.getBomName()).eq(Objects.nonNull(obj.getBomUsage()), ApsSchedulingGoodsBomTotal::getBomUsage, obj.getBomUsage()).eq(StringUtils.isNoneBlank(obj.getBomUnit()), ApsSchedulingGoodsBomTotal::getBomUnit, obj.getBomUnit()).eq(Objects.nonNull(obj.getBomCostPrice()), ApsSchedulingGoodsBomTotal::getBomCostPrice, obj.getBomCostPrice()).eq(StringUtils.isNoneBlank(obj.getBomCostPriceUnit()), ApsSchedulingGoodsBomTotal::getBomCostPriceUnit, obj.getBomCostPriceUnit()).eq(Objects.nonNull(obj.getBomUseWorkStation()), ApsSchedulingGoodsBomTotal::getBomUseWorkStation, obj.getBomUseWorkStation()).eq(Objects.nonNull(obj.getBomUseDate()), ApsSchedulingGoodsBomTotal::getBomUseDate, obj.getBomUseDate()).eq(Objects.nonNull(obj.getFactoryId()), ApsSchedulingGoodsBomTotal::getFactoryId, obj.getFactoryId())

      ;
    }
    q.orderByDesc(ApsSchedulingGoodsBomTotal::getId);
    return q;

  }

  private void setQueryListHeader(DynamicsPage<ApsSchedulingGoodsBomTotal> page) {

    ServiceComment.header(page, "ApsSchedulingGoodsBomTotalService#queryPageList");

  }


}

