package com.olivia.peanut.aps.service.impl;

import cn.hutool.core.collection.CollUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.github.yulichang.base.MPJBaseServiceImpl;
import com.github.yulichang.wrapper.MPJLambdaWrapper;
import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.olivia.peanut.aps.api.entity.apsSchedulingGoodsBom.*;
import com.olivia.peanut.aps.mapper.ApsSchedulingGoodsBomMapper;
import com.olivia.peanut.aps.model.ApsSchedulingGoodsBom;
import com.olivia.peanut.aps.service.ApsSchedulingGoodsBomService;
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
import org.springframework.aop.framework.AopContext;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 订单商品零件表(ApsSchedulingGoodsBom)表服务实现类
 *
 * @author peanut
 * @since 2024-06-02 21:50:25
 */
@Service("apsSchedulingGoodsBomService")
@Transactional
public class ApsSchedulingGoodsBomServiceImpl extends MPJBaseServiceImpl<ApsSchedulingGoodsBomMapper, ApsSchedulingGoodsBom> implements ApsSchedulingGoodsBomService {

  final static Cache<String, Map<String, String>> cache = CacheBuilder.newBuilder().maximumSize(100).expireAfterWrite(30, TimeUnit.MINUTES).build();


  public @Override ApsSchedulingGoodsBomQueryListRes queryList(ApsSchedulingGoodsBomQueryListReq req) {

    MPJLambdaWrapper<ApsSchedulingGoodsBom> q = getWrapper(req.getData());
    List<ApsSchedulingGoodsBom> list = this.list(q);

    List<ApsSchedulingGoodsBomDto> dataList = list.stream().map(t -> $.copy(t, ApsSchedulingGoodsBomDto.class)).collect(Collectors.toList());
    //  this.setName(dataList);
    ((ApsSchedulingGoodsBomServiceImpl) AopContext.currentProxy()).setName(dataList);

    return new ApsSchedulingGoodsBomQueryListRes().setDataList(dataList);
  }


  public @Override DynamicsPage<ApsSchedulingGoodsBomExportQueryPageListInfoRes> queryPageList(ApsSchedulingGoodsBomExportQueryPageListReq req) {

    DynamicsPage<ApsSchedulingGoodsBom> page = new DynamicsPage<>();
    page.setCurrent(req.getPageNum()).setSize(req.getPageSize());
    setQueryListHeader(page);
    MPJLambdaWrapper<ApsSchedulingGoodsBom> q = getWrapper(req.getData());
    List<ApsSchedulingGoodsBomExportQueryPageListInfoRes> records;
    if (Boolean.TRUE.equals(req.getQueryPage())) {
      IPage<ApsSchedulingGoodsBom> list = this.page(page, q);
      IPage<ApsSchedulingGoodsBomExportQueryPageListInfoRes> dataList = list.convert(t -> $.copy(t, ApsSchedulingGoodsBomExportQueryPageListInfoRes.class));
      records = dataList.getRecords();
    } else {
      records = $.copyList(this.list(q), ApsSchedulingGoodsBomExportQueryPageListInfoRes.class);
    }

    // 类型转换，  更换枚举 等操作

    List<ApsSchedulingGoodsBomExportQueryPageListInfoRes> listInfoRes = $.copyList(records, ApsSchedulingGoodsBomExportQueryPageListInfoRes.class);
    // this.setName(listInfoRes);
    ((ApsSchedulingGoodsBomServiceImpl) AopContext.currentProxy()).setName(listInfoRes);

    return DynamicsPage.init(page, listInfoRes);
  }

  // 以下为私有对象封装

  @SetUserName
  public @Override void setName(List<? extends ApsSchedulingGoodsBomDto> apsSchedulingGoodsBomDtoList) {

    if (CollUtil.isEmpty(apsSchedulingGoodsBomDtoList)) {
    }


  }


  private MPJLambdaWrapper<ApsSchedulingGoodsBom> getWrapper(ApsSchedulingGoodsBomDto obj) {
    MPJLambdaWrapper<ApsSchedulingGoodsBom> q = new MPJLambdaWrapper<>();

    if (Objects.nonNull(obj)) {
      q
          .eq(Objects.nonNull(obj.getSchedulingId()), ApsSchedulingGoodsBom::getSchedulingId, obj.getSchedulingId())
          .eq(Objects.nonNull(obj.getGoodsId()), ApsSchedulingGoodsBom::getGoodsId, obj.getGoodsId())
          .eq(Objects.nonNull(obj.getGoodsStatusId()), ApsSchedulingGoodsBom::getGoodsStatusId, obj.getGoodsStatusId())
          .eq(Objects.nonNull(obj.getBomId()), ApsSchedulingGoodsBom::getBomId, obj.getBomId())
          .eq(StringUtils.isNoneBlank(obj.getBomCode()), ApsSchedulingGoodsBom::getBomCode, obj.getBomCode())
          .eq(StringUtils.isNoneBlank(obj.getBomName()), ApsSchedulingGoodsBom::getBomName, obj.getBomName())
          .eq(Objects.nonNull(obj.getBomUsage()), ApsSchedulingGoodsBom::getBomUsage, obj.getBomUsage())
          .eq(StringUtils.isNoneBlank(obj.getBomUnit()), ApsSchedulingGoodsBom::getBomUnit, obj.getBomUnit())
          .eq(Objects.nonNull(obj.getBomCostPrice()), ApsSchedulingGoodsBom::getBomCostPrice, obj.getBomCostPrice())
          .eq(StringUtils.isNoneBlank(obj.getBomCostPriceUnit()), ApsSchedulingGoodsBom::getBomCostPriceUnit, obj.getBomCostPriceUnit())
          .eq(Objects.nonNull(obj.getBomUseWorkStation()), ApsSchedulingGoodsBom::getBomUseWorkStation, obj.getBomUseWorkStation())
          .eq(Objects.nonNull(obj.getBomUseDate()), ApsSchedulingGoodsBom::getBomUseDate, obj.getBomUseDate())
          .eq(Objects.nonNull(obj.getFactoryId()), ApsSchedulingGoodsBom::getFactoryId, obj.getFactoryId())

      ;
    }
    q.orderByDesc(ApsSchedulingGoodsBom::getId);
    return q;

  }

  private void setQueryListHeader(DynamicsPage<ApsSchedulingGoodsBom> page) {

    ServiceComment.header(page, "ApsSchedulingGoodsBomService#queryPageList");

  }


}

