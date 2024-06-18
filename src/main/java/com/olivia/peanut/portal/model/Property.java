package com.olivia.peanut.portal.model;


import com.baomidou.mybatisplus.annotation.TableName;
import com.olivia.sdk.utils.BaseEntity;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * 资产信息(Property)表实体类
 *
 * @author makejava
 * @since 2024-03-11 17:20:53
 */
@Accessors(chain = true)
@Getter
@Setter
//
@TableName("t_property")
public class Property extends BaseEntity {

  /***
   *  所属工厂id
   */
  private Long factoryId;
  /***
   *  楼层
   */
  private Long storeyId;
  /***
   *  房间ID
   */
  private Long roomId;
  /***
   *  资产编号
   */
  private String propertyCode;
  /***
   *  资产
   */
  private String propertyName;
  /***
   *  是否在用  1是, 0否
   */
  private Boolean inUse;

}

