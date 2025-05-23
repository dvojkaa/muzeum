package cz.cvut.fel.muzeumSys.service;

import cz.cvut.fel.muzeumSys.dto.Record.GroupDto;
import cz.cvut.fel.muzeumSys.mapper.GroupMapper;
import cz.cvut.fel.muzeumSys.model.Group;
import cz.cvut.fel.muzeumSys.repository.GroupRepository;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class GroupService {

    private final GroupRepository groupRepository;
    private final GroupMapper groupMapper;

    public GroupService(GroupRepository groupRepository, GroupMapper groupMapper) {
        this.groupRepository = groupRepository;
        this.groupMapper = groupMapper;
    }

    public Group createGroup(GroupDto groupDto) {
        Group group = groupMapper.toEntity(groupDto);

        groupRepository.save(group);
        return group;
    }

    public List<Group> getGroups() {
        return groupRepository.findAll();
    }
}
