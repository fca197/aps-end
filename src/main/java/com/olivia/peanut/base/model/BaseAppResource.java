package com.olivia.peanut.base.model;


import java.time.LocalDate;
import java.time.LocalDateTime;
import com.olivia.sdk.utils.BaseEntity;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import com.baomidou.mybatisplus.annotation.TableName;

/**
 * 资源(BaseAppResource)表实体类
 *
 * @author peanut
 * @since 2024-08-05 11:19:00
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
   *  应用编码
   */
  private String appCode;
  /***
   *  菜单编码
   */
  private String resourceCode;
  /***
   *  菜单名称
   */
  private String resourceName;
  /***
   *  菜单URL
   */
  private String resourceUrl;
  /***
   *  菜单图标
   */
  private String resourceIcon;
  /***
   *  菜单类型
   */
  private String resourceType;
  /***
   *  是否按钮
   */
  private Boolean isButton;

  private Boolean isHidden;
  /***
   *  父菜单ID
   */
  private Long parentId;
  /***
   *  菜单路径
   */
  private String path;


  private String filePath;
}

