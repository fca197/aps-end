package com.olivia.peanut.portal.api.entity.login.account;

import com.olivia.peanut.portal.api.entity.BaseEntityDto;
import java.util.List;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/***
 *
 */
@Setter
@Getter
@Accessors(chain = true)
public class LoginAccountDto extends BaseEntityDto {

  private String userName;
  private Long tenantId;
  private String loginPhone;

  private String baseRoleName;
  private List<Long> baseRoleGroupIds;

  private String baseRoleGroupName;
  private List<Long> baseRoleIds;
}
