package com.olivia.peanut.aps.api.impl;


import com.github.yulichang.wrapper.MPJLambdaWrapper;
import com.olivia.peanut.aps.api.ApsOrderGoodsSaleConfigApi;
import com.olivia.peanut.aps.api.entity.apsOrderGoodsSaleConfig.*;
import com.olivia.peanut.aps.api.impl.listener.ApsOrderGoodsSaleConfigImportListener;
import com.olivia.peanut.aps.model.ApsOrderGoodsSaleConfig;
import com.olivia.peanut.aps.service.ApsOrderGoodsSaleConfigService;
import com.olivia.sdk.utils.$;
import com.olivia.sdk.utils.DynamicsPage;
import com.olivia.sdk.utils.PoiExcelUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * (ApsOrderGoodsSaleConfig)表服务实现类
 *
 * @author peanut
 * @since 2024-04-09 13:19:37
 */
@RestController
public class ApsOrderGoodsSaleConfigApiImpl implements ApsOrderGoodsSaleConfigApi {

  private @Autowired ApsOrderGoodsSaleConfigService apsOrderGoodsSaleConfigService;

  /****
   * insert
   *
   */
  public @Override ApsOrderGoodsSaleConfigInsertRes insert(ApsOrderGoodsSaleConfigInsertReq req) {
    this.apsOrderGoodsSaleConfigService.save($.copy(req, ApsOrderGoodsSaleConfig.class));
    return new ApsOrderGoodsSaleConfigInsertRes().setCount(1);
  }

  /****
   * deleteByIds
   *
   */
  public @Override ApsOrderGoodsSaleConfigDeleteByIdListRes deleteByIdList(ApsOrderGoodsSaleConfigDeleteByIdListReq req) {
    apsOrderGoodsSaleConfigService.removeByIds(req.getIdList());
    return new ApsOrderGoodsSaleConfigDeleteByIdListRes();
  }

  /****
   * queryList
   *
   */
  public @Override ApsOrderGoodsSaleConfigQueryListRes queryList(ApsOrderGoodsSaleConfigQueryListReq req) {
    return apsOrderGoodsSaleConfigService.queryList(req);
  }

  /****
   * updateById
   *
   */
  public @Override ApsOrderGoodsSaleConfigUpdateByIdRes updateById(ApsOrderGoodsSaleConfigUpdateByIdReq req) {
    apsOrderGoodsSaleConfigService.updateById($.copy(req, ApsOrderGoodsSaleConfig.class));
    return new ApsOrderGoodsSaleConfigUpdateByIdRes();

  }

  public @Override DynamicsPage<ApsOrderGoodsSaleConfigExportQueryPageListInfoRes> queryPageList(ApsOrderGoodsSaleConfigExportQueryPageListReq req) {
    return apsOrderGoodsSaleConfigService.queryPageList(req);
  }

  public @Override void queryPageListExport(ApsOrderGoodsSaleConfigExportQueryPageListReq req) {
    DynamicsPage<ApsOrderGoodsSaleConfigExportQueryPageListInfoRes> page = queryPageList(req);
    List<ApsOrderGoodsSaleConfigExportQueryPageListInfoRes> list = page.getDataList();
    // 类型转换，  更换枚举 等操作
    List<ApsOrderGoodsSaleConfigExportQueryPageListInfoRes> listInfoRes = $.copyList(list, ApsOrderGoodsSaleConfigExportQueryPageListInfoRes.class);
    PoiExcelUtil.export(ApsOrderGoodsSaleConfigExportQueryPageListInfoRes.class, listInfoRes, "");
  }

  public @Override ApsOrderGoodsSaleConfigImportRes importData(@RequestParam("file") MultipartFile file) {
    List<ApsOrderGoodsSaleConfigImportReq> reqList = PoiExcelUtil.readData(file, new ApsOrderGoodsSaleConfigImportListener(), ApsOrderGoodsSaleConfigImportReq.class);
    // 类型转换，  更换枚举 等操作
    List<ApsOrderGoodsSaleConfig> readList = $.copyList(reqList, ApsOrderGoodsSaleConfig.class);
    boolean bool = apsOrderGoodsSaleConfigService.saveBatch(readList);
    int c = bool ? readList.size() : 0;
    return new ApsOrderGoodsSaleConfigImportRes().setCount(c);
  }

  public @Override ApsOrderGoodsSaleConfigQueryByIdListRes queryByIdListRes(ApsOrderGoodsSaleConfigQueryByIdListReq req) {
    MPJLambdaWrapper<ApsOrderGoodsSaleConfig> q = new MPJLambdaWrapper<ApsOrderGoodsSaleConfig>(ApsOrderGoodsSaleConfig.class)
        .selectAll(ApsOrderGoodsSaleConfig.class).in(ApsOrderGoodsSaleConfig::getId, req.getIdList());
    List<ApsOrderGoodsSaleConfig> list = this.apsOrderGoodsSaleConfigService.list(q);
    List<ApsOrderGoodsSaleConfigDto> dataList = $.copyList(list, ApsOrderGoodsSaleConfigDto.class);
    this.apsOrderGoodsSaleConfigService.setName(dataList);
    return new ApsOrderGoodsSaleConfigQueryByIdListRes().setDataList(dataList);
  }
}
