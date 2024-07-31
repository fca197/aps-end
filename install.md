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

0. maven 配置
```xml

<?xml version="1.0" encoding="UTF-8"?>
<settings xmlns="http://maven.apache.org/SETTINGS/1.0.0"
          xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
          xsi:schemaLocation="http://maven.apache.org/SETTINGS/1.0.0 http://maven.apache.org/xsd/settings-1.0.0.xsd">
    <mirrors>
      <mirror>
        <id>mirror</id>
        <mirrorOf>central,jcenter,!2483310-snapshot-nvNXbv</mirrorOf>
        <name>mirror</name>
        <url>https://maven.aliyun.com/nexus/content/groups/public</url>
      </mirror>
    </mirrors>
    <servers>
      <server>
        <id>2483310-snapshot-nvNXbv</id>
        <username>66a05e4f70c8e3d8a56a3c1c</username>
        <password>8KXHuzGpkXwt</password>
      </server> 
    </servers>
    <profiles>
         <profile>
          <id>rdc</id>
            <properties>
              <altReleaseDeploymentRepository>
                2483310-snapshot-nvNXbv::default::https://packages.aliyun.com/maven/repository/2483310-snapshot-nvNXbv
              </altReleaseDeploymentRepository>
            </properties>  
            <repositories>
              <repository>
                <id>2483310-snapshot-nvNXbv</id>
                <url>https://packages.aliyun.com/maven/repository/2483310-snapshot-nvNXbv</url>
              </repository>
            </repositories>
        </profile> 
    </profiles>
    <activeProfiles>
        <activeProfile>rdc</activeProfile>
    </activeProfiles>
</settings>

 
```

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
    nohup java  -Dfile.encoding=UTF-8    -Xms512m -Xmx3000m  -XX:+UseZGC  -jar   -Dspring.profiles.active=fwq  -Dserver.port=8080 app.jar >  log.log  2>&1 &
  
```

6. 启动app

```bash

    sh run-app.sh
  
```

# 问题
0. 启动报错:  ServiceNotice.* 依赖注入失败

> 检查 mysql是否正确, mysql版本,mysql驱动版本, jdbc连接地址,用户名,密码
> 检查 redis是否正确, 包含 application.yml中 redis节点和 redisson.yml 配置


1. 启动报错:  Resource ortools-win32-x86-64/ was not found in ClassLoader jdk.internal.loader.ClassLoaders答:

> 问题原因: ortools依赖的window组建依赖过大,生产为linux系统,不用此组建,为避免打包过大,所以删除该依赖
> 方法1: 在类BootstrapApplication 中找到 :  Loader.loadNativeLibraries(); 注释掉该语句
> 方法2: pom.xml中 ortools-java依赖排除的节点删除,增加项目依赖



2. 打包后文件

> 打包后在[target](target) 生成两个jar文件 , app.jar  即部署jar文件
   
3. 执行时数据库缺少字段

> 目前项目还在开发中, sql文件未更新,请手动添加字段或微信联系获取最新数据结构
   
4. 列表展示字段为空,或修改字段显示宽度

> 请修改base_table_header中配置, 或菜单:基础配置=>表格头管理
> biz_key: 来自对应的业务ServiceImpl中setQueryListHeader方法查看

5. SpringBoot启动报错:java.nio.charset.MalformedInputException: Input length = 1解决方案

> 问题原因: 文件编码问题, 请使用 utf-8编码,修改方法: https://blog.51cto.com/u_2870645/5295690
> 修改后,重新获取源码,重新编译,重新打包

6. com.olivia:peanut-sdk:1.0.0-SNAPSHOT 下载失败

> maven 下载失败, 请修改setting.xml,增加仓库配置
