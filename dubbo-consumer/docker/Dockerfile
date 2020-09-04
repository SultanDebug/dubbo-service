#创建镜像
#docker build -f Dockerfile -t consumer:1.0 .
#启动容器
#docker run --name consumer -p 21005:21005 -d consumer:1.0
#查看日志
#docker logs -f consumer
FROM java:8
VOLUME /tmp
ADD dubbo-consumer-1.0.jar consumer.jar
RUN bash -c 'touch /consumer.jar'
ENTRYPOINT ["java","-jar","/consumer.jar"]
