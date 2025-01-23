package com.olivia.peanut.aps.api.impl;


import com.github.yulichang.wrapper.MPJLambdaWrapper;
import com.olivia.peanut.aps.api.ApsMakeCapacityFactoryApi;
import com.olivia.peanut.aps.api.entity.apsMakeCapacityFactory.*;
import com.olivia.peanut.aps.api.impl.listener.ApsMakeCapacityFactoryImportListener;
import com.olivia.peanut.aps.model.ApsMakeCapacityFactory;
import com.olivia.peanut.aps.service.ApsMakeCapacityFactoryService;
import com.olivia.sdk.utils.$;
import com.olivia.sdk.utils.DynamicsPage;
import com.olivia.sdk.utils.PoiExcelUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * (ApsMakeCapacityFactory)表服务实现类
 *
 * @author peanut
 * @since 2024-04-13 12:05:03
 */
@RestController
public class ApsMakeCapacityFactoryApiImpl implements ApsMakeCapacityFactoryApi {

  private @Autowired ApsMakeCapacityFactoryService apsMakeCapacityFactoryService;

  /****
   * insert
   *
   */
  public @Override ApsMakeCapacityFactoryInsertRes insert(ApsMakeCapacityFactoryInsertReq req) {
    return this.apsMakeCapacityFactoryService.save(req);

  }

  /****
   * deleteByIds
   *
   */
  public @Override ApsMakeCapacityFactoryDeleteByIdListRes deleteByIdList(ApsMakeCapacityFactoryDeleteByIdListReq req) {
    apsMakeCapacityFactoryService.removeByIds(req.getIdList());
    return new ApsMakeCapacityFactoryDeleteByIdListRes();
  }

  /****
   * queryList
   *
   */
  public @Override ApsMakeCapacityFactoryQueryListRes queryList(ApsMakeCapacityFactoryQueryListReq req) {
    return apsMakeCapacityFactoryService.queryList(req);
  }

  /****
   * updateById
   *
   */
  public @Override ApsMakeCapacityFactoryUpdateByIdRes updateById(ApsMakeCapacityFactoryUpdateByIdReq req) {
    apsMakeCapacityFactoryService.updateById($.copy(req, ApsMakeCapacityFactory.class));
    return new ApsMakeCapacityFactoryUpdateByIdRes();

  }

  public @Override DynamicsPage<ApsMakeCapacityFactoryExportQueryPageListInfoRes> queryPageList(ApsMakeCapacityFactoryExportQueryPageListReq req) {
    return apsMakeCapacityFactoryService.queryPageList(req);
  }

  public @Override void queryPageListExport(ApsMakeCapacityFactoryExportQueryPageListReq req) {
    DynamicsPage<ApsMakeCapacityFactoryExportQueryPageListInfoRes> page = queryPageList(req);
    List<ApsMakeCapacityFactoryExportQueryPageListInfoRes> list = page.getDataList();
    // 类型转换，  更换枚举 等操作
    List<ApsMakeCapacityFactoryExportQueryPageListInfoRes> listInfoRes = $.copyList(list, ApsMakeCapacityFactoryExportQueryPageListInfoRes.class);
    PoiExcelUtil.export(ApsMakeCapacityFactoryExportQueryPageListInfoRes.class, listInfoRes, "");
  }

  public @Override ApsMakeCapacityFactoryImportRes importData(@RequestParam("file") MultipartFile file) {
    List<ApsMakeCapacityFactoryImportReq> reqList = PoiExcelUtil.readData(file, new ApsMakeCapacityFactoryImportListener(), ApsMakeCapacityFactoryImportReq.class);
    // 类型转换，  更换枚举 等操作
    List<ApsMakeCapacityFactory> readList = $.copyList(reqList, ApsMakeCapacityFactory.class);
    boolean bool = apsMakeCapacityFactoryService.saveBatch(readList);
    int c = bool ? readList.size() : 0;
    return new ApsMakeCapacityFactoryImportRes().setCount(c);
  }

  public @Override ApsMakeCapacityFactoryQueryByIdListRes queryByIdListRes(ApsMakeCapacityFactoryQueryByIdListReq req) {
    MPJLambdaWrapper<ApsMakeCapacityFactory> q = new MPJLambdaWrapper<ApsMakeCapacityFactory>(ApsMakeCapacityFactory.class)
        .selectAll(ApsMakeCapacityFactory.class).in(ApsMakeCapacityFactory::getId, req.getIdList());
    List<ApsMakeCapacityFactory> list = this.apsMakeCapacityFactoryService.list(q);
    List<ApsMakeCapacityFactoryDto> dataList = $.copyList(list, ApsMakeCapacityFactoryDto.class);
    this.apsMakeCapacityFactoryService.setName(dataList);
    return new ApsMakeCapacityFactoryQueryByIdListRes().setDataList(dataList);
  }
}
