package com.olivia.peanut.base.api.entity.h3;

import com.uber.h3core.util.LatLng;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/***
 *
 */
@Setter
@Getter
@Accessors(chain = true)
public class GreatCircleDistanceReq {

  private LatLng sourceLatLng;
  private LatLng endLatLng;

  private String lengthUnit;

  enum LengthUnit {
    /**
     * Radians on the WGS84 sphere
     */
    rads,
    /**
     * Kilometers
     */
    km,
    /**
     * Meters
     */
    m
  }

}
