package com.olivia.peanut.aps.api.impl;


import com.github.yulichang.wrapper.MPJLambdaWrapper;
import com.olivia.peanut.aps.api.ApsRoomApi;
import com.olivia.peanut.aps.api.entity.apsRoom.*;
import com.olivia.peanut.aps.api.impl.listener.ApsRoomImportListener;
import com.olivia.peanut.aps.model.ApsRoom;
import com.olivia.peanut.aps.service.ApsRoomService;
import com.olivia.sdk.utils.$;
import com.olivia.sdk.utils.DynamicsPage;
import com.olivia.sdk.utils.PoiExcelUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * (ApsRoom)表服务实现类
 *
 * @author peanut
 * @since 2024-04-01 15:27:29
 */
@RestController
public class ApsRoomApiImpl implements ApsRoomApi {

  private @Autowired ApsRoomService apsRoomService;

  /****
   * insert
   *
   */
  public @Override ApsRoomInsertRes insert(ApsRoomInsertReq req) {
    return this.apsRoomService.save(req);
  }

  /****
   * deleteByIds
   *
   */
  public @Override ApsRoomDeleteByIdListRes deleteByIdList(ApsRoomDeleteByIdListReq req) {
    apsRoomService.removeByIds(req.getIdList());
    return new ApsRoomDeleteByIdListRes();
  }

  /****
   * queryList
   *
   */
  public @Override ApsRoomQueryListRes queryList(ApsRoomQueryListReq req) {
    return apsRoomService.queryList(req);
  }

  /****
   * updateById
   *
   */
  public @Override ApsRoomUpdateByIdRes updateById(ApsRoomUpdateByIdReq req) {
    apsRoomService.updateById(req);
    return new ApsRoomUpdateByIdRes();

  }

  public @Override DynamicsPage<ApsRoomExportQueryPageListInfoRes> queryPageList(ApsRoomExportQueryPageListReq req) {
    return apsRoomService.queryPageList(req);
  }

  public @Override void queryPageListExport(ApsRoomExportQueryPageListReq req) {
    DynamicsPage<ApsRoomExportQueryPageListInfoRes> page = queryPageList(req);
    List<ApsRoomExportQueryPageListInfoRes> list = page.getDataList();
    // 类型转换，  更换枚举 等操作
    List<ApsRoomExportQueryPageListInfoRes> listInfoRes = $.copyList(list, ApsRoomExportQueryPageListInfoRes.class);
    PoiExcelUtil.export(ApsRoomExportQueryPageListInfoRes.class, listInfoRes, "");
  }

  public @Override ApsRoomImportRes importData(@RequestParam("file") MultipartFile file) {
    List<ApsRoomImportReq> reqList = PoiExcelUtil.readData(file, new ApsRoomImportListener(), ApsRoomImportReq.class);
    // 类型转换，  更换枚举 等操作
    List<ApsRoom> readList = $.copyList(reqList, ApsRoom.class);
    boolean bool = apsRoomService.saveBatch(readList);
    int c = bool ? readList.size() : 0;
    return new ApsRoomImportRes().setCount(c);
  }

  public @Override ApsRoomQueryByIdListRes queryByIdListRes(ApsRoomQueryByIdListReq req) {
    MPJLambdaWrapper<ApsRoom> q = new MPJLambdaWrapper<ApsRoom>(ApsRoom.class)
        .selectAll(ApsRoom.class).in(ApsRoom::getId, req.getIdList());
    List<ApsRoom> list = this.apsRoomService.list(q);
    List<ApsRoomDto> dataList = $.copyList(list, ApsRoomDto.class);
    this.apsRoomService.setName(dataList);
    return new ApsRoomQueryByIdListRes().setDataList(dataList);
  }
}
