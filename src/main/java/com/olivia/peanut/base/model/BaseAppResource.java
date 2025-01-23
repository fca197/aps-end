package com.olivia.peanut.base.model;


import com.baomidou.mybatisplus.annotation.TableName;
import com.olivia.sdk.utils.BaseEntity;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * 资源(BaseAppResource)表实体类
 *
 * @author peanut
 * @since 2024-08-06 17:30:28
 */
@Accessors(chain = true)
@Getter
@Setter
//@SuppressWarnings("serial")
@TableName("base_app_resource")
public class BaseAppResource extends BaseEntity {

  /***
   *  应用ID
   */
  private Long appId;
  /***
   *  资源ID
   */
  private Long resourceId;

}

