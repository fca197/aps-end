package com.olivia.peanut.portal.api.impl;

import static java.lang.Boolean.FALSE;
import static java.lang.Boolean.TRUE;

import cn.hutool.core.collection.CollUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.toolkit.IdWorker;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.github.yulichang.wrapper.MPJLambdaWrapper;
import com.olivia.peanut.portal.api.CheckReportListApi;
import com.olivia.peanut.portal.api.entity.checkReportList.*;
import com.olivia.peanut.portal.api.entity.checkReportList.CheckReportListFactoryDataRes.Info;
import com.olivia.peanut.portal.api.impl.listener.CheckReportListImportListener;
import com.olivia.peanut.portal.model.*;
import com.olivia.peanut.portal.service.*;
import com.olivia.sdk.config.PeanutProperties;
import com.olivia.sdk.utils.*;
import jakarta.annotation.Resource;
import java.util.*;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * 报表检查记录信息(CheckReportList)表服务实现类
 *
 * @author makejava
 * @since 2024-03-14 13:31:36
 */
@RestController
public class CheckReportListApiImpl implements CheckReportListApi {

  @Resource
  StoreyService storeyService;
  @Resource
  RoomService roomService;
  @Resource
  PeanutProperties peanutProperties;
  @Resource
  CacheService cacheService;
  private @Autowired CheckReportListService checkReportListService;
  private @Resource PropertyService propertyService;

  private @Resource CheckReportService checkReportService;


  /****
   * insert
   *
   */
  @Transactional
  public @Override CheckReportListInsertRes insert(CheckReportListInsertReq req) {

    Long propertyId = IdUtils.getLongId(req.getPropertyCode());

    CheckReport report = this.checkReportService.getById(req.getCheckId());
    $.requireNonNullCanIgnoreException(report, "盘点不存在");
    Property property = propertyService.getById(propertyId);
    $.requireNonNullCanIgnoreException(property, "资产不存在");
    if (FALSE.equals(property.getInUse())) {
      String toUseKey = "lock_" + property.getId();
      if (!this.cacheService.hasKey(toUseKey)) {
        this.cacheService.setKv(toUseKey, 1, peanutProperties.getScanProperty2UseInTimeSecond());
        $.assertTrueCanIgnoreException(property.getInUse(), "资产已禁用, " + peanutProperties.getScanProperty2UseInTimeSecond() + "秒内重新扫码会再次启用该资产");
      } else {
        this.propertyService.update(new LambdaUpdateWrapper<Property>().eq(BaseEntity::getId, propertyId).set(Property::getInUse, TRUE));
        this.cacheService.deleteKey(toUseKey);
      }
    }

    $.assertTrueCanIgnoreException(Objects.equals(report.getFactoryId(), property.getFactoryId()), "该资产不在此报表中,请重新选则报表");
    CheckReportList entity = new CheckReportList();
    entity.setId(IdWorker.getId());
    entity.setReportId(report.getId());
    entity.setFactoryId(property.getFactoryId()).setPropertyId(report.getId()).setPropertyId(propertyId).setRoomId(property.getRoomId()).setStoreyId(property.getStoreyId());
    this.checkReportListService.remove(
        Wrappers.lambdaQuery(CheckReportList.class).eq(CheckReportList::getFactoryId, report.getFactoryId()).eq(CheckReportList::getPropertyId, propertyId)
            .eq(CheckReportList::getReportId, report.getId()));
    this.checkReportListService.save(entity);
    return new CheckReportListInsertRes().setCount(1).setId(entity.getId()).setReportName(report.getReportName());
  }

  /****
   * deleteByIds
   *
   */
  public @Override CheckReportListDeleteByIdListRes deleteByIdList(CheckReportListDeleteByIdListReq req) {
    checkReportListService.removeByIds(req.getIdList());
    return new CheckReportListDeleteByIdListRes();
  }

  /****
   * queryList
   *
   */
  public @Override CheckReportListQueryListRes queryList(CheckReportListQueryListReq req) {
    return checkReportListService.queryList(req);
  }

  /****
   * updateById
   *
   */
  public @Override CheckReportListUpdateByIdRes updateById(CheckReportListUpdateByIdReq req) {
    checkReportListService.updateById($.copy(req, CheckReportList.class));
    return new CheckReportListUpdateByIdRes();

  }

  public @Override DynamicsPage<CheckReportListExportQueryPageListInfoRes> queryPageList(CheckReportListExportQueryPageListReq req) {
    return checkReportListService.queryPageList(req);
  }

  public @Override void queryPageListExport(CheckReportListExportQueryPageListReq req) {
    DynamicsPage<CheckReportListExportQueryPageListInfoRes> page = queryPageList(req);
    List<CheckReportListExportQueryPageListInfoRes> list = page.getDataList();
    // 类型转换，  更换枚举 等操作
    List<CheckReportListExportQueryPageListInfoRes> listInfoRes = $.copyList(list, CheckReportListExportQueryPageListInfoRes.class);
    PoiExcelUtil.export(CheckReportListExportQueryPageListInfoRes.class, listInfoRes, "报表检查记录信息");
  }

  public @Override CheckReportListImportRes importData(@RequestParam("file") MultipartFile file) {
    List<CheckReportListImportReq> reqList = PoiExcelUtil.readData(file, new CheckReportListImportListener(), CheckReportListImportReq.class);
    // 类型转换，  更换枚举 等操作
    List<CheckReportList> readList = $.copyList(reqList, CheckReportList.class);
    boolean bool = checkReportListService.saveBatch(readList);
    int c = bool ? readList.size() : 0;
    return new CheckReportListImportRes().setCount(c);
  }

  public @Override CheckReportListQueryByIdListRes queryByIdListRes(CheckReportListQueryByIdListReq req) {
    MPJLambdaWrapper<CheckReportList> q = new MPJLambdaWrapper<>(CheckReportList.class).selectAll(CheckReportList.class).in(CheckReportList::getId, req.getIdList());
    List<CheckReportList> list = this.checkReportListService.list(q);
    List<CheckReportListDto> dataList = $.copyList(list, CheckReportListDto.class);
    return new CheckReportListQueryByIdListRes().setDataList(dataList);
  }

  @Override
  public CheckReportListFactoryDataRes factoryData(CheckReportListFactoryDataReq req) {
    CheckReport report = this.checkReportService.getById(req.getCheckId());
    $.requireNonNullCanIgnoreException(report, "盘点不存在");
    List<Room> roomList = this.roomService.list(new LambdaQueryWrapper<Room>().eq(Room::getFactoryId, report.getFactoryId()));
    if (CollUtil.isEmpty(roomList)) {
      return new CheckReportListFactoryDataRes().setDataList(List.of());
    }
    List<Runnable> runnableList = new ArrayList<>();
    List<CheckReportListFactoryDataRes.Info> dataList = Collections.synchronizedList(new ArrayList<>());
    Map<Long, Storey> storeyMap = this.storeyService.listByFactoryIdList(report.getFactoryId());
    roomList.forEach(r -> runnableList.add(() -> {
      CheckReportListFactoryDataRes.Info info = new Info();
      List<Long> propertyIdList = this.propertyService.list(
              new LambdaQueryWrapper<Property>().select(BaseEntity::getId).eq(Property::getRoomId, r.getId()).eq(Property::getInUse, TRUE)).stream().map(BaseEntity::getId)
          .toList();
      Storey storey = storeyMap.get(r.getStoreyId());
      info.setAllCount((long) propertyIdList.size()).setRoomName(r.getRoomName()).setRoomId(r.getId()).setStoreyName(storey.getStoreyName());
      info.setRoomSort(r.getRoomSort()).setStoreySort(storey.getStoreySort()).setCheckId(req.getCheckId()).setCheckCount(0L);
      dataList.add(info);
      if (CollUtil.isNotEmpty(propertyIdList)) {
        long checkCount = this.checkReportListService.count(
            new LambdaQueryWrapper<CheckReportList>().eq(CheckReportList::getReportId, report.getId()).in(CheckReportList::getPropertyId, propertyIdList)
                .eq(CheckReportList::getRoomId, r.getId()));
        info.setCheckCount(checkCount);
      }
    }));

    RunUtils.run("查询报表" ,runnableList);
    dataList.sort(Comparator.comparingInt(Info::getStoreySort).thenComparingInt(Info::getRoomSort));
    return new CheckReportListFactoryDataRes().setDataList(dataList);
  }

  @Override
  public CheckReportListRoomDataRes roomData(CheckReportListRoomDataReq req) {

    List<CheckReportListRoomDataRes.Info> infoList = new ArrayList<>();
    List<Property> propertyList = this.propertyService.list(new LambdaQueryWrapper<Property>().eq(Property::getRoomId, req.getRoomId()).eq(Property::getInUse, TRUE));
    if (CollUtil.isEmpty(propertyList)) {
      return new CheckReportListRoomDataRes().setDataList(List.of());
    }
    List<CheckReportList> reportListList = this.checkReportListService.list(new LambdaQueryWrapper<CheckReportList>().eq(CheckReportList::getReportId, req.getCheckId())
        .in(CheckReportList::getPropertyId, propertyList.stream().map(BaseEntity::getId).toList()).eq(CheckReportList::getRoomId, req.getRoomId()));
    Map<Long, CheckReportList> reportListMap = reportListList.stream().collect(Collectors.toMap(CheckReportList::getPropertyId, p -> p, (p1, p2) -> p1));
    propertyList.forEach(p -> {
      CheckReportList checkReportList = reportListMap.get(p.getId());
      CheckReportListRoomDataRes.Info info = new CheckReportListRoomDataRes.Info().setCheckId(req.getCheckId());
      info.setPropertyName(p.getPropertyName()).setPropertyId(p.getId()).setIsCheck(FALSE).setRoomId(p.getRoomId());
      if (Objects.nonNull(checkReportList)) {
        info.setIsCheck(TRUE).setCheckDate(checkReportList.getCreateTime());
      }
      infoList.add(info);
    });
    return new CheckReportListRoomDataRes().setDataList(infoList);
  }
}
