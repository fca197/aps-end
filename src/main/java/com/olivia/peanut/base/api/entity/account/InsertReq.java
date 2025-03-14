package com.olivia.peanut.base.api.entity.account;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.hibernate.validator.constraints.Length;

/***
 *
 */
@Setter
@Getter
@Accessors(chain = true)
public class InsertReq {

  @NotBlank(message = "用户名不能为空")
  @Length(min = 1, max = 50, message = "用户名长度必须在{min}-{max}之间")
  private String userName;
  @NotBlank(message = "密码不能为空")
  @Pattern(regexp = "^1((34[0-8])|(8\\d{2})|(([35][0-35-9]|4[579]|66|7[35678]|9[1389])\\d{1}))\\d{7}$")
  private String loginPhone;
  @NotBlank(message = "密码不能为空")
  private String pwd;
}
