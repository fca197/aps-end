package com.olivia.peanut.base.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.olivia.peanut.base.mapper.LoginAccountMapper;
import com.olivia.peanut.base.model.LoginAccount;
import com.olivia.peanut.base.service.LoginAccountService;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.springframework.stereotype.Service;

/***
 *
 */
@Setter
@Getter
@Accessors(chain = true)
@Service
public class LoginAccountServiceImpl extends ServiceImpl<LoginAccountMapper, LoginAccount> implements LoginAccountService {

}
