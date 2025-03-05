//package cz.cvut.fel.muzeumSys.model;
//
//import cz.cvut.fel.muzeumSys.model.enums.Role;
//import jakarta.persistence.*;
//import lombok.AllArgsConstructor;
//import lombok.Getter;
//import lombok.NoArgsConstructor;
//import lombok.Setter;
////import org.springframework.security.crypto.password.PasswordEncoder;
//
//@Getter
//@Setter
//@Inheritance(strategy = InheritanceType.JOINED)
//public abstract class User {
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long id;
//
//    @Basic(optional = false)
//    @Column(nullable = false)
//    private String email;
//
//    @Basic(optional = false)
//    @Column(nullable = false)
//    private String password;
//
//    @Basic(optional = false)
//    @Column(nullable = false)
//    private Role role;
//
////    public void encodePassword(PasswordEncoder passwordEncoder) {
////        this.password = passwordEncoder.encode(password);
////    }
//}
