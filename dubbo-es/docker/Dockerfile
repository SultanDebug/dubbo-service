#创建镜像
#docker build -f Dockerfile -t es:1.0 .
#启动容器
#docker run --name es -p 21005:21005 -d es:1.0
#查看日志
#docker logs -f es
FROM java:8
VOLUME /tmp
ADD dubbo-es-1.0.jar es.jar
RUN bash -c 'touch /es.jar'
ENTRYPOINT ["java","-jar","/es.jar"]
