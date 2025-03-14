package com.olivia.peanut.base.api.entity.ding;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Setter
@Getter
@Accessors(chain = true)
public class GetQrRes {

  /**
   * 二维码
   */
  private String qrCode;
  /***
   * 过期时间
   */
  private Long expireTime;
}
