package com.olivia.peanut.base.api.entity.baseUserResource;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * 用户角色资源表(BaseUserResource)查询对象入参
 *
 * @author peanut
 * @since 2024-08-09 16:26:39
 */
@Accessors(chain = true)
@Getter
@Setter
@SuppressWarnings("serial")
public class BaseUserResourceQueryListReq {

  private BaseUserResourceDto data;
}

