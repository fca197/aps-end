package com.olivia.peanut.portal.service;

import com.github.yulichang.base.MPJBaseService;
import com.olivia.peanut.portal.api.entity.msgMessageRead.MsgMessageReadExportQueryPageListInfoRes;
import com.olivia.peanut.portal.api.entity.msgMessageRead.MsgMessageReadExportQueryPageListReq;
import com.olivia.peanut.portal.api.entity.msgMessageRead.MsgMessageReadQueryListReq;
import com.olivia.peanut.portal.api.entity.msgMessageRead.MsgMessageReadQueryListRes;
import com.olivia.peanut.portal.model.MsgMessageRead;
import com.olivia.sdk.utils.DynamicsPage;

/**
 * (MsgMessageRead)表服务接口
 *
 * @author peanut
 * @since 2024-03-23 19:17:47
 */
public interface MsgMessageReadService extends MPJBaseService<MsgMessageRead> {

  MsgMessageReadQueryListRes queryList(MsgMessageReadQueryListReq req);

  DynamicsPage<MsgMessageReadExportQueryPageListInfoRes> queryPageList(MsgMessageReadExportQueryPageListReq req);

}

