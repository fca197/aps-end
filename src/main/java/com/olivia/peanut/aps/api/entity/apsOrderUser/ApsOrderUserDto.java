package com.olivia.peanut.aps.api.entity.apsOrderUser;

import com.olivia.peanut.portal.api.entity.BaseEntityDto;
import lombok.Getter;
import lombok.Setter;

/**
 * (ApsOrderUser)查询对象返回
 *
 * @author peanut
 * @since 2024-04-09 13:19:39
 */
//@Accessors(chain=true)
@Getter
@Setter
@SuppressWarnings("serial")
public class ApsOrderUserDto extends BaseEntityDto {

  private Long orderId;
  private String userName;
  private String userPhone;
  private Integer userSex;
  private String countryCode;
  private String provinceCode;
  private String cityCode;
  private String areaCode;
  private String userAddress;
  private String userRemark;
  private Long factoryId;

}


