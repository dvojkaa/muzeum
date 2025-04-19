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

    private final EmergencyRecordRepository repository;


    public EmergencyRecordService(EmergencyRecordRepository repository) {
        this.repository = repository;
    }

    public EmergencyRecord create(Art art, User user, String note) {
        EmergencyRecord record = new EmergencyRecord();
        record.setArt(art);
        record.setUser(user);
        record.setTimestamp(LocalDateTime.now());
        record.setNote(note);
        return repository.save(record);
    }

    public List<EmergencyRecord> getAll() {
        return repository.findAll();
    }

//    public List<EmergencyRecord> getByUser(Long userId) {
//        return repository.findByUserId(userId);
//    }
//
//    public List<EmergencyRecord> getByArt(Long artId) {
//        return repository.findByArtId(artId);
//    }
}
