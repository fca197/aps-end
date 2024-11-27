package com.olivia.peanut.aps.api.impl;


import com.github.yulichang.wrapper.MPJLambdaWrapper;
import com.olivia.peanut.aps.api.ApsGoodsForecastMainMakeApi;
import com.olivia.peanut.aps.api.entity.apsGoodsForecastMainMake.*;
import com.olivia.peanut.aps.api.impl.listener.ApsGoodsForecastMainMakeImportListener;
import com.olivia.peanut.aps.model.ApsGoodsForecastMainMake;
import com.olivia.peanut.aps.service.ApsGoodsForecastMainMakeService;
import com.olivia.sdk.utils.$;
import com.olivia.sdk.utils.DynamicsPage;
import com.olivia.sdk.utils.PoiExcelUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * (ApsGoodsForecastMainMake)表服务实现类
 *
 * @author peanut
 * @since 2024-04-08 09:52:49
 */
@RestController
public class ApsGoodsForecastMainMakeApiImpl implements ApsGoodsForecastMainMakeApi {

  private @Autowired ApsGoodsForecastMainMakeService apsGoodsForecastMainMakeService;

  /****
   * insert
   *
   */
  public @Override ApsGoodsForecastMainMakeInsertRes insert(ApsGoodsForecastMainMakeInsertReq req) {
    this.apsGoodsForecastMainMakeService.save($.copy(req, ApsGoodsForecastMainMake.class));
    return new ApsGoodsForecastMainMakeInsertRes().setCount(1);
  }

  /****
   * deleteByIds
   *
   */
  public @Override ApsGoodsForecastMainMakeDeleteByIdListRes deleteByIdList(ApsGoodsForecastMainMakeDeleteByIdListReq req) {
    apsGoodsForecastMainMakeService.removeByIds(req.getIdList());
    return new ApsGoodsForecastMainMakeDeleteByIdListRes();
  }

  /****
   * queryList
   *
   */
  public @Override ApsGoodsForecastMainMakeQueryListRes queryList(ApsGoodsForecastMainMakeQueryListReq req) {
    return apsGoodsForecastMainMakeService.queryList(req);
  }

  /****
   * updateById
   *
   */
  public @Override ApsGoodsForecastMainMakeUpdateByIdRes updateById(ApsGoodsForecastMainMakeUpdateByIdReq req) {
    apsGoodsForecastMainMakeService.updateById($.copy(req, ApsGoodsForecastMainMake.class));
    return new ApsGoodsForecastMainMakeUpdateByIdRes();

  }

  public @Override DynamicsPage<ApsGoodsForecastMainMakeExportQueryPageListInfoRes> queryPageList(ApsGoodsForecastMainMakeExportQueryPageListReq req) {
    return apsGoodsForecastMainMakeService.queryPageList(req);
  }

  public @Override void queryPageListExport(ApsGoodsForecastMainMakeExportQueryPageListReq req) {
    DynamicsPage<ApsGoodsForecastMainMakeExportQueryPageListInfoRes> page = queryPageList(req);
    List<ApsGoodsForecastMainMakeExportQueryPageListInfoRes> list = page.getDataList();
    // 类型转换，  更换枚举 等操作
    List<ApsGoodsForecastMainMakeExportQueryPageListInfoRes> listInfoRes = $.copyList(list, ApsGoodsForecastMainMakeExportQueryPageListInfoRes.class);
    PoiExcelUtil.export(ApsGoodsForecastMainMakeExportQueryPageListInfoRes.class, listInfoRes, "");
  }

  public @Override ApsGoodsForecastMainMakeImportRes importData(@RequestParam("file") MultipartFile file) {
    List<ApsGoodsForecastMainMakeImportReq> reqList = PoiExcelUtil.readData(file, new ApsGoodsForecastMainMakeImportListener(), ApsGoodsForecastMainMakeImportReq.class);
    // 类型转换，  更换枚举 等操作
    List<ApsGoodsForecastMainMake> readList = $.copyList(reqList, ApsGoodsForecastMainMake.class);
    boolean bool = apsGoodsForecastMainMakeService.saveBatch(readList);
    int c = bool ? readList.size() : 0;
    return new ApsGoodsForecastMainMakeImportRes().setCount(c);
  }

  public @Override ApsGoodsForecastMainMakeQueryByIdListRes queryByIdListRes(ApsGoodsForecastMainMakeQueryByIdListReq req) {
    MPJLambdaWrapper<ApsGoodsForecastMainMake> q = new MPJLambdaWrapper<ApsGoodsForecastMainMake>(ApsGoodsForecastMainMake.class)
        .selectAll(ApsGoodsForecastMainMake.class).in(ApsGoodsForecastMainMake::getId, req.getIdList());
    List<ApsGoodsForecastMainMake> list = this.apsGoodsForecastMainMakeService.list(q);
    List<ApsGoodsForecastMainMakeDto> dataList = $.copyList(list, ApsGoodsForecastMainMakeDto.class);
    this.apsGoodsForecastMainMakeService.setName(dataList);
    return new ApsGoodsForecastMainMakeQueryByIdListRes().setDataList(dataList);
  }

  @Override
  public DynamicsPage<ApsGoodsForecastMainMakeQueryDataByIdRes> queryDataById(ApsGoodsForecastMainMakeQueryDataByIdReq req) {
    return this.apsGoodsForecastMainMakeService.queryDataById(req);
  }
}
