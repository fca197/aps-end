package com.olivia.peanut.aps.model;


import com.baomidou.mybatisplus.annotation.TableName;
import com.olivia.sdk.utils.BaseEntity;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * (ApsOrderUser)表实体类
 *
 * @author peanut
 * @since 2024-04-09 13:19:39
 */
@Accessors(chain = true)
@Getter
@Setter
//@SuppressWarnings("serial")
@TableName("aps_order_user")
public class ApsOrderUser extends BaseEntity {

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

