package cz.cvut.fel.muzeumSys.mapper;

import cz.cvut.fel.muzeumSys.dto.Entity.RoomDto;
import cz.cvut.fel.muzeumSys.model.Room;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface RoomMapper {


    RoomDto toDto(Room roomEntity);

    Room toEntity(RoomDto roomDto);

}
