package org.Lab2;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;

import java.util.Objects;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.FileReader;


public class MqttOptions {
    public MqttOptions() {

        this.broker = "tcp://localhost:1883";
        this.topic = "default_topic";
        try {
            MemoryPersistence persistence = new MemoryPersistence();
            this.client = new MqttClient(broker, "default_client_id", persistence);
            this.connOpts = new MqttConnectOptions();
            this.client.connect(connOpts);

        } catch (MqttException e) {
            e.printStackTrace();
        }
    }

    private Sensor[] sensors;
    private String broker;
    private static String topic;
    private static MqttClient client = null;
    MqttConnectOptions connOpts = null;

    private BufferedWriter fileWriter;
    private Gson gson;
    public MqttOptions(String broker, String clientId, String topic, String filePath) {
        this.broker = broker;
        this.topic = topic;
        try {

            MemoryPersistence persistence = new MemoryPersistence();

            client = new MqttClient(broker, clientId, persistence);
            connOpts = new MqttConnectOptions();
            client.connect(connOpts);

        } catch (MqttException e) {
            e.printStackTrace();
        }

        // Initialize file writer
        try {
            fileWriter = new BufferedWriter(new FileWriter(filePath, true)); // 'true' for appending
        } catch (IOException e) {
            e.printStackTrace();
        }
        // Initialize Gson
        this.gson = new GsonBuilder().setPrettyPrinting().create();

    }

    public void messageAndWrite(String message) {
        try {
            fileWriter.write(message + "\n");
            fileWriter.flush();

            MqttMessage mqttMessage = new MqttMessage(message.getBytes());
            mqttMessage.setQos(2);
            client.publish(topic, mqttMessage);
            //client.disconnect();

        } catch (MqttException e) {
            e.printStackTrace();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public void message(String message) {
        try {
            MqttMessage mqttMessage = new MqttMessage(message.getBytes());
            mqttMessage.setQos(2);
            client.publish(topic, mqttMessage);

        } catch (MqttException e) {
            e.printStackTrace();
        }
    }

    public String readFromFile(String filePath) {
        StringBuilder content = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                content.append(line).append("\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return content.toString();
    }

    public void sendFileContentAsMessages(String filePath) {
        String fileContent = readFromFile(filePath);
        String[] lines = fileContent.split("\n");

        for (String line : lines) {
            message(line.trim());
        }
    }

    public void run(Sensor[] sensors) {
        this.sensors = sensors;
        for (Sensor sensor : sensors) {
            if(Objects.equals(sensor.getType(), "Temperature")){
                message("Trenutna temperatura vode: "+ sensor.getTemperature() + " °C\n" );
            } else if (Objects.equals(sensor.getType(), "Pressure")) {
                message("Trenutni tlak vode: " + sensor.getPressure() + " Bar\n");
            } else if (Objects.equals(sensor.getType(), "1min1hour1day")) {
                message("Potrosnja u zadnjih 1 min: " + sensor.getConsumption1min() +  " m3 \nPotrosnja u zadnjih 10 min: " + sensor.getConsumption10min() + " m3\nPotrosnja u zadnjih 1 sat: "+ sensor.getConsumption1hour() +" m3\nPotrosnja u zadnjih 1 dan: " + sensor.getConsumption1day() +" m3\n");
            }else if (Objects.equals(sensor.getType(), "1week1month1year")) {
                message("Potrosnja u zadnjih 1 tjedan: " + sensor.getConsumption1week() +  " m3 \nPotrosnja u zadnjih 1 mjesec: " + sensor.getConsumption1month() + " m3\nPotrosnja u zadnjih godinu: " + sensor.getConsumption1year());
            }
        }
        try {
            client.disconnect();
        } catch (MqttException e) {
            throw new RuntimeException(e);
        }

    }
    public void runAndWrite(Sensor[] sensors) {
        this.sensors = sensors;
        for (Sensor sensor : sensors) {
            if(Objects.equals(sensor.getType(), "Temperature")){
                messageAndWrite("Trenutna temperatura vode: "+ sensor.getTemperature() + " °C\n" );
            } else if (Objects.equals(sensor.getType(), "Pressure")) {
                messageAndWrite("Trenutni tlak vode: " + sensor.getPressure() + " Bar\n");
            } else if (Objects.equals(sensor.getType(), "1min1hour1day")) {
                messageAndWrite("Potrosnja u zadnjih 1 min: " + sensor.getConsumption1min() +  " m3 \nPotrosnja u zadnjih 10 min: " + sensor.getConsumption10min() + " m3\nPotrosnja u zadnjih 1 sat: "+ sensor.getConsumption1hour() +" m3\nPotrosnja u zadnjih 1 dan: " + sensor.getConsumption1day() +" m3\n");
            }else if (Objects.equals(sensor.getType(), "1week1month1year")) {
                messageAndWrite("Potrosnja u zadnjih 1 tjedan: " + sensor.getConsumption1week() +  " m3 \nPotrosnja u zadnjih 1 mjesec: " + sensor.getConsumption1month() + " m3\nPotrosnja u zadnjih godinu: " + sensor.getConsumption1year());
            }
        }
        try {
            client.disconnect();
        } catch (MqttException e) {
            throw new RuntimeException(e);
        }

    }
}