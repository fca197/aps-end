package com.olivia.peanut.base.api.entity.baseOplog;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.util.List;

/**
 * 操作日志(BaseOplog)查询对象返回
 *
 * @author makejava
 * @since 2024-11-30 16:01:01
 */
@Accessors(chain = true)
@Getter
@Setter
@SuppressWarnings("serial")
public class BaseOplogQueryListRes {
  /***
   * 返回对象列表
   */
  private List<BaseOplogDto> dataList;


}

