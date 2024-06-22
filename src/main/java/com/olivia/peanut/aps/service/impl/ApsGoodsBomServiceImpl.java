package com.olivia.peanut.aps.service.impl;

import cn.hutool.core.collection.CollUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.github.yulichang.base.MPJBaseServiceImpl;
import com.github.yulichang.wrapper.MPJLambdaWrapper;
import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.olivia.peanut.aps.api.entity.apsGoodsBom.*;
import com.olivia.peanut.aps.mapper.ApsGoodsBomMapper;
import com.olivia.peanut.aps.model.ApsGoods;
import com.olivia.peanut.aps.model.ApsGoodsBom;
import com.olivia.peanut.aps.model.ApsWorkshopStation;
import com.olivia.peanut.aps.service.ApsGoodsBomService;
import com.olivia.peanut.aps.service.ApsGoodsService;
import com.olivia.peanut.aps.service.ApsWorkshopStationService;
import com.olivia.peanut.portal.model.Factory;
import com.olivia.peanut.portal.service.FactoryService;
import com.olivia.sdk.comment.ServiceComment;
import com.olivia.sdk.utils.$;
import com.olivia.sdk.utils.BaseEntity;
import com.olivia.sdk.utils.DynamicsPage;
import jakarta.annotation.Resource;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * (ApsGoodsBom)表服务实现类
 *
 * @author peanut
 * @since 2024-04-03 22:28:56
 */
@Service("apsGoodsBomService")
@Transactional
public class ApsGoodsBomServiceImpl extends MPJBaseServiceImpl<ApsGoodsBomMapper, ApsGoodsBom> implements ApsGoodsBomService {

  final static Cache<String, Map<String, String>> cache = CacheBuilder.newBuilder().maximumSize(100).expireAfterWrite(30, TimeUnit.MINUTES).build();

  @Resource
  ApsGoodsService apsGoodsService;
  @Resource
  FactoryService factoryService;
  @Resource
  ApsWorkshopStationService apsWorkshopStationService;

  public @Override ApsGoodsBomQueryListRes queryList(ApsGoodsBomQueryListReq req) {

    MPJLambdaWrapper<ApsGoodsBom> q = getWrapper(req.getData());
    List<ApsGoodsBom> list = this.list(q);

    List<ApsGoodsBomDto> dataList = list.stream().map(t -> $.copy(t, ApsGoodsBomDto.class)).collect(Collectors.toList());

    this.setName(dataList);
    return new ApsGoodsBomQueryListRes().setDataList(dataList);
  }

  public @Override DynamicsPage<ApsGoodsBomExportQueryPageListInfoRes> queryPageList(ApsGoodsBomExportQueryPageListReq req) {

    DynamicsPage<ApsGoodsBom> page = new DynamicsPage<>();
    page.setCurrent(req.getPageNum()).setSize(req.getPageSize());
    setQueryListHeader(page);
    MPJLambdaWrapper<ApsGoodsBom> q = getWrapper(req.getData());
    List<ApsGoodsBomExportQueryPageListInfoRes> records;
    if (Boolean.TRUE.equals(req.getQueryPage())) {
      IPage<ApsGoodsBom> list = this.page(page, q);
      IPage<ApsGoodsBomExportQueryPageListInfoRes> dataList = list.convert(t -> $.copy(t, ApsGoodsBomExportQueryPageListInfoRes.class));
      records = dataList.getRecords();
    } else {
      records = $.copyList(this.list(q), ApsGoodsBomExportQueryPageListInfoRes.class);
    }

    // 类型转换，  更换枚举 等操作

    List<ApsGoodsBomExportQueryPageListInfoRes> listInfoRes = $.copyList(records, ApsGoodsBomExportQueryPageListInfoRes.class);
    this.setName(listInfoRes);
    return DynamicsPage.init(page, listInfoRes);
  }

  public @Override void setName(List<? extends ApsGoodsBomDto> apsGoodsBomDtoList) {
    if (CollUtil.isEmpty(apsGoodsBomDtoList)) {
      return;
    }

    Set<Long> fidSet = apsGoodsBomDtoList.stream().map(ApsGoodsBomDto::getFactoryId).collect(Collectors.toSet());
    Set<Long> gidSet = apsGoodsBomDtoList.stream().map(ApsGoodsBomDto::getGoodsId).collect(Collectors.toSet());
    Set<Long> widSet = apsGoodsBomDtoList.stream().map(ApsGoodsBomDto::getBomUseWorkStation).collect(Collectors.toSet());
    Map<Long, String> wNMap = apsWorkshopStationService.listByIds(widSet).stream().collect(Collectors.toMap(BaseEntity::getId, ApsWorkshopStation::getStationName));
    Map<Long, String> fNMap = this.apsGoodsService.listByIds(gidSet).stream().collect(Collectors.toMap(BaseEntity::getId, ApsGoods::getGoodsName));
    Map<Long, String> gNMap = this.factoryService.listByIds(fidSet).stream().collect(Collectors.toMap(BaseEntity::getId, Factory::getFactoryName));
    apsGoodsBomDtoList.forEach(
        t -> t.setGoodsName(fNMap.get(t.getGoodsId())).setFactoryName(gNMap.get(t.getFactoryId())).setBomUseWorkStationName(wNMap.get(t.getBomUseWorkStation())));
  }

  // 以下为私有对象封装


  private MPJLambdaWrapper<ApsGoodsBom> getWrapper(ApsGoodsBomDto obj) {
    MPJLambdaWrapper<ApsGoodsBom> q = new MPJLambdaWrapper<>();

    if (Objects.nonNull(obj)) {
      q
          .eq(Objects.nonNull(obj.getGoodsId()), ApsGoodsBom::getGoodsId, obj.getGoodsId())
          .eq(StringUtils.isNoneBlank(obj.getBomCode()), ApsGoodsBom::getBomCode, obj.getBomCode())
          .likeRight(StringUtils.isNoneBlank(obj.getBomName()), ApsGoodsBom::getBomName, obj.getBomName())
          .eq(Objects.nonNull(obj.getBomUsage()), ApsGoodsBom::getBomUsage, obj.getBomUsage())
          .eq(StringUtils.isNoneBlank(obj.getBomUnit()), ApsGoodsBom::getBomUnit, obj.getBomUnit())
          .eq(Objects.nonNull(obj.getBomCostPrice()), ApsGoodsBom::getBomCostPrice, obj.getBomCostPrice())
          .eq(Objects.nonNull(obj.getBomCostPriceUnit()), ApsGoodsBom::getBomCostPriceUnit, obj.getBomCostPriceUnit())
          .eq(Objects.nonNull(obj.getBomUseWorkStation()), ApsGoodsBom::getBomUseWorkStation, obj.getBomUseWorkStation())
          .eq(StringUtils.isNoneBlank(obj.getBomUseExpression()), ApsGoodsBom::getBomUseExpression, obj.getBomUseExpression())
          .eq(Objects.nonNull(obj.getFactoryId()), ApsGoodsBom::getFactoryId, obj.getFactoryId())

      ;
    }
    q.orderByDesc(ApsGoodsBom::getId);
    return q;

  }

  private void setQueryListHeader(DynamicsPage<ApsGoodsBom> page) {

    ServiceComment.header(page, "ApsGoodsBomService#queryPageList");

  }


}

