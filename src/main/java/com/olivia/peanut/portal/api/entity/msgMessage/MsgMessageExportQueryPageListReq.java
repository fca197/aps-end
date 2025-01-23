package com.olivia.peanut.portal.api.entity.msgMessage;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * (MsgMessage)查询对象入参
 *
 * @author peanut
 * @since 2024-03-23 19:05:39
 */
@Accessors(chain = true)
@Getter
@Setter
@SuppressWarnings("serial")
public class MsgMessageExportQueryPageListReq {

  private int pageNum;
  private int pageSize;
  private Boolean queryPage = true;
  private MsgMessageDto data;


}

