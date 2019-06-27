# jobdemo


目前已经实现的功能：

- 定时任务数据库维护
- 失败后重试
- 自己维护的定时任务数据库删除以后，从quartz 队列移除。不再运行。

接口已经写好，页面懒得写，需要定时任务demo的可以参考。

## 编译运行

1. clone 本仓库
2. cd jobdemo
3. mvn clean install
4. 创建数据库，job 存储的数据库 jpa 会自己创建，quartz 需要执行脚本 ```/db/quartz-mysql.sql``` 文件来建表,在建表前需要手动创建"quartz"数据库
5. 启动 执行 ```org.gitors.jobdemo.JobdemoApplication``` 类 里面的 **main** 方法