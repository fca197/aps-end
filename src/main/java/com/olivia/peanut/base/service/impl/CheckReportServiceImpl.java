package com.olivia.peanut.base.service.impl;

import cn.hutool.core.collection.CollUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.github.yulichang.base.MPJBaseServiceImpl;
import com.github.yulichang.wrapper.MPJLambdaWrapper;
import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.olivia.peanut.base.api.entity.checkReport.*;
import com.olivia.peanut.base.model.Factory;
import com.olivia.peanut.base.service.FactoryService;
import com.olivia.peanut.portal.mapper.CheckReportMapper;
import com.olivia.peanut.portal.model.CheckReport;
import com.olivia.peanut.portal.service.CheckReportService;
import com.olivia.sdk.utils.$;
import com.olivia.sdk.utils.DynamicsPage;
import com.olivia.sdk.utils.RunUtils;
import com.olivia.sdk.utils.Str;
import jakarta.annotation.Resource;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

import static java.lang.Boolean.TRUE;

/**
 * 报表信息(CheckReport)表服务实现类
 *
 * @author makejava
 * @since 2024-03-14 13:31:35
 */
@Service("checkReportService")
@Transactional
public class CheckReportServiceImpl extends MPJBaseServiceImpl<CheckReportMapper, CheckReport> implements CheckReportService {

  final static Cache<String, Map<String, String>> cache = CacheBuilder.newBuilder().maximumSize(100).expireAfterWrite(30, TimeUnit.MINUTES).build();
  @Resource
  FactoryService factoryService;

  public @Override CheckReportQueryListRes queryList(CheckReportQueryListReq req) {

    MPJLambdaWrapper<CheckReport> q = getWrapper(req.getData());
    List<CheckReport> list = this.list(q);

    List<CheckReportDto> dataList = list.stream().map(t -> $.copy(t, CheckReportDto.class)).collect(Collectors.toList());
    setAllName(dataList);
    return new CheckReportQueryListRes().setDataList(dataList);
  }

  private void setAllName(List<? extends CheckReportDto> checkReportDtoList) {
    if (CollUtil.isNotEmpty(checkReportDtoList)) {
      List<Runnable> runnableList = new ArrayList<>();
      runnableList.add(() -> {
        Set<Long> idList = checkReportDtoList.stream().map(CheckReportDto::getFactoryId).collect(Collectors.toSet());
        Map<Long, String> m = factoryService.listByIds(idList).stream().collect(Collectors.toMap(Factory::getId, Factory::getFactoryName));
        checkReportDtoList.forEach(t -> t.setFactoryName(m.get(t.getFactoryId())));
      });
      runnableList.add(() ->
          checkReportDtoList.forEach(t -> t.setIsOverStr(Str.booleanToStr(t.getIsOver())))
      );
      RunUtils.run("设置名称", runnableList);
    }
  }

  public @Override DynamicsPage<CheckReportExportQueryPageListInfoRes> queryPageList(CheckReportExportQueryPageListReq req) {

    DynamicsPage<CheckReport> page = new DynamicsPage<>();
    page.setCurrent(req.getPageNum()).setSize(req.getPageSize());
    setQueryListHeader(page);
    MPJLambdaWrapper<CheckReport> q = getWrapper(req.getData());
    List<CheckReportExportQueryPageListInfoRes> records;
    if (TRUE.equals(req.getQueryPage())) {
      IPage<CheckReport> list = this.page(page, q);
      IPage<CheckReportExportQueryPageListInfoRes> dataList = list.convert(t -> $.copy(t, CheckReportExportQueryPageListInfoRes.class));
      records = dataList.getRecords();
    } else {
      records = $.copyList(this.list(q), CheckReportExportQueryPageListInfoRes.class);
    }

    // 类型转换，  更换枚举 等操作
    List<CheckReportExportQueryPageListInfoRes> listInfoRes = $.copyList(records, CheckReportExportQueryPageListInfoRes.class);
    setAllName(listInfoRes);
    return DynamicsPage.init(page, listInfoRes);
  }

  // 以下为私有对象封装


  private MPJLambdaWrapper<CheckReport> getWrapper(CheckReportDto obj) {
    MPJLambdaWrapper<CheckReport> q = new MPJLambdaWrapper<>();

    if (Objects.nonNull(obj)) {
      q
          .eq(Objects.nonNull(obj.getId()), CheckReport::getId, obj.getId())
          .eq(Objects.nonNull(obj.getTenantId()), CheckReport::getTenantId, obj.getTenantId())
          .eq(Objects.nonNull(obj.getFactoryId()), CheckReport::getFactoryId, obj.getFactoryId())
          .eq(StringUtils.isNoneBlank(obj.getReportCode()), CheckReport::getReportCode, obj.getReportCode())
          .likeRight(StringUtils.isNoneBlank(obj.getReportName()), CheckReport::getReportName, obj.getReportName())
          .eq(Objects.nonNull(obj.getCreateTime()), CheckReport::getCreateTime, obj.getCreateTime())
          .eq(Objects.nonNull(obj.getCreateBy()), CheckReport::getCreateBy, obj.getCreateBy())
          .eq(Objects.nonNull(obj.getUpdateTime()), CheckReport::getUpdateTime, obj.getUpdateTime())
          .eq(Objects.nonNull(obj.getUpdateBy()), CheckReport::getUpdateBy, obj.getUpdateBy())
          .eq(Objects.nonNull(obj.getVersionNum()), CheckReport::getVersionNum, obj.getVersionNum())
          .eq(Objects.nonNull(obj.getIsOver()), CheckReport::getIsOver, obj.getIsOver())
      ;
    }

    return q.orderByDesc(CheckReport::getId);

  }

  private void setQueryListHeader(DynamicsPage<CheckReport> page) {
    page
        .addHeader("id", "序号")
//        .addHeader("tenantName", "租户")
        .addHeader("factoryName", "工厂")
        .addHeader("reportCode", "报表编码")
        .addHeader("reportName", "报表名称")
        .addHeader("isOverStr", "是否结束")
        .addHeader("createTime", "创建时间")
        .addHeader("updateTime", "更新时间")
    ;
  }


}

