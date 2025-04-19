package cz.cvut.fel.muzeumSys.rest.controller;

import cz.cvut.fel.muzeumSys.dto.Record.ArtDto;
import cz.cvut.fel.muzeumSys.dto.Record.EmergencyRecordDto;
import cz.cvut.fel.muzeumSys.mapper.EmergencyRecordMapper;
import cz.cvut.fel.muzeumSys.service.EmergencyRecordService;
import cz.cvut.fel.muzeumSys.service.UserService;
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

//    @PostMapping
//    public ResponseEntity<?> markEmergency(@RequestBody List<ArtDto> arts,
//                                           @AuthenticationPrincipal UserDetails userDetails) {
//
//        List<EmergencyRecordDto> results = arts.stream()
//                .map(a -> service.create(a.id(), userId, null))
//                .map(mapper::toDto)
//                .toList();
//
//        return ResponseEntity.ok(results);
//    }

    @PostMapping(value="/info", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<EmergencyRecordDto>> getAll() {
        return ResponseEntity.ok(service.getAll().stream().map(mapper::toDto).toList());
    }
}
