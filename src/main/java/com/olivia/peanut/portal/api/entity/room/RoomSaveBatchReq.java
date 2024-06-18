package com.olivia.peanut.portal.api.entity.room;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/***
 *
 */
@Setter
@Getter
@Accessors(chain = true)
public class RoomSaveBatchReq {

  @NotNull(message = "工厂不能为空")
  private Long factoryId;
  @NotNull(message = "前缀类型不能为空")
  private Boolean isPrefix;
  @NotNull(message = "房间数量不能为空")
  @Min(value = 1, message = "房间数量不能小于1")
  @Max(value = 60, message = "房间数量不能大于60")
  private Integer roomCount;
  @NotNull(message = "楼层不能为空")
  private Long storeyId;
  private String roomPrefix;
}
