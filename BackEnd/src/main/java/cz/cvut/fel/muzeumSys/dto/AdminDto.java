package cz.cvut.fel.muzeumSys.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Data Transfer Object for Admin entity.
 */
@Data
@NoArgsConstructor
public class AdminDto {

    private Long id;
    private String firstName;
    private String lastName;
    private String email;
}
