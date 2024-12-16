package com.olivia.peanut.base.service.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.util.List;

@Setter
@Getter
@Accessors(chain = true)
public class SendMailReq {
  private String to;
  private String subject;
  private String content;
  private List<SendMailAttFileReq> mailAttReqList;
}
