package com.olivia.peanut.aps.api.entity.workshopSection;


import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 工段信息(WorkshopSection)表实体类
 *
 * @author peanut
 * @since 2023-12-28 17:29:06
 */
@Data
@Accessors(chain = true)
public class WorkshopSectionUpdateIdReq {

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
   * 工段名称
   **/
  private String sectionName;
  /**
   * 工段编码
   **/
  private String sectionCode;
  /**
   * 工段类型
   **/
  private String sectionType;
  /**
   * 工段状态
   **/
  private String sectionStatus;
}


