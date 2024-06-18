# 技术栈

## 后端

| 组建名称              | 依赖版本         | 说明                 |
|-------------------|--------------|--------------------|
| JDK               | jdk 17       | Jdk最低版本            |
| Spring Boot       | 3.2.0        | springboot版本       |
| mybatis-plus      | 3.5.3.1      | mybatisplus版本      |
| mybatis-plus-join | 1.4.8.1      | mybatisplus-join版本 |
| sqlite-jdbc       | 3.45.1.0     | sqlite驱动版本         |
| lombok            | 1.18.22      | lombok版本           |
| uber-h3           | 4.1.1        | 经纬度转code码          |
| hutool            | 5.8.0        | 常用工具库              |
| org.kie           | 7.14.0.Final | 规则引擎               |
| fastjson2         | 2.0.46       | fastjson2版本        |

### mybatis-plus

| 组件名称                             | 描述     | 使用点                      |
|----------------------------------|--------|--------------------------|
| TenantLineInnerInterceptor       | 租户插件   | 按租户进行数据隔离                |
| OptimisticLockerInnerInterceptor | 乐观锁插件  | 避免全表操作                   |
| PaginationInnerInterceptor       | 分页操作   | 使用 page操作                |
| MyMetaObjectHandler              | 数据自动填充 | 对象集成 BaseEntity, 给指定字段赋值 |

### 后端参数(peanut:)

| 参数名称                         | 参数类型             | 参数说明                                 | 默认值 |  
|------------------------------|------------------|--------------------------------------|-----|
| scanProperty2UseInTimeSecond | int              | 资产禁用时,N秒扫描内二次扫描时,启用该资产               | 60  |
| entityPackageName            | String           | 指定包名下查找类的BelongDb 注解,进行数据库表字段的初始化与修改 |
| redisKey                     | String           | redis key前缀                          |
| urlWhiteList                 | List<String>     | 白名单,指定接口时,可访问的接口,不进行二次包装             |
| aps.forecastMainPrefix       | String           | 预测主前缀                                |
| aps.forecastMainSuffix       | String           | 预测前缀                                 |
| sale2ProjectThreadSize       | int              | 预测子线程数                               |
| dingConfigList               | List<DingConfig> | 钉钉机器人配置                              |
| DingConfig.clientId          | String           | 钉钉clientId                           |
| DingConfig.clientSecret      | String           | 钉钉clientSecret                       |
| DingConfig.agentId           | Long             | 钉钉agentId                            |
| DingConfig.robotCode         | String           | 钉钉编号                                 |
| DingConfig.dingCode          | String           | 钉钉编号                                 |
| DingConfig.dingName          | String           | 钉钉名称                                 |

### 初始化类

| 初始化类                  | 说明       | 备注                                   |
|-----------------------|----------|--------------------------------------|
| MockResourceLoad      | mock数据配置 | 返回接口的mock值                           |
| DbInitConfig          | 数据库初始化操作 | 指定包名下查找类的BelongDb 注解,进行数据库表字段的初始化与修改 |
| ResultResponseWrapper | 接口返回值修改  | 根据请求头判断返回值是否进行二次包装                   |
| ServiceNotice         | 通知类接口    | 可以接入钉钉机器人等                           |
| WebBeanConfig         | web配置    | 字段序列化等配置,字段指定时MaskValue进行数据脱敏        |

# 数据库

| 组建名称        | 依赖版本     | 说明         |
|-------------|----------|------------|
| sqlite-jdbc | 3.45.1.0 | sqlite驱动版本 |
| h2          | 2.2.224  | h2驱动版本     |

---

# 杂记

## idea 插件

| 插件名称                       | 描述                                                       | 备注                                                                                                                  |
|----------------------------|----------------------------------------------------------|---------------------------------------------------------------------------------------------------------------------|
| Lombok                     | lombok                                                   | 常用方法注解                                                                                                              |
| EasyCode-MybatisCodeHelper | 代码生成器  ,模版见 [EasyCodeConfig.json](./EasyCodeConfig.json) | 插件地址 [官网](https://plugins.jetbrains.com/plugin/13847-easycode-mybatiscodehelper)                                    |
| drools                     | 规则引擎编辑器                                                  | 插件地址 [官网](https://plugins.jetbrains.com/plugin/16871-drools)                                                        |
| mybatisLogFree             | 输出可执行 mybaits 日志                                         | 插件地址 [官网](https://plugins.jetbrains.com/plugin/17898-mybatis-log-free)                                              |
| TONGYI Lingma              | 通义灵码 ,代码提示插件                                             | 插件地址 [官网](https://plugins.jetbrains.com/plugin/17809-tongyi-lingma--your-ai-coding-assistant-type-less-code-more- ) |


