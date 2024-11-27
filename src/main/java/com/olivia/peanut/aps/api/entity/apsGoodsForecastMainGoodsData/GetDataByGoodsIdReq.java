package com.olivia.peanut.aps.api.entity.apsGoodsForecastMainGoodsData;

import com.olivia.peanut.aps.model.ApsGoodsForecastMainGoodsData;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.util.List;

/***
 *
 */
@Setter
@Getter
@Accessors(chain = true)
public class GetDataByGoodsIdReq extends ApsGoodsForecastMainGoodsData {

  List<String> dateRange;
}
