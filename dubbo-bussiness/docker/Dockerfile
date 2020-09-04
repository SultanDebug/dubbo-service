#创建镜像
#docker build -f Dockerfile -t bussiness:1.0 .
#启动容器
#docker run --name bussiness -p 21005:21005 -d bussiness:1.0
#查看日志
#docker logs -f bussiness
FROM java:8
VOLUME /tmp
ADD dubbo-bussiness-1.0.jar bussiness.jar
RUN bash -c 'touch /bussiness.jar'
ENTRYPOINT ["java","-jar","/bussiness.jar"]
