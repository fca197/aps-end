package com.olivia.peanut.base.api.impl;


import com.github.yulichang.wrapper.MPJLambdaWrapper;
import com.olivia.peanut.base.api.BaseSupplierApi;
import com.olivia.peanut.base.api.entity.baseSupplier.*;

import com.olivia.peanut.portal.api.impl.listener.BaseSupplierImportListener;
import com.olivia.peanut.base.model.BaseSupplier;
import com.olivia.peanut.base.service.BaseSupplierService;
import com.olivia.sdk.utils.$;
import com.olivia.sdk.utils.DynamicsPage;
import com.olivia.sdk.utils.PoiExcelUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * (BaseSupplier)表服务实现类
 *
 * @author peanut
 * @since 2024-03-28 15:35:37
 */
@RestController
public class BaseSupplierApiImpl implements BaseSupplierApi {

  private @Autowired BaseSupplierService baseSupplierService;

  /****
   * insert
   *
   */
  public @Override BaseSupplierInsertRes insert(BaseSupplierInsertReq req) {
    this.baseSupplierService.save($.copy(req, BaseSupplier.class));
    return new BaseSupplierInsertRes().setCount(1);
  }

  /****
   * deleteByIds
   *
   */
  public @Override BaseSupplierDeleteByIdListRes deleteByIdList(BaseSupplierDeleteByIdListReq req) {
    baseSupplierService.removeByIds(req.getIdList());
    return new BaseSupplierDeleteByIdListRes();
  }

  /****
   * queryList
   *
   */
  public @Override BaseSupplierQueryListRes queryList(BaseSupplierQueryListReq req) {
    return baseSupplierService.queryList(req);
  }

  /****
   * updateById
   *
   */
  public @Override BaseSupplierUpdateByIdRes updateById(BaseSupplierUpdateByIdReq req) {
    baseSupplierService.updateById($.copy(req, BaseSupplier.class));
    return new BaseSupplierUpdateByIdRes();

  }

  public @Override DynamicsPage<BaseSupplierExportQueryPageListInfoRes> queryPageList(BaseSupplierExportQueryPageListReq req) {
    return baseSupplierService.queryPageList(req);
  }

  public @Override void queryPageListExport(BaseSupplierExportQueryPageListReq req) {
    DynamicsPage<BaseSupplierExportQueryPageListInfoRes> page = queryPageList(req);
    List<BaseSupplierExportQueryPageListInfoRes> list = page.getDataList();
    // 类型转换，  更换枚举 等操作
    List<BaseSupplierExportQueryPageListInfoRes> listInfoRes = $.copyList(list, BaseSupplierExportQueryPageListInfoRes.class);
    PoiExcelUtil.export(BaseSupplierExportQueryPageListInfoRes.class, listInfoRes, "");
  }

  public @Override BaseSupplierImportRes importData(@RequestParam("file") MultipartFile file) {
    List<BaseSupplierImportReq> reqList = PoiExcelUtil.readData(file, new BaseSupplierImportListener(), BaseSupplierImportReq.class);
    // 类型转换，  更换枚举 等操作
    List<BaseSupplier> readList = $.copyList(reqList, BaseSupplier.class);
    boolean bool = baseSupplierService.saveBatch(readList);
    int c = bool ? readList.size() : 0;
    return new BaseSupplierImportRes().setCount(c);
  }

  public @Override BaseSupplierQueryByIdListRes queryByIdListRes(BaseSupplierQueryByIdListReq req) {
    MPJLambdaWrapper<BaseSupplier> q = new MPJLambdaWrapper<BaseSupplier>(BaseSupplier.class)
        .selectAll(BaseSupplier.class).in(BaseSupplier::getId, req.getIdList());
    List<BaseSupplier> list = this.baseSupplierService.list(q);
    List<BaseSupplierDto> dataList = $.copyList(list, BaseSupplierDto.class);
    return new BaseSupplierQueryByIdListRes().setDataList(dataList);
  }
}
