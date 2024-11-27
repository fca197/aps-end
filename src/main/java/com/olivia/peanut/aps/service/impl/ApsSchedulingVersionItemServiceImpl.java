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


  private MPJLambdaWrapper<ApsSchedulingVersionItem> getWrapper(ApsSchedulingVersionItemDto obj) {
    MPJLambdaWrapper<ApsSchedulingVersionItem> q = new MPJLambdaWrapper<>();

    if (Objects.nonNull(obj)) {
      q.eq(Objects.nonNull(obj.getSchedulingVersionId()), ApsSchedulingVersionItem::getSchedulingVersionId, obj.getSchedulingVersionId())
          .eq(Objects.nonNull(obj.getOrderId()), ApsSchedulingVersionItem::getOrderId, obj.getOrderId())
          .eq(Objects.nonNull(obj.getGoodsId()), ApsSchedulingVersionItem::getGoodsId, obj.getGoodsId())
          .eq(StringUtils.isNoneBlank(obj.getField0()), ApsSchedulingVersionItem::getField0, obj.getField0())
          .eq(StringUtils.isNoneBlank(obj.getField1()), ApsSchedulingVersionItem::getField1, obj.getField1())
          .eq(StringUtils.isNoneBlank(obj.getField2()), ApsSchedulingVersionItem::getField2, obj.getField2())
          .eq(StringUtils.isNoneBlank(obj.getField3()), ApsSchedulingVersionItem::getField3, obj.getField3())
          .eq(StringUtils.isNoneBlank(obj.getField4()), ApsSchedulingVersionItem::getField4, obj.getField4())
          .eq(StringUtils.isNoneBlank(obj.getField5()), ApsSchedulingVersionItem::getField5, obj.getField5())
          .eq(StringUtils.isNoneBlank(obj.getField6()), ApsSchedulingVersionItem::getField6, obj.getField6())
          .eq(StringUtils.isNoneBlank(obj.getField7()), ApsSchedulingVersionItem::getField7, obj.getField7())
          .eq(StringUtils.isNoneBlank(obj.getField8()), ApsSchedulingVersionItem::getField8, obj.getField8())
          .eq(StringUtils.isNoneBlank(obj.getField9()), ApsSchedulingVersionItem::getField9, obj.getField9())
          .eq(StringUtils.isNoneBlank(obj.getField10()), ApsSchedulingVersionItem::getField10, obj.getField10())
          .eq(StringUtils.isNoneBlank(obj.getField11()), ApsSchedulingVersionItem::getField11, obj.getField11())
          .eq(StringUtils.isNoneBlank(obj.getField12()), ApsSchedulingVersionItem::getField12, obj.getField12())
          .eq(StringUtils.isNoneBlank(obj.getField13()), ApsSchedulingVersionItem::getField13, obj.getField13())
          .eq(StringUtils.isNoneBlank(obj.getField14()), ApsSchedulingVersionItem::getField14, obj.getField14())
          .eq(StringUtils.isNoneBlank(obj.getField15()), ApsSchedulingVersionItem::getField15, obj.getField15())
          .eq(StringUtils.isNoneBlank(obj.getField16()), ApsSchedulingVersionItem::getField16, obj.getField16())
          .eq(StringUtils.isNoneBlank(obj.getField17()), ApsSchedulingVersionItem::getField17, obj.getField17())
          .eq(StringUtils.isNoneBlank(obj.getField18()), ApsSchedulingVersionItem::getField18, obj.getField18())
          .eq(StringUtils.isNoneBlank(obj.getField19()), ApsSchedulingVersionItem::getField19, obj.getField19())
          .eq(StringUtils.isNoneBlank(obj.getField20()), ApsSchedulingVersionItem::getField20, obj.getField20())
          .eq(Objects.nonNull(obj.getResultType()), ApsSchedulingVersionItem::getResultType, obj.getResultType())
          .eq(Objects.nonNull(obj.getNumberIndex()), ApsSchedulingVersionItem::getNumberIndex, obj.getNumberIndex())

      ;
    }
    q.orderByDesc(ApsSchedulingVersionItem::getId);
    return q;

  }

  private void setQueryListHeader(DynamicsPage<ApsSchedulingVersionItem> page) {

    ServiceComment.header(page, "ApsSchedulingVersionItemService#queryPageList");

  }


}

