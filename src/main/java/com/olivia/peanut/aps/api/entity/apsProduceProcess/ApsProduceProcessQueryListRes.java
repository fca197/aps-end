package com.olivia.peanut.aps.api.entity.apsProduceProcess;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.util.List;

/**
 * aps 生产路径(ApsProduceProcess)查询对象返回
 *
 * @author makejava
 * @since 2024-10-24 17:00:18
 */
@Accessors(chain = true)
@Getter
@Setter
@SuppressWarnings("serial")
public class ApsProduceProcessQueryListRes {
  /***
   * 返回对象列表
   */
  private List<ApsProduceProcessDto> dataList;


}

