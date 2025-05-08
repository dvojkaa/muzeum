package cz.cvut.fel.muzeumSys.rest.controller;

import cz.cvut.fel.muzeumSys.dto.Record.EmergencyRecordDto;
import cz.cvut.fel.muzeumSys.mapper.EmergencyRecordMapper;
import cz.cvut.fel.muzeumSys.model.EmergencyRecord;
import cz.cvut.fel.muzeumSys.service.EmergencyRecordService;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/emergency")
public class EmergencyRecordController {

    private final EmergencyRecordService service;
    private final EmergencyRecordMapper mapper;

    public EmergencyRecordController(EmergencyRecordService service, EmergencyRecordMapper mapper) {
        this.service = service;
        this.mapper = mapper;
    }
//    @PostMapping(value = "/update", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
//    public ResponseEntity<EmergencyRecord> updateRecord(@RequestBody EmergencyRecordDto emergencyRecordDto) {
//        return ResponseEntity.ok(service.update(emergencyRecordDto));
//    }

    @PostMapping(value="/info", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<EmergencyRecord>> getAll() {
        return ResponseEntity.ok(service.getAll());
    }

    @DeleteMapping(value = "/delete/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<EmergencyRecord> deleteRecord(@PathVariable Long id) {
        return ResponseEntity.ok(service.delete(id));
    }
}
