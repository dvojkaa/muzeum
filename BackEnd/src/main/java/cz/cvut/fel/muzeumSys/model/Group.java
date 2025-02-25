package cz.cvut.fel.muzeumSys.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString(exclude = "arts")
@Entity
@Table(name = "art_group")
@NamedQueries({

})
public class Group extends AbstractEntity {

    @Basic(optional = false)
    @Column(nullable = false, unique = true)
    private String name;

    @Basic(optional = true)
    @Column
    private String description;

    @OneToMany(mappedBy = "group", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<Art> arts;
}
