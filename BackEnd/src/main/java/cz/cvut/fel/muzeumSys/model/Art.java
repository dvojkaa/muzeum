package cz.cvut.fel.muzeumSys.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import cz.cvut.fel.muzeumSys.model.enums.Era;
import cz.cvut.fel.muzeumSys.model.enums.Type;
import jakarta.persistence.*;
import lombok.*;

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
public class Art{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Enumerated(EnumType.STRING)
    private Era era;

    @Enumerated(EnumType.STRING)
    private Type type;

    private String qrCodePath;

    private String imgPath;

    private String color;

    private String description;

    private String parameters;

    private String author;

    @ManyToOne
    @JoinColumn(name = "group_id")

    private Group group;

    @ManyToOne
    @JoinColumn(name = "room_id")
    private Room room;

    private String base64Image;

}
