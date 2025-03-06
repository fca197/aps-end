# 任务编排 demo

````json lines

[
  {
    id: '2',
    type: "begin,end,javaBean,http",
    taskName: "任务名称",
    javaBean: {
      taskName: "",
      taskType: "springBean,javaClass"
    },
    http: {
      url: "https://test.cn/a/b/c",
      reqMethod: "post,get",
      dataConverter: "toJson,toParam",
      signTypeName: "ignore"
    },
    data: {
      key1: "value1"
    },
    prefixListener: "",
    suffixListener: "",
    config: {
      "retryCount": 3,
      retryInterval: 33,
      timeOut: 3000,
      abend: "all,task,ignore"
    },
    outputMapping: {
      "inputKey1": "data.key1",
      "inputKey2": "data.key2"
    },
    inputMapping: {
      "outputKey1": "response.data.field1",
      "outputKey2": "response.data.field2"
    },
    target: [
      {
        condition: "a==b",
        targetId: "2"
      }
    ]
  }
]

````

## 任务配置

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