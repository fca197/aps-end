package com.olivia.peanut.base.api;


import com.olivia.peanut.base.api.entity.msgMessage.MsgMessageMaskReadReq;
import com.olivia.peanut.base.api.entity.msgMessage.MsgMessageMaskReadRes;
import com.olivia.peanut.base.api.entity.msgMessageRead.*;
import com.olivia.sdk.ann.InsertCheck;
import com.olivia.sdk.ann.UpdateCheck;
import com.olivia.sdk.utils.DynamicsPage;
import jakarta.validation.Valid;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

/**
 * (MsgMessageRead)对外API
 *
 * @author peanut
 * @since 2024-03-23 19:17:47
 */
// @FeignClient(value = "",contextId = "msgMessageRead-api",url = "${ portal..center.endpoint:}")
public interface MsgMessageReadApi {

  /**
   * 保存
   */
  @PostMapping("/msgMessageRead/insert")
  MsgMessageReadInsertRes insert(@RequestBody @Validated(InsertCheck.class) MsgMessageReadInsertReq req);

  /**
   * 根据ID 删除
   */
  @PostMapping("/msgMessageRead/deleteByIdList")
  MsgMessageReadDeleteByIdListRes deleteByIdList(@RequestBody @Valid MsgMessageReadDeleteByIdListReq req);

  /**
   * 查询
   */
  @PostMapping("/msgMessageRead/queryList")
  MsgMessageReadQueryListRes queryList(@RequestBody @Valid MsgMessageReadQueryListReq req);

  /**
   * 根据ID 更新
   */
  @PostMapping("/msgMessageRead/updateById")
  MsgMessageReadUpdateByIdRes updateById(@RequestBody @Validated(UpdateCheck.class) MsgMessageReadUpdateByIdReq req);

  /**
   * 分页查询
   */
  @PostMapping("/msgMessageRead/queryPageList")
  DynamicsPage<MsgMessageReadExportQueryPageListInfoRes> queryPageList(@RequestBody @Valid MsgMessageReadExportQueryPageListReq req);

  /**
   * 导出
   */
  @PostMapping("/msgMessageRead/exportQueryPageList")
  void queryPageListExport(@RequestBody @Valid MsgMessageReadExportQueryPageListReq req);

  /**
   * 导入
   */
  @PostMapping("/msgMessageRead/importData")
  MsgMessageReadImportRes importData(@RequestParam("file") MultipartFile file);


  /**
   * 根据ID 批量查询
   */
  @PostMapping("/msgMessageRead/queryByIdList")
  MsgMessageReadQueryByIdListRes queryByIdListRes(@RequestBody @Valid MsgMessageReadQueryByIdListReq req);

  @PostMapping("/msgMessageRead/maskRead")
  MsgMessageMaskReadRes maskRead(@RequestBody @Valid MsgMessageMaskReadReq req);

}
