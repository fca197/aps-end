package com.olivia.peanut.base.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.github.yulichang.base.MPJBaseServiceImpl;
import com.github.yulichang.wrapper.MPJLambdaWrapper;
import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.olivia.peanut.base.api.entity.shiftItem.*;
import com.olivia.peanut.base.mapper.ShiftItemMapper;
import com.olivia.peanut.base.model.ShiftItem;
import com.olivia.peanut.base.service.ShiftItemService;
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
 * (ShiftItem)表服务实现类
 *
 * @author peanut
 * @since 2024-04-04 12:10:16
 */
@Service("shiftItemService")
@Transactional
public class ShiftItemServiceImpl extends MPJBaseServiceImpl<ShiftItemMapper, ShiftItem> implements ShiftItemService {

  final static Cache<String, Map<String, String>> cache = CacheBuilder.newBuilder().maximumSize(100).expireAfterWrite(30, TimeUnit.MINUTES).build();


  public @Override ShiftItemQueryListRes queryList(ShiftItemQueryListReq req) {

    MPJLambdaWrapper<ShiftItem> q = getWrapper(req.getData());
    List<ShiftItem> list = this.list(q);

    List<ShiftItemDto> dataList = list.stream().map(t -> $.copy(t, ShiftItemDto.class)).collect(Collectors.toList());

    return new ShiftItemQueryListRes().setDataList(dataList);
  }


  public @Override DynamicsPage<ShiftItemExportQueryPageListInfoRes> queryPageList(ShiftItemExportQueryPageListReq req) {

    DynamicsPage<ShiftItem> page = new DynamicsPage<>();
    page.setCurrent(req.getPageNum()).setSize(req.getPageSize());
    setQueryListHeader(page);
    MPJLambdaWrapper<ShiftItem> q = getWrapper(req.getData());
    List<ShiftItemExportQueryPageListInfoRes> records;
    if (Boolean.TRUE.equals(req.getQueryPage())) {
      IPage<ShiftItem> list = this.page(page, q);
      IPage<ShiftItemExportQueryPageListInfoRes> dataList = list.convert(t -> $.copy(t, ShiftItemExportQueryPageListInfoRes.class));
      records = dataList.getRecords();
    } else {
      records = $.copyList(this.list(q), ShiftItemExportQueryPageListInfoRes.class);
    }

    // 类型转换，  更换枚举 等操作

    List<ShiftItemExportQueryPageListInfoRes> listInfoRes = $.copyList(records, ShiftItemExportQueryPageListInfoRes.class);

    return DynamicsPage.init(page, listInfoRes);
  }


  @SetUserName
  public @Override void setName(List<? extends ShiftItemDto> shiftItemDtoList) {

  }

  // 以下为私有对象封装


  private MPJLambdaWrapper<ShiftItem> getWrapper(ShiftItemDto obj) {
    MPJLambdaWrapper<ShiftItem> q = new MPJLambdaWrapper<>();

    if (Objects.nonNull(obj)) {
      q
          .eq(Objects.nonNull(obj.getShiftId()), ShiftItem::getShiftId, obj.getShiftId())
          .eq(Objects.nonNull(obj.getFactoryId()), ShiftItem::getFactoryId, obj.getFactoryId())

      ;
    }
    q.orderByDesc(ShiftItem::getId);
    return q;

  }

  private void setQueryListHeader(DynamicsPage<ShiftItem> page) {

    ServiceComment.header(page, "ShiftItemService#queryPageList");

  }


}

