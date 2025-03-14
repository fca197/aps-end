package com.olivia.peanut.base.api.entity.fileUpload;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * (FileUpload)保存返回
 *
 * @author peanut
 * @since 2024-03-18 15:22:31
 */
@Accessors(chain = true)
@Getter
@Setter
@SuppressWarnings("serial")
public class FileUploadInsertRes {

  /****
   * 写入行数
   */
  private int count;

  private FileUploadDto data;
}

