# 任务编排 demo

````json lines

{
  "node": [
    {
      type: "javaBean,http",
      javaBean: {
        taskName: "",
      },
      http: {
        url: "https://test.cn/a/b/c",
        reqMethod: "post,get",
        dataConverter: "toJson,toParam",
        signTypeName: "ignore"
      },
      id: '2',
      position: {
        x: 50,
        y: 250
      },
      data: {
        label: 'Node 2',
      },
      listener: {
        prefix: "prefixListener",
        suffix: "suffixListener"
      },
      config: {
        timeOut: 3000,
        abend: "all,task,ignore"
      },
      javaCode: {
        before: "",
        after: ""
      }
    }
  ],
  "edge": [
    {
      id: 'e1->2',
      source: '1',
      target: '2',
      condition: "a==b"
    }
  ]
}

````

## 任务配置

## 任务引擎

1. 发起，任务ID
2. 查找第一个任务：
3. 开始执行： DOING
2. 前置监听器：
3. 方法执行：
4. 后置监听器
5. 方法结束
6. 根据流转获取下N个任务 ，标记任务状态待开始 TODO：
7. 返回2执行
8. 都执行结束：
9. 任务执行结束
10. 异常抛出

# 任务配置

1. 前置监听器
2. 方法执行
3. 后置监听器
4. 结束
5. 异常抛出