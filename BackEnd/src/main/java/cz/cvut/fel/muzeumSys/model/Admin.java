package cz.cvut.fel.muzeumSys.model;

import jakarta.persistence.*;
import lombok.*;

@DiscriminatorValue("ADMIN")
@Getter
@Setter
@ToString
@Entity
@Table(name = "admins")
//@NamedQueries({
//        @NamedQuery(name = "Admin.findByUsername", query = "SELECT a FROM admins a WHERE a.email = :email")
//})
public class Admin extends User {

}
