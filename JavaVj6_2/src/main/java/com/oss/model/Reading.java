package com.oss.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
public class Reading {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private int readingValue;

    @ManyToOne
    @JoinColumn(name = "smartDeviceId", nullable = false)
    private SmartDevice smartDevice;

    @Column(name = "readingMonth")
    private int readingMonth;

    @Column(name = "readingYear")
    private int readingYear;


    public void setReadingValue(int readingValue) {
        this.readingValue = readingValue;
    }

    public int getReadingValue() {
        return readingValue;
    }

    public void setReadingMonth(int readingMonth) {
        this.readingMonth = readingMonth;
    }

    public int getReadingMonth() {
        return readingMonth;
    }

    public void setReadingYear(int readingYear) {
        this.readingYear = readingYear;
    }

    public int getReadingYear() {
        return readingYear;
    }
    public Reading(int reading_value, SmartDevice smartDevice, int readingMonth, int readingYear) {
        this.readingValue = reading_value;
        this.smartDevice = smartDevice;
        this.readingMonth = readingMonth;
        this.readingYear = readingYear;
    }
}
