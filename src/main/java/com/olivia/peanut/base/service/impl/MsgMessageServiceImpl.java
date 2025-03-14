package com.olivia.peanut.base.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.github.yulichang.base.MPJBaseServiceImpl;
import com.github.yulichang.wrapper.MPJLambdaWrapper;
import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.olivia.peanut.base.api.entity.msgMessage.*;
import com.olivia.peanut.base.mapper.MsgMessageMapper;
import com.olivia.peanut.base.model.MsgMessage;
import com.olivia.peanut.base.model.MsgMessageRead;
import com.olivia.peanut.base.service.MsgMessageReadService;
import com.olivia.peanut.base.service.MsgMessageService;
import com.olivia.sdk.filter.LoginUser;
import com.olivia.sdk.filter.LoginUserContext;
import com.olivia.sdk.utils.$;
import com.olivia.sdk.utils.DynamicsPage;
import com.olivia.sdk.utils.Str;
import jakarta.annotation.Resource;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

import static java.lang.Boolean.FALSE;
import static java.lang.Boolean.TRUE;

/**
 * (MsgMessage)表服务实现类
 *
 * @author peanut
 * @since 2024-03-23 19:05:39
 */
@Service("msgMessageService")
@Transactional
public class MsgMessageServiceImpl extends MPJBaseServiceImpl<MsgMessageMapper, MsgMessage> implements MsgMessageService {

  final static Cache<String, Map<String, String>> cache = CacheBuilder.newBuilder().maximumSize(100).expireAfterWrite(30, TimeUnit.MINUTES).build();
  @Resource
  MsgMessageReadService msgMessageReadService;

  public @Override MsgMessageQueryListRes queryList(MsgMessageQueryListReq req) {

    MPJLambdaWrapper<MsgMessage> q = getWrapper(req.getData());
    List<MsgMessage> list = this.list(q);
    List<MsgMessageDto> dataList = list.stream().map(t -> $.copy(t, MsgMessageDto.class)).collect(Collectors.toList());
    return new MsgMessageQueryListRes().setDataList(dataList);
  }

  public @Override DynamicsPage<MsgMessageExportQueryPageListInfoRes> queryPageList(MsgMessageExportQueryPageListReq req) {

    DynamicsPage<MsgMessage> page = new DynamicsPage<>();
    page.setCurrent(req.getPageNum()).setSize(req.getPageSize());
    setQueryListHeader(page);
    MPJLambdaWrapper<MsgMessage> q = getWrapper(req.getData());
    List<MsgMessageExportQueryPageListInfoRes> records;
    if (TRUE.equals(req.getQueryPage())) {
      IPage<MsgMessage> list = this.page(page, q);
      IPage<MsgMessageExportQueryPageListInfoRes> dataList = list.convert(t -> $.copy(t, MsgMessageExportQueryPageListInfoRes.class));
      records = dataList.getRecords();
    } else {
      records = $.copyList(this.list(q), MsgMessageExportQueryPageListInfoRes.class);
    }
    LoginUser loginUser = LoginUserContext.getLoginUser();
    MsgMessageRead messageRead = msgMessageReadService.getOne(new LambdaQueryWrapper<MsgMessageRead>().eq(MsgMessageRead::getUserId, loginUser.getId()).last(Str.LIMIT_1));

    // 类型转换，  更换枚举 等操作
    List<MsgMessageExportQueryPageListInfoRes> listInfoRes = $.copyList(records, MsgMessageExportQueryPageListInfoRes.class);
    if (Objects.nonNull(messageRead) && Objects.nonNull(messageRead.getLastReadTime())) {
      listInfoRes.forEach(t -> {
        if (t.getCreateTime().isAfter(messageRead.getLastReadTime())) {
          t.setIsRead(false);
        } else {
          t.setIsRead(TRUE);
        }
      });
    } else {
      listInfoRes.forEach(t -> t.setIsRead(FALSE));
    }

    return DynamicsPage.init(page, listInfoRes);
  }

  // 以下为私有对象封装


  private MPJLambdaWrapper<MsgMessage> getWrapper(MsgMessageDto obj) {
    MPJLambdaWrapper<MsgMessage> q = new MPJLambdaWrapper<>();

    if (Objects.nonNull(obj)) {
      q
          .eq(Objects.nonNull(obj.getId()), MsgMessage::getId, obj.getId())
          .eq(Objects.nonNull(obj.getTenantId()), MsgMessage::getTenantId, obj.getTenantId())
          .eq(Objects.nonNull(obj.getIsAll()), MsgMessage::getIsAll, obj.getIsAll())
          .eq(StringUtils.isNoneBlank(obj.getMessageTitle()), MsgMessage::getMessageTitle, obj.getMessageTitle())
          .eq(StringUtils.isNoneBlank(obj.getMessageContext()), MsgMessage::getMessageContext, obj.getMessageContext())
          .eq(Objects.nonNull(obj.getCreateTime()), MsgMessage::getCreateTime, obj.getCreateTime())
          .eq(Objects.nonNull(obj.getUpdateTime()), MsgMessage::getUpdateTime, obj.getUpdateTime())

      ;
    }
    q.orderByDesc(MsgMessage::getId);
    return q;

  }

  private void setQueryListHeader(DynamicsPage<MsgMessage> page) {
    page
        .addHeader("id", "$column.comment")
        .addHeader("tenantId", "$column.comment")
        .addHeader("isAll", "$column.comment")
        .addHeader("messageTitle", "$column.comment")
        .addHeader("messageContext", "$column.comment")
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

