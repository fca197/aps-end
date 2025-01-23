package com.olivia.peanut.portal.api.entity.storey;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.util.List;

/**
 * 楼层信息(Storey)查询对象入参
 *
 * @author makejava
 * @since 2024-03-11 17:20:56
 */
@Accessors(chain = true)
@Getter
@Setter

public class StoreyQueryByIdListReq {

  private List<Long> idList;


}

