package com.olivia.peanut.aps.model;


import com.baomidou.mybatisplus.annotation.TableName;
import com.olivia.sdk.utils.BaseEntity;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * (ApsProcessPathRoom)表实体类
 *
 * @author peanut
 * @since 2024-04-01 17:49:19
 */
@Accessors(chain = true)
@Getter
@Setter
//@SuppressWarnings("serial")
@TableName("aps_process_path_room")
public class ApsProcessPathRoom extends BaseEntity {

  private Long processPathId;
  private Long roomId;
  private Long factoryId;

}

