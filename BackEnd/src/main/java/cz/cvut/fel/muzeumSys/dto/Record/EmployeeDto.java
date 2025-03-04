package cz.cvut.fel.muzeumSys.dto.Record;


/**
 * Data Transfer Object for Employee entity.
 */

public record EmployeeDto (
        Long id,
        String firstName,
        String lastName,
        String phoneNumber,
        Boolean suspended
        ){
}
