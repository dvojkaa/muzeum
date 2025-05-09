package cz.cvut.fel.muzeumSys.service;

import cz.cvut.fel.muzeumSys.mapper.EmergencyRecordMapper;
import cz.cvut.fel.muzeumSys.model.Art;
import cz.cvut.fel.muzeumSys.model.EmergencyRecord;
import cz.cvut.fel.muzeumSys.model.User;
import cz.cvut.fel.muzeumSys.repository.EmergencyRecordRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class EmergencyRecordService {


    private final EmergencyRecordRepository emergencyRecordRepository;
    private final EmergencyRecordMapper emergencyRecordMapper;

    public EmergencyRecordService(EmergencyRecordRepository emergencyRecordRepository,
                                  EmergencyRecordMapper emergencyRecordMapper) {
        this.emergencyRecordRepository = emergencyRecordRepository;
        this.emergencyRecordMapper = emergencyRecordMapper;

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


    public EmergencyRecord delete(Long id) {
        if (!emergencyRecordRepository.existsById(id)) {
            throw new EntityNotFoundException("Nouzový záznam s ID " + id + " nebyl nalezen.");
        }
        EmergencyRecord emergencyRecord = emergencyRecordRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Dílo s ID " + id + " nebylo nalezeno."));
        emergencyRecordRepository.delete(emergencyRecord);
        return emergencyRecord;


    }

    public EmergencyRecord update(Long id, Art art, User user, LocalDateTime time, String note) {
        EmergencyRecord record = new EmergencyRecord();
        record.setId(id);
        record.setArt(art);
        record.setUser(user);
        record.setTimestamp(time);
        record.setTimestamp(LocalDateTime.now());
        record.setNote(note);
        return emergencyRecordRepository.save(record);
    }
}
