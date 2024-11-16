package com.olivia.peanut.portal.service.impl;

import cn.hutool.core.collection.CollUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.github.yulichang.base.MPJBaseServiceImpl;
import com.github.yulichang.wrapper.MPJLambdaWrapper;
import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.olivia.peanut.portal.api.entity.districtCode.*;
import com.olivia.peanut.portal.mapper.DistrictCodeMapper;
import com.olivia.peanut.portal.model.DistrictCode;
import com.olivia.peanut.portal.service.DistrictCodeService;
import com.olivia.sdk.ann.SetUserName;
import com.olivia.sdk.comment.ServiceComment;
import com.olivia.sdk.utils.$;
import com.olivia.sdk.utils.DynamicsPage;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * (DistrictCode)表服务实现类
 *
 * @author peanut
 * @since 2024-04-09 13:19:07
 */
@Service("districtCodeService")
@Transactional
public class DistrictCodeServiceImpl extends MPJBaseServiceImpl<DistrictCodeMapper, DistrictCode> implements DistrictCodeService {

  final static Cache<String, Map<String, String>> cache = CacheBuilder.newBuilder().maximumSize(100).expireAfterWrite(30, TimeUnit.MINUTES).build();


  public @Override DistrictCodeQueryListRes queryList(DistrictCodeQueryListReq req) {

    MPJLambdaWrapper<DistrictCode> q = getWrapper(req.getData());
    List<DistrictCode> list = this.list(q);

    List<DistrictCodeDto> dataList = list.stream().map(t -> $.copy(t, DistrictCodeDto.class)).collect(Collectors.toList());
    //  this.setName(dataList);
    return new DistrictCodeQueryListRes().setDataList(dataList);
  }


  public @Override DynamicsPage<DistrictCodeExportQueryPageListInfoRes> queryPageList(DistrictCodeExportQueryPageListReq req) {

    DynamicsPage<DistrictCode> page = new DynamicsPage<>();
    page.setCurrent(req.getPageNum()).setSize(req.getPageSize());
    setQueryListHeader(page);
    MPJLambdaWrapper<DistrictCode> q = getWrapper(req.getData());
    List<DistrictCodeExportQueryPageListInfoRes> records;
    if (Boolean.TRUE.equals(req.getQueryPage())) {
      IPage<DistrictCode> list = this.page(page, q);
      IPage<DistrictCodeExportQueryPageListInfoRes> dataList = list.convert(t -> $.copy(t, DistrictCodeExportQueryPageListInfoRes.class));
      records = dataList.getRecords();
    } else {
      records = $.copyList(this.list(q), DistrictCodeExportQueryPageListInfoRes.class);
    }

    // 类型转换，  更换枚举 等操作

    List<DistrictCodeExportQueryPageListInfoRes> listInfoRes = $.copyList(records, DistrictCodeExportQueryPageListInfoRes.class);
    // this.setName(listInfoRes);
    return DynamicsPage.init(page, listInfoRes);
  }

  @Override
  public Map<String, DistrictCode> getDistrictCodeMap(Collection<String> codeList) {
    if (CollUtil.isNotEmpty(codeList)) {
      return this.list(new MPJLambdaWrapper<DistrictCode>().in(DistrictCode::getCode, codeList)).stream().collect(Collectors.toMap(DistrictCode::getCode, Function.identity()));
    }
    return Map.of();
  }

  // 以下为私有对象封装

  @SetUserName
  public @Override void setName(List<? extends DistrictCodeDto> districtCodeDtoList) {

    if (CollUtil.isEmpty(districtCodeDtoList)) {
    }


  }


  private MPJLambdaWrapper<DistrictCode> getWrapper(DistrictCodeDto obj) {
    MPJLambdaWrapper<DistrictCode> q = new MPJLambdaWrapper<>();

    if (Objects.nonNull(obj)) {
      q
          .eq(StringUtils.isNoneBlank(obj.getCode()), DistrictCode::getCode, obj.getCode())
          .eq(StringUtils.isNoneBlank(obj.getName()), DistrictCode::getName, obj.getName())
          .eq(StringUtils.isNoneBlank(obj.getParentCode()), DistrictCode::getParentCode, obj.getParentCode())
          .eq(Objects.nonNull(obj.getLevel()), DistrictCode::getLevel, obj.getLevel())

      ;
    }
    q.orderByAsc(DistrictCode::getCode);
    return q;

  }

  private void setQueryListHeader(DynamicsPage<DistrictCode> page) {

    ServiceComment.header(page, "DistrictCodeService#queryPageList");

  }


}

