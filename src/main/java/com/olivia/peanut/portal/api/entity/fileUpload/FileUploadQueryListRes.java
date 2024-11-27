package com.olivia.peanut.portal.api.entity.fileUpload;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.util.List;

/**
 * (FileUpload)查询对象返回
 *
 * @author peanut
 * @since 2024-03-18 15:22:31
 */
@Accessors(chain = true)
@Getter
@Setter
@SuppressWarnings("serial")
public class FileUploadQueryListRes {

  /***
   * 返回对象列表
   */
  private List<FileUploadDto> dataList;


}

