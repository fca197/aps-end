package com.olivia.peanut.base.api;


import com.olivia.peanut.base.api.entity.dictionary.*;
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
 * 字典值(Dictionary)对外API
 *
 * @author makejava
 * @since 2024-03-11 10:44:04
 */
// @FeignClient(value = "",contextId = "dictionary-api",url = "${ portal..center.endpoint:}")
public interface DictionaryApi {

  /**
   * 保存 字典值
   */
  @PostMapping("/dictionary/insert")
  DictionaryInsertRes insert(@RequestBody @Validated(InsertCheck.class) DictionaryInsertReq req);

  /**
   * 根据ID 删除 字典值
   */
  @PostMapping("/dictionary/deleteByIdList")
  DictionaryDeleteByIdListRes deleteByIdList(@RequestBody @Valid DictionaryDeleteByIdListReq req);

  /**
   * 查询 字典值
   */
  @PostMapping("/dictionary/queryList")
  DictionaryQueryListRes queryList(@RequestBody @Valid DictionaryQueryListReq req);

  /**
   * 根据ID 更新 字典值
   */
  @PostMapping("/dictionary/updateById")
  DictionaryUpdateByIdRes updateById(@RequestBody @Validated(UpdateCheck.class) DictionaryUpdateByIdReq req);

  /**
   * 分页查询 字典值
   */
  @PostMapping("/dictionary/queryPageList")
  DynamicsPage<DictionaryExportQueryPageListInfoRes> queryPageList(@RequestBody @Valid DictionaryExportQueryPageListReq req);

  /**
   * 导出 字典值
   */
  @PostMapping("/dictionary/exportQueryPageList")
  void queryPageListExport(@RequestBody @Valid DictionaryExportQueryPageListReq req);

  /**
   * 导入
   */
  @PostMapping("/dictionary/importData")
  DictionaryImportRes importData(@RequestParam("file") MultipartFile file);


  /**
   * 根据ID 批量查询
   */
  @PostMapping("/dictionary/queryByIdList")
  DictionaryQueryByIdListRes queryByIdListRes(@RequestBody @Valid DictionaryQueryByIdListReq req);


}
