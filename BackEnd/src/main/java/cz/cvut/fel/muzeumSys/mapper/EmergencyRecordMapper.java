package cz.cvut.fel.muzeumSys.mapper;

import cz.cvut.fel.muzeumSys.dto.Record.EmergencyRecordDto;
import cz.cvut.fel.muzeumSys.model.EmergencyRecord;
import org.springframework.stereotype.Component;

@Component
public class EmergencyRecordMapper {

    public EmergencyRecordDto toDto(EmergencyRecord record) {
        return new EmergencyRecordDto(
                record.getId(),
                record.getArt().getId(),
                record.getArt().getName(),
                record.getUser().getId(),
                record.getUser().getEmail(),
                record.getTimestamp(),
                record.getNote()
        );
    }

    public void updateRecordFromDto(EmergencyRecordDto emergencyRecordDto, EmergencyRecord existingRecord) {
    }
}
