package com.olivia.peanut.aps.api.impl;


import com.github.yulichang.wrapper.MPJLambdaWrapper;
import com.olivia.peanut.aps.api.ApsProjectConfigApi;
import com.olivia.peanut.aps.api.entity.apsProjectConfig.*;
import com.olivia.peanut.aps.api.impl.listener.ApsProjectConfigImportListener;
import com.olivia.peanut.aps.model.ApsProjectConfig;
import com.olivia.peanut.aps.service.ApsProjectConfigService;
import com.olivia.sdk.utils.$;
import com.olivia.sdk.utils.DynamicsPage;
import com.olivia.sdk.utils.PoiExcelUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * (ApsProjectConfig)表服务实现类
 *
 * @author peanut
 * @since 2024-03-30 16:21:19
 */
@RestController
public class ApsProjectConfigApiImpl implements ApsProjectConfigApi {

  private @Autowired ApsProjectConfigService apsProjectConfigService;

  /****
   * insert
   *
   */
  public @Override ApsProjectConfigInsertRes insert(ApsProjectConfigInsertReq req) {
    this.apsProjectConfigService.save($.copy(req, ApsProjectConfig.class));
    return new ApsProjectConfigInsertRes().setCount(1);
  }

  /****
   * deleteByIds
   *
   */
  public @Override ApsProjectConfigDeleteByIdListRes deleteByIdList(ApsProjectConfigDeleteByIdListReq req) {
    apsProjectConfigService.removeByIds(req.getIdList());
    return new ApsProjectConfigDeleteByIdListRes();
  }

  /****
   * queryList
   *
   */
  public @Override ApsProjectConfigQueryListRes queryList(ApsProjectConfigQueryListReq req) {
    return apsProjectConfigService.queryList(req);
  }

  /****
   * updateById
   *
   */
  public @Override ApsProjectConfigUpdateByIdRes updateById(ApsProjectConfigUpdateByIdReq req) {
    apsProjectConfigService.updateById($.copy(req, ApsProjectConfig.class));
    return new ApsProjectConfigUpdateByIdRes();

  }

  public @Override DynamicsPage<ApsProjectConfigExportQueryPageListInfoRes> queryPageList(ApsProjectConfigExportQueryPageListReq req) {
    return apsProjectConfigService.queryPageList(req);
  }

  public @Override void queryPageListExport(ApsProjectConfigExportQueryPageListReq req) {
    DynamicsPage<ApsProjectConfigExportQueryPageListInfoRes> page = queryPageList(req);
    List<ApsProjectConfigExportQueryPageListInfoRes> list = page.getDataList();
    // 类型转换，  更换枚举 等操作
    List<ApsProjectConfigExportQueryPageListInfoRes> listInfoRes = $.copyList(list, ApsProjectConfigExportQueryPageListInfoRes.class);
    PoiExcelUtil.export(ApsProjectConfigExportQueryPageListInfoRes.class, listInfoRes, "");
  }

  public @Override ApsProjectConfigImportRes importData(@RequestParam("file") MultipartFile file) {
    List<ApsProjectConfigImportReq> reqList = PoiExcelUtil.readData(file, new ApsProjectConfigImportListener(), ApsProjectConfigImportReq.class);
    // 类型转换，  更换枚举 等操作
    List<ApsProjectConfig> readList = $.copyList(reqList, ApsProjectConfig.class);
    boolean bool = apsProjectConfigService.saveBatch(readList);
    int c = bool ? readList.size() : 0;
    return new ApsProjectConfigImportRes().setCount(c);
  }

  public @Override ApsProjectConfigQueryByIdListRes queryByIdListRes(ApsProjectConfigQueryByIdListReq req) {
    MPJLambdaWrapper<ApsProjectConfig> q = new MPJLambdaWrapper<ApsProjectConfig>(ApsProjectConfig.class)
        .selectAll(ApsProjectConfig.class).in(ApsProjectConfig::getId, req.getIdList());
    List<ApsProjectConfig> list = this.apsProjectConfigService.list(q);
    List<ApsProjectConfigDto> dataList = $.copyList(list, ApsProjectConfigDto.class);
    return new ApsProjectConfigQueryByIdListRes().setDataList(dataList);
  }
}
