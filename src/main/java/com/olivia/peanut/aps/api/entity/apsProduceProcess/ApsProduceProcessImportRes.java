package com.olivia.peanut.aps.api.entity.apsProduceProcess;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.util.List;
import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * aps 生产路径(ApsProduceProcess)保存返回
 *
 * @author makejava
 * @since 2024-10-24 17:00:19
 */
@Accessors(chain = true)
@Getter
@Setter
@SuppressWarnings("serial")
public class ApsProduceProcessImportRes {
  /****
   * 写入行数
   */
  private int count;
  /**
   * 错误信息
   */
  private List<String> errorMsg;
}

