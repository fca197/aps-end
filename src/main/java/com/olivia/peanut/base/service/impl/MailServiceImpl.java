package com.olivia.peanut.base.service.impl;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.extra.spring.SpringUtil;
import com.olivia.peanut.base.service.MailService;
import com.olivia.peanut.base.service.entity.SendMailAttFileReq;
import com.olivia.peanut.base.service.entity.SendMailReq;
import com.olivia.sdk.utils.RunUtils;
import jakarta.annotation.Resource;
import jakarta.mail.internet.MimeMessage;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.mail.MailProperties;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.List;
import java.util.Objects;

@Slf4j
@Service
public class MailServiceImpl implements MailService {

  @Resource
  MailProperties mailProperties;

  @Override
  public void sendMail(List<SendMailReq> reqArr) {
    if (reqArr != null) {
      for (SendMailReq req : reqArr) {
        RunUtils.run("sendMail->" + req.getTo(), () -> sendMailMain(req));
      }
    }
  }

  @SneakyThrows
  private void sendMailMain(SendMailReq req) {

    JavaMailSenderImpl javaMailSender = SpringUtil.getBean(JavaMailSenderImpl.class);
    if (Objects.isNull(javaMailSender)) {
      log.info("sendMailMain javaMailSender is null break");
      return;
    }
    MimeMessage mimeMessage = javaMailSender.createMimeMessage();
    MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true);
    mimeMessageHelper.setSubject(req.getSubject());
    mimeMessageHelper.setTo(req.getTo());
    mimeMessageHelper.setText(req.getContent(), true);
    mimeMessageHelper.setFrom(mailProperties.getUsername());
    if (CollUtil.isNotEmpty(req.getMailAttReqList())) {
      for (SendMailAttFileReq mailAttReq : req.getMailAttReqList()) {
        mimeMessageHelper.addAttachment(mailAttReq.getFileName(), new File(mailAttReq.getFilePath()));
      }
    }
    javaMailSender.send(mimeMessage);
    log.info("sendMailMain {} send end", req.getTo());
  }
}
