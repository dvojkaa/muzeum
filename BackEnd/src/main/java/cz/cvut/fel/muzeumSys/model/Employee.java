package cz.cvut.fel.muzeumSys.model;

import cz.cvut.fel.muzeumSys.model.enums.Role;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity(name = "employee")
//@NamedQueries({
//        @NamedQuery(name = "Customer.findByUsername", query = "SELECT c FROM Employee c WHERE c.email = :username"),
//        @NamedQuery(name = "Customer.findByEmail", query = "SELECT c FROM Employee c WHERE c.email = :email"),
//        @NamedQuery(name = "Customer.findByPhoneNumber", query = "SELECT c FROM Employee c WHERE c.phoneNumber = :phoneNumber"),
//        @NamedQuery(name = "Customer.findByEmailAndPassword", query = "SELECT c FROM Employee c WHERE c.phoneNumber = :phoneNumber ")
//
//})
public class Employee{

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

    @Column(nullable = false)
//    @Basic(optional = false)
    private String phoneNumber;

    @Column(nullable = false)
    @Basic(optional = false)
    private Boolean suspended;

}
