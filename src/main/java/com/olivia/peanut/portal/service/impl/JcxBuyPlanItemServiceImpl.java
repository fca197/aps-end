package com.olivia.peanut.portal.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.github.yulichang.base.MPJBaseServiceImpl;
import com.github.yulichang.wrapper.MPJLambdaWrapper;
import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.olivia.peanut.portal.api.entity.jcxBuyPlanItem.*;
import com.olivia.peanut.portal.mapper.JcxBuyPlanItemMapper;
import com.olivia.peanut.portal.model.JcxBuyPlanItem;
import com.olivia.peanut.portal.service.JcxBuyPlanItemService;
import com.olivia.sdk.utils.$;
import com.olivia.sdk.utils.DynamicsPage;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * (JcxBuyPlanItem)表服务实现类
 *
 * @author peanut
 * @since 2024-03-24 20:27:12
 */
@Service("jcxBuyPlanItemService")
@Transactional
public class JcxBuyPlanItemServiceImpl extends MPJBaseServiceImpl<JcxBuyPlanItemMapper, JcxBuyPlanItem> implements JcxBuyPlanItemService {

  final static Cache<String, Map<String, String>> cache = CacheBuilder.newBuilder().maximumSize(100).expireAfterWrite(30, TimeUnit.MINUTES).build();

  public @Override JcxBuyPlanItemQueryListRes queryList(JcxBuyPlanItemQueryListReq req) {

    MPJLambdaWrapper<JcxBuyPlanItem> q = getWrapper(req.getData());
    List<JcxBuyPlanItem> list = this.list(q);

    List<JcxBuyPlanItemDto> dataList = list.stream().map(t -> $.copy(t, JcxBuyPlanItemDto.class)).collect(Collectors.toList());

    return new JcxBuyPlanItemQueryListRes().setDataList(dataList);
  }


  public @Override DynamicsPage<JcxBuyPlanItemExportQueryPageListInfoRes> queryPageList(JcxBuyPlanItemExportQueryPageListReq req) {

    DynamicsPage<JcxBuyPlanItem> page = new DynamicsPage<>();
    page.setCurrent(req.getPageNum()).setSize(req.getPageSize());
    setQueryListHeader(page);
    MPJLambdaWrapper<JcxBuyPlanItem> q = getWrapper(req.getData());
    List<JcxBuyPlanItemExportQueryPageListInfoRes> records;
    if (Boolean.TRUE.equals(req.getQueryPage())) {
      IPage<JcxBuyPlanItem> list = this.page(page, q);
      IPage<JcxBuyPlanItemExportQueryPageListInfoRes> dataList = list.convert(t -> $.copy(t, JcxBuyPlanItemExportQueryPageListInfoRes.class));
      records = dataList.getRecords();
    } else {
      records = $.copyList(this.list(q), JcxBuyPlanItemExportQueryPageListInfoRes.class);
    }

    // 类型转换，  更换枚举 等操作
    List<JcxBuyPlanItemExportQueryPageListInfoRes> listInfoRes = $.copyList(records, JcxBuyPlanItemExportQueryPageListInfoRes.class);

    return DynamicsPage.init(page, listInfoRes);
  }

  // 以下为私有对象封装


  private MPJLambdaWrapper<JcxBuyPlanItem> getWrapper(JcxBuyPlanItemDto obj) {
    MPJLambdaWrapper<JcxBuyPlanItem> q = new MPJLambdaWrapper<>();

    if (Objects.nonNull(obj)) {
      q
          .eq(Objects.nonNull(obj.getId()), JcxBuyPlanItem::getId, obj.getId())
          .eq(Objects.nonNull(obj.getPlanId()), JcxBuyPlanItem::getPlanId, obj.getPlanId())
          .eq(Objects.nonNull(obj.getGoodsId()), JcxBuyPlanItem::getGoodsId, obj.getGoodsId())
          .eq(Objects.nonNull(obj.getCostPrice()), JcxBuyPlanItem::getCostPrice, obj.getCostPrice())
          .eq(Objects.nonNull(obj.getSalesPrice()), JcxBuyPlanItem::getSalesPrice, obj.getSalesPrice())
          .eq(Objects.nonNull(obj.getWarningCount()), JcxBuyPlanItem::getWarningCount, obj.getWarningCount())
          .eq(Objects.nonNull(obj.getGoodsGrossProfit()), JcxBuyPlanItem::getGoodsGrossProfit, obj.getGoodsGrossProfit())
          .eq(Objects.nonNull(obj.getGoodsNetProfit()), JcxBuyPlanItem::getGoodsNetProfit, obj.getGoodsNetProfit())
          .eq(Objects.nonNull(obj.getGoodsInventoryCount()), JcxBuyPlanItem::getGoodsInventoryCount, obj.getGoodsInventoryCount())

      ;
    }

    return q.orderByDesc(JcxBuyPlanItem::getId);

  }

  private void setQueryListHeader(DynamicsPage<JcxBuyPlanItem> page) {
    page
        .addHeader("id", "$column.comment")
        .addHeader("planId", "$column.comment")
        .addHeader("goodsId", "$column.comment")
        .addHeader("costPrice", "$column.comment")
        .addHeader("salesPrice", "$column.comment")
        .addHeader("warningCount", "$column.comment")
        .addHeader("goodsGrossProfit", "$column.comment")
        .addHeader("goodsNetProfit", "$column.comment")
        .addHeader("goodsInventoryCount", "$column.comment")
        .addHeader("isDelete", "$column.comment")
        .addHeader("createTime", "$column.comment")
        .addHeader("createBy", "$column.comment")
        .addHeader("updateTime", "$column.comment")
        .addHeader("updateBy", "$column.comment")
        .addHeader("traceId", "$column.comment")
        .addHeader("version", "$column.comment")
        .addHeader("versionNum", "$column.comment")
    ;
  }


}

