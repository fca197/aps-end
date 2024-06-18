package com.olivia.peanut.portal.api.entity.login.account;

import com.olivia.peanut.portal.model.LoginAccount;
import com.olivia.sdk.controller.entity.PageQuery;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/***
 *
 */
@Setter
@Getter
@Accessors(chain = true)
public class QueryPageListReq extends PageQuery<LoginAccount> {

}
