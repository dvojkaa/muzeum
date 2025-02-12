package cz.cvut.fel.MuzeumSys.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Data Transfer Object for Admin entity.
 */
@Data
@NoArgsConstructor
public class AdminDTO {

    private Integer id;
    private String firstName;
    private String lastName;
    private String email;
}
