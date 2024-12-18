<div align="center">
  <img src="/assets/snow.svg" style="width:100px;height:100px;">
</div>
<p align="center">
<h2 align="center">Api-Template</h2>
`Api-Template` 是一个通用的轻量级后端开发模板，基于 Spring Boot、MySQL、Sa-Token 和 Redis 等技术栈，旨在为后端开发提供一个高效、简洁、可扩展的基础模板。该模板适合用于快速构建后端服务和 API 接口，并集成了常见的功能，如用户认证、权限管理、缓存机制，以及使用工厂 + 策略模式适配多种登录方式以及多种发送邮件的方式等。

## 特性

- **Spring Boot**：基于 Spring Boot 构建，支持快速开发和部署。
- **MySQL**：集成 MySQL 数据库，支持常见的数据库操作。
- **Sa-Token**：集成 Sa-Token 实现安全的用户认证与权限控制。
- **Redis**：使用 Redis 实现分布式缓存，提升系统性能。
- **统一异常处理**：全局异常捕获与处理，统一返回格式。
- **日志管理**：集成日志记录系统，支持操作日志和异常日志。
- **Knife4j**：一个集 Swagger2 和 OpenAPI3 为一体的增强解决方案。
- **本地文件上传**：支持本地文件上传，并自动保存到服务器。
- **发送邮件和手机验证码**：支持发送邮件和手机验证码，提升用户体验。
- **设计模式**：适配不同登录方式以及发送邮件的方式。

## 技术栈

- **后端框架**：Spring Boot
- **数据库**：MySQL
- **缓存**：Redis
- **认证与权限**：Sa-Token
- **接口文档**：Knife4j
- **手机短信**：阿里云 SMS
- **邮件**：Email

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
│   │   │               ├── annotations    # 自定义注解
│   │   │               ├── common         # 通用类
│   │   │               ├── configuration  # 配置类
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
│   │   │   ├── application-dev.yml        # 配置文件
│   │   │   ├── application-prod.yml       # 配置文件
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
    url: jdbc:mysql://localhost:3306/init?useUnicode=true&characterEncoding=utf8&serverTimezone=GMT%2B8
    username: yourusername
    password: yourpassword
    driver-class-name: com.mysql.cj.jdbc.Driver
```
### 3. 配置 Redis
```bash
spring:
  redis:
    host: localhost
    port: 6379
    password: yourpassword
```
### 4. 配置邮箱（按需设置）
```bash
  mail:
    host: smtp.163.com
    password: xxxx
    username: xxxxx
    default-encoding: UTF-8
    port: 465
    properties:
      mail:
        smtp:
          ssl:
            enable: true
```
### 5 配置 SMS 阿里云短信（按需设置）
```bash
aliyun:
  product: 'Dysmsapi'
  domain: 'dysmsapi.aliyuncs.com'
  accessKey: 'AccessKeyId'
  accessSecret: 'AccesskeySecret'
```
### 6. 启动项目
使用 IDE 运行 ApiTemplateApplication.java
### 7. 访问 API 文档
启动项目后，您可以通过访问 http://localhost:8888/doc.html 来查看自动生成的 API 文档

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

