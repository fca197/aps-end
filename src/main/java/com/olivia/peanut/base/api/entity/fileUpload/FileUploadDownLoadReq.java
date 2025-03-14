package com.olivia.peanut.base.api.entity.fileUpload;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/***
 *
 */
@Setter
@Getter
@Accessors(chain = true)
public class FileUploadDownLoadReq {

  @NotNull(message = "下载数据不能为空")
  private Long id;
}
