package com.olivia.peanut.portal.api.entity.msgMessageRead;

import java.util.List;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * (MsgMessageRead)查询对象返回
 *
 * @author peanut
 * @since 2024-03-23 19:17:47
 */
@Accessors(chain = true)
@Getter
@Setter
@SuppressWarnings("serial")
public class MsgMessageReadQueryListRes {

  /***
   * 返回对象列表
   */
  private List<MsgMessageReadDto> dataList;


}

