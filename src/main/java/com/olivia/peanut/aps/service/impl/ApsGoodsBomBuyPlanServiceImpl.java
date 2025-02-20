package com.olivia.peanut.aps.service.impl;

import cn.hutool.core.collection.CollUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.github.yulichang.base.MPJBaseServiceImpl;
import com.github.yulichang.wrapper.MPJLambdaWrapper;
import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.olivia.peanut.aps.api.entity.apsGoodsBomBuyPlan.*;
import com.olivia.peanut.aps.mapper.ApsGoodsBomBuyPlanMapper;
import com.olivia.peanut.aps.model.ApsGoodsBomBuyPlan;
import com.olivia.peanut.aps.service.ApsGoodsBomBuyPlanService;
import com.olivia.sdk.ann.SetUserName;
import com.olivia.sdk.comment.ServiceComment;
import com.olivia.sdk.utils.$;
import com.olivia.sdk.utils.DynamicsPage;
import com.olivia.sdk.utils.LambdaQueryUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

import static com.olivia.sdk.utils.Str.UN_CHECKED;

/**
 * BOM 购买计划(ApsGoodsBomBuyPlan)表服务实现类
 *
 * @author peanut
 * @since 2024-06-05 14:35:30
 */
@Service("apsGoodsBomBuyPlanService")
@Transactional
public class ApsGoodsBomBuyPlanServiceImpl extends MPJBaseServiceImpl<ApsGoodsBomBuyPlanMapper, ApsGoodsBomBuyPlan> implements ApsGoodsBomBuyPlanService {

  final static Cache<String, Map<String, String>> cache = CacheBuilder.newBuilder().maximumSize(100).expireAfterWrite(30, TimeUnit.MINUTES).build();


  public @Override ApsGoodsBomBuyPlanQueryListRes queryList(ApsGoodsBomBuyPlanQueryListReq req) {

    MPJLambdaWrapper<ApsGoodsBomBuyPlan> q = getWrapper(req.getData());
    List<ApsGoodsBomBuyPlan> list = this.list(q);

    List<ApsGoodsBomBuyPlanDto> dataList = list.stream().map(t -> $.copy(t, ApsGoodsBomBuyPlanDto.class)).collect(Collectors.toList());
    //  this.setName(dataList);

    return new ApsGoodsBomBuyPlanQueryListRes().setDataList(dataList);
  }


  public @Override DynamicsPage<ApsGoodsBomBuyPlanExportQueryPageListInfoRes> queryPageList(ApsGoodsBomBuyPlanExportQueryPageListReq req) {

    DynamicsPage<ApsGoodsBomBuyPlan> page = new DynamicsPage<>();
    page.setCurrent(req.getPageNum()).setSize(req.getPageSize());
    setQueryListHeader(page);
    MPJLambdaWrapper<ApsGoodsBomBuyPlan> q = getWrapper(req.getData());
    List<ApsGoodsBomBuyPlanExportQueryPageListInfoRes> records;
    if (Boolean.TRUE.equals(req.getQueryPage())) {
      IPage<ApsGoodsBomBuyPlan> list = this.page(page, q);
      IPage<ApsGoodsBomBuyPlanExportQueryPageListInfoRes> dataList = list.convert(t -> $.copy(t, ApsGoodsBomBuyPlanExportQueryPageListInfoRes.class));
      records = dataList.getRecords();
    } else {
      records = $.copyList(this.list(q), ApsGoodsBomBuyPlanExportQueryPageListInfoRes.class);
    }

    // 类型转换，  更换枚举 等操作

    List<ApsGoodsBomBuyPlanExportQueryPageListInfoRes> listInfoRes = $.copyList(records, ApsGoodsBomBuyPlanExportQueryPageListInfoRes.class);
    // this.setName(listInfoRes);
    return DynamicsPage.init(page, listInfoRes);
  }

  // 以下为私有对象封装

  @SetUserName
  public @Override void setName(List<? extends ApsGoodsBomBuyPlanDto> apsGoodsBomBuyPlanDtoList) {

    if (CollUtil.isEmpty(apsGoodsBomBuyPlanDtoList)) {
    }


  }


  @SuppressWarnings(UN_CHECKED)
  private MPJLambdaWrapper<ApsGoodsBomBuyPlan> getWrapper(ApsGoodsBomBuyPlanDto obj) {
    MPJLambdaWrapper<ApsGoodsBomBuyPlan> q = new MPJLambdaWrapper<>();
    LambdaQueryUtil.lambdaQueryWrapper(q, obj, ApsGoodsBomBuyPlan.class, ApsGoodsBomBuyPlan::getPlanName, ApsGoodsBomBuyPlan::getPlanSource);
    q.orderByDesc(ApsGoodsBomBuyPlan::getId);
    return q;

  }

  private void setQueryListHeader(DynamicsPage<ApsGoodsBomBuyPlan> page) {

    ServiceComment.header(page, "ApsGoodsBomBuyPlanService#queryPageList");

  }


}

