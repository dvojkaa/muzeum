package cz.cvut.fel.muzeumSys.rest.controller;

import cz.cvut.fel.muzeumSys.dto.Record.GroupDto;
import cz.cvut.fel.muzeumSys.model.Group;
import cz.cvut.fel.muzeumSys.service.GroupService;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/group")
public class GroupController {

    private final GroupService groupService;

    public GroupController(GroupService groupService) {
        this.groupService = groupService;
    }


    @PostMapping(value="/create", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Group> createGroup(@RequestBody GroupDto groupDto) {
        return ResponseEntity.ok(groupService.createGroup(groupDto));
    }
}
