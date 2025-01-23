package com.olivia.peanut.base.api.impl;

import cn.hutool.core.collection.CollUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.github.yulichang.wrapper.MPJLambdaWrapper;
import com.olivia.peanut.base.api.BaseRoleResourceApi;
import com.olivia.peanut.base.api.entity.baseRoleResource.*;
import com.olivia.peanut.base.api.impl.listener.BaseRoleResourceImportListener;
import com.olivia.peanut.base.model.BaseRoleResource;
import com.olivia.peanut.base.service.BaseRoleResourceService;
import com.olivia.sdk.utils.$;
import com.olivia.sdk.utils.DynamicsPage;
import com.olivia.sdk.utils.PoiExcelUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * 角色资源表(BaseRoleResource)表服务实现类
 *
 * @author peanut
 * @since 2024-08-09 15:42:36
 */
@RestController
public class BaseRoleResourceApiImpl implements BaseRoleResourceApi {

  private @Autowired BaseRoleResourceService baseRoleResourceService;

  /****
   * insert
   *
   */
  @Transactional
  public @Override BaseRoleResourceInsertRes insert(BaseRoleResourceInsertReq req) {
    this.baseRoleResourceService.remove(new LambdaQueryWrapper<BaseRoleResource>().eq(BaseRoleResource::getRoleId, req.getRoleId()));
//    this.baseRoleResourceService.save($.copy(req, BaseRoleResource.class));
    if (CollUtil.isNotEmpty(req.getResourceIdList())) {
      this.baseRoleResourceService.saveBatch(req.getResourceIdList().stream()
          .map(t -> new BaseRoleResource().setResourceId(t).setRoleId(req.getRoleId())).toList());
      return new BaseRoleResourceInsertRes().setCount(req.getResourceIdList().size() + 1);
    }

    return new BaseRoleResourceInsertRes().setCount(1);
  }

  /****
   * deleteByIds
   *
   */
  public @Override BaseRoleResourceDeleteByIdListRes deleteByIdList(BaseRoleResourceDeleteByIdListReq req) {
    baseRoleResourceService.removeByIds(req.getIdList());
    return new BaseRoleResourceDeleteByIdListRes();
  }

  /****
   * queryList
   *
   */
  public @Override BaseRoleResourceQueryListRes queryList(BaseRoleResourceQueryListReq req) {
    return baseRoleResourceService.queryList(req);
  }

  /****
   * updateById
   *
   */
  public @Override BaseRoleResourceUpdateByIdRes updateById(BaseRoleResourceUpdateByIdReq req) {
    baseRoleResourceService.updateById($.copy(req, BaseRoleResource.class));
    return new BaseRoleResourceUpdateByIdRes();

  }

  public @Override DynamicsPage<BaseRoleResourceExportQueryPageListInfoRes> queryPageList(BaseRoleResourceExportQueryPageListReq req) {
    return baseRoleResourceService.queryPageList(req);
  }

  public @Override void queryPageListExport(BaseRoleResourceExportQueryPageListReq req) {
    DynamicsPage<BaseRoleResourceExportQueryPageListInfoRes> page = queryPageList(req);
    List<BaseRoleResourceExportQueryPageListInfoRes> list = page.getDataList();
    // 类型转换，  更换枚举 等操作
    List<BaseRoleResourceExportQueryPageListInfoRes> listInfoRes = $.copyList(list, BaseRoleResourceExportQueryPageListInfoRes.class);
    PoiExcelUtil.export(BaseRoleResourceExportQueryPageListInfoRes.class, listInfoRes, "角色资源表");
  }

  public @Override BaseRoleResourceImportRes importData(@RequestParam("file") MultipartFile file) {
    List<BaseRoleResourceImportReq> reqList = PoiExcelUtil.readData(file, new BaseRoleResourceImportListener(), BaseRoleResourceImportReq.class);
    // 类型转换，  更换枚举 等操作
    List<BaseRoleResource> readList = $.copyList(reqList, BaseRoleResource.class);
    boolean bool = baseRoleResourceService.saveBatch(readList);
    int c = bool ? readList.size() : 0;
    return new BaseRoleResourceImportRes().setCount(c);
  }

  public @Override BaseRoleResourceQueryByIdListRes queryByIdListRes(BaseRoleResourceQueryByIdListReq req) {
    MPJLambdaWrapper<BaseRoleResource> q = new MPJLambdaWrapper<BaseRoleResource>(BaseRoleResource.class)
        .selectAll(BaseRoleResource.class).in(BaseRoleResource::getId, req.getIdList());
    List<BaseRoleResource> list = this.baseRoleResourceService.list(q);
    List<BaseRoleResourceDto> dataList = $.copyList(list, BaseRoleResourceDto.class);
    this.baseRoleResourceService.setName(dataList);
    return new BaseRoleResourceQueryByIdListRes().setDataList(dataList);
  }
}
