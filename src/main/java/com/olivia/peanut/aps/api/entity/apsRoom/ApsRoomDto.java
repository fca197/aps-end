package com.olivia.peanut.aps.api.entity.apsRoom;

import com.olivia.peanut.aps.api.entity.apsRoomConfig.ApsRoomConfigDto;
import com.olivia.peanut.portal.api.entity.BaseEntityDto;
import com.olivia.sdk.ann.InsertCheck;
import com.olivia.sdk.ann.UpdateCheck;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * (ApsRoom)查询对象返回
 *
 * @author peanut
 * @since 2024-04-01 15:27:30
 */
//@Accessors(chain=true)
@Getter
@Setter
@SuppressWarnings("serial")
public class ApsRoomDto extends BaseEntityDto {


  @NotBlank(message = "房间编码不能为空", groups = {InsertCheck.class, UpdateCheck.class})
  private String roomCode;
  @NotBlank(message = "房间名称不能为空", groups = {InsertCheck.class, UpdateCheck.class})
  private String roomName;

  private String factoryName;
  @NotNull(message = "工厂不能为空", groups = {InsertCheck.class, UpdateCheck.class})
  private Long factoryId;
  @NotNull(message = "房间类型不能为空", groups = {InsertCheck.class, UpdateCheck.class})
  private List<ApsRoomConfigDto> configList;

}


