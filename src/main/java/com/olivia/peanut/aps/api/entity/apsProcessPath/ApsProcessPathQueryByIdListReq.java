package com.olivia.peanut.aps.api.entity.apsProcessPath;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.util.List;

/**
 * (ApsProcessPath)查询对象入参
 *
 * @author peanut
 * @since 2024-04-01 17:49:18
 */
@Accessors(chain = true)
@Getter
@Setter
@SuppressWarnings("serial")
public class ApsProcessPathQueryByIdListReq {

  private List<Long> idList;


}

