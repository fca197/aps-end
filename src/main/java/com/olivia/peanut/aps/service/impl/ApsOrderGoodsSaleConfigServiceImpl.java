package com.olivia.peanut.aps.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.github.yulichang.base.MPJBaseServiceImpl;
import com.github.yulichang.wrapper.MPJLambdaWrapper;
import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.olivia.peanut.aps.api.entity.apsOrderGoodsSaleConfig.*;
import com.olivia.peanut.aps.mapper.ApsOrderGoodsSaleConfigMapper;
import com.olivia.peanut.aps.model.ApsOrderGoodsSaleConfig;
import com.olivia.peanut.aps.service.ApsOrderGoodsSaleConfigService;
import com.olivia.sdk.ann.SetUserName;
import com.olivia.sdk.comment.ServiceComment;
import com.olivia.sdk.utils.$;
import com.olivia.sdk.utils.BigDecimalUtils;
import com.olivia.sdk.utils.DynamicsPage;
import org.springframework.aop.framework.AopContext;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

/**
 * (ApsOrderGoodsSaleConfig)表服务实现类
 *
 * @author peanut
 * @since 2024-04-09 13:19:38
 */
@Service("apsOrderGoodsSaleConfigService")
@Transactional
public class ApsOrderGoodsSaleConfigServiceImpl extends MPJBaseServiceImpl<ApsOrderGoodsSaleConfigMapper, ApsOrderGoodsSaleConfig> implements ApsOrderGoodsSaleConfigService {

  final static Cache<String, Map<String, String>> cache = CacheBuilder.newBuilder().maximumSize(100).expireAfterWrite(30, TimeUnit.MINUTES).build();


  public @Override ApsOrderGoodsSaleConfigQueryListRes queryList(ApsOrderGoodsSaleConfigQueryListReq req) {

    MPJLambdaWrapper<ApsOrderGoodsSaleConfig> q = getWrapper(req.getData());
    List<ApsOrderGoodsSaleConfig> list = this.list(q);

    List<ApsOrderGoodsSaleConfigDto> dataList = list.stream().map(t -> $.copy(t, ApsOrderGoodsSaleConfigDto.class)).collect(Collectors.toList());
    //  this.setName(dataList);

    ((ApsOrderGoodsSaleConfigServiceImpl) AopContext.currentProxy()).setName(dataList);
    return new ApsOrderGoodsSaleConfigQueryListRes().setDataList(dataList);
  }


  public @Override DynamicsPage<ApsOrderGoodsSaleConfigExportQueryPageListInfoRes> queryPageList(ApsOrderGoodsSaleConfigExportQueryPageListReq req) {

    DynamicsPage<ApsOrderGoodsSaleConfig> page = new DynamicsPage<>();
    page.setCurrent(req.getPageNum()).setSize(req.getPageSize());
    setQueryListHeader(page);
    MPJLambdaWrapper<ApsOrderGoodsSaleConfig> q = getWrapper(req.getData());
    List<ApsOrderGoodsSaleConfigExportQueryPageListInfoRes> records;
    if (Boolean.TRUE.equals(req.getQueryPage())) {
      IPage<ApsOrderGoodsSaleConfig> list = this.page(page, q);
      IPage<ApsOrderGoodsSaleConfigExportQueryPageListInfoRes> dataList = list.convert(t -> $.copy(t, ApsOrderGoodsSaleConfigExportQueryPageListInfoRes.class));
      records = dataList.getRecords();
    } else {
      records = $.copyList(this.list(q), ApsOrderGoodsSaleConfigExportQueryPageListInfoRes.class);
    }

    // 类型转换，  更换枚举 等操作

    List<ApsOrderGoodsSaleConfigExportQueryPageListInfoRes> listInfoRes = $.copyList(records, ApsOrderGoodsSaleConfigExportQueryPageListInfoRes.class);
    ((ApsOrderGoodsSaleConfigServiceImpl) AopContext.currentProxy()).setName(listInfoRes);
    return DynamicsPage.init(page, listInfoRes);
  }

  // 以下为私有对象封装

  @SetUserName
  public @Override void setName(List<? extends ApsOrderGoodsSaleConfigDto> list) {
    BigDecimalUtils.valueExpand(list, 100, BigDecimalUtils.MatchType.likeLeft, "monthRatio");


  }


  private MPJLambdaWrapper<ApsOrderGoodsSaleConfig> getWrapper(ApsOrderGoodsSaleConfigDto obj) {
    MPJLambdaWrapper<ApsOrderGoodsSaleConfig> q = new MPJLambdaWrapper<>();

    if (Objects.nonNull(obj)) {
      q
          .eq(Objects.nonNull(obj.getOrderId()), ApsOrderGoodsSaleConfig::getOrderId, obj.getOrderId())
          .eq(Objects.nonNull(obj.getGoodsId()), ApsOrderGoodsSaleConfig::getGoodsId, obj.getGoodsId())
          .eq(Objects.nonNull(obj.getFactoryId()), ApsOrderGoodsSaleConfig::getFactoryId, obj.getFactoryId())

      ;
    }
    q.orderByDesc(ApsOrderGoodsSaleConfig::getId);
    return q;

  }

  private void setQueryListHeader(DynamicsPage<ApsOrderGoodsSaleConfig> page) {

    ServiceComment.header(page, "ApsOrderGoodsSaleConfigService#queryPageList");

  }


}

