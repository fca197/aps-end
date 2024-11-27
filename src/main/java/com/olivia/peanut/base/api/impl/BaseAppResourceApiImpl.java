package com.olivia.peanut.base.api.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.github.yulichang.wrapper.MPJLambdaWrapper;
import com.olivia.peanut.base.api.BaseAppResourceApi;
import com.olivia.peanut.base.api.entity.baseAppResource.*;
import com.olivia.peanut.base.api.impl.listener.BaseAppResourceImportListener;
import com.olivia.peanut.base.model.BaseAppResource;
import com.olivia.peanut.base.service.BaseAppResourceService;
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
 * 资源(BaseAppResource)表服务实现类
 *
 * @author peanut
 * @since 2024-08-06 17:30:28
 */
@RestController
public class BaseAppResourceApiImpl implements BaseAppResourceApi {

  private @Autowired BaseAppResourceService baseAppResourceService;

  /****
   * insert
   *
   */
  public @Override BaseAppResourceInsertRes insert(BaseAppResourceInsertReq req) {
    this.baseAppResourceService.save($.copy(req, BaseAppResource.class));
    return new BaseAppResourceInsertRes().setCount(1);
  }

  @Override
  @Transactional
  public BaseAppResourceInsertListRes insertList(BaseAppResourceInsertListReq req) {
    this.baseAppResourceService.remove(new LambdaQueryWrapper<BaseAppResource>().eq(BaseAppResource::getAppId, req.getAppId()));
    baseAppResourceService.saveBatch(
        req.getAppResourceIdList().stream().map(appResourceId -> new BaseAppResource().setAppId(req.getAppId()).setResourceId(appResourceId)).toList());
    return new BaseAppResourceInsertListRes().setSize(req.getAppResourceIdList().size());
  }

  /****
   * deleteByIds
   *
   */
  public @Override BaseAppResourceDeleteByIdListRes deleteByIdList(BaseAppResourceDeleteByIdListReq req) {
    baseAppResourceService.removeByIds(req.getIdList());
    return new BaseAppResourceDeleteByIdListRes();
  }

  /****
   * queryList
   *
   */
  public @Override BaseAppResourceQueryListRes queryList(BaseAppResourceQueryListReq req) {
    return baseAppResourceService.queryList(req);
  }

  /****
   * updateById
   *
   */
  public @Override BaseAppResourceUpdateByIdRes updateById(BaseAppResourceUpdateByIdReq req) {
    baseAppResourceService.updateById($.copy(req, BaseAppResource.class));
    return new BaseAppResourceUpdateByIdRes();

  }

  public @Override DynamicsPage<BaseAppResourceExportQueryPageListInfoRes> queryPageList(BaseAppResourceExportQueryPageListReq req) {
    return baseAppResourceService.queryPageList(req);
  }

  public @Override void queryPageListExport(BaseAppResourceExportQueryPageListReq req) {
    DynamicsPage<BaseAppResourceExportQueryPageListInfoRes> page = queryPageList(req);
    List<BaseAppResourceExportQueryPageListInfoRes> list = page.getDataList();
    // 类型转换，  更换枚举 等操作
    List<BaseAppResourceExportQueryPageListInfoRes> listInfoRes = $.copyList(list, BaseAppResourceExportQueryPageListInfoRes.class);
    PoiExcelUtil.export(BaseAppResourceExportQueryPageListInfoRes.class, listInfoRes, "资源");
  }

  public @Override BaseAppResourceImportRes importData(@RequestParam("file") MultipartFile file) {
    List<BaseAppResourceImportReq> reqList = PoiExcelUtil.readData(file, new BaseAppResourceImportListener(), BaseAppResourceImportReq.class);
    // 类型转换，  更换枚举 等操作
    List<BaseAppResource> readList = $.copyList(reqList, BaseAppResource.class);
    boolean bool = baseAppResourceService.saveBatch(readList);
    int c = bool ? readList.size() : 0;
    return new BaseAppResourceImportRes().setCount(c);
  }

  public @Override BaseAppResourceQueryByIdListRes queryByIdListRes(BaseAppResourceQueryByIdListReq req) {
    MPJLambdaWrapper<BaseAppResource> q = new MPJLambdaWrapper<BaseAppResource>(BaseAppResource.class)
        .selectAll(BaseAppResource.class).in(BaseAppResource::getId, req.getIdList());
    List<BaseAppResource> list = this.baseAppResourceService.list(q);
    List<BaseAppResourceDto> dataList = $.copyList(list, BaseAppResourceDto.class);
    this.baseAppResourceService.setName(dataList);
    return new BaseAppResourceQueryByIdListRes().setDataList(dataList);
  }
}
