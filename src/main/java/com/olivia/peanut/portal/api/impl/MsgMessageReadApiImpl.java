package com.olivia.peanut.portal.api.impl;

import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.toolkit.IdWorker;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.github.yulichang.wrapper.MPJLambdaWrapper;
import com.olivia.peanut.portal.api.MsgMessageReadApi;
import com.olivia.peanut.portal.api.entity.msgMessage.MsgMessageMaskReadReq;
import com.olivia.peanut.portal.api.entity.msgMessage.MsgMessageMaskReadRes;
import com.olivia.peanut.portal.api.entity.msgMessageRead.*;
import com.olivia.peanut.portal.api.impl.listener.MsgMessageReadImportListener;
import com.olivia.peanut.portal.model.MsgMessageRead;
import com.olivia.peanut.portal.service.MsgMessageReadService;
import com.olivia.sdk.filter.LoginUser;
import com.olivia.sdk.filter.LoginUserContext;
import com.olivia.sdk.utils.*;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * (MsgMessageRead)表服务实现类
 *
 * @author peanut
 * @since 2024-03-23 19:17:47
 */
@RestController
public class MsgMessageReadApiImpl implements MsgMessageReadApi {

  private @Autowired MsgMessageReadService msgMessageReadService;

  /****
   * insert
   *
   */
  public @Override MsgMessageReadInsertRes insert(MsgMessageReadInsertReq req) {
    this.msgMessageReadService.save($.copy(req, MsgMessageRead.class));
    return new MsgMessageReadInsertRes().setCount(1);
  }

  /****
   * deleteByIds
   *
   */
  public @Override MsgMessageReadDeleteByIdListRes deleteByIdList(MsgMessageReadDeleteByIdListReq req) {
    msgMessageReadService.removeByIds(req.getIdList());
    return new MsgMessageReadDeleteByIdListRes();
  }

  /****
   * queryList
   *
   */
  public @Override MsgMessageReadQueryListRes queryList(MsgMessageReadQueryListReq req) {
    return msgMessageReadService.queryList(req);
  }

  /****
   * updateById
   *
   */
  public @Override MsgMessageReadUpdateByIdRes updateById(MsgMessageReadUpdateByIdReq req) {
    msgMessageReadService.updateById($.copy(req, MsgMessageRead.class));
    return new MsgMessageReadUpdateByIdRes();

  }

  public @Override DynamicsPage<MsgMessageReadExportQueryPageListInfoRes> queryPageList(MsgMessageReadExportQueryPageListReq req) {
    return msgMessageReadService.queryPageList(req);
  }

  public @Override void queryPageListExport(MsgMessageReadExportQueryPageListReq req) {
    DynamicsPage<MsgMessageReadExportQueryPageListInfoRes> page = queryPageList(req);
    List<MsgMessageReadExportQueryPageListInfoRes> list = page.getDataList();
    // 类型转换，  更换枚举 等操作
    List<MsgMessageReadExportQueryPageListInfoRes> listInfoRes = $.copyList(list, MsgMessageReadExportQueryPageListInfoRes.class);
    PoiExcelUtil.export(MsgMessageReadExportQueryPageListInfoRes.class, listInfoRes, "");
  }

  public @Override MsgMessageReadImportRes importData(@RequestParam("file") MultipartFile file) {
    List<MsgMessageReadImportReq> reqList = PoiExcelUtil.readData(file, new MsgMessageReadImportListener(), MsgMessageReadImportReq.class);
    // 类型转换，  更换枚举 等操作
    List<MsgMessageRead> readList = $.copyList(reqList, MsgMessageRead.class);
    boolean bool = msgMessageReadService.saveBatch(readList);
    int c = bool ? readList.size() : 0;
    return new MsgMessageReadImportRes().setCount(c);
  }

  public @Override MsgMessageReadQueryByIdListRes queryByIdListRes(MsgMessageReadQueryByIdListReq req) {
    MPJLambdaWrapper<MsgMessageRead> q = new MPJLambdaWrapper<MsgMessageRead>(MsgMessageRead.class).selectAll(MsgMessageRead.class).in(MsgMessageRead::getId, req.getIdList());
    List<MsgMessageRead> list = this.msgMessageReadService.list(q);
    List<MsgMessageReadDto> dataList = $.copyList(list, MsgMessageReadDto.class);
    return new MsgMessageReadQueryByIdListRes().setDataList(dataList);
  }

  @Override
  public MsgMessageMaskReadRes maskRead(MsgMessageMaskReadReq req) {
    LoginUser loginUser = LoginUserContext.getLoginUser();
    MsgMessageRead msgMessageRead = this.msgMessageReadService.getOne(Wrappers.<MsgMessageRead>lambdaQuery().eq(MsgMessageRead::getUserId, loginUser.getId()).last(Str.LIMIT_1));
    if (Objects.isNull(msgMessageRead)) {
      msgMessageRead = new MsgMessageRead();
      msgMessageRead.setId(IdWorker.getId());
      msgMessageRead.setUserId(loginUser.getId()).setLastReadTime(LocalDateTime.now());
      this.msgMessageReadService.save(msgMessageRead);
    } else {
      this.msgMessageReadService.update(
          new LambdaUpdateWrapper<MsgMessageRead>().eq(BaseEntity::getId, msgMessageRead.getId()).set(MsgMessageRead::getLastReadTime, LocalDateTime.now()));
    }
    return new MsgMessageMaskReadRes().setId(msgMessageRead.getId());
  }
}
