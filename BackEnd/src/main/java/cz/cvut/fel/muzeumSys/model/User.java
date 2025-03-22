package cz.cvut.fel.muzeumSys.model;

import cz.cvut.fel.muzeumSys.model.enums.Role;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;


@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(discriminatorType = DiscriminatorType.STRING)
@Getter
@Setter
@NoArgsConstructor
@Table(name = "users")

public abstract class User {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @Column(nullable = false, unique = true)
    private String email;

//    @Column(nullable = false)
    private String password;

    private Role role;


    private String firstName;

    private String lastName;

    @Column(nullable = false, unique = true)
    private String phoneNumber;

}
