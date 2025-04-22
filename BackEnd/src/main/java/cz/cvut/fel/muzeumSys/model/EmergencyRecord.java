package cz.cvut.fel.muzeumSys.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@Entity
@Table(name = "emergency_record")
public class EmergencyRecord {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "art.id", nullable = false)
    private Art art;

    @ManyToOne
    @JoinColumn(name = "user.id", nullable = false)
    private User user;

    private LocalDateTime timestamp;

    private String note;

    // konstruktory, gettery, settery
}
