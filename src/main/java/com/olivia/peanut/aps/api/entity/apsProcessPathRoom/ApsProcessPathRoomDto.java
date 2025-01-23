package com.olivia.peanut.aps.api.entity.apsProcessPathRoom;

import com.olivia.peanut.aps.api.entity.apsRoomConfig.ApsRoomConfigDto;
import com.olivia.peanut.portal.api.entity.BaseEntityDto;
import com.olivia.sdk.ann.InsertCheck;
import com.olivia.sdk.ann.UpdateCheck;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.util.List;

/**
 * (ApsProcessPathRoom)查询对象返回
 *
 * @author peanut
 * @since 2024-04-01 17:49:19
 */
//@Accessors(chain=true)
@Getter
@Setter
@Accessors(chain = true)
@SuppressWarnings("serial")
public class ApsProcessPathRoomDto extends BaseEntityDto {

  @NotNull(message = "流程不能为空", groups = {InsertCheck.class, UpdateCheck.class})
  private Long processPathId;
  @NotNull(message = "房间不能为空", groups = {InsertCheck.class, UpdateCheck.class})
  private Long roomId;
  @NotNull(message = "工厂不能为空", groups = {InsertCheck.class, UpdateCheck.class})
  private Long factoryId;

  private String roomName;

  @NotNull(message = "房间配置不能为空", groups = {InsertCheck.class, UpdateCheck.class})
  private List<ApsRoomConfigDto> apsRoomConfigList;

}


