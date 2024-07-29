package org.Lab2;

public class Sensor {
    public Sensor() {
        this.type = "DefaultType";
        this.minTemperature = 0.0;
        this.maxTemperature = 0.0;
        this.temperature = 0.0;
        this.minPressure = 0.0;
        this.maxPressure = 0.0;
        this.pressure = 0.0;
        this.consumption1min = 0.0;
        this.consumption10min = 0.0;
        this.consumption1hour = 0.0;
        this.consumption1day = 0.0;
        this.consumption1week = 0.0;
        this.consumption1month = 0.0;
        this.consumption1year = 0.0;
    }

    private String type;
    private double minTemperature;
    private double maxTemperature;
    private double temperature;
    private double minPressure;
    private double maxPressure;
    private double pressure;
    private double consumption1min;
    private double consumption10min;
    private double consumption1hour;
    private double consumption1day;
    private double consumption1week;
    private double consumption1month;
    private double consumption1year;

    public String getType(){
        return type;
    }
    public double getTemperature() {
        return temperature;
    }

    // Getter method for pressure
    public double getPressure() {
        return pressure;
    }

    // Getter methods for consumption data
    public double getConsumption1min() {
        return consumption1min;
    }

    public double getConsumption10min() {
        return consumption10min;
    }

    public double getConsumption1hour() {
        return consumption1hour;
    }

    public double getConsumption1day() {
        return consumption1day;
    }

    public double getConsumption1week() {
        return consumption1week;
    }

    public double getConsumption1month() {
        return consumption1month;
    }

    public double getConsumption1year() {
        return consumption1year;
    }

    public Sensor(String type){
        this.type = type;
        String error = "Error when deciding what type of data to display.";

        switch (type) {
            case "Temperature":
                minTemperature = -3266.8;
                maxTemperature = 3266.8;
                temperature = (minTemperature + (Math.random() * (maxTemperature - minTemperature))) / 10;
                break;
            case "Pressure":
                minPressure = 0;
                maxPressure = 65.336;
                pressure = (minPressure + (Math.random() * (maxPressure - minPressure))) / 100;
                break;
            case "1min1hour1day":
                consumption1min = Math.random() * 65336;
                consumption10min = Math.random() * 65336;
                consumption1hour = Math.random() * 65336;
                consumption1day = Math.random() * 65336;
                break;
            case "1week1month1year":
                consumption1week = (Math.random() * 6533.6) / 10;
                consumption1month = (Math.random() * 6533.6) / 10;
                consumption1year = (Math.random() * 6533.6) / 10;
                break;
            default:
                throw new RuntimeException("This is an exception message");
        }
    }
}