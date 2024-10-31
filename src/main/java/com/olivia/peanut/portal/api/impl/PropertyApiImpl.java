package com.olivia.peanut.portal.api.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.toolkit.IdWorker;
import com.github.yulichang.wrapper.MPJLambdaWrapper;
import com.olivia.peanut.portal.api.PropertyApi;
import com.olivia.peanut.portal.api.entity.property.*;
import com.olivia.peanut.portal.api.impl.listener.PropertyImportListener;
import com.olivia.peanut.portal.model.Property;
import com.olivia.peanut.portal.model.Room;
import com.olivia.peanut.portal.service.PropertyService;
import com.olivia.peanut.portal.service.RoomService;
import com.olivia.sdk.utils.*;
import jakarta.annotation.Resource;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * 资产信息(Property)表服务实现类
 *
 * @author makejava
 * @since 2024-03-11 17:20:52
 */
@RestController
public class PropertyApiImpl implements PropertyApi {

  @Resource
  RoomService roomService;
  private @Autowired PropertyService propertyService;

  /****
   * insert
   *
   */
  public @Override PropertyInsertRes insert(PropertyInsertReq req) {
    req.checkParam();
    long id = IdWorker.getId();
    req.setId(id);
    req.setPropertyCode(IdUtils.getUniqueId(id));
    this.propertyService.save($.copy(req, Property.class));
    return new PropertyInsertRes().setCount(1).setId(id);
  }

  /****
   * deleteByIds
   *
   */
  public @Override PropertyDeleteByIdListRes deleteByIdList(PropertyDeleteByIdListReq req) {
    propertyService.removeByIds(req.getIdList());
    return new PropertyDeleteByIdListRes();
  }

  /****
   * queryList
   *
   */
  public @Override PropertyQueryListRes queryList(PropertyQueryListReq req) {
    return propertyService.queryList(req);
  }

  /****
   * updateById
   *
   */
  public @Override PropertyUpdateByIdRes updateById(PropertyUpdateByIdReq req) {
    propertyService.updateById($.copy(req, Property.class).setPropertyCode(null));
    return new PropertyUpdateByIdRes();

  }

  public @Override DynamicsPage<PropertyExportQueryPageListInfoRes> queryPageList(PropertyExportQueryPageListReq req) {
    return propertyService.queryPageList(req);
  }

  public @Override void queryPageListExport(PropertyExportQueryPageListReq req) {
    DynamicsPage<PropertyExportQueryPageListInfoRes> page = queryPageList(req);
    List<PropertyExportQueryPageListInfoRes> list = page.getDataList();
    // 类型转换，  更换枚举 等操作
    List<PropertyExportQueryPageListInfoRes> listInfoRes = $.copyList(list, PropertyExportQueryPageListInfoRes.class);
    PoiExcelUtil.export(PropertyExportQueryPageListInfoRes.class, listInfoRes, "资产信息");
  }

  public @Override PropertyImportRes importData(@RequestParam("file") MultipartFile file) {
    List<PropertyImportReq> reqList = PoiExcelUtil.readData(file, new PropertyImportListener(), PropertyImportReq.class);
    // 类型转换，  更换枚举 等操作
    List<Property> readList = $.copyList(reqList, Property.class);
    boolean bool = propertyService.saveBatch(readList);
    int c = bool ? readList.size() : 0;
    return new PropertyImportRes().setCount(c);
  }

  public @Override PropertyQueryByIdListRes queryByIdListRes(PropertyQueryByIdListReq req) {
    MPJLambdaWrapper<Property> q = new MPJLambdaWrapper<>(Property.class).selectAll(Property.class).in(Property::getId, req.getIdList());
    List<Property> list = this.propertyService.list(q);
    List<PropertyDto> dataList = $.copyList(list, PropertyDto.class);
    return new PropertyQueryByIdListRes().setDataList(dataList);
  }

  @Override
  public PropertySaveBatchRes saveBatch(PropertySaveBatchReq req) {
    Room room = roomService.getOne(
        new LambdaQueryWrapper<Room>().eq(Room::getId, req.getRoomId()).eq(Room::getFactoryId, req.getFactoryId()).eq(Room::getStoreyId, req.getStoreyId()));
    $.requireNonNullCanIgnoreException(room, "房间不存在");
    List<Property> propertyList = new ArrayList<>();
    IntStream.range(0, req.getPropertyCount()).forEach(i -> {
      Property property = new Property();
      long id = IdWorker.getId();
      property.setFactoryId(req.getFactoryId()).setStoreyId(req.getStoreyId()).setRoomId(req.getRoomId()).setId(id);
      property.setPropertyName(req.getPropertyName()).setPropertyCode(IdUtils.getUniqueId(id)).setInUse(Boolean.TRUE);
      propertyList.add(property);
    });
    this.propertyService.saveBatch(propertyList);
    return new PropertySaveBatchRes().setIdList(propertyList.stream().map(Property::getId).toList());
  }

  @Override
  public PropertyUpdateUseRes updateUse(PropertyUpdateUseReq req) {
    Property property = this.propertyService.getById(req.getId());
    $.requireNonNullCanIgnoreException(property, "资产不存在");
    boolean update = this.propertyService.update(new LambdaUpdateWrapper<Property>().set(Property::getInUse, req.getInUse()).eq(BaseEntity::getId, req.getId()));
    return new PropertyUpdateUseRes().setIsSuccess(update);
  }
}
