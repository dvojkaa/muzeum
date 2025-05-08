package cz.cvut.fel.muzeumSys.rest.controller;

import cz.cvut.fel.muzeumSys.dto.Record.RoomDto;
import cz.cvut.fel.muzeumSys.model.Group;
import cz.cvut.fel.muzeumSys.model.Room;
import cz.cvut.fel.muzeumSys.service.RoomService;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/room")
public class RoomController {

    private final RoomService roomService;

    public RoomController(RoomService roomService) {
        this.roomService = roomService;
    }


    @PostMapping(value="/create", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Room> createRoom(@RequestBody RoomDto roomDto) {
        return ResponseEntity.ok(roomService.createRoom(roomDto));
    }
    @PostMapping(value="/info", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Room>> getRooms() {
        return ResponseEntity.ok(roomService.getRooms());
    }


//    @PostMapping(value="/addArt", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
//    public ResponseEntity<Room> addArt(Room room, Art art){
//        return roomService.addArt(room, art);
//    }

}
