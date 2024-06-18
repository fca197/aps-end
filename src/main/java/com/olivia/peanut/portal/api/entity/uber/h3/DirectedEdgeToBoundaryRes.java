package com.olivia.peanut.portal.api.entity.uber.h3;

import com.uber.h3core.util.LatLng;
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
public class DirectedEdgeToBoundaryRes {

  List<LatLng> dataList;

}
