package com.olivia.peanut.aps.service.impl;

import cn.hutool.core.collection.CollUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.github.yulichang.base.MPJBaseServiceImpl;
import com.github.yulichang.wrapper.MPJLambdaWrapper;
import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.olivia.peanut.aps.api.entity.apsSchedulingVersionItem.*;
import com.olivia.peanut.aps.mapper.ApsSchedulingVersionItemMapper;
import com.olivia.peanut.aps.model.ApsSchedulingVersionItem;
import com.olivia.peanut.aps.service.ApsSchedulingVersionItemService;
import com.olivia.sdk.ann.SetUserName;
import com.olivia.sdk.comment.ServiceComment;
import com.olivia.sdk.utils.$;
import com.olivia.sdk.utils.DynamicsPage;
import com.olivia.sdk.utils.LambdaQueryUtil;
import com.olivia.sdk.utils.Str;
import org.springframework.aop.framework.AopContext;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

/**
 * (ApsSchedulingVersionItem)表服务实现类
 *
 * @author peanut
 * @since 2024-04-16 09:24:06
 */
@Service("apsSchedulingVersionItemService")
@Transactional
public class ApsSchedulingVersionItemServiceImpl extends MPJBaseServiceImpl<ApsSchedulingVersionItemMapper, ApsSchedulingVersionItem> implements ApsSchedulingVersionItemService {

  final static Cache<String, Map<String, String>> cache = CacheBuilder.newBuilder().maximumSize(100).expireAfterWrite(30, TimeUnit.MINUTES).build();


  public @Override ApsSchedulingVersionItemQueryListRes queryList(ApsSchedulingVersionItemQueryListReq req) {

    MPJLambdaWrapper<ApsSchedulingVersionItem> q = getWrapper(req.getData());
    List<ApsSchedulingVersionItem> list = this.list(q);

    List<ApsSchedulingVersionItemDto> dataList = list.stream().map(t -> $.copy(t, ApsSchedulingVersionItemDto.class)).collect(Collectors.toList());
    //  this.setName(dataList);
    ((ApsSchedulingVersionItemServiceImpl) AopContext.currentProxy()).setName(dataList);

    return new ApsSchedulingVersionItemQueryListRes().setDataList(dataList);
  }


  public @Override DynamicsPage<ApsSchedulingVersionItemExportQueryPageListInfoRes> queryPageList(ApsSchedulingVersionItemExportQueryPageListReq req) {

    DynamicsPage<ApsSchedulingVersionItem> page = new DynamicsPage<>();
    page.setCurrent(req.getPageNum()).setSize(req.getPageSize());
    setQueryListHeader(page);
    MPJLambdaWrapper<ApsSchedulingVersionItem> q = getWrapper(req.getData());
    List<ApsSchedulingVersionItemExportQueryPageListInfoRes> records;
    if (Boolean.TRUE.equals(req.getQueryPage())) {
      IPage<ApsSchedulingVersionItem> list = this.page(page, q);
      IPage<ApsSchedulingVersionItemExportQueryPageListInfoRes> dataList = list.convert(t -> $.copy(t, ApsSchedulingVersionItemExportQueryPageListInfoRes.class));
      records = dataList.getRecords();
    } else {
      records = $.copyList(this.list(q), ApsSchedulingVersionItemExportQueryPageListInfoRes.class);
    }

    // 类型转换，  更换枚举 等操作

    List<ApsSchedulingVersionItemExportQueryPageListInfoRes> listInfoRes = $.copyList(records, ApsSchedulingVersionItemExportQueryPageListInfoRes.class);
    // this.setName(listInfoRes);
    ((ApsSchedulingVersionItemServiceImpl) AopContext.currentProxy()).setName(listInfoRes);

    return DynamicsPage.init(page, listInfoRes);
  }

  // 以下为私有对象封装

  @SetUserName
  public @Override void setName(List<? extends ApsSchedulingVersionItemDto> apsSchedulingVersionItemDtoList) {

    if (CollUtil.isEmpty(apsSchedulingVersionItemDtoList)) {
    }


  }


  @SuppressWarnings(Str.UN_CHECKED)
  private MPJLambdaWrapper<ApsSchedulingVersionItem> getWrapper(ApsSchedulingVersionItemDto obj) {
    MPJLambdaWrapper<ApsSchedulingVersionItem> q = new MPJLambdaWrapper<>();

    LambdaQueryUtil.lambdaQueryWrapper(q, obj, ApsSchedulingVersionItem.class,
        ApsSchedulingVersionItem::getSchedulingVersionId, ApsSchedulingVersionItem::getOrderId,
        ApsSchedulingVersionItem::getGoodsId
    );
    q.orderByDesc(ApsSchedulingVersionItem::getId);
    return q;

  }

  private void setQueryListHeader(DynamicsPage<ApsSchedulingVersionItem> page) {

    ServiceComment.header(page, "ApsSchedulingVersionItemService#queryPageList");

  }


}

