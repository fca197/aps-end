
## 工作流相关下载
[camunda-modeler](https://github.com/camunda/camunda-modeler/releases)

## 工作流配置约定

1. 工作流开始第一个节点id必须为: begin ,流程被驳回时需要定位到第一个节点
2. 工作流多人处理时, condition 配置为: ${userIdList} , completion-condition 配置为: ${nrOfCompletedInstances == 1},1人通过即通过
3. 工作流单个任务分派 assignee 配置为: ${userId} ,
4. 超时配置 :  timeOut 配置为: 4D

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

| 配置名称         | 类型     | 值                     | 描述     | 使用类                | 示例                          |
|--------------|--------|-----------------------|--------|--------------------|-----------------------------|
| userAssignee | map    | key: role , roleCode  | 任务分配给谁 | CreateTaskListener | {"role":"CEO"}    ,角色CEO 处理 |
| userAssignee | map    | key: user , login     | 任务分配给谁 | CreateTaskListener | {"user":"login"} ,登录用户  处理  | 
| timeOut      | String | 时长-[H (小时),D (天),W(周) | 任务超时时间 | CreateTaskListener | 3D  ,3天后超时                  |

## 多人处理

先了解到，对于“多实例任务”， task 会自动生成以下几个流程变量：

> nrOfActiviteInstances：当前活动的实例数量，即还没有完成的实例数量
> loopCounter ：循环计数器，办理人在列表中的索引
> nrOfInstances：会签中总共的实例数
> nrOfCompletedInstances：已经完成的实例数量

那么，在 Completion condition 中 可以这么填写：

> $ {nrOfInstances == nrOfCompletedInstances} 表示所有人员审批完成后会签结束。
> ${ nrOfCompletedInstances == 1} 表示一个人完成审批。
