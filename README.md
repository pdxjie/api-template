# api-template

`api-template` 是一个通用的后端开发模板，基于 Spring Boot、MySQL、Sa-Token 和 Redis 等技术栈，旨在为后端开发提供一个高效、简洁、可扩展的基础模板。该模板适合用于快速构建后端服务和 API 接口，并集成了常见的功能，如用户认证、权限管理、缓存机制等。

## 特性

- **Spring Boot**：基于 Spring Boot 构建，支持快速开发和部署。
- **MySQL**：集成 MySQL 数据库，支持常见的数据库操作。
- **Sa-Token**：集成 Sa-Token 实现安全的用户认证与权限控制。
- **Redis**：使用 Redis 实现分布式缓存，提升系统性能。
- **统一异常处理**：全局异常捕获与处理，统一返回格式。
- **日志管理**：集成日志记录系统，支持操作日志和异常日志。
- **Knife4j**：一个集 Swagger2 和 OpenAPI3 为一体的增强解决方案。

## 技术栈

- **后端框架**：Spring Boot
- **数据库**：MySQL
- **缓存**：Redis
- **认证与权限**：Sa-Token
- **接口文档**：Knife4j

## 项目结构

```plaintext
api-template
│
├── src
│   ├── main
│   │   ├── java
│   │   │   └── com
│   │   │       └── basis
│   │   │           └── apitemplate
│   │   │               ├── common         # 通用类
│   │   │               ├── config         # 配置类
│   │   │               ├── controller     # 控制器
│   │   │               ├── model          # 实体类
│   │   │               │     ├── vo       # 视图对象
│   │   │               │     ├── entity   # 实体类
│   │   │               │     ├── dto      # 数据传输对象
│   │   │               │     ├── enums    # 枚举类
│   │   │               │     └── constant # 常量
│   │   │               ├── service        # 服务层
│   │   │               │     ├── impl     # 业务实现层
│   │   │               ├── mapper         # 数据访问层
│   │   │               ├── exception      # 异常处理
│   │   │               └── utils          # 工具类
│   │   ├── resources
│   │   │   ├── database                   # 数据库脚本
│   │   │         └── init.sql             # 初始化项目数据库脚本
│   │   │   ├── mapper                     # MyBatis 映射文件
│   │   │   ├── application.yml            # 配置文件
│   └── test
│       └── java
│           └── com
│               └── example
│                   └── apitemplate
│                       └── ApiTemplateApplicationTests.java
│
├── pom.xml                                # 项目依赖
└── README.md                              # 项目说明文档
```
## 安装和运行
### 1. 克隆项目
```bash
git clone https://github.com/pdxjie/api-template.git
cd api-template
```
### 2. 配置数据库
在 application.yml 中配置 MySQL 数据库连接信息
```bash
spring:
  datasource:
    url: jdbc:mysql://localhost:3306/init.sql
    username: yourusername
    password: yourpassword
    driver-class-name: com.mysql.cj.jdbc.Driver
```
### 3. 配置 Redis(默认禁用)
```bash
spring:
  redis:
    host: localhost
    port: 6379
    password: yourpassword
```
### 4. 启动项目
使用 IDE 运行 ApiTemplateApplication.java
### 5. 访问 API 文档
启动项目后，您可以通过访问 http://localhost:8888/swagger-ui.html 来查看自动生成的 API 文档

## 常见功能
### 用户认证与权限控制
使用 Sa-Token 实现用户认证与权限控制，支持以下功能：
- 登录：支持用户通过用户名和密码进行登录。
- 登出：支持用户退出登录，清除会话信息。
- 权限检查：通过注解方式对接口进行权限控制。 
### 缓存管理
集成 Redis 作为缓存系统，支持缓存常用数据，提高性能。
### 错误处理
全局统一的异常处理机制，自动捕获并处理 API 请求中的异常，返回统一的错误格式。
### 日志记录
通过 AOP 记录操作日志和异常日志，方便后期追溯和问题排查。
## 贡献
欢迎提交 Issue 和 Pull Request。如果你有任何建议或改进意见，欢迎反馈！
## License
此文档已将开源协议修改为 **Apache 2.0**。您可以根据项目的实际情况进一步调整或补充。

