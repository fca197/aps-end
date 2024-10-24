package com.olivia.peanut.portal.api.impl;

import static java.lang.Boolean.TRUE;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.RandomUtil;
import cn.hutool.crypto.digest.MD5;
import com.alibaba.fastjson2.JSON;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.IdWorker;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.olivia.peanut.base.model.*;
import com.olivia.peanut.base.service.*;
import com.olivia.peanut.portal.api.LoginAccountApi;
import com.olivia.peanut.portal.api.entity.BaseEntityDto;
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

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

import lombok.extern.slf4j.Slf4j;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.transaction.annotation.Transactional;
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
  BaseUserRoleService baseUserRoleService;
  @Resource
  BaseUserRoleGroupService baseUserRoleGroupService;
  @Resource
  BaseRoleService baseRoleService;
  @Resource
  BaseRoleGroupService baseRoleGroupService;
  @Resource
  BaseUserDeptService baseUserDeptService;
  @Resource
  BaseDeptService deptService;
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
    LoginAccount loginAccount = this.loginAccountService.getOne(
        new LambdaQueryWrapper<LoginAccount>().eq(LoginAccount::getLoginPhone, req.getLoginPhone()).eq(LoginAccount::getUserPwd, req.getPwd()), false);
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
  public DynamicsPage<LoginAccountDto> queryPageList(QueryPageListReq req) {

    LoginUserContext.ignoreTenantId();
    LambdaQueryWrapper<LoginAccount> query = Wrappers.lambdaQuery();
    LoginAccount data = req.getData();
    if (Objects.nonNull(data)) {
      query.eq(Objects.nonNull(data.getId()), BaseEntity::getId, data.getId());
      query.eq(StringUtils.isNotBlank(data.getLoginPhone()), LoginAccount::getLoginPhone, data.getLoginPhone());
      query.likeRight(StringUtils.isNotBlank(data.getUserName()), LoginAccount::getUserName, data.getUserName());
    }
    Page<LoginAccount> page = this.loginAccountService.page(new Page<>(req.getPageNum(), req.getPageSize()), query.orderByDesc(BaseEntity::getId));

    List<LoginAccountDto> result = $.copyList(page.getRecords(), LoginAccountDto.class);
    if (CollUtil.isNotEmpty(result)) {
      Map<Long, String> gnMap = this.baseRoleGroupService.list().stream().collect(Collectors.toMap(BaseEntity::getId, BaseRoleGroup::getRoleGroupName));
      Map<Long, String> rnMap = this.baseRoleService.list().stream().collect(Collectors.toMap(BaseEntity::getId, BaseRole::getRoleName));
      Map<Long, String> dnMap = deptService.list().stream().collect(Collectors.toMap(BaseEntity::getId, BaseDept::getDeptName));
      List<Long> userIdList = result.stream().map(BaseEntityDto::getId).toList();
      Map<Long, List<Long>> userDeptMap = this.baseUserDeptService.list(new LambdaQueryWrapper<BaseUserDept>().in(BaseUserDept::getUserId, userIdList)).stream().collect(
          Collectors.groupingBy(BaseUserDept::getUserId,
              Collectors.collectingAndThen(Collectors.<BaseUserDept>toList(),
                  list -> list.stream().map(BaseUserDept::getDeptId).toList())
          )
      );

      List<BaseUserRoleGroup> userRoleGroupList = baseUserRoleGroupService.list(new LambdaQueryWrapper<BaseUserRoleGroup>().in(BaseUserRoleGroup::getUserId, userIdList));
      Map<Long, List<Long>> userRoleGroupIdMap = userRoleGroupList.stream().collect(Collectors.groupingBy(BaseUserRoleGroup::getUserId,
          Collectors.collectingAndThen(Collectors.<BaseUserRoleGroup>toList(), list -> list.stream().map(BaseUserRoleGroup::getRoleGroupId).collect(Collectors.toList()))));

      Map<Long, List<Long>> userRoleMap = this.baseUserRoleService.list(new LambdaQueryWrapper<BaseUserRole>().in(BaseUserRole::getUserId, userIdList))
          .stream().collect(
              Collectors.groupingBy(BaseUserRole::getUserId,
                  Collectors.collectingAndThen(Collectors.<BaseUserRole>toList(),
                      list -> list.stream().map(BaseUserRole::getRoleId).toList())));

      result.forEach(t -> {
        List<Long> userGroupIdList = userRoleGroupIdMap.getOrDefault(t.getId(), List.of());
        t.setBaseRoleGroupName(userGroupIdList.stream().map(gnMap::get).distinct().sorted().collect(Collectors.joining(",")));
        t.setBaseRoleGroupIds(userGroupIdList);
        List<Long> userRoleIdList = userRoleMap.getOrDefault(t.getId(), List.of());
        t.setBaseRoleName(userRoleIdList.stream().map(rnMap::get).filter(StringUtils::isNotBlank).distinct().sorted().collect(Collectors.joining(",")));
        t.setBaseRoleIds(userRoleIdList);
        List<Long> deptIds = userDeptMap.getOrDefault(t.getId(), List.of());
        t.setDeptIds(deptIds).setDeptName(deptIds.stream().map(dnMap::get).filter(StringUtils::isNotBlank).distinct().sorted().collect(Collectors.joining(",")));
      });
    }
    DynamicsPage<LoginAccountDto> ret = new DynamicsPage<>();
    ret.setTotal(page.getTotal()).setRecords(result);
    return ret;
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

  @Override
  @Transactional
  public UpdateRoleRes updateRole(UpdateRoleReq req) {
    this.baseUserRoleService.remove(new LambdaQueryWrapper<BaseUserRole>().eq(BaseUserRole::getUserId, req.getUserId()));
    this.baseUserRoleGroupService.remove(new LambdaQueryWrapper<BaseUserRoleGroup>().eq(BaseUserRoleGroup::getUserId, req.getUserId()));
    if (CollUtil.isNotEmpty(req.getRoleGroupIds())) {
      this.baseUserRoleGroupService.saveBatch(req.getRoleGroupIds().stream().map(t -> new BaseUserRoleGroup().setUserId(req.getUserId()).setRoleGroupId(t)).toList());
    }
    if (CollUtil.isNotEmpty(req.getRoleIds())) {
      this.baseUserRoleService.saveBatch(req.getRoleIds().stream().map(t -> new BaseUserRole().setUserId(req.getUserId()).setRoleId(t)).toList());
    }

    return new UpdateRoleRes();
  }

  @Override
  public UpdateDeptRes updateDept(UpdateDeptReq req) {
    this.baseUserDeptService.remove(new LambdaQueryWrapper<BaseUserDept>().eq(BaseUserDept::getUserId, req.getUserId()));
    if (CollUtil.isNotEmpty(req.getDeptList())) {
      this.baseUserDeptService.saveBatch(req.getDeptList().stream().map(t -> new BaseUserDept().setUserId(req.getUserId()).setDeptId(t)).toList());
    }
    return new UpdateDeptRes();
  }
}
