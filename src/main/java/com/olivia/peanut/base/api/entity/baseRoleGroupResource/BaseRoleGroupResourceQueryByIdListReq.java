package com.olivia.peanut.base.api.entity.baseRoleGroupResource;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.util.List;

/**
 * 角色组资源表(BaseRoleGroupResource)查询对象入参
 *
 * @author peanut
 * @since 2024-08-09 15:42:35
 */
@Accessors(chain = true)
@Getter
@Setter
@SuppressWarnings("serial")
public class BaseRoleGroupResourceQueryByIdListReq {

  private List<Long> idList;


}

