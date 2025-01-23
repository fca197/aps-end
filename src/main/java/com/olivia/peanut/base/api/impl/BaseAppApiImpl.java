package com.olivia.peanut.base.api.impl;

import com.github.yulichang.wrapper.MPJLambdaWrapper;
import com.olivia.peanut.base.api.BaseAppApi;
import com.olivia.peanut.base.api.entity.baseApp.*;
import com.olivia.peanut.base.api.impl.listener.BaseAppImportListener;
import com.olivia.peanut.base.model.BaseApp;
import com.olivia.peanut.base.service.BaseAppService;
import com.olivia.sdk.utils.$;
import com.olivia.sdk.utils.DynamicsPage;
import com.olivia.sdk.utils.PoiExcelUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * 应用表(BaseApp)表服务实现类
 *
 * @author peanut
 * @since 2024-08-05 11:18:57
 */
@RestController
public class BaseAppApiImpl implements BaseAppApi {

  private @Autowired BaseAppService baseAppService;

  /****
   * insert
   *
   */
  public @Override BaseAppInsertRes insert(BaseAppInsertReq req) {
    this.baseAppService.save($.copy(req, BaseApp.class));
    return new BaseAppInsertRes().setCount(1);
  }

  /****
   * deleteByIds
   *
   */
  public @Override BaseAppDeleteByIdListRes deleteByIdList(BaseAppDeleteByIdListReq req) {
    baseAppService.removeByIds(req.getIdList());
    return new BaseAppDeleteByIdListRes();
  }

  /****
   * queryList
   *
   */
  public @Override BaseAppQueryListRes queryList(BaseAppQueryListReq req) {
    return baseAppService.queryList(req);
  }

  /****
   * updateById
   *
   */
  public @Override BaseAppUpdateByIdRes updateById(BaseAppUpdateByIdReq req) {
    baseAppService.updateById($.copy(req, BaseApp.class));
    return new BaseAppUpdateByIdRes();

  }

  public @Override DynamicsPage<BaseAppExportQueryPageListInfoRes> queryPageList(BaseAppExportQueryPageListReq req) {
    return baseAppService.queryPageList(req);
  }

  public @Override void queryPageListExport(BaseAppExportQueryPageListReq req) {
    DynamicsPage<BaseAppExportQueryPageListInfoRes> page = queryPageList(req);
    List<BaseAppExportQueryPageListInfoRes> list = page.getDataList();
    // 类型转换，  更换枚举 等操作
    List<BaseAppExportQueryPageListInfoRes> listInfoRes = $.copyList(list, BaseAppExportQueryPageListInfoRes.class);
    PoiExcelUtil.export(BaseAppExportQueryPageListInfoRes.class, listInfoRes, "应用表");
  }

  public @Override BaseAppImportRes importData(@RequestParam("file") MultipartFile file) {
    List<BaseAppImportReq> reqList = PoiExcelUtil.readData(file, new BaseAppImportListener(), BaseAppImportReq.class);
    // 类型转换，  更换枚举 等操作
    List<BaseApp> readList = $.copyList(reqList, BaseApp.class);
    boolean bool = baseAppService.saveBatch(readList);
    int c = bool ? readList.size() : 0;
    return new BaseAppImportRes().setCount(c);
  }

  public @Override BaseAppQueryByIdListRes queryByIdListRes(BaseAppQueryByIdListReq req) {
    MPJLambdaWrapper<BaseApp> q = new MPJLambdaWrapper<BaseApp>(BaseApp.class)
        .selectAll(BaseApp.class).in(BaseApp::getId, req.getIdList());
    List<BaseApp> list = this.baseAppService.list(q);
    List<BaseAppDto> dataList = $.copyList(list, BaseAppDto.class);
    this.baseAppService.setName(dataList);
    return new BaseAppQueryByIdListRes().setDataList(dataList);
  }
}
