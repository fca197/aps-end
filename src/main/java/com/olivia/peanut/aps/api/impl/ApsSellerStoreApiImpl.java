package com.olivia.peanut.aps.api.impl;

import com.github.yulichang.wrapper.MPJLambdaWrapper;
import com.olivia.peanut.aps.api.ApsSellerStoreApi;
import com.olivia.peanut.aps.api.entity.apsSellerStore.*;
import com.olivia.peanut.aps.api.impl.listener.ApsSellerStoreImportListener;
import com.olivia.peanut.aps.model.ApsSellerStore;
import com.olivia.peanut.aps.service.ApsSellerStoreService;
import com.olivia.sdk.utils.$;
import com.olivia.sdk.utils.DynamicsPage;
import com.olivia.sdk.utils.PoiExcelUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * aps销售门店(ApsSellerStore)表服务实现类
 *
 * @author makejava
 * @since 2024-11-15 14:58:58
 */
@RestController
public class ApsSellerStoreApiImpl implements ApsSellerStoreApi {

  private @Autowired ApsSellerStoreService apsSellerStoreService;

  /****
   * insert
   *
   */
  public @Override ApsSellerStoreInsertRes insert(ApsSellerStoreInsertReq req) {
    this.apsSellerStoreService.save($.copy(req, ApsSellerStore.class));
    return new ApsSellerStoreInsertRes().setCount(1);
  }

  /****
   * deleteByIds
   *
   */
  public @Override ApsSellerStoreDeleteByIdListRes deleteByIdList(ApsSellerStoreDeleteByIdListReq req) {
    apsSellerStoreService.removeByIds(req.getIdList());
    return new ApsSellerStoreDeleteByIdListRes();
  }

  /****
   * queryList
   *
   */
  public @Override ApsSellerStoreQueryListRes queryList(ApsSellerStoreQueryListReq req) {
    return apsSellerStoreService.queryList(req);
  }

  /****
   * updateById
   *
   */
  public @Override ApsSellerStoreUpdateByIdRes updateById(ApsSellerStoreUpdateByIdReq req) {
    apsSellerStoreService.updateById($.copy(req, ApsSellerStore.class));
    return new ApsSellerStoreUpdateByIdRes();

  }

  public @Override DynamicsPage<ApsSellerStoreExportQueryPageListInfoRes> queryPageList(ApsSellerStoreExportQueryPageListReq req) {
    return apsSellerStoreService.queryPageList(req);
  }

  public @Override void queryPageListExport(ApsSellerStoreExportQueryPageListReq req) {
    DynamicsPage<ApsSellerStoreExportQueryPageListInfoRes> page = queryPageList(req);
    List<ApsSellerStoreExportQueryPageListInfoRes> list = page.getDataList();
    // 类型转换，  更换枚举 等操作
    List<ApsSellerStoreExportQueryPageListInfoRes> listInfoRes = $.copyList(list, ApsSellerStoreExportQueryPageListInfoRes.class);
    PoiExcelUtil.export(ApsSellerStoreExportQueryPageListInfoRes.class, listInfoRes, "aps销售门店");
  }

  public @Override ApsSellerStoreImportRes importData(@RequestParam("file") MultipartFile file) {
    List<ApsSellerStoreImportReq> reqList = PoiExcelUtil.readData(file, new ApsSellerStoreImportListener(), ApsSellerStoreImportReq.class);
    // 类型转换，  更换枚举 等操作
    List<ApsSellerStore> readList = $.copyList(reqList, ApsSellerStore.class);
    boolean bool = apsSellerStoreService.saveBatch(readList);
    int c = bool ? readList.size() : 0;
    return new ApsSellerStoreImportRes().setCount(c);
  }

  public @Override ApsSellerStoreQueryByIdListRes queryByIdListRes(ApsSellerStoreQueryByIdListReq req) {
    MPJLambdaWrapper<ApsSellerStore> q = new MPJLambdaWrapper<ApsSellerStore>(ApsSellerStore.class)
        .selectAll(ApsSellerStore.class).in(ApsSellerStore::getId, req.getIdList());
    List<ApsSellerStore> list = this.apsSellerStoreService.list(q);
    List<ApsSellerStoreDto> dataList = $.copyList(list, ApsSellerStoreDto.class);
    this.apsSellerStoreService.setName(dataList);
    return new ApsSellerStoreQueryByIdListRes().setDataList(dataList);
  }
}
