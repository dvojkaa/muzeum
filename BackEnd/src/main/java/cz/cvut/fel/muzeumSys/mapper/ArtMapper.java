package cz.cvut.fel.muzeumSys.mapper;

import cz.cvut.fel.muzeumSys.dto.ArtDto;
import cz.cvut.fel.muzeumSys.model.Art;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ArtMapper {

    ArtDto toDto(Art artEntity);

    Art toEntity(ArtDto artDto);
}
