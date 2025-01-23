package com.olivia.peanut.base.api.impl;

import com.github.yulichang.wrapper.MPJLambdaWrapper;
import com.olivia.peanut.base.api.BaseUserResourceApi;
import com.olivia.peanut.base.api.entity.baseUserResource.*;
import com.olivia.peanut.base.api.impl.listener.BaseUserResourceImportListener;
import com.olivia.peanut.base.model.BaseUserResource;
import com.olivia.peanut.base.service.BaseUserResourceService;
import com.olivia.sdk.utils.$;
import com.olivia.sdk.utils.DynamicsPage;
import com.olivia.sdk.utils.PoiExcelUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * 用户角色资源表(BaseUserResource)表服务实现类
 *
 * @author peanut
 * @since 2024-08-09 16:26:39
 */
@RestController
public class BaseUserResourceApiImpl implements BaseUserResourceApi {

  private @Autowired BaseUserResourceService baseUserResourceService;

  /****
   * insert
   *
   */
  public @Override BaseUserResourceInsertRes insert(BaseUserResourceInsertReq req) {
    this.baseUserResourceService.save($.copy(req, BaseUserResource.class));
    return new BaseUserResourceInsertRes().setCount(1);
  }

  /****
   * deleteByIds
   *
   */
  public @Override BaseUserResourceDeleteByIdListRes deleteByIdList(BaseUserResourceDeleteByIdListReq req) {
    baseUserResourceService.removeByIds(req.getIdList());
    return new BaseUserResourceDeleteByIdListRes();
  }

  /****
   * queryList
   *
   */
  public @Override BaseUserResourceQueryListRes queryList(BaseUserResourceQueryListReq req) {
    return baseUserResourceService.queryList(req);
  }

  /****
   * updateById
   *
   */
  public @Override BaseUserResourceUpdateByIdRes updateById(BaseUserResourceUpdateByIdReq req) {
    baseUserResourceService.updateById($.copy(req, BaseUserResource.class));
    return new BaseUserResourceUpdateByIdRes();

  }

  public @Override DynamicsPage<BaseUserResourceExportQueryPageListInfoRes> queryPageList(BaseUserResourceExportQueryPageListReq req) {
    return baseUserResourceService.queryPageList(req);
  }

  public @Override void queryPageListExport(BaseUserResourceExportQueryPageListReq req) {
    DynamicsPage<BaseUserResourceExportQueryPageListInfoRes> page = queryPageList(req);
    List<BaseUserResourceExportQueryPageListInfoRes> list = page.getDataList();
    // 类型转换，  更换枚举 等操作
    List<BaseUserResourceExportQueryPageListInfoRes> listInfoRes = $.copyList(list, BaseUserResourceExportQueryPageListInfoRes.class);
    PoiExcelUtil.export(BaseUserResourceExportQueryPageListInfoRes.class, listInfoRes, "用户角色资源表");
  }

  public @Override BaseUserResourceImportRes importData(@RequestParam("file") MultipartFile file) {
    List<BaseUserResourceImportReq> reqList = PoiExcelUtil.readData(file, new BaseUserResourceImportListener(), BaseUserResourceImportReq.class);
    // 类型转换，  更换枚举 等操作
    List<BaseUserResource> readList = $.copyList(reqList, BaseUserResource.class);
    boolean bool = baseUserResourceService.saveBatch(readList);
    int c = bool ? readList.size() : 0;
    return new BaseUserResourceImportRes().setCount(c);
  }

  public @Override BaseUserResourceQueryByIdListRes queryByIdListRes(BaseUserResourceQueryByIdListReq req) {
    MPJLambdaWrapper<BaseUserResource> q = new MPJLambdaWrapper<BaseUserResource>(BaseUserResource.class)
        .selectAll(BaseUserResource.class).in(BaseUserResource::getId, req.getIdList());
    List<BaseUserResource> list = this.baseUserResourceService.list(q);
    List<BaseUserResourceDto> dataList = $.copyList(list, BaseUserResourceDto.class);
    this.baseUserResourceService.setName(dataList);
    return new BaseUserResourceQueryByIdListRes().setDataList(dataList);
  }
}
