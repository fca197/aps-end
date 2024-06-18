package com.olivia.peanut.aps.model;


import com.baomidou.mybatisplus.annotation.TableName;
import com.olivia.sdk.utils.BaseEntity;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * (ApsRoomConfig)表实体类
 *
 * @author peanut
 * @since 2024-04-01 15:27:30
 */
@Accessors(chain = true)
@Getter
@Setter
//@SuppressWarnings("serial")
@TableName("aps_room_config")
public class ApsRoomConfig extends BaseEntity {

  private Long roomId;
  private Long sectionId;
  private Long stationId;
  private Long statusId;
  private Integer executeTime;
  private Long factoryId;

}

