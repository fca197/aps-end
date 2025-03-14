package com.olivia.peanut.base.api;

import com.olivia.peanut.base.api.entity.h3.*;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * H3 UberH3Api
 * res 长度 <a href="https://blog.csdn.net/allenlu2008/article/details/103029132" res 长度介绍</a>
 */
@RequestMapping("/uber/h3")
public interface UberH3Api {

  /**
   * 根据经纬度 获取  Uber H3 Code
   *
   * @param req
   * @return
   */

  @PostMapping("/geoToH3Address")
  UberH3ToGeoBoundaryRes geoToH3Address(@RequestBody @Validated UberH3ToGeoBoundaryReq req);

  @PostMapping("/latLngCellToBoundary")
  LatLngCellToBoundaryRes latLngCellToBoundary(@RequestBody @Validated LatLngCellToBoundaryReq req);

  /**
   * 获取H3CODE 中心点
   *
   * @param req
   * @return
   */
  @PostMapping("/cellToLatLng")
  CellToLatLngRes cellToLatLng(@RequestBody @Validated CellToLatLngReq req);

  /**
   * 获取周围层级
   *
   * @param req
   * @return
   */
  @PostMapping("/gridDisk")
  GridDiskRes gridDisk(@RequestBody @Validated GridDiskReq req);

  @PostMapping("/gridDiskDistances")
  GridDiskDistancesRes gridDistance(@RequestBody @Validated GridDiskDistancesReq req);


  /**
   * 获取距离
   *
   * @param req
   * @return
   */
  @PostMapping("/gridDistance")
  GridDistanceRes gridDistance(@RequestBody @Validated GridDistanceReq req);

  /**
   * 获取两个h3code间所有h3code
   *
   * @param req
   * @return
   */
  @PostMapping("/gridPathCells")
  GridPathCellsRes gridPathCells(@RequestBody @Validated GridPathCellsReq req);

  /**
   * 获取h3code所在的父级h3code
   *
   * @param req
   * @return
   */
  @PostMapping("/cellToParent")
  CellToParentAddressRes cellToParent(@RequestBody @Validated CellToParentAddressReq req);

  /**
   * 获取h3code所在的子级h3code
   *
   * @param req
   * @return
   */
  @PostMapping("/cellToChildren")
  CellToChildrenRes cellToChildren(@RequestBody @Validated CellToChildrenResReq req);

  /**
   * 获取两点间距离
   *
   * @param req
   * @return
   */
  @PostMapping("/greatCircleDistance")
  GreatCircleDistanceRes greatCircleDistance(@RequestBody @Validated GreatCircleDistanceReq req);

  /**
   * 返回表示给定边的坐标列表。
   */
  @PostMapping("/directedEdgeToBoundary")
  DirectedEdgeToBoundaryRes directedEdgeToBoundary(@RequestBody @Validated DirectedEdgeToBoundaryReq req);


  @PostMapping("/passengerLocationUpload")
  PassengerLocationUploadRes passengerLocationUpload(@RequestBody @Validated PassengerLocationUploadReq req);

  @PostMapping("/driverLocationUpload")
  DriverLocationUploadRes driverLocationUpload(@RequestBody @Validated DriverLocationUploadReq req);


}
