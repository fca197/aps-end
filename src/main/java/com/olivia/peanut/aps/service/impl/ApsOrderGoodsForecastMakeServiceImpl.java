package com.olivia.peanut.aps.service.impl;

import cn.hutool.core.collection.CollUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.github.yulichang.base.MPJBaseServiceImpl;
import com.github.yulichang.wrapper.MPJLambdaWrapper;
import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.olivia.peanut.aps.api.entity.apsOrderGoodsForecastMake.*;
import com.olivia.peanut.aps.mapper.ApsOrderGoodsForecastMakeMapper;
import com.olivia.peanut.aps.model.ApsOrderGoodsForecastMake;
import com.olivia.peanut.aps.service.ApsOrderGoodsForecastMakeService;
import com.olivia.sdk.ann.SetUserName;
import com.olivia.sdk.comment.ServiceComment;
import com.olivia.sdk.utils.$;
import com.olivia.sdk.utils.DynamicsPage;
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
 * 订单商品节点预测表(ApsOrderGoodsForecastMake)表服务实现类
 *
 * @author peanut
 * @since 2024-06-02 23:11:41
 */
@Service("apsOrderGoodsForecastMakeService")
@Transactional
public class ApsOrderGoodsForecastMakeServiceImpl extends MPJBaseServiceImpl<ApsOrderGoodsForecastMakeMapper, ApsOrderGoodsForecastMake> implements
    ApsOrderGoodsForecastMakeService {

  final static Cache<String, Map<String, String>> cache = CacheBuilder.newBuilder().maximumSize(100).expireAfterWrite(30, TimeUnit.MINUTES).build();


  public @Override ApsOrderGoodsForecastMakeQueryListRes queryList(ApsOrderGoodsForecastMakeQueryListReq req) {

    MPJLambdaWrapper<ApsOrderGoodsForecastMake> q = getWrapper(req.getData());
    List<ApsOrderGoodsForecastMake> list = this.list(q);

    List<ApsOrderGoodsForecastMakeDto> dataList = list.stream().map(t -> $.copy(t, ApsOrderGoodsForecastMakeDto.class)).collect(Collectors.toList());
    //  this.setName(dataList);
    ((ApsOrderGoodsForecastMakeService) AopContext.currentProxy()).setName(dataList);

    return new ApsOrderGoodsForecastMakeQueryListRes().setDataList(dataList);
  }


  public @Override DynamicsPage<ApsOrderGoodsForecastMakeExportQueryPageListInfoRes> queryPageList(ApsOrderGoodsForecastMakeExportQueryPageListReq req) {

    DynamicsPage<ApsOrderGoodsForecastMake> page = new DynamicsPage<>();
    page.setCurrent(req.getPageNum()).setSize(req.getPageSize());
    setQueryListHeader(page);
    MPJLambdaWrapper<ApsOrderGoodsForecastMake> q = getWrapper(req.getData());
    List<ApsOrderGoodsForecastMakeExportQueryPageListInfoRes> records;
    if (Boolean.TRUE.equals(req.getQueryPage())) {
      IPage<ApsOrderGoodsForecastMake> list = this.page(page, q);
      IPage<ApsOrderGoodsForecastMakeExportQueryPageListInfoRes> dataList = list.convert(t -> $.copy(t, ApsOrderGoodsForecastMakeExportQueryPageListInfoRes.class));
      records = dataList.getRecords();
    } else {
      records = $.copyList(this.list(q), ApsOrderGoodsForecastMakeExportQueryPageListInfoRes.class);
    }

    // 类型转换，  更换枚举 等操作

    List<ApsOrderGoodsForecastMakeExportQueryPageListInfoRes> listInfoRes = $.copyList(records, ApsOrderGoodsForecastMakeExportQueryPageListInfoRes.class);
    // this.setName(listInfoRes);

    ((ApsOrderGoodsForecastMakeService) AopContext.currentProxy()).setName(listInfoRes);
    return DynamicsPage.init(page, listInfoRes);
  }

  // 以下为私有对象封装

  @SetUserName
  public @Override void setName(List<? extends ApsOrderGoodsForecastMakeDto> apsOrderGoodsForecastMakeDtoList) {

    if (CollUtil.isEmpty(apsOrderGoodsForecastMakeDtoList)) {
    }


  }


  private MPJLambdaWrapper<ApsOrderGoodsForecastMake> getWrapper(ApsOrderGoodsForecastMakeDto obj) {
    MPJLambdaWrapper<ApsOrderGoodsForecastMake> q = new MPJLambdaWrapper<>();

    if (Objects.nonNull(obj)) {
      q
          .eq(Objects.nonNull(obj.getOrderId()), ApsOrderGoodsForecastMake::getOrderId, obj.getOrderId())
          .eq(Objects.nonNull(obj.getGoodsId()), ApsOrderGoodsForecastMake::getGoodsId, obj.getGoodsId())
          .eq(Objects.nonNull(obj.getGoodsStatusId()), ApsOrderGoodsForecastMake::getGoodsStatusId, obj.getGoodsStatusId())
          .eq(StringUtils.isNoneBlank(obj.getGoodsStatusName()), ApsOrderGoodsForecastMake::getGoodsStatusName, obj.getGoodsStatusName())
          .eq(Objects.nonNull(obj.getForecastMakeDate()), ApsOrderGoodsForecastMake::getForecastMakeDate, obj.getForecastMakeDate())
          .eq(Objects.nonNull(obj.getFactoryId()), ApsOrderGoodsForecastMake::getFactoryId, obj.getFactoryId())

      ;
    }
    q.orderByDesc(ApsOrderGoodsForecastMake::getId);
    return q;

  }

  private void setQueryListHeader(DynamicsPage<ApsOrderGoodsForecastMake> page) {

    ServiceComment.header(page, "ApsOrderGoodsForecastMakeService#queryPageList");

  }


}

