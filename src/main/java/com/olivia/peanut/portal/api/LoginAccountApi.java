package com.olivia.peanut.portal.api;

import com.olivia.peanut.portal.api.entity.login.account.*;
import com.olivia.peanut.portal.model.LoginAccount;
import com.olivia.sdk.utils.DynamicsPage;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;

/***
 *
 */
public interface LoginAccountApi {


  @RequestMapping("/loginAccount/login/phone/pwd")
  LoginPhonePwdRes loginPhonePwd(@RequestBody @Validated LoginPhonePwdReq req);

  @RequestMapping("/loginAccount/getInfo")
  GetUserInfoRes getUserInfo(@RequestBody GetUserInfoReq req, @RequestHeader("j-token") String token);

  @RequestMapping("/loginAccount/queryPageList")
  DynamicsPage<LoginAccountDto> queryPageList(@RequestBody QueryPageListReq req);

  @PostMapping("/loginAccount/resetPwd")
  ResetPwdRes resetPwd(@RequestBody ResetPwdReq req);

  @PostMapping("/loginAccount/insert")
  InsertRes insert(@RequestBody InsertReq req);
}
