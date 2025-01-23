package com.olivia.peanut.aps.service.impl;

import cn.hutool.core.collection.CollUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.github.yulichang.base.MPJBaseServiceImpl;
import com.github.yulichang.wrapper.MPJLambdaWrapper;
import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.olivia.peanut.aps.api.entity.apsGoodsSaleProjectConfig.*;
import com.olivia.peanut.aps.api.entity.apsGoodsSaleProjectConfig.ApsGoodsSaleProjectConfigSale2ProjectRes.Info;
import com.olivia.peanut.aps.mapper.ApsGoodsSaleProjectConfigMapper;
import com.olivia.peanut.aps.model.ApsGoodsSaleProjectConfig;
import com.olivia.peanut.aps.model.ApsProjectConfig;
import com.olivia.peanut.aps.model.ApsSaleConfig;
import com.olivia.peanut.aps.service.ApsGoodsSaleProjectConfigService;
import com.olivia.peanut.aps.service.ApsProjectConfigService;
import com.olivia.peanut.aps.service.ApsSaleConfigService;
import com.olivia.sdk.ann.SetUserName;
import com.olivia.sdk.comment.ServiceComment;
import com.olivia.sdk.utils.$;
import com.olivia.sdk.utils.DynamicsPage;
import jakarta.annotation.Resource;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.aop.framework.AopContext;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.*;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * (ApsGoodsSaleProjectConfig)表服务实现类
 *
 * @author peanut
 * @since 2024-04-27 16:07:22
 */
@Slf4j
@Service("apsGoodsSaleProjectConfigService")
@Transactional
public class ApsGoodsSaleProjectConfigServiceImpl extends MPJBaseServiceImpl<ApsGoodsSaleProjectConfigMapper, ApsGoodsSaleProjectConfig> implements
    ApsGoodsSaleProjectConfigService {

  private final static int TIME_OUT = 5;
  //  final static Cache<Long, Map<String, String>> cache = CacheBuilder.newBuilder().maximumSize(100).expireAfterWrite(TIME_OUT, TimeUnit.MINUTES).build();
  final static Cache<Long, Map<Long, List<ApsGoodsSaleProjectConfig>>> apsGoodsSaleProjectConfigCache = CacheBuilder.newBuilder().maximumSize(100)
      .expireAfterWrite(TIME_OUT, TimeUnit.MINUTES).build();
  final static Cache<String, Map<String, ApsSaleConfig>> saleConfigSaleCodeCache = CacheBuilder.newBuilder().maximumSize(100).expireAfterWrite(TIME_OUT, TimeUnit.MINUTES).build();
  final static Cache<String, Map<Long, ApsSaleConfig>> saleConfigIdCache = CacheBuilder.newBuilder().maximumSize(100).expireAfterWrite(TIME_OUT, TimeUnit.MINUTES).build();

  final static Cache<String, Map<Long, ApsProjectConfig>> projectConfigCache = CacheBuilder.newBuilder().maximumSize(100).expireAfterWrite(TIME_OUT, TimeUnit.MINUTES).build();

  @Resource
  ApsSaleConfigService apsSaleConfigService;

  @Resource
  ApsProjectConfigService apsProjectConfigService;

  public @Override ApsGoodsSaleProjectConfigQueryListRes queryList(ApsGoodsSaleProjectConfigQueryListReq req) {

    MPJLambdaWrapper<ApsGoodsSaleProjectConfig> q = getWrapper(req.getData());
    List<ApsGoodsSaleProjectConfig> list = this.list(q);

    List<ApsGoodsSaleProjectConfigDto> dataList = list.stream().map(t -> $.copy(t, ApsGoodsSaleProjectConfigDto.class)).collect(Collectors.toList());
    //  this.setName(dataList);
    ((ApsGoodsSaleProjectConfigService) AopContext.currentProxy()).setName(dataList);

    return new ApsGoodsSaleProjectConfigQueryListRes().setDataList(dataList);
  }

  public @Override DynamicsPage<ApsGoodsSaleProjectConfigExportQueryPageListInfoRes> queryPageList(ApsGoodsSaleProjectConfigExportQueryPageListReq req) {

    DynamicsPage<ApsGoodsSaleProjectConfig> page = new DynamicsPage<>();
    page.setCurrent(req.getPageNum()).setSize(req.getPageSize());
    setQueryListHeader(page);
    MPJLambdaWrapper<ApsGoodsSaleProjectConfig> q = getWrapper(req.getData());
    List<ApsGoodsSaleProjectConfigExportQueryPageListInfoRes> records;
    if (Boolean.TRUE.equals(req.getQueryPage())) {
      IPage<ApsGoodsSaleProjectConfig> list = this.page(page, q);
      IPage<ApsGoodsSaleProjectConfigExportQueryPageListInfoRes> dataList = list.convert(t -> $.copy(t, ApsGoodsSaleProjectConfigExportQueryPageListInfoRes.class));
      records = dataList.getRecords();
    } else {
      records = $.copyList(this.list(q), ApsGoodsSaleProjectConfigExportQueryPageListInfoRes.class);
    }

    // 类型转换，  更换枚举 等操作

    List<ApsGoodsSaleProjectConfigExportQueryPageListInfoRes> listInfoRes = $.copyList(records, ApsGoodsSaleProjectConfigExportQueryPageListInfoRes.class);
    // this.setName(listInfoRes);

    ((ApsGoodsSaleProjectConfigService) AopContext.currentProxy()).setName(listInfoRes);
    return DynamicsPage.init(page, listInfoRes);
  }

  @Override
  @SneakyThrows(value = {ExecutionException.class})
  public ApsGoodsSaleProjectConfigSale2ProjectRes sale2project(ApsGoodsSaleProjectConfigSale2ProjectReq req) {
//    log.info("Sale2Project {} {}",req.getSaleConfig(),req.getConvertCount());
    Map<String, ApsSaleConfig> apsSaleConfigMap = saleConfigSaleCodeCache.get("",
        () -> this.apsSaleConfigService.list().stream().collect(Collectors.toMap(ApsSaleConfig::getSaleCode, Function.identity())));

    Map<Long, ApsSaleConfig> saleConfigIdMap = saleConfigIdCache.get("",
        () -> this.apsSaleConfigService.list().stream().collect(Collectors.toMap(ApsSaleConfig::getId, Function.identity())));

    Map<Long, List<ApsGoodsSaleProjectConfig>> projectConfigMap = apsGoodsSaleProjectConfigCache.get(req.getGoodsId(), () -> this.list(
            new LambdaQueryWrapper<ApsGoodsSaleProjectConfig>().eq(ApsGoodsSaleProjectConfig::getGoodsId, req.getGoodsId()).ne(ApsGoodsSaleProjectConfig::getQuantity, 0)).stream()
        .collect(Collectors.groupingBy(t -> saleConfigIdMap.getOrDefault(t.getSaleConfigId(), new ApsSaleConfig()).getId())));

    Map<Long, ApsProjectConfig> apsProjectConfigMap = projectConfigCache.get("",
        () -> this.apsProjectConfigService.list().stream().collect(Collectors.toMap(ApsProjectConfig::getId, Function.identity())));

    List<String> saleConfigList = Arrays.stream(req.getSaleConfig().split(",")).toList();
    String str = saleConfigList.stream().filter(t -> !apsSaleConfigMap.containsKey(t)).collect(Collectors.joining(","));
    $.assertTrueCanIgnoreException(StringUtils.isBlank(str), "销售配置[" + str + "]不存在");

    Map<String, BigDecimal> result = new HashMap<>();
    IntStream.range(0, req.getConvertCount().intValue()).forEach(index -> {
      List<Long> projectConfigList = new ArrayList<>();
      saleConfigList.forEach(saleCode -> {
        ApsSaleConfig apsSaleConfig = apsSaleConfigMap.get(saleCode);
        List<ApsGoodsSaleProjectConfig> goodsSaleProjectConfigList = projectConfigMap.get(apsSaleConfig.getId());
        if (CollUtil.isEmpty(goodsSaleProjectConfigList)) {
          log.warn("sale2project  goodsSaleProjectConfigList is null {} ", apsSaleConfig.getId());
          return;
        }
        if (goodsSaleProjectConfigList.size() == 1) {
          ApsGoodsSaleProjectConfig apsGoodsSaleProjectConfig = goodsSaleProjectConfigList.getFirst();
          projectConfigList.add(apsGoodsSaleProjectConfig.getProjectConfigId());
          return;
        }
        for (ApsGoodsSaleProjectConfig projectConfig : goodsSaleProjectConfigList) {
          Long currentIndex = projectConfig.getCurrentIndex();
          if (currentIndex <= projectConfig.getQuantity()) {
            projectConfigList.add(projectConfig.getProjectConfigId());
            currentIndex++;
            projectConfig.setCurrentIndex(currentIndex);
            break;
          }
        }
        long count = goodsSaleProjectConfigList.stream().filter(t -> t.getCurrentIndex() > t.getQuantity()).count();
        if (count == goodsSaleProjectConfigList.size()) {
          goodsSaleProjectConfigList.forEach(t -> t.setCurrentIndex(1L));
        }
      });
      result.compute(projectConfigList.stream().map(t -> apsProjectConfigMap.get(t).getSaleCode()).sorted().collect(Collectors.joining(",")),
          (k, v) -> v == null ? new BigDecimal(1) : v.add(new BigDecimal(1)));
    });

//    RunUtils.run("修改索引:", List.of(() -> {
//      List<List<ApsGoodsSaleProjectConfig>> projectConfigList = new ArrayList<>(projectConfigMap.values());
//      List<ApsGoodsSaleProjectConfig> updateList = projectConfigList.stream().flatMap(Collection::stream).toList();
//      this.updateBatchById(updateList);
//    }));

    ApsGoodsSaleProjectConfigSale2ProjectRes res = new ApsGoodsSaleProjectConfigSale2ProjectRes();
    List<Info> list = result.keySet().stream().sorted().map(t -> new Info().setProjectCode(t).setConvertCount(result.get(t))).toList();
    return res.setDataList(list).setBizKey(req.getBizKey()).setId(req.getId());
  }
// 以下为私有对象封装

  @SetUserName
  public @Override void setName(List<? extends ApsGoodsSaleProjectConfigDto> apsGoodsSaleProjectConfigDtoList) {

    if (CollUtil.isEmpty(apsGoodsSaleProjectConfigDtoList)) {
    }


  }


  private MPJLambdaWrapper<ApsGoodsSaleProjectConfig> getWrapper(ApsGoodsSaleProjectConfigDto obj) {
    MPJLambdaWrapper<ApsGoodsSaleProjectConfig> q = new MPJLambdaWrapper<>();

    if (Objects.nonNull(obj)) {
      q.eq(Objects.nonNull(obj.getGoodsId()), ApsGoodsSaleProjectConfig::getGoodsId, obj.getGoodsId())
          .eq(Objects.nonNull(obj.getFactoryId()), ApsGoodsSaleProjectConfig::getFactoryId, obj.getFactoryId())
          .eq(Objects.nonNull(obj.getProjectConfigId()), ApsGoodsSaleProjectConfig::getProjectConfigId, obj.getProjectConfigId())
          .eq(StringUtils.isNoneBlank(obj.getProjectConfigName()), ApsGoodsSaleProjectConfig::getProjectConfigName, obj.getProjectConfigName())
          .eq(Objects.nonNull(obj.getProjectConfigParentId()), ApsGoodsSaleProjectConfig::getProjectConfigParentId, obj.getProjectConfigParentId())
          .eq(Objects.nonNull(obj.getQuantity()), ApsGoodsSaleProjectConfig::getQuantity, obj.getQuantity())
          .eq(Objects.nonNull(obj.getSaleConfigId()), ApsGoodsSaleProjectConfig::getSaleConfigId, obj.getSaleConfigId())
          .eq(StringUtils.isNoneBlank(obj.getSaleConfigName()), ApsGoodsSaleProjectConfig::getSaleConfigName, obj.getSaleConfigName())
          .eq(Objects.nonNull(obj.getSaleConfigParentId()), ApsGoodsSaleProjectConfig::getSaleConfigParentId, obj.getSaleConfigParentId())

      ;
    }
    q.orderByDesc(ApsGoodsSaleProjectConfig::getId);
    return q;

  }

  private void setQueryListHeader(DynamicsPage<ApsGoodsSaleProjectConfig> page) {

    ServiceComment.header(page, "ApsGoodsSaleProjectConfigService#queryPageList");

  }


}

