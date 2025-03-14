package com.olivia.peanut.base.api.impl;

import cn.hutool.core.bean.BeanUtil;
import com.olivia.peanut.base.api.UberH3Api;
import com.olivia.peanut.base.api.entity.h3.*;
import com.uber.h3core.LengthUnit;
import com.uber.h3core.util.LatLng;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.concurrent.TimeUnit;

import static com.olivia.peanut.util.H3Utils.UBER_H3_CORE;

/***
 *
 */
@Slf4j
@RestController
public class UberH3ApiImpl implements UberH3Api {


  public static String REDIS_KEY_DRIVER_GEO = "RS_D_G";
  public static String REDIS_KEY_DRIVER_LAT_LNG = "RS_D_G_L_";
  public static String REDIS_KEY_PASSENGER_GEO = "RS_P_G";
  public static String REDIS_KEY_PASSENGER_LAT_LNG = "RS_P_G_L";
  @Autowired
  StringRedisTemplate redisTemplate;
  int redisTimeOut = 30;


  private UberH3ToGeoBoundaryRes geoToH3AddressTmp(UberH3ToGeoBoundaryReq req) {
    long h3Code = UBER_H3_CORE.latLngToCell(req.getLat(), req.getLng(), req.getRes());
    return new UberH3ToGeoBoundaryRes().setH3Code(h3Code);

  }

  private UberH3ToGeoBoundaryRes geoToH3AddressMono(UberH3ToGeoBoundaryReq req) {

    long h3Code = UBER_H3_CORE.latLngToCell(req.getLat(), req.getLng(), req.getRes());
    UberH3ToGeoBoundaryRes res = new UberH3ToGeoBoundaryRes().setH3Code(h3Code);
    return res;
  }

  @Override
  public UberH3ToGeoBoundaryRes geoToH3Address(UberH3ToGeoBoundaryReq req) {

//         UberH3ToGeoBoundaryRes  resMono = geoToH3AddressMono(req);
//        resMono.then(geoToH3AddressMono(req)).
    return geoToH3AddressTmp(req);

  }

  @Override
  public LatLngCellToBoundaryRes latLngCellToBoundary(LatLngCellToBoundaryReq req) {

    long meshIndex = UBER_H3_CORE.latLngToCell(req.getLat(), req.getLng(), req.getRes());
    List<LatLng> latLngList = UBER_H3_CORE.cellToBoundary(meshIndex);
    LatLngCellToBoundaryRes boundaryRes = new LatLngCellToBoundaryRes().setDataList(latLngList);
    UberH3ToGeoBoundaryRes h3AddressMono = geoToH3AddressTmp(new UberH3ToGeoBoundaryReq().setLat(req.getLat()).setLng(req.getLng()).setRes(req.getRes()));
    boundaryRes.setCurrentH3code(h3AddressMono.getH3Code());
    return boundaryRes;
  }

  @Override
  public CellToLatLngRes cellToLatLng(CellToLatLngReq req) {

    LatLng latLng = UBER_H3_CORE.cellToLatLng(req.getH3Code());
    return new CellToLatLngRes().setLat(latLng.lat).setLng(latLng.lng);

  }

  @Override
  public GridDiskRes gridDisk(GridDiskReq req) {

    log.info("gridDisk");
    List<Long> gridDisk = UBER_H3_CORE.gridDisk(req.getH3Code(), req.getRes());
    GridDiskRes gridDiskRes = new GridDiskRes().setRegionList(gridDisk);
    return gridDiskRes;
  }

  @Override
  public GridDiskDistancesRes gridDistance(GridDiskDistancesReq req) {

    List<List<Long>> listList = UBER_H3_CORE.gridDiskDistances(req.getH3Code(), req.getK());
    return new GridDiskDistancesRes().setDataList(listList);

  }

  @Override
  public GridDistanceRes gridDistance(GridDistanceReq req) {

    long distance = UBER_H3_CORE.gridDistance(req.getSourceH3code(), req.getTargetH3code());
    return new GridDistanceRes().setDistance(distance);
  }

  @Override
  public GridPathCellsRes gridPathCells(GridPathCellsReq req) {

    List<Long> list = UBER_H3_CORE.gridPathCells(req.getSourceH3code(), req.getTargetH3code());
    return new GridPathCellsRes().setDataList(list);
  }

  @Override
  public CellToParentAddressRes cellToParent(CellToParentAddressReq req) {

    Long parentCode = UBER_H3_CORE.cellToParent(req.getH3Code(), req.getRes());
    return new CellToParentAddressRes().setParentCode(parentCode);

  }

  @Override
  public CellToChildrenRes cellToChildren(CellToChildrenResReq req) {

    List<Long> list = UBER_H3_CORE.cellToChildren(req.getH3Code(), req.getRes());
    return new CellToChildrenRes().setDataList(list);

  }

  @Override
  public GreatCircleDistanceRes greatCircleDistance(GreatCircleDistanceReq req) {

    double distance = UBER_H3_CORE.greatCircleDistance(BeanUtil.copyProperties(req.getSourceLatLng(), LatLng.class), BeanUtil.copyProperties(req.getEndLatLng(), LatLng.class),
        LengthUnit.valueOf(req.getLengthUnit()));
    return new GreatCircleDistanceRes().setDistance(distance);


  }

  @Override
  public DirectedEdgeToBoundaryRes directedEdgeToBoundary(DirectedEdgeToBoundaryReq req) {

    List<LatLng> latLngList = UBER_H3_CORE.directedEdgeToBoundary(req.getH3Code());
    return new DirectedEdgeToBoundaryRes().setDataList(latLngList);

  }

  @Override
  public DriverLocationUploadRes driverLocationUpload(DriverLocationUploadReq req) {

    UberH3ToGeoBoundaryRes geo = this.geoToH3AddressTmp(new UberH3ToGeoBoundaryReq().setLat(req.getLat()).setLng(req.getLng()).setRes(8));
    String driverId = req.getDriverId().toString();
    String key = REDIS_KEY_DRIVER_GEO + geo.getH3Code();
    log.info("key  :{}  did :{} ", key, driverId);
    redisTemplate.opsForValue().set(REDIS_KEY_DRIVER_LAT_LNG + req.getDriverId(), req.getLat() + "," + req.getLng(), redisTimeOut, TimeUnit.MINUTES);
    redisTemplate.opsForList().remove(key, 0, driverId);
    redisTemplate.opsForList().rightPush(key, driverId);
    redisTemplate.expire(key, redisTimeOut, TimeUnit.MINUTES);
    redisTemplate.opsForSet().add(REDIS_KEY_DRIVER_GEO, geo.getH3Code().toString());
    redisTemplate.expire(REDIS_KEY_DRIVER_GEO, redisTimeOut, TimeUnit.MINUTES);
    return new DriverLocationUploadRes();

  }

  @Override
  public PassengerLocationUploadRes passengerLocationUpload(PassengerLocationUploadReq req) {

    UberH3ToGeoBoundaryRes geo = this.geoToH3AddressTmp(new UberH3ToGeoBoundaryReq().setLat(req.getLat()).setLng(req.getLng()).setRes(8));
    String key = REDIS_KEY_PASSENGER_GEO + geo.getH3Code();
    String pId = req.getPassengerId().toString();
    log.info("key  :{}  pid :{} ", key, pId);
    redisTemplate.opsForList().remove(key, 0, pId);
    redisTemplate.opsForList().rightPush(key, pId);
    redisTemplate.opsForValue().set(REDIS_KEY_PASSENGER_LAT_LNG + req.getPassengerId(), req.getLat() + "," + req.getLng(), redisTimeOut, TimeUnit.MINUTES);
    redisTemplate.expire(key, redisTimeOut, TimeUnit.MINUTES);
    redisTemplate.opsForSet().add(REDIS_KEY_PASSENGER_GEO, geo.getH3Code().toString());
    redisTemplate.expire(REDIS_KEY_PASSENGER_GEO, redisTimeOut, TimeUnit.MINUTES);
    return new PassengerLocationUploadRes();

  }
}
