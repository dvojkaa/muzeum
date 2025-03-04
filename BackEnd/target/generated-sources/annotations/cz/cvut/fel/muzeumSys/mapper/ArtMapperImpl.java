package cz.cvut.fel.muzeumSys.mapper;

import cz.cvut.fel.muzeumSys.dto.Record.ArtDto;
import cz.cvut.fel.muzeumSys.model.Art;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-02-27T16:10:01+0100",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 23.0.2 (Oracle Corporation)"
)
@Component
public class ArtMapperImpl implements ArtMapper {

    @Override
    public ArtDto toDto(Art art) {
        if ( art == null ) {
            return null;
        }

        Long id = null;
        String name = null;
        String era = null;
        String type = null;
        String description = null;
        String parameters = null;
        String author = null;
        String qrCode = null;
        Integer group = null;
        Integer room = null;

        ArtDto artDto = new ArtDto( id, name, era, type, description, parameters, author, qrCode, group, room );

        return artDto;
    }

    @Override
    public Art toEntity(ArtDto artDto) {
        if ( artDto == null ) {
            return null;
        }

        Art art = new Art();

        return art;
    }
}
