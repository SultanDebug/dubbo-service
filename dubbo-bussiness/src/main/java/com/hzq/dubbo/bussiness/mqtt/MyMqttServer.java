
package com.hzq.dubbo.bussiness.mqtt;

import ch.qos.logback.core.util.TimeUtil;
import io.micrometer.core.instrument.util.TimeUtils;
import org.eclipse.paho.client.mqttv3.*;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;


/**
 * 功能说明
 *
 * @author 黄震强
 * @version 1.0.0
 * @date 2020/10/22 15:29
 */
public class MyMqttServer {
    //tcp://MQTT安装的服务器地址:MQTT定义的端口号
//    public static final String HOST = "tcp://192.168.1.102:1883";
//    public static final String HOST = "test.mosquitto.org";
    public static final String HOST = "tcp://192.168.163.57:1883";
    //定义一个主题
    public static final String TOPIC = "hzqtopic";
    //定义MQTT的ID，可以在MQTT服务配置中指定
    private static final String clientid = "myserver";

    private MqttClient client;
    private MqttTopic mytopic;
    private String userName = "RainbowPush";
    private String passWord = "RainbowPush";

    private MqttMessage message;

    public MyMqttServer() throws MqttException {
        // MemoryPersistence设置clientid的保存形式，默认为以内存保存
        client = new MqttClient(HOST, clientid, new MemoryPersistence());
        connect();
    }

    private void connect() {
        MqttConnectOptions options = new MqttConnectOptions();
        options.setCleanSession(false);
        options.setUserName(userName);
        options.setPassword(passWord.toCharArray());
        // 设置超时时间
        options.setConnectionTimeout(10);
        // 设置会话心跳时间
        options.setKeepAliveInterval(20);
        try {
//            client.setCallback(new MyPushCallback());
            client.connect(options);

            mytopic = client.getTopic(TOPIC);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void publish(MqttTopic topic , MqttMessage message) throws MqttPersistenceException,
            MqttException {
        MqttDeliveryToken token = topic.publish(message);
        token.waitForCompletion();
        System.out.println("message is published completely! "
                + token.isComplete());
    }


    /*public static void main(String[] args) throws MqttException {
        MyMqttServer server = new MyMqttServer();

        for (int i = 0; i<10;i++){
            server.message = new MqttMessage();
            server.message.setQos(1);
            server.message.setRetained(true);
            server.message.setPayload(("hello,mytopic："+i).getBytes());
            server.publish(server.mytopic , server.message);
            System.out.println(server.message.isRetained() + "------ratained状态");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }*/
}
