package com.olivia.peanut.portal.api.entity.fileUpload;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/***
 *
 */
@Setter
@Getter
@Accessors(chain = true)
public class GetFileBase64Res {

  private String base64;
}
