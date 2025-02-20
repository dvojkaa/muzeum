package cz.cvut.fel.MuzeumSys.mapper;

import cz.cvut.fel.MuzeumSys.bo.GroupBO;
import cz.cvut.fel.MuzeumSys.dto.GroupDTO;
import cz.cvut.fel.MuzeumSys.model.Group;
import org.mapstruct.Mapper;

/**
 * Mapper pro převod mezi entitami {@link Group}, {@link GroupBO} a {@link GroupDTO}.
 */
@Mapper(componentModel = "spring")
public interface GroupMapper {

    GroupBO groupToGroupBO(Group group);

    GroupDTO groupBoToGroupDto(GroupBO groupBO);

    GroupBO groupDtoToGroupBo(GroupDTO groupDTO);

    Group groupBoToGroup(GroupBO groupBO);
}
