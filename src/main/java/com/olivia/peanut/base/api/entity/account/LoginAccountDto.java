package com.olivia.peanut.base.api.entity.account;


import com.olivia.peanut.portal.api.entity.BaseEntityDto;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.util.List;

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
  private String deptName;

  private List<Long> deptIds;
  ;
}
