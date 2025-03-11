package cz.cvut.fel.muzeumSys.mapper;

import cz.cvut.fel.muzeumSys.dto.Record.AdminDto;
import cz.cvut.fel.muzeumSys.model.Admin;
import cz.cvut.fel.muzeumSys.model.enums.Role;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-03-09T22:04:45+0100",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 23.0.2 (Oracle Corporation)"
)
@Component
public class AdminMapperImpl implements AdminMapper {

    @Override
    public AdminDto toDto(Admin adminEntity) {
        if ( adminEntity == null ) {
            return null;
        }

        Long id = null;
        String firstName = null;
        String lastName = null;
        String email = null;
        String password = null;
        String role = null;

        id = adminEntity.getId();
        firstName = adminEntity.getFirstName();
        lastName = adminEntity.getLastName();
        email = adminEntity.getEmail();
        password = adminEntity.getPassword();
        if ( adminEntity.getRole() != null ) {
            role = adminEntity.getRole().name();
        }

        AdminDto adminDto = new AdminDto( id, firstName, lastName, email, password, role );

        return adminDto;
    }

    @Override
    public Admin toEntity(AdminDto adminDto) {
        if ( adminDto == null ) {
            return null;
        }

        Admin admin = new Admin();

        admin.setId( adminDto.id() );
        admin.setEmail( adminDto.email() );
        admin.setPassword( adminDto.password() );
        if ( adminDto.role() != null ) {
            admin.setRole( Enum.valueOf( Role.class, adminDto.role() ) );
        }
        admin.setFirstName( adminDto.firstName() );
        admin.setLastName( adminDto.lastName() );

        return admin;
    }
}
