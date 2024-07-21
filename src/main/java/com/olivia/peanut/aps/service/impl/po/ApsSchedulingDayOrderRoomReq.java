package com.olivia.peanut.aps.service.impl.po;

import com.olivia.peanut.aps.api.entity.apsSchedulingDayConfig.ApsSchedulingDayConfigDto;
import com.olivia.peanut.aps.model.ApsSchedulingIssueItem;
import java.util.List;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/***
 *
 */
@Setter
@Getter
@Accessors(chain = true)
public class ApsSchedulingDayOrderRoomReq {

  private Long schedulingDayId;
  private List<ApsSchedulingIssueItem> issueItemList;

  private ApsSchedulingDayConfigDto schedulingDayConfigDto;
}
