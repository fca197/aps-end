package com.olivia.peanut.aps.service.impl;

import cn.hutool.core.collection.CollUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.github.yulichang.base.MPJBaseServiceImpl;
import com.github.yulichang.wrapper.MPJLambdaWrapper;
import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.olivia.peanut.aps.api.entity.apsOrderUser.*;
import com.olivia.peanut.aps.mapper.ApsOrderUserMapper;
import com.olivia.peanut.aps.model.ApsOrderUser;
import com.olivia.peanut.aps.service.ApsOrderUserService;
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
 * (ApsOrderUser)表服务实现类
 *
 * @author peanut
 * @since 2024-04-09 13:19:39
 */
@Service("apsOrderUserService")
@Transactional
public class ApsOrderUserServiceImpl extends MPJBaseServiceImpl<ApsOrderUserMapper, ApsOrderUser> implements ApsOrderUserService {

  final static Cache<String, Map<String, String>> cache = CacheBuilder.newBuilder().maximumSize(100).expireAfterWrite(30, TimeUnit.MINUTES).build();


  public @Override ApsOrderUserQueryListRes queryList(ApsOrderUserQueryListReq req) {

    MPJLambdaWrapper<ApsOrderUser> q = getWrapper(req.getData());
    List<ApsOrderUser> list = this.list(q);

    List<ApsOrderUserDto> dataList = list.stream().map(t -> $.copy(t, ApsOrderUserDto.class)).collect(Collectors.toList());
    //  this.setName(dataList);
    ((ApsOrderUserServiceImpl) AopContext.currentProxy()).setName(dataList);

    return new ApsOrderUserQueryListRes().setDataList(dataList);
  }


  public @Override DynamicsPage<ApsOrderUserExportQueryPageListInfoRes> queryPageList(ApsOrderUserExportQueryPageListReq req) {

    DynamicsPage<ApsOrderUser> page = new DynamicsPage<>();
    page.setCurrent(req.getPageNum()).setSize(req.getPageSize());
    setQueryListHeader(page);
    MPJLambdaWrapper<ApsOrderUser> q = getWrapper(req.getData());
    List<ApsOrderUserExportQueryPageListInfoRes> records;
    if (Boolean.TRUE.equals(req.getQueryPage())) {
      IPage<ApsOrderUser> list = this.page(page, q);
      IPage<ApsOrderUserExportQueryPageListInfoRes> dataList = list.convert(t -> $.copy(t, ApsOrderUserExportQueryPageListInfoRes.class));
      records = dataList.getRecords();
    } else {
      records = $.copyList(this.list(q), ApsOrderUserExportQueryPageListInfoRes.class);
    }

    // 类型转换，  更换枚举 等操作

    List<ApsOrderUserExportQueryPageListInfoRes> listInfoRes = $.copyList(records, ApsOrderUserExportQueryPageListInfoRes.class);
    // this.setName(listInfoRes);
    ((ApsOrderUserServiceImpl) AopContext.currentProxy()).setName(listInfoRes);

    return DynamicsPage.init(page, listInfoRes);
  }

  // 以下为私有对象封装

  @SetUserName
  public @Override void setName(List<? extends ApsOrderUserDto> apsOrderUserDtoList) {

    if (CollUtil.isEmpty(apsOrderUserDtoList)) {
    }


  }


  private MPJLambdaWrapper<ApsOrderUser> getWrapper(ApsOrderUserDto obj) {
    MPJLambdaWrapper<ApsOrderUser> q = new MPJLambdaWrapper<>();

    if (Objects.nonNull(obj)) {
      q
          .eq(Objects.nonNull(obj.getOrderId()), ApsOrderUser::getOrderId, obj.getOrderId())
          .eq(StringUtils.isNoneBlank(obj.getUserName()), ApsOrderUser::getUserName, obj.getUserName())
          .eq(StringUtils.isNoneBlank(obj.getUserPhone()), ApsOrderUser::getUserPhone, obj.getUserPhone())
          .eq(Objects.nonNull(obj.getUserSex()), ApsOrderUser::getUserSex, obj.getUserSex())
          .eq(StringUtils.isNoneBlank(obj.getCountryCode()), ApsOrderUser::getCountryCode, obj.getCountryCode())
          .eq(StringUtils.isNoneBlank(obj.getProvinceCode()), ApsOrderUser::getProvinceCode, obj.getProvinceCode())
          .eq(StringUtils.isNoneBlank(obj.getCityCode()), ApsOrderUser::getCityCode, obj.getCityCode())
          .eq(StringUtils.isNoneBlank(obj.getAreaCode()), ApsOrderUser::getAreaCode, obj.getAreaCode())
          .eq(StringUtils.isNoneBlank(obj.getUserAddress()), ApsOrderUser::getUserAddress, obj.getUserAddress())
          .eq(StringUtils.isNoneBlank(obj.getUserRemark()), ApsOrderUser::getUserRemark, obj.getUserRemark())
          .eq(Objects.nonNull(obj.getFactoryId()), ApsOrderUser::getFactoryId, obj.getFactoryId())

      ;
    }
    q.orderByDesc(ApsOrderUser::getId);
    return q;

  }

  private void setQueryListHeader(DynamicsPage<ApsOrderUser> page) {

    ServiceComment.header(page, "ApsOrderUserService#queryPageList");

  }


}

