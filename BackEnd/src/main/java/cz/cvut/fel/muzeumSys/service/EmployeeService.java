package cz.cvut.fel.muzeumSys.service;

import cz.cvut.fel.muzeumSys.mapper.EmployeeMapper;
import cz.cvut.fel.muzeumSys.repository.EmployeeRepository;
import org.springframework.stereotype.Service;

@Service
public class EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final EmployeeMapper employeeMapper;

    public EmployeeService(EmployeeRepository employeeRepository, EmployeeMapper employeeMapper) {
        this.employeeRepository = employeeRepository;
        this.employeeMapper = employeeMapper;
    }
}
