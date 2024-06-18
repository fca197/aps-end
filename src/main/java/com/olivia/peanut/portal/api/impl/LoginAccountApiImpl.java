package com.olivia.peanut.portal.api.impl;

import static java.lang.Boolean.TRUE;

import cn.hutool.core.util.RandomUtil;
import cn.hutool.crypto.digest.MD5;
import com.alibaba.fastjson2.JSON;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.IdWorker;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.olivia.peanut.portal.api.LoginAccountApi;
import com.olivia.peanut.portal.api.entity.login.account.*;
import com.olivia.peanut.portal.model.LoginAccount;
import com.olivia.peanut.portal.service.LoginAccountService;
import com.olivia.sdk.ann.Timed;
import com.olivia.sdk.config.PeanutProperties;
import com.olivia.sdk.filter.LoginUserContext;
import com.olivia.sdk.utils.$;
import com.olivia.sdk.utils.BaseEntity;
import com.olivia.sdk.utils.DynamicsPage;
import jakarta.annotation.Resource;
import java.util.Objects;
import java.util.concurrent.TimeUnit;
import lombok.extern.slf4j.Slf4j;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.RestController;

/***
 *
 */
@Slf4j
@RestController
public class LoginAccountApiImpl implements LoginAccountApi {

  @Resource
  PeanutProperties peanutProperties;

  @Resource
  StringRedisTemplate stringRedisTemplate;
  @Resource
  private LoginAccountService loginAccountService;
  @Resource
  private RedissonClient redissonClient;

  @Override
  @Timed
  public LoginPhonePwdRes loginPhonePwd(LoginPhonePwdReq req) {

    LoginUserContext.ignoreTenantId(TRUE);
    RLock lock = redissonClient.getLock(peanutProperties.getRedisKey().getLoginLock() + req.getLoginPhone());

    lock.lock(3, TimeUnit.SECONDS);
    LoginAccount loginAccount = this.loginAccountService.getOne(new LambdaQueryWrapper<LoginAccount>()
        .eq(LoginAccount::getLoginPhone, req.getLoginPhone()).eq(LoginAccount::getUserPwd, req.getPwd()), false);
    $.requireNonNullCanIgnoreException(loginAccount, "用户名密码错误");
    loginAccount.setUserPwd(null);
    String token = String.join("_", IdWorker.get32UUID().toUpperCase());
    String str = JSON.toJSONString(loginAccount);
    String key = peanutProperties.getRedisKey().getUserToken() + token;
    log.info("loginPhonePwd ,loginPhone: {} token: {} loginAccount: {}", req.getLoginPhone(), key, str);
    stringRedisTemplate.opsForValue().set(key, str, 10, TimeUnit.DAYS);
    return new LoginPhonePwdRes().setToken(token)
//                .setLoginAccountList(Arrays.asList(loginAccount, loginAccount, loginAccount))
//                .setMap(Map.of("zzz", "aaa", "xxx", "xaa")).setBigDecimal(new BigDecimal("3232.23312345678909876543234567898765432"))
        ;
  }

  @Override
  public GetUserInfoRes getUserInfo(GetUserInfoReq req, String token) {

    LoginUserContext.ignoreTenantId();
    String key = peanutProperties.getRedisKey().getUserToken() + token;
    String str = stringRedisTemplate.opsForValue().get(key);
    $.requireNonNullCanIgnoreException(str, "登录已过期");
    return JSON.parseObject(str, GetUserInfoRes.class);
  }

  @Override
  public DynamicsPage<LoginAccount> queryPageList(QueryPageListReq req) {

    LoginUserContext.ignoreTenantId();
    LambdaQueryWrapper<LoginAccount> query = Wrappers.lambdaQuery();
    LoginAccount data = req.getData();
    if (Objects.nonNull(data)) {
      query.eq(Objects.nonNull(data.getId()), BaseEntity::getId, data.getId());
      query.eq(StringUtils.isNotBlank(data.getLoginPhone()), LoginAccount::getLoginPhone, data.getLoginPhone());
      query.likeRight(StringUtils.isNotBlank(data.getUserName()), LoginAccount::getUserName, data.getUserName());
    }
    Page<LoginAccount> page = this.loginAccountService.page(new Page<>(req.getPageNum(), req.getPageSize()), query.orderByDesc(BaseEntity::getId));
    return DynamicsPage.init(page);
  }

  @Override
  public ResetPwdRes resetPwd(ResetPwdReq req) {

    LoginUserContext.ignoreTenantId();
    LoginAccount account = this.loginAccountService.getById(req.getId());
    $.requireNonNullCanIgnoreException(account, "用户不存在");
    String newPwd = RandomUtil.randomString(10);
    String pwd = MD5.create().digestHex(newPwd).toUpperCase();
    this.loginAccountService.update(Wrappers.<LoginAccount>lambdaUpdate().eq(BaseEntity::getId, req.getId()).set(LoginAccount::getUserPwd, pwd));
    return new ResetPwdRes().setNewPwd(newPwd);
  }

  @Override
  public InsertRes insert(InsertReq req) {
    LoginUserContext.ignoreTenantId();
    LoginAccount copy = $.copy(req, LoginAccount.class);
    copy.setId(IdWorker.getId());
    this.loginAccountService.save(copy);
    return new InsertRes().setId(copy.getId());
  }
}
