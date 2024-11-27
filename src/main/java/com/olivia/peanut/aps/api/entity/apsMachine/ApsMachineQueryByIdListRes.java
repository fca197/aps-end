package com.olivia.peanut.aps.api.entity.apsMachine;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.util.List;

/**
 * aps 生产机器(ApsMachine)查询对象返回
 *
 * @author makejava
 * @since 2024-10-24 16:31:16
 */
@Accessors(chain = true)
@Getter
@Setter
@SuppressWarnings("serial")
public class ApsMachineQueryByIdListRes {
  /***
   * 返回对象列表
   */
  private List<ApsMachineDto> dataList;


}

