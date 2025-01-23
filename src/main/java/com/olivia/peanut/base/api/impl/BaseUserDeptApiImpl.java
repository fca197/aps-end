package com.olivia.peanut.base.api.impl;

import com.github.yulichang.wrapper.MPJLambdaWrapper;
import com.olivia.peanut.base.api.BaseUserDeptApi;
import com.olivia.peanut.base.api.entity.baseUserDept.*;
import com.olivia.peanut.base.api.impl.listener.BaseUserDeptImportListener;
import com.olivia.peanut.base.model.BaseUserDept;
import com.olivia.peanut.base.service.BaseUserDeptService;
import com.olivia.sdk.utils.$;
import com.olivia.sdk.utils.DynamicsPage;
import com.olivia.sdk.utils.PoiExcelUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * 用户部门表(BaseUserDept)表服务实现类
 *
 * @author peanut
 * @since 2024-07-31 14:36:01
 */
@RestController
public class BaseUserDeptApiImpl implements BaseUserDeptApi {

  private @Autowired BaseUserDeptService baseUserDeptService;

  /****
   * insert
   *
   */
  public @Override BaseUserDeptInsertRes insert(BaseUserDeptInsertReq req) {
    this.baseUserDeptService.save($.copy(req, BaseUserDept.class));
    return new BaseUserDeptInsertRes().setCount(1);
  }

  /****
   * deleteByIds
   *
   */
  public @Override BaseUserDeptDeleteByIdListRes deleteByIdList(BaseUserDeptDeleteByIdListReq req) {
    baseUserDeptService.removeByIds(req.getIdList());
    return new BaseUserDeptDeleteByIdListRes();
  }

  /****
   * queryList
   *
   */
  public @Override BaseUserDeptQueryListRes queryList(BaseUserDeptQueryListReq req) {
    return baseUserDeptService.queryList(req);
  }

  /****
   * updateById
   *
   */
  public @Override BaseUserDeptUpdateByIdRes updateById(BaseUserDeptUpdateByIdReq req) {
    baseUserDeptService.updateById($.copy(req, BaseUserDept.class));
    return new BaseUserDeptUpdateByIdRes();

  }

  public @Override DynamicsPage<BaseUserDeptExportQueryPageListInfoRes> queryPageList(BaseUserDeptExportQueryPageListReq req) {
    return baseUserDeptService.queryPageList(req);
  }

  public @Override void queryPageListExport(BaseUserDeptExportQueryPageListReq req) {
    DynamicsPage<BaseUserDeptExportQueryPageListInfoRes> page = queryPageList(req);
    List<BaseUserDeptExportQueryPageListInfoRes> list = page.getDataList();
    // 类型转换，  更换枚举 等操作
    List<BaseUserDeptExportQueryPageListInfoRes> listInfoRes = $.copyList(list, BaseUserDeptExportQueryPageListInfoRes.class);
    PoiExcelUtil.export(BaseUserDeptExportQueryPageListInfoRes.class, listInfoRes, "用户部门表");
  }

  public @Override BaseUserDeptImportRes importData(@RequestParam("file") MultipartFile file) {
    List<BaseUserDeptImportReq> reqList = PoiExcelUtil.readData(file, new BaseUserDeptImportListener(), BaseUserDeptImportReq.class);
    // 类型转换，  更换枚举 等操作
    List<BaseUserDept> readList = $.copyList(reqList, BaseUserDept.class);
    boolean bool = baseUserDeptService.saveBatch(readList);
    int c = bool ? readList.size() : 0;
    return new BaseUserDeptImportRes().setCount(c);
  }

  public @Override BaseUserDeptQueryByIdListRes queryByIdListRes(BaseUserDeptQueryByIdListReq req) {
    MPJLambdaWrapper<BaseUserDept> q = new MPJLambdaWrapper<BaseUserDept>(BaseUserDept.class)
        .selectAll(BaseUserDept.class).in(BaseUserDept::getId, req.getIdList());
    List<BaseUserDept> list = this.baseUserDeptService.list(q);
    List<BaseUserDeptDto> dataList = $.copyList(list, BaseUserDeptDto.class);
    this.baseUserDeptService.setName(dataList);
    return new BaseUserDeptQueryByIdListRes().setDataList(dataList);
  }
}
