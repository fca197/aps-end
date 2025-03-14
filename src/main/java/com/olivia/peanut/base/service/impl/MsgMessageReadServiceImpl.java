package com.olivia.peanut.base.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.github.yulichang.base.MPJBaseServiceImpl;
import com.github.yulichang.wrapper.MPJLambdaWrapper;
import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.olivia.peanut.base.api.entity.msgMessageRead.*;
import com.olivia.peanut.base.mapper.MsgMessageReadMapper;
import com.olivia.peanut.base.model.MsgMessageRead;
import com.olivia.peanut.base.service.MsgMessageReadService;
import com.olivia.sdk.utils.$;
import com.olivia.sdk.utils.DynamicsPage;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

/**
 * (MsgMessageRead)表服务实现类
 *
 * @author peanut
 * @since 2024-03-23 19:17:48
 */
@Service("msgMessageReadService")
@Transactional
public class MsgMessageReadServiceImpl extends MPJBaseServiceImpl<MsgMessageReadMapper, MsgMessageRead> implements MsgMessageReadService {

  final static Cache<String, Map<String, String>> cache = CacheBuilder.newBuilder().maximumSize(100).expireAfterWrite(30, TimeUnit.MINUTES).build();

  public @Override MsgMessageReadQueryListRes queryList(MsgMessageReadQueryListReq req) {

    MPJLambdaWrapper<MsgMessageRead> q = getWrapper(req.getData());
    List<MsgMessageRead> list = this.list(q);

    List<MsgMessageReadDto> dataList = list.stream().map(t -> $.copy(t, MsgMessageReadDto.class)).collect(Collectors.toList());

    return new MsgMessageReadQueryListRes().setDataList(dataList);
  }


  public @Override DynamicsPage<MsgMessageReadExportQueryPageListInfoRes> queryPageList(MsgMessageReadExportQueryPageListReq req) {

    DynamicsPage<MsgMessageRead> page = new DynamicsPage<>();
    page.setCurrent(req.getPageNum()).setSize(req.getPageSize());
    setQueryListHeader(page);
    MPJLambdaWrapper<MsgMessageRead> q = getWrapper(req.getData());
    List<MsgMessageReadExportQueryPageListInfoRes> records;
    if (Boolean.TRUE.equals(req.getQueryPage())) {
      IPage<MsgMessageRead> list = this.page(page, q);
      IPage<MsgMessageReadExportQueryPageListInfoRes> dataList = list.convert(t -> $.copy(t, MsgMessageReadExportQueryPageListInfoRes.class));
      records = dataList.getRecords();
    } else {
      records = $.copyList(this.list(q), MsgMessageReadExportQueryPageListInfoRes.class);
    }

    // 类型转换，  更换枚举 等操作
    List<MsgMessageReadExportQueryPageListInfoRes> listInfoRes = $.copyList(records, MsgMessageReadExportQueryPageListInfoRes.class);

    return DynamicsPage.init(page, listInfoRes);
  }

  // 以下为私有对象封装


  private MPJLambdaWrapper<MsgMessageRead> getWrapper(MsgMessageReadDto obj) {
    MPJLambdaWrapper<MsgMessageRead> q = new MPJLambdaWrapper<>();

    if (Objects.nonNull(obj)) {
      q
          .eq(Objects.nonNull(obj.getUserId()), MsgMessageRead::getUserId, obj.getUserId())
          .eq(Objects.nonNull(obj.getLastReadTime()), MsgMessageRead::getLastReadTime, obj.getLastReadTime())
          .orderByDesc(MsgMessageRead::getId)
      ;
    }

    return q;

  }

  private void setQueryListHeader(DynamicsPage<MsgMessageRead> page) {
    page
        .addHeader("id", "$column.comment")
        .addHeader("tenantId", "$column.comment")
        .addHeader("userId", "$column.comment")
        .addHeader("lastReadTime", "$column.comment")
        .addHeader("isDelete", "$column.comment")
        .addHeader("createTime", "$column.comment")
        .addHeader("createBy", "$column.comment")
        .addHeader("updateTime", "$column.comment")
        .addHeader("updateBy", "$column.comment")
        .addHeader("traceId", "$column.comment")
        .addHeader("version", "$column.comment")
        .addHeader("isUsed", "$column.comment")
        .addHeader("versionNum", "$column.comment")
    ;
  }


}

