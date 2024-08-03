package com.olivia.peanut.flow.api;

import com.olivia.peanut.flow.api.entity.flowFormUserValue.*;
import com.olivia.sdk.ann.InsertCheck;
import com.olivia.sdk.ann.UpdateCheck;
import com.olivia.sdk.utils.DynamicsPage;
import jakarta.validation.Valid;
import java.util.List;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;


/**
 * 工作流表单用户数据表(FlowFormUserValue)对外API
 *
 * @author peanut
 * @since 2024-08-03 18:10:53
 */
// @FeignClient(value = "",contextId = "flowFormUserValue-api",url = "${ portal..center.endpoint:}")
public interface FlowFormUserValueApi {

  /**
   * 保存 工作流表单用户数据表
   */
  @PostMapping("/flowFormUserValue/insert")
  FlowFormUserValueInsertRes insert(@RequestBody @Validated(InsertCheck.class) FlowFormUserValueInsertReq req);

  @PostMapping("/flowFormUserValue/insertBatch")
  FlowFormUserValueInsertRes insertBadBatch(@RequestBody @Validated(InsertCheck.class) List<FlowFormUserValueInsertReq> req);

  /**
   * 根据ID 删除 工作流表单用户数据表
   */
  @PostMapping("/flowFormUserValue/deleteByIdList")
  FlowFormUserValueDeleteByIdListRes deleteByIdList(@RequestBody @Valid FlowFormUserValueDeleteByIdListReq req);

  /**
   * 查询 工作流表单用户数据表
   */
  @PostMapping("/flowFormUserValue/queryList")
  FlowFormUserValueQueryListRes queryList(@RequestBody @Valid FlowFormUserValueQueryListReq req);

  /**
   * 根据ID 更新 工作流表单用户数据表
   */
  @PostMapping("/flowFormUserValue/updateById")
  FlowFormUserValueUpdateByIdRes updateById(@RequestBody @Validated(UpdateCheck.class) FlowFormUserValueUpdateByIdReq req);

  /**
   * 分页查询 工作流表单用户数据表
   */
  @PostMapping("/flowFormUserValue/queryPageList")
  DynamicsPage<FlowFormUserValueExportQueryPageListInfoRes> queryPageList(@RequestBody @Valid FlowFormUserValueExportQueryPageListReq req);

  /**
   * 导出 工作流表单用户数据表
   */
  @PostMapping("/flowFormUserValue/exportQueryPageList")
  void queryPageListExport(@RequestBody @Valid FlowFormUserValueExportQueryPageListReq req);

  /**
   * 导入
   */
  @PostMapping("/flowFormUserValue/importData")
  FlowFormUserValueImportRes importData(@RequestParam("file") MultipartFile file);


  /**
   * 根据ID 批量查询
   */
  @PostMapping("/flowFormUserValue/queryByIdList")
  FlowFormUserValueQueryByIdListRes queryByIdListRes(@RequestBody @Valid FlowFormUserValueQueryByIdListReq req);


}
