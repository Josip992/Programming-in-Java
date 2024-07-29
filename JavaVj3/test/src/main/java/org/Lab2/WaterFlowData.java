package org.Lab2;

import java.util.Objects;

public class WaterFlowData {

    public WaterFlowData() {
        this.sensors = new Sensor[0];
    }
    private Sensor[] sensors;

    public WaterFlowData(Sensor... sensors) {
        this.sensors = sensors;
    }

    public Sensor[] getSensors() {
        return sensors;
    }


}