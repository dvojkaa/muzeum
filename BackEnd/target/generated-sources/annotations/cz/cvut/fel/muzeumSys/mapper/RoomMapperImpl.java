package cz.cvut.fel.muzeumSys.mapper;

import cz.cvut.fel.muzeumSys.dto.Record.RoomDto;
import cz.cvut.fel.muzeumSys.model.Room;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-03-09T22:04:45+0100",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 23.0.2 (Oracle Corporation)"
)
@Component
public class RoomMapperImpl implements RoomMapper {

    @Override
    public RoomDto toDto(Room roomEntity) {
        if ( roomEntity == null ) {
            return null;
        }

        Long id = null;
        String name = null;
        Integer floorNumber = null;

        id = roomEntity.getId();
        name = roomEntity.getName();
        floorNumber = roomEntity.getFloorNumber();

        RoomDto roomDto = new RoomDto( id, name, floorNumber );

        return roomDto;
    }

    @Override
    public Room toEntity(RoomDto roomDto) {
        if ( roomDto == null ) {
            return null;
        }

        Room room = new Room();

        room.setId( roomDto.id() );
        room.setName( roomDto.name() );
        room.setFloorNumber( roomDto.floorNumber() );

        return room;
    }
}
