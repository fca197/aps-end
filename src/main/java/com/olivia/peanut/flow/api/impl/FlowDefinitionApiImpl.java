package com.olivia.peanut.flow.api.impl;

import com.github.yulichang.wrapper.MPJLambdaWrapper;
import com.olivia.peanut.flow.api.FlowDefinitionApi;
import com.olivia.peanut.flow.api.entity.flowDefinition.*;
import com.olivia.peanut.flow.api.impl.listener.FlowDefinitionImportListener;
import com.olivia.peanut.flow.model.FlowDefinition;
import com.olivia.peanut.flow.service.FlowDefinitionService;
import com.olivia.sdk.utils.$;
import com.olivia.sdk.utils.DynamicsPage;
import com.olivia.sdk.utils.PoiExcelUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * 工作定义表(FlowDefinition)表服务实现类
 *
 * @author peanut
 * @since 2024-08-01 10:43:48
 */
@RestController
public class FlowDefinitionApiImpl implements FlowDefinitionApi {

  private @Autowired FlowDefinitionService flowDefinitionService;

  /****
   * insert
   *
   */
  public @Override FlowDefinitionInsertRes insert(FlowDefinitionInsertReq req) {
    this.flowDefinitionService.save($.copy(req, FlowDefinition.class));
    return new FlowDefinitionInsertRes().setCount(1);
  }

  /****
   * deleteByIds
   *
   */
  public @Override FlowDefinitionDeleteByIdListRes deleteByIdList(FlowDefinitionDeleteByIdListReq req) {
    flowDefinitionService.removeByIds(req.getIdList());
    return new FlowDefinitionDeleteByIdListRes();
  }

  /****
   * queryList
   *
   */
  public @Override FlowDefinitionQueryListRes queryList(FlowDefinitionQueryListReq req) {
    return flowDefinitionService.queryList(req);
  }

  /****
   * updateById
   *
   */
  public @Override FlowDefinitionUpdateByIdRes updateById(FlowDefinitionUpdateByIdReq req) {
    flowDefinitionService.updateById($.copy(req, FlowDefinition.class));
    return new FlowDefinitionUpdateByIdRes();

  }

  public @Override DynamicsPage<FlowDefinitionExportQueryPageListInfoRes> queryPageList(FlowDefinitionExportQueryPageListReq req) {
    return flowDefinitionService.queryPageList(req);
  }

  public @Override void queryPageListExport(FlowDefinitionExportQueryPageListReq req) {
    DynamicsPage<FlowDefinitionExportQueryPageListInfoRes> page = queryPageList(req);
    List<FlowDefinitionExportQueryPageListInfoRes> list = page.getDataList();
    // 类型转换，  更换枚举 等操作
    List<FlowDefinitionExportQueryPageListInfoRes> listInfoRes = $.copyList(list, FlowDefinitionExportQueryPageListInfoRes.class);
    PoiExcelUtil.export(FlowDefinitionExportQueryPageListInfoRes.class, listInfoRes, "工作定义表");
  }

  public @Override FlowDefinitionImportRes importData(@RequestParam("file") MultipartFile file) {
    List<FlowDefinitionImportReq> reqList = PoiExcelUtil.readData(file, new FlowDefinitionImportListener(), FlowDefinitionImportReq.class);
    // 类型转换，  更换枚举 等操作
    List<FlowDefinition> readList = $.copyList(reqList, FlowDefinition.class);
    boolean bool = flowDefinitionService.saveBatch(readList);
    int c = bool ? readList.size() : 0;
    return new FlowDefinitionImportRes().setCount(c);
  }

  public @Override FlowDefinitionQueryByIdListRes queryByIdListRes(FlowDefinitionQueryByIdListReq req) {
    MPJLambdaWrapper<FlowDefinition> q = new MPJLambdaWrapper<FlowDefinition>(FlowDefinition.class)
        .selectAll(FlowDefinition.class).in(FlowDefinition::getId, req.getIdList());
    List<FlowDefinition> list = this.flowDefinitionService.list(q);
    List<FlowDefinitionDto> dataList = $.copyList(list, FlowDefinitionDto.class);
    this.flowDefinitionService.setName(dataList);
    return new FlowDefinitionQueryByIdListRes().setDataList(dataList);
  }
}
