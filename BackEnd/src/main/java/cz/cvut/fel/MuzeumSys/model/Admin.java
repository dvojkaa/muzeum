package cz.cvut.fel.MuzeumSys.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity(name = "admins")
@NamedQueries({
        @NamedQuery(name = "Admin.findByUsername", query = "SELECT a FROM admins a WHERE a.email = :email")
})
public class Admin extends User {

    @Column(nullable = false)
    @Basic(optional = false)
    private String firstName;

    @Column(nullable = false)
    @Basic(optional = false)
    private String lastName;

    @Column(nullable = false)
    @Basic(optional = false)
    private String email;

}
