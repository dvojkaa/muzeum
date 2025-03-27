package cz.cvut.fel.muzeumSys.dto.Record;

public record UserDto (

        Long id,
        String firstName,
        String lastName,
        String email,
        String password,
        String role,
        String phoneNumber

){}