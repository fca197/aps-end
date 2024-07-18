package com.olivia.peanut.aps.api.entity.apsLogisticsPathItem;

import java.util.List;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * 物流路详情径表(ApsLogisticsPathItem)保存返回
 *
 * @author peanut
 * @since 2024-07-18 13:27:40
 */
@Accessors(chain = true)
@Getter
@Setter
@SuppressWarnings("serial")
public class ApsLogisticsPathItemImportRes {

  /****
   * 写入行数
   */
  private int count;
  /**
   * 错误信息
   */
  private List<String> errorMsg;
}

