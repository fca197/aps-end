package com.olivia.peanut.flow.api.impl;

import com.github.yulichang.wrapper.MPJLambdaWrapper;
import com.olivia.peanut.flow.api.FlowFormApi;
import com.olivia.peanut.flow.api.entity.flowForm.*;
import com.olivia.peanut.flow.api.impl.listener.FlowFormImportListener;
import com.olivia.peanut.flow.model.FlowForm;
import com.olivia.peanut.flow.service.FlowFormService;
import com.olivia.sdk.utils.$;
import com.olivia.sdk.utils.DynamicsPage;
import com.olivia.sdk.utils.PoiExcelUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * 工作流表单表(FlowForm)表服务实现类
 *
 * @author peanut
 * @since 2024-08-02 23:26:21
 */
@RestController
public class FlowFormApiImpl implements FlowFormApi {

  private @Autowired FlowFormService flowFormService;

  /****
   * insert
   *
   */
  public @Override FlowFormInsertRes insert(FlowFormInsertReq req) {
    return this.flowFormService.save(req);
//    return new FlowFormInsertRes().setCount(1);
  }

  /****
   * deleteByIds
   *
   */
  public @Override FlowFormDeleteByIdListRes deleteByIdList(FlowFormDeleteByIdListReq req) {
    flowFormService.removeByIds(req.getIdList());
    return new FlowFormDeleteByIdListRes();
  }

  /****
   * queryList
   *
   */
  public @Override FlowFormQueryListRes queryList(FlowFormQueryListReq req) {
    return flowFormService.queryList(req);
  }

  /****
   * updateById
   *
   */
  public @Override FlowFormUpdateByIdRes updateById(FlowFormUpdateByIdReq req) {
    return flowFormService.updateById(req);
//    return new FlowFormUpdateByIdRes();

  }

  public @Override DynamicsPage<FlowFormExportQueryPageListInfoRes> queryPageList(FlowFormExportQueryPageListReq req) {
    return flowFormService.queryPageList(req);
  }

  public @Override void queryPageListExport(FlowFormExportQueryPageListReq req) {
    DynamicsPage<FlowFormExportQueryPageListInfoRes> page = queryPageList(req);
    List<FlowFormExportQueryPageListInfoRes> list = page.getDataList();
    // 类型转换，  更换枚举 等操作
    List<FlowFormExportQueryPageListInfoRes> listInfoRes = $.copyList(list, FlowFormExportQueryPageListInfoRes.class);
    PoiExcelUtil.export(FlowFormExportQueryPageListInfoRes.class, listInfoRes, "工作流表单表");
  }

  public @Override FlowFormImportRes importData(@RequestParam("file") MultipartFile file) {
    List<FlowFormImportReq> reqList = PoiExcelUtil.readData(file, new FlowFormImportListener(), FlowFormImportReq.class);
    // 类型转换，  更换枚举 等操作
    List<FlowForm> readList = $.copyList(reqList, FlowForm.class);
    boolean bool = flowFormService.saveBatch(readList);
    int c = bool ? readList.size() : 0;
    return new FlowFormImportRes().setCount(c);
  }

  public @Override FlowFormQueryByIdListRes queryByIdListRes(FlowFormQueryByIdListReq req) {
    MPJLambdaWrapper<FlowForm> q = new MPJLambdaWrapper<FlowForm>(FlowForm.class)
        .selectAll(FlowForm.class).in(FlowForm::getId, req.getIdList());
    List<FlowForm> list = this.flowFormService.list(q);
    List<FlowFormDto> dataList = $.copyList(list, FlowFormDto.class);
    this.flowFormService.setName(dataList);
    return new FlowFormQueryByIdListRes().setDataList(dataList);
  }
}
