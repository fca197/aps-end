package com.olivia.peanut.aps.service.impl;

import cn.hutool.core.collection.CollUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.github.yulichang.base.MPJBaseServiceImpl;
import com.github.yulichang.wrapper.MPJLambdaWrapper;
import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.olivia.peanut.aps.api.entity.apsSellerStore.*;
import com.olivia.peanut.aps.mapper.ApsSellerStoreMapper;
import com.olivia.peanut.aps.model.ApsSellerStore;
import com.olivia.peanut.aps.service.ApsSellerStoreService;
import com.olivia.peanut.base.model.DistrictCode;
import com.olivia.peanut.base.service.BaseTableHeaderService;
import com.olivia.peanut.base.service.DistrictCodeService;
import com.olivia.sdk.service.SetNameService;
import com.olivia.sdk.utils.$;
import com.olivia.sdk.utils.DynamicsPage;
import com.olivia.sdk.utils.LambdaQueryUtil;
import com.olivia.sdk.utils.Str;
import jakarta.annotation.Resource;
import org.springframework.aop.framework.AopContext;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

/**
 * aps销售门店(ApsSellerStore)表服务实现类
 *
 * @author makejava
 * @since 2024-11-15 14:58:59
 */
@Service("apsSellerStoreService")
@Transactional
public class ApsSellerStoreServiceImpl extends MPJBaseServiceImpl<ApsSellerStoreMapper, ApsSellerStore> implements ApsSellerStoreService {

  final static Cache<String, Map<String, String>> cache = CacheBuilder.newBuilder().maximumSize(100).expireAfterWrite(30, TimeUnit.MINUTES).build();

  @Resource
  BaseTableHeaderService tableHeaderService;
  @Resource
  SetNameService setNameService;
  @Resource
  DistrictCodeService districtCodeService;

  public @Override ApsSellerStoreQueryListRes queryList(ApsSellerStoreQueryListReq req) {

    MPJLambdaWrapper<ApsSellerStore> q = getWrapper(req.getData());
    List<ApsSellerStore> list = this.list(q);

    List<ApsSellerStoreDto> dataList = list.stream().map(t -> $.copy(t, ApsSellerStoreDto.class)).collect(Collectors.toList());
    ((ApsSellerStoreService) AopContext.currentProxy()).setName(dataList);
    return new ApsSellerStoreQueryListRes().setDataList(dataList);
  }

  public @Override DynamicsPage<ApsSellerStoreExportQueryPageListInfoRes> queryPageList(ApsSellerStoreExportQueryPageListReq req) {

    DynamicsPage<ApsSellerStore> page = new DynamicsPage<>();
    page.setCurrent(req.getPageNum()).setSize(req.getPageSize());
    setQueryListHeader(page);
    MPJLambdaWrapper<ApsSellerStore> q = getWrapper(req.getData());
    List<ApsSellerStoreExportQueryPageListInfoRes> records;
    if (Boolean.TRUE.equals(req.getQueryPage())) {
      IPage<ApsSellerStore> list = this.page(page, q);
      IPage<ApsSellerStoreExportQueryPageListInfoRes> dataList = list.convert(t -> $.copy(t, ApsSellerStoreExportQueryPageListInfoRes.class));
      records = dataList.getRecords();
    } else {
      records = $.copyList(this.list(q), ApsSellerStoreExportQueryPageListInfoRes.class);
    }

    // 类型转换，  更换枚举 等操作

    List<ApsSellerStoreExportQueryPageListInfoRes> listInfoRes = $.copyList(records, ApsSellerStoreExportQueryPageListInfoRes.class);
    ((ApsSellerStoreService) AopContext.currentProxy()).setName(listInfoRes);

    return DynamicsPage.init(page, listInfoRes);
  }

  // 以下为私有对象封装

  public @Override void setName(List<? extends ApsSellerStoreDto> list) {

    //   setNameService.setName(list, SetNamePojoUtils.FACTORY, SetNamePojoUtils.OP_USER_NAME);

    if (CollUtil.isEmpty(list)) {
      return;
    }
    Set<String> provinceCodeSet = list.stream().map(ApsSellerStoreDto::getSellerStoreProvinceCode).collect(Collectors.toSet());
    Set<String> cityCodeSet = list.stream().map(ApsSellerStoreDto::getSellerStoreCityCode).collect(Collectors.toSet());
    Set<String> areaCodeSet = list.stream().map(ApsSellerStoreDto::getSellerStoreAreaCode).collect(Collectors.toSet());
    provinceCodeSet.addAll(cityCodeSet);
    provinceCodeSet.addAll(areaCodeSet);
    Map<String, DistrictCode> districtCodeMap = districtCodeService.getDistrictCodeMap(provinceCodeSet);
    DistrictCode districtCode = new DistrictCode().setName("-");
    list.forEach(t -> t.setSellerStoreAreaName(String.join("/", //
        districtCodeMap.getOrDefault(t.getSellerStoreProvinceCode(), districtCode).getName(), //
        districtCodeMap.getOrDefault(t.getSellerStoreCityCode(), districtCode).getName(),//
        districtCodeMap.getOrDefault(t.getSellerStoreAreaCode(), districtCode).getName())
    ));
  }


  @SuppressWarnings(Str.UN_CHECKED)
  private MPJLambdaWrapper<ApsSellerStore> getWrapper(ApsSellerStoreDto obj) {
    MPJLambdaWrapper<ApsSellerStore> q = new MPJLambdaWrapper<>();


    LambdaQueryUtil.lambdaQueryWrapper(q, obj, ApsSellerStore.class, ApsSellerStore::getId,
        ApsSellerStore::getSellerStoreCode, ApsSellerStore::getSellerStoreName,
        ApsSellerStore::getSellerStorePhone, ApsSellerStore::getSellerStoreProvinceCode, ApsSellerStore::getSellerStoreCityCode
        , ApsSellerStore::getSellerStoreAreaCode);
    q.orderByDesc(ApsSellerStore::getId);
    return q;

  }

  private void setQueryListHeader(DynamicsPage<ApsSellerStore> page) {

    tableHeaderService.listByBizKey(page, "ApsSellerStoreService#queryPageList");

  }


}

