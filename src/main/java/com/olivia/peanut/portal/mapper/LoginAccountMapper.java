package com.olivia.peanut.portal.mapper;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.olivia.peanut.portal.model.LoginAccount;
import com.olivia.sdk.ann.MethodExt;
import org.apache.ibatis.annotations.Param;

public interface LoginAccountMapper extends BaseMapper<LoginAccount> {

  @MethodExt
  <P extends IPage<LoginAccount>> P selectPage(P page, @Param(Constants.WRAPPER) Wrapper<LoginAccount> queryWrapper);
}
