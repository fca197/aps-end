package com.olivia.peanut.aps.model;


import com.baomidou.mybatisplus.annotation.TableName;
import com.olivia.sdk.utils.BaseEntity;
import java.time.LocalDateTime;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * 工段信息(WorkshopSection)表实体类
 *
 * @author makejava
 * @since 2024-03-11 10:55:22
 */
@Accessors(chain = true)
@Getter
@Setter
//
@TableName("aps_workshop_section")
public class ApsWorkshopSection extends BaseEntity {

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

