package com.olivia.peanut.portal.api.impl;

import com.github.yulichang.wrapper.MPJLambdaWrapper;
import com.olivia.peanut.portal.api.TenantInfoApi;
import com.olivia.peanut.portal.api.entity.tenantInfo.*;
import com.olivia.peanut.portal.api.impl.listener.TenantInfoImportListener;
import com.olivia.peanut.portal.model.TenantInfo;
import com.olivia.peanut.portal.service.TenantInfoService;
import com.olivia.sdk.filter.LoginUserContext;
import com.olivia.sdk.utils.$;
import com.olivia.sdk.utils.DynamicsPage;
import com.olivia.sdk.utils.PoiExcelUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * 租户信息(TenantInfo)表服务实现类
 *
 * @author makejava
 * @since 2024-03-11 10:55:21
 */
@RestController
public class TenantInfoApiImpl implements TenantInfoApi {

  private @Autowired TenantInfoService tenantInfoService;

  /****
   * insert
   *
   */
  public @Override TenantInfoInsertRes insert(TenantInfoInsertReq req) {
    this.tenantInfoService.save($.copy(req, TenantInfo.class));
    return new TenantInfoInsertRes().setCount(1);
  }

  /****
   * deleteByIds
   *
   */
  public @Override TenantInfoDeleteByIdListRes deleteByIdList(TenantInfoDeleteByIdListReq req) {
    tenantInfoService.removeByIds(req.getIdList());
    return new TenantInfoDeleteByIdListRes();
  }

  /****
   * queryList
   *
   */
  public @Override TenantInfoQueryListRes queryList(TenantInfoQueryListReq req) {
    return tenantInfoService.queryList(req);
  }

  /****
   * updateById
   *
   */
  public @Override TenantInfoUpdateByIdRes updateById(TenantInfoUpdateByIdReq req) {
    tenantInfoService.updateById($.copy(req, TenantInfo.class));
    return new TenantInfoUpdateByIdRes();

  }

  public @Override DynamicsPage<TenantInfoExportQueryPageListInfoRes> queryPageList(TenantInfoExportQueryPageListReq req) {
    LoginUserContext.ignoreTenantId();
    return tenantInfoService.queryPageList(req);
  }

  public @Override void queryPageListExport(TenantInfoExportQueryPageListReq req) {
    DynamicsPage<TenantInfoExportQueryPageListInfoRes> page = queryPageList(req);
    List<TenantInfoExportQueryPageListInfoRes> list = page.getDataList();
    // 类型转换，  更换枚举 等操作
    List<TenantInfoExportQueryPageListInfoRes> listInfoRes = $.copyList(list, TenantInfoExportQueryPageListInfoRes.class);
    PoiExcelUtil.export(TenantInfoExportQueryPageListInfoRes.class, listInfoRes, "租户信息");
  }

  public @Override TenantInfoImportRes importData(@RequestParam("file") MultipartFile file) {
    List<TenantInfoImportReq> reqList = PoiExcelUtil.readData(file, new TenantInfoImportListener(), TenantInfoImportReq.class);
    // 类型转换，  更换枚举 等操作
    List<TenantInfo> readList = $.copyList(reqList, TenantInfo.class);
    boolean bool = tenantInfoService.saveBatch(readList);
    int c = bool ? readList.size() : 0;
    return new TenantInfoImportRes().setCount(c);
  }

  public @Override TenantInfoQueryByIdListRes queryByIdListRes(TenantInfoQueryByIdListReq req) {
    MPJLambdaWrapper<TenantInfo> q = new MPJLambdaWrapper<TenantInfo>(TenantInfo.class)
        .selectAll(TenantInfo.class).in(TenantInfo::getId, req.getIdList());
    List<TenantInfo> list = this.tenantInfoService.list(q);
    List<TenantInfoQueryByIdListRes.Info> dataList = $.copyList(list, TenantInfoQueryByIdListRes.Info.class);
    return new TenantInfoQueryByIdListRes().setDataList(dataList);
  }
}
