package com.olivia.peanut.base.api.impl;


import com.aliyun.dingtalkoauth2_1_0.Client;
import com.aliyun.dingtalkoauth2_1_0.models.GetSsoUserInfoHeaders;
import com.aliyun.dingtalkoauth2_1_0.models.GetSsoUserInfoRequest;
import com.aliyun.dingtalkoauth2_1_0.models.GetSsoUserInfoResponse;
import com.aliyun.teaopenapi.models.Config;
import com.aliyun.teautil.models.RuntimeOptions;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.IdWorker;
import com.olivia.peanut.base.api.DingApi;
import com.olivia.peanut.base.api.entity.ding.CodeLoginReq;
import com.olivia.peanut.base.api.entity.ding.CodeLoginRes;
import com.olivia.peanut.base.model.LoginAccount;
import com.olivia.peanut.base.service.LoginAccountService;
import com.olivia.sdk.comment.DingConfigComment;
import com.olivia.sdk.config.PeanutProperties;
import com.olivia.sdk.config.entity.DingConfig;
import com.olivia.sdk.utils.$;
import com.olivia.sdk.utils.JSON;
import com.olivia.sdk.utils.Str;
import jakarta.annotation.Resource;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.TimeUnit;

@Slf4j
@RestController
public class DingApiImpl implements DingApi {

  @Resource
  PeanutProperties peanutProperties;

  @Resource
  DingConfigComment dingConfigComment;
  @Resource
  LoginAccountService loginAccountService;

  @Resource
  StringRedisTemplate stringRedisTemplate;

  @Override
  @SneakyThrows
  public CodeLoginRes codeLogin(CodeLoginReq req) {
    DingConfig dingConfig = peanutProperties.getDingConfig(req.getCorpId());
    String accessToken = dingConfigComment.getAccessToken(dingConfig);

    Config config = new Config();
    config.protocol = "https";
    config.regionId = "central";
    Client client = new Client(config);

    GetSsoUserInfoHeaders getSsoUserInfoHeaders = new GetSsoUserInfoHeaders();
    getSsoUserInfoHeaders.xAcsDingtalkAccessToken = accessToken;
    GetSsoUserInfoRequest dingReq = new GetSsoUserInfoRequest().setCode(req.getCode());
    GetSsoUserInfoResponse ssoUserInfo = client.getSsoUserInfoWithOptions(dingReq, getSsoUserInfoHeaders, new RuntimeOptions());
    String userId = ssoUserInfo.getBody().getUserId();
    LoginAccount loginAccount = this.loginAccountService.getOne(new QueryWrapper<LoginAccount>().eq(dingConfig.getUserIdColumnName(), userId).last(Str.LIMIT_1));
    $.requireNonNullCanIgnoreException(loginAccount, "用户不在门店档案中");
    loginAccount.setUserPwd(null);
    String token = String.join("_", IdWorker.get32UUID().toUpperCase());
    String str = JSON.toJSONString(loginAccount);
    String key = peanutProperties.getRedisKey().getUserToken() + token;
    log.info("ding login token: {} loginAccount: {}", key, str);
    stringRedisTemplate.opsForValue().set(key, str, 10, TimeUnit.DAYS);
    return new CodeLoginRes().setToken(token);

  }
}
