package com.olivia.peanut.flow.api.impl;

import com.baomidou.mybatisplus.core.toolkit.IdWorker;
import com.github.yulichang.wrapper.MPJLambdaWrapper;
import com.olivia.peanut.flow.api.FlowFormUserValueApi;
import com.olivia.peanut.flow.api.entity.flowFormUserValue.*;
import com.olivia.peanut.flow.api.impl.listener.FlowFormUserValueImportListener;
import com.olivia.peanut.flow.model.FlowFormUserValue;
import com.olivia.peanut.flow.service.FlowFormUserValueService;
import com.olivia.sdk.utils.$;
import com.olivia.sdk.utils.DynamicsPage;
import com.olivia.sdk.utils.PoiExcelUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * 工作流表单用户数据表(FlowFormUserValue)表服务实现类
 *
 * @author peanut
 * @since 2024-08-03 18:10:53
 */
@RestController
public class FlowFormUserValueApiImpl implements FlowFormUserValueApi {

  private @Autowired FlowFormUserValueService flowFormUserValueService;

  /****
   * insert
   *
   */
  public @Override FlowFormUserValueInsertRes insert(FlowFormUserValueInsertReq req) {
    this.flowFormUserValueService.save($.copy(req, FlowFormUserValue.class));
    return new FlowFormUserValueInsertRes().setCount(1);
  }

  @Override
  public FlowFormUserValueInsertRes insertBadBatch(List<FlowFormUserValueInsertReq> req) {
    $.requireNonNullCanIgnoreException(req, "用户值不能为空");
    if (req.getFirst().getId() == null) {
      req.forEach(t -> t.setId(IdWorker.getId()));
      this.flowFormUserValueService.saveBatch($.copyList(req, FlowFormUserValue.class));
    } else {
      this.flowFormUserValueService.updateBatchById($.copyList(req, FlowFormUserValue.class));
    }
    return new FlowFormUserValueInsertRes().setCount(req.size());

  }

  /****
   * deleteByIds
   *
   */
  public @Override FlowFormUserValueDeleteByIdListRes deleteByIdList(FlowFormUserValueDeleteByIdListReq req) {
    flowFormUserValueService.removeByIds(req.getIdList());
    return new FlowFormUserValueDeleteByIdListRes();
  }

  /****
   * queryList
   *
   */
  public @Override FlowFormUserValueQueryListRes queryList(FlowFormUserValueQueryListReq req) {
    return flowFormUserValueService.queryList(req);
  }

  /****
   * updateById
   *
   */
  public @Override FlowFormUserValueUpdateByIdRes updateById(FlowFormUserValueUpdateByIdReq req) {
    flowFormUserValueService.updateById($.copy(req, FlowFormUserValue.class));
    return new FlowFormUserValueUpdateByIdRes();

  }

  public @Override DynamicsPage<FlowFormUserValueExportQueryPageListInfoRes> queryPageList(FlowFormUserValueExportQueryPageListReq req) {
    return flowFormUserValueService.queryPageList(req);
  }

  public @Override void queryPageListExport(FlowFormUserValueExportQueryPageListReq req) {
    DynamicsPage<FlowFormUserValueExportQueryPageListInfoRes> page = queryPageList(req);
    List<FlowFormUserValueExportQueryPageListInfoRes> list = page.getDataList();
    // 类型转换，  更换枚举 等操作
    List<FlowFormUserValueExportQueryPageListInfoRes> listInfoRes = $.copyList(list, FlowFormUserValueExportQueryPageListInfoRes.class);
    PoiExcelUtil.export(FlowFormUserValueExportQueryPageListInfoRes.class, listInfoRes, "工作流表单用户数据表");
  }

  public @Override FlowFormUserValueImportRes importData(@RequestParam("file") MultipartFile file) {
    List<FlowFormUserValueImportReq> reqList = PoiExcelUtil.readData(file, new FlowFormUserValueImportListener(), FlowFormUserValueImportReq.class);
    // 类型转换，  更换枚举 等操作
    List<FlowFormUserValue> readList = $.copyList(reqList, FlowFormUserValue.class);
    boolean bool = flowFormUserValueService.saveBatch(readList);
    int c = bool ? readList.size() : 0;
    return new FlowFormUserValueImportRes().setCount(c);
  }

  public @Override FlowFormUserValueQueryByIdListRes queryByIdListRes(FlowFormUserValueQueryByIdListReq req) {
    MPJLambdaWrapper<FlowFormUserValue> q = new MPJLambdaWrapper<FlowFormUserValue>(FlowFormUserValue.class)
        .selectAll(FlowFormUserValue.class).in(FlowFormUserValue::getId, req.getIdList());
    List<FlowFormUserValue> list = this.flowFormUserValueService.list(q);
    List<FlowFormUserValueDto> dataList = $.copyList(list, FlowFormUserValueDto.class);
    this.flowFormUserValueService.setName(dataList);
    return new FlowFormUserValueQueryByIdListRes().setDataList(dataList);
  }
}
