package com.olivia.peanut.base.model;


import com.baomidou.mybatisplus.annotation.TableName;
import com.olivia.sdk.utils.BaseEntity;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * 租户信息(TenantInfo)表实体类
 *
 * @author makejava
 * @since 2024-03-11 10:55:21
 */
@Accessors(chain = true)
@Getter
@Setter
//
@TableName("t_tenant_info")
public class TenantInfo extends BaseEntity {


  /***
   *  租户名称
   */
  private String tenantName;
  /***
   *  租户编码
   */
  private String tenantCode;


}

