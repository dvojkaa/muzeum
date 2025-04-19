package cz.cvut.fel.muzeumSys.repository;

import cz.cvut.fel.muzeumSys.model.EmergencyRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmergencyRecordRepository extends JpaRepository<EmergencyRecord, Long> {
    List<EmergencyRecord> findByUserId(Long userId);
    List<EmergencyRecord> findByArtId(Long artId);
}

