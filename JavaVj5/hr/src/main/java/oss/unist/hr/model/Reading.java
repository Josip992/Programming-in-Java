package oss.unist.hr.model;
//import javax.persistence.Entity;
//import javax.persistence.Id;
//import javax.persistence.*;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
public class Reading {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;

        @Column(nullable = false)
        private int value;

        @ManyToOne
        @JoinColumn(name = "device_id", nullable = false)
        private SmartDevice smartDevice;

        // Getters, setters, constructors

        public Reading(){

        }
        public Reading(int value, SmartDevice smartDevice) {
                this.value = value;
                this.smartDevice = smartDevice;
        }
}
