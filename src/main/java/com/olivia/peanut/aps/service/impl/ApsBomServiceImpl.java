package com.olivia.peanut.aps.service.impl;

import cn.hutool.core.collection.CollUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.github.yulichang.base.MPJBaseServiceImpl;
import com.github.yulichang.wrapper.MPJLambdaWrapper;
import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.olivia.peanut.aps.api.entity.apsBom.*;
import com.olivia.peanut.aps.mapper.ApsBomMapper;
import com.olivia.peanut.aps.model.ApsBom;
import com.olivia.peanut.aps.service.ApsBomService;
import com.olivia.peanut.portal.service.BaseTableHeaderService;
import com.olivia.sdk.utils.$;
import com.olivia.sdk.utils.DynamicsPage;
import jakarta.annotation.Resource;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * BOM 清单(ApsBom)表服务实现类
 *
 * @author peanut
 * @since 2024-06-06 11:27:34
 */
@Service("apsBomService")
@Transactional
public class ApsBomServiceImpl extends MPJBaseServiceImpl<ApsBomMapper, ApsBom> implements ApsBomService {

  final static Cache<String, Map<String, String>> cache = CacheBuilder.newBuilder().maximumSize(100).expireAfterWrite(30, TimeUnit.MINUTES).build();

  @Resource
  BaseTableHeaderService tableHeaderService;


  public @Override ApsBomQueryListRes queryList(ApsBomQueryListReq req) {

    MPJLambdaWrapper<ApsBom> q = getWrapper(req.getData());
    List<ApsBom> list = this.list(q);

    List<ApsBomDto> dataList = list.stream().map(t -> $.copy(t, ApsBomDto.class)).collect(Collectors.toList());
    this.setName(dataList);
    return new ApsBomQueryListRes().setDataList(dataList);
  }


  public @Override DynamicsPage<ApsBomExportQueryPageListInfoRes> queryPageList(ApsBomExportQueryPageListReq req) {

    DynamicsPage<ApsBom> page = new DynamicsPage<>();
    page.setCurrent(req.getPageNum()).setSize(req.getPageSize());
    setQueryListHeader(page);
    MPJLambdaWrapper<ApsBom> q = getWrapper(req.getData());
    List<ApsBomExportQueryPageListInfoRes> records;
    if (Boolean.TRUE.equals(req.getQueryPage())) {
      IPage<ApsBom> list = this.page(page, q);
      IPage<ApsBomExportQueryPageListInfoRes> dataList = list.convert(t -> $.copy(t, ApsBomExportQueryPageListInfoRes.class));
      records = dataList.getRecords();
    } else {
      records = $.copyList(this.list(q), ApsBomExportQueryPageListInfoRes.class);
    }

    // 类型转换，  更换枚举 等操作

    List<ApsBomExportQueryPageListInfoRes> listInfoRes = $.copyList(records, ApsBomExportQueryPageListInfoRes.class);
    this.setName(listInfoRes);
    return DynamicsPage.init(page, listInfoRes);
  }

  // 以下为私有对象封装

  public @Override void setName(List<? extends ApsBomDto> apsBomDtoList) {

    if (CollUtil.isEmpty(apsBomDtoList)) {
    }


  }


  private MPJLambdaWrapper<ApsBom> getWrapper(ApsBomDto obj) {
    MPJLambdaWrapper<ApsBom> q = new MPJLambdaWrapper<>();

    if (Objects.nonNull(obj)) {
      q
          .eq(StringUtils.isNoneBlank(obj.getBomCode()), ApsBom::getBomCode, obj.getBomCode())
          .eq(StringUtils.isNoneBlank(obj.getBomName()), ApsBom::getBomName, obj.getBomName())
          .eq(Objects.nonNull(obj.getBomCostPrice()), ApsBom::getBomCostPrice, obj.getBomCostPrice())
          .eq(StringUtils.isNoneBlank(obj.getBomCostPriceUnit()), ApsBom::getBomCostPriceUnit, obj.getBomCostPriceUnit())
          .eq(Objects.nonNull(obj.getBomInventory()), ApsBom::getBomInventory, obj.getBomInventory())

      ;
    }
    q.orderByDesc(ApsBom::getId);
    return q;

  }

  private void setQueryListHeader(DynamicsPage<ApsBom> page) {

    tableHeaderService.listByBizKey(page, "ApsBomService#queryPageList");

  }


}

