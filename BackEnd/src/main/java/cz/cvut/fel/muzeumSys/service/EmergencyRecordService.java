package cz.cvut.fel.muzeumSys.service;

import cz.cvut.fel.muzeumSys.model.Art;
import cz.cvut.fel.muzeumSys.model.EmergencyRecord;
import cz.cvut.fel.muzeumSys.model.User;
import cz.cvut.fel.muzeumSys.repository.EmergencyRecordRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class EmergencyRecordService {

    private final EmergencyRecordRepository emergencyRecordRepository;

    public EmergencyRecordService( EmergencyRecordRepository emergencyRecordRepository) {
        this.emergencyRecordRepository = emergencyRecordRepository;
    }

    public EmergencyRecord create(Art art, User user, String note) {
        EmergencyRecord record = new EmergencyRecord();
        record.setArt(art);
        record.setUser(user);
        record.setTimestamp(LocalDateTime.now());
        record.setNote(note);
        return emergencyRecordRepository.save(record);
    }

    public List<EmergencyRecord> getAll() {
        return emergencyRecordRepository.findAll();
    }

    public void deleteAllByArtId(Long id) {
        emergencyRecordRepository.deleteAll(emergencyRecordRepository.findByArtId(id));
    }


    public void deleteAllByUserId(Long id) {
        emergencyRecordRepository.deleteAll(emergencyRecordRepository.findByUserId(id));
    }


    public void clearUserReferences(Long userId) {
        List<EmergencyRecord> records = emergencyRecordRepository.findByUserId(userId);
        for (EmergencyRecord record : records) {
            record.setUser(null);
        }
        emergencyRecordRepository.saveAll(records);
    }

//    public List<EmergencyRecord> getByUser(Long userId) {
//        return repository.findByUserId(userId);
//    }
//
//    public List<EmergencyRecord> getByArt(Long artId) {
//        return repository.findByArtId(artId);
//    }
}
