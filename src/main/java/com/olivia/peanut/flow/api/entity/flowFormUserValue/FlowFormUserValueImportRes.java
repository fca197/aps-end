package com.olivia.peanut.flow.api.entity.flowFormUserValue;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.util.List;

/**
 * 工作流表单用户数据表(FlowFormUserValue)保存返回
 *
 * @author peanut
 * @since 2024-08-03 18:10:54
 */
@Accessors(chain = true)
@Getter
@Setter
@SuppressWarnings("serial")
public class FlowFormUserValueImportRes {

  /****
   * 写入行数
   */
  private int count;
  /**
   * 错误信息
   */
  private List<String> errorMsg;
}

