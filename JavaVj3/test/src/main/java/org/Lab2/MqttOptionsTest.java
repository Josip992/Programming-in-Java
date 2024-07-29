package org.Lab2;

import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.eclipse.paho.client.mqttv3.MqttClient;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class MqttOptionsTest {

    @Test
    public void testIsClientConnected() throws MqttException {

        MemoryPersistence persistence = new MemoryPersistence();
        MqttClient client = new MqttClient("tcp://localhost:1883", "JavaMqttClient", persistence);
        MqttConnectOptions connOpts = new MqttConnectOptions();
        client.connect(connOpts);

        assertNotNull(client);
        Assertions.assertTrue(client.isConnected());

        client.disconnect();
    }

}