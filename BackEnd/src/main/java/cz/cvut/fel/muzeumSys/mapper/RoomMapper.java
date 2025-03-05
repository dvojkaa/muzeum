package cz.cvut.fel.muzeumSys.mapper;

import cz.cvut.fel.muzeumSys.dto.Record.RoomDto;
import cz.cvut.fel.muzeumSys.model.Room;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface RoomMapper {

    RoomMapper INSTANCE = Mappers.getMapper(RoomMapper.class);

    RoomDto toDto(Room roomEntity);

    Room toEntity(RoomDto roomDto);

}
