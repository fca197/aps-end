package com.olivia.peanut.portal.api.impl.listener;

import com.olivia.peanut.portal.api.RandomApi;
import com.olivia.sdk.utils.RandomUserUtil;
import com.olivia.sdk.utils.model.UserInfo;
import org.springframework.web.bind.annotation.RestController;

/***
 *
 */
@RestController
public class RandomApiImpl implements RandomApi {

  @Override
  public UserInfo getRandomUser() {
    return RandomUserUtil.getRandomUser();
  }
}
