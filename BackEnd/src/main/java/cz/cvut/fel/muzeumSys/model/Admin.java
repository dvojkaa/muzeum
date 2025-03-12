package cz.cvut.fel.muzeumSys.model;

import cz.cvut.fel.muzeumSys.model.enums.Role;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@ToString
@Entity
@Table(name = "admins")
//@NamedQueries({
//        @NamedQuery(name = "Admin.findByUsername", query = "SELECT a FROM admins a WHERE a.email = :email")
//})
public class Admin{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

//    @Basic(optional = false)
    @Column(nullable = false)
    private String email;

//    @Basic(optional = false)
    @Column(nullable = false)
    private String password;

//    @Basic(optional = false)
    @Column(nullable = false)
    private Role role;

    @Column(nullable = false)
//    @Basic(optional = false)
    private String firstName;

    @Column(nullable = false)
//    @Basic(optional = false)
    private String lastName;


}
