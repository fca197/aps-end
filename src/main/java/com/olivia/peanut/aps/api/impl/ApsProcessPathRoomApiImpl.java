package com.olivia.peanut.aps.api.impl;


import com.github.yulichang.wrapper.MPJLambdaWrapper;
import com.olivia.peanut.aps.api.ApsProcessPathRoomApi;
import com.olivia.peanut.aps.api.entity.apsProcessPathRoom.*;
import com.olivia.peanut.aps.api.impl.listener.ApsProcessPathRoomImportListener;
import com.olivia.peanut.aps.model.ApsProcessPathRoom;
import com.olivia.peanut.aps.service.ApsProcessPathRoomService;
import com.olivia.sdk.utils.$;
import com.olivia.sdk.utils.DynamicsPage;
import com.olivia.sdk.utils.PoiExcelUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * (ApsProcessPathRoom)表服务实现类
 *
 * @author peanut
 * @since 2024-04-01 17:49:18
 */
@RestController
public class ApsProcessPathRoomApiImpl implements ApsProcessPathRoomApi {

  private @Autowired ApsProcessPathRoomService apsProcessPathRoomService;

  /****
   * insert
   *
   */
  public @Override ApsProcessPathRoomInsertRes insert(ApsProcessPathRoomInsertReq req) {
    this.apsProcessPathRoomService.save($.copy(req, ApsProcessPathRoom.class));
    return new ApsProcessPathRoomInsertRes().setCount(1);
  }

  /****
   * deleteByIds
   *
   */
  public @Override ApsProcessPathRoomDeleteByIdListRes deleteByIdList(ApsProcessPathRoomDeleteByIdListReq req) {
    apsProcessPathRoomService.removeByIds(req.getIdList());
    return new ApsProcessPathRoomDeleteByIdListRes();
  }

  /****
   * queryList
   *
   */
  public @Override ApsProcessPathRoomQueryListRes queryList(ApsProcessPathRoomQueryListReq req) {
    return apsProcessPathRoomService.queryList(req);
  }

  /****
   * updateById
   *
   */
  public @Override ApsProcessPathRoomUpdateByIdRes updateById(ApsProcessPathRoomUpdateByIdReq req) {
    apsProcessPathRoomService.updateById($.copy(req, ApsProcessPathRoom.class));
    return new ApsProcessPathRoomUpdateByIdRes();

  }

  public @Override DynamicsPage<ApsProcessPathRoomExportQueryPageListInfoRes> queryPageList(ApsProcessPathRoomExportQueryPageListReq req) {
    return apsProcessPathRoomService.queryPageList(req);
  }

  public @Override void queryPageListExport(ApsProcessPathRoomExportQueryPageListReq req) {
    DynamicsPage<ApsProcessPathRoomExportQueryPageListInfoRes> page = queryPageList(req);
    List<ApsProcessPathRoomExportQueryPageListInfoRes> list = page.getDataList();
    // 类型转换，  更换枚举 等操作
    List<ApsProcessPathRoomExportQueryPageListInfoRes> listInfoRes = $.copyList(list, ApsProcessPathRoomExportQueryPageListInfoRes.class);
    PoiExcelUtil.export(ApsProcessPathRoomExportQueryPageListInfoRes.class, listInfoRes, "");
  }

  public @Override ApsProcessPathRoomImportRes importData(@RequestParam("file") MultipartFile file) {
    List<ApsProcessPathRoomImportReq> reqList = PoiExcelUtil.readData(file, new ApsProcessPathRoomImportListener(), ApsProcessPathRoomImportReq.class);
    // 类型转换，  更换枚举 等操作
    List<ApsProcessPathRoom> readList = $.copyList(reqList, ApsProcessPathRoom.class);
    boolean bool = apsProcessPathRoomService.saveBatch(readList);
    int c = bool ? readList.size() : 0;
    return new ApsProcessPathRoomImportRes().setCount(c);
  }

  public @Override ApsProcessPathRoomQueryByIdListRes queryByIdListRes(ApsProcessPathRoomQueryByIdListReq req) {
    MPJLambdaWrapper<ApsProcessPathRoom> q = new MPJLambdaWrapper<ApsProcessPathRoom>(ApsProcessPathRoom.class)
        .selectAll(ApsProcessPathRoom.class).in(ApsProcessPathRoom::getId, req.getIdList());
    List<ApsProcessPathRoom> list = this.apsProcessPathRoomService.list(q);
    List<ApsProcessPathRoomDto> dataList = $.copyList(list, ApsProcessPathRoomDto.class);
    return new ApsProcessPathRoomQueryByIdListRes().setDataList(dataList);
  }
}
