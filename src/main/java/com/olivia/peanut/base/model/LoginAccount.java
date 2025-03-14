package com.olivia.peanut.base.model;

import com.baomidou.mybatisplus.annotation.TableName;
import com.olivia.sdk.utils.BaseEntity;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/***
 *
 */
@Setter
@Getter
@Accessors(chain = true)
@TableName("t_login_account")
public class LoginAccount extends BaseEntity {

  private String userName;
  private String loginPhone;
  private String userPwd;

  private Boolean isAdmin;

  public boolean isAdmin() {
    return Boolean.TRUE.equals(isAdmin);
  }
}
