# 框架生成代码介绍

| 类                                  | 说明                                              |
|------------------------------------|-------------------------------------------------|
| api/..Api                          | 对外接口 ,可以提出api+entity当此项目的对外API接口                |
| api/impl/..ApiImpl                 | 对外接口实现类                                         |
| api/impl/listener/..ImportListener | excel导入处理类 ,lombok 注解@Accessors(chain=true)禁止使用 |
| mapper/..Mapper                    | mybatis 生成的mapper                               |
| model/..                           | 数据库对应实体                                         |
| service/..                         | 逻辑处理层接口                                         |
| service/impl/..Impl                | 逻辑处理层接口实现类                                      |

# 订单部分

| 功能点      | 路径                                            | 开发事项                    |
|----------|-----------------------------------------------|-------------------------|
| 订单创建     | /apsOrder/insert                              | 自己实现订单录入 ,或接入MQ接收订单     |
| 订单状态变更   | /apsOrder/updateOrderStatus                   | 可以接入MQ对接该接口             |  
| 历史订单汇总   | /apsOrderGoodsHistory/selectOrder2History     | xxl-job 接入   ，支持当前月，上个月 |  
| 历史销售配置汇总 | /apsOrderGoodsSaleHistory/selectOrder2History | xxl-job 接入   ，支持当前月，上个月 |  

# 排产

| 功能点    | 路径                                   | 开发事项                                  |
|--------|--------------------------------------|---------------------------------------|
| 排产约束条件 | apsSchedulingConstraints/getUseField | 增加自己的逻辑条件                             |                             |
| 排产下发   | /apsSchedulingIssueItem/insert       | 确认当天生产订单,后续进行排程操作, 可以对接第三方,进行订单排产下发确认 |

# 排程

| 功能点 | 路径 | 开发事项 |
|-----|----|------|

# 任务相关

[任务开发手册](task.md)