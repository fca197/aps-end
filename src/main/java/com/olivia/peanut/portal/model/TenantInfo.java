package com.olivia.peanut.portal.model;


import com.baomidou.mybatisplus.annotation.TableName;
import com.olivia.sdk.utils.BaseEntity;
import java.time.LocalDateTime;
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
   *  id
   */
  private Long id;
  /***
   *  租户名称
   */
  private String tenantName;
  /***
   *  租户编码
   */
  private String tenantCode;


  /***
   *  创建时间
   */
  private LocalDateTime createTime;
  /***
   *  创建人id
   */
  private Long createBy;
  /***
   *  更新时间
   */
  private LocalDateTime updateTime;
  /***
   *  更新人id
   */
  private Long updateBy;
  /***
   *  链路追踪ID
   */
  private String traceId;
  private Long tenantId;

}

