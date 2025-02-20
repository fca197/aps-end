package com.olivia.peanut.aps.service.impl;

import cn.hutool.core.collection.CollUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.github.yulichang.base.MPJBaseServiceImpl;
import com.github.yulichang.wrapper.MPJLambdaWrapper;
import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.olivia.peanut.aps.api.entity.apsBom.SupplyModelEnum;
import com.olivia.peanut.aps.api.entity.apsGoodsBomBuyPlanItem.*;
import com.olivia.peanut.aps.mapper.ApsGoodsBomBuyPlanItemMapper;
import com.olivia.peanut.aps.model.ApsBom;
import com.olivia.peanut.aps.model.ApsBomSupplier;
import com.olivia.peanut.aps.model.ApsGoodsBomBuyPlanItem;
import com.olivia.peanut.aps.service.ApsBomService;
import com.olivia.peanut.aps.service.ApsBomSupplierService;
import com.olivia.peanut.aps.service.ApsGoodsBomBuyPlanItemService;
import com.olivia.peanut.aps.service.ApsGoodsBomBuyPlanService;
import com.olivia.peanut.aps.service.impl.utils.ApsBomPlan2Email;
import com.olivia.sdk.ann.RedissonLockAnn;
import com.olivia.sdk.ann.SetUserName;
import com.olivia.sdk.comment.ServiceComment;
import com.olivia.sdk.utils.$;
import com.olivia.sdk.utils.BaseEntity;
import com.olivia.sdk.utils.DynamicsPage;
import com.olivia.sdk.utils.LambdaQueryUtil;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.aop.framework.AopContext;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;
import java.util.stream.Collectors;

import static com.olivia.sdk.utils.Str.UN_CHECKED;

/**
 * BOM 购买清单(ApsGoodsBomBuyPlanItem)表服务实现类
 *
 * @author peanut
 * @since 2024-06-05 14:35:31
 */
@Slf4j
@Service("apsGoodsBomBuyPlanItemService")
@Transactional
public class ApsGoodsBomBuyPlanItemServiceImpl extends MPJBaseServiceImpl<ApsGoodsBomBuyPlanItemMapper, ApsGoodsBomBuyPlanItem> implements ApsGoodsBomBuyPlanItemService {

  final static Cache<String, Map<String, String>> cache = CacheBuilder.newBuilder().maximumSize(100).expireAfterWrite(30, TimeUnit.MINUTES).build();


  @Resource
  ApsGoodsBomBuyPlanService apsGoodsBomBuyPlanService;
  @Resource
  ApsBomService apsBomService;
  @Resource
  ApsBomSupplierService apsBomSupplierService;

  public @Override ApsGoodsBomBuyPlanItemQueryListRes queryList(ApsGoodsBomBuyPlanItemQueryListReq req) {

    MPJLambdaWrapper<ApsGoodsBomBuyPlanItem> q = getWrapper(req.getData());
    List<ApsGoodsBomBuyPlanItem> list = this.list(q);

    List<ApsGoodsBomBuyPlanItemDto> dataList = list.stream().map(t -> $.copy(t, ApsGoodsBomBuyPlanItemDto.class)).collect(Collectors.toList());
    ((ApsGoodsBomBuyPlanItemServiceImpl) AopContext.currentProxy()).setName(dataList);

    return new ApsGoodsBomBuyPlanItemQueryListRes().setDataList(dataList);
  }


  public @Override DynamicsPage<ApsGoodsBomBuyPlanItemExportQueryPageListInfoRes> queryPageList(ApsGoodsBomBuyPlanItemExportQueryPageListReq req) {

    DynamicsPage<ApsGoodsBomBuyPlanItem> page = new DynamicsPage<>();
    page.setCurrent(req.getPageNum()).setSize(req.getPageSize());
    setQueryListHeader(page);
    MPJLambdaWrapper<ApsGoodsBomBuyPlanItem> q = getWrapper(req.getData());
    List<ApsGoodsBomBuyPlanItemExportQueryPageListInfoRes> records;
    if (Boolean.TRUE.equals(req.getQueryPage())) {
      IPage<ApsGoodsBomBuyPlanItem> list = this.page(page, q);
      IPage<ApsGoodsBomBuyPlanItemExportQueryPageListInfoRes> dataList = list.convert(t -> $.copy(t, ApsGoodsBomBuyPlanItemExportQueryPageListInfoRes.class));
      records = dataList.getRecords();
    } else {
      records = $.copyList(this.list(q), ApsGoodsBomBuyPlanItemExportQueryPageListInfoRes.class);
    }

    // 类型转换，  更换枚举 等操作

    List<ApsGoodsBomBuyPlanItemExportQueryPageListInfoRes> listInfoRes = $.copyList(records, ApsGoodsBomBuyPlanItemExportQueryPageListInfoRes.class);
    // this.setName(listInfoRes);
    ((ApsGoodsBomBuyPlanItemServiceImpl) AopContext.currentProxy()).setName(listInfoRes);

    return DynamicsPage.init(page, listInfoRes);
  }

  @Override
  @RedissonLockAnn(lockBizKeyFlag = "plan#sendMail", keyExpression = "#req.buyPlanId")
  public SendMail2supplierRes sendMail2supplier(SendMail2supplierReq req) {
    List<ApsGoodsBomBuyPlanItem> planItemList = this.list(new LambdaQueryWrapper<ApsGoodsBomBuyPlanItem>().in(ApsGoodsBomBuyPlanItem::getBuyPlanId, req.getBuyPlanId()));
    $.requireNonNullCanIgnoreException(planItemList, "零件获取为空");
    List<ApsBom> bomList = apsBomService.list(new LambdaQueryWrapper<ApsBom>().in(BaseEntity::getId, planItemList.stream().map(ApsGoodsBomBuyPlanItem::getBomId).toList())
        .eq(ApsBom::getSupplyMode, SupplyModelEnum.buy.name()));
    Set<Long> bomIdSet = bomList.stream().map(BaseEntity::getId).collect(Collectors.toSet());
    planItemList.removeIf(t -> !bomIdSet.contains(t.getBomId()));
    $.requireNonNullCanIgnoreException(planItemList, "所需购买零件获取为空");
    Map<Long, List<ApsGoodsBomBuyPlanItem>> planBomMap = planItemList.stream().collect(Collectors.groupingBy(ApsGoodsBomBuyPlanItem::getBomId));
    Map<Long, ApsBomSupplier> bomSupplierMap = apsBomSupplierService.listByIds(bomList.stream().map(ApsBom::getApsBomSupplierId).toList()).stream().collect(Collectors.toMap(BaseEntity::getId, Function.identity()));
    bomList.stream().collect(Collectors.groupingBy(ApsBom::getApsBomSupplierId)).forEach((k, vl) -> {
      ApsBomSupplier apsBomSupplier = bomSupplierMap.get(k);
      ApsBomPlan2Email.sendMail(req, k, apsBomSupplier, vl, planBomMap);
    });

    return new SendMail2supplierRes();
  }

  // 以下为私有对象封装

  @SetUserName
  public @Override void setName(List<? extends ApsGoodsBomBuyPlanItemDto> apsGoodsBomBuyPlanItemDtoList) {

    if (CollUtil.isEmpty(apsGoodsBomBuyPlanItemDtoList)) {
      return;
    }
    apsGoodsBomBuyPlanItemDtoList.sort(Comparator.comparing(ApsGoodsBomBuyPlanItemDto::getIsFollow).reversed());
  }


  @SuppressWarnings(UN_CHECKED)
  private MPJLambdaWrapper<ApsGoodsBomBuyPlanItem> getWrapper(ApsGoodsBomBuyPlanItemDto obj) {
    MPJLambdaWrapper<ApsGoodsBomBuyPlanItem> q = new MPJLambdaWrapper<>();

    LambdaQueryUtil.lambdaQueryWrapper(q, obj, ApsGoodsBomBuyPlanItem.class, ApsGoodsBomBuyPlanItem::getBuyPlanId //
        , ApsGoodsBomBuyPlanItem::getBomId//
        , ApsGoodsBomBuyPlanItem::getBomCode//
        , ApsGoodsBomBuyPlanItem::getBomName

    );
    q.orderByDesc(ApsGoodsBomBuyPlanItem::getId);
    return q;

  }

  private void setQueryListHeader(DynamicsPage<ApsGoodsBomBuyPlanItem> page) {

    ServiceComment.header(page, "ApsGoodsBomBuyPlanItemService#queryPageList");

  }


}

