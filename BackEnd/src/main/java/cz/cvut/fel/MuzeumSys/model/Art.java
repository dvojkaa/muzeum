package cz.cvut.fel.MuzeumSys.model;


import cz.cvut.fel.nss.SaunaStudio.model.AbstractEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
@Entity(name = "art")
@NamedQueries({
        @NamedQuery(name = "Art.findByname", query = "SELECT a FROM art a WHERE a.name = :name")
})
public class Art extends AbstractEntity {
    @Basic(optional = false)
    @Column(nullable = false)
    private String email;
    private String name;
    private Era era;
    private Type type;
    private Double qrCode;
    private String discription;
    private String parametrs;
    private String author;

}
