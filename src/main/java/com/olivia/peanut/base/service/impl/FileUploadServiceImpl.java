package com.olivia.peanut.base.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.github.yulichang.base.MPJBaseServiceImpl;
import com.github.yulichang.wrapper.MPJLambdaWrapper;
import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.olivia.peanut.base.api.entity.fileUpload.*;
import com.olivia.peanut.base.mapper.FileUploadMapper;
import com.olivia.peanut.base.model.FileUpload;
import com.olivia.peanut.base.service.FileUploadService;
import com.olivia.sdk.utils.$;
import com.olivia.sdk.utils.DynamicsPage;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

/**
 * (FileUpload)表服务实现类
 *
 * @author peanut
 * @since 2024-03-18 15:22:32
 */
@Service("fileUploadService")
@Transactional
public class FileUploadServiceImpl extends MPJBaseServiceImpl<FileUploadMapper, FileUpload> implements FileUploadService {

  final static Cache<String, Map<String, String>> cache = CacheBuilder.newBuilder().maximumSize(100).expireAfterWrite(30, TimeUnit.MINUTES).build();

  public @Override FileUploadQueryListRes queryList(FileUploadQueryListReq req) {

    MPJLambdaWrapper<FileUpload> q = getWrapper(req.getData());
    List<FileUpload> list = this.list(q);

    List<FileUploadDto> dataList = list.stream().map(t -> $.copy(t, FileUploadDto.class)).collect(Collectors.toList());

    return new FileUploadQueryListRes().setDataList(dataList);
  }


  public @Override DynamicsPage<FileUploadExportQueryPageListInfoRes> queryPageList(FileUploadExportQueryPageListReq req) {

    DynamicsPage<FileUpload> page = new DynamicsPage<>();
    page.setCurrent(req.getPageNum()).setSize(req.getPageSize());
    setQueryListHeader(page);
    MPJLambdaWrapper<FileUpload> q = getWrapper(req.getData());
    List<FileUploadExportQueryPageListInfoRes> records;
    if (Boolean.TRUE.equals(req.getQueryPage())) {
      IPage<FileUpload> list = this.page(page, q);
      IPage<FileUploadExportQueryPageListInfoRes> dataList = list.convert(t -> $.copy(t, FileUploadExportQueryPageListInfoRes.class));
      records = dataList.getRecords();
    } else {
      records = $.copyList(this.list(q), FileUploadExportQueryPageListInfoRes.class);
    }

    // 类型转换，  更换枚举 等操作
    List<FileUploadExportQueryPageListInfoRes> listInfoRes = $.copyList(records, FileUploadExportQueryPageListInfoRes.class);

    return DynamicsPage.init(page, listInfoRes);
  }

  // 以下为私有对象封装


  private MPJLambdaWrapper<FileUpload> getWrapper(FileUploadDto obj) {
    MPJLambdaWrapper<FileUpload> q = new MPJLambdaWrapper<>();

    if (Objects.nonNull(obj)) {
      q
          .eq(Objects.nonNull(obj.getId()), FileUpload::getId, obj.getId())
          .eq(StringUtils.isNoneBlank(obj.getFileName()), FileUpload::getFileName, obj.getFileName())
          .eq(Objects.nonNull(obj.getFileSize()), FileUpload::getFileSize, obj.getFileSize())
          .eq(StringUtils.isNoneBlank(obj.getLocalFilePath()), FileUpload::getLocalFilePath, obj.getLocalFilePath())
          .eq(StringUtils.isNoneBlank(obj.getCloudFilePath()), FileUpload::getCloudFilePath, obj.getCloudFilePath())
          .eq(StringUtils.isNoneBlank(obj.getExpireTime()), FileUpload::getExpireTime, obj.getExpireTime())
          .eq(Objects.nonNull(obj.getVersionNum()), FileUpload::getVersionNum, obj.getVersionNum())
          .orderByDesc(FileUpload::getId)
      ;
    }

    return q;

  }

  private void setQueryListHeader(DynamicsPage<FileUpload> page) {
    page
        .addHeader("id", "序号")
        .addHeader("fileName", "文件名")
        .addHeader("fileSize", "文件大小")
        .addHeader("localFilePath", "本地地址")
        .addHeader("cloudFilePath", "云地址")
        .addHeader("expireTime", "过期时间")
        .addHeader("createTime", "创建时间")
        .addHeader("updateTime", "更新时间")
    ;
  }
}

