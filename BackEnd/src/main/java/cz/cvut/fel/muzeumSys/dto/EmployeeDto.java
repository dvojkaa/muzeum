package cz.cvut.fel.muzeumSys.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Data Transfer Object for Employee entity.
 */
@Data
@NoArgsConstructor
public class EmployeeDto {

    private Long id;
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private Boolean suspended;
}
