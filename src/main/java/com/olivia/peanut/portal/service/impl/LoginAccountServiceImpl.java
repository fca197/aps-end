package com.olivia.peanut.portal.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.olivia.peanut.portal.mapper.LoginAccountMapper;
import com.olivia.peanut.portal.model.LoginAccount;
import com.olivia.peanut.portal.service.LoginAccountService;
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
