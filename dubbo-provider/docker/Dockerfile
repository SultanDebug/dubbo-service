#创建镜像
#docker build -f Dockerfile -t provider:1.0 .
#启动容器
#docker run --name provider -p 21005:21005 -d provider:1.0
#查看日志
#docker logs -f provider
FROM java:8
VOLUME /tmp
ADD dubbo-provider-1.0.jar provider.jar
RUN bash -c 'touch /provider.jar'
ENTRYPOINT ["java","-jar","/provider.jar"]
