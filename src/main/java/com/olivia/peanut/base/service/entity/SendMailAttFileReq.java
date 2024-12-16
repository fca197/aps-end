package com.olivia.peanut.base.service.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Setter
@Getter
@Accessors(chain = true)
public class SendMailAttFileReq {
  private String fileName;
  private String filePath;
}
