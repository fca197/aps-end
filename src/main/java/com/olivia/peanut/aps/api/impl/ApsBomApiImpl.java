package com.olivia.peanut.aps.api.impl;

import com.github.yulichang.wrapper.MPJLambdaWrapper;
import com.olivia.peanut.aps.api.ApsBomApi;
import com.olivia.peanut.aps.api.entity.apsBom.*;
import com.olivia.peanut.aps.api.impl.listener.ApsBomImportListener;
import com.olivia.peanut.aps.model.ApsBom;
import com.olivia.peanut.aps.service.ApsBomService;
import com.olivia.sdk.utils.$;
import com.olivia.sdk.utils.DynamicsPage;
import com.olivia.sdk.utils.PoiExcelUtil;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * BOM 清单(ApsBom)表服务实现类
 *
 * @author peanut
 * @since 2024-06-06 11:27:33
 */
@RestController
public class ApsBomApiImpl implements ApsBomApi {

  private @Autowired ApsBomService apsBomService;

  /****
   * insert
   *
   */
  public @Override ApsBomInsertRes insert(ApsBomInsertReq req) {
    this.apsBomService.save($.copy(req, ApsBom.class));
    return new ApsBomInsertRes().setCount(1);
  }

  /****
   * deleteByIds
   *
   */
  public @Override ApsBomDeleteByIdListRes deleteByIdList(ApsBomDeleteByIdListReq req) {
    apsBomService.removeByIds(req.getIdList());
    return new ApsBomDeleteByIdListRes();
  }

  /****
   * queryList
   *
   */
  public @Override ApsBomQueryListRes queryList(ApsBomQueryListReq req) {
    return apsBomService.queryList(req);
  }

  /****
   * updateById
   *
   */
  public @Override ApsBomUpdateByIdRes updateById(ApsBomUpdateByIdReq req) {
    apsBomService.updateById($.copy(req, ApsBom.class));
    return new ApsBomUpdateByIdRes();

  }

  public @Override DynamicsPage<ApsBomExportQueryPageListInfoRes> queryPageList(ApsBomExportQueryPageListReq req) {
    return apsBomService.queryPageList(req);
  }

  public @Override void queryPageListExport(ApsBomExportQueryPageListReq req) {
    DynamicsPage<ApsBomExportQueryPageListInfoRes> page = queryPageList(req);
    List<ApsBomExportQueryPageListInfoRes> list = page.getDataList();
    // 类型转换，  更换枚举 等操作
    List<ApsBomExportQueryPageListInfoRes> listInfoRes = $.copyList(list, ApsBomExportQueryPageListInfoRes.class);
    PoiExcelUtil.export(ApsBomExportQueryPageListInfoRes.class, listInfoRes, "BOM 清单");
  }

  public @Override ApsBomImportRes importData(@RequestParam("file") MultipartFile file) {
    List<ApsBomImportReq> reqList = PoiExcelUtil.readData(file, new ApsBomImportListener(), ApsBomImportReq.class);
    // 类型转换，  更换枚举 等操作
    List<ApsBom> readList = $.copyList(reqList, ApsBom.class);
    boolean bool = apsBomService.saveBatch(readList);
    int c = bool ? readList.size() : 0;
    return new ApsBomImportRes().setCount(c);
  }

  public @Override ApsBomQueryByIdListRes queryByIdListRes(ApsBomQueryByIdListReq req) {
    MPJLambdaWrapper<ApsBom> q = new MPJLambdaWrapper<ApsBom>(ApsBom.class)
        .selectAll(ApsBom.class).in(ApsBom::getId, req.getIdList());
    List<ApsBom> list = this.apsBomService.list(q);
    List<ApsBomDto> dataList = $.copyList(list, ApsBomDto.class);
    this.apsBomService.setName(dataList);
    return new ApsBomQueryByIdListRes().setDataList(dataList);
  }
}
