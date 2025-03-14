package com.olivia.peanut.base.api.entity.baseApp;


import com.olivia.peanut.portal.api.entity.BaseEntityDto;
import com.olivia.sdk.ann.InsertCheck;
import com.olivia.sdk.ann.UpdateCheck;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

/**
 * 应用表(BaseApp)查询对象返回
 *
 * @author peanut
 * @since 2024-08-05 11:18:58
 */
//@Accessors(chain=true)
@Getter
@Setter
@SuppressWarnings("serial")
public class BaseAppDto extends BaseEntityDto {

  /***
   *  app编码
   */
  @NotBlank(message = "app编码不能为空", groups = {InsertCheck.class, UpdateCheck.class})
  private String appCode;
  /***
   *  app名称
   */
  @NotBlank(message = "app名称不能为空", groups = {InsertCheck.class, UpdateCheck.class})
  private String appName;

}


