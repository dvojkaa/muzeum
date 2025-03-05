package cz.cvut.fel.muzeumSys.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString()
@Entity
@Table(name = "room")

public class Room {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    //    @Basic(optional = false)
//    @Column(nullable = false)
    private String name;
//
//    @Basic(optional = false)
//    @Column(nullable = false)
    private Integer floorNumber;

//    @OneToMany(mappedBy = "room", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
//    private List<Art> arts;
}
