package cz.cvut.fel.muzeumSys.service;


import cz.cvut.fel.muzeumSys.dto.Record.RoomDto;
import cz.cvut.fel.muzeumSys.mapper.RoomMapper;
import cz.cvut.fel.muzeumSys.model.Art;
import cz.cvut.fel.muzeumSys.model.Room;
import cz.cvut.fel.muzeumSys.repository.RoomRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class RoomService {

    private final RoomRepository roomRepository;
    private final RoomMapper roomMapper;

    public RoomService(RoomRepository roomRepository, RoomMapper roomMapper) {
        this.roomRepository = roomRepository;
        this.roomMapper = roomMapper;
    }


    public ResponseEntity<Room> createRoom(RoomDto roomDto) {
        Room room = roomMapper.toEntity(roomDto);

        roomRepository.save(room);

        return ResponseEntity.ok(room);
    }

    public ResponseEntity<Room> addArt(Room room, Art art){
//        List<Art> arts = room.getArts();
//        arts.add(art);
//        art.setRoom(room);
        return ResponseEntity.ok(room);
    }
}
