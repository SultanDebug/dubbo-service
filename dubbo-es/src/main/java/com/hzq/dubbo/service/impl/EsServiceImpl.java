
package com.hzq.dubbo.service.impl;

import com.hzq.dubbo.service.EsService;
import com.hzq.dubbo.util.EsUtil;
import org.apache.http.HttpEntity;
import org.apache.http.HttpHost;
import org.apache.http.entity.ContentType;
import org.apache.http.nio.entity.NStringEntity;
import org.apache.http.util.EntityUtils;
import org.elasticsearch.client.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;

/**
 * 功能说明
 *
 * @author 黄震强
 * @version 1.0.0
 * @date 2020/7/16 16:19
 */
@Service
public class EsServiceImpl implements EsService {

    @Autowired
    private EsUtil esUtil;

    public void add() {
        RestHighLevelClient client = new RestHighLevelClient(RestClient.builder(new HttpHost("192.168.215.208", 9200, "http")));
    }

    /**
     * 根据index获取es数据
     *
     * @param
     * @return
     * @author 黄震强
     * @version 1.0.0
     * @date 2020/8/2 11:56
    */
    public String getByIdx(String idx) {
        try {
            String index = "/nba/nba/" + idx;
            HttpEntity entity = new NStringEntity(
                    "{ \"query\": { \"match_all\": {}}}",
                    ContentType.APPLICATION_JSON);
            String endPoint = "/nba/nba" + "/_search";
            Request request = new Request("GET",endPoint) ;
            request.setEntity(entity);

            Response response = esUtil.getLowLevelClient().performRequest(request);
            return EntityUtils.toString(response.getEntity());
        } catch (IOException e) {
            e.printStackTrace();
        }

        return "";
    }

}
