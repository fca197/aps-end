package com.olivia.peanut.base.service;

import com.github.yulichang.base.MPJBaseService;
import com.olivia.peanut.base.api.entity.fileUpload.FileUploadExportQueryPageListInfoRes;
import com.olivia.peanut.base.api.entity.fileUpload.FileUploadExportQueryPageListReq;
import com.olivia.peanut.base.api.entity.fileUpload.FileUploadQueryListReq;
import com.olivia.peanut.base.api.entity.fileUpload.FileUploadQueryListRes;
import com.olivia.peanut.base.model.FileUpload;
import com.olivia.sdk.utils.DynamicsPage;

/**
 * (FileUpload)表服务接口
 *
 * @author peanut
 * @since 2024-03-18 15:22:31
 */
public interface FileUploadService extends MPJBaseService<FileUpload> {

  FileUploadQueryListRes queryList(FileUploadQueryListReq req);

  DynamicsPage<FileUploadExportQueryPageListInfoRes> queryPageList(FileUploadExportQueryPageListReq req);

}

