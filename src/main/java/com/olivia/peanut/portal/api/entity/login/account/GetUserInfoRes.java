package com.olivia.peanut.portal.api.entity.login.account;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/***
 *
 */
@Setter
@Getter
@Accessors(chain = true)
public class GetUserInfoRes {

  private Long id;
  private String userName;
  private Long tenantId = 1001L;
  private String loginPhone;
}
