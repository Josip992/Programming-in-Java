package oss.unist.hr.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

//import javax.persistence.*;
import jakarta.annotation.PostConstruct;
import jakarta.persistence.*;

@Data  // Lombok annotation to generate getters, setters, toString, hashCode, and equals
@NoArgsConstructor  // Lombok annotation to generate a no-args constructor
@AllArgsConstructor  // Lombok annotation to generate an all-args constructor
@Entity
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String street;

    @Column(nullable = false)
    private String city;

}