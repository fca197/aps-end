package com.olivia.peanut.aps.api.entity.workshopSection;

import java.util.List;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * 工段信息(WorkshopSection)查询对象入参
 *
 * @author makejava
 * @since 2024-03-11 10:55:23
 */
@Accessors(chain = true)
@Getter
@Setter

public class WorkshopSectionQueryByIdListReq {

  private List<Long> idList;


  public void checkParam() {
  }

}

