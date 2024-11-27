package com.olivia.peanut.portal.api.entity.fileUpload;

import jakarta.validation.constraints.NotEmpty;

import java.util.List;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * (FileUpload)根据ID删除多个入参
 *
 * @author peanut
 * @since 2024-03-18 15:22:31
 */
@Accessors(chain = true)
@Getter
@Setter
@SuppressWarnings("serial")
public class FileUploadDeleteByIdListReq {

  /***
   * 要删除的ID
   */
  @NotEmpty(message = "请选择删除对象")
  private List<Long> idList;


}

