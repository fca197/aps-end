package com.olivia.peanut.base.service;

import com.olivia.peanut.base.service.entity.SendMailReq;

import java.util.List;

public interface MailService {

  void sendMail(List<SendMailReq> reqArr);

  default void sendMail(SendMailReq... reqArr) {
    sendMail(List.of(reqArr));
  }
}
