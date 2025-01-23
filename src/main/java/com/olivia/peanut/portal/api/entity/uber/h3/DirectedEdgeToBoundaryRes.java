package com.olivia.peanut.portal.api.entity.uber.h3;

import com.uber.h3core.util.LatLng;
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
public class DirectedEdgeToBoundaryRes {

  List<LatLng> dataList;

}
