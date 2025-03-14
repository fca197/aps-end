package com.olivia.peanut.base.api;

import com.olivia.peanut.base.api.entity.account.*;
import com.olivia.sdk.utils.DynamicsPage;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

/***
 *
 */
public interface LoginAccountApi {


  @RequestMapping("/loginAccount/login/phone/pwd")
  LoginPhonePwdRes loginPhonePwd(@RequestBody @Validated LoginPhonePwdReq req);

  @RequestMapping("/loginAccount/getInfo")
  GetUserInfoRes getUserInfo(@RequestBody GetUserInfoReq req);

  @RequestMapping("/loginAccount/queryPageList")
  DynamicsPage<LoginAccountDto> queryPageList(@RequestBody QueryPageListReq req);

  @PostMapping("/loginAccount/resetPwd")
  ResetPwdRes resetPwd(@RequestBody ResetPwdReq req);

  @PostMapping("/loginAccount/insert")
  InsertRes insert(@RequestBody InsertReq req);

  @PostMapping("/loginAccount/updateRole")
  UpdateRoleRes updateRole(@RequestBody UpdateRoleReq req);

  // updateDept
  @PostMapping("/loginAccount/updateDept")
  UpdateDeptRes updateDept(@RequestBody UpdateDeptReq req);
}
