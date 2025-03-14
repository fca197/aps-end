package com.olivia.peanut.base.api.impl;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.github.yulichang.wrapper.MPJLambdaWrapper;
import com.olivia.peanut.base.api.MsgMessageApi;
import com.olivia.peanut.base.api.entity.msgMessage.*;

import com.olivia.peanut.portal.api.impl.listener.MsgMessageImportListener;
import com.olivia.peanut.base.model.MsgMessage;
import com.olivia.peanut.base.model.MsgMessageRead;
import com.olivia.peanut.base.service.MsgMessageReadService;
import com.olivia.peanut.base.service.MsgMessageService;
import com.olivia.sdk.filter.LoginUser;
import com.olivia.sdk.filter.LoginUserContext;
import com.olivia.sdk.utils.$;
import com.olivia.sdk.utils.BaseEntity;
import com.olivia.sdk.utils.DynamicsPage;
import com.olivia.sdk.utils.PoiExcelUtil;
import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Objects;

/**
 * (MsgMessage)表服务实现类
 *
 * @author peanut
 * @since 2024-03-23 19:05:39
 */
@RestController
public class MsgMessageApiImpl implements MsgMessageApi {

  @Resource
  MsgMessageReadService msgMessageReadService;
  private @Autowired MsgMessageService msgMessageService;

  /****
   * insert
   *
   */
  public @Override MsgMessageInsertRes insert(MsgMessageInsertReq req) {
    this.msgMessageService.save($.copy(req, MsgMessage.class));
    return new MsgMessageInsertRes().setCount(1);
  }

  /****
   * deleteByIds
   *
   */
  public @Override MsgMessageDeleteByIdListRes deleteByIdList(MsgMessageDeleteByIdListReq req) {
    msgMessageService.removeByIds(req.getIdList());
    return new MsgMessageDeleteByIdListRes();
  }

  /****
   * queryList
   *
   */
  public @Override MsgMessageQueryListRes queryList(MsgMessageQueryListReq req) {
    return msgMessageService.queryList(req);
  }

  /****
   * updateById
   *
   */
  public @Override MsgMessageUpdateByIdRes updateById(MsgMessageUpdateByIdReq req) {
    msgMessageService.updateById($.copy(req, MsgMessage.class));
    return new MsgMessageUpdateByIdRes();

  }

  public @Override DynamicsPage<MsgMessageExportQueryPageListInfoRes> queryPageList(MsgMessageExportQueryPageListReq req) {
    return msgMessageService.queryPageList(req);
  }

  public @Override void queryPageListExport(MsgMessageExportQueryPageListReq req) {
    DynamicsPage<MsgMessageExportQueryPageListInfoRes> page = queryPageList(req);
    List<MsgMessageExportQueryPageListInfoRes> list = page.getDataList();
    // 类型转换，  更换枚举 等操作
    List<MsgMessageExportQueryPageListInfoRes> listInfoRes = $.copyList(list, MsgMessageExportQueryPageListInfoRes.class);
    PoiExcelUtil.export(MsgMessageExportQueryPageListInfoRes.class, listInfoRes, "");
  }

  public @Override MsgMessageImportRes importData(@RequestParam("file") MultipartFile file) {
    List<MsgMessageImportReq> reqList = PoiExcelUtil.readData(file, new MsgMessageImportListener(), MsgMessageImportReq.class);
    // 类型转换，  更换枚举 等操作
    List<MsgMessage> readList = $.copyList(reqList, MsgMessage.class);
    boolean bool = msgMessageService.saveBatch(readList);
    int c = bool ? readList.size() : 0;
    return new MsgMessageImportRes().setCount(c);
  }

  public @Override MsgMessageQueryByIdListRes queryByIdListRes(MsgMessageQueryByIdListReq req) {
    MPJLambdaWrapper<MsgMessage> q = new MPJLambdaWrapper<MsgMessage>(MsgMessage.class)
        .selectAll(MsgMessage.class).in(MsgMessage::getId, req.getIdList());
    List<MsgMessage> list = this.msgMessageService.list(q);
    List<MsgMessageDto> dataList = $.copyList(list, MsgMessageDto.class);
    return new MsgMessageQueryByIdListRes().setDataList(dataList);
  }

  @Override
  public UnReadCountRes unReadCount(UnReadCountReq req) {
    LoginUser loginUser = LoginUserContext.getLoginUser();
    MsgMessageRead messageRead = this.msgMessageReadService.getOne(new MPJLambdaWrapper<MsgMessageRead>()
        .eq(MsgMessageRead::getUserId, loginUser.getId()));
    LambdaQueryWrapper<MsgMessage> wrapper = new LambdaQueryWrapper<MsgMessage>()
        .eq(MsgMessage::getIsAll, Boolean.TRUE);
    if (Objects.nonNull(messageRead)) {
      wrapper.ge(BaseEntity::getCreateTime, messageRead.getLastReadTime());
    }
    long count = this.msgMessageService.count(wrapper);
    return new UnReadCountRes().setCount(count);
  }
}
