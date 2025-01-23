package com.olivia.peanut.aps.api.entity.apsProcessPath;

import com.olivia.peanut.aps.api.entity.apsProcessPathRoom.ApsProcessPathRoomDto;
import com.olivia.peanut.portal.api.entity.BaseEntityDto;
import com.olivia.sdk.ann.InsertCheck;
import com.olivia.sdk.ann.UpdateCheck;
import com.olivia.sdk.utils.Str;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.util.List;

/**
 * (ApsProcessPath)查询对象返回
 *
 * @author peanut
 * @since 2024-04-01 17:49:18
 */
//@Accessors(chain=true)
@Getter
@Setter
@Accessors(chain = true)
@SuppressWarnings("serial")
public class ApsProcessPathDto extends BaseEntityDto {

  @NotBlank(message = "工单路径编码不能为空", groups = {InsertCheck.class, UpdateCheck.class})
  private String processPathCode;
  @NotBlank(message = "工单路径名称不能为空", groups = {InsertCheck.class, UpdateCheck.class})
  private String processPathName;
  @NotBlank(message = "工单路径备注不能为空", groups = {InsertCheck.class, UpdateCheck.class})
  private String processPathRemark;
  @NotNull(message = "工单路径是否默认不能为空", groups = {InsertCheck.class, UpdateCheck.class})
  private Boolean isDefault;
  private String factoryName;
  @NotNull(message = "工单路径工厂不能为空", groups = {InsertCheck.class, UpdateCheck.class})
  private Long factoryId;
  @NotNull(message = "工单路径房间不能为空", groups = {InsertCheck.class, UpdateCheck.class})
  private List<ApsProcessPathRoomDto> pathRoomList;

  public String getIsDefaultStr() {
    return Str.booleanToStr(isDefault);
//    return Boolean.TRUE.equals(isDefault) ? "是" : "否";
  }

}


