package com.olivia.peanut.base.api.impl;

import com.github.yulichang.wrapper.MPJLambdaWrapper;
import com.olivia.peanut.base.api.DictionaryApi;
import com.olivia.peanut.base.api.entity.dictionary.*;

import com.olivia.peanut.portal.api.impl.listener.DictionaryImportListener;
import com.olivia.peanut.base.model.Dictionary;
import com.olivia.peanut.base.service.DictionaryService;
import com.olivia.sdk.utils.$;
import com.olivia.sdk.utils.DynamicsPage;
import com.olivia.sdk.utils.PoiExcelUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * 字典值(Dictionary)表服务实现类
 *
 * @author makejava
 * @since 2024-03-11 10:44:04
 */
@RestController
public class DictionaryApiImpl implements DictionaryApi {

  private @Autowired DictionaryService dictionaryService;

  /****
   * insert
   *
   */
  public @Override DictionaryInsertRes insert(DictionaryInsertReq req) {
    this.dictionaryService.save($.copy(req, Dictionary.class));
    return new DictionaryInsertRes().setCount(1);
  }

  /****
   * deleteByIds
   *
   */
  public @Override DictionaryDeleteByIdListRes deleteByIdList(DictionaryDeleteByIdListReq req) {
    dictionaryService.removeByIds(req.getIdList());
    return new DictionaryDeleteByIdListRes();
  }

  /****
   * queryList
   *
   */
  public @Override DictionaryQueryListRes queryList(DictionaryQueryListReq req) {
    return dictionaryService.queryList(req);
  }

  /****
   * updateById
   *
   */
  public @Override DictionaryUpdateByIdRes updateById(DictionaryUpdateByIdReq req) {
    dictionaryService.updateById($.copy(req, Dictionary.class));
    return new DictionaryUpdateByIdRes();

  }

  public @Override DynamicsPage<DictionaryExportQueryPageListInfoRes> queryPageList(DictionaryExportQueryPageListReq req) {
    return dictionaryService.queryPageList(req);
  }

  public @Override void queryPageListExport(DictionaryExportQueryPageListReq req) {
    DynamicsPage<DictionaryExportQueryPageListInfoRes> page = queryPageList(req);
    List<DictionaryExportQueryPageListInfoRes> list = page.getDataList();
    // 类型转换，  更换枚举 等操作
    List<DictionaryExportQueryPageListInfoRes> listInfoRes = $.copyList(list, DictionaryExportQueryPageListInfoRes.class);
    PoiExcelUtil.export(DictionaryExportQueryPageListInfoRes.class, listInfoRes, "字典值");
  }

  public @Override DictionaryImportRes importData(@RequestParam("file") MultipartFile file) {
    List<DictionaryImportReq> reqList = PoiExcelUtil.readData(file, new DictionaryImportListener(), DictionaryImportReq.class);
    // 类型转换，  更换枚举 等操作
    List<Dictionary> readList = $.copyList(reqList, Dictionary.class);
    boolean bool = dictionaryService.saveBatch(readList);
    int c = bool ? readList.size() : 0;
    return new DictionaryImportRes().setCount(c);
  }

  public @Override DictionaryQueryByIdListRes queryByIdListRes(DictionaryQueryByIdListReq req) {
    MPJLambdaWrapper<Dictionary> q = new MPJLambdaWrapper<Dictionary>(Dictionary.class)
        .selectAll(Dictionary.class).in(Dictionary::getId, req.getIdList());
    List<Dictionary> list = this.dictionaryService.list(q);
    List<DictionaryQueryByIdListRes.Info> dataList = $.copyList(list, DictionaryQueryByIdListRes.Info.class);
    return new DictionaryQueryByIdListRes().setDataList(dataList);
  }
}
