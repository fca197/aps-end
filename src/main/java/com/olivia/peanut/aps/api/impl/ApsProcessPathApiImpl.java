package com.olivia.peanut.aps.api.impl;


import com.github.yulichang.wrapper.MPJLambdaWrapper;
import com.olivia.peanut.aps.api.ApsProcessPathApi;
import com.olivia.peanut.aps.api.entity.apsProcessPath.*;
import com.olivia.peanut.aps.api.impl.listener.ApsProcessPathImportListener;
import com.olivia.peanut.aps.model.ApsProcessPath;
import com.olivia.peanut.aps.service.ApsProcessPathService;
import com.olivia.sdk.utils.$;
import com.olivia.sdk.utils.DynamicsPage;
import com.olivia.sdk.utils.PoiExcelUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * (ApsProcessPath)表服务实现类
 *
 * @author peanut
 * @since 2024-04-01 17:49:17
 */
@RestController
public class ApsProcessPathApiImpl implements ApsProcessPathApi {

  private @Autowired ApsProcessPathService apsProcessPathService;

  /****
   * insert
   *
   */
  public @Override ApsProcessPathInsertRes insert(ApsProcessPathInsertReq req) {
    return this.apsProcessPathService.save(req);

  }

  /****
   * deleteByIds
   *
   */
  public @Override ApsProcessPathDeleteByIdListRes deleteByIdList(ApsProcessPathDeleteByIdListReq req) {
    apsProcessPathService.removeByIds(req.getIdList());
    return new ApsProcessPathDeleteByIdListRes();
  }

  /****
   * queryList
   *
   */
  public @Override ApsProcessPathQueryListRes queryList(ApsProcessPathQueryListReq req) {
    return apsProcessPathService.queryList(req);
  }

  /****
   * updateById
   *
   */
  public @Override ApsProcessPathUpdateByIdRes updateById(ApsProcessPathUpdateByIdReq req) {
    apsProcessPathService.updateById(req);
    return new ApsProcessPathUpdateByIdRes();

  }

  public @Override DynamicsPage<ApsProcessPathExportQueryPageListInfoRes> queryPageList(ApsProcessPathExportQueryPageListReq req) {
    return apsProcessPathService.queryPageList(req);
  }

  public @Override void queryPageListExport(ApsProcessPathExportQueryPageListReq req) {
    DynamicsPage<ApsProcessPathExportQueryPageListInfoRes> page = queryPageList(req);
    List<ApsProcessPathExportQueryPageListInfoRes> list = page.getDataList();
    // 类型转换，  更换枚举 等操作
    List<ApsProcessPathExportQueryPageListInfoRes> listInfoRes = $.copyList(list, ApsProcessPathExportQueryPageListInfoRes.class);
    PoiExcelUtil.export(ApsProcessPathExportQueryPageListInfoRes.class, listInfoRes, "");
  }

  public @Override ApsProcessPathImportRes importData(@RequestParam("file") MultipartFile file) {
    List<ApsProcessPathImportReq> reqList = PoiExcelUtil.readData(file, new ApsProcessPathImportListener(), ApsProcessPathImportReq.class);
    // 类型转换，  更换枚举 等操作
    List<ApsProcessPath> readList = $.copyList(reqList, ApsProcessPath.class);
    boolean bool = apsProcessPathService.saveBatch(readList);
    int c = bool ? readList.size() : 0;
    return new ApsProcessPathImportRes().setCount(c);
  }

  public @Override ApsProcessPathQueryByIdListRes queryByIdListRes(ApsProcessPathQueryByIdListReq req) {
    MPJLambdaWrapper<ApsProcessPath> q = new MPJLambdaWrapper<ApsProcessPath>(ApsProcessPath.class)
        .selectAll(ApsProcessPath.class).in(ApsProcessPath::getId, req.getIdList());
    List<ApsProcessPath> list = this.apsProcessPathService.list(q);
    List<ApsProcessPathDto> dataList = $.copyList(list, ApsProcessPathDto.class);
    this.apsProcessPathService.setName(dataList);
    return new ApsProcessPathQueryByIdListRes().setDataList(dataList);
  }
}
