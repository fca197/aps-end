package com.olivia.peanut.base.api.impl;

import com.github.yulichang.wrapper.MPJLambdaWrapper;
import com.olivia.peanut.base.api.BaseH3CodeApi;
import com.olivia.peanut.base.api.entity.baseH3Code.*;
import com.olivia.peanut.base.api.impl.listener.BaseH3CodeImportListener;
import com.olivia.peanut.base.model.BaseH3Code;
import com.olivia.peanut.base.service.BaseH3CodeService;
import com.olivia.sdk.utils.$;
import com.olivia.sdk.utils.DynamicsPage;
import com.olivia.sdk.utils.PoiExcelUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * H3对应的值(BaseH3Code)表服务实现类
 *
 * @author makejava
 * @since 2024-11-19 16:09:17
 */
@RestController
public class BaseH3CodeApiImpl implements BaseH3CodeApi {

  private @Autowired BaseH3CodeService baseH3CodeService;

  /****
   * insert
   *
   */
  public @Override BaseH3CodeInsertRes insert(BaseH3CodeInsertReq req) {
    this.baseH3CodeService.save($.copy(req, BaseH3Code.class));
    return new BaseH3CodeInsertRes().setCount(1);
  }

  /****
   * deleteByIds
   *
   */
  public @Override BaseH3CodeDeleteByIdListRes deleteByIdList(BaseH3CodeDeleteByIdListReq req) {
    baseH3CodeService.removeByIds(req.getIdList());
    return new BaseH3CodeDeleteByIdListRes();
  }

  /****
   * queryList
   *
   */
  public @Override BaseH3CodeQueryListRes queryList(BaseH3CodeQueryListReq req) {
    return baseH3CodeService.queryList(req);
  }

  /****
   * updateById
   *
   */
  public @Override BaseH3CodeUpdateByIdRes updateById(BaseH3CodeUpdateByIdReq req) {
    baseH3CodeService.updateById($.copy(req, BaseH3Code.class));
    return new BaseH3CodeUpdateByIdRes();

  }

  public @Override DynamicsPage<BaseH3CodeExportQueryPageListInfoRes> queryPageList(BaseH3CodeExportQueryPageListReq req) {
    return baseH3CodeService.queryPageList(req);
  }

  public @Override void queryPageListExport(BaseH3CodeExportQueryPageListReq req) {
    DynamicsPage<BaseH3CodeExportQueryPageListInfoRes> page = queryPageList(req);
    List<BaseH3CodeExportQueryPageListInfoRes> list = page.getDataList();
    // 类型转换，  更换枚举 等操作
    List<BaseH3CodeExportQueryPageListInfoRes> listInfoRes = $.copyList(list, BaseH3CodeExportQueryPageListInfoRes.class);
    PoiExcelUtil.export(BaseH3CodeExportQueryPageListInfoRes.class, listInfoRes, "H3对应的值");
  }

  public @Override BaseH3CodeImportRes importData(@RequestParam("file") MultipartFile file) {
    List<BaseH3CodeImportReq> reqList = PoiExcelUtil.readData(file, new BaseH3CodeImportListener(), BaseH3CodeImportReq.class);
    // 类型转换，  更换枚举 等操作
    List<BaseH3Code> readList = $.copyList(reqList, BaseH3Code.class);
    boolean bool = baseH3CodeService.saveBatch(readList);
    int c = bool ? readList.size() : 0;
    return new BaseH3CodeImportRes().setCount(c);
  }

  public @Override BaseH3CodeQueryByIdListRes queryByIdListRes(BaseH3CodeQueryByIdListReq req) {
    MPJLambdaWrapper<BaseH3Code> q = new MPJLambdaWrapper<BaseH3Code>(BaseH3Code.class)
        .selectAll(BaseH3Code.class).in(BaseH3Code::getId, req.getIdList());
    List<BaseH3Code> list = this.baseH3CodeService.list(q);
    List<BaseH3CodeDto> dataList = $.copyList(list, BaseH3CodeDto.class);
    this.baseH3CodeService.setName(dataList);
    return new BaseH3CodeQueryByIdListRes().setDataList(dataList);
  }
}
