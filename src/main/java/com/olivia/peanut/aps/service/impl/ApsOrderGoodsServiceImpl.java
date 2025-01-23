package com.olivia.peanut.aps.service.impl;

import cn.hutool.core.collection.CollUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.github.yulichang.base.MPJBaseServiceImpl;
import com.github.yulichang.wrapper.MPJLambdaWrapper;
import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.olivia.peanut.aps.api.entity.apsOrderGoods.*;
import com.olivia.peanut.aps.mapper.ApsOrderGoodsMapper;
import com.olivia.peanut.aps.model.ApsOrderGoods;
import com.olivia.peanut.aps.service.ApsOrderGoodsService;
import com.olivia.sdk.ann.SetUserName;
import com.olivia.sdk.comment.ServiceComment;
import com.olivia.sdk.utils.$;
import com.olivia.sdk.utils.DynamicsPage;
import lombok.NonNull;
import org.apache.commons.lang3.StringUtils;
import org.springframework.aop.framework.AopContext;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

/**
 * (ApsOrderGoods)表服务实现类
 *
 * @author peanut
 * @since 2024-04-09 13:19:36
 */
@Service("apsOrderGoodsService")
@Transactional
public class ApsOrderGoodsServiceImpl extends MPJBaseServiceImpl<ApsOrderGoodsMapper, ApsOrderGoods> implements ApsOrderGoodsService {

  final static Cache<String, Map<String, String>> cache = CacheBuilder.newBuilder().maximumSize(100).expireAfterWrite(30, TimeUnit.MINUTES).build();


  public @Override ApsOrderGoodsQueryListRes queryList(ApsOrderGoodsQueryListReq req) {

    MPJLambdaWrapper<ApsOrderGoods> q = getWrapper(req.getData());
    List<ApsOrderGoods> list = this.list(q);

    List<ApsOrderGoodsDto> dataList = list.stream().map(t -> $.copy(t, ApsOrderGoodsDto.class)).collect(Collectors.toList());
    //  this.setName(dataList);
    ((ApsOrderGoodsServiceImpl) AopContext.currentProxy()).setName(dataList);

    return new ApsOrderGoodsQueryListRes().setDataList(dataList);
  }

  // 以下为私有对象封装

  public @Override DynamicsPage<ApsOrderGoodsExportQueryPageListInfoRes> queryPageList(ApsOrderGoodsExportQueryPageListReq req) {

    DynamicsPage<ApsOrderGoods> page = new DynamicsPage<>();
    page.setCurrent(req.getPageNum()).setSize(req.getPageSize());
    setQueryListHeader(page);
    MPJLambdaWrapper<ApsOrderGoods> q = getWrapper(req.getData());
    List<ApsOrderGoodsExportQueryPageListInfoRes> records;
    if (Boolean.TRUE.equals(req.getQueryPage())) {
      IPage<ApsOrderGoods> list = this.page(page, q);
      IPage<ApsOrderGoodsExportQueryPageListInfoRes> dataList = list.convert(t -> $.copy(t, ApsOrderGoodsExportQueryPageListInfoRes.class));
      records = dataList.getRecords();
    } else {
      records = $.copyList(this.list(q), ApsOrderGoodsExportQueryPageListInfoRes.class);
    }

    // 类型转换，  更换枚举 等操作

    List<ApsOrderGoodsExportQueryPageListInfoRes> listInfoRes = $.copyList(records, ApsOrderGoodsExportQueryPageListInfoRes.class);
    // this.setName(listInfoRes);
    ((ApsOrderGoodsServiceImpl) AopContext.currentProxy()).setName(listInfoRes);

    return DynamicsPage.init(page, listInfoRes);
  }

  @SetUserName
  public @Override void setName(List<? extends ApsOrderGoodsDto> apsOrderGoodsDtoList) {

    if (CollUtil.isEmpty(apsOrderGoodsDtoList)) {
      return;
    }
  }

  @Override
  public List<ApsOrderGoods> getApsOrderGoodsByOrderId(@NonNull Long orderId) {
    return this.list(new LambdaQueryWrapper<ApsOrderGoods>().eq(ApsOrderGoods::getOrderId, orderId));
  }

  private MPJLambdaWrapper<ApsOrderGoods> getWrapper(ApsOrderGoodsDto obj) {
    MPJLambdaWrapper<ApsOrderGoods> q = new MPJLambdaWrapper<>();

    if (Objects.nonNull(obj)) {
      q
          .eq(Objects.nonNull(obj.getOrderId()), ApsOrderGoods::getOrderId, obj.getOrderId())
          .eq(Objects.nonNull(obj.getGoodsId()), ApsOrderGoods::getGoodsId, obj.getGoodsId())
          .eq(StringUtils.isNoneBlank(obj.getGoodsName()), ApsOrderGoods::getGoodsName, obj.getGoodsName())
          .eq(StringUtils.isNoneBlank(obj.getGoodsRemark()), ApsOrderGoods::getGoodsRemark, obj.getGoodsRemark())
          .eq(Objects.nonNull(obj.getGoodsAmount()), ApsOrderGoods::getGoodsAmount, obj.getGoodsAmount())
          .eq(Objects.nonNull(obj.getGoodsPrice()), ApsOrderGoods::getGoodsPrice, obj.getGoodsPrice())
          .eq(Objects.nonNull(obj.getGoodsTotalPrice()), ApsOrderGoods::getGoodsTotalPrice, obj.getGoodsTotalPrice())
          .eq(StringUtils.isNoneBlank(obj.getGoodsUnit()), ApsOrderGoods::getGoodsUnit, obj.getGoodsUnit())
          .eq(Objects.nonNull(obj.getGoodsUnitPrice()), ApsOrderGoods::getGoodsUnitPrice, obj.getGoodsUnitPrice())
          .eq(Objects.nonNull(obj.getGoodsUnitTotalPrice()), ApsOrderGoods::getGoodsUnitTotalPrice, obj.getGoodsUnitTotalPrice())
          .eq(Objects.nonNull(obj.getFactoryId()), ApsOrderGoods::getFactoryId, obj.getFactoryId())

      ;
    }
    q.orderByDesc(ApsOrderGoods::getId);
    return q;

  }

  private void setQueryListHeader(DynamicsPage<ApsOrderGoods> page) {

    ServiceComment.header(page, "ApsOrderGoodsService#queryPageList");

  }


}

