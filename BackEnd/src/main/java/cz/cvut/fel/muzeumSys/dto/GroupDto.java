package cz.cvut.fel.muzeumSys.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Data Transfer Object for Group entity.
 */
@Data
@NoArgsConstructor
public class GroupDto {

    private Long id;
    private String name;
    private String description;
}
