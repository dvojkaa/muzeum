package cz.cvut.fel.muzeumSys.dto.Record;

/**
 * Data Transfer Object for Admin entity.
 */

public record AdminDto(

        Long id,
        String firstName,
        String lastName,
        String email,
        String password,
        String phoneNumber
) {
}
