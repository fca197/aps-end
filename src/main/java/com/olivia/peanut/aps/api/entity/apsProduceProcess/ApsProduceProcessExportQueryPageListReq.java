package com.olivia.peanut.aps.api.entity.apsProduceProcess;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * aps 生产路径(ApsProduceProcess)查询对象入参
 *
 * @author makejava
 * @since 2024-10-24 17:00:19
 */
@Accessors(chain = true)
@Getter
@Setter
@SuppressWarnings("serial")
public class ApsProduceProcessExportQueryPageListReq {
  private int pageNum;
  private int pageSize;
  private Boolean queryPage = true;
  private ApsProduceProcessDto data;


}

