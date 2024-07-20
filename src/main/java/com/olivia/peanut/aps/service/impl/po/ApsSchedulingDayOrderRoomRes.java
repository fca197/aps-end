package com.olivia.peanut.aps.service.impl.po;

import com.olivia.peanut.aps.model.ApsSchedulingDayConfigVersionDetail;
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
public class ApsSchedulingDayOrderRoomRes {

  List<ApsSchedulingDayConfigVersionDetail> apsSchedulingDayConfigVersionDetailList;

}
