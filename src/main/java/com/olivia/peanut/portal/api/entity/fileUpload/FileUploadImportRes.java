package com.olivia.peanut.portal.api.entity.fileUpload;

import java.util.List;

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
public class FileUploadImportRes {

  /****
   * 写入行数
   */
  private int count;
  /**
   * 错误信息
   */
  private List<String> errorMsg;
}

