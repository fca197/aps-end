package com.olivia.peanut.aps.model;


import com.baomidou.mybatisplus.annotation.TableName;
import com.olivia.sdk.utils.BaseEntity;
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
   *  工段名称
   */
  private String sectionName;
  /***
   *  工段编码
   */
  private String sectionCode;


}

