package com.olivia.peanut.util;

import com.uber.h3core.H3Core;
import com.uber.h3core.util.LatLng;
import lombok.SneakyThrows;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.IntStream;

public class H3Utils {
  public static final H3Core UBER_H3_CORE = getUberH3Core();

  @SneakyThrows
  private static H3Core getUberH3Core() {
    return H3Core.newInstance();
  }

  /****
   * 获取当前H3
   * @param lat   维度
   * @param lon 经度

   * @return 16 层 （0-15） h3值
   */
  public static List<Long> getAllCell(BigDecimal lat, BigDecimal lon) {
    double latTmp = lat.doubleValue();
    double lngTmp = lon.doubleValue();
    return IntStream.range(0, 16).mapToObj(t -> UBER_H3_CORE.latLngToCell(latTmp, lngTmp, t)).toList();
  }

  /****
   * 获取当前H3
   * @param lat   维度
   * @param lon 经度
   * @param res 层级
   * @return h3值
   */
  public static Long getCurrentCell(BigDecimal lat, BigDecimal lon, int res) {
    return UBER_H3_CORE.latLngToCell(lat.doubleValue(), lon.doubleValue(), res);
  }

  /****
   * 获取周围相邻的 经纬度
   * @param lat 维度
   * @param lon 警务
   * @param res 层级
   * @return h3 list
   */
  public static List<Long> cellToBoundary(BigDecimal lat, BigDecimal lon, int res) {
    List<LatLng> latLngList = UBER_H3_CORE.cellToBoundary(getCurrentCell(lat, lon, res));
    return latLngList.stream().map(t -> UBER_H3_CORE.latLngToCell(t.lat, t.lng, res)).toList();
  }

  /*****
   *  相邻的H3获取
   * @param h3 h3值
   * @param res 层级
   * @return h3 list 列表
   */
  public static List<Long> cellToBoundary(Long h3, int res) {
    List<LatLng> latLngList = UBER_H3_CORE.cellToBoundary(h3);
    return latLngList.stream().map(t -> UBER_H3_CORE.latLngToCell(t.lat, t.lng, res)).toList();
  }

}
