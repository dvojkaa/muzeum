package cz.cvut.fel.muzeumSys.mapper;


import cz.cvut.fel.muzeumSys.dto.Record.GroupDto;
import cz.cvut.fel.muzeumSys.model.Group;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface GroupMapper {

    GroupDto toDto(Group group);

    Group toEntity(GroupDto groupDto);

}
