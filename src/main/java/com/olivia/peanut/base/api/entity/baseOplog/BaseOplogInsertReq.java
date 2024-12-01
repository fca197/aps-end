package com.olivia.peanut.base.api.entity.baseOplog;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * 操作日志(BaseOplog)保存入参
 *
 * @author makejava
 * @since 2024-11-30 16:01:00
 */
@Accessors(chain = true)
@Getter
@Setter
@SuppressWarnings("serial")
public class BaseOplogInsertReq extends BaseOplogDto {

  public void checkParam() {
  }
}

