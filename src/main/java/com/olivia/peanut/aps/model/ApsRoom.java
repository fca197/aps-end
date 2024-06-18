package com.olivia.peanut.aps.model;


import com.baomidou.mybatisplus.annotation.TableName;
import com.olivia.sdk.utils.BaseEntity;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * (ApsRoom)表实体类
 *
 * @author peanut
 * @since 2024-04-01 15:27:29
 */
@Accessors(chain = true)
@Getter
@Setter
//@SuppressWarnings("serial")
@TableName("aps_room")
public class ApsRoom extends BaseEntity {

  private String roomCode;
  private String roomName;
  private Long factoryId;

}

