package cz.cvut.fel.muzeumSys.mapper;

import cz.cvut.fel.muzeumSys.dto.Record.GroupDto;
import cz.cvut.fel.muzeumSys.model.Group;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-03-05T15:39:15+0100",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 21.0.6 (Amazon.com Inc.)"
)
@Component
public class GroupMapperImpl implements GroupMapper {

    @Override
    public GroupDto toDto(Group groupEntity) {
        if ( groupEntity == null ) {
            return null;
        }

        Long id = null;
        String name = null;
        String description = null;

        id = groupEntity.getId();
        name = groupEntity.getName();
        description = groupEntity.getDescription();

        GroupDto groupDto = new GroupDto( id, name, description );

        return groupDto;
    }

    @Override
    public Group toEntity(GroupDto groupDto) {
        if ( groupDto == null ) {
            return null;
        }

        Group group = new Group();

        group.setId( groupDto.id() );
        group.setName( groupDto.name() );
        group.setDescription( groupDto.description() );

        return group;
    }
}
