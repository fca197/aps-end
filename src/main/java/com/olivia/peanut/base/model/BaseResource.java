package com.olivia.peanut.base.model;


import java.time.LocalDate;
import java.time.LocalDateTime;
import com.olivia.sdk.utils.BaseEntity;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import com.baomidou.mybatisplus.annotation.TableName;

/**
 * 资源(BaseResource)表实体类
 *
 * @author peanut
 * @since 2024-07-31 14:33:33
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

}

