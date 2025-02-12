package cz.cvut.fel.MuzeumSys.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Data Transfer Object for Group entity.
 */
@Data
@NoArgsConstructor
public class GroupDTO {

    private Integer id;
    private String name;
    private String description;
}
