#!/bin/bash

# 构建docker镜像
docker build -t quartz:1.0 -f Dockerfile .

# 创建启动容器
docker run -d --name quartz -m 500m -p 23333:2333 \
--log-driver json-file \
--log-opt max-size=10m \
--log-opt max-file=3 \
quartz:1.0
