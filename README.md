# Stick Basic Cloud 微服务架构

## 项目概述

这是将 BasicBack 单体应用拆分后的微服务架构项目，基于 Spring Cloud Alibaba + Nacos + Gateway + Dubbo 技术栈构建。

## 技术栈

- **Spring Boot**: 3.2.4
- **Spring Cloud Alibaba**: 2023.0.1.0
- **Nacos**: 服务注册与配置中心
- **Spring Cloud Gateway**: API 网关
- **Dubbo**: 3.3.0 (RPC 远程调用)
- **MyBatis**: 3.5.19
- **MySQL**: 8.0.33
- **Redis**: 缓存
- **Spring AI**: 1.0.0-M6 (配置在公共模块)
- **Elasticsearch**: 7.8.0 (搜索服务)
- **Aliyun OSS**: 3.10.2 (文件上传服务)

## 模块说明

### 1. stick-common (公共模块)
- 包含所有微服务共用的代码
- 常量类 (MessageConstant, SystemConstants)
- 通用工具类 (JwtUtil)
- 通用实体类 (Result)
- **AI 配置** (AiConfig - Spring AI 统一配置)
- 公共依赖配置

### 2. stick-api (远程调用接口模块)
- 定义所有微服务的 Dubbo 接口
- UserDubboService: 用户服务接口
- PostDubboService: 帖子服务接口
- TopicDubboService: 话题服务接口
- MailDubboService: 邮件服务接口
- StickDubboService: 磁贴服务接口
- DivinationDubboService: 占卜服务接口
- SearchDubboService: 搜索服务接口
- UploadDubboService: 文件上传服务接口

### 3. stick-gateway (网关模块)
- 统一入口，端口: 8080
- 路由配置:
  - /api/user/** -> stick-user-service
  - /api/post/** -> stick-post-service
  - /api/topic/** -> stick-topic-service
  - /api/mail/** -> stick-mail-service
  - /api/stick/** -> stick-stick-service
  - /api/divination/** -> stick-divination-service
  - /api/search/** -> stick-search-service
  - /api/upload/** -> stick-upload-service
- 负载均衡和请求转发

### 4. stick-user-service (用户服务)
- 端口: 8081
- Dubbo 端口: 20881
- 功能: 用户注册、登录、个人信息管理

### 5. stick-post-service (帖子服务)
- 端口: 8082
- Dubbo 端口: 20882
- 功能: 帖子发布、评论、点赞、收藏

### 6. stick-topic-service (话题服务)
- 端口: 8086
- Dubbo 端口: 20886
- 功能: 话题创建、关注、话题内容管理

### 7. stick-mail-service (邮件服务)
- 端口: 8087
- Dubbo 端口: 20887
- 功能: 邮件发送、收件箱、发件箱管理

### 8. stick-stick-service (磁贴服务)
- 端口: 8088
- Dubbo 端口: 20888
- 功能: 磁贴创建、点赞、评论管理

### 9. stick-divination-service (占卜服务)
- 端口: 8089
- Dubbo 端口: 20889
- 功能: 测字占卜、汉字识别、运势预测
- 使用公共模块的 AI 配置

### 10. stick-search-service (搜索服务)
- 端口: 8090
- Dubbo 端口: 20890
- 功能: 全文搜索、用户搜索、帖子搜索、话题搜索、磁贴搜索
- 集成 Elasticsearch

### 11. stick-upload-service (文件上传服务)
- 端口: 8091
- Dubbo 端口: 20891
- 功能: 图片上传、文件上传、文件管理
- 集成阿里云 OSS

## 启动顺序

1. 启动 Nacos 服务 (默认端口 8848)
2. 启动 Redis 服务
3. 启动 MySQL 数据库
4. 启动 Elasticsearch (搜索服务需要)
5. 启动各个微服务 (顺序不限，建议先启动 Gateway)
6. 启动网关: stick-gateway

## 配置说明

所有服务都支持环境变量配置：

- `NACOS_HOST`: Nacos 服务器地址 (默认: localhost)
- `NACOS_NAMESPACE`: Nacos 命名空间 (默认: 空)
- `NACOS_GROUP`: Nacos 分组 (默认: DEFAULT_GROUP)
- `SPRING_DATASOURCE_HOST`: MySQL 主机地址 (默认: localhost)
- `SPRING_DATA_REDIS_HOST`: Redis 主机地址 (默认: localhost)
- `SPRING_DATA_REDIS_PORT`: Redis 端口 (默认: 6379)
- `OPENAI_API_KEY`: OpenAI API 密钥
- `ALIBABA_CLOUD_ACCESS_KEY_ID`: 阿里云 Access Key ID
- `ALIBABA_CLOUD_ACCESS_KEY_SECRET`: 阿里云 Access Key Secret

## 服务间调用

微服务之间通过 Dubbo 进行 RPC 调用：

```java
@DubboReference
private UserDubboService userDubboService;

Result result = userDubboService.getProfile(userId);
```

## 网关路由

所有外部请求都通过网关进入：

```
http://localhost:8080/api/user/register
http://localhost:8080/api/post/create
http://localhost:8080/api/topic/create
http://localhost:8080/api/mail/send
http://localhost:8080/api/stick/create
http://localhost:8080/api/divination/predict
http://localhost:8080/api/search/query
http://localhost:8080/api/upload/image
```

## AI 配置说明

AI 功能作为公共配置，在 stick-common 模块中统一配置：

- AiConfig: Spring AI 配置类
- 所有需要 AI 功能的服务只需引入 stick-common 依赖即可使用
- 占卜服务使用 AI 进行测字和运势预测

## 后续工作

1. 将原项目的业务代码迁移到对应的微服务模块
2. 为每个服务实现 Dubbo 接口
3. 配置 Nacos 配置中心
4. 实现服务间调用
5. 添加分布式事务处理
6. 完善监控和日志
7. 实现 Elasticsearch 索引同步
8. 完善阿里云 OSS 文件管理
