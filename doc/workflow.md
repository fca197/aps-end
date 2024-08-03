## 工作流 listener

| 类功能        | 使用位置           | 事件类型              | 实现接口              | 类名                                                                    |
|------------|----------------|-------------------|-------------------|-----------------------------------------------------------------------|
| 任务分配给流程创建人 | 工作流第一个任务       | assignee          | TaskListener      | com.olivia.peanut.flow.core.listener.task.CreateBeginTaskListener     |
| 流程结束通知类    | 工作流结束          | begin/end         | JavaDelegate      | com.olivia.peanut.flow.core.delegate.NoticeTaskDelegate               |
| 任务分派       | 指定用户使用,指派第一个用户 | create/assignment | TaskListener      | com.olivia.peanut.flow.core.listener.task.CreateTaskListener          |
|            |                |                   | ExecutionListener | com.olivia.peanut.flow.core.listener.execution.EndExecutionListener   |
|            |                |                   | ExecutionListener | com.olivia.peanut.flow.core.listener.execution.StartExecutionListener |
|            |                |                   | ExecutionListener | com.olivia.peanut.flow.core.listener.execution.TakeExecutionListener  |
|            |                |                   | TaskListener      | com.olivia.peanut.flow.core.listener.task.AssignmentTaskListener      |
|            |                |                   | TaskListener      | com.olivia.peanut.flow.core.listener.task.CompleteTaskListener        |
|            |                |                   | TaskListener      | com.olivia.peanut.flow.core.listener.task.DeleteTaskListener          |
|            |                |                   | TaskListener      | com.olivia.peanut.flow.core.listener.task.NoticeTaskListener          |
|            |                |                   | TaskListener      | com.olivia.peanut.flow.core.listener.task.TimeOutTaskListener         |
|            |                |                   | TaskListener      | com.olivia.peanut.flow.core.listener.task.UpdateTaskListener          |

## 工作流配置 inputs

| 配置名称         | 类型  | 值                    | 描述     | 使用类                | 示例             |
|--------------|-----|----------------------|--------|--------------------|----------------|
| userAssignee | map | key: role , roleCode | 任务分配给谁 | CreateTaskListener | {"role":"CEO"} |

