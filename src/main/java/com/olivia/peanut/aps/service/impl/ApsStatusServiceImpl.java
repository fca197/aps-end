package com.olivia.peanut.aps.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.github.yulichang.base.MPJBaseServiceImpl;
import com.github.yulichang.wrapper.MPJLambdaWrapper;
import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.olivia.peanut.aps.api.entity.apsStatus.*;
import com.olivia.peanut.aps.mapper.ApsStatusMapper;
import com.olivia.peanut.aps.model.ApsStatus;
import com.olivia.peanut.aps.service.ApsStatusService;
import com.olivia.sdk.ann.SetUserName;
import com.olivia.sdk.comment.ServiceComment;
import com.olivia.sdk.utils.$;
import com.olivia.sdk.utils.DynamicsPage;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;
import lombok.SneakyThrows;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * (ApsStatus)表服务实现类
 *
 * @author peanut
 * @since 2024-04-01 10:50:12
 */
@Service("apsStatusService")
@Transactional
public class ApsStatusServiceImpl extends MPJBaseServiceImpl<ApsStatusMapper, ApsStatus> implements ApsStatusService {

  final static Cache<String, List<ApsStatus>> cache = CacheBuilder.newBuilder().maximumSize(100).expireAfterWrite(30, TimeUnit.MINUTES).build();


  public @Override ApsStatusQueryListRes queryList(ApsStatusQueryListReq req) {

    MPJLambdaWrapper<ApsStatus> q = getWrapper(req.getData());
    List<ApsStatus> list = this.list(q);

    List<ApsStatusDto> dataList = list.stream().map(t -> $.copy(t, ApsStatusDto.class)).collect(Collectors.toList());

    return new ApsStatusQueryListRes().setDataList(dataList);
  }


  public @Override DynamicsPage<ApsStatusExportQueryPageListInfoRes> queryPageList(ApsStatusExportQueryPageListReq req) {

    DynamicsPage<ApsStatus> page = new DynamicsPage<>();
    page.setCurrent(req.getPageNum()).setSize(req.getPageSize());
    setQueryListHeader(page);
    MPJLambdaWrapper<ApsStatus> q = getWrapper(req.getData());
    List<ApsStatusExportQueryPageListInfoRes> records;
    if (Boolean.TRUE.equals(req.getQueryPage())) {
      IPage<ApsStatus> list = this.page(page, q);
      IPage<ApsStatusExportQueryPageListInfoRes> dataList = list.convert(t -> $.copy(t, ApsStatusExportQueryPageListInfoRes.class));
      records = dataList.getRecords();
    } else {
      records = $.copyList(this.list(q), ApsStatusExportQueryPageListInfoRes.class);
    }

    // 类型转换，  更换枚举 等操作

    List<ApsStatusExportQueryPageListInfoRes> listInfoRes = $.copyList(records, ApsStatusExportQueryPageListInfoRes.class);

    return DynamicsPage.init(page, listInfoRes);
  }


  @SetUserName
  public @Override void setName(List<? extends ApsStatusDto> apsStatusDtoList) {

  }

  @Override
  @SneakyThrows
  public List<ApsStatus> list() {
    return cache.get("all", super::list);
  }

  // 以下为私有对象封装


  private MPJLambdaWrapper<ApsStatus> getWrapper(ApsStatusDto obj) {
    MPJLambdaWrapper<ApsStatus> q = new MPJLambdaWrapper<>();

    if (Objects.nonNull(obj)) {
      q
          .eq(StringUtils.isNoneBlank(obj.getStatusCode()), ApsStatus::getStatusCode, obj.getStatusCode())
          .eq(StringUtils.isNoneBlank(obj.getStatusName()), ApsStatus::getStatusName, obj.getStatusName())
      ;
    }
    q.orderByDesc(ApsStatus::getId);
    return q;

  }

  private void setQueryListHeader(DynamicsPage<ApsStatus> page) {

    ServiceComment.header(page, "ApsStatusService#queryPageList");

  }


}

