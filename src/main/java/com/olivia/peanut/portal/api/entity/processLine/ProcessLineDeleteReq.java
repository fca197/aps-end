package com.olivia.peanut.portal.api.entity.processLine;


import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;

/**
 * 流水线信息(ProcessLine)表实体类
 *
 * @author peanut
 * @since 2024-01-11 17:30:13
 */
@Data
@Accessors(chain = true)
public class ProcessLineDeleteReq {

  @NotNull(message = "删除条数不能为空")
  @Size(min = 1, max = 100, message = "删除条数必须介于 {min} 到 {max} 之间")
  private List<Long> idList;
}


