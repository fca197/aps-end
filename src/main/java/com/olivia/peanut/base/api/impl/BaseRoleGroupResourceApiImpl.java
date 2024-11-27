package com.olivia.peanut.base.api.impl;

import cn.hutool.core.collection.CollUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.github.yulichang.wrapper.MPJLambdaWrapper;
import com.olivia.peanut.base.api.BaseRoleGroupResourceApi;
import com.olivia.peanut.base.api.entity.baseRoleGroupResource.*;
import com.olivia.peanut.base.api.impl.listener.BaseRoleGroupResourceImportListener;
import com.olivia.peanut.base.model.BaseRoleGroupResource;
import com.olivia.peanut.base.service.BaseRoleGroupResourceService;
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
 * 角色组资源表(BaseRoleGroupResource)表服务实现类
 *
 * @author peanut
 * @since 2024-08-09 15:42:34
 */
@RestController
public class BaseRoleGroupResourceApiImpl implements BaseRoleGroupResourceApi {

  private @Autowired BaseRoleGroupResourceService baseRoleGroupResourceService;

  /****
   * insert
   *
   */
  @Transactional
  public @Override BaseRoleGroupResourceInsertRes insert(BaseRoleGroupResourceInsertReq req) {

    this.baseRoleGroupResourceService.remove(new LambdaQueryWrapper<BaseRoleGroupResource>().eq(BaseRoleGroupResource::getRoleGroupId, req.getRoleGroupId()));
//    this.baseRoleGroupResourceService.save($.copy(req, BaseRoleGroupResource.class));

    if (CollUtil.isNotEmpty(req.getResourceIdList())) {
      this.baseRoleGroupResourceService.saveBatch(req.getResourceIdList().stream()
          .map(t -> new BaseRoleGroupResource().setRoleGroupId(req.getRoleGroupId()).setResourceId(t)).toList());
      return new BaseRoleGroupResourceInsertRes().setCount(req.getResourceIdList().size());
    }

    return new BaseRoleGroupResourceInsertRes().setCount(0);
  }

  /****
   * deleteByIds
   *
   */
  public @Override BaseRoleGroupResourceDeleteByIdListRes deleteByIdList(BaseRoleGroupResourceDeleteByIdListReq req) {
    baseRoleGroupResourceService.removeByIds(req.getIdList());
    return new BaseRoleGroupResourceDeleteByIdListRes();
  }

  /****
   * queryList
   *
   */
  public @Override BaseRoleGroupResourceQueryListRes queryList(BaseRoleGroupResourceQueryListReq req) {
    return baseRoleGroupResourceService.queryList(req);
  }

  /****
   * updateById
   *
   */
  public @Override BaseRoleGroupResourceUpdateByIdRes updateById(BaseRoleGroupResourceUpdateByIdReq req) {
    baseRoleGroupResourceService.updateById($.copy(req, BaseRoleGroupResource.class));
    return new BaseRoleGroupResourceUpdateByIdRes();

  }

  public @Override DynamicsPage<BaseRoleGroupResourceExportQueryPageListInfoRes> queryPageList(BaseRoleGroupResourceExportQueryPageListReq req) {
    return baseRoleGroupResourceService.queryPageList(req);
  }

  public @Override void queryPageListExport(BaseRoleGroupResourceExportQueryPageListReq req) {
    DynamicsPage<BaseRoleGroupResourceExportQueryPageListInfoRes> page = queryPageList(req);
    List<BaseRoleGroupResourceExportQueryPageListInfoRes> list = page.getDataList();
    // 类型转换，  更换枚举 等操作
    List<BaseRoleGroupResourceExportQueryPageListInfoRes> listInfoRes = $.copyList(list, BaseRoleGroupResourceExportQueryPageListInfoRes.class);
    PoiExcelUtil.export(BaseRoleGroupResourceExportQueryPageListInfoRes.class, listInfoRes, "角色组资源表");
  }

  public @Override BaseRoleGroupResourceImportRes importData(@RequestParam("file") MultipartFile file) {
    List<BaseRoleGroupResourceImportReq> reqList = PoiExcelUtil.readData(file, new BaseRoleGroupResourceImportListener(), BaseRoleGroupResourceImportReq.class);
    // 类型转换，  更换枚举 等操作
    List<BaseRoleGroupResource> readList = $.copyList(reqList, BaseRoleGroupResource.class);
    boolean bool = baseRoleGroupResourceService.saveBatch(readList);
    int c = bool ? readList.size() : 0;
    return new BaseRoleGroupResourceImportRes().setCount(c);
  }

  public @Override BaseRoleGroupResourceQueryByIdListRes queryByIdListRes(BaseRoleGroupResourceQueryByIdListReq req) {
    MPJLambdaWrapper<BaseRoleGroupResource> q = new MPJLambdaWrapper<BaseRoleGroupResource>(BaseRoleGroupResource.class)
        .selectAll(BaseRoleGroupResource.class).in(BaseRoleGroupResource::getId, req.getIdList());
    List<BaseRoleGroupResource> list = this.baseRoleGroupResourceService.list(q);
    List<BaseRoleGroupResourceDto> dataList = $.copyList(list, BaseRoleGroupResourceDto.class);
    this.baseRoleGroupResourceService.setName(dataList);
    return new BaseRoleGroupResourceQueryByIdListRes().setDataList(dataList);
  }
}
