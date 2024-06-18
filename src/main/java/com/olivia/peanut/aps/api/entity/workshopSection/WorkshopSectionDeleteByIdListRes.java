package com.olivia.peanut.aps.api.entity.workshopSection;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * 工段信息(WorkshopSection)根据ID删除多个反参
 *
 * @author makejava
 * @since 2024-03-11 10:55:22
 */
@Accessors(chain = true)
@Getter
@Setter

public class WorkshopSectionDeleteByIdListRes {

  /***
   * 受影响行数
   */
  private int count;

}

