package cz.cvut.fel.MuzeumSys.bo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Business objekt pro entitu Group.
 */
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class GroupBO {
    private Integer id;
    private String name;
    private String description;
}
