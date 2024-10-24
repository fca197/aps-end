package com.olivia.peanut.aps.model;


import java.time.LocalDate;
import java.time.LocalDateTime;

import com.olivia.sdk.utils.BaseEntity;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import com.baomidou.mybatisplus.annotation.TableName;

/**
 * aps 生产路径(ApsProduceProcess)表实体类
 *
 * @author makejava
 * @since 2024-10-24 17:00:19
 */
@Accessors(chain = true)
@Getter
@Setter
//@SuppressWarnings("serial")
@TableName("aps_produce_process")
public class ApsProduceProcess extends BaseEntity {
  /***
   *  生产路径编码
   */
  private String produceProcessNo;
  /***
   *  生产路径名称
   */
  private String produceProcessName;

}

