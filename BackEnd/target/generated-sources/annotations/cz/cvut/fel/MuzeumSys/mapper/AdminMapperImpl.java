package cz.cvut.fel.MuzeumSys.mapper;

import cz.cvut.fel.nss.SaunaStudio.bo.AdminBO;
import cz.cvut.fel.nss.SaunaStudio.dto.AdminDTO;
import cz.cvut.fel.nss.SaunaStudio.model.Admin;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-02-20T15:46:18+0100",
    comments = "version: 1.6.0, compiler: javac, environment: Java 23.0.2 (Oracle Corporation)"
)
@Component
public class AdminMapperImpl implements AdminMapper {

    @Override
    public AdminBO adminToAdminBO(Admin admin) {
        if ( admin == null ) {
            return null;
        }

        AdminBO adminBO = new AdminBO();

        adminBO.setUsername( admin.getUsername() );
        adminBO.setPassword( admin.getPassword() );

        return adminBO;
    }

    @Override
    public AdminDTO adminBoToAdminDto(AdminBO adminBO) {
        if ( adminBO == null ) {
            return null;
        }

        AdminDTO adminDTO = new AdminDTO();

        adminDTO.setUsername( adminBO.getUsername() );
        adminDTO.setPassword( adminBO.getPassword() );

        return adminDTO;
    }

    @Override
    public AdminBO adminDtoToAdminBo(AdminDTO adminDTO) {
        if ( adminDTO == null ) {
            return null;
        }

        AdminBO adminBO = new AdminBO();

        adminBO.setUsername( adminDTO.getUsername() );
        adminBO.setPassword( adminDTO.getPassword() );

        return adminBO;
    }

    @Override
    public Admin adminBoToAdmin(AdminBO adminBO) {
        if ( adminBO == null ) {
            return null;
        }

        Admin admin = new Admin();

        admin.setUsername( adminBO.getUsername() );
        admin.setPassword( adminBO.getPassword() );

        return admin;
    }
}
