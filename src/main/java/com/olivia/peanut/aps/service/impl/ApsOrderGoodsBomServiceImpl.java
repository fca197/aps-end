package com.olivia.peanut.aps.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.github.yulichang.base.MPJBaseServiceImpl;
import com.github.yulichang.wrapper.MPJLambdaWrapper;
import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.olivia.peanut.aps.api.entity.apsOrderGoodsBom.*;
import com.olivia.peanut.aps.mapper.ApsOrderGoodsBomMapper;
import com.olivia.peanut.aps.model.ApsOrderGoodsBom;
import com.olivia.peanut.aps.service.ApsOrderGoodsBomService;
import com.olivia.peanut.base.service.BaseTableHeaderService;
import com.olivia.sdk.service.SetNameService;
import com.olivia.sdk.utils.$;
import com.olivia.sdk.utils.DynamicsPage;
import jakarta.annotation.Resource;
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
 * 订单商品零件表(ApsOrderGoodsBom)表服务实现类
 *
 * @author peanut
 * @since 2024-07-30 10:28:23
 */
@Service("apsOrderGoodsBomService")
@Transactional
public class ApsOrderGoodsBomServiceImpl extends MPJBaseServiceImpl<ApsOrderGoodsBomMapper, ApsOrderGoodsBom> implements ApsOrderGoodsBomService {

  final static Cache<String, Map<String, String>> cache = CacheBuilder.newBuilder().maximumSize(100).expireAfterWrite(30, TimeUnit.MINUTES).build();

  @Resource
  BaseTableHeaderService tableHeaderService;
  @Resource
  SetNameService setNameService;


  public @Override ApsOrderGoodsBomQueryListRes queryList(ApsOrderGoodsBomQueryListReq req) {

    MPJLambdaWrapper<ApsOrderGoodsBom> q = getWrapper(req.getData());
    List<ApsOrderGoodsBom> list = this.list(q);

    List<ApsOrderGoodsBomDto> dataList = list.stream().map(t -> $.copy(t, ApsOrderGoodsBomDto.class)).collect(Collectors.toList());
    ((ApsOrderGoodsBomService) AopContext.currentProxy()).setName(dataList);
    return new ApsOrderGoodsBomQueryListRes().setDataList(dataList);
  }


  public @Override DynamicsPage<ApsOrderGoodsBomExportQueryPageListInfoRes> queryPageList(ApsOrderGoodsBomExportQueryPageListReq req) {

    DynamicsPage<ApsOrderGoodsBom> page = new DynamicsPage<>();
    page.setCurrent(req.getPageNum()).setSize(req.getPageSize());
    setQueryListHeader(page);
    MPJLambdaWrapper<ApsOrderGoodsBom> q = getWrapper(req.getData());
    List<ApsOrderGoodsBomExportQueryPageListInfoRes> records;
    if (Boolean.TRUE.equals(req.getQueryPage())) {
      IPage<ApsOrderGoodsBom> list = this.page(page, q);
      IPage<ApsOrderGoodsBomExportQueryPageListInfoRes> dataList = list.convert(t -> $.copy(t, ApsOrderGoodsBomExportQueryPageListInfoRes.class));
      records = dataList.getRecords();
    } else {
      records = $.copyList(this.list(q), ApsOrderGoodsBomExportQueryPageListInfoRes.class);
    }

    // 类型转换，  更换枚举 等操作

    List<ApsOrderGoodsBomExportQueryPageListInfoRes> listInfoRes = $.copyList(records, ApsOrderGoodsBomExportQueryPageListInfoRes.class);
    ((ApsOrderGoodsBomService) AopContext.currentProxy()).setName(listInfoRes);

    return DynamicsPage.init(page, listInfoRes);
  }

  // 以下为私有对象封装

  public @Override void setName(List<? extends ApsOrderGoodsBomDto> list) {

    //   setNameService.setName(list, SetNamePojoUtils.FACTORY, SetNamePojoUtils.OP_USER_NAME);

  }


  private MPJLambdaWrapper<ApsOrderGoodsBom> getWrapper(ApsOrderGoodsBomDto obj) {
    MPJLambdaWrapper<ApsOrderGoodsBom> q = new MPJLambdaWrapper<>();

    if (Objects.nonNull(obj)) {
      q
          .eq(Objects.nonNull(obj.getOrderId()), ApsOrderGoodsBom::getOrderId, obj.getOrderId())
          .eq(Objects.nonNull(obj.getGoodsId()), ApsOrderGoodsBom::getGoodsId, obj.getGoodsId())
          .eq(Objects.nonNull(obj.getGoodsStatusId()), ApsOrderGoodsBom::getGoodsStatusId, obj.getGoodsStatusId())
          .eq(Objects.nonNull(obj.getBomId()), ApsOrderGoodsBom::getBomId, obj.getBomId())
          .eq(StringUtils.isNoneBlank(obj.getBomCode()), ApsOrderGoodsBom::getBomCode, obj.getBomCode())
          .eq(StringUtils.isNoneBlank(obj.getBomName()), ApsOrderGoodsBom::getBomName, obj.getBomName())
          .eq(Objects.nonNull(obj.getBomUsage()), ApsOrderGoodsBom::getBomUsage, obj.getBomUsage())
          .eq(StringUtils.isNoneBlank(obj.getBomUnit()), ApsOrderGoodsBom::getBomUnit, obj.getBomUnit())
          .eq(Objects.nonNull(obj.getBomCostPrice()), ApsOrderGoodsBom::getBomCostPrice, obj.getBomCostPrice())
          .eq(StringUtils.isNoneBlank(obj.getBomCostPriceUnit()), ApsOrderGoodsBom::getBomCostPriceUnit, obj.getBomCostPriceUnit())
          .eq(Objects.nonNull(obj.getBomUseWorkStation()), ApsOrderGoodsBom::getBomUseWorkStation, obj.getBomUseWorkStation())
          .eq(Objects.nonNull(obj.getBomUseDate()), ApsOrderGoodsBom::getBomUseDate, obj.getBomUseDate())
          .eq(Objects.nonNull(obj.getFactoryId()), ApsOrderGoodsBom::getFactoryId, obj.getFactoryId())

      ;
    }
    q.orderByDesc(ApsOrderGoodsBom::getId);
    return q;

  }

  private void setQueryListHeader(DynamicsPage<ApsOrderGoodsBom> page) {

    tableHeaderService.listByBizKey(page, "ApsOrderGoodsBomService#queryPageList");

  }


}

