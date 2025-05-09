package cz.cvut.fel.muzeumSys.model;

import cz.cvut.fel.muzeumSys.model.enums.Era;
import cz.cvut.fel.muzeumSys.model.enums.Type;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Entity
@Table(name = "art")
public class Art {

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
