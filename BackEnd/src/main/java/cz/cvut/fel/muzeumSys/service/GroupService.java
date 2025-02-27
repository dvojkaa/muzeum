package cz.cvut.fel.muzeumSys.service;

import cz.cvut.fel.muzeumSys.mapper.GroupMapper;
import cz.cvut.fel.muzeumSys.repository.GroupRepository;
import org.springframework.stereotype.Service;


@Service
public class GroupService {

    private final GroupRepository groupRepository;
    private final GroupMapper groupMapper;

    public GroupService(GroupRepository groupRepository, GroupMapper groupMapper) {
        this.groupRepository = groupRepository;
        this.groupMapper = groupMapper;
    }
}
