package com.olivia.peanut.aps.api.impl;


import com.github.yulichang.wrapper.MPJLambdaWrapper;
import com.olivia.peanut.aps.api.ApsRoomConfigApi;
import com.olivia.peanut.aps.api.entity.apsRoomConfig.*;
import com.olivia.peanut.aps.api.impl.listener.ApsRoomConfigImportListener;
import com.olivia.peanut.aps.model.ApsRoomConfig;
import com.olivia.peanut.aps.service.ApsRoomConfigService;
import com.olivia.sdk.utils.$;
import com.olivia.sdk.utils.DynamicsPage;
import com.olivia.sdk.utils.PoiExcelUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * (ApsRoomConfig)表服务实现类
 *
 * @author peanut
 * @since 2024-04-01 15:27:30
 */
@RestController
public class ApsRoomConfigApiImpl implements ApsRoomConfigApi {

  private @Autowired ApsRoomConfigService apsRoomConfigService;

  /****
   * insert
   *
   */
  public @Override ApsRoomConfigInsertRes insert(ApsRoomConfigInsertReq req) {
    this.apsRoomConfigService.save($.copy(req, ApsRoomConfig.class));
    return new ApsRoomConfigInsertRes().setCount(1);
  }

  /****
   * deleteByIds
   *
   */
  public @Override ApsRoomConfigDeleteByIdListRes deleteByIdList(ApsRoomConfigDeleteByIdListReq req) {
    apsRoomConfigService.removeByIds(req.getIdList());
    return new ApsRoomConfigDeleteByIdListRes();
  }

  /****
   * queryList
   *
   */
  public @Override ApsRoomConfigQueryListRes queryList(ApsRoomConfigQueryListReq req) {
    return apsRoomConfigService.queryList(req);
  }

  /****
   * updateById
   *
   */
  public @Override ApsRoomConfigUpdateByIdRes updateById(ApsRoomConfigUpdateByIdReq req) {
    apsRoomConfigService.updateById($.copy(req, ApsRoomConfig.class));
    return new ApsRoomConfigUpdateByIdRes();

  }

  public @Override DynamicsPage<ApsRoomConfigExportQueryPageListInfoRes> queryPageList(ApsRoomConfigExportQueryPageListReq req) {
    return apsRoomConfigService.queryPageList(req);
  }

  public @Override void queryPageListExport(ApsRoomConfigExportQueryPageListReq req) {
    DynamicsPage<ApsRoomConfigExportQueryPageListInfoRes> page = queryPageList(req);
    List<ApsRoomConfigExportQueryPageListInfoRes> list = page.getDataList();
    // 类型转换，  更换枚举 等操作
    List<ApsRoomConfigExportQueryPageListInfoRes> listInfoRes = $.copyList(list, ApsRoomConfigExportQueryPageListInfoRes.class);
    PoiExcelUtil.export(ApsRoomConfigExportQueryPageListInfoRes.class, listInfoRes, "");
  }

  public @Override ApsRoomConfigImportRes importData(@RequestParam("file") MultipartFile file) {
    List<ApsRoomConfigImportReq> reqList = PoiExcelUtil.readData(file, new ApsRoomConfigImportListener(), ApsRoomConfigImportReq.class);
    // 类型转换，  更换枚举 等操作
    List<ApsRoomConfig> readList = $.copyList(reqList, ApsRoomConfig.class);
    boolean bool = apsRoomConfigService.saveBatch(readList);
    int c = bool ? readList.size() : 0;
    return new ApsRoomConfigImportRes().setCount(c);
  }

  public @Override ApsRoomConfigQueryByIdListRes queryByIdListRes(ApsRoomConfigQueryByIdListReq req) {
    MPJLambdaWrapper<ApsRoomConfig> q = new MPJLambdaWrapper<ApsRoomConfig>(ApsRoomConfig.class)
        .selectAll(ApsRoomConfig.class).in(ApsRoomConfig::getId, req.getIdList());
    List<ApsRoomConfig> list = this.apsRoomConfigService.list(q);
    List<ApsRoomConfigDto> dataList = $.copyList(list, ApsRoomConfigDto.class);
    return new ApsRoomConfigQueryByIdListRes().setDataList(dataList);
  }
}
