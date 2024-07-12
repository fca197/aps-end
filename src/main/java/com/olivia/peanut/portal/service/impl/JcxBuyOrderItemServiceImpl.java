package com.olivia.peanut.portal.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.github.yulichang.base.MPJBaseServiceImpl;
import com.github.yulichang.wrapper.MPJLambdaWrapper;
import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.olivia.peanut.portal.api.entity.jcxBuyOrderItem.*;
import com.olivia.peanut.portal.mapper.JcxBuyOrderItemMapper;
import com.olivia.peanut.portal.model.JcxBuyOrderItem;
import com.olivia.peanut.portal.service.JcxBuyOrderItemService;
import com.olivia.sdk.ann.SetUserName;
import com.olivia.sdk.comment.ServiceComment;
import com.olivia.sdk.utils.$;
import com.olivia.sdk.utils.DynamicsPage;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * (JcxBuyOrderItem)表服务实现类
 *
 * @author peanut
 * @since 2024-03-27 13:51:37
 */
@Service("jcxBuyOrderItemService")
@Transactional
public class JcxBuyOrderItemServiceImpl extends MPJBaseServiceImpl<JcxBuyOrderItemMapper, JcxBuyOrderItem> implements JcxBuyOrderItemService {

  final static Cache<String, Map<String, String>> cache = CacheBuilder.newBuilder().maximumSize(100).expireAfterWrite(30, TimeUnit.MINUTES).build();


  public @Override JcxBuyOrderItemQueryListRes queryList(JcxBuyOrderItemQueryListReq req) {

    MPJLambdaWrapper<JcxBuyOrderItem> q = getWrapper(req.getData());
    List<JcxBuyOrderItem> list = this.list(q);

    List<JcxBuyOrderItemDto> dataList = list.stream().map(t -> $.copy(t, JcxBuyOrderItemDto.class)).collect(Collectors.toList());

    return new JcxBuyOrderItemQueryListRes().setDataList(dataList);
  }


  public @Override DynamicsPage<JcxBuyOrderItemExportQueryPageListInfoRes> queryPageList(JcxBuyOrderItemExportQueryPageListReq req) {

    DynamicsPage<JcxBuyOrderItem> page = new DynamicsPage<>();
    page.setCurrent(req.getPageNum()).setSize(req.getPageSize());
    setQueryListHeader(page);
    MPJLambdaWrapper<JcxBuyOrderItem> q = getWrapper(req.getData());
    List<JcxBuyOrderItemExportQueryPageListInfoRes> records;
    if (Boolean.TRUE.equals(req.getQueryPage())) {
      IPage<JcxBuyOrderItem> list = this.page(page, q);
      IPage<JcxBuyOrderItemExportQueryPageListInfoRes> dataList = list.convert(t -> $.copy(t, JcxBuyOrderItemExportQueryPageListInfoRes.class));
      records = dataList.getRecords();
    } else {
      records = $.copyList(this.list(q), JcxBuyOrderItemExportQueryPageListInfoRes.class);
    }

    // 类型转换，  更换枚举 等操作

    List<JcxBuyOrderItemExportQueryPageListInfoRes> listInfoRes = $.copyList(records, JcxBuyOrderItemExportQueryPageListInfoRes.class);

    return DynamicsPage.init(page, listInfoRes);
  }


  @SetUserName
  public @Override void setName(List<? extends JcxBuyOrderItemDto> JcxBuyOrderItemDtoList) {

  }

  // 以下为私有对象封装


  private MPJLambdaWrapper<JcxBuyOrderItem> getWrapper(JcxBuyOrderItemDto obj) {
    MPJLambdaWrapper<JcxBuyOrderItem> q = new MPJLambdaWrapper<>();

    if (Objects.nonNull(obj)) {
      q
          .eq(Objects.nonNull(obj.getId()), JcxBuyOrderItem::getId, obj.getId())
          .eq(Objects.nonNull(obj.getOrderId()), JcxBuyOrderItem::getOrderId, obj.getOrderId())
          .eq(Objects.nonNull(obj.getGoodsId()), JcxBuyOrderItem::getGoodsId, obj.getGoodsId())
          .eq(Objects.nonNull(obj.getGoodsCostPrice()), JcxBuyOrderItem::getGoodsCostPrice, obj.getGoodsCostPrice())
          .eq(Objects.nonNull(obj.getGoodsBuyCount()), JcxBuyOrderItem::getGoodsBuyCount, obj.getGoodsBuyCount())
          .eq(StringUtils.isNoneBlank(obj.getGoodsUnit()), JcxBuyOrderItem::getGoodsUnit, obj.getGoodsUnit())
          .eq(Objects.nonNull(obj.getGoodsCostPriceTotal()), JcxBuyOrderItem::getGoodsCostPriceTotal, obj.getGoodsCostPriceTotal())
          .eq(StringUtils.isNoneBlank(obj.getGoodsBuyRemark()), JcxBuyOrderItem::getGoodsBuyRemark, obj.getGoodsBuyRemark())
          .eq(Objects.nonNull(obj.getTenantId()), JcxBuyOrderItem::getTenantId, obj.getTenantId())
          .eq(Objects.nonNull(obj.getCreateTime()), JcxBuyOrderItem::getCreateTime, obj.getCreateTime())
          .eq(Objects.nonNull(obj.getUpdateTime()), JcxBuyOrderItem::getUpdateTime, obj.getUpdateTime())
          .eq(Objects.nonNull(obj.getVersionNum()), JcxBuyOrderItem::getVersionNum, obj.getVersionNum())

      ;
    }
    q.orderByDesc(JcxBuyOrderItem::getId);
    return q;

  }

  private void setQueryListHeader(DynamicsPage<JcxBuyOrderItem> page) {

    ServiceComment.header(page, "JcxBuyOrderItemService#queryPageList");

  }


}

