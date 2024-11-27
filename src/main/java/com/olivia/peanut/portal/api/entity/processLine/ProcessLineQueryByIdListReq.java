package com.olivia.peanut.portal.api.entity.processLine;

import java.util.List;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * 流水线信息(ProcessLine)查询对象入参
 *
 * @author makejava
 * @since 2024-03-11 10:55:21
 */
@Accessors(chain = true)
@Getter
@Setter

public class ProcessLineQueryByIdListReq {

  private List<Long> idList;


}

