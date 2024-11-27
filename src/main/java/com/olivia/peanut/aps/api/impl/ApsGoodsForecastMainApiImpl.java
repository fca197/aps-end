package com.olivia.peanut.aps.api.impl;


import com.github.yulichang.wrapper.MPJLambdaWrapper;
import com.olivia.peanut.aps.api.ApsGoodsForecastMainApi;
import com.olivia.peanut.aps.api.entity.apsGoodsForecastMain.*;
import com.olivia.peanut.aps.api.impl.listener.ApsGoodsForecastMainImportListener;
import com.olivia.peanut.aps.model.ApsGoodsForecastMain;
import com.olivia.peanut.aps.service.ApsGoodsForecastMainService;
import com.olivia.sdk.utils.$;
import com.olivia.sdk.utils.DynamicsPage;
import com.olivia.sdk.utils.PoiExcelUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * (ApsGoodsForecastMain)表服务实现类
 *
 * @author peanut
 * @since 2024-04-02 09:42:26
 */
@RestController
public class ApsGoodsForecastMainApiImpl implements ApsGoodsForecastMainApi {

  private @Autowired ApsGoodsForecastMainService apsGoodsForecastMainService;

  /****
   * insert
   *
   */
  public @Override ApsGoodsForecastMainInsertRes insert(ApsGoodsForecastMainInsertReq req) {
    this.apsGoodsForecastMainService.save($.copy(req, ApsGoodsForecastMain.class));
    return new ApsGoodsForecastMainInsertRes().setCount(1);
  }

  /****
   * deleteByIds
   *
   */
  public @Override ApsGoodsForecastMainDeleteByIdListRes deleteByIdList(ApsGoodsForecastMainDeleteByIdListReq req) {
    apsGoodsForecastMainService.removeByIds(req.getIdList());
    return new ApsGoodsForecastMainDeleteByIdListRes();
  }

  /****
   * queryList
   *
   */
  public @Override ApsGoodsForecastMainQueryListRes queryList(ApsGoodsForecastMainQueryListReq req) {
    return apsGoodsForecastMainService.queryList(req);
  }

  /****
   * updateById
   *
   */
  public @Override ApsGoodsForecastMainUpdateByIdRes updateById(ApsGoodsForecastMainUpdateByIdReq req) {
    apsGoodsForecastMainService.updateById($.copy(req, ApsGoodsForecastMain.class));
    return new ApsGoodsForecastMainUpdateByIdRes();

  }

  public @Override DynamicsPage<ApsGoodsForecastMainExportQueryPageListInfoRes> queryPageList(ApsGoodsForecastMainExportQueryPageListReq req) {
    return apsGoodsForecastMainService.queryPageList(req);
  }

  public @Override void queryPageListExport(ApsGoodsForecastMainExportQueryPageListReq req) {
    DynamicsPage<ApsGoodsForecastMainExportQueryPageListInfoRes> page = queryPageList(req);
    List<ApsGoodsForecastMainExportQueryPageListInfoRes> list = page.getDataList();
    // 类型转换，  更换枚举 等操作
    List<ApsGoodsForecastMainExportQueryPageListInfoRes> listInfoRes = $.copyList(list, ApsGoodsForecastMainExportQueryPageListInfoRes.class);
    PoiExcelUtil.export(ApsGoodsForecastMainExportQueryPageListInfoRes.class, listInfoRes, "");
  }

  public @Override ApsGoodsForecastMainImportRes importData(@RequestParam("file") MultipartFile file) {
    List<ApsGoodsForecastMainImportReq> reqList = PoiExcelUtil.readData(file, new ApsGoodsForecastMainImportListener(), ApsGoodsForecastMainImportReq.class);
    // 类型转换，  更换枚举 等操作
    List<ApsGoodsForecastMain> readList = $.copyList(reqList, ApsGoodsForecastMain.class);
    boolean bool = apsGoodsForecastMainService.saveBatch(readList);
    int c = bool ? readList.size() : 0;
    return new ApsGoodsForecastMainImportRes().setCount(c);
  }

  public @Override ApsGoodsForecastMainQueryByIdListRes queryByIdListRes(ApsGoodsForecastMainQueryByIdListReq req) {
    MPJLambdaWrapper<ApsGoodsForecastMain> q = new MPJLambdaWrapper<ApsGoodsForecastMain>(ApsGoodsForecastMain.class)
        .selectAll(ApsGoodsForecastMain.class).in(ApsGoodsForecastMain::getId, req.getIdList());
    List<ApsGoodsForecastMain> list = this.apsGoodsForecastMainService.list(q);
    List<ApsGoodsForecastMainDto> dataList = $.copyList(list, ApsGoodsForecastMainDto.class);
    return new ApsGoodsForecastMainQueryByIdListRes().setDataList(dataList);
  }
}
