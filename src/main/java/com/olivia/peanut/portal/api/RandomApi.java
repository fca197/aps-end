package com.olivia.peanut.portal.api;

import com.olivia.sdk.utils.model.UserInfo;
import org.springframework.web.bind.annotation.PostMapping;

/***
 *
 */
public interface RandomApi {

  @PostMapping("/random/getRandomUser")
  UserInfo getRandomUser();
}
