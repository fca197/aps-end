package com.olivia.peanut.aps.service.impl.utils;

import com.olivia.peanut.aps.model.ApsSchedulingDayConfigVersionDetail;
import com.olivia.peanut.aps.service.impl.po.ApsSchedulingDayOrderRoomReq;
import com.olivia.peanut.aps.service.impl.po.ApsSchedulingDayOrderRoomRes;
import java.util.ArrayList;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/***
 *
 */
@Setter
@Getter
@Accessors(chain = true)
public class ApsSchedulingDayUtils {

  private ApsSchedulingDayUtils() {
  }

  public static ApsSchedulingDayOrderRoomRes orderRoomStatus(ApsSchedulingDayOrderRoomReq req) {
    ArrayList<ApsSchedulingDayConfigVersionDetail> arrayList = new ArrayList<>();
    arrayList.forEach(t -> t.setSchedulingDayId(req.getSchedulingDayId()));
    return new ApsSchedulingDayOrderRoomRes().setApsSchedulingDayConfigVersionDetailList(arrayList);
  }

}
