package com.olivia.peanut.flow.api.impl;

import com.github.yulichang.wrapper.MPJLambdaWrapper;
import com.olivia.peanut.flow.api.FlowGroupApi;
import com.olivia.peanut.flow.api.entity.flowGroup.*;
import com.olivia.peanut.flow.api.impl.listener.FlowGroupImportListener;
import com.olivia.peanut.flow.model.FlowGroup;
import com.olivia.peanut.flow.service.FlowGroupService;
import com.olivia.sdk.utils.$;
import com.olivia.sdk.utils.DynamicsPage;
import com.olivia.sdk.utils.PoiExcelUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * 工作流组表(FlowGroup)表服务实现类
 *
 * @author peanut
 * @since 2024-08-01 10:43:52
 */
@RestController
public class FlowGroupApiImpl implements FlowGroupApi {

  private @Autowired FlowGroupService flowGroupService;

  /****
   * insert
   *
   */
  public @Override FlowGroupInsertRes insert(FlowGroupInsertReq req) {
    this.flowGroupService.save($.copy(req, FlowGroup.class));
    return new FlowGroupInsertRes().setCount(1);
  }

  /****
   * deleteByIds
   *
   */
  public @Override FlowGroupDeleteByIdListRes deleteByIdList(FlowGroupDeleteByIdListReq req) {
    flowGroupService.removeByIds(req.getIdList());
    return new FlowGroupDeleteByIdListRes();
  }

  /****
   * queryList
   *
   */
  public @Override FlowGroupQueryListRes queryList(FlowGroupQueryListReq req) {
    return flowGroupService.queryList(req);
  }

  /****
   * updateById
   *
   */
  public @Override FlowGroupUpdateByIdRes updateById(FlowGroupUpdateByIdReq req) {
    flowGroupService.updateById($.copy(req, FlowGroup.class));
    return new FlowGroupUpdateByIdRes();

  }

  public @Override DynamicsPage<FlowGroupExportQueryPageListInfoRes> queryPageList(FlowGroupExportQueryPageListReq req) {
    return flowGroupService.queryPageList(req);
  }

  public @Override void queryPageListExport(FlowGroupExportQueryPageListReq req) {
    DynamicsPage<FlowGroupExportQueryPageListInfoRes> page = queryPageList(req);
    List<FlowGroupExportQueryPageListInfoRes> list = page.getDataList();
    // 类型转换，  更换枚举 等操作
    List<FlowGroupExportQueryPageListInfoRes> listInfoRes = $.copyList(list, FlowGroupExportQueryPageListInfoRes.class);
    PoiExcelUtil.export(FlowGroupExportQueryPageListInfoRes.class, listInfoRes, "工作流组表");
  }

  public @Override FlowGroupImportRes importData(@RequestParam("file") MultipartFile file) {
    List<FlowGroupImportReq> reqList = PoiExcelUtil.readData(file, new FlowGroupImportListener(), FlowGroupImportReq.class);
    // 类型转换，  更换枚举 等操作
    List<FlowGroup> readList = $.copyList(reqList, FlowGroup.class);
    boolean bool = flowGroupService.saveBatch(readList);
    int c = bool ? readList.size() : 0;
    return new FlowGroupImportRes().setCount(c);
  }

  public @Override FlowGroupQueryByIdListRes queryByIdListRes(FlowGroupQueryByIdListReq req) {
    MPJLambdaWrapper<FlowGroup> q = new MPJLambdaWrapper<FlowGroup>(FlowGroup.class)
        .selectAll(FlowGroup.class).in(FlowGroup::getId, req.getIdList());
    List<FlowGroup> list = this.flowGroupService.list(q);
    List<FlowGroupDto> dataList = $.copyList(list, FlowGroupDto.class);
    this.flowGroupService.setName(dataList);
    return new FlowGroupQueryByIdListRes().setDataList(dataList);
  }
}
