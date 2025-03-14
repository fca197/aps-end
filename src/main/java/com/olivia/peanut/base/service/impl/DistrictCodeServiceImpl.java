package com.olivia.peanut.base.service.impl;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.ReflectUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.github.yulichang.base.MPJBaseServiceImpl;
import com.github.yulichang.wrapper.MPJLambdaWrapper;
import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.olivia.peanut.base.api.entity.districtCode.*;
import com.olivia.peanut.base.mapper.DistrictCodeMapper;
import com.olivia.peanut.base.model.DistrictCode;
import com.olivia.peanut.base.service.DistrictCodeService;
import com.olivia.peanut.enums.DistrictCodeSelectType;
import com.olivia.sdk.ann.SetUserName;
import com.olivia.sdk.comment.ServiceComment;
import com.olivia.sdk.utils.$;
import com.olivia.sdk.utils.DynamicsPage;
import com.olivia.sdk.utils.FieldUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
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

  @Override
  public void setDistrictName(List<?> list, DistrictCodeSelectType... selectTypes) {
    if (CollUtil.isEmpty(list) || selectTypes == null || selectTypes.length == 0) {
      return;
    }
    DistrictCodeSelectType selectType = selectTypes[0];
    if (DistrictCodeSelectType.all.equals(selectType)) {
      setDistrictNameValue(list, DistrictCodeSelectType.provinceCode, DistrictCodeSelectType.cityCode, DistrictCodeSelectType.areaCode);
    } else {
      setDistrictNameValue(list, selectTypes);

    }
  }

  private void setDistrictNameValue(List<?> list, DistrictCodeSelectType... selectTypes) {
    List<DistrictCodeSelectType> selectTypeList = Arrays.stream(selectTypes).toList();
    List<Object> codeList = list.stream().map(t -> selectTypeList.stream().map(s -> FieldUtils.getFieldValue(t, FieldUtils.getField(t, s.name()))).toList())
        .flatMap(List::stream).distinct().toList();

    Map<String, String> codeNameMap = this.list(new LambdaQueryWrapper<DistrictCode>().in(DistrictCode::getCode, codeList)).stream().collect(Collectors.toMap(DistrictCode::getCode, DistrictCode::getName));

    list.forEach(v -> {
      selectTypeList.stream().forEach(s -> {
        ReflectUtil.setFieldValue(v, FieldUtils.getField(v, s.getShowFieldName()),
            codeNameMap.get(FieldUtils.getFieldValue(v, FieldUtils.getField(v, s.name()))));
      });
    });

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

