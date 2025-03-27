//package cz.cvut.fel.muzeumSys.rest.controller;
//
//import cz.cvut.fel.muzeumSys.dto.Record.EmployeeDto;
//import cz.cvut.fel.muzeumSys.model.Employee;
//import cz.cvut.fel.muzeumSys.service.EmployeeService;
//import org.springframework.http.MediaType;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.List;
//
//@CrossOrigin(origins = "http://localhost:5173",allowCredentials = "true")
//@RestController
//@RequestMapping(value = "/employee")
//public class EmployeeController {
//
//    private final EmployeeService employeeService;
//
//    public EmployeeController(EmployeeService employeeService){
//        this.employeeService = employeeService;
//    }
//
//    @PostMapping(value="/create", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
//    public ResponseEntity<Employee> createEmployee(@RequestBody EmployeeDto employeeDto) {
//        return ResponseEntity.ok(employeeService.createEmployee(employeeDto));
//    }
//
//    @PostMapping(value="/info", produces = MediaType.APPLICATION_JSON_VALUE)
//    public ResponseEntity<List<Employee>> getEmployees() {
//        return ResponseEntity.ok(employeeService.getEmployees());
//    }
//
//    @PostMapping(value="/login", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
//    public ResponseEntity<Employee> login() {
//        return ResponseEntity.ok(employeeService.login());
//    }
//
//}
