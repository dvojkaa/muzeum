package cz.cvut.fel.muzeumSys.repository;

import cz.cvut.fel.muzeumSys.model.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoomRepository extends JpaRepository<Room, Long> {

}
