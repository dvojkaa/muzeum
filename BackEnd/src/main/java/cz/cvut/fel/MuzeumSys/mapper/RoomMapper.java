package cz.cvut.fel.MuzeumSys.mapper;

import cz.cvut.fel.MuzeumSys.bo.RoomBO;
import cz.cvut.fel.MuzeumSys.dto.RoomDTO;
import cz.cvut.fel.MuzeumSys.model.Room;
import org.mapstruct.Mapper;

/**
 * Mapper pro převod mezi entitami {@link Room}, {@link RoomBO} a {@link RoomDTO}.
 */
@Mapper(componentModel = "spring")
public interface RoomMapper {

    RoomBO roomToRoomBO(Room room);

    RoomDTO roomBoToRoomDto(RoomBO roomBO);

    RoomBO roomDtoToRoomBo(RoomDTO roomDTO);

    Room roomBoToRoom(RoomBO roomBO);
}
