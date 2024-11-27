package com.olivia.peanut.aps.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.github.yulichang.base.MPJBaseServiceImpl;
import com.github.yulichang.wrapper.MPJLambdaWrapper;
import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.olivia.peanut.aps.api.entity.apsGoodsSaleItem.*;
import com.olivia.peanut.aps.mapper.ApsGoodsSaleItemMapper;
import com.olivia.peanut.aps.model.ApsGoodsSaleItem;
import com.olivia.peanut.aps.service.ApsGoodsSaleItemService;
import com.olivia.sdk.ann.SetUserName;
import com.olivia.sdk.comment.ServiceComment;
import com.olivia.sdk.utils.$;
import com.olivia.sdk.utils.DynamicsPage;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

/**
 * (ApsGoodsSaleItem)表服务实现类
 *
 * @author peanut
 * @since 2024-03-30 10:38:36
 */
@Service("apsGoodsSaleItemService")
@Transactional
public class ApsGoodsSaleItemServiceImpl extends MPJBaseServiceImpl<ApsGoodsSaleItemMapper, ApsGoodsSaleItem> implements ApsGoodsSaleItemService {

  final static Cache<String, Map<String, String>> cache = CacheBuilder.newBuilder().maximumSize(100).expireAfterWrite(30, TimeUnit.MINUTES).build();


  public @Override ApsGoodsSaleItemQueryListRes queryList(ApsGoodsSaleItemQueryListReq req) {

    MPJLambdaWrapper<ApsGoodsSaleItem> q = getWrapper(req.getData());
    List<ApsGoodsSaleItem> list = this.list(q);

    List<ApsGoodsSaleItemDto> dataList = list.stream().map(t -> $.copy(t, ApsGoodsSaleItemDto.class)).collect(Collectors.toList());

    return new ApsGoodsSaleItemQueryListRes().setDataList(dataList);
  }


  public @Override DynamicsPage<ApsGoodsSaleItemExportQueryPageListInfoRes> queryPageList(ApsGoodsSaleItemExportQueryPageListReq req) {

    DynamicsPage<ApsGoodsSaleItem> page = new DynamicsPage<>();
    page.setCurrent(req.getPageNum()).setSize(req.getPageSize());
    setQueryListHeader(page);
    MPJLambdaWrapper<ApsGoodsSaleItem> q = getWrapper(req.getData());
    List<ApsGoodsSaleItemExportQueryPageListInfoRes> records;
    if (Boolean.TRUE.equals(req.getQueryPage())) {
      IPage<ApsGoodsSaleItem> list = this.page(page, q);
      IPage<ApsGoodsSaleItemExportQueryPageListInfoRes> dataList = list.convert(t -> $.copy(t, ApsGoodsSaleItemExportQueryPageListInfoRes.class));
      records = dataList.getRecords();
    } else {
      records = $.copyList(this.list(q), ApsGoodsSaleItemExportQueryPageListInfoRes.class);
    }

    // 类型转换，  更换枚举 等操作

    List<ApsGoodsSaleItemExportQueryPageListInfoRes> listInfoRes = $.copyList(records, ApsGoodsSaleItemExportQueryPageListInfoRes.class);

    return DynamicsPage.init(page, listInfoRes);
  }


  @SetUserName
  public @Override void setName(List<? extends ApsGoodsSaleItemDto> apsGoodsSaleItemDtoList) {

  }

  // 以下为私有对象封装


  private MPJLambdaWrapper<ApsGoodsSaleItem> getWrapper(ApsGoodsSaleItemDto obj) {
    MPJLambdaWrapper<ApsGoodsSaleItem> q = new MPJLambdaWrapper<>();

    if (Objects.nonNull(obj)) {
      q.eq(Objects.nonNull(obj.getGoodsId()), ApsGoodsSaleItem::getGoodsId, obj.getGoodsId())
          .eq(Objects.nonNull(obj.getUseForecast()), ApsGoodsSaleItem::getUseForecast, obj.getUseForecast())
          .eq(Objects.nonNull(obj.getSupplierStatus()), ApsGoodsSaleItem::getSupplierStatus, obj.getSupplierStatus())

      ;
    }
    q.orderByDesc(ApsGoodsSaleItem::getId);
    return q;

  }

  private void setQueryListHeader(DynamicsPage<ApsGoodsSaleItem> page) {

    ServiceComment.header(page, "ApsGoodsSaleItemService#queryPageList");

  }


}

