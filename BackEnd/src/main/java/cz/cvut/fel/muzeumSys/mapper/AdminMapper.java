package cz.cvut.fel.muzeumSys.mapper;

import cz.cvut.fel.muzeumSys.dto.Record.AdminDto;
import cz.cvut.fel.muzeumSys.dto.Record.UserDto;
import cz.cvut.fel.muzeumSys.model.Admin;
import cz.cvut.fel.muzeumSys.model.User;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

/**
 * Mapper pro převod mezi entitami {@link Admin} a {@link AdminDto}.
 *
 * <p>Tento mapper převádí data mezi různými vrstvami aplikace: mezi entitami, byznys objekty a datovými přenosovými objekty.</p>
 */
@Mapper(componentModel = "spring")
public interface AdminMapper {

    AdminDto toDto(Admin adminEntity);

    Admin toEntity(AdminDto adminDto);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateAdminFromDto(UserDto userDto, @MappingTarget User existingUser);
}
