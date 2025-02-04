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
        @NamedQuery(name = "Art.findByName", query = "SELECT a FROM art a WHERE a.name = :name"),
        @NamedQuery(name = "Art.findByEra", query = "SELECT a FROM art a WHERE a.era = :era"),
        @NamedQuery(name = "Art.findByType", query = "SELECT a FROM art a WHERE a.type = :type"),
        @NamedQuery(name = "Art.findByAuthor", query = "SELECT a FROM art a WHERE a.author = :author")
})
public class Art extends AbstractEntity {

    @Basic(optional = false)
    @Column(nullable = false)
    private String name;

    @Enumerated(EnumType.STRING)
    @Basic(optional = false)
    @Column(nullable = false)
    private Era era;

    @Basic(optional = false)
    @Column(nullable = false)
    private Type type;

    @Basic(optional = false)
    @Column(nullable = false)
    private Double qrCode;

    @Basic(optional = false)
    @Column(nullable = false)
    private String discription;

    @Basic(optional = false)
    @Column(nullable = false)
    private String parametrs;

    @Basic(optional = false)
    @Column(nullable = false)
    private String author;

}
