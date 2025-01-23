package com.olivia.peanut.aps.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.github.yulichang.base.MPJBaseServiceImpl;
import com.github.yulichang.wrapper.MPJLambdaWrapper;
import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.olivia.peanut.aps.api.entity.apsOrderGoodsStatusDate.*;
import com.olivia.peanut.aps.mapper.ApsOrderGoodsStatusDateMapper;
import com.olivia.peanut.aps.model.ApsOrderGoodsStatusDate;
import com.olivia.peanut.aps.service.ApsOrderGoodsStatusDateService;
import com.olivia.peanut.aps.service.ApsStatusService;
import com.olivia.peanut.util.SetNamePojoUtils;
import com.olivia.sdk.comment.ServiceComment;
import com.olivia.sdk.service.SetNameService;
import com.olivia.sdk.utils.$;
import com.olivia.sdk.utils.DynamicsPage;
import jakarta.annotation.Resource;
import org.springframework.aop.framework.AopContext;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

/**
 * 订单商品状态表(ApsOrderGoodsStatusDate)表服务实现类
 *
 * @author peanut
 * @since 2024-06-14 10:26:59
 */
@Service("apsOrderGoodsStatusDateService")
@Transactional
public class ApsOrderGoodsStatusDateServiceImpl extends MPJBaseServiceImpl<ApsOrderGoodsStatusDateMapper, ApsOrderGoodsStatusDate> implements ApsOrderGoodsStatusDateService {

  final static Cache<String, Map<String, String>> cache = CacheBuilder.newBuilder().maximumSize(100).expireAfterWrite(30, TimeUnit.MINUTES).build();

  @Resource
  SetNameService setNameService;

  public @Override ApsOrderGoodsStatusDateQueryListRes queryList(ApsOrderGoodsStatusDateQueryListReq req) {

    MPJLambdaWrapper<ApsOrderGoodsStatusDate> q = getWrapper(req.getData());
    List<ApsOrderGoodsStatusDate> list = this.list(q);

    List<ApsOrderGoodsStatusDateDto> dataList = list.stream().map(t -> $.copy(t, ApsOrderGoodsStatusDateDto.class)).collect(Collectors.toList());
    //  this.setName(dataList);
    ((ApsOrderGoodsStatusDateService) AopContext.currentProxy()).setName(dataList);

    return new ApsOrderGoodsStatusDateQueryListRes().setDataList(dataList);
  }
  // 以下为私有对象封装

  public @Override DynamicsPage<ApsOrderGoodsStatusDateExportQueryPageListInfoRes> queryPageList(ApsOrderGoodsStatusDateExportQueryPageListReq req) {

    DynamicsPage<ApsOrderGoodsStatusDate> page = new DynamicsPage<>();
    page.setCurrent(req.getPageNum()).setSize(req.getPageSize());
    setQueryListHeader(page);
    MPJLambdaWrapper<ApsOrderGoodsStatusDate> q = getWrapper(req.getData());
    List<ApsOrderGoodsStatusDateExportQueryPageListInfoRes> records;
    if (Boolean.TRUE.equals(req.getQueryPage())) {
      IPage<ApsOrderGoodsStatusDate> list = this.page(page, q);
      IPage<ApsOrderGoodsStatusDateExportQueryPageListInfoRes> dataList = list.convert(t -> $.copy(t, ApsOrderGoodsStatusDateExportQueryPageListInfoRes.class));
      records = dataList.getRecords();
    } else {
      records = $.copyList(this.list(q), ApsOrderGoodsStatusDateExportQueryPageListInfoRes.class);
    }

    // 类型转换，  更换枚举 等操作

    List<ApsOrderGoodsStatusDateExportQueryPageListInfoRes> listInfoRes = $.copyList(records, ApsOrderGoodsStatusDateExportQueryPageListInfoRes.class);
    // this.setName(listInfoRes);
    ((ApsOrderGoodsStatusDateService) AopContext.currentProxy()).setName(listInfoRes);

    return DynamicsPage.init(page, listInfoRes);
  }

  //  @SetUserName
  public @Override void setName(List<? extends ApsOrderGoodsStatusDateDto> apsOrderGoodsStatusDateDtoList) {

    setNameService.setName(apsOrderGoodsStatusDateDtoList,
//        SetNamePojoUtils.OP_USER_NAME,
        SetNamePojoUtils.GOODS,//
        SetNamePojoUtils.getSetNamePojo(ApsStatusService.class, "statusName", "goodsStatusId", "goodsStatusName")
    );

//    if (CollUtil.isEmpty(apsOrderGoodsStatusDateDtoList)) {
//      return;
//    }
//    Map<Long, String> gMap = apsGoodsService.listByIds(apsOrderGoodsStatusDateDtoList.stream().map(ApsOrderGoodsStatusDateDto::getGoodsId).toList())
//        .stream().collect(Collectors.toMap(ApsGoods::getId, ApsGoods::getGoodsName, (k1, k2) -> k1));
//
//    Map<Long, String> sMap = apsStatusService.list().stream().collect(Collectors.toMap(ApsStatus::getId, ApsStatus::getStatusName, (k1, k2) -> k1));
//
//    apsOrderGoodsStatusDateDtoList.forEach(t -> t.setGoodsName(gMap.get(t.getGoodsId())).setGoodsStatusName(sMap.get(t.getGoodsStatusId()))
//    );
  }

  @Override
  public List<ApsOrderGoodsStatusDate> listByOrderIdGoodsId(Long orderId, Long goodsId) {
    return this.list(new LambdaQueryWrapper<ApsOrderGoodsStatusDate>().eq(ApsOrderGoodsStatusDate::getOrderId, orderId).eq(ApsOrderGoodsStatusDate::getGoodsId, goodsId));
  }

  private MPJLambdaWrapper<ApsOrderGoodsStatusDate> getWrapper(ApsOrderGoodsStatusDateDto obj) {
    MPJLambdaWrapper<ApsOrderGoodsStatusDate> q = new MPJLambdaWrapper<>();

    if (Objects.nonNull(obj)) {
      q
          .eq(Objects.nonNull(obj.getOrderId()), ApsOrderGoodsStatusDate::getOrderId, obj.getOrderId())
          .eq(Objects.nonNull(obj.getGoodsId()), ApsOrderGoodsStatusDate::getGoodsId, obj.getGoodsId())
          .eq(Objects.nonNull(obj.getGoodsStatusId()), ApsOrderGoodsStatusDate::getGoodsStatusId, obj.getGoodsStatusId())
          .eq(Objects.nonNull(obj.getFactoryId()), ApsOrderGoodsStatusDate::getFactoryId, obj.getFactoryId())

      ;
    }
    q.orderByDesc(ApsOrderGoodsStatusDate::getId);
    return q;

  }

  private void setQueryListHeader(DynamicsPage<ApsOrderGoodsStatusDate> page) {

    ServiceComment.header(page, "ApsOrderGoodsStatusDateService#queryPageList");

  }


}

