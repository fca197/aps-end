package com.olivia.peanut.portal.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.github.yulichang.base.MPJBaseServiceImpl;
import com.github.yulichang.wrapper.MPJLambdaWrapper;
import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.olivia.peanut.aps.con.ApsStr;
import com.olivia.peanut.portal.api.entity.processLine.*;
import com.olivia.peanut.portal.mapper.ProcessLineMapper;
import com.olivia.peanut.portal.model.ProcessLine;
import com.olivia.peanut.portal.service.ProcessLineService;
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
 * 流水线信息(ProcessLine)表服务实现类
 *
 * @author makejava
 * @since 2024-03-11 10:55:21
 */
@Service("processLineService")
@Transactional
public class ProcessLineServiceImpl extends MPJBaseServiceImpl<ProcessLineMapper, ProcessLine> implements ProcessLineService {

  final static Cache<String, Map<String, String>> cache = CacheBuilder.newBuilder().maximumSize(100).expireAfterWrite(30, TimeUnit.MINUTES).build();

  public @Override ProcessLineQueryListRes queryList(ProcessLineQueryListReq req) {

    MPJLambdaWrapper<ProcessLine> q = getWrapper(req.getData());
    List<ProcessLine> list = this.list(q);

    List<ProcessLineQueryListRes.Info> dataList = list.stream().map(t -> $.copy(t, ProcessLineQueryListRes.Info.class)).collect(Collectors.toList());

    return new ProcessLineQueryListRes().setDataList(dataList);
  }


  public @Override DynamicsPage<ProcessLineExportQueryPageListInfoRes> queryPageList(ProcessLineExportQueryPageListReq req) {

    DynamicsPage<ProcessLine> page = new DynamicsPage<>();
    page.setCurrent(req.getPageNum()).setSize(req.getPageSize());
    setQueryListHeader(page);
    MPJLambdaWrapper<ProcessLine> q = getWrapper(req.getData());
    List<ProcessLineExportQueryPageListInfoRes> records;
    if (Boolean.TRUE.equals(req.getQueryPage())) {
      IPage<ProcessLine> list = this.page(page, q);
      IPage<ProcessLineExportQueryPageListInfoRes> dataList = list.convert(t -> $.copy(t, ProcessLineExportQueryPageListInfoRes.class));
      records = dataList.getRecords();
    } else {
      records = $.copyList(this.list(q), ProcessLineExportQueryPageListInfoRes.class);
    }

    // 类型转换，  更换枚举 等操作
    List<ProcessLineExportQueryPageListInfoRes> listInfoRes = $.copyList(records, ProcessLineExportQueryPageListInfoRes.class);

    return DynamicsPage.init(page, listInfoRes);
  }

  // 以下为私有对象封装


  private MPJLambdaWrapper<ProcessLine> getWrapper(ProcessLineDto obj) {
    MPJLambdaWrapper<ProcessLine> q = new MPJLambdaWrapper<>();

    if (Objects.nonNull(obj)) {
      q
          .eq(Objects.nonNull(obj.getId()), ProcessLine::getId, obj.getId())
          .eq(Objects.nonNull(obj.getTenantId()), ProcessLine::getTenantId, obj.getTenantId())
          .eq(Objects.nonNull(obj.getFactoryId()), ProcessLine::getFactoryId, obj.getFactoryId())
          .eq(Objects.nonNull(obj.getProcessId()), ProcessLine::getProcessId, obj.getProcessId())
          .eq(StringUtils.isNoneBlank(obj.getLineName()), ProcessLine::getLineName, obj.getLineName())
          .eq(StringUtils.isNoneBlank(obj.getLineCode()), ProcessLine::getLineCode, obj.getLineCode())
          .eq(StringUtils.isNoneBlank(obj.getLineDesc()), ProcessLine::getLineDesc, obj.getLineDesc())
          .eq(Objects.nonNull(obj.getLineSort()), ProcessLine::getLineSort, obj.getLineSort())
          .eq(Objects.nonNull(obj.getCreateTime()), ProcessLine::getCreateTime, obj.getCreateTime())
          .eq(Objects.nonNull(obj.getCreateBy()), ProcessLine::getCreateBy, obj.getCreateBy())
          .eq(Objects.nonNull(obj.getUpdateTime()), ProcessLine::getUpdateTime, obj.getUpdateTime())
          .eq(Objects.nonNull(obj.getUpdateBy()), ProcessLine::getUpdateBy, obj.getUpdateBy())
          .orderByDesc(ProcessLine::getId)
      ;
    }

    return q;

  }

  private void setQueryListHeader(DynamicsPage<ProcessLine> page) {
    page
        .addHeader("id", "id")
        .addHeader("tenantId", "所属租户id")
        .addHeader(ApsStr.FACTORY_ID, "所属工厂id")
        .addHeader("processId", "所属进程id")
        .addHeader("lineName", "线别名称")
        .addHeader("lineCode", "线别编码")
        .addHeader("lineDesc", "线别描述")
        .addHeader("lineSort", "线别排序")
        .addHeader("isDelete", "是否删除(0:否 1:是)")
        .addHeader("createTime", "创建时间")
        .addHeader("createBy", "创建人id")
        .addHeader("updateTime", "更新时间")
        .addHeader("updateBy", "更新人id")
        .addHeader("traceId", "链路追踪ID")
    ;
  }


}

