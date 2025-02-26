package com.olivia.peanut.base.model;


import com.baomidou.mybatisplus.annotation.TableName;
import com.olivia.sdk.utils.BaseEntity;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * 资源(BaseResource)表实体类
 *
 * @author peanut
 * @since 2024-08-06 17:29:01
 */
@Accessors(chain = true)
@Getter
@Setter
//@SuppressWarnings("serial")
@TableName("base_resource")
public class BaseResource extends BaseEntity {

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
   *  是否按钮 0 否,1 是
   */
  private Boolean isButton;
  /***
   *  父菜单ID
   */
  private Long parentId;
  /***
   *  菜单路径
   */
  private String path;

  private Integer sortIndex;

}

