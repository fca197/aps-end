package com.olivia.peanut.base.api.impl;

import com.github.yulichang.wrapper.MPJLambdaWrapper;
import com.olivia.peanut.base.api.BaseRoleApi;
import com.olivia.peanut.base.api.entity.baseRole.*;
import com.olivia.peanut.base.api.impl.listener.BaseRoleImportListener;
import com.olivia.peanut.base.model.BaseRole;
import com.olivia.peanut.base.service.BaseRoleService;
import com.olivia.sdk.utils.$;
import com.olivia.sdk.utils.DynamicsPage;
import com.olivia.sdk.utils.PoiExcelUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * 角色表(BaseRole)表服务实现类
 *
 * @author peanut
 * @since 2024-07-31 14:33:34
 */
@RestController
public class BaseRoleApiImpl implements BaseRoleApi {

  private @Autowired BaseRoleService baseRoleService;

  /****
   * insert
   *
   */
  public @Override BaseRoleInsertRes insert(BaseRoleInsertReq req) {
    this.baseRoleService.save($.copy(req, BaseRole.class));
    return new BaseRoleInsertRes().setCount(1);
  }

  /****
   * deleteByIds
   *
   */
  public @Override BaseRoleDeleteByIdListRes deleteByIdList(BaseRoleDeleteByIdListReq req) {
    baseRoleService.removeByIds(req.getIdList());
    return new BaseRoleDeleteByIdListRes();
  }

  /****
   * queryList
   *
   */
  public @Override BaseRoleQueryListRes queryList(BaseRoleQueryListReq req) {
    return baseRoleService.queryList(req);
  }

  /****
   * updateById
   *
   */
  public @Override BaseRoleUpdateByIdRes updateById(BaseRoleUpdateByIdReq req) {
    baseRoleService.updateById($.copy(req, BaseRole.class));
    return new BaseRoleUpdateByIdRes();

  }

  public @Override DynamicsPage<BaseRoleExportQueryPageListInfoRes> queryPageList(BaseRoleExportQueryPageListReq req) {
    return baseRoleService.queryPageList(req);
  }

  public @Override void queryPageListExport(BaseRoleExportQueryPageListReq req) {
    DynamicsPage<BaseRoleExportQueryPageListInfoRes> page = queryPageList(req);
    List<BaseRoleExportQueryPageListInfoRes> list = page.getDataList();
    // 类型转换，  更换枚举 等操作
    List<BaseRoleExportQueryPageListInfoRes> listInfoRes = $.copyList(list, BaseRoleExportQueryPageListInfoRes.class);
    PoiExcelUtil.export(BaseRoleExportQueryPageListInfoRes.class, listInfoRes, "角色表");
  }

  public @Override BaseRoleImportRes importData(@RequestParam("file") MultipartFile file) {
    List<BaseRoleImportReq> reqList = PoiExcelUtil.readData(file, new BaseRoleImportListener(), BaseRoleImportReq.class);
    // 类型转换，  更换枚举 等操作
    List<BaseRole> readList = $.copyList(reqList, BaseRole.class);
    boolean bool = baseRoleService.saveBatch(readList);
    int c = bool ? readList.size() : 0;
    return new BaseRoleImportRes().setCount(c);
  }

  public @Override BaseRoleQueryByIdListRes queryByIdListRes(BaseRoleQueryByIdListReq req) {
    MPJLambdaWrapper<BaseRole> q = new MPJLambdaWrapper<BaseRole>(BaseRole.class)
        .selectAll(BaseRole.class).in(BaseRole::getId, req.getIdList());
    List<BaseRole> list = this.baseRoleService.list(q);
    List<BaseRoleDto> dataList = $.copyList(list, BaseRoleDto.class);
    this.baseRoleService.setName(dataList);
    return new BaseRoleQueryByIdListRes().setDataList(dataList);
  }
}
