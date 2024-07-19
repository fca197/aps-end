package com.olivia.peanut.aps.api.entity.apsRoomConfig;

import com.olivia.peanut.portal.api.entity.BaseEntityDto;
import com.olivia.sdk.ann.InsertCheck;
import com.olivia.sdk.ann.UpdateCheck;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

/**
 * (ApsRoomConfig)查询对象返回
 *
 * @author peanut
 * @since 2024-04-01 15:27:30
 */
//@Accessors(chain=true)
@Getter
@Setter
@SuppressWarnings("serial")
public class ApsRoomConfigDto extends BaseEntityDto {

  @NotNull(message = "房间不能为空", groups = {InsertCheck.class, UpdateCheck.class})
  private Long roomId;
  private String roomName;
  @NotNull(message = "工段不能为空", groups = {InsertCheck.class, UpdateCheck.class})
  private Long sectionId;
  @NotNull(message = "工位不能为空", groups = {InsertCheck.class, UpdateCheck.class})
  private Long stationId;
  @NotNull(message = "状态不能为空", groups = {InsertCheck.class, UpdateCheck.class})
  private Long statusId;
  @NotNull(message = "执行时间不能为空", groups = {InsertCheck.class, UpdateCheck.class})
  private Integer executeTime;
  @NotNull(message = "工厂不能为空", groups = {InsertCheck.class, UpdateCheck.class})
  private Long factoryId;

}


