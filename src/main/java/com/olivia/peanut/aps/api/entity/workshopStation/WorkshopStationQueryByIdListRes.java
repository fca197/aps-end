package com.olivia.peanut.aps.api.entity.workshopStation;

import java.time.LocalDateTime;
import java.util.List;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * 工位信息(WorkshopStation)查询对象返回
 *
 * @author makejava
 * @since 2024-03-11 10:55:24
 */
@Accessors(chain = true)
@Getter
@Setter

public class WorkshopStationQueryByIdListRes {

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
     *  所属工段id
     */
    private Long sectionId;
    /***
     *  工位名称
     */
    private String stationName;
    /***
     *  工位编码
     */
    private String stationCode;
    /***
     *  工位类型
     */
    private String stationType;
    /***
     *  工位状态
     */
    private String stationStatus;
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

