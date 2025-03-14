package com.olivia.peanut.base.api.entity.fileUpload;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * (FileUpload)查询对象入参
 *
 * @author peanut
 * @since 2024-03-18 15:22:31
 */
@Accessors(chain = true)
@Getter
@Setter
@SuppressWarnings("serial")
public class FileUploadExportQueryPageListReq {

  private int pageNum;
  private int pageSize;
  private Boolean queryPage = true;
  private FileUploadDto data;


}

