package com.olivia.peanut.base.api.entity.baseAppResource;


import com.olivia.peanut.portal.api.entity.BaseEntityDto;
import com.olivia.sdk.ann.InsertCheck;
import com.olivia.sdk.ann.UpdateCheck;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

/**
 * 资源(BaseAppResource)查询对象返回
 *
 * @author peanut
 * @since 2024-08-06 17:30:28
 */
//@Accessors(chain=true)
@Getter
@Setter
@SuppressWarnings("serial")
public class BaseAppResourceDto extends BaseEntityDto {

  /***
   *  应用ID
   */
  @NotNull(message = "应用ID不能为空", groups = {InsertCheck.class, UpdateCheck.class})
  private Long appId;

  private String appCode;
  /***
   *  资源ID
   */
  @NotNull(message = "资源ID不能为空", groups = {InsertCheck.class, UpdateCheck.class})
  private Long resourceId;


}


