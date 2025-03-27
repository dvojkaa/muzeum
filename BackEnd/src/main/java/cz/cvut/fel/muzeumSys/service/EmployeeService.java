//package cz.cvut.fel.muzeumSys.service;
//
//import cz.cvut.fel.muzeumSys.dto.Record.EmployeeDto;
//import cz.cvut.fel.muzeumSys.mapper.EmployeeMapper;
//import cz.cvut.fel.muzeumSys.model.Employee;
//import cz.cvut.fel.muzeumSys.repository.EmployeeRepository;
////import org.springframework.security.access.prepost.PreAuthorize;
//import org.springframework.stereotype.Service;
//
//import java.util.List;
//
//@Service
//public class EmployeeService {
//
//    private final EmployeeRepository employeeRepository;
//    private final EmployeeMapper employeeMapper;
//
//    public EmployeeService(EmployeeRepository employeeRepository, EmployeeMapper employeeMapper) {
//        this.employeeRepository = employeeRepository;
//        this.employeeMapper = employeeMapper;
//    }
//
////    @PreAuthorize("hasRole()")
//    public Employee createEmployee(EmployeeDto employeeDto) {
//        Employee employee = employeeMapper.toEntity(employeeDto);
//
//        employeeRepository.save(employee);
//        return employee;
//    }
//
//    public List<Employee> getEmployees() {
//        return employeeRepository.findAll();
//    }
//
//    public Employee login() {
////        return employeeRepository;
//        return null;
//    }
//}
