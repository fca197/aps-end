package com.olivia.peanut.aps.api.entity.apsSchedulingDayConfigVersion;

import com.alibaba.fastjson2.JSONObject;
import com.olivia.peanut.aps.model.ApsSchedulingDayConfigVersionDetail;
import java.util.List;
import java.util.Map;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/***
 *
 */
@Setter
@Getter
@Accessors(chain = true)
public class ApsSchedulingDayConfigVersionDetailListRes   extends JSONObject {

  private Integer sortIndex;

  Map<String, List<ApsSchedulingDayConfigVersionDetail>> versionDetailMap;
}
