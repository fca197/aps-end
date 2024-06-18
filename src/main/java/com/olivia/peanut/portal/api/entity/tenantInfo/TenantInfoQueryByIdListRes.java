package com.olivia.peanut.portal.api.entity.tenantInfo;

import java.time.LocalDateTime;
import java.util.List;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * 租户信息(TenantInfo)查询对象返回
 *
 * @author makejava
 * @since 2024-03-11 10:55:22
 */
@Accessors(chain = true)
@Getter
@Setter

public class TenantInfoQueryByIdListRes {

  /***
   * 返回对象列表
   */
  private List<Info> dataList;


  @Getter
  @Setter
  public static class Info {

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
     *
     */

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
}

