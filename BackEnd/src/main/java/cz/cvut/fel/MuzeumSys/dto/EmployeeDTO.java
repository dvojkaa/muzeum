package cz.cvut.fel.MuzeumSys.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Data Transfer Object for Employee entity.
 */
@Data
@NoArgsConstructor
public class EmployeeDTO {

    private Integer id;
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private Boolean suspended;
}
