# 任务编排 demo

````json lines


````

## 任务配置

| 任务类型 | 实现接口         | 注意事项                           |
|------|--------------|--------------------------------|
| java | TaskBeanExec | 如果是springBean类型，指定@Component() |
| java | TaskBeanExec | 如果是javaClass类型，使用完整类名          |

## 任务引擎

1. 发起，任务ID
2. 查找第一个任务：
3. 开始执行： DOING
4. 前置监听器：
5. 方法执行：
6. 后置监听器
7. 方法结束
8. 根据流转获取下N个任务 ，标记任务状态待开始 TODO：
9. 返回2执行
10. 都执行结束：
11. 任务执行结束
12. 异常抛出

# 任务配置

1. 前置监听器
2. 方法执行
3. 后置监听器
4. 结束
5. 异常抛出

# 任务引擎实现

包名:  com.olivia.peanut.task.engine

| 类名                         | 实现功能      | 备注                                                        |
|----------------------------|-----------|-----------------------------------------------------------|
| BaseTaskEngine             | 引擎主入口     | Long startTaskId(Long taskId) 开始一个任务                      |
| TaskInfoDefRunner          | 任务执行封装类   | 任务实际执行具体实现                                                |
| TaskRunnerExec             | 任务分发执行类   | 实现java或http接口调用                                           |
| JavaBeanTaskRunnerExecImpl | java 任务实现 | TaskRunnerExec实现类， 流程配置主要实现该接口，可以配置springBean或javaClass调用 |
| HttpTaskRunnerExecImpl     | http接口调用  | TaskRunnerExec实现类， 使用HttpClient进行post或get地址调用             |
| EndRunnerExecImpl          | 结束日志输出    | TaskRunnerExec实现类， 紧记录日志                                  |
| TaskListener               | java监听器   | 实现前置或后置监听器                                                |