package com.olivia.peanut.aps.api.impl;


import com.github.yulichang.wrapper.MPJLambdaWrapper;
import com.olivia.peanut.aps.api.ApsMakeCapacitySaleConfigApi;
import com.olivia.peanut.aps.api.entity.apsMakeCapacitySaleConfig.*;
import com.olivia.peanut.aps.api.impl.listener.ApsMakeCapacitySaleConfigImportListener;
import com.olivia.peanut.aps.model.ApsMakeCapacitySaleConfig;
import com.olivia.peanut.aps.service.ApsMakeCapacitySaleConfigService;
import com.olivia.sdk.utils.$;
import com.olivia.sdk.utils.DynamicsPage;
import com.olivia.sdk.utils.PoiExcelUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * (ApsMakeCapacitySaleConfig)表服务实现类
 *
 * @author peanut
 * @since 2024-04-13 12:05:06
 */
@RestController
public class ApsMakeCapacitySaleConfigApiImpl implements ApsMakeCapacitySaleConfigApi {

  private @Autowired ApsMakeCapacitySaleConfigService apsMakeCapacitySaleConfigService;

  /****
   * insert
   *
   */
  public @Override ApsMakeCapacitySaleConfigInsertRes insert(ApsMakeCapacitySaleConfigInsertReq req) {
    return this.apsMakeCapacitySaleConfigService.save(req);

  }

  /****
   * deleteByIds
   *
   */
  public @Override ApsMakeCapacitySaleConfigDeleteByIdListRes deleteByIdList(ApsMakeCapacitySaleConfigDeleteByIdListReq req) {
    apsMakeCapacitySaleConfigService.removeByIds(req.getIdList());
    return new ApsMakeCapacitySaleConfigDeleteByIdListRes();
  }

  /****
   * queryList
   *
   */
  public @Override ApsMakeCapacitySaleConfigQueryListRes queryList(ApsMakeCapacitySaleConfigQueryListReq req) {
    return apsMakeCapacitySaleConfigService.queryList(req);
  }

  /****
   * updateById
   *
   */
  public @Override ApsMakeCapacitySaleConfigUpdateByIdRes updateById(ApsMakeCapacitySaleConfigUpdateByIdReq req) {
    apsMakeCapacitySaleConfigService.updateById($.copy(req, ApsMakeCapacitySaleConfig.class));
    return new ApsMakeCapacitySaleConfigUpdateByIdRes();

  }

  public @Override DynamicsPage<ApsMakeCapacitySaleConfigExportQueryPageListInfoRes> queryPageList(ApsMakeCapacitySaleConfigExportQueryPageListReq req) {
    return apsMakeCapacitySaleConfigService.queryPageList(req);
  }

  public @Override void queryPageListExport(ApsMakeCapacitySaleConfigExportQueryPageListReq req) {
    DynamicsPage<ApsMakeCapacitySaleConfigExportQueryPageListInfoRes> page = queryPageList(req);
    List<ApsMakeCapacitySaleConfigExportQueryPageListInfoRes> list = page.getDataList();
    // 类型转换，  更换枚举 等操作
    List<ApsMakeCapacitySaleConfigExportQueryPageListInfoRes> listInfoRes = $.copyList(list, ApsMakeCapacitySaleConfigExportQueryPageListInfoRes.class);
    PoiExcelUtil.export(ApsMakeCapacitySaleConfigExportQueryPageListInfoRes.class, listInfoRes, "");
  }

  public @Override ApsMakeCapacitySaleConfigImportRes importData(@RequestParam("file") MultipartFile file) {
    List<ApsMakeCapacitySaleConfigImportReq> reqList = PoiExcelUtil.readData(file, new ApsMakeCapacitySaleConfigImportListener(), ApsMakeCapacitySaleConfigImportReq.class);
    // 类型转换，  更换枚举 等操作
    List<ApsMakeCapacitySaleConfig> readList = $.copyList(reqList, ApsMakeCapacitySaleConfig.class);
    boolean bool = apsMakeCapacitySaleConfigService.saveBatch(readList);
    int c = bool ? readList.size() : 0;
    return new ApsMakeCapacitySaleConfigImportRes().setCount(c);
  }

  public @Override ApsMakeCapacitySaleConfigQueryByIdListRes queryByIdListRes(ApsMakeCapacitySaleConfigQueryByIdListReq req) {
    MPJLambdaWrapper<ApsMakeCapacitySaleConfig> q = new MPJLambdaWrapper<ApsMakeCapacitySaleConfig>(ApsMakeCapacitySaleConfig.class)
        .selectAll(ApsMakeCapacitySaleConfig.class).in(ApsMakeCapacitySaleConfig::getId, req.getIdList());
    List<ApsMakeCapacitySaleConfig> list = this.apsMakeCapacitySaleConfigService.list(q);
    List<ApsMakeCapacitySaleConfigDto> dataList = $.copyList(list, ApsMakeCapacitySaleConfigDto.class);
    this.apsMakeCapacitySaleConfigService.setName(dataList);
    return new ApsMakeCapacitySaleConfigQueryByIdListRes().setDataList(dataList);
  }
}
