package com.olivia.peanut.base.model;


import com.baomidou.mybatisplus.annotation.TableName;
import com.olivia.sdk.utils.BaseEntity;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * 应用表(BaseApp)表实体类
 *
 * @author peanut
 * @since 2024-08-05 11:18:57
 */
@Accessors(chain = true)
@Getter
@Setter
//@SuppressWarnings("serial")
@TableName("base_app")
public class BaseApp extends BaseEntity {

  /***
   *  app编码
   */
  private String appCode;
  /***
   *  app名称
   */
  private String appName;

}

