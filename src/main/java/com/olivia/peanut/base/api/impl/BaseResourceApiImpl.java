package com.olivia.peanut.base.api.impl;

import com.github.yulichang.wrapper.MPJLambdaWrapper;
import com.olivia.peanut.base.api.BaseResourceApi;
import com.olivia.peanut.base.api.entity.baseResource.*;
import com.olivia.peanut.base.api.impl.listener.BaseResourceImportListener;
import com.olivia.peanut.base.model.BaseResource;
import com.olivia.peanut.base.service.BaseResourceService;
import com.olivia.sdk.utils.$;
import com.olivia.sdk.utils.DynamicsPage;
import com.olivia.sdk.utils.IdUtils;
import com.olivia.sdk.utils.PoiExcelUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Objects;

/**
 * 资源(BaseResource)表服务实现类
 *
 * @author peanut
 * @since 2024-08-06 17:29:00
 */
@RestController
public class BaseResourceApiImpl implements BaseResourceApi {

  private @Autowired BaseResourceService baseResourceService;

  /****
   * insert
   *
   */
  public @Override BaseResourceInsertRes insert(BaseResourceInsertReq req) {
    BaseResource baseResource = $.copy(req, BaseResource.class);
    Long id = IdUtils.getId();
    buildPath(req.getParentId(), baseResource, id);

    this.baseResourceService.save(baseResource);
    return new BaseResourceInsertRes().setCount(1);
  }

  private void buildPath(Long parentId, BaseResource baseResource, Long id) {
    if (Objects.nonNull(parentId) && !Objects.equals(parentId, 0L)) {
      BaseResource parentResource = this.baseResourceService.getById(parentId);
      baseResource.setPath(parentResource.getPath() + "/" + id);
    } else {
      baseResource.setParentId(0L);
      baseResource.setPath("/0/" + id);
    }
    baseResource.setId(id);
  }

  /****
   * deleteByIds
   *
   */
  public @Override BaseResourceDeleteByIdListRes deleteByIdList(BaseResourceDeleteByIdListReq req) {
    baseResourceService.removeByIds(req.getIdList());
    return new BaseResourceDeleteByIdListRes();
  }

  /****
   * queryList
   *
   */
  public @Override BaseResourceQueryListRes queryList(BaseResourceQueryListReq req) {
    return baseResourceService.queryList(req);
  }

  /****
   * updateById
   *
   */
  public @Override BaseResourceUpdateByIdRes updateById(BaseResourceUpdateByIdReq req) {
    BaseResource baseResource = $.copy(req, BaseResource.class);
    buildPath(req.getParentId(), baseResource, baseResource.getId());
    baseResourceService.updateById(baseResource);
    return new BaseResourceUpdateByIdRes();
  }


  public @Override DynamicsPage<BaseResourceExportQueryPageListInfoRes> queryPageList(BaseResourceExportQueryPageListReq req) {
    return baseResourceService.queryPageList(req);
  }

  public @Override void queryPageListExport(BaseResourceExportQueryPageListReq req) {
    DynamicsPage<BaseResourceExportQueryPageListInfoRes> page = queryPageList(req);
    List<BaseResourceExportQueryPageListInfoRes> list = page.getDataList();
    // 类型转换，  更换枚举 等操作
    List<BaseResourceExportQueryPageListInfoRes> listInfoRes = $.copyList(list, BaseResourceExportQueryPageListInfoRes.class);
    PoiExcelUtil.export(BaseResourceExportQueryPageListInfoRes.class, listInfoRes, "资源");
  }

  public @Override BaseResourceImportRes importData(@RequestParam("file") MultipartFile file) {
    List<BaseResourceImportReq> reqList = PoiExcelUtil.readData(file, new BaseResourceImportListener(), BaseResourceImportReq.class);
    // 类型转换，  更换枚举 等操作
    List<BaseResource> readList = $.copyList(reqList, BaseResource.class);
    boolean bool = baseResourceService.saveBatch(readList);
    int c = bool ? readList.size() : 0;
    return new BaseResourceImportRes().setCount(c);
  }

  public @Override BaseResourceQueryByIdListRes queryByIdListRes(BaseResourceQueryByIdListReq req) {
    MPJLambdaWrapper<BaseResource> q = new MPJLambdaWrapper<BaseResource>(BaseResource.class)
        .selectAll(BaseResource.class).in(BaseResource::getId, req.getIdList());
    List<BaseResource> list = this.baseResourceService.list(q);
    List<BaseResourceDto> dataList = $.copyList(list, BaseResourceDto.class);
    this.baseResourceService.setName(dataList);
    return new BaseResourceQueryByIdListRes().setDataList(dataList);
  }
}
