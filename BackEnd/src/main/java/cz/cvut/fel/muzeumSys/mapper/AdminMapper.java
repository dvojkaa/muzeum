package cz.cvut.fel.muzeumSys.mapper;

import cz.cvut.fel.muzeumSys.dto.AdminDto;
import org.mapstruct.Mapper;

/**
 * Mapper pro převod mezi entitami {@link Admin}, {@link AdminBO} a {@link AdminDto}.
 *
 * <p>Tento mapper převádí data mezi různými vrstvami aplikace: mezi entitami, byznys objekty a datovými přenosovými objekty.</p>
 */
@Mapper(componentModel = "spring")
public interface AdminMapper {

}
