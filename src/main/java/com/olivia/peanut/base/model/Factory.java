package com.olivia.peanut.base.model;


import com.baomidou.mybatisplus.annotation.TableName;
import com.olivia.sdk.utils.BaseEntity;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * 工段信息(Factory)表实体类
 *
 * @author makejava
 * @since 2024-03-11 10:44:05
 */
@Accessors(chain = true)
@Getter
@Setter
//
@TableName("t_factory")
public class Factory extends BaseEntity {

  /***
   *  id
   */
  private Long id;
  /***
   *  所属租户id
   */
  private Long tenantId;
  /***
   *  工厂名称
   */
  private String factoryName;
  /***
   *  工厂编码
   */
  private String factoryCode;
  /***
   *  工厂状态
   */
  private String factoryStatus;

}

