package cz.cvut.fel.muzeumSys.mapper;

import cz.cvut.fel.muzeumSys.dto.Record.ArtDto;
import cz.cvut.fel.muzeumSys.model.Art;
import cz.cvut.fel.muzeumSys.model.Group;
import cz.cvut.fel.muzeumSys.model.Room;
import cz.cvut.fel.muzeumSys.model.enums.Era;
import cz.cvut.fel.muzeumSys.model.enums.Type;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-04-23T17:21:40+0200",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 23.0.2 (Oracle Corporation)"
)
@Component
public class ArtMapperImpl implements ArtMapper {

    @Override
    public ArtDto toDto(Art art) {
        if ( art == null ) {
            return null;
        }

        Long room_id = null;
        Long group_id = null;
        Long id = null;
        String name = null;
        String era = null;
        String type = null;
        String color = null;
        String description = null;
        String parameters = null;
        String author = null;
        String imgPath = null;

        room_id = artRoomId( art );
        group_id = artGroupId( art );
        id = art.getId();
        name = art.getName();
        if ( art.getEra() != null ) {
            era = art.getEra().name();
        }
        if ( art.getType() != null ) {
            type = art.getType().name();
        }
        color = art.getColor();
        description = art.getDescription();
        parameters = art.getParameters();
        author = art.getAuthor();
        imgPath = art.getImgPath();

        String qrCode = null;

        ArtDto artDto = new ArtDto( id, name, era, type, color, description, parameters, author, qrCode, imgPath, group_id, room_id );

        return artDto;
    }

    @Override
    public Art toEntity(ArtDto artDto) {
        if ( artDto == null ) {
            return null;
        }

        Art art = new Art();

        art.setRoom( ArtMapper.roomFromId( artDto.room_id() ) );
        art.setGroup( ArtMapper.groupFromId( artDto.group_id() ) );
        art.setId( artDto.id() );
        art.setName( artDto.name() );
        if ( artDto.era() != null ) {
            art.setEra( Enum.valueOf( Era.class, artDto.era() ) );
        }
        if ( artDto.type() != null ) {
            art.setType( Enum.valueOf( Type.class, artDto.type() ) );
        }
        art.setImgPath( artDto.imgPath() );
        art.setColor( artDto.color() );
        art.setDescription( artDto.description() );
        art.setParameters( artDto.parameters() );
        art.setAuthor( artDto.author() );

        return art;
    }

    @Override
    public void updateArtFromDto(ArtDto dto, Art entity) {
        if ( dto == null ) {
            return;
        }

        if ( dto.id() != null ) {
            entity.setId( dto.id() );
        }
        if ( dto.name() != null ) {
            entity.setName( dto.name() );
        }
        if ( dto.era() != null ) {
            entity.setEra( Enum.valueOf( Era.class, dto.era() ) );
        }
        if ( dto.type() != null ) {
            entity.setType( Enum.valueOf( Type.class, dto.type() ) );
        }
        if ( dto.imgPath() != null ) {
            entity.setImgPath( dto.imgPath() );
        }
        if ( dto.color() != null ) {
            entity.setColor( dto.color() );
        }
        if ( dto.description() != null ) {
            entity.setDescription( dto.description() );
        }
        if ( dto.parameters() != null ) {
            entity.setParameters( dto.parameters() );
        }
        if ( dto.author() != null ) {
            entity.setAuthor( dto.author() );
        }
    }

    private Long artRoomId(Art art) {
        if ( art == null ) {
            return null;
        }
        Room room = art.getRoom();
        if ( room == null ) {
            return null;
        }
        Long id = room.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }

    private Long artGroupId(Art art) {
        if ( art == null ) {
            return null;
        }
        Group group = art.getGroup();
        if ( group == null ) {
            return null;
        }
        Long id = group.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }
}
