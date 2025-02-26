package cz.cvut.fel.muzeumSys.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Data Transfer Object for Admin entity.
 */

public record AdminDto (

        Long id,
        String firstName,
        String lastName,
        String email
){
}
