package com.olivia.peanut.aps.api.entity.apsMachine;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.util.List;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * aps 生产机器(ApsMachine)根据ID删除多个反参
 *
 * @author makejava
 * @since 2024-10-24 16:31:14
 */
@Accessors(chain = true)
@Getter
@Setter
@SuppressWarnings("serial")
public class ApsMachineDeleteByIdListRes {
  /***
   * 受影响行数
   */
  private int count;

}

