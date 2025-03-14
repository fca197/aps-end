package com.olivia.peanut.base.api.entity.msgMessageRead;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.util.List;

/**
 * (MsgMessageRead)查询对象入参
 *
 * @author peanut
 * @since 2024-03-23 19:17:48
 */
@Accessors(chain = true)
@Getter
@Setter
@SuppressWarnings("serial")
public class MsgMessageReadQueryByIdListReq {

  private List<Long> idList;


}

