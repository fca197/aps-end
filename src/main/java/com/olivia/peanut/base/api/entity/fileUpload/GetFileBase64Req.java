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
public class GetFileBase64Req {

  @NotNull(message = "文件不能为空")
  private Long id;
}
