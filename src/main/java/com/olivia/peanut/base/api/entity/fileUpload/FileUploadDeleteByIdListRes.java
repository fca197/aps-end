package com.olivia.peanut.base.api.entity.fileUpload;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * (FileUpload)根据ID删除多个反参
 *
 * @author peanut
 * @since 2024-03-18 15:22:31
 */
@Accessors(chain = true)
@Getter
@Setter
@SuppressWarnings("serial")
public class FileUploadDeleteByIdListRes {

  /***
   * 受影响行数
   */
  private int count;

}

