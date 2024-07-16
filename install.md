# 安装文档

# 后端

## 后端配置

| 硬件  | 配置 | 说明 |
|-----|----|----|
| CPU | 2核 |    |
| 内存  | 4G |    | 

### 后端软件配置

| 软件    | 版本    | 说明                                                                        |
|-------|-------|---------------------------------------------------------------------------|
| MySQL | 8     |                                                                           |
| Redis | 6     | [安装](https://zhuanlan.zhihu.com/p/700234831)                              |
| JDK   | 17    | [环境变量配置](https://blog.csdn.net/weixin_52070377/article/details/133829439) |
| maven | 3.8.4 | 下载依赖打包使用                                                                  |

### 安装步骤

1. 下载源码

```bash
    
    git clone https://gitee.com/slsplatform/peanut-end.git
    
```

2. 切换到项目目录,并修改mysql,redis配置

```bash
    cd  peanut-end
    # 修改mysql,redis配置
    resources/application.yml
       spring:
         datasource:
           url: jdbc:mysql://127.0.0.1:3306/peanut?useUnicode=true&characterEncoding=utf-8&useSSL=false&serverTimezone=Asia/Shanghai
           username: root
           password: 123456
         redis:
           host: 127.0.0.1
           port: 6379
           password:
           redisson:
             # 此处也需要修改
             file: classpath:redisson.yml
```

3. 编译打包

```bash

    mvn install -DskipTests clean package

```

4. 获取打包文件

```bash

    target/app.jar
    
```

5. 编辑启动脚本 (run-app.sh)

```bash
 
    rm -rf   log.log
    nohup java  -Dfile.encoding=UTF-8    -Xms512m -Xmx3000m -XX:+UseG1GC   -jar   -Dspring.profiles.active=fwq  -Dserver.port=8080 app.jar >  log.log  2>&1 &

```

6. 启动app

```bash

    sh run-app.sh
    
```


# 问题
1. 启动报错:  Resource ortools-win32-x86-64/ was not found in ClassLoader jdk.internal.loader.ClassLoaders  
 答:
    问题原因: ortools依赖的window组建依赖过大,生产为linux系统,不用此组建,为避免打包过大,所以删除该依赖
    方法1: 在类BootstrapApplication 中找到 :  Loader.loadNativeLibraries(); 注释掉该语句
    方法2: pom.xml中 ortools-java依赖排除的节点删除,增加项目依赖

2. 

