package com.olivia.peanut.aps.api.impl;

import com.github.yulichang.wrapper.MPJLambdaWrapper;
import com.olivia.peanut.aps.api.ApsSchedulingDayConfigApi;
import com.olivia.peanut.aps.api.entity.apsSchedulingDayConfig.*;
import com.olivia.peanut.aps.api.impl.listener.ApsSchedulingDayConfigImportListener;
import com.olivia.peanut.aps.model.ApsSchedulingDayConfig;
import com.olivia.peanut.aps.service.ApsSchedulingDayConfigService;
import com.olivia.sdk.utils.$;
import com.olivia.sdk.utils.DynamicsPage;
import com.olivia.sdk.utils.PoiExcelUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * 排程版本表(ApsSchedulingDayConfig)表服务实现类
 *
 * @author peanut
 * @since 2024-07-19 19:19:48
 */
@RestController
public class ApsSchedulingDayConfigApiImpl implements ApsSchedulingDayConfigApi {

  private @Autowired ApsSchedulingDayConfigService apsSchedulingDayConfigService;

  /****
   * insert
   *
   */
  public @Override ApsSchedulingDayConfigInsertRes insert(ApsSchedulingDayConfigInsertReq req) {
    return this.apsSchedulingDayConfigService.save(req);
//    return new ApsSchedulingDayConfigInsertRes().setCount(1);
  }

  /****
   * deleteByIds
   *
   */
  public @Override ApsSchedulingDayConfigDeleteByIdListRes deleteByIdList(ApsSchedulingDayConfigDeleteByIdListReq req) {
    apsSchedulingDayConfigService.removeByIds(req.getIdList());
    return new ApsSchedulingDayConfigDeleteByIdListRes();
  }

  /****
   * queryList
   *
   */
  public @Override ApsSchedulingDayConfigQueryListRes queryList(ApsSchedulingDayConfigQueryListReq req) {
    return apsSchedulingDayConfigService.queryList(req);
  }

  /****
   * updateById
   *
   */
  public @Override ApsSchedulingDayConfigUpdateByIdRes updateById(ApsSchedulingDayConfigUpdateByIdReq req) {
    return apsSchedulingDayConfigService.updateById(req);

  }

  public @Override DynamicsPage<ApsSchedulingDayConfigExportQueryPageListInfoRes> queryPageList(ApsSchedulingDayConfigExportQueryPageListReq req) {
    return apsSchedulingDayConfigService.queryPageList(req);
  }

  public @Override void queryPageListExport(ApsSchedulingDayConfigExportQueryPageListReq req) {
    DynamicsPage<ApsSchedulingDayConfigExportQueryPageListInfoRes> page = queryPageList(req);
    List<ApsSchedulingDayConfigExportQueryPageListInfoRes> list = page.getDataList();
    // 类型转换，  更换枚举 等操作
    List<ApsSchedulingDayConfigExportQueryPageListInfoRes> listInfoRes = $.copyList(list, ApsSchedulingDayConfigExportQueryPageListInfoRes.class);
    PoiExcelUtil.export(ApsSchedulingDayConfigExportQueryPageListInfoRes.class, listInfoRes, "排程版本表");
  }

  public @Override ApsSchedulingDayConfigImportRes importData(@RequestParam("file") MultipartFile file) {
    List<ApsSchedulingDayConfigImportReq> reqList = PoiExcelUtil.readData(file, new ApsSchedulingDayConfigImportListener(), ApsSchedulingDayConfigImportReq.class);
    // 类型转换，  更换枚举 等操作
    List<ApsSchedulingDayConfig> readList = $.copyList(reqList, ApsSchedulingDayConfig.class);
    boolean bool = apsSchedulingDayConfigService.saveBatch(readList);
    int c = bool ? readList.size() : 0;
    return new ApsSchedulingDayConfigImportRes().setCount(c);
  }

  public @Override ApsSchedulingDayConfigQueryByIdListRes queryByIdListRes(ApsSchedulingDayConfigQueryByIdListReq req) {
    MPJLambdaWrapper<ApsSchedulingDayConfig> q = new MPJLambdaWrapper<ApsSchedulingDayConfig>(ApsSchedulingDayConfig.class)
        .selectAll(ApsSchedulingDayConfig.class).in(ApsSchedulingDayConfig::getId, req.getIdList());
    List<ApsSchedulingDayConfig> list = this.apsSchedulingDayConfigService.list(q);
    List<ApsSchedulingDayConfigDto> dataList = $.copyList(list, ApsSchedulingDayConfigDto.class);
    this.apsSchedulingDayConfigService.setName(dataList);
    return new ApsSchedulingDayConfigQueryByIdListRes().setDataList(dataList);
  }
}
