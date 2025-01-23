package com.olivia.peanut.aps.model;


import com.baomidou.mybatisplus.annotation.TableName;
import com.olivia.sdk.utils.BaseEntity;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * 工位信息(WorkshopStation)表实体类
 *
 * @author makejava
 * @since 2024-03-11 10:55:23
 */
@Accessors(chain = true)
@Getter
@Setter
//
@TableName("aps_workshop_station")
public class ApsWorkshopStation extends BaseEntity {
  /***
   *  工位名称
   */
  private String stationName;
  /***
   *  工位编码
   */
  private String stationCode;


}

