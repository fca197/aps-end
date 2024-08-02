package com.olivia.peanut.flow.api.impl;

import java.time.LocalDateTime;

import com.olivia.peanut.flow.model.FlowFormItem;
import com.olivia.sdk.utils.$;
import com.olivia.sdk.utils.DynamicsPage;
import com.olivia.sdk.utils.PoiExcelUtil;
import java.util.stream.Collectors;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;
import org.apache.commons.lang3.StringUtils;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import com.olivia.peanut.flow.api.entity.flowFormItem.*;
import com.olivia.peanut.flow.service.FlowFormItemService;
import com.olivia.peanut.flow.model.*;
import com.baomidou.mybatisplus.core.conditions.query.*;
import com.github.yulichang.wrapper.MPJLambdaWrapper;
import org.springframework.web.bind.annotation.*;
import com.olivia.peanut.flow.api.FlowFormItemApi;

import com.olivia.peanut.flow.api.impl.listener.*;
import org.springframework.web.multipart.MultipartFile;

/**
 * 工作流表单项表(FlowFormItem)表服务实现类
 *
 * @author peanut
 * @since 2024-08-02 23:26:25
 */
@RestController
public class FlowFormItemApiImpl implements FlowFormItemApi {

  private @Autowired FlowFormItemService flowFormItemService;

  /****
   * insert
   *
   */
  public @Override FlowFormItemInsertRes insert(FlowFormItemInsertReq req) {
    this.flowFormItemService.save($.copy(req, FlowFormItem.class));
    return new FlowFormItemInsertRes().setCount(1);
  }

  /****
   * deleteByIds
   *
   */
  public @Override FlowFormItemDeleteByIdListRes deleteByIdList(FlowFormItemDeleteByIdListReq req) {
    flowFormItemService.removeByIds(req.getIdList());
    return new FlowFormItemDeleteByIdListRes();
  }

  /****
   * queryList
   *
   */
  public @Override FlowFormItemQueryListRes queryList(FlowFormItemQueryListReq req) {
    return flowFormItemService.queryList(req);
  }

  /****
   * updateById
   *
   */
  public @Override FlowFormItemUpdateByIdRes updateById(FlowFormItemUpdateByIdReq req) {
    flowFormItemService.updateById($.copy(req, FlowFormItem.class));
    return new FlowFormItemUpdateByIdRes();

  }

  public @Override DynamicsPage<FlowFormItemExportQueryPageListInfoRes> queryPageList(FlowFormItemExportQueryPageListReq req) {
    return flowFormItemService.queryPageList(req);
  }

  public @Override void queryPageListExport(FlowFormItemExportQueryPageListReq req) {
    DynamicsPage<FlowFormItemExportQueryPageListInfoRes> page = queryPageList(req);
    List<FlowFormItemExportQueryPageListInfoRes> list = page.getDataList();
    // 类型转换，  更换枚举 等操作
    List<FlowFormItemExportQueryPageListInfoRes> listInfoRes = $.copyList(list, FlowFormItemExportQueryPageListInfoRes.class);
    PoiExcelUtil.export(FlowFormItemExportQueryPageListInfoRes.class, listInfoRes, "工作流表单项表");
  }

  public @Override FlowFormItemImportRes importData(@RequestParam("file") MultipartFile file) {
    List<FlowFormItemImportReq> reqList = PoiExcelUtil.readData(file, new FlowFormItemImportListener(), FlowFormItemImportReq.class);
    // 类型转换，  更换枚举 等操作
    List<FlowFormItem> readList = $.copyList(reqList, FlowFormItem.class);
    boolean bool = flowFormItemService.saveBatch(readList);
    int c = bool ? readList.size() : 0;
    return new FlowFormItemImportRes().setCount(c);
  }

  public @Override FlowFormItemQueryByIdListRes queryByIdListRes(FlowFormItemQueryByIdListReq req) {
    MPJLambdaWrapper<FlowFormItem> q = new MPJLambdaWrapper<FlowFormItem>(FlowFormItem.class)
        .selectAll(FlowFormItem.class).in(FlowFormItem::getId, req.getIdList());
    List<FlowFormItem> list = this.flowFormItemService.list(q);
    List<FlowFormItemDto> dataList = $.copyList(list, FlowFormItemDto.class);
    this.flowFormItemService.setName(dataList);
    return new FlowFormItemQueryByIdListRes().setDataList(dataList);
  }
}
