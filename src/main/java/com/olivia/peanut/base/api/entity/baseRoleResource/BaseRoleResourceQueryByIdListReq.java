package com.olivia.peanut.base.api.entity.baseRoleResource;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.util.List;

/**
 * 角色资源表(BaseRoleResource)查询对象入参
 *
 * @author peanut
 * @since 2024-08-09 15:42:37
 */
@Accessors(chain = true)
@Getter
@Setter
@SuppressWarnings("serial")
public class BaseRoleResourceQueryByIdListReq {

  private List<Long> idList;


}

