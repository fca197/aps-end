package com.olivia.peanut.aps.api.entity.workshopStation;


import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 工位信息(WorkshopStation)表实体类
 *
 * @author peanut
 * @since 2023-12-28 17:29:07
 */
@Data
@Accessors(chain = true)
public class WorkshopStationUpdateIdReq {

  @NotNull(message = "修改信息不能为空")
  private Long id;
  /**
   * 所属租户id
   **/
  private Long tenantId;
  /**
   * 所属工厂id
   **/
  private Long factoryId;
  /**
   * 所属工段id
   **/
  private Long sectionId;
  /**
   * 工位名称
   **/
  private String stationName;
  /**
   * 工位编码
   **/
  private String stationCode;
  /**
   * 工位类型
   **/
  private String stationType;
  /**
   * 工位状态
   **/
  private String stationStatus;
}


