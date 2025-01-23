package com.olivia.peanut.base.api.impl;

import com.github.yulichang.wrapper.MPJLambdaWrapper;
import com.olivia.peanut.base.api.BaseUserRoleApi;
import com.olivia.peanut.base.api.entity.baseUserRole.*;
import com.olivia.peanut.base.api.impl.listener.BaseUserRoleImportListener;
import com.olivia.peanut.base.model.BaseUserRole;
import com.olivia.peanut.base.service.BaseUserRoleService;
import com.olivia.sdk.utils.$;
import com.olivia.sdk.utils.DynamicsPage;
import com.olivia.sdk.utils.PoiExcelUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * 用户角色表(BaseUserRole)表服务实现类
 *
 * @author peanut
 * @since 2024-07-31 14:36:02
 */
@RestController
public class BaseUserRoleApiImpl implements BaseUserRoleApi {

  private @Autowired BaseUserRoleService baseUserRoleService;

  /****
   * insert
   *
   */
  public @Override BaseUserRoleInsertRes insert(BaseUserRoleInsertReq req) {
    this.baseUserRoleService.save($.copy(req, BaseUserRole.class));
    return new BaseUserRoleInsertRes().setCount(1);
  }

  /****
   * deleteByIds
   *
   */
  public @Override BaseUserRoleDeleteByIdListRes deleteByIdList(BaseUserRoleDeleteByIdListReq req) {
    baseUserRoleService.removeByIds(req.getIdList());
    return new BaseUserRoleDeleteByIdListRes();
  }

  /****
   * queryList
   *
   */
  public @Override BaseUserRoleQueryListRes queryList(BaseUserRoleQueryListReq req) {
    return baseUserRoleService.queryList(req);
  }

  /****
   * updateById
   *
   */
  public @Override BaseUserRoleUpdateByIdRes updateById(BaseUserRoleUpdateByIdReq req) {
    baseUserRoleService.updateById($.copy(req, BaseUserRole.class));
    return new BaseUserRoleUpdateByIdRes();

  }

  public @Override DynamicsPage<BaseUserRoleExportQueryPageListInfoRes> queryPageList(BaseUserRoleExportQueryPageListReq req) {
    return baseUserRoleService.queryPageList(req);
  }

  public @Override void queryPageListExport(BaseUserRoleExportQueryPageListReq req) {
    DynamicsPage<BaseUserRoleExportQueryPageListInfoRes> page = queryPageList(req);
    List<BaseUserRoleExportQueryPageListInfoRes> list = page.getDataList();
    // 类型转换，  更换枚举 等操作
    List<BaseUserRoleExportQueryPageListInfoRes> listInfoRes = $.copyList(list, BaseUserRoleExportQueryPageListInfoRes.class);
    PoiExcelUtil.export(BaseUserRoleExportQueryPageListInfoRes.class, listInfoRes, "用户角色表");
  }

  public @Override BaseUserRoleImportRes importData(@RequestParam("file") MultipartFile file) {
    List<BaseUserRoleImportReq> reqList = PoiExcelUtil.readData(file, new BaseUserRoleImportListener(), BaseUserRoleImportReq.class);
    // 类型转换，  更换枚举 等操作
    List<BaseUserRole> readList = $.copyList(reqList, BaseUserRole.class);
    boolean bool = baseUserRoleService.saveBatch(readList);
    int c = bool ? readList.size() : 0;
    return new BaseUserRoleImportRes().setCount(c);
  }

  public @Override BaseUserRoleQueryByIdListRes queryByIdListRes(BaseUserRoleQueryByIdListReq req) {
    MPJLambdaWrapper<BaseUserRole> q = new MPJLambdaWrapper<BaseUserRole>(BaseUserRole.class)
        .selectAll(BaseUserRole.class).in(BaseUserRole::getId, req.getIdList());
    List<BaseUserRole> list = this.baseUserRoleService.list(q);
    List<BaseUserRoleDto> dataList = $.copyList(list, BaseUserRoleDto.class);
    this.baseUserRoleService.setName(dataList);
    return new BaseUserRoleQueryByIdListRes().setDataList(dataList);
  }
}
