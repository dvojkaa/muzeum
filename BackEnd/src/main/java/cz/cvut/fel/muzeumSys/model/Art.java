package cz.cvut.fel.muzeumSys.model;

import cz.cvut.fel.muzeumSys.model.enums.Era;
import cz.cvut.fel.muzeumSys.model.enums.Type;
import jakarta.persistence.*;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Table(name = "art")
//@NamedQueries({
//        @NamedQuery(name = "Art.findByName", query = "SELECT a FROM Art a WHERE a.name = :name"),
//        @NamedQuery(name = "Art.findByEra", query = "SELECT a FROM Art a WHERE a.era = :era"),
//        @NamedQuery(name = "Art.findByType", query = "SELECT a FROM Art a WHERE a.type = :type"),
//        @NamedQuery(name = "Art.findByAuthor", query = "SELECT a FROM Art a WHERE a.author = :author")
//})
public class Art extends AbstractEntity {

//    @Basic(optional = false)
//    @Column(nullable = false)
    private String name;

    @Enumerated(EnumType.STRING)
//    @Basic(optional = false)
//    @Column(nullable = false)
    private Era era;

    @Enumerated(EnumType.STRING)
//    @Basic(optional = false)
//    @Column(nullable = false)
    private Type type;

//    @Basic(optional = false)
//    @Column(nullable = false)
    private String qrCode;

//    @Basic(optional = false)
//    @Column(nullable = false)
    private String description;

//    @Basic(optional = false)
//    @Column(nullable = false)
    private String parameters;

//    @Basic(optional = false)
//    @Column(nullable = false)
    private String author;

    @ManyToOne
//    @JoinColumn(name = "group_id", nullable = false)
    private Group group;

    @ManyToOne
//    @JoinColumn(name = "room_id", nullable = false)
    private Room room;
}
