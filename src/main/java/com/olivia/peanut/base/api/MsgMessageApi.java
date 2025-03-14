package com.olivia.peanut.base.api;


import com.olivia.peanut.base.api.entity.msgMessage.*;
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
 * (MsgMessage)对外API
 *
 * @author peanut
 * @since 2024-03-23 19:05:39
 */
// @FeignClient(value = "",contextId = "msgMessage-api",url = "${ portal..center.endpoint:}")
public interface MsgMessageApi {

  /**
   * 保存
   */
  @PostMapping("/msgMessage/insert")
  MsgMessageInsertRes insert(@RequestBody @Validated(InsertCheck.class) MsgMessageInsertReq req);

  /**
   * 根据ID 删除
   */
  @PostMapping("/msgMessage/deleteByIdList")
  MsgMessageDeleteByIdListRes deleteByIdList(@RequestBody @Valid MsgMessageDeleteByIdListReq req);

  /**
   * 查询
   */
  @PostMapping("/msgMessage/queryList")
  MsgMessageQueryListRes queryList(@RequestBody @Valid MsgMessageQueryListReq req);

  /**
   * 根据ID 更新
   */
  @PostMapping("/msgMessage/updateById")
  MsgMessageUpdateByIdRes updateById(@RequestBody @Validated(UpdateCheck.class) MsgMessageUpdateByIdReq req);

  /**
   * 分页查询
   */
  @PostMapping("/msgMessage/queryPageList")
  DynamicsPage<MsgMessageExportQueryPageListInfoRes> queryPageList(@RequestBody @Valid MsgMessageExportQueryPageListReq req);

  /**
   * 导出
   */
  @PostMapping("/msgMessage/exportQueryPageList")
  void queryPageListExport(@RequestBody @Valid MsgMessageExportQueryPageListReq req);

  /**
   * 导入
   */
  @PostMapping("/msgMessage/importData")
  MsgMessageImportRes importData(@RequestParam("file") MultipartFile file);


  /**
   * 根据ID 批量查询
   */
  @PostMapping("/msgMessage/queryByIdList")
  MsgMessageQueryByIdListRes queryByIdListRes(@RequestBody @Valid MsgMessageQueryByIdListReq req);

  @PostMapping("/msgMessage/unReadCount")
  UnReadCountRes unReadCount(@RequestBody @Valid UnReadCountReq req);
}
