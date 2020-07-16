/*
 * 深圳市灵智数科有限公司版权所有.
 */
package com.hzq.dubbo.util;

import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.action.admin.indices.create.CreateIndexRequest;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.client.Response;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.client.indices.CreateIndexResponse;
import org.elasticsearch.common.xcontent.XContentType;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.io.IOException;

/**
 * 功能说明
 *
 * @author 黄震强
 * @version 1.0.0
 * @date 2020/7/16 16:45
 */
@Component
@Slf4j
public class EsUtil implements InitializingBean {

    @Resource(name = "myClient")
    private RestHighLevelClient client;

//    private static RestHighLevelClient client;

    private ObjectMapper mapper = new ObjectMapper();

    /**
     * @PostContruct是spring框架的注解
     * spring容器初始化的时候执行该方法
     */
    @Override
    public void afterPropertiesSet() throws Exception {
        init();
    }
//    @PostConstruct
    public void init() {
//        client = this.restHighLevelClient;
    }

    /**
     * 创建索引
     *
     * @param index
     * @return
     */
    public boolean createIndex(String index) {
        //index名必须全小写，否则报错
        CreateIndexRequest request = new CreateIndexRequest(index);
        try {
            CreateIndexResponse indexResponse = client.indices().create(request);
            if (indexResponse.isAcknowledged()) {
                log.info("创建索引成功");
            } else {
                log.info("创建索引失败");
            }
            return indexResponse.isAcknowledged();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return false;
    }

    /**
     * 插入数据
     * @param index
     * @param type
     * @param object
     * @return
     */
    public String addData(String index, String type, String id, JSONObject object) {
        IndexRequest indexRequest = new IndexRequest(index, type, id);
        try {
            indexRequest.source(mapper.writeValueAsString(object), XContentType.JSON);
            IndexResponse indexResponse = client.index(indexRequest);
            return indexResponse.getId();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 检查索引
     * @param index
     * @return
     * @throws IOException
     */
    public boolean checkIndexExist(String index) {
        try {
            Response response = client.getLowLevelClient().performRequest("HEAD", index);
            boolean exist = response.getStatusLine().getReasonPhrase().equals("OK");
            return exist;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }


    /**
     * 获取低水平客户端
     * @return
     */
    public RestClient getLowLevelClient() {
        return client.getLowLevelClient();
    }
}
