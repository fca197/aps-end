package com.olivia.peanut.portal.model;


import com.baomidou.mybatisplus.annotation.TableName;
import com.olivia.sdk.utils.BaseEntity;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * (Cache)表实体类
 *
 * @author makejava
 * @since 2024-03-17 14:32:47
 */
@Accessors(chain = true)
@Getter
@Setter
//
@TableName("t_cache")
public class Cache extends BaseEntity {

  private String cacheKey;
  private String cacheValue;
  private String clazz;
  private String expireTime;
}

