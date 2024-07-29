package com.oss.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Entity
@Data
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "address_id", unique = true)
    private Address address;

    @OneToOne(mappedBy = "client", cascade = CascadeType.ALL)
    @JsonIgnore
    private SmartDevice smartDevice;

    public Client() {
    }

    public Client(Long id, String name, Address address, SmartDevice smartDevice) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.smartDevice = smartDevice;
        if (smartDevice != null) {
            smartDevice.setClient(this);
        }
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public void setSmartDevice(SmartDevice smartDevice) {
        this.smartDevice = smartDevice;
        if (smartDevice != null) {
            smartDevice.setClient(this);
        }
    }
}