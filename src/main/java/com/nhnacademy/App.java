package com.nhnacademy;

import java.util.UUID;
import org.eclipse.paho.client.mqttv3.IMqttClient;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;

/**
 * Hello world!
 */
public class App {


    public static void main(String[] args) throws InterruptedException{
        String publisherId = UUID.randomUUID().toString();
        try (IMqttClient client = new MqttClient("tcp://localhost:1883", publisherId)) {
            MqttConnectOptions options = new MqttConnectOptions();
            options.setAutomaticReconnect(true);
            options.setCleanSession(true);
            options.setConnectionTimeout(10);
            options.setWill("test/will", "Disconnected".getBytes(), 1 , false);
            // 어떠한 이유로 네트워크가 이상해졌을 때 일정 시간 데이터가 ㅇ오가지 않으면 다시 연결
           //options.setAutomaticReconnect(false);

        
        client.connect();
        client.publish("test/a/b/c",  new MqttMessage("Hello".getBytes()));
        client.disconnect();
        } catch (MqttException e) {
            e.printStackTrace();
        }
    }
}
