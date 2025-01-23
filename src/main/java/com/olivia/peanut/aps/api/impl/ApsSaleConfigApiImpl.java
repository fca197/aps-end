package com.olivia.peanut.aps.api.impl;


import com.github.yulichang.wrapper.MPJLambdaWrapper;
import com.olivia.peanut.aps.api.ApsSaleConfigApi;
import com.olivia.peanut.aps.api.entity.apsSaleConfig.*;
import com.olivia.peanut.aps.api.impl.listener.ApsSaleConfigImportListener;
import com.olivia.peanut.aps.model.ApsSaleConfig;
import com.olivia.peanut.aps.service.ApsSaleConfigService;
import com.olivia.sdk.utils.$;
import com.olivia.sdk.utils.DynamicsPage;
import com.olivia.sdk.utils.PoiExcelUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * (ApsSaleConfig)表服务实现类
 *
 * @author peanut
 * @since 2024-03-29 23:10:39
 */
@RestController
public class ApsSaleConfigApiImpl implements ApsSaleConfigApi {

  private @Autowired ApsSaleConfigService apsSaleConfigService;

  /****
   * insert
   *
   */
  public @Override ApsSaleConfigInsertRes insert(ApsSaleConfigInsertReq req) {
    this.apsSaleConfigService.save($.copy(req, ApsSaleConfig.class));
    return new ApsSaleConfigInsertRes().setCount(1);
  }

  /****
   * deleteByIds
   *
   */
  public @Override ApsSaleConfigDeleteByIdListRes deleteByIdList(ApsSaleConfigDeleteByIdListReq req) {
    apsSaleConfigService.removeByIds(req.getIdList());
    return new ApsSaleConfigDeleteByIdListRes();
  }

  /****
   * queryList
   *
   */
  public @Override ApsSaleConfigQueryListRes queryList(ApsSaleConfigQueryListReq req) {
    return apsSaleConfigService.queryList(req);
  }

  /****
   * updateById
   *
   */
  public @Override ApsSaleConfigUpdateByIdRes updateById(ApsSaleConfigUpdateByIdReq req) {
    apsSaleConfigService.updateById($.copy(req, ApsSaleConfig.class));
    return new ApsSaleConfigUpdateByIdRes();

  }

  public @Override DynamicsPage<ApsSaleConfigExportQueryPageListInfoRes> queryPageList(ApsSaleConfigExportQueryPageListReq req) {
    return apsSaleConfigService.queryPageList(req);
  }

  public @Override void queryPageListExport(ApsSaleConfigExportQueryPageListReq req) {
    DynamicsPage<ApsSaleConfigExportQueryPageListInfoRes> page = queryPageList(req);
    List<ApsSaleConfigExportQueryPageListInfoRes> list = page.getDataList();
    // 类型转换，  更换枚举 等操作
    List<ApsSaleConfigExportQueryPageListInfoRes> listInfoRes = $.copyList(list, ApsSaleConfigExportQueryPageListInfoRes.class);
    PoiExcelUtil.export(ApsSaleConfigExportQueryPageListInfoRes.class, listInfoRes, "");
  }

  public @Override ApsSaleConfigImportRes importData(@RequestParam("file") MultipartFile file) {
    List<ApsSaleConfigImportReq> reqList = PoiExcelUtil.readData(file, new ApsSaleConfigImportListener(), ApsSaleConfigImportReq.class);
    // 类型转换，  更换枚举 等操作
    List<ApsSaleConfig> readList = $.copyList(reqList, ApsSaleConfig.class);
    boolean bool = apsSaleConfigService.saveBatch(readList);
    int c = bool ? readList.size() : 0;
    return new ApsSaleConfigImportRes().setCount(c);
  }

  public @Override ApsSaleConfigQueryByIdListRes queryByIdListRes(ApsSaleConfigQueryByIdListReq req) {
    MPJLambdaWrapper<ApsSaleConfig> q = new MPJLambdaWrapper<ApsSaleConfig>(ApsSaleConfig.class)
        .selectAll(ApsSaleConfig.class).in(ApsSaleConfig::getId, req.getIdList());
    List<ApsSaleConfig> list = this.apsSaleConfigService.list(q);
    List<ApsSaleConfigDto> dataList = $.copyList(list, ApsSaleConfigDto.class);
    return new ApsSaleConfigQueryByIdListRes().setDataList(dataList);
  }
}
