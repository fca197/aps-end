package com.olivia.peanut.portal.api.impl;

import com.github.yulichang.wrapper.MPJLambdaWrapper;
import com.olivia.peanut.portal.api.FactoryApi;
import com.olivia.peanut.portal.api.entity.factory.*;
import com.olivia.peanut.portal.api.impl.listener.FactoryImportListener;
import com.olivia.peanut.portal.model.Factory;
import com.olivia.peanut.portal.service.FactoryService;
import com.olivia.sdk.utils.$;
import com.olivia.sdk.utils.DynamicsPage;
import com.olivia.sdk.utils.PoiExcelUtil;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * 工段信息(Factory)表服务实现类
 *
 * @author makejava
 * @since 2024-03-11 10:44:04
 */
@RestController
public class FactoryApiImpl implements FactoryApi {

  private @Autowired FactoryService factoryService;

  /****
   * insert
   *
   */
  public @Override FactoryInsertRes insert(FactoryInsertReq req) {
    this.factoryService.save($.copy(req, Factory.class));
    return new FactoryInsertRes().setCount(1);
  }

  /****
   * deleteByIds
   *
   */
  public @Override FactoryDeleteByIdListRes deleteByIdList(FactoryDeleteByIdListReq req) {
    factoryService.removeByIds(req.getIdList());
    return new FactoryDeleteByIdListRes();
  }

  /****
   * queryList
   *
   */
  public @Override FactoryQueryListRes queryList(FactoryQueryListReq req) {
    return factoryService.queryList(req);
  }

  /****
   * updateById
   *
   */
  public @Override FactoryUpdateByIdRes updateById(FactoryUpdateByIdReq req) {
    factoryService.updateById($.copy(req, Factory.class));
    return new FactoryUpdateByIdRes();

  }

  public @Override DynamicsPage<FactoryExportQueryPageListInfoRes> queryPageList(FactoryExportQueryPageListReq req) {
    return factoryService.queryPageList(req);
  }

  public @Override void queryPageListExport(FactoryExportQueryPageListReq req) {
    DynamicsPage<FactoryExportQueryPageListInfoRes> page = queryPageList(req);
    List<FactoryExportQueryPageListInfoRes> list = page.getDataList();
    // 类型转换，  更换枚举 等操作
    List<FactoryExportQueryPageListInfoRes> listInfoRes = $.copyList(list, FactoryExportQueryPageListInfoRes.class);
    PoiExcelUtil.export(FactoryExportQueryPageListInfoRes.class, listInfoRes, "工段信息");
  }

  public @Override FactoryImportRes importData(@RequestParam("file") MultipartFile file) {
    List<FactoryImportReq> reqList = PoiExcelUtil.readData(file, new FactoryImportListener(), FactoryImportReq.class);
    // 类型转换，  更换枚举 等操作
    List<Factory> readList = $.copyList(reqList, Factory.class);
    boolean bool = factoryService.saveBatch(readList);
    int c = bool ? readList.size() : 0;
    return new FactoryImportRes().setCount(c);
  }

  public @Override FactoryQueryByIdListRes queryByIdListRes(FactoryQueryByIdListReq req) {
    MPJLambdaWrapper<Factory> q = new MPJLambdaWrapper<Factory>(Factory.class)
        .selectAll(Factory.class).in(Factory::getId, req.getIdList());
    List<Factory> list = this.factoryService.list(q);
    List<FactoryQueryByIdListRes.Info> dataList = $.copyList(list, FactoryQueryByIdListRes.Info.class);
    return new FactoryQueryByIdListRes().setDataList(dataList);
  }
}
