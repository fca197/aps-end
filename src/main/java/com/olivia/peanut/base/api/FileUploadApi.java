package com.olivia.peanut.base.api;


import com.olivia.peanut.base.api.entity.fileUpload.*;
import com.olivia.sdk.ann.InsertCheck;
import com.olivia.sdk.ann.UpdateCheck;
import com.olivia.sdk.utils.DynamicsPage;
import jakarta.validation.Valid;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

/**
 * (FileUpload)对外API
 *
 * @author peanut
 * @since 2024-03-18 15:22:31
 */
// @FeignClient(value = "",contextId = "fileUpload-api",url = "${ portal..center.endpoint:}")
public interface FileUploadApi {

  /**
   * 保存
   */
  @PostMapping("/fileUpload/insertEntity")
  FileUploadInsertRes insert(@RequestBody @Validated(InsertCheck.class) FileUploadInsertReq req);

  @PostMapping("/fileUpload/insert")
  FileUploadInsertRes insertByType(@RequestParam(name = "fileType", required = false) String fileType, @RequestParam("file") MultipartFile multipartFile);

  /**
   * 根据ID 删除
   */
  @PostMapping("/fileUpload/deleteByIdList")
  FileUploadDeleteByIdListRes deleteByIdList(@RequestBody @Valid FileUploadDeleteByIdListReq req);

  /**
   * 查询
   */
  @PostMapping("/fileUpload/queryList")
  FileUploadQueryListRes queryList(@RequestBody @Valid FileUploadQueryListReq req);

  /**
   * 根据ID 更新
   */
  @PostMapping("/fileUpload/updateById")
  FileUploadUpdateByIdRes updateById(@RequestBody @Validated(UpdateCheck.class) FileUploadUpdateByIdReq req);

  /**
   * 分页查询
   */
  @PostMapping("/fileUpload/queryPageList")
  DynamicsPage<FileUploadExportQueryPageListInfoRes> queryPageList(@RequestBody @Valid FileUploadExportQueryPageListReq req);

  /**
   * 导出
   */
  @PostMapping("/fileUpload/exportQueryPageList")
  void queryPageListExport(@RequestBody @Valid FileUploadExportQueryPageListReq req);

  /**
   * 导入
   */
  @PostMapping("/fileUpload/importData")
  FileUploadImportRes importData(@RequestParam("file") MultipartFile file);


  /**
   * 根据ID 批量查询
   */
  @PostMapping("/fileUpload/queryByIdList")
  FileUploadQueryByIdListRes queryByIdListRes(@RequestBody @Valid FileUploadQueryByIdListReq req);


  @PostMapping("/fileUpload/downLoad")
  void downLoadFile(@RequestBody @Valid FileUploadDownLoadReq req);

  @PostMapping("/fileUpload/getFileBase64")
  GetFileBase64Res getFileBase64(@RequestBody @Valid GetFileBase64Req req);

  @GetMapping("/fileUpload/getFileByte/{id}")
  void getFileByte(@PathVariable("id") Long id);

}
