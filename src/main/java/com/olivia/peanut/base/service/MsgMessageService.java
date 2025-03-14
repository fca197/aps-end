package com.olivia.peanut.base.service;

import com.github.yulichang.base.MPJBaseService;
import com.olivia.peanut.base.api.entity.msgMessage.MsgMessageExportQueryPageListInfoRes;
import com.olivia.peanut.base.api.entity.msgMessage.MsgMessageExportQueryPageListReq;
import com.olivia.peanut.base.api.entity.msgMessage.MsgMessageQueryListReq;
import com.olivia.peanut.base.api.entity.msgMessage.MsgMessageQueryListRes;
import com.olivia.peanut.base.model.MsgMessage;
import com.olivia.sdk.utils.DynamicsPage;

/**
 * (MsgMessage)表服务接口
 *
 * @author peanut
 * @since 2024-03-23 19:05:39
 */
public interface MsgMessageService extends MPJBaseService<MsgMessage> {

  MsgMessageQueryListRes queryList(MsgMessageQueryListReq req);

  DynamicsPage<MsgMessageExportQueryPageListInfoRes> queryPageList(MsgMessageExportQueryPageListReq req);

}

