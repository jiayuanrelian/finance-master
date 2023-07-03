# 环境要求

- java8+
- maven3.5.0+
- zookeeper3.6.0+

# 模块说明

```
elasticjob
	mySimpleJob 定时任务1
	mySimpleJob2 定时任务2
	myShardingJob 分片任务1
	myShardingJob2 分片任务2
```

配置文件

```properties
# 2181是zookeeper端口号，需要保持一致，可在zookeeper的zoo.cfg进行修改
# ip和端口号按要求修改
elasticjob.reg-center.server-lists=127.0.0.1:2181   
elasticjob.reg-center.namespace=mySimpleJob
elasticjob.reg-center.max-sleep-time-milliseconds=8000
elasticjob.jobs.my-simple-job.elastic-job-class=job.com.simple.job.finance.MySimpleJob
elasticjob.jobs.my-simple-job.cron=0/10 * * * * ?
# 分片任务数量
elasticjob.jobs.my-simple-job.sharding-total-count=1   
```

# 定时任务

启动mySimpleJob模块和mySimpleJob2模块可实现定时任务的故障转移

# 分片任务

启动myShardingJob模块和myShardingJob2模块实现任务分片

# 打包

maven打包插件已经配置完成，可直接执行

```
mvn clean install
```

实现打包

