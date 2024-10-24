package com.olivia.peanut.portal.api.impl;


import cn.hutool.core.io.FileUtil;
import com.baomidou.mybatisplus.core.toolkit.IdWorker;
import com.github.yulichang.wrapper.MPJLambdaWrapper;
import com.olivia.peanut.portal.api.FileUploadApi;
import com.olivia.peanut.portal.api.entity.fileUpload.*;
import com.olivia.peanut.portal.api.impl.listener.FileUploadImportListener;
import com.olivia.peanut.portal.model.FileUpload;
import com.olivia.peanut.portal.service.FileUploadService;
import com.olivia.sdk.ann.MethodExt;
import com.olivia.sdk.config.PeanutProperties;
import com.olivia.sdk.exception.CanIgnoreException;
import com.olivia.sdk.filter.LoginUserContext;
import com.olivia.sdk.utils.*;
import jakarta.annotation.Resource;
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServletResponse;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.net.URLEncoder;
import java.nio.channels.Channels;
import java.time.LocalDate;
import java.util.Base64;
import java.util.List;
import java.util.Objects;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * (FileUpload)表服务实现类
 *
 * @author peanut
 * @since 2024-03-18 15:22:31
 */
@Slf4j
@RestController
public class FileUploadApiImpl implements FileUploadApi {

  @Resource
  PeanutProperties peanutProperties;
  private @Autowired FileUploadService fileUploadService;

  /****
   * insert
   *
   */
  public @Override FileUploadInsertRes insert(FileUploadInsertReq req) {
    this.fileUploadService.save($.copy(req, FileUpload.class));
    return new FileUploadInsertRes().setCount(1);
  }

  /****
   * deleteByIds
   *
   */
  public @Override FileUploadDeleteByIdListRes deleteByIdList(FileUploadDeleteByIdListReq req) {
    fileUploadService.removeByIds(req.getIdList());
    return new FileUploadDeleteByIdListRes();
  }

  /****
   * queryList
   *
   */
  public @Override FileUploadQueryListRes queryList(FileUploadQueryListReq req) {
    return fileUploadService.queryList(req);
  }

  /****
   * updateById
   *
   */
  public @Override FileUploadUpdateByIdRes updateById(FileUploadUpdateByIdReq req) {
    fileUploadService.updateById($.copy(req, FileUpload.class));
    return new FileUploadUpdateByIdRes();

  }

  public @Override DynamicsPage<FileUploadExportQueryPageListInfoRes> queryPageList(FileUploadExportQueryPageListReq req) {
    return fileUploadService.queryPageList(req);
  }

  public @Override void queryPageListExport(FileUploadExportQueryPageListReq req) {
    DynamicsPage<FileUploadExportQueryPageListInfoRes> page = queryPageList(req);
    List<FileUploadExportQueryPageListInfoRes> list = page.getDataList();
    // 类型转换，  更换枚举 等操作
    List<FileUploadExportQueryPageListInfoRes> listInfoRes = $.copyList(list, FileUploadExportQueryPageListInfoRes.class);
    PoiExcelUtil.export(FileUploadExportQueryPageListInfoRes.class, listInfoRes, "");
  }

  public @Override FileUploadImportRes importData(@RequestParam("file") MultipartFile file) {
    List<FileUploadImportReq> reqList = PoiExcelUtil.readData(file, new FileUploadImportListener(), FileUploadImportReq.class);
    // 类型转换，  更换枚举 等操作
    List<FileUpload> readList = $.copyList(reqList, FileUpload.class);
    boolean bool = fileUploadService.saveBatch(readList);
    int c = bool ? readList.size() : 0;
    return new FileUploadImportRes().setCount(c);
  }

  public @Override FileUploadQueryByIdListRes queryByIdListRes(FileUploadQueryByIdListReq req) {
    MPJLambdaWrapper<FileUpload> q = new MPJLambdaWrapper<FileUpload>(FileUpload.class).selectAll(FileUpload.class).in(FileUpload::getId, req.getIdList());
    List<FileUpload> list = this.fileUploadService.list(q);
    List<FileUploadDto> dataList = $.copyList(list, FileUploadDto.class);
    return new FileUploadQueryByIdListRes().setDataList(dataList);
  }

  @Override
  public FileUploadInsertRes insertByType(String fileType, MultipartFile multipartFile) {
    fileType = StringUtils.firstNonEmpty(fileType, Str.DEFAULT);
    String filename = multipartFile.getOriginalFilename();
    FileUpload fileUpload = new FileUpload().setFileType(fileType).setFileName(filename).setExpireTime("9999-12-31 23:59:59");
    fileUpload.setId(IdWorker.getId());
    fileUpload.setFileSuffix(FileUtil.extName(filename));
    LocalDate localDate = LocalDate.now();
    String localDir = peanutProperties.getLocalFileUploadPath() + "/" + fileType + "/" + localDate.getYear() + "/" + localDate.getDayOfMonth() + "/";
    String localPath = localDir + IdWorker.getIdStr() + "." + fileUpload.getFileSuffix();
    FileUtil.mkParentDirs(localPath);
    try {
      multipartFile.transferTo(new File(localPath));
      fileUpload.setLocalFilePath(localPath.replace(peanutProperties.getLocalFileUploadPath(), ""));

      this.fileUploadService.save(fileUpload);
      return new FileUploadInsertRes().setData($.copy(fileUpload, FileUploadDto.class));
    } catch (Exception e) {
      log.error("文件上传失败 {}", e.getMessage(), e);
      throw new CanIgnoreException("文件上传失败");
    }
  }

  @Override
  @MethodExt(isDownLoad = true)
  public void downLoadFile(FileUploadDownLoadReq req) {
    HttpServletResponse response = ReqResUtils.getResponse();
    FileUpload fileUpload = this.fileUploadService.getById(req.getId());
    $.requireNonNullCanIgnoreException(fileUpload, "文件不存在");
    try (InputStream inputStream = new FileInputStream(peanutProperties.getLocalFileUploadPath() + fileUpload.getLocalFilePath());
         //
         ServletOutputStream outputStream = response.getOutputStream();) {
      response.reset();
      response.setContentType("application/octet-stream");
      String filename = fileUpload.getFileName();
      response.addHeader("Content-Disposition", "attachment; filename=" + URLEncoder.encode(filename, "UTF-8"));
      byte[] b = new byte[1024];
      int len;
      //从输入流中读取一定数量的字节，并将其存储在缓冲区字节数组中，读到末尾返回-1
      while ((len = inputStream.read(b)) > 0) {
        outputStream.write(b, 0, len);
      }
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }

  @Override
  @MethodExt(printResult = false)
  public GetFileBase64Res getFileBase64(GetFileBase64Req req) {
    FileUpload fileUpload = this.fileUploadService.getById(req.getId());
    if (Objects.isNull(fileUpload)) {
      return new GetFileBase64Res().setBase64("");
    }
    File file = new File(peanutProperties.getLocalFileUploadPath() + fileUpload.getLocalFilePath());
    if (file.exists()) {
      try {
        byte[] bytes = FileUtils.readFileToByteArray(file);
        return new GetFileBase64Res().setBase64(Base64.getEncoder().encodeToString(bytes));
      } catch (Exception e) {
        log.error("文件读取失败 {}", e.getMessage(), e);
        throw new CanIgnoreException("文件读取失败");
      }
    }
    return null;
  }

  @Override
  public void getFileByte(Long id) {
    LoginUserContext.ignoreTenantId();
    FileUpload fileUpload = this.fileUploadService.getById(id);
    $.requireNonNullCanIgnoreException(fileUpload, "文件不存在");
    HttpServletResponse response = ReqResUtils.getResponse();
    try (FileInputStream fis = new FileInputStream(peanutProperties.getLocalFileUploadPath() + fileUpload.getLocalFilePath())) {
      response.setContentType(getResponseContentType(fileUpload.getFileSuffix()));
      fis.getChannel().transferTo(0, fis.available(), Channels.newChannel(response.getOutputStream()));
    } catch (Exception e) {
      log.error("文件读取失败 {}", e.getMessage(), e);
      throw new CanIgnoreException(e);
    }
  }

  public String getResponseContentType(String str) {
    return switch (str) {
      case "jpg" -> "image/jpeg";
      case "png" -> "image/png";
      case "gif" -> "image/gif";
      case "bmp" -> "image/bmp";
      case "excel" -> "application/vnd.ms-excel";
      case "pdf" -> "application/pdf";
      default -> "application/octet-stream";
    };
  }
}
