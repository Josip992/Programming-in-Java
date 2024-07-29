package org.Lab2;

import org.eclipse.paho.client.mqttv3.*;

public class Main {
    public static void main(String[] args) {

        //mosquitto_sub -t "test" u cmd
        //cd /d I: force change
        Sensor S = new Sensor("1min1hour1day");
        Sensor B = new Sensor("1week1month1year");

        MqttOptions client = new MqttOptions("tcp://localhost:1883", "JavaMqttClient", "test", "./podatci.txt");

        WaterFlowData waterFlowData = new WaterFlowData(S, B);
        //client.run(waterFlowData.getSensors());
        //client.runAndWrite(waterFlowData.getSensors());

        client.sendFileContentAsMessages("podatci.txt");

        System.exit(0);
    }
}

