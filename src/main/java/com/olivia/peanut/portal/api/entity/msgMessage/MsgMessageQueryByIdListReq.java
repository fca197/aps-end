package com.olivia.peanut.portal.api.entity.msgMessage;

import java.util.List;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * (MsgMessage)查询对象入参
 *
 * @author peanut
 * @since 2024-03-23 19:05:40
 */
@Accessors(chain = true)
@Getter
@Setter
@SuppressWarnings("serial")
public class MsgMessageQueryByIdListReq {

  private List<Long> idList;


}

