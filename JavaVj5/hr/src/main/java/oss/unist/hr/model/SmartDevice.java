
package oss.unist.hr.model;

//import javax.persistence.*;
        import jakarta.persistence.*;
        import lombok.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SmartDevice {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String deviceName;

    @OneToOne
    @JoinColumn(name = "client_id", unique = true)
    private Client client;

    // Constructors, getters, and setters

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }
}