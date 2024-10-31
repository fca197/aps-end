## 工作流相关下载

[camunda-modeler](https://github.com/camunda/camunda-modeler/releases)

## 工作流配置约定

0. 工作流版本 camunda-7版本, 使用bpmn结构
1. 工作流开始第一个节点id必须为: begin ,流程被驳回时需要定位到第一个节点
2. 用户选择查看 [task inputs 添加默认数据](#task-inputs-添加默认数据)

## 工作流 listener

| 类功能        | 使用位置           | 事件类型              | 实现接口              | 类名                                                                       |
|------------|----------------|-------------------|-------------------|--------------------------------------------------------------------------|
| 任务分配给流程创建人 | 用户任务第一个任务      | create            | TaskListener      | com.olivia.peanut.flow.core.listener.task.CreateBeginTaskListener        |
| 流程结束通知类    | 工作流结束          | end               | JavaDelegate      | com.olivia.peanut.flow.core.delegate.NoticeTaskDelegate                  |
| 任务分派       | 指定用户使用,指派第一个用户 | create/assignment | TaskListener      | com.olivia.peanut.flow.core.listener.task.CreateTaskListener             |
| 钉钉通知流程结束   | 工作流 end监听器     | end               | ExecutionListener | com.olivia.peanut.flow.core.listener.execution.DingNoticeTaskEndListener |
|            |                |                   | ExecutionListener | com.olivia.peanut.flow.core.listener.execution.EndExecutionListener      |
|            |                |                   | ExecutionListener | com.olivia.peanut.flow.core.listener.execution.StartExecutionListener    |
|            |                |                   | ExecutionListener | com.olivia.peanut.flow.core.listener.execution.TakeExecutionListener     |
|            |                |                   | TaskListener      | com.olivia.peanut.flow.core.listener.task.AssignmentTaskListener         |
|            |                |                   | TaskListener      | com.olivia.peanut.flow.core.listener.task.CompleteTaskListener           |
|            |                |                   | TaskListener      | com.olivia.peanut.flow.core.listener.task.DeleteTaskListener             |
|            |                |                   | TaskListener      | com.olivia.peanut.flow.core.listener.task.NoticeTaskListener             |
|            |                |                   | TaskListener      | com.olivia.peanut.flow.core.listener.task.TimeOutTaskListener            |
|            |                |                   | TaskListener      | com.olivia.peanut.flow.core.listener.task.UpdateTaskListener             |

## 工作流配置 inputs

| 配置名称         | 类型     | 值                                     | 描述            | 使用类                | 示例                     |
|--------------|--------|---------------------------------------|---------------|--------------------|------------------------|
| userAssignee | map    | key: role , value: roleCode (可变)      | 指定角色处理        | CreateTaskListener | {"role":"CEO"}         |
| userAssignee | map    | key: user ,  value: login   (定值)      | 当前登录用户处理      | CreateTaskListener | {"user":"login"}       | 
| userAssignee | map    | key: deptRole ,  value: roleCode (可变) | 用户所在部门角色下人员处理 | CreateTaskListener | {"deptRole":"manager"} |
| copyAssignee | map    | key: role , value: roleCode (可变)      | 抄送            | CreateTaskListener | {"role":"CEO"}         |
| copyAssignee | map    | key: user ,  value: login   (定值)      | 抄送            | CreateTaskListener | {"user":"login"}       |
| copyAssignee | map    | key: deptRole ,  value: roleCode (可变) | 抄送            | CreateTaskListener | {"deptRole":"manager"} | 
| timeOut      | String | 时长-[H (小时),D (天),W(周)                 | 任务超时时间        | CreateTaskListener | 3D  ,3天后超时             |

## task inputs 添加默认数据

| 配置名称           | 说明     | inputs 来源    | 值                           |
|----------------|--------|--------------|-----------------------------|
| copyUserIdList | 抄送用户列表 | copyAssignee | ["userId1","userId2"]       |
| userIdList     | 抄送用户列表 | userAssignee | ["userId1","userId2"]       |
| userId         | 抄送用户列表 | userAssignee | userId: userIdList列表第一个用户ID |

## 流程变量流转

| 变量名称                | 流程环节    | 描述                        |
|---------------------|---------|---------------------------|
| businessKey         | 流程创建    | flow_form_user_value  中ID |
| FLOW_FORM_ID        | 流程创建    | 流程引用表单ID                  |
| FLOW_NAME           | 流程创建    | 流程名称                      |
| FLOW_CREATE_USER_ID | 流程创建    | 流程实例创建人                   |
| IS_FIRST_TASK       | 流程创建    | 是否第一个任务                   |
| userId              | 流程任务开始前 | 用户在inputs放入的用户配置第一个人      |
| userIdList          | 流程任务开始前 | 用户在inputs放入的用户配置的所有人      |

## 多人处理

先了解到，对于“多实例任务”， task 会自动生成以下几个流程变量：

> nrOfActiviteInstances：当前活动的实例数量，即还没有完成的实例数量  
> loopCounter ：循环计数器，办理人在列表中的索引  
> nrOfInstances：会签中总共的实例数  
> nrOfCompletedInstances：已经完成的实例数量

那么，在 Completion condition 中 可以这么填写：

> $ {nrOfInstances == nrOfCompletedInstances} 表示所有人员审批完成后会签结束。  
> ${ nrOfCompletedInstances == 1} 表示一个人完成审批。

## 问题

1. 流程TTL应该配置

> 流程配置 history cleanup 中:  time to live 必填,单位 天

2. 2
