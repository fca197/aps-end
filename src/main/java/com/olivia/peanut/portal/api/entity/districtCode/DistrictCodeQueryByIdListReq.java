package com.olivia.peanut.portal.api.entity.districtCode;

import java.util.List;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * (DistrictCode)查询对象入参
 *
 * @author peanut
 * @since 2024-04-09 13:19:07
 */
@Accessors(chain = true)
@Getter
@Setter
@SuppressWarnings("serial")
public class DistrictCodeQueryByIdListReq {

  private List<Long> idList;


}

