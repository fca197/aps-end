package com.olivia.peanut.aps.api.entity.workshopSection;

import java.time.LocalDateTime;
import java.util.List;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * 工段信息(WorkshopSection)查询对象返回
 *
 * @author makejava
 * @since 2024-03-11 10:55:22
 */
@Accessors(chain = true)
@Getter
@Setter

public class WorkshopSectionQueryListRes {

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
     *  所属工厂id
     */
    private Long factoryId;
    /***
     *  工段名称
     */
    private String sectionName;
    /***
     *  工段编码
     */
    private String sectionCode;
    /***
     *  工段类型
     */
    private String sectionType;
    /***
     *  工段状态
     */
    private String sectionStatus;
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

  }
}

