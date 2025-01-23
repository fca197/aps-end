package com.olivia.peanut.aps.service.impl;

import cn.hutool.core.collection.CollUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.github.yulichang.base.MPJBaseServiceImpl;
import com.github.yulichang.wrapper.MPJLambdaWrapper;
import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.olivia.peanut.aps.api.entity.apsOrderGoodsProjectConfig.*;
import com.olivia.peanut.aps.mapper.ApsOrderGoodsProjectConfigMapper;
import com.olivia.peanut.aps.model.ApsOrderGoodsProjectConfig;
import com.olivia.peanut.aps.service.ApsOrderGoodsProjectConfigService;
import com.olivia.sdk.ann.SetUserName;
import com.olivia.sdk.comment.ServiceComment;
import com.olivia.sdk.utils.$;
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
 * (ApsOrderGoodsProjectConfig)表服务实现类
 *
 * @author peanut
 * @since 2024-04-09 13:19:37
 */
@Service("apsOrderGoodsProjectConfigService")
@Transactional
public class ApsOrderGoodsProjectConfigServiceImpl extends MPJBaseServiceImpl<ApsOrderGoodsProjectConfigMapper, ApsOrderGoodsProjectConfig> implements
    ApsOrderGoodsProjectConfigService {

  final static Cache<String, Map<String, String>> cache = CacheBuilder.newBuilder().maximumSize(100).expireAfterWrite(30, TimeUnit.MINUTES).build();


  public @Override ApsOrderGoodsProjectConfigQueryListRes queryList(ApsOrderGoodsProjectConfigQueryListReq req) {

    MPJLambdaWrapper<ApsOrderGoodsProjectConfig> q = getWrapper(req.getData());
    List<ApsOrderGoodsProjectConfig> list = this.list(q);

    List<ApsOrderGoodsProjectConfigDto> dataList = list.stream().map(t -> $.copy(t, ApsOrderGoodsProjectConfigDto.class)).collect(Collectors.toList());
    //  this.setName(dataList);

    ((ApsOrderGoodsProjectConfigService) AopContext.currentProxy()).setName(dataList);
    return new ApsOrderGoodsProjectConfigQueryListRes().setDataList(dataList);
  }


  public @Override DynamicsPage<ApsOrderGoodsProjectConfigExportQueryPageListInfoRes> queryPageList(ApsOrderGoodsProjectConfigExportQueryPageListReq req) {

    DynamicsPage<ApsOrderGoodsProjectConfig> page = new DynamicsPage<>();
    page.setCurrent(req.getPageNum()).setSize(req.getPageSize());
    setQueryListHeader(page);
    MPJLambdaWrapper<ApsOrderGoodsProjectConfig> q = getWrapper(req.getData());
    List<ApsOrderGoodsProjectConfigExportQueryPageListInfoRes> records;
    if (Boolean.TRUE.equals(req.getQueryPage())) {
      IPage<ApsOrderGoodsProjectConfig> list = this.page(page, q);
      IPage<ApsOrderGoodsProjectConfigExportQueryPageListInfoRes> dataList = list.convert(t -> $.copy(t, ApsOrderGoodsProjectConfigExportQueryPageListInfoRes.class));
      records = dataList.getRecords();
    } else {
      records = $.copyList(this.list(q), ApsOrderGoodsProjectConfigExportQueryPageListInfoRes.class);
    }

    // 类型转换，  更换枚举 等操作

    List<ApsOrderGoodsProjectConfigExportQueryPageListInfoRes> listInfoRes = $.copyList(records, ApsOrderGoodsProjectConfigExportQueryPageListInfoRes.class);
    // this.setName(listInfoRes);

    ((ApsOrderGoodsProjectConfigService) AopContext.currentProxy()).setName(listInfoRes);

    return DynamicsPage.init(page, listInfoRes);
  }

  // 以下为私有对象封装

  @SetUserName
  public @Override void setName(List<? extends ApsOrderGoodsProjectConfigDto> apsOrderGoodsProjectConfigDtoList) {

    if (CollUtil.isEmpty(apsOrderGoodsProjectConfigDtoList)) {
    }


  }


  private MPJLambdaWrapper<ApsOrderGoodsProjectConfig> getWrapper(ApsOrderGoodsProjectConfigDto obj) {
    MPJLambdaWrapper<ApsOrderGoodsProjectConfig> q = new MPJLambdaWrapper<>();

    if (Objects.nonNull(obj)) {
      q
          .eq(Objects.nonNull(obj.getOrderId()), ApsOrderGoodsProjectConfig::getOrderId, obj.getOrderId())
          .eq(Objects.nonNull(obj.getGoodsId()), ApsOrderGoodsProjectConfig::getGoodsId, obj.getGoodsId())
          .eq(Objects.nonNull(obj.getFactoryId()), ApsOrderGoodsProjectConfig::getFactoryId, obj.getFactoryId())

      ;
    }
    q.orderByDesc(ApsOrderGoodsProjectConfig::getId);
    return q;

  }

  private void setQueryListHeader(DynamicsPage<ApsOrderGoodsProjectConfig> page) {

    ServiceComment.header(page, "ApsOrderGoodsProjectConfigService#queryPageList");

  }


}

