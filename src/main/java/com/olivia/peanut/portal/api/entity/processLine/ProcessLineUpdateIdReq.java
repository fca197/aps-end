package com.olivia.peanut.portal.api.entity.processLine;


import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 流水线信息(ProcessLine)表实体类
 *
 * @author peanut
 * @since 2024-01-11 17:30:13
 */
@Data
@Accessors(chain = true)
public class ProcessLineUpdateIdReq {

  @NotNull(message = "修改信息不能为空")
  private Long id;
  /**
   * 所属租户id
   **/
  private Long tenantId;
  /**
   * 所属工厂id
   **/
  private Long factoryId;
  /**
   * 所属进程id
   **/
  private Long processId;
  /**
   * 线别名称
   **/
  private String lineName;
  /**
   * 线别编码
   **/
  private String lineCode;
  /**
   * 线别描述
   **/
  private String lineDesc;
  /**
   * 线别排序
   **/
  private Integer lineSort;

}


