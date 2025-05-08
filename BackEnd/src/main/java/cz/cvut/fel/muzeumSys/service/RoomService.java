package cz.cvut.fel.muzeumSys.service;


import cz.cvut.fel.muzeumSys.dto.Record.RoomDto;
import cz.cvut.fel.muzeumSys.mapper.RoomMapper;
//import cz.cvut.fel.muzeumSys.model.Art;
import cz.cvut.fel.muzeumSys.model.Room;
import cz.cvut.fel.muzeumSys.repository.RoomRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoomService {

    private final RoomRepository roomRepository;
    private final RoomMapper roomMapper;

    public RoomService(RoomRepository roomRepository, RoomMapper roomMapper) {
        this.roomRepository = roomRepository;
        this.roomMapper = roomMapper;
    }


    public Room createRoom(RoomDto roomDto) {
        Room room = roomMapper.toEntity(roomDto);

        roomRepository.save(room);
        return room;


    }

    public List<Room> getRooms() {
        return roomRepository.findAll();
    }
//
//    public ResponseEntity<Room> addArt(Room room, Art art){
////        List<Art> arts = room.getArts();
////        arts.add(art);
////        art.setRoom(room);
//        return ResponseEntity.ok(room);
//    }
}
