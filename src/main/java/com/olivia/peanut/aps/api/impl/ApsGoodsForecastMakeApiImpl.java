package com.olivia.peanut.aps.api.impl;


import com.github.yulichang.wrapper.MPJLambdaWrapper;
import com.olivia.peanut.aps.api.ApsGoodsForecastMakeApi;
import com.olivia.peanut.aps.api.entity.apsGoodsForecastMake.*;
import com.olivia.peanut.aps.api.impl.listener.ApsGoodsForecastMakeImportListener;
import com.olivia.peanut.aps.model.ApsGoodsForecastMake;
import com.olivia.peanut.aps.service.ApsGoodsForecastMakeService;
import com.olivia.sdk.utils.$;
import com.olivia.sdk.utils.DynamicsPage;
import com.olivia.sdk.utils.PoiExcelUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * (ApsGoodsForecastMake)表服务实现类
 *
 * @author peanut
 * @since 2024-04-07 15:07:48
 */
@RestController
public class ApsGoodsForecastMakeApiImpl implements ApsGoodsForecastMakeApi {

  private @Autowired ApsGoodsForecastMakeService apsGoodsForecastMakeService;

  /****
   * insert
   *
   */
  public @Override ApsGoodsForecastMakeInsertRes insert(ApsGoodsForecastMakeInsertReq req) {
    return this.apsGoodsForecastMakeService.save(req);
  }

  /****
   * deleteByIds
   *
   */
  public @Override ApsGoodsForecastMakeDeleteByIdListRes deleteByIdList(ApsGoodsForecastMakeDeleteByIdListReq req) {
    apsGoodsForecastMakeService.removeByIds(req.getIdList());
    return new ApsGoodsForecastMakeDeleteByIdListRes();
  }

  /****
   * queryList
   *
   */
  public @Override ApsGoodsForecastMakeQueryListRes queryList(ApsGoodsForecastMakeQueryListReq req) {
    return apsGoodsForecastMakeService.queryList(req);
  }

  /****
   * updateById
   *
   */
  public @Override ApsGoodsForecastMakeUpdateByIdRes updateById(ApsGoodsForecastMakeUpdateByIdReq req) {
    apsGoodsForecastMakeService.updateById($.copy(req, ApsGoodsForecastMake.class));
    return new ApsGoodsForecastMakeUpdateByIdRes();

  }

  public @Override DynamicsPage<ApsGoodsForecastMakeExportQueryPageListInfoRes> queryPageList(ApsGoodsForecastMakeExportQueryPageListReq req) {
    return apsGoodsForecastMakeService.queryPageList(req);
  }

  public @Override void queryPageListExport(ApsGoodsForecastMakeExportQueryPageListReq req) {
    DynamicsPage<ApsGoodsForecastMakeExportQueryPageListInfoRes> page = queryPageList(req);
    List<ApsGoodsForecastMakeExportQueryPageListInfoRes> list = page.getDataList();
    // 类型转换，  更换枚举 等操作
    List<ApsGoodsForecastMakeExportQueryPageListInfoRes> listInfoRes = $.copyList(list, ApsGoodsForecastMakeExportQueryPageListInfoRes.class);
    PoiExcelUtil.export(ApsGoodsForecastMakeExportQueryPageListInfoRes.class, listInfoRes, "");
  }

  public @Override ApsGoodsForecastMakeImportRes importData(@RequestParam("file") MultipartFile file) {
    List<ApsGoodsForecastMakeImportReq> reqList = PoiExcelUtil.readData(file, new ApsGoodsForecastMakeImportListener(), ApsGoodsForecastMakeImportReq.class);
    // 类型转换，  更换枚举 等操作
    List<ApsGoodsForecastMake> readList = $.copyList(reqList, ApsGoodsForecastMake.class);
    boolean bool = apsGoodsForecastMakeService.saveBatch(readList);
    int c = bool ? readList.size() : 0;
    return new ApsGoodsForecastMakeImportRes().setCount(c);
  }

  public @Override ApsGoodsForecastMakeQueryByIdListRes queryByIdListRes(ApsGoodsForecastMakeQueryByIdListReq req) {
    MPJLambdaWrapper<ApsGoodsForecastMake> q = new MPJLambdaWrapper<ApsGoodsForecastMake>(ApsGoodsForecastMake.class)
        .selectAll(ApsGoodsForecastMake.class).in(ApsGoodsForecastMake::getId, req.getIdList());
    List<ApsGoodsForecastMake> list = this.apsGoodsForecastMakeService.list(q);
    List<ApsGoodsForecastMakeDto> dataList = $.copyList(list, ApsGoodsForecastMakeDto.class);
    this.apsGoodsForecastMakeService.setName(dataList);
    return new ApsGoodsForecastMakeQueryByIdListRes().setDataList(dataList);
  }

  @Override
  public DynamicsPage<ApsGoodsForecastMakeQueryDataByIdRes> queryDataById(ApsGoodsForecastMakeQueryDataByIdReq req) {
    return this.apsGoodsForecastMakeService.queryDataById(req);
  }

  @Override
  public DynamicsPage<ApsGoodsForecastMakeQueryDataByIdRes> queryProjectDataById(ApsGoodsForecastMakeQueryDataByIdReq req) {
    return this.apsGoodsForecastMakeService.queryProjectDataById(req);

  }

  @Override
  public DynamicsPage<ApsGoodsForecastMakeQueryUseBomByIdRes> queryBomUseDataById(ApsGoodsForecastMakeQueryUseBomByIdReq req) {
    return this.apsGoodsForecastMakeService.queryBomUseDataById(req);
  }

  @Override
  public ApsGoodsForecastMakeDeployRes deploy(ApsGoodsForecastMakeDeployReq req) {
    return this.apsGoodsForecastMakeService.deploy(req);
  }
}
