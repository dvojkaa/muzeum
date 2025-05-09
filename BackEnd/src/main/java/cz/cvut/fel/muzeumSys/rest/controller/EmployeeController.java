package cz.cvut.fel.muzeumSys.rest.controller;

import cz.cvut.fel.muzeumSys.dto.Record.ArtDto;
import cz.cvut.fel.muzeumSys.dto.Record.EmergencyRecordDto;
import cz.cvut.fel.muzeumSys.dto.Record.EmergencyRequestDto;
import cz.cvut.fel.muzeumSys.dto.Record.EmployeeDto;
import cz.cvut.fel.muzeumSys.model.Art;
import cz.cvut.fel.muzeumSys.model.EmergencyRecord;
import cz.cvut.fel.muzeumSys.model.Employee;
import cz.cvut.fel.muzeumSys.service.ArtService;
import cz.cvut.fel.muzeumSys.service.EmployeeService;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

//@CrossOrigin(origins = "${FRONTEND_URL}", allowCredentials = "true")
@RestController
@RequestMapping(value = "/employee")
public class EmployeeController {

    private final EmployeeService employeeService;
    private final ArtService artService;

    public EmployeeController(EmployeeService employeeService, ArtService artService) {
        this.employeeService = employeeService;
        this.artService = artService;
    }

    @PostMapping(value = "/create", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Employee> createEmployee(@RequestBody EmployeeDto employeeDto) {
        return ResponseEntity.ok(employeeService.createEmployee(employeeDto));
    }

    @PostMapping(value = "/info", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Employee>> getEmployees() {
        return ResponseEntity.ok(employeeService.getEmployees());
    }


    @PostMapping(value = "/edit", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Art> edit(@RequestBody List<ArtDto> artList) {
        return ResponseEntity.ok(artService.editArt(artList).getFirst());
    }

    @PostMapping(value = "/move", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Art> move(@RequestBody List<ArtDto> artList) {
        return ResponseEntity.ok(artService.editArt(artList).getFirst());
    }

    @PostMapping(value = "/editGroup", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Art>> editGroup(@RequestBody List<ArtDto> artList) {
        return ResponseEntity.ok(artService.editArt(artList));
    }

    @PostMapping(value = "/updateRecord", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<EmergencyRecord> updateRecord(@RequestBody EmergencyRecordDto emergencyRecordDto) {
        return ResponseEntity.ok(artService.updateRecord(emergencyRecordDto));
    }

    //    @PostMapping(value="/emergency", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
//    public ResponseEntity<List<Art>> emergency(@RequestBody List<ArtDto> artList) {
//        return ResponseEntity.ok(artService.emergency(artList));
//    }
    @PostMapping(value = "/emergency", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Art>> emergency(@RequestBody EmergencyRequestDto request) {
        return ResponseEntity.ok(artService.emergency(request.arts(), request.note()));
    }


}
