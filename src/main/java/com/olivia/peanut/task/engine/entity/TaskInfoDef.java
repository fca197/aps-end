package com.olivia.peanut.task.engine.entity;

import com.olivia.peanut.task.engine.entity.vo.*;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.util.List;

@Setter
@Getter
@Accessors(chain = true)
public class TaskInfoDef {

  /***
   * 上一环节ID
   */
  private String sourceTaskId;
  /***
   * 进入条件
   */
  private String sourceTaskCondition;


  /****
   * 当前环节ID
   */
  private String id;
  /***
   * 任务名称
   */
  private String taskName;
  /***
   * 任务类型  BEGIN, JAVA_BEAN, HTTP, END
   */
  private TaskType taskType;

  /****
   *  Java类型任务
   */
  private TaskJavaType taskJavaType;
  /****
   * 任务名称
   */
  private String taskBeanName;


  /***
   * http 类型任务 请求地址
   */
  private String reqUrl;

  /***
   * http 类型任务 请求方法
   */
  private HttpReqMethod reqMethod;

  /****
   * 结果检查
   */
  private CheckType checkType;

  /****
   * http 检查
   */
  private String checkUrl;
  /***
   * 请求方式
   */
  private HttpReqMethod checkReqMethod;
  /**
   * java 检查 类型
   */
  private TaskJavaType checkTaskJavaType;
  /****
   * java 名称
   */
  private String checkTaskBeanName;

  /***
   * 配置 重试间隔
   */
  private Long retryInterval;
  /***
   * 配置 重试次数
   */
  private Long retryCount;
  /**
   * 执行次数
   */
  private Long executeCount;
  /***
   * 超时时间 ms
   */
  private Long timeOut;
  /***
   * 异常终止
   */
  private ExceptionStop exceptionStop;

  /***
   * 前置监听器
   */

  private String prefixListenerName;

  /****
   * 后置监听器
   */
  private String suffixListenerName;
  /****
   * 入参映射
   */
  private List<Mapping> inputMappingList;
  /***
   * 反回值映射
   */
  private List<Mapping> outputMappingList;


}
