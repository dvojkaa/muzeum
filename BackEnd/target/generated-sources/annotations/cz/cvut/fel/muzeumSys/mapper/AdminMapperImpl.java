package cz.cvut.fel.muzeumSys.mapper;

import cz.cvut.fel.muzeumSys.dto.Record.AdminDto;
import cz.cvut.fel.muzeumSys.model.Admin;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-03-04T16:18:57+0100",
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

        AdminDto adminDto = new AdminDto( id, firstName, lastName, email, password, role );

        return adminDto;
    }

    @Override
    public Admin toEntity(AdminDto adminDto) {
        if ( adminDto == null ) {
            return null;
        }

        Admin admin = new Admin();

        return admin;
    }
}
