package com.olivia.peanut.portal.api.entity.property;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.util.List;

/**
 * 资产信息(Property)查询对象入参
 *
 * @author makejava
 * @since 2024-03-11 17:20:54
 */
@Accessors(chain = true)
@Getter
@Setter

public class PropertyQueryByIdListReq {

  private List<Long> idList;


}

