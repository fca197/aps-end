package com.olivia.peanut.portal.api.entity.factory;

import java.time.LocalTime;
import java.util.List;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * 工段信息(Factory)查询对象返回
 *
 * @author makejava
 * @since 2024-03-11 10:44:05
 */
@Accessors(chain = true)
@Getter
@Setter

public class FactoryQueryByIdListRes {

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
     *  所属租户id
     */
    private Long tenantId;
    /***
     *  工厂名称
     */
    private String factoryName;
    /***
     *  工厂编码
     */
    private String factoryCode;
    /***
     *  工厂状态
     */
    private String factoryStatus;
    /***
     *
     */

    /***
     *  创建时间
     */
    private LocalTime createTime;
    /***
     *  创建人id
     */
    private Long createBy;
    /***
     *  更新时间
     */
    private LocalTime updateTime;
    /***
     *  更新人id
     */
    private Long updateBy;
    /***
     *  链路追踪ID
     */
    private String traceId;

  }
}

