package cz.cvut.fel.muzeumSys.mapper;

import cz.cvut.fel.muzeumSys.dto.Record.ArtDto;
import cz.cvut.fel.muzeumSys.model.Art;
import cz.cvut.fel.muzeumSys.model.Group;
import cz.cvut.fel.muzeumSys.model.Room;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

@Mapper(componentModel = "spring")
public interface ArtMapper {


    @Mapping(source = "room.id", target = "room_id")
    @Mapping(source = "group.id", target = "group_id")
    ArtDto toDto(Art art);

    @Mapping(source = "room_id", target = "room", qualifiedByName = "roomFromId")
    @Mapping(source = "group_id", target = "group", qualifiedByName = "groupFromId")
    Art toEntity(ArtDto artDto);

    @Named("roomFromId")
    static Room roomFromId(Long id) {
        if (id == null) return null;
        Room room = new Room();
        room.setId(id);
        return room;
    }

    @Named("groupFromId")
    static Group groupFromId(Long id) {
        if (id == null) return null;
        Group group = new Group();
        group.setId(id);
        return group;
    }
}
