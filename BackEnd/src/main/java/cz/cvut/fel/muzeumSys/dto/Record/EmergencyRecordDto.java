package cz.cvut.fel.muzeumSys.dto.Record;

import java.time.LocalDateTime;

public record EmergencyRecordDto (
     Long id,
     Long artId,
     String artName,
     Long userId,
     String username,
     LocalDateTime timestamp,
     String note
){
}
