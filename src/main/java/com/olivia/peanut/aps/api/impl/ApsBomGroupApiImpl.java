package com.olivia.peanut.aps.api.impl;

import com.baomidou.mybatisplus.core.toolkit.IdWorker;
import com.github.yulichang.wrapper.MPJLambdaWrapper;
import com.olivia.peanut.aps.api.ApsBomGroupApi;
import com.olivia.peanut.aps.api.entity.apsBomGroup.*;
import com.olivia.peanut.aps.api.impl.listener.ApsBomGroupImportListener;
import com.olivia.peanut.aps.model.ApsBomGroup;
import com.olivia.peanut.aps.service.ApsBomGroupService;
import com.olivia.sdk.utils.$;
import com.olivia.sdk.utils.DynamicsPage;
import com.olivia.sdk.utils.PoiExcelUtil;
import com.olivia.sdk.utils.RunUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Objects;

/**
 * 零件组配置(ApsBomGroup)表服务实现类
 *
 * @author peanut
 * @since 2024-06-19 17:41:23
 */
@RestController
public class ApsBomGroupApiImpl implements ApsBomGroupApi {

  private @Autowired ApsBomGroupService apsBomGroupService;

  /****
   * insert
   *
   */
  public @Override ApsBomGroupInsertRes insert(ApsBomGroupInsertReq req) {

    ApsBomGroup bomGroup = $.copy(req, ApsBomGroup.class);
    bomGroup.setId(IdWorker.getId());
    if (Objects.equals(req.getParentId(), 0L)) {
      bomGroup.setPathId("0/" + bomGroup.getId());
    } else {
      ApsBomGroup apsBomGroup = this.apsBomGroupService.getById(req.getParentId());
      bomGroup.setPathId(apsBomGroup.getPathId() + "/" + bomGroup.getId());
    }
    this.apsBomGroupService.save(bomGroup);
    return new ApsBomGroupInsertRes().setCount(1);
  }

  /****
   * deleteByIds
   *
   */
  public @Override ApsBomGroupDeleteByIdListRes deleteByIdList(ApsBomGroupDeleteByIdListReq req) {
    apsBomGroupService.removeByIds(req.getIdList());
    return new ApsBomGroupDeleteByIdListRes();
  }

  /****
   * queryList
   *
   */
  public @Override ApsBomGroupQueryListRes queryList(ApsBomGroupQueryListReq req) {
    return apsBomGroupService.queryList(req);
  }

  /****
   * updateById
   *
   */
  public @Override ApsBomGroupUpdateByIdRes updateById(ApsBomGroupUpdateByIdReq req) {
    return apsBomGroupService.updateById(req);


  }

  public @Override DynamicsPage<ApsBomGroupExportQueryPageListInfoRes> queryPageList(ApsBomGroupExportQueryPageListReq req) {
    return apsBomGroupService.queryPageList(req);
  }

  public @Override void queryPageListExport(ApsBomGroupExportQueryPageListReq req) {
    DynamicsPage<ApsBomGroupExportQueryPageListInfoRes> page = queryPageList(req);
    List<ApsBomGroupExportQueryPageListInfoRes> list = page.getDataList();
    // 类型转换，  更换枚举 等操作
    List<ApsBomGroupExportQueryPageListInfoRes> listInfoRes = $.copyList(list, ApsBomGroupExportQueryPageListInfoRes.class);
    PoiExcelUtil.export(ApsBomGroupExportQueryPageListInfoRes.class, listInfoRes, "零件组配置");
  }

  public @Override ApsBomGroupImportRes importData(@RequestParam("file") MultipartFile file) {
    RunUtils.noImpl();
    List<ApsBomGroupImportReq> reqList = PoiExcelUtil.readData(file, new ApsBomGroupImportListener(), ApsBomGroupImportReq.class);
    // 类型转换，  更换枚举 等操作
    List<ApsBomGroup> readList = $.copyList(reqList, ApsBomGroup.class);
    boolean bool = apsBomGroupService.saveBatch(readList);
    int c = bool ? readList.size() : 0;
    return new ApsBomGroupImportRes().setCount(c);
  }

  public @Override ApsBomGroupQueryByIdListRes queryByIdListRes(ApsBomGroupQueryByIdListReq req) {
    MPJLambdaWrapper<ApsBomGroup> q = new MPJLambdaWrapper<ApsBomGroup>(ApsBomGroup.class)
        .selectAll(ApsBomGroup.class).in(ApsBomGroup::getId, req.getIdList());
    List<ApsBomGroup> list = this.apsBomGroupService.list(q);
    List<ApsBomGroupDto> dataList = $.copyList(list, ApsBomGroupDto.class);
    this.apsBomGroupService.setName(dataList);
    return new ApsBomGroupQueryByIdListRes().setDataList(dataList);
  }
}
