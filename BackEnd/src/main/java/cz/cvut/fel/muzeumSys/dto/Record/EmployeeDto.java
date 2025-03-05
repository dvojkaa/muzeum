package cz.cvut.fel.muzeumSys.dto.Record;


/**
 * Data Transfer Object for Employee entity.
 */

public record EmployeeDto (
        Long id,
        String email,
        String role,
        String password,
        String firstName,
        String lastName,
        String phoneNumber,
        Boolean suspended
        ){
}
