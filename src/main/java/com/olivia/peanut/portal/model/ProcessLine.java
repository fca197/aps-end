package com.olivia.peanut.portal.model;


import com.baomidou.mybatisplus.annotation.TableName;
import com.olivia.sdk.utils.BaseEntity;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * 流水线信息(ProcessLine)表实体类
 *
 * @author makejava
 * @since 2024-03-11 10:55:21
 */
@Accessors(chain = true)
@Getter
@Setter
//
@TableName("t_process_line")
public class ProcessLine extends BaseEntity {

  /***
   *  所属租户id
   */
  private Long tenantId;
  /***
   *  所属工厂id
   */
  private Long factoryId;
  /***
   *  所属进程id
   */
  private Long processId;
  /***
   *  线别名称
   */
  private String lineName;
  /***
   *  线别编码
   */
  private String lineCode;
  /***
   *  线别描述
   */
  private String lineDesc;
  /***
   *  线别排序
   */
  private Integer lineSort;


}

