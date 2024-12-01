package com.olivia.peanut.base.api.impl;

import com.github.yulichang.wrapper.MPJLambdaWrapper;
import com.olivia.peanut.base.api.BaseOplogApi;
import com.olivia.peanut.base.api.entity.baseOplog.*;
import com.olivia.peanut.base.api.impl.listener.BaseOplogImportListener;
import com.olivia.peanut.base.model.BaseOplog;
import com.olivia.peanut.base.service.BaseOplogService;
import com.olivia.sdk.utils.$;
import com.olivia.sdk.utils.DynamicsPage;
import com.olivia.sdk.utils.PoiExcelUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * 操作日志(BaseOplog)表服务实现类
 *
 * @author makejava
 * @since 2024-11-30 16:01:00
 */
@RestController
public class BaseOplogApiImpl implements BaseOplogApi {

  private @Autowired BaseOplogService baseOplogService;

  /****
   * insert
   *
   */
  public @Override BaseOplogInsertRes insert(BaseOplogInsertReq req) {
    this.baseOplogService.save($.copy(req, BaseOplog.class));
    return new BaseOplogInsertRes().setCount(1);
  }

  /****
   * deleteByIds
   *
   */
  public @Override BaseOplogDeleteByIdListRes deleteByIdList(BaseOplogDeleteByIdListReq req) {
    baseOplogService.removeByIds(req.getIdList());
    return new BaseOplogDeleteByIdListRes();
  }

  /****
   * queryList
   *
   */
  public @Override BaseOplogQueryListRes queryList(BaseOplogQueryListReq req) {
    return baseOplogService.queryList(req);
  }

  /****
   * updateById
   *
   */
  public @Override BaseOplogUpdateByIdRes updateById(BaseOplogUpdateByIdReq req) {
    baseOplogService.updateById($.copy(req, BaseOplog.class));
    return new BaseOplogUpdateByIdRes();

  }

  public @Override DynamicsPage<BaseOplogExportQueryPageListInfoRes> queryPageList(BaseOplogExportQueryPageListReq req) {
    return baseOplogService.queryPageList(req);
  }

  public @Override void queryPageListExport(BaseOplogExportQueryPageListReq req) {
    DynamicsPage<BaseOplogExportQueryPageListInfoRes> page = queryPageList(req);
    List<BaseOplogExportQueryPageListInfoRes> list = page.getDataList();
    // 类型转换，  更换枚举 等操作
    List<BaseOplogExportQueryPageListInfoRes> listInfoRes = $.copyList(list, BaseOplogExportQueryPageListInfoRes.class);
    PoiExcelUtil.export(BaseOplogExportQueryPageListInfoRes.class, listInfoRes, "操作日志");
  }

  public @Override BaseOplogImportRes importData(@RequestParam("file") MultipartFile file) {
    List<BaseOplogImportReq> reqList = PoiExcelUtil.readData(file, new BaseOplogImportListener(), BaseOplogImportReq.class);
    // 类型转换，  更换枚举 等操作
    List<BaseOplog> readList = $.copyList(reqList, BaseOplog.class);
    boolean bool = baseOplogService.saveBatch(readList);
    int c = bool ? readList.size() : 0;
    return new BaseOplogImportRes().setCount(c);
  }

  public @Override BaseOplogQueryByIdListRes queryByIdListRes(BaseOplogQueryByIdListReq req) {
    MPJLambdaWrapper<BaseOplog> q = new MPJLambdaWrapper<BaseOplog>(BaseOplog.class)
        .selectAll(BaseOplog.class).in(BaseOplog::getId, req.getIdList());
    List<BaseOplog> list = this.baseOplogService.list(q);
    List<BaseOplogDto> dataList = $.copyList(list, BaseOplogDto.class);
    this.baseOplogService.setName(dataList);
    return new BaseOplogQueryByIdListRes().setDataList(dataList);
  }
}
