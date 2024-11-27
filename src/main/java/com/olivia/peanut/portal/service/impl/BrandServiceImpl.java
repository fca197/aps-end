package com.olivia.peanut.portal.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.github.yulichang.base.MPJBaseServiceImpl;
import com.github.yulichang.wrapper.MPJLambdaWrapper;
import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.olivia.peanut.portal.api.entity.brand.*;
import com.olivia.peanut.portal.mapper.BrandMapper;
import com.olivia.peanut.portal.model.Brand;
import com.olivia.peanut.portal.service.BrandService;
import com.olivia.sdk.utils.$;
import com.olivia.sdk.utils.DynamicsPage;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

/**
 * 品牌信息(Brand)表服务实现类
 *
 * @author makejava
 * @since 2024-03-11 10:44:02
 */
@Service("brandService")
@Transactional
public class BrandServiceImpl extends MPJBaseServiceImpl<BrandMapper, Brand> implements BrandService {

  final static Cache<String, Map<String, String>> cache = CacheBuilder.newBuilder().maximumSize(100).expireAfterWrite(30, TimeUnit.MINUTES).build();

  public @Override BrandQueryListRes queryList(BrandQueryListReq req) {

    MPJLambdaWrapper<Brand> q = getWrapper(req.getData());
    List<Brand> list = this.list(q);

    List<BrandQueryListRes.Info> dataList = list.stream().map(t -> $.copy(t, BrandQueryListRes.Info.class)).collect(Collectors.toList());

    return new BrandQueryListRes().setDataList(dataList);
  }


  public @Override DynamicsPage<BrandExportQueryPageListInfoRes> queryPageList(BrandExportQueryPageListReq req) {

    DynamicsPage<Brand> page = new DynamicsPage<>();
    page.setCurrent(req.getPageNum()).setSize(req.getPageSize());
    setQueryListHeader(page);
    MPJLambdaWrapper<Brand> q = getWrapper(req.getData());
    List<BrandExportQueryPageListInfoRes> records;
    if (Boolean.TRUE.equals(req.getQueryPage())) {
      IPage<Brand> list = this.page(page, q);
      IPage<BrandExportQueryPageListInfoRes> dataList = list.convert(t -> $.copy(t, BrandExportQueryPageListInfoRes.class));
      records = dataList.getRecords();
    } else {
      records = $.copyList(this.list(q), BrandExportQueryPageListInfoRes.class);
    }

    // 类型转换，  更换枚举 等操作
    List<BrandExportQueryPageListInfoRes> listInfoRes = $.copyList(records, BrandExportQueryPageListInfoRes.class);

    return DynamicsPage.init(page, listInfoRes);
  }

  // 以下为私有对象封装


  private MPJLambdaWrapper<Brand> getWrapper(BrandDto obj) {
    MPJLambdaWrapper<Brand> q = new MPJLambdaWrapper<>();

    if (Objects.nonNull(obj)) {
      q
          .eq(Objects.nonNull(obj.getId()), Brand::getId, obj.getId())
          .eq(Objects.nonNull(obj.getTenantId()), Brand::getTenantId, obj.getTenantId())
          .eq(StringUtils.isNoneBlank(obj.getBrandCode()), Brand::getBrandCode, obj.getBrandCode())
          .eq(StringUtils.isNoneBlank(obj.getBrandName()), Brand::getBrandName, obj.getBrandName())
          .eq(Objects.nonNull(obj.getVersionNum()), Brand::getVersionNum, obj.getVersionNum())
          .orderByDesc(Brand::getId)
      ;
    }

    return q;

  }

  private void setQueryListHeader(DynamicsPage<Brand> page) {
    page
        .addHeader("id", "id")
        .addHeader("tenantId", "所属租户id")
        .addHeader("factoryId", "所属工厂id")
        .addHeader("brandCode", "品牌编码")
        .addHeader("brandName", "品牌名称")
        .addHeader("brandStatus", "品牌状态")
        .addHeader("isDelete", "是否删除(0:否 1:是)")
        .addHeader("createTime", "创建时间")
        .addHeader("createBy", "创建人id")
        .addHeader("updateTime", "更新时间")
        .addHeader("updateBy", "更新人id")
        .addHeader("traceId", "链路追踪ID")
        .addHeader("version", "版本号")
    ;
  }


}

